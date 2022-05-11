# The syntax for creating a struct is the same as the syntax for creating a map—you simply add the module name between the % and the { .
# for example: %Subscriber{}
defmodule Subscriber do
  defstruct name: "", paid: false, over_18: true
end
