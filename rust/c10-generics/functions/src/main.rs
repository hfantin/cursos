// traits are similar to interfaces in other languages

// fn greater(list: &[i32]) -> &i32 {
//     let mut num = &list[0];
//     for item in list {
//         if item > num {
//             num = item
//         }
//     }
//     num
// }

fn greater<T: PartialOrd>(list: &[T]) -> &T {
    let mut largest = &list[0];

    for item in list {
        if item > largest {
            largest = item;
        }
    }

    largest
}

// using Copy trait
fn largest<T: PartialOrd + Copy>(list: &[T]) -> T {
    let mut largest = list[0];

    for &item in list {
        if item > largest {
            largest = item;
        }
    }

    largest
}

// this is a way to eliminate code duplication by using functions
fn main() {
    println!("Functions! \n");

    let list = vec![8, 100, 16, 41, 4, 87, 33];
    let g = greater(&list);
    println!("the greater is {}", g);

    let char_list = vec!['y', 'm', 'a', 'q'];
    let g = greater(&char_list);
    println!("the greater is {}", g);

    println!("\nusing copy:");

    let list = vec![8, 100, 16, 41, 4, 87, 33];
    let g = largest(&list);
    println!("the greater is {}", g);

    let char_list = vec!['y', 'm', 'a', 'q'];
    let g = largest(&char_list);
    println!("the greater is {}", g);
}
