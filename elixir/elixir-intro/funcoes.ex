defmodule Funcoes do
   def show() do
        itens = [%{produto: "TV", valor: 935.50}, 
                 %{produto: "Playstation", valor: 1200.90},
                 %{produto: "Notebook", valor: 3205.70} ] 
        soma = Enum.map(itens, &(Float.round(&1.valor - &1.valor * 0.2))) |>
               Enum.filter(&(&1 > 10.0)) |>
               Enum.sum
   end
end
