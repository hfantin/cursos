# Exercises of the rust book
- [the book](https://doc.rust-lang.org/book/)


## last readed chapter
https://doc.rust-lang.org/book/ch13-00-functional-features.html
https://doc.rust-lang.org/book/ch13-01-closures.html#closure-type-inference-and-annotation
https://doc.rust-lang.org/book/ch13-01-closures.html#limitations-of-the-cacher-implementation
### exercicios
Convert temperatures between Fahrenheit and Celsius.
Generate the nth Fibonacci number.

### floating point operations format
> let difference = 0.1 + 0.2;
> println!("{0:.2?}", difference);
> println!("{1:.0$?}", 2, difference);
> println!("{:.*?}", 2, difference);
> println!("{1:.*?}", 2, difference);
> println!("{number:.prec$?}", prec = 2, > > > number = difference);


### tests
- passing arguments: 
> cargo test -- --help
- one thread:
> cargo test -- --test-threads=1
- show output(println)
> cargo test -- --show-output
- pass the name of test - can be partial, it will match the name of the function
> cargo test name_of_function
- run ignored tests
> cargo test -- --ignored
- run specific integration test
> cargo test --test name_of_file


### patterns

memoization(keep in memory) or lazy evaluation: a struct that will hold the closure and the resulting value of calling the closure. The struct will execute the closure only if we need the resulting value, and it will cache the resulting value so the rest of our code doesn’t have to be responsible for saving and reusing the result