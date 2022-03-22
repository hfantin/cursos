#  retorna a lista de valores
defmodule MeuModulo.LoopDesafio do
    def tabuada(numero) when is_integer(numero) do
        tabuada(numero, 1)
    end

    defp tabuada(_, 11, lista), do: lista

    defp tabuada(numero, multiplicador, lista) do
        tabuada(numero, multiplicador + 1,  valores ++ [numero * multiplicador])
    end
end