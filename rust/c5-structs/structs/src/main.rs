#[derive(Debug)]
struct User {
    username: String,
    email: String,
    sign_in_count: u64,
    active: bool,
}

// tuple 
#[derive(Debug)]
struct Color(i32, i32, i32);

fn main() {
    println!("Structs");
    let u = build_user(String::from("Teste"), String::from("teste@teste.com"));
    // the sintax .. specify the rest of the fields
    let u2 = User{ username: String::from("aaa"), email: String::from("aaa@aaa.com"), ..u};

    println!("{:?}", u);
    println!("{:?}", u2);

    let c = Color(255, 255, 255);
    println!("{:?}", c);
}


fn build_user(username: String, email: String) -> User {
    User {
        username, // same as username: username,
        email, 
        sign_in_count: 1,
        active: true, 
    }
}