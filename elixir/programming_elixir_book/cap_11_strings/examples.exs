defmodule Examples do
  def anagram(sqs1, sqs2), do: Enum.sort(sqs1) == Enum.sort(sqs2)

  def center(list) do
    [head | _] = Enum.sort_by(list, &byte_size/1) |> Enum.reverse
    max = String.length head
    show(list, max)
  end

  defp show([], _max_length), do: :ok
  defp show([head | tail ], max) do
    pad = div(max - String.length(head), 2) + String.length(head)
    IO.puts("#{String.pad_leading(head, pad)}")
    show(tail, max)
  end

#  Write a function to capitalize the sentences in a string. Each sentence is
# terminated by a period and a space. Right now, the case of the characters in the
# string is random.
#
# iex> capitalize_sentences("oh. a DOG. woof. ")
# "Oh. A dog. Woof. "
  def capitalize_sentences(string) do
    string
    |> String.split(~r{\.\s+})
    |> Enum.map(&(String.capitalize(&1)))
    |> Enum.join(".")
  end
end
