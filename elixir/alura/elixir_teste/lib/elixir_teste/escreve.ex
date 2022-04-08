defmodule Mix.Tasks.Escreve do
  @moduledoc """
  Documentação completa da tarefa

  `mix escreve`
  """

  use Mix.Task

  @shortdoc "escreve um numero aleatorio no arquivo.txt"
  def run(_) do
    ElixirTeste.EscreveNumeroAleatorio.escreve()
  end
end
