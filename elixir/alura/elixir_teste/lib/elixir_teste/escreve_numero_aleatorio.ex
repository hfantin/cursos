defmodule ElixirTeste.EscreveNumeroAleatorio do
  def escreve do
    caminho_arquivo =
      Path.join([
        :code.priv_dir(:elixir_teste),
        'arquivo.txt'
      ])

    numero_aleatorio = :rand.uniform(1000)
    IO.puts("gravando #{numero_aleatorio} em #{caminho_arquivo}")
    File.write!(caminho_arquivo, "Numero aleatorio: #{numero_aleatorio}")
  end
end
