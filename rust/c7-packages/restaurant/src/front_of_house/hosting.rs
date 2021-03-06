pub mod hosting;

pub fn add_to_waitlist() {}

fn seat_at_table() {}

use crate::front_of_house::hosting;
// or with relative path
// use self::front_of_house::hosting;

pub fn eat_at_restaurant() {
    // Absolute path - like /front_of_house/hosting/add_to_waitlist where the keyword "crate" is like /
    crate::front_of_house::hosting::add_to_waitlist();

    // Relative path
    front_of_house::hosting::add_to_waitlist();

     // Order a breakfast in the summer with Rye toast
     let mut meal = back_of_house::Breakfast::summer("Rye");
     // Change our mind about what bread we'd like
     meal.toast = String::from("Wheat");
     println!("I'd like {} toast please", meal.toast);
 
     // The next line won't compile if we uncomment it; we're not allowed
     // to see or modify the seasonal fruit that comes with the meal
     // meal.seasonal_fruit = String::from("blueberries");

     let order1 = back_of_house::Appetizer::Soup;
     let order2 = back_of_house::Appetizer::Salad;


     // using keyword use - similar to create symbolic link in the filesytem
     hosting::add_to_waitlist()
}
