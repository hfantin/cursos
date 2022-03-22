#  retorna a lista de valores
defmodule MeuModulo.LoopDesafio do
    def tabuada(numero) when is_integer(numero) do
        tabuada(numero, 1)
    end

    defp tabuada(_, 11), do: []

    defp tabuada(numero, multiplicador) do
        [numero * multiplicador | tabuada(numero, multiplicador + 1)]
    end
end