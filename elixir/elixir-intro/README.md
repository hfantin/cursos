# Elixir Lang
1. Hello World:   
   elixir hello_world.exs
2. REPL -> iex   
   IO.puts("hello world")
   IO.puts "hello worl"
3. inspect para verificar o codigo.
   ex.: a = 2 + 2
        inspect a 
        "4"
4. conversao
   binario para decimal: 0b1111 -> 15   
   hexa para decimal:    0xF -> 15
   octal para decimal:   0o17 -> 15
5. Atoms   
   Atom.to_string(:teste)
   String.to_atom("teste")
6. Booleanos   
   true, false e nil(avaliado como false)
   :true, :false
   qualquer coisa diferente de false é true, ex:
   0 || false -> 0
7. Strings:   
   a = "teste"
   "string #{a}" -> "string teste"
8. Listas
   [1, "a", :true] ++ [2, 5] -> [ 1, "a", :true, 2, 5] 
9. tuplas:   
   {1, "a", 3}
   tuple_size
   elem tupla, 0
10. Mapas:
   mapa = %{"a" => "1", "b" => "2"}
   mapa["a"] -> "1"

11. Funções:   
    iex multiplicador.ex
        Multiplicador.multiplicar(2, 3)
    soma = &(&1, &2) 
        soma.(3, 5)
     multiplicador = fn a, b -> a * b end
         multiplicador.(3, 4)
     # when    
     mf = fn 
        a, b when a > 0 and b == 10 -> a + b
        a, b when is_atom(a) -> "#{a} é um atom"
     end 
        mf.(1, 10)
        mf.(:teste, 1)
12. pipe:   
   |> pega a saida da expressao a esquerda e passa como parametro pra funcao a direita    ex.:  
      colecao = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]   
      sem pipe:   
        Enum.map(colecao, &(&1 *2))
        Enum.filter(colecao, &(&1 > 2))
        Enum.filter(Enum.map(colecao, &(&1 * 2)), &(&1>4))
      com pipe:   
        Enum.map(colecao, &(&1 *2)) |> Enum.filter(&(&1 > 4))


      Soma de todos os itens com valor superior a 10.00:   
        itens = [%{produto: "Tv LG 32 polegadas", valor: 935.50}, %{produto: "Notebook Samsung 1TB", valor: 1599.00}, %{produto: "Barbeador Gilette", valor: 9.99}]

        Enum.map(itens, &(Float.round(&1.valor - &1.valor * 0.2))) |> Enum.filter(&(&1 > 10.00)) |> Enum.sum
        Resultado: 2027.0
   
13. Listas:
   