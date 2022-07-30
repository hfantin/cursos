# book programming elixir 1.6 

## Links
- [book](https://pragprog.com/titles/elixir16/programming-elixir-1-6/)
- [forum](https://elixirforum.com/)
- [expresions in guard clauses](http://elixir-lang.org/getting-started/case-cond-and-if.html#expressions-in-guard-clauses)
- [type check functions - erlang](http://erlang.org/doc/man/erlang.html#is_atom-1)



## nodes 
- Example:
```
# open node 1
iex --sname node_1  or iex --name node_1@host.local
# create function 
func = fn -> IO.puts(Enum.join(File.ls!, ",")) end
# open node 2
iex --sname node_2 
# create the same function
Node.spawn(:node_1@localhost, func)
```
- using cookies
> iex --sname one --cookie cookie-name


## fire up server manually 
> iex -S mix 
> { :ok, pid } = GenServer.start_link(Sequence.Server, 100)
> GenServer.call(pid, :next_number)
> GenServer.call(pid, {:set_number, 999})
