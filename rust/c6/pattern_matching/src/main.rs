#[derive(Debug)] // so we can inspect the state in a minute
enum UsState {
    Alabama,
    Alaska,
    // --snip--
}

enum Coin {
    Penny,
    Nickel,
    Dime,
    Quarter(UsState),
}

fn main() {
    println!("value of a penny: {}", value_in_cents(Coin::Penny));
    println!("value of a nickel: {}", value_in_cents(Coin::Nickel));
    println!("value of a dime: {}", value_in_cents(Coin::Dime));
    println!("value of a quarter: {}", value_in_cents(Coin::Quarter(UsState::Alaska)));

    // plus one on Option
    let five = Some(5);
    let six = plus_one(five);
    let none = plus_one(None);
    println!("five plus one {}", six.unwrap());

    // the _ placeholder

    let some_u8_value = 3;
    match some_u8_value {
        1 => println!("one"),
        3 => println!("three"),
        5 => println!("five"),
        7 => println!("seven"),
        _ => (), // unit value - nothing happens
    }

    // if let - more concise
    let some_u8_value = Some(3);
    match some_u8_value {
        Some(3) => println!("three"),
        _ => (),
    }

    // using if let - you can also use else as the same of _
    if let Some(3) = some_u8_value {
        println!("three");
    }


}

fn plus_one(x: Option<i32>) -> Option<i32> {
    match x {
        None => None,
        Some(i) => Some(i + 1),
    }
}



fn value_in_cents(coin: Coin) -> u8 {
    match coin {
        Coin::Penny => 1,
        Coin::Nickel => 5,
        Coin::Dime => 10,
        Coin::Quarter(state) => {
            println!("State quarter from {:?}!", state);
            25
        }
    }
}