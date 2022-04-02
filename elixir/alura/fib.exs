defmodule Fib do 
    #  should return 1, 1, 2, 3, 5, 8, 13, 21, 34, 55... from https://alchemist.camp/episodes/fibonacci-tail
    def time(func, arg) do
        t0 = Time.utc_now
        func.(arg)
        Time.diff(Time.utc_now, t0, :millisecond)
    end    

    def compare(n \\ 45) do 
        IO.puts "Naive: #{time(&naive/1, n)}"
        IO.puts "Faster: #{time(&faster/1, n)}"
    end

    # this aproach takes too much time when the number is big, because it requires a lot of calculations
    def naive(1), do: 1
    def naive(2), do: 1
    def naive(n) do 
        naive(n-2) + naive(n-1)
    end

    def faster(n), do: faster(n, 0, 1)
    def faster(1, _acc1, acc2), do: acc2
    def faster(n, acc1, acc2) do 
        faster(n - 1, acc2, acc1 + acc2)
    end

    def find(nth) do
        list = [1, 1]
        fib(list, nth)
    end

    def fib(list, 2) do
        Enum.reverse(list)
    end

    def fib(list, n) do
        fib([hd(list) + hd(tl(list))] ++ list, n - 1)
    end
end 