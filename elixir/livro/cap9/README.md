Exercicio do capitulo 9 do livro "Elixir - do zero à concorrencia" da casa do codigo.   

Ping e pong para testar processos

# Executar
iex -S mix
App.Table.start

# testes com processos no iex

iex
pid_do_iex = self()
pid_do_processo = spawn fn -> send pid_do_iex, {self(), "Estamos em outro processo..."} end

receive do {pid_do_processo, mensagem} -> IO.puts "#{mensagem}" end



# duvidas
-como saber se um processo está vivo ou não;
-o que acontece se um processo receber uma mensagem diferente da qual ele espera e como resolver isso
-como ligar um processo ao outro, de forma que,se um morrer, o outro também morre.