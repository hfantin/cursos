
defmodule Buggy do
  # call Buggy.parser_header(<<0, 1, 0, 8, 0, 120>>)
  # its broken

  # buggy code: integer-16 on division arg
  # def parser_header(<<format::integer-16, tracks::integer-16, division::integer-16>>) do
  def parser_header(<<format::integer-16, tracks::integer-16, division::bits-16>>) do
    # this is a breakpoint
    # use binding and continue()
    # require IEx; IEx.pry
    # another way is to use break! macro on iex
    IO.puts("format: #{format}")
    IO.puts("tracks: #{tracks}")
    IO.puts("division: #{decode(division)}")
  end

  def decode(<<1::1, beats::15>>) do
    "♩ = #{beats}"
  end

  def decode(<<0::1, fps::7, beats::8>>) do
    "#{-fps} fps, #{beats}/ frame"
  end
end
