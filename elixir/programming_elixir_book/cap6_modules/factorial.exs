defmodule Factorial do
  def of(0), do: 1
  def of(n), do: n * of(n-1)
  # version 2 checking type and negative - guard clauses
  def of2(0), do: 1
  def of2(n) when is_integer(n) and n > 0 do
    n * of2(n-1)
  end
end
