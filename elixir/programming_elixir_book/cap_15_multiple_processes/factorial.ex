defmodule Factorial do
  def factorial(0), do: 1
  #  this function is not tail recursive because of the multiplication
  def factorial(n), do: n * factorial(n - 1)
end
