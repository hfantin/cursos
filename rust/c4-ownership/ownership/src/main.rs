fn main() {
    println!("Ownership");
    // this will produce an error because s1 was moved to s2 an its out of scope
    // let s1 = String::from("hello");
    // let s2 = s1;
    // println!("s1={}",s1);

    let s = String::from("hello");
    takes_ownership(s);
    // s is no longer valid after moving to the function
    let x = 5;

    makes_copy(x);
    // x was copied to the function, so its valid after
}

fn makes_copy(some_integer: i32) {
    println!("copy: {}", some_integer);

}

fn takes_ownership(some_string: String) {
    println!("ownwership: {}", some_string);
}
