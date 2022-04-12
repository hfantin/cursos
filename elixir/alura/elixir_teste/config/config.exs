use Mix.Config

config :elixir_teste, ElixirTeste.Agendador,
  jobs: [
    # Every minute
    {"* * * * *",  fn -> GenServer.cast(:servidor_mensagens, :escreve) end }
  ]
