defmodule Lazy do

    def filtrar_lista(quantidade \\10) do
        Enum.map(1..20_000_000, &(&1)) |> Enum.take(quantidade)
    end

    # stream é mais rapido mas deve ser usado somente com listas grandes
    def filtrar_stream(quantidade \\10) do
        Stream.map(1..20_000_000, &(&1)) |> Enum.take(quantidade)
    end

end