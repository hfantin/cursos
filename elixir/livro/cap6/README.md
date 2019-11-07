Exercicio do capitulo 6 do livro "Elixir - do zero à concorrencia" da casa do codigo.   

# Compilar
elixirc calculator.ex student.ex subject.ex

# Executar
iex
{resultado, melhor} = Caculator.start


# docs
h Studend
h 

# Outras formas de chamar módulos, como require , alias , use e import:

do [link](https://elixir-lang.org/getting-started/alias-require-and-import.html) na documentacao oficial:   


- Alias the module so it can be called as Bar instead of Foo.Bar   
alias Foo.Bar, as: Bar

- Require the module in order to use its macros:    
require Foo

- Import functions from Foo so they can be called without the `Foo.` prefix:   
import Foo

- Invokes the custom code defined in Foo as an extension point   
use Foo
