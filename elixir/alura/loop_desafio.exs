#  retorna a lista de valores
defmodule MeuModulo.LoopDesafio do
    def tabuada(numero) do
        tabuada(numero, 1)
    end

    # defp tabuada(_, 11), do: nil
    defp tabuada(numero, 10), do: [numero * 10]
    defp tabuada(numero, multiplicador) do
        # IO.puts("#{numero} x #{multiplicador} = #{numero * multiplicador}")
        # tabuada(numero, multiplicador + 1)
        #  [0 | list]
        [numero * multiplicador | tabuada(numero, multiplicador + 1)]

    end
end