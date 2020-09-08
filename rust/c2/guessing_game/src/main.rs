use std::io;

fn main() {
    println!("Guess the number!");

    println!("Please input your guess.");

    let mut guess = String::new();

    io::stdin()
        .read_line(&mut guess) // passing guess as reference(&) - references are immutable by default, so, you need to use mut
        .expect("Failed to read line");

    println!("You guessed: {}", guess);
}
