use crate::front_of_house::hosting;

fn serve_order() {}

mod back_of_house {
    fn fix_incorrect_order() {
        cook_order();
        // this super is like call filesystem ../
        super::serve_order();
    }

    fn cook_order() {}

    pub struct Breakfast {
        pub toast: String,
        seasonal_fruit: String,
    }

    impl Breakfast {
        pub fn summer(toast: &str) -> Breakfast {
            Breakfast {
                toast: String::from(toast),
                seasonal_fruit: String::from("peaches"),
            }
        }
    }

    // all of enum variants are public when itself is pub
    pub enum Appetizer {
        Soup,
        Salad,
    }
}

