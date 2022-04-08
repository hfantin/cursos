defmodule MeuModulo.Math do
  def soma(p1, p2) do
    p1 + p2
  end

  def zero?(0), do: true
  def zero?(x) when is_integer(x), do: false

end
