defmodule MeuModulo.Arquivos do
    
    def ler(arq) do 
        IO.puts("- lendo arquvivo #{arq}:")
        case File.read(arq) do 
            {:ok, conteudo} -> IO.puts("\n #{conteudo} \n")
            {:error, :enoent} -> IO.puts("> arquivo inexistente")
            {:error, :eacces} -> IO.puts("> arquivo sem permissao de leitura")
            _ -> IO.puts("> erro desconhecido")
        end
    end

    def ler2(arq) do 
        case File.read(arq) do 
            {:ok, conteudo} -> IO.puts(conteudo)
            {:error, msg} -> case msg do 
                :enoent -> "arquivo inexistente"
                :eacces -> "arquivo sem permissao de leitura"
                _ -> "erro desconhecido"
            end
        end
    end

    def ler_teste(arq) do 
        File.read!(arq)
    end
end