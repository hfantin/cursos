use std::fmt::Display;

pub trait Summary {
    fn summarize_author(&self) -> String;
    // fn summarize(&self) -> String;
    // this is the default implementation
    fn summarize(&self) -> String {
        format!("(Read more {}...)", self.summarize_author())
    }
}

pub struct NewsArticle {
    pub headline: String, 
    pub location: String, 
    pub author: String, 
    pub content: String, 
}

impl Summary for NewsArticle {
    fn summarize_author(&self) -> String{
        format!("@{}", self.author)
    }
    fn summarize(&self) -> String {
        format!("{}, by {} ({})", self.headline, self.author, self.location)
    }
}

pub struct Tweet {
    pub username: String, 
    pub content: String, 
    pub reply: bool, 
    pub retweet: bool, 
}

impl Summary for Tweet {
    fn summarize_author(&self) -> String{
        format!("@{}", self.username)
    }
    // fn summarize(&self) -> String {
    //     format!("{}: {}", self.username, self.content)
    // }
}


// trait as parameter
//fn notify_multiple(item: &(impl Summary + Display)) {
fn notify(item: &impl Summary) {
    println!("Breaking news! {}", item.summarize());
}


// This do the same as notify using trait bounds syntax
// fn notify_bounds_multiple<T: Summary + Display>(item: &T){
// or with where:
// fn notify_bounds_multiple<T>(item: &T) where T: Summary + Display
fn notify_bounds<T: Summary>(item: &T){
    println!("Breaking news with trait bounds! {}", item.summarize());
}



// returning a trait
fn returns_summarize() -> impl Summary {
    Tweet{
        username: String::from("horse_ebooks"), 
        content: String::from(
            "of course, as you probably already know, people",
        ),
        reply: false,
        retweet: false,
    }
}

fn main() {
    println!("Traits!");

    let tweet = Tweet{
        username: String::from("horse_ebooks"), 
        content: String::from("of course, as you probably know, people"), 
        reply: false, 
        retweet: false,
    };

    println!("1 new tweet: {}", tweet.summarize());

    notify(&tweet);
    notify_bounds(&tweet);
}


