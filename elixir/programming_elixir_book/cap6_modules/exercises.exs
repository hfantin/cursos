defmodule Exercises do
  # exercises 7
  # a. Convert a float to a string with two decimal digits. (Erlang)
  def float_to_string(n) do
    :io.format("the number is ~.2f~n", n)
  end
  #  b. Get the value of an operating-system environment variable.
  def os(), do: System.get_env("HOME")
end
