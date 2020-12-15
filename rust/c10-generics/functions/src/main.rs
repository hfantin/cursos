// traits are similar to interfaces in other languages 

fn greater(list: &[i32]) -> &i32 {
    let mut num = &list[0];
    for item in list {
        if item > num {
            num = item
        }
    }
    num
}

// this is a way to eliminate code duplication by using functions
fn main() {
    println!("Functions!");

    let list = vec![10, 5, 99, 71, 2];
    let g = greater(&list);
    println!("the greater is {}", g);
    
    let list = vec![8, 100, 16, 41, 4, 87, 33];
    let g = greater(&list);
    println!("the greater is {}", g);
}
