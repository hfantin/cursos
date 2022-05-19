# example of Stream.resource
# how to test:
# countdown = Countdown.timer
# printer = countdown |> Stream.each(&IO.puts/1)
# speaker = printer |> Stream.each(&Countdown.say/1)
# speaker |> Enum.take(5)
defmodule Countdown do
  def sleep(seconds) do
    receive do
    after
      seconds * 1000 -> nil
    end
  end

  def say(text) do
    spawn(fn -> :os.cmd('say #{text}') end)
  end

  def timer do
    Stream.resource(
      # the number of seconds to the start of the next minute
      fn ->
        {_h, _m, s} = :erlang.time()
        60 - s - 1
      end,
      # wait for the next second, then return its countdown
      fn
        0 ->
          {:halt, 0}

        c ->
          sleep(1)
          {[inspect(c)], c - 1 }
      end,
      # nothing ot deallocate
      fn _ -> nil end
    )
  end
end
