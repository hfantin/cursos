fn main() {
    /*
    Another string types(with diferent encoding):
      OsString,
      OsStr,
      CString, 
      CStr
    */
    println!("Strings!!!");

    let literal = "inicial contents"; // literal string ->  &str
    let literal_2 = "abc";

    let mut s = literal.to_string();      // new STring
    let mut s2 = String::from("other"); 

    s.push_str(literal_2);  // appends a literal to the string
    println!("{}", s);


    s2.push('l'); // push appends a single to char to the string

    println!("{}", s2);

    // combining 2 strings: 
    let s3 = s + &s2; // s is moved here and can no longer be used
    println!("s3 {}", s3);


    let s4 = String::from("tic");
    let s5 = String::from("tac");
    let s6 = String::from("toe");

    let s7 = format!("{}-{}-{}", s4, s5, s6);
    println!("{}", s7);

    // iterate over string
    println!("");
    for c in "नमस्ते".chars() {
      println!("{}", c);
    } 
    // bytes
    println!("");
    for b in "नमस्ते".bytes() {
      println!("{}", b);
    }
}
