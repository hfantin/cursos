# Stack

## fire up server manually 
> iex -S mix 
> { :ok, pid } = GenServer.start_link(Stack.Server, [5, "cat", 9])
> GenServer.cast(pid, {:push, "beer"})
> GenServer.call(pid, :pop)
