defmodule MeuModulo.Concat do
    def join(a, b \\ nil, separador \\ " ")

    def join(a, b, _) when is_nil(b) do 
        a
    end

    def join(a, b, separador) do 
        a <> separador <> b
    end
end