# AndroidRoomKotlin
Estudo da utilização do ORM Android Room juntamente com a linguagem Kotlin.
Este texto descreve os passos para a utilização deste recurso de forma simples

## 1 - Adicionando dependencias 
Primeiro passo é adicionar o Android Room ao seu projeto e para isso você pode obter as versões adequadas em 
**Android Developer Room**
https://developer.android.com/jetpack/androidx/releases/room

Você precisa adicionar a implementação do Room em seu arquivo app na área `gradle script` do seu projeto.
![alt text][add-dependenica]

Neste exemplo fora utilizada a seguinte configuração

 `def room_version = "2.1.0-alpha05"` 

    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

Adicione também no topo do mesmo arquivo 
`apply plugin: 'kotlin-kapt'`

Após inserir a dependencia aparecerá a opção para sincronizar as dependencias `syn now` no topo da janela, selecione esta opção e aguarde o processo de build. Se tudo der certo as notações necessárias estarão disponíveis para utilização.

### Atenção
para projetos Kotlin utiliza-se o **kapt** no lugar do **annotationProcessor**

## 2 - Criando o banco de dados
Para organização crie 3 diretórios para cada item que utilizaremos (database, dao e model)
No diretório database crie a classe que representará o banco de dados, neste exemplo foi utilizada a classe `DatabaseKotlin`.

![alt text][diretorios] 

A classe deve conter antes de sua declaração a notação `@Database`que espera como argumentos uma array de entidades, a versão do banco o parametro `exportSchema` se omitido tem por padrão o valor **false**.

A classe database precisa herdar a classe `RoomDatabase` e precisar ser `abstract` pois não queremos implementar os métodos da classe `RomDatabase`

Adicione também uma função abstrata que retorna uma instancia de `PessoaDao`, se preferir pode deixar esta linha comentada  até criar a classe `PessoaDao`.

Ao indicar que uma classe é uma entidade deste banco de dados o android studio alerta que ela precisa ter a notação `@Entity`.

## 3 - Descrição da tabela (Entidade) Pessoa
No diretório model deste exemplo está adicionada a classe `Pessoa` com os campos `id`, `idade`, `nome` e `profissão`.
A classe pessoa precisa ter a notação `Entity` antes de sua declaração e o campo id recebe a notação `@PrimaryKey` pode ser adicionado a função de auto incrementar adicionando o parâmetro `(autoGenerate = true)`.

![alt text][classe-database]

### Atenção
O Android Room precisa que sua classe de entidade possua um construtor vazio, os demais contrutores precisam receber a notação `@Ignore` antes de sua declaração.

## 4 - Escrevendo o Dao (Comportamento da entidade)
Como não implementaremos os métodos já disponibilizados pelo Android Room a classe `PessoaDao` é uma interface.
A classe `PessoaDao` deve receber a notação `@Dao` antes de ser declarada e com isso pode se indicar os metodos que realizam os compotamentos da classe.

Neste exemplo foram utilizados três métodos que o Android Room nos disponibiliza 
- `@Insert` para salvar as informações no banco
- `@Query` que realiza a consulta
- `@Delete` que deleta um registro
 
 As funções não necessitam de corpo pois as ações são realizadas pelo Android Room, sendo necessário para sistema apenas conhecer os nomes dos métodos que serão utilizados na aplicação.

## 5 - Utilização
Com o banco de dados  e entidade definidos já é possivel testar o comportamento.

Neste exemplo é preenchida uma lista de objetos do tipo `Pessoa` para que possamos salvar através do método `salvar` definido em `PessoaDao`.

Também são utilizados os seguintes métodos:
- `salvarPessoas` Que lê a lista de `Pessoa` salva no banco
- `getDatabase` Que retorna uma instancia do `DatabaseKotlin` para acessarmos as informações do banco
- `listarTodos` Que retorna uma `String` com informações formatadas de todos os registros salvos de pessoas

Neste exemplo somente é salva a lista de objetos do tipo `Pessoa` em seguida é impressa a lista com todos os registros salvos no banco.

### Atenção
Em `getDatabase()` é utilizado o método `allowMainThreadQueries()`, esta pratica não é recomendada devido a possibilidade de sobrecarga que a manipulação de grandes volumes de dados podem ocasionar no aparelho.

### Observação final
O método `salvar` é ativo dentro método `onCreate` então toda vez que a activitie for redesenhada a função será executada e os dados da lista são reinseridos, inclusive quando viramos o aparelho de lado, já que ele redesenha a activitie em uma nova orientação.

[classe-database]: https://github.com/flaviodiminuto/AndroidRoomKotlin/blob/master/app/Prints/ClasseDatabase.PNG
[add-dependenica]: https://github.com/flaviodiminuto/AndroidRoomKotlin/blob/master/app/Prints/ClasseDatabase.PNG
[diretorios]: https://github.com/flaviodiminuto/AndroidRoomKotlin/blob/master/app/Prints/DiretorioDatabase.PNG


### Referencias

[Android Architecture Components com Kotlin: Persistindo dados com Room](https://medium.com/collabcode/android-archtecture-components-com-kotlin-persistindo-dados-com-room-f8c9eba58854)

[Persistence Data in Kotlin](https://medium.com/@biratkirat/9-persistence-data-in-kotlin-a0998009a92a)
