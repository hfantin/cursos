use std::fs::File;
use std::io::ErrorKind;
use std::io;
use std::fs;
use std::io::Read;



/*
use std::error::Error;


* using ? operator on main function
fn main() -> Result<(), Box<dyn Error>> {
    let f = File::open("hello.txt")?;
    Ok(())
}
*/

fn main() {
    println!("Recoverable errors");
    let f = File::open("hello.txt");
    let f = match f {
        Ok(file) => file,
        Err(error) => match error.kind() {
            ErrorKind::NotFound => match File::create("hello.txt") {
                Ok(fc) => fc,
                Err(e) => panic!("Problem creating file: {:?}", e),
            }, 
            other_error => panic!("Problem opening the file: {:?}", other_error)
        }
    };

    // With closures
    /*
    let f = File::open("hello.txt").unwrap_or_else(|error| {
        if error.kind() == ErrorKind::NotFound {
            File::create("hello.txt").unwrap_or_else(|error| {
                panic!("Problem creating the file: {:?}", error);
            })
        } else {
            panic!("Problem opening the file: {:?}", error);
        }
    });

    */

    // Shortcuts for panic: unwrap and expect
    // let f = File::open("hello2.txt").unwrap();
    // using expect and change the error message:
    // let f = File::open("hello2.txt").expect("Failed to open hello2.txt");
    match read_username_from_file3() {
        Ok(u) => println!("username: {}", u),
        Err(e) => panic!("error: {}", e),
    };

    let name = read_username_from_file4().expect("failed to read username");
    println!("username v4: {}", name);
}




fn read_username_from_file() -> Result<String, io::Error> {
    let f = File::open("hello2.txt");

    let mut f = match f {
        Ok(file) => file,
        Err(e) => return Err(e),
    };

    let mut s = String::new();

    match f.read_to_string(&mut s) {
        Ok(_) => Ok(s),
        Err(e) => Err(e),
    }
}



// v2 of function using ? operator
fn read_username_from_file2() -> Result<String, io::Error> {
    let mut f = File::open("hello.txt")?;
    let mut s = String::new();
    f.read_to_string(&mut s)?;
    Ok(s)
}

// v3 of function using ? operator
fn read_username_from_file3() -> Result<String, io::Error> {
    let mut s = String::new();
    File::open("hello.txt")?.read_to_string(&mut s)?;
    Ok(s)
}

// Rust provides the convenient fs::read_to_string function that opens the file, creates a new String, reads the contents of the file, puts the contents into that String, and returns it
fn read_username_from_file4() -> Result<String, io::Error> {
    fs::read_to_string("hello.txt")
}