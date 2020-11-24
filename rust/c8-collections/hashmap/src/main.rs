use std::collections::HashMap;

fn main() {
    println!("Hashmaps!");
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Yellow"), 50);
    println!("scores: {:?}", scores);

    // from list of teams and scores
    let teams = vec![String::from("Blue"), String::from("Yellow")];
    let initial_scores = vec![10, 50];

    let scores2: HashMap<_, _> =
        teams.into_iter().zip(initial_scores.into_iter()).collect();
    println!("\nscores2: {:?}", scores2);

    // getting values
    let mut scores3 = HashMap::new();
    scores3.insert(String::from("blue"), 10);
    scores3.insert(String::from("yellow"), 50);
    let team_name = String::from("blue");
    let score_blue = scores3.get(&team_name);
    match score_blue {
        Some(i) => println!("\nscore blue: {}", i), 
        None => println!("there is no score :("),
    }
    // iterate over map
    for (key, value) in &scores3 {
        println!("{}: {}", key, value);
    }

    // overwriting the value

    let mut scores4 = HashMap::new();
    scores4.insert(String::from("Blue"), 10);
    scores4.insert(String::from("Blue"), 20);
    println!("scores4: {:?}", scores4);

    // insert if now exists
    

    let mut scores5 = HashMap::new();
    scores5.insert(String::from("blue"), 10);
    scores5.entry(String::from("yellow")).or_insert(50);
    scores5.entry(String::from("blue")).or_insert(50);
    println!("scores5: {:?}", scores5);


    // updating the old value
    let text = "hello world wonderful world";

    let mut map = HashMap::new();

    for word in text.split_whitespace() {
        let count = map.entry(word).or_insert(0);
        *count += 1;
    }

    println!("{:?}", map);




}
