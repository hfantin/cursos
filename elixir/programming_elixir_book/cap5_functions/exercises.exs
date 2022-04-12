## exercises of cap 5 - Anonymous Functions
# 2) Write a function that takes three arguments.
#    If the first two are zero, return “FizzBuzz.”
#    If the first is zero, return “Fizz.”
#    If the second is zero, return “Buzz.” Otherwise return the third argument.
#    Do not use any language features that we haven’t yet covered in this book.
f2 = fn
  0, 0, _ -> "FizzBuzz"
  0, _, _ -> "Fizz"
  _, 0, _ -> "Buzz"
  _, _, c -> c
end

IO.puts "\nexercise 2"
IO.puts f2.(0, 0, 1)
IO.puts f2.(0, 1, 1)
IO.puts f2.(1, 0, 1)
IO.puts f2.(1, 1, 1)

# 3) The operator rem(a, b) returns the remainder after dividing a by b . Write a
#    function that takes a single integer ( n ) and calls the function in the previ-
#    ous exercise, passing it rem(n,3) , rem(n,5) , and n . Call it seven times with
#    the arguments 10, 11, 12, and so on. You should get “Buzz, 11, Fizz, 13,
#    14, FizzBuzz, 16.”
f3 = fn
  n -> f2.(rem(n, 3), rem(n, 5), n)
end
IO.puts "\nexercise 3"
IO.puts f3.(10)
IO.puts f3.(11)
IO.puts f3.(12)
IO.puts f3.(13)
IO.puts f3.(14)
IO.puts f3.(15)
IO.puts f3.(16)


# 4) Write a function prefix that takes a string. It should return a new function
#    that takes a second string. When that second function is called, it will
#    return a string containing the first string, a space, and the second string.

f3 = fn prefix -> (fn name -> "#{prefix} #{name}" end) end
test = f3.("hello")

IO.puts "\nexercise 4"
IO.puts test.("world")


# 5)  Use the & notation to rewrite the following:
#     – Enum.map [1,2,3,4], fn x -> x + 2 end
#     – Enum.each [1,2,3,4], fn x -> IO.inspect x end

IO.puts "\nexercise 5"
list1 = Enum.map [1, 2, 3, 4], &(&1 + 2)
IO.puts(["result ", Enum.join(list1, " ")])

IO.puts("inspect list: ")
Enum.map [3, 5, 9, 18], &IO.inspect/1
