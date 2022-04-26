defmodule Math do
  # Exercise 4
  # Implement and run a function sum(n) that uses recursion to calculate the sum of the integers from 1 to n.
  # You’ll need to write this function inside a module in a separate file.
  # Then load up IEx, compile that file, and try your function.
  def sum(1), do: 1
  def sum(n), do: n + sum(n-1)

  # Exercise 5
  # Write a function gcd(x,y) that finds the greatest common divisor between two nonnegative integers.
  # Algebraically, gcd(x,y) is x if y is zero; it’s gcd(y, rem(x,y)) otherwise.

  # gcd(x,y) -> x when y is zero
  # gcd(x,y) -> y, rem(x,y)
  def gcd(x, 0), do: x
  def gcd(x, y), do: gcd(y, rem(x, y))
end
