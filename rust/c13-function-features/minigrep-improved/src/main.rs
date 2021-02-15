use std::env;
use std::process;

use minigrep::Config;

fn main() {
    // if you need unicode args, use std::env::args_os instead
    let config = Config::new(env::args()).unwrap_or_else(|err| {
        eprintln!("Problem parsing arguments: {}", err);
        process::exit(1);
    });
    println!("Searching for {} in file {}", config.query, config.filename);
    if let Err(e) = minigrep::run(config) {
        eprintln!("Application error: {}", e);
        process::exit(1);
    }
}
