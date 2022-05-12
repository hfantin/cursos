defmodule Exercises do
  # exercises 7
  # a. Convert a float to a string with two decimal digits. (Erlang)
  def float_to_string(n) do
    :io.format("the number is ~.2f~n", n)
  end

  #  b. Get the value of an operating-system environment variable.
  def os(), do: System.get_env("HOME")

  # Return the extension component of a file name (so return .exs if given "dave/test.exs").
  def ext(file), do: Path.extname(file)

  # Return the process’s current working directory.
  def cwd(), do: File.cwd()

  # Execute a command in your operating system’s shell.
  def command(cmd), do: System.cmd(cmd, [])
end
