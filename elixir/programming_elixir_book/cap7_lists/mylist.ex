defmodule MyList do
  def len([]), do: 0
  def len([_head | tail]), do: 1 + len(tail)

  def square([]), do: []
  def square([head | tail]), do: [head * head | square(tail)]

  def add_1([]), do: []
  def add_1([head | tail]), do: [head + 1 | add_1(tail)]

  def map([], _func), do: []
  def map([head | tail], func), do: [func.(head) | map(tail, func)]

  def reduce([], value, _func), do: value
  def reduce([head | tail], value, func), do: reduce(tail, func.(head, value), func)

  # exercise 1
  # Write a mapsum function that takes a list and a function. It applies the
  # function to each element of the list and then sums the result, so MyList.mapsum [1, 2, 3], &(&1 * &1) is 14
  def mapsum([], _func), do: 0
  def mapsum([head | tail], func), do: func.(head) + mapsum(tail, func)

  # exercise 2
  # Write a max(list) that returns the element with the maximum value in the
  # list. (This is slightly trickier than it sounds.)
  def max([x]), do: x
  def max([head | tail]), do: Kernel.max(head, max(tail))


  # An Elixir single-quoted string is actually a list of individual character
  # codes. Write a caesar(list, n) function that adds n to each list element,
  # wrapping if the addition results in a character greater than z.
  # iex> MyList.caesar('ryvkve', 13)
  # ?????? :)
  def caesar([], _n), do: []
  def caesar([head | tail], n) when head + n <= ?z, do: [head + n | caesar(tail, n)]
  def caesar([head | tail], n), do: [head + n - 26 | caesar(tail, n)]
end
