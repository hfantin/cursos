fn main() {
    println!("drop");
    let c = CustomSmartPointer{
        data: String::from("my stuff"),
    };
    let d = CustomSmartPointer{
        data: String::from("other stuff"),
    };
    // explicit call of drop
    // drop(c);
    println!("Custom Stuff created");

}

struct CustomSmartPointer {
    data: String, 
}

impl Drop for CustomSmartPointer {
    fn drop(&mut self) {
        println!("Dropping CustomSmartPoint with data {}!", self.data);
    }
}