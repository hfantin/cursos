defmodule Spawn do

  @doc """
  Simple example of how to spawn a process
  ## Example
    iex> pid = spawn(Spawn, :greet, [])
    iex> send pid, {self(), "World!"}
    iex> receive do {:ok, message} -> IO.puts message end
  """
  def greet do
    receive do
      {:sender, message} ->
        send :sender, {:ok, "Hello, #{message}"}
        greet()
    end
  end
end
