package main

import "fmt"

func main() {
	fmt.Println("closure x")
	x := closure()
	fmt.Println(x())
	fmt.Println(x())
	y := closure()
	fmt.Println("closure y")
	fmt.Println(y())
	fmt.Println(y())
	fmt.Println(y())
	fmt.Println(y())
	fmt.Println(y())

}

func closure() func() int {
	x := 0
	return func() int {
		x++
		return x
	}
}
