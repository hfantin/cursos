defmodule Sequence.Stash do
  use GenServer

  @me __MODULE__

  def start_link(current_number) do
    GenServer.start_link(__MODULE__, current_number, name: @me)
  end

  def get() do
    GenServer.call(@me, {:get})
  end

  def update(current_number) do
    GenServer.cast(@me, {:update, current_number})
  end

  def init(current_number) do
    {:ok, current_number}
  end

  def handle_call({:get}, _from, current_number) do
    {:reply, current_number, current_number}
  end

  def handle_cast({:update, current_number}, _current_number) do
    {:noreply, current_number}
  end
end
