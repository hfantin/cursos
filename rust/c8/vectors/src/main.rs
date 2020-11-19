// docs: https://doc.rust-lang.org/std/vec/struct.Vec.html#method.as_slice
fn main() {
    println!("Vectors!");
    // let v: Vec<i32> = Vec::new();
    let v = vec![1, 2, 3];
    println!("{:?}", v);

    // modify vector
    let mut mv = Vec::new();
    mv.push(5);
    mv.push(6);
    mv.push(7);
    println!("{:?}", mv);

    // iterate
    let v2 = vec![1, 2, 3, 4, 5];
    let third: &i32 = &v[2];
    println!("the third element is {}", third);

    match v.get(2) {
        Some(third) => println!("The third element is {}", third), 
        None => println!("there is no third element"), 
    }

    // adding a new element onto the end of the vector might require allocating new memory and copying the old elements to the new space,
    //  if there isn’t enough room to put all the elements next to each other where the vector currently is. In that case, the reference to the first element would be pointing to deallocated memory.
    
    // let mut v = vec![1, 2, 3, 4, 5];
    // let first = &v[0];
    // v.push(6);

    // println!("The first element is: {}", first);

    // iterate over each element getting immutable references 
    println!("\niterate over each element getting immutable references:");
    let v1 = vec![1, 3, 5, 7];
    for i in &v1 {
        println!("i {}", i);
    }

    println!("\niterate over array getting mutable references:");
    let mut v2 = vec![1, 3, 5, 7];
    for i in &mut v2{
        *i += 50;
        println!("i {}", i);
    }


    println!("\nusing enum to store multiple types:");
    #[derive(Debug)]
    enum SpreadsheetCell {
        Int(i32),
        Float(f64),
        Text(String),
    }

    let row = vec![
        SpreadsheetCell::Int(3),
        SpreadsheetCell::Text(String::from("blue")),
        SpreadsheetCell::Float(10.12),
    ];

    println!("{:?}", row);

    // methods

    let mut v3 = vec![1, 2, 4, 6];
    let e = v3.pop();
    println!("e {:?}", e);
    println!("{:?}", v3);
}
