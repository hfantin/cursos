defmodule Exercises do
  #  Implement the following Enum functions using no library functions or list
  #  comprehensions: all? , each , filter , split , and take . You may need to use an if
  #  statement to implement filter . The syntax for this is
  def all?([], _fun), do: true
  def all?([head | tail], fun), do: all?(tail, fun) and fun.(head)

  def each([], _fun), do: []
  def each([head | tail], fun), do: [fun.(head) | each(tail, fun)]

  def filter([], _fun), do: []

  def filter([head | tail], fun) do
    if fun.(head) do
      [head | filter(tail, fun)]
    else
      filter(tail, fun)
    end
  end

  def split(list, count), do: _split(list, [], count)
  defp _split([], front, _), do: [Enum.reverse(front), []]
  defp _split(tail, front, 0), do: [Enum.reverse(front), tail]

  defp _split([head | tail], front, count) do
    _split(tail, [head | front], count - 1)
  end

  def take(list, n), do: hd(split(list, n))

  # (Hard) Write a flatten(list) function that takes a list that may contain any
  # number of sublists, which themselves may contain sublists, to any depth.
  # It returns the elements of these lists as a flat list.
  # iex> MyList.flatten([ 1, [ 2, 3, [4] ], 5, [[[6]]]])
  # [1,2,3,4,5,6]
  # The simplest version is probably to use list concatenation. However,
  # this version ends up rebuilding the list at each step
  def flatten1([]), do: []
  def flatten1([head | tail]), do: flatten1(head) ++ flatten1(tail)
  def flatten1(head), do: [head]

  # José Valim came up with a different implementation. It is interesting
  # to spend some time with this, because it breaks down the problem
  # a little differently. Rather that extract individual elements
  # to built the result list, it treats the original list more like
  # a tree, flattening subtrees on the right and merging the results
  # into a tree that itself gets flattened.
  def flatten(list), do: do_flatten(list, [])

  def do_flatten([h | t], tail) when is_list(h) do
    do_flatten(h, do_flatten(t, tail))
  end

  def do_flatten([h | t], tail) do
    [h | do_flatten(t, tail)]
  end

  def do_flatten([], tail) do
    tail
  end

  # Frorm chapter 7 page 71
  # Write a function MyList.span(from, to) that returns a list of the numbers from "from" up to "to".
  def span(from, to) when from > to, do: []
  def span(from, to), do: [from | span(from + 1, to)]

  # Exercise: ListsAndRecursion-7
  # In the last exercise of Chapter 7, Lists and Recursion, on page 71, you
  # wrote a span function. Use it and list comprehensions to return a list of
  # the prime numbers from 2 to n.
  def ex7(from \\2, n) do
    range = span(from, n)
    range -- (for a <- range, b <- range,a <= b, a * b <= n, do: a * b)
  end
end
