use std::fmt::Display;

fn main() {
    println!("Lifetime");
    {
        // this wont work because x will drop and the compiler will give an error: borrowed value does not live long enough
        let r;                // ---------+-- 'a
                              //          |
        {                     //          |
            let x = 5;        // -+-- 'b  |
            r = &x;           //  |       |
        }                     // -+       |
                              //          |
      //println!("r: {}", r); //          |
    }                         // ---------+



    let string1 = String::from("abcd");
    let string2 = "xyz";

    let result = longest(string1.as_str(), string2);
    println!("The longest string is {}", result);

    let result = longest_with_an_announcement(string1.as_str(), string2, String::from("tosco"));
    println!("The longest string is {}", result);


}


// this wont work because the compiler doenst know the lifetime, we need to use the lifetime anotations
// fn longest(x: &str, y: &str) -> &str {
fn longest<'a>(x: &'a str, y: &'a str) -> &'a str {
    if x.len() > y.len() {
        x
    } else {
        y
    }
}



fn longest_with_an_announcement<'a, T>(
    x: &'a str,
    y: &'a str,
    ann: T,
) -> &'a str
where
    T: Display,
{
    println!("Announcement! {}", ann);
    if x.len() > y.len() {
        x
    } else {
        y
    }
}