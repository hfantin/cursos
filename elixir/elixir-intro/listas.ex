defmodule Listas do
    
    # lista simples - head - tail
    def listar() do
        lista = [1 | [2, 3, 4]] # o mesmo que lista = [1, 2, 3, 4]
        lista
    end

    def headTail() do
        [head | tail] = [1, 2, 3, 4, 5]
        head
    end
    #list comprehension - percorrer listas
    def percorrer() do
        lista = for x <- [1, 2, 3], do: x * 2
    end

    # filtrar lista
    def filtrar_int() do
        lista = for x <- [1, 2, 3, :hamilton, :mari], is_integer(x), do: x
    end

    def filtrar_atom() do
        lista = for x <- [1, 2, 3, :hamilton, :mari], is_atom(x), do: x
    end

    def filtrar_idade(id \\18) do
        maior_idade = fn {nome, idade} -> idade >= id end
        estudantes = [{"Hamilton", 40}, {"Mari", 32}, {"Samuca", 12}, {"Isadora", 13}, {"Angelo", 15}]
        lista = for est <- estudantes, maior_idade.(est), do: est
    end

    # def filtrar_id(id \\18) do
    #     maior_idade = &(&1, &2 >= id)
    #     estudantes = [{"Hamilton", 40}, {"Mari", 32}, {"Samuca", 12}, {"Isadora", 13}, {"Angelo", 15}]
    #     lista = for est <- estudantes, maior_idade.(est), do: est
    # end

    def filtrar_tupla() do
       for x <- [1, 2, 3, :hamilton, :mari], is_integer(x), do: { x, x * 2 }
    end

    def filtrar_lista() do
       for {nome, idade} <- [{"hamilton", 40}, {"Mari", 32}, {"Samuca", 12}], do: nome
    end

    def filtrar_mapa() do
       for {nome, idade} <- [{"hamilton", 40}, {"Mari", 32}, {"Samuca", 12}], do: %{nome: nome, idade: idade}
    end

    def filtrar_duas_lista() do
       for a <- [1, 2], b <- [3, 4], do: {a, b}
    end

    def filtrar_into() do
       for {nome, idade} <- [{"hamilton", 40}, {"Mari", 32}, {"Samuca", 12}], into: %{}, do: {nome, idade}
    end

    def filtrar_into_existente() do
       for {nome, idade} <- [{"hamilton", 40}, {"Mari", 32}, {"Samuca", 12}], into: %{"jovem" => 10}, do: {nome, idade}
    end
end