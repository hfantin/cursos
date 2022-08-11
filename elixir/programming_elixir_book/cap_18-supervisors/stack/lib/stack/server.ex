defmodule Stack.Server do
  use GenServer

  def start_link(current_stack) do
    GenServer.start_link(__MODULE__, current_stack, name: __MODULE__)
  end

  def pop do
    GenServer.call __MODULE__, :pop
  end

  def push(value) do
    GenServer.cast __MODULE__, { :push, value }
  end

  def init(stack) do
    {:ok, stack}
  end

  def handle_call(:pop, _from, [head | tail]) do
    {:reply, head, tail}
  end

  # def handle_call(:pop, _from, []) do
  #   {:reply, "the stack is empty", []}
  # end

  def handle_cast({:push, value}, stack) do
    {:noreply, [value | stack]}
  end

  def terminate(reason, state) do
    IO.puts("terminate - reason: #{inspect(reason)}, state: #{inspect(state)}")
  end
end
