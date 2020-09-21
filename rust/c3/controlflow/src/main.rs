fn main() {
    let number = 3;
    if number < 5 {
        println!("condition was true");
    } else {
        println!("condition was false");
    }

    // if as expression
    let condition = true;
    let number = if condition { 5 } else { 6 };
    println!("the value of number is {}", number);

    // loops: loop, while and for
    let mut counter = 0;
    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 3; 
        }
    };
    println!("the result is {}", result);
    // while: 
    let mut number = 3;
    while number != 0 {
        println!("{}", number);
        number -= 1;
    }
    println!("finish while");

    // iterate over array using while
    let a = [10, 20, 30, 40, 50];
    let mut index = 0;
    while index < 5 {
        println!("the value is: {}", a[index]);
        index += 1;
    }

    // for - the same using for 
    println!("using for ");
    for element in a.iter() {
        println!("the value is: {}", element);
    }

    // for -countdown with rev and range
    println!("countdown with rev and range");
    for number in (1..4).rev() {
        println!("-{}", number);
    }



}



// changed
// clearer
// safety
//