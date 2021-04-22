use std::sync::{Arc, Mutex};
use std::thread;
use std::rc::Rc;

fn main() {
    println!("mutex!");
    ex_1();
    ex_2();
    ex_3();
}

fn ex_1() {
    println!("ex 1");
    let m = Mutex::new(5);
    { 
        let mut num =  m.lock().unwrap();
        *num = 6;
    }
    println!("m = {:?}", m);
}


fn ex_2() {
    println!("\nex 2");
    // this won't work
    // let counter = Rc::new(Mutex::new(0));
    // let mut handles = vec![];

    // for _ in 0..10 {
    //     let counter = Rc::clone(&counter);
    //     let handle = thread::spawn(move || {
    //         let mut num = counter.lock().unwrap();

    //         *num += 1;
    //     });
    //     handles.push(handle);
    // }

    // for handle in handles {
    //     handle.join().unwrap();
    // }

    // println!("Result: {}", *counter.lock().unwrap());
}

fn ex_3() {
    println!("\nex 3");
    let counter = Arc::new(Mutex::new(0));
    let mut handles = vec![];

    for _ in 0..10 {
        let counter = Arc::clone(&counter);
        let handle = thread::spawn(move || {
            let mut num = counter.lock().unwrap();

            *num += 1;
        });
        handles.push(handle);
    }

    for handle in handles {
        handle.join().unwrap();
    }

    println!("Result: {}", *counter.lock().unwrap());
}