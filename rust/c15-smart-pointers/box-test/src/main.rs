use List::{Cons, Nil};

fn main() {
    println!("Box test");
    // box is a smart pointer
    // Box allocate the value on heap, instead of stack
    let b = Box::new(5);
    println!("b = {}", b);

    // cons list - looks like linked list, where each element contains its value and a pointer to the next item

    let list = Cons(1.2, Box::new(Cons(2.3,  Box::new(Nil))));
    println!("list {:?}", list);
}

#[derive(Debug)]
enum List<T>{
    Cons(T, Box<List<T>>), 
    Nil, 
}
