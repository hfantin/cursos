defmodule ElixirTeste.Application do
  use Application

  def start(_type, _args) do
    children = [
      ElixirTeste.Agendador,
      ElixirTeste.ServidorMensagens
    ]

    opts = [strategy: :one_for_one, name: ElixirTeste.Supervisor]
    Supervisor.start_link(children, opts)
  end
end
