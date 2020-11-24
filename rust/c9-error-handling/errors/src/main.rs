fn main() {
    println!("Error handling");

    // this will crash the program
    // panic!("crash and burn");
    let v = vec![1, 2, 3];
    v[99];
    // to see the backtrace: export RUST_BACKTRACE=1
}
