package main

import "fmt"

type tosco int
type teste tosco

var x tosco
var y int

func main() {

	fmt.Printf("%v\n", x)
	fmt.Printf("%T\n", x)
	x = 42
	fmt.Printf("%v\n", x)

	y = int(x)
	fmt.Printf("%T\n", y)
	fmt.Printf("%v\n", y)

}
