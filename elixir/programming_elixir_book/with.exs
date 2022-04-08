defmodule With do
  def read(f) do
    lp =
      with {:ok, file} = File.open(f),
           content = IO.read(file, :all),
           :ok = File.close(file),
           [_, uid, gid] = Regex.run(~r/^hamilton.*?:(\d+):(\d+)/m, content) do
        "Grouyp #{gid}, User: #{uid}"
      end

    IO.puts(lp)
  end

  # using <- to return nil when not found
  def read2(f, name \\ "lp") do
    lp =
      with {:ok, file} = File.open(f),
           content = IO.read(file, :all),
           :ok = File.close(file),
           [_, uid, gid] <- Regex.run(~r/^#{name}.*?:(\d+):(\d+)/m, content),
           do: "Grouyp #{gid}, User: #{uid}"

    IO.puts("finding #{name}: #{lp}")
  end
end
