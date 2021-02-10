use std::thread;
use std::time::Duration;

struct Cacher<T>
where
    T: Fn(u32) -> u32,
{
    calculation: T,
    value: Option<u32>,
}

impl<T> Cacher<T>
where
    T: Fn(u32) -> u32,
{
    fn new(calculation: T) -> Cacher<T> {
        Cacher {
            calculation,
            value: None,
        }
    }

    fn value(&mut self, arg: u32) -> u32 {
        match self.value {
            Some(v) => v,
            None => {
                let v = (self.calculation)(arg);
                self.value = Some(v);
                v
            }
        }
    }
}

fn main() {
    // let simulated_user_specified_value = 10;
    // let simulated_random_number = 7;

    // generate_workout(simulated_user_specified_value, simulated_random_number);

    // using variables from scope

    let x = vec![1, 2, 3];
    // wiht move, you will take ownership of the values in the envrironment
    let equal_to_x = move |z| z == x;
    // this will cause an error
    // println!("can't use x here: {:?}", x);
    let y = vec![1, 2, 3];
    println!("{:?}", equal_to_x(y));
}

fn generate_workout(intensity: u32, random_number: u32) {
    // optionaL: type annotations
    // let expensive_closure = |num: u32| -> u32 {

    // implementation without cacher
    // let expensive_closure = |num| {
    //     println!("calculating slowly...");
    //     thread::sleep(Duration::from_secs(2));
    //     num
    // };

    // using cacher
    let mut expensive_result = Cacher::new(|num| {
        println!("calculating slowly...");
        thread::sleep(Duration::from_secs(2));
        num
    });



    if intensity < 25 {
        // println!("Today, do {} pushups!", expensive_closure(intensity));
        // println!("Next, do {} situps!", expensive_closure(intensity));

        println!("Today, do {} pushups!", expensive_result.value(intensity));
        println!("Next, do {} situps!", expensive_result.value(intensity));
    } else {
        if random_number == 3 {
            println!("Take a break today! Remember to stay hydrated!");
        } else {
            // println!("Today, run for {} minutes!", expensive_closure(intensity));
            println!("Today, run for {} minutes!", expensive_result.value(intensity));
        }
    }
}
