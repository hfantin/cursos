use std::ops::Deref;

fn main() {
    println!("Deref");

    let x = 5;
    let y = &x;
    // let y = Box::new(x); // this will work simliar to &x
    assert_eq!(5, x);
    assert_eq!(5, *y); // we must dereference the pointer to compare the value it points to

    // defining our own box type

    let a = 6;
    let b = MyBox::new(a);
    assert_eq!(6, a);
    assert_eq!(6, *b);
}

struct MyBox<T>(T);
impl<T> MyBox<T> {
    fn new(x: T) -> MyBox<T> {
        MyBox(x)
    }
}
impl<T> Deref for MyBox<T> {
    type Target = T;

    fn deref(&self) -> &T {
        &self.0
    }
}
