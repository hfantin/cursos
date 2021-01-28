# Exercises of the rust book
- [the book](https://doc.rust-lang.org/book/)


## last readed chapter
https://doc.rust-lang.org/book/ch13-00-functional-features.html
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