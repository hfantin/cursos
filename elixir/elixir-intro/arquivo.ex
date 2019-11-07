defmodule Arquivo do
    def gravar(arquivo \\"arquivo.txt", texto \\"teste")  do
        {:ok, file} = File.open(arquivo, [:append]) # :write sobrescreve
        IO.binwrite(file, "#{texto}\n")
        File.close(arquivo)
    end

    def gravar2(arquivo \\"arquivo.txt", texto \\"teste")  do
        File.write(arquivo, texto)
    end

    def ler(arquivo \\"arquivo.txt")  do
       {:ok, conteudo} = File.read(arquivo)
       conteudo
    end
end