defmodule ElixirTeste.ServidorMensagens do
  use GenServer

  def start_link(_) do
    IO.puts "start link"
    GenServer.start_link(__MODULE__, :ok, name: :servidor_mensagens)
  end

  def init(:ok) do
    IO.puts "iniciando servidor..."
    {:ok, %{}}
  end

  def handle_cast(:escreve, _) do
    IO.puts "enviando mensagem..."
    ElixirTeste.EscreveNumeroAleatorio.escreve
    {:noreply, %{}}
  end

end
