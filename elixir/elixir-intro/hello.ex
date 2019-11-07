# em erlang
# % module_name.erl
# -module(module_name).  % you may use some other name
# -compile(export_all).

# hello() ->
#   io:format("~s~n", ["Hello world!"]).


# elixirc <nome_programa> -> compilar
# module_name.ex

# executar: 
#   iex
#   c "hello.ex"
#   Hello.exibir 
defmodule Hello do
  def exibir() do
    IO.puts "Hello World"
  end
end
