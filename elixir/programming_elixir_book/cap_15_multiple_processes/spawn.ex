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
      {sender, msg} ->
        send sender, {:ok, "Hello, #{msg}"}
        greet()
    end
  end
end

### client
pid = spawn(Spawn, :greet, [])
send pid, {self(), "world!"}
receive do
  {:ok, v} ->
    IO.puts v
end

send pid, {self(), "naval!"}
receive do
  {:ok, v} ->
    IO.puts v
  after 500 ->
    IO.puts "the greeter has gone away"
end
