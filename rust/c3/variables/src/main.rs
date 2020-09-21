fn main() {
    let mut x = 5;
    println!("The value of x is: {}", x);
    x = 6;
    println!("The value of x is: {}", x);
    let a = 0.1;
    let b = 0.2;
    println!("{}", a + b);

    let c = [3; 5];
    println!("{:?}", c);

    // functions 
    another_function(5);

    println!("five: {}", five());
    println!("plus one: {}", plus_one(5));
}

fn five() -> i32 {
    5
}
/*

*/

fn plus_one(x: i32) -> i32 {
    x + 1
}

fn another_function(x: i32) {
    println!("the value of x is: {}", x)

}
