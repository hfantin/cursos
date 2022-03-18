defmodule TestModule do
    
    import IO, only: [puts: 1]
    import Kernel, except: [inspect: 1]
    
    alias MeuModulo.Math

    require Integer

    def hello do
        inspect("hello world")
    end

    def hello2 do
        inspect(Math.soma(3, 4))
    end

    def exibe_se_eh_par(numero) do
        puts "o numero #{numero} ẽ par? #{Integer.is_even(numero)}"
    end

    def inspect(p) do 
        puts "iniciando inspecao"
        puts p
        puts "finalizando inspecao"
    end
end