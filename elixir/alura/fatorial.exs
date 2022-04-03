defmodule Fatorial do
  # calcular o fatorial:  n! = n . (n – 1). (n – 2). (n – 3)
  def fatorial(0), do: 1

  def fatorial(numero) when is_integer(numero) and numero > 0 do
    numero * fatorial(numero - 1)
  end

  def fatorial(_), do: IO.puts("informe numero positivo")
end
