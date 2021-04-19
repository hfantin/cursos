use std::sync::mpsc;
use std::thread;
use std::time::Duration;

fn main() {
    println!("Channels!");

    let (tx, rx) = mpsc::channel();

    let tx1 = tx.clone();
    thread::spawn(move || {
        let vals = vec![
            String::from("hi"),
            String::from("from"),
            String::from("the"),
            String::from("thread"),
        ];
        for val in vals {
            thread::sleep(Duration::from_secs(1));
            tx1.send(val).unwrap();
        }
        // one message
        // let val = String::from("hi");
        // thread::sleep(Duration::from_millis(1000));
        // tx.send(val).unwrap();
        // not allowed:
        // println!("val is {}", val);

        // multiple message
    });


    thread::spawn(move || {
        let vals = vec![
            String::from("2 more"),
            String::from("2 messages"),
            String::from("2 for"),
            String::from("2 you"),
            String::from("2 ok?"),
        ];
        for val in vals {
            thread::sleep(Duration::from_secs(1));
            tx.send(val).unwrap();
        }
    });


    // let received = rx.recv().unwrap();
    for received in rx {
        println!("got: {}", received);
    }
}
