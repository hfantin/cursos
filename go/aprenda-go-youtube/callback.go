package main

import "fmt"

func main() {
	fmt.Println("callback")

	parent(area)
	parent(perim)

}
func area(w, h int) int {
	return w * h
}
func perim(w, h int) int {
	return 2*w + 2*h
}

func parent(f func(int, int) int) {
	fmt.Println("Calculo entre 10 e 20:", f(10, 20))
}
