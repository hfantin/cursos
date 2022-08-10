# Sequence

## run server manually
> iex -S mix 
> { :ok, pid } = GenServer.start_link(Sequence.Server, 100, [debug: [:trace]]) 
> GenServer.call(pid, :next_number)
> GenServer.call(pid, {:set_number, 1})
> GenServer.cast(pid, {:increment_number, 200})


## add statistics 
> { :ok, pid } = GenServer.start_link(Sequence.Server, 100, [debug: [:statistics]]) 
> :sys.statistics pid, :get
- to enable trace after server started:
> :sys.trace pid, true
- to show status
> :sys.get_status pid


## using external api
> Sequence.Server.start_link 120
> Sequence.Server.next_number
> Sequence.Server.increment_number 50

## Naming a process
> { :ok, pid } = GenServer.start_link(Sequence.Server, 100, name: :seq) 
> GenServer.call(:seq, :next_number)