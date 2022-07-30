defmodule Stack.Server do
  use GenServer

  def init(stack) do
    {:ok, stack}
  end

  def handle_call(:pop, _from, [head | tail]) do
    {:reply, head, tail}
  end

  def handle_call(:pop, _from, []) do
    {:reply, "the stack is empty", []}
  end

  def handle_cast({:push, value}, stack) do
    {:noreply, [value | stack]}
  end
end
