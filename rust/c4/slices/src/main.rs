

fn main() {
    println!("String slices");

    let s = String::from("hello world");
    let sl = "hello world";

    let word = first_word(&s[..]); // or just &s
    let word2 = first_word(&sl[..]); // or just &sl
    // this will cause an error: 
    // s.clear();

    println!("{}", word);
    println!("{}", word2);

    // other slices: 
    let a = [1, 2, 3, 4, 5];
    let slice = &a[1..3];
    println!("{:?}", slice);

}

// return the first word if an space exists, for example, "hello world" will return "hello"
fn first_word(s: &str) -> &str {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[..i];
        }
    }
    return &s[..];
}
