# Exercises of the rust book
- [the book](https://doc.rust-lang.org/book/)


###
https://doc.rust-lang.org/nomicon/index.html

## last readed chapter
https://doc.rust-lang.org/book/ch15-00-smart-pointers.html
https://doc.rust-lang.org/book/ch15-01-box.html
https://doc.rust-lang.org/book/ch15-02-deref.html
https://doc.rust-lang.org/book/ch15-03-drop.html
https://doc.rust-lang.org/book/ch15-04-rc.html
https://doc.rust-lang.org/book/ch15-05-interior-mutability.html
https://doc.rust-lang.org/book/ch15-06-reference-cycles.html
https://doc.rust-lang.org/book/ch16-00-concurrency.html
https://doc.rust-lang.org/book/ch16-03-shared-state.html
https://doc.rust-lang.org/book/ch16-04-extensible-concurrency-sync-and-send.html
https://doc.rust-lang.org/book/ch17-01-what-is-oo.html
### exercises
Convert temperatures between Fahrenheit and Celsius.
Generate the nth Fibonacci number.

### floating point operations format
> let difference = 0.1 + 0.2;
> println!("{0:.2?}", difference);
> println!("{1:.0$?}", 2, difference);
> println!("{:.*?}", 2, difference);
> println!("{1:.*?}", 2, difference);
> println!("{number:.prec$?}", prec = 2, > > > number = difference);


### tests commands
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



### Cross compiling:
- [example](https://exceptionshub.com/cross-compile-a-rust-application-from-linux-to-windows.html)
- rustup target list


### Docs
- [cargo](https://doc.rust-lang.org/cargo/)


### documentation commands
- cargo doc
- cargo doc --open



### check out this
- Halting Problem


## Smart pointer 
- Box points to data allocated on the heap
- RC multiple owners and track the number of references
- RefCell interior mutability for immutables types
- 