defmodule StreamExample do
  # this takes a number and apply to the function
  def unfold(n, initial_value \\ 1) do
    # unfold takes one parameter, the accumulator(acc)
    # and returns a tuple where the first element
    # contains the value and the second contains the accumulator
    # to be passed to the next call
    Stream.unfold(initial_value, fn acc -> {acc * 2, acc * 2 } end)
    |> Enum.take(n)
  end

  # 0, 1, 1, 2, 3, 5, 8...
  def fibo(n) do
    Stream.unfold({0, 1}, fn { current, next } -> {current, { next, current + next } } end)
    |> Enum.take(n)
  end

end
