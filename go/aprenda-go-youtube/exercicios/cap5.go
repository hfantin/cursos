package main

import "fmt"

const (
	a = 2000 + iota
	b
	c
	d
)

func main() {
	x := 8

	fmt.Printf("%d %#b %#x\n", x, x, x)

	y := x << 2
	z := x >> 2

	fmt.Printf("%d %#b %#x\n", y, y, y)
	fmt.Printf("%d %#b %#x\n", z, z, z)

	fmt.Println(a, b, c, d)
}
