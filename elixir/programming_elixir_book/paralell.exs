defmodule Parallel do
  #  usage Parallel.pmap([1, 2, 3], &(&1 * &1))
  def pmap(collection, func) do
    collection
    |> Enum.map(&Task.async(fn -> func.(&1) end))
    |> Enum.map(&Task.await/1)
  end
end
