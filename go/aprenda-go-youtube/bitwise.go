package main

import "fmt"

const (
	_  = iota             // 0
	KB = 1 << (iota * 10) // 1 << (1 * 10)
	MB                    // 1 << (2 * 10)
	GB                    // 1 << (3 * 10)
	TB                    // 1 << (4 * 10)
)

// https://medium.com/learning-the-go-programming-language/bit-hacking-with-go-e0acee258827
func main() {
	fmt.Println("bitwise")

	fmt.Println("binary\t\t\t\t\t  decimal")
	fmt.Printf("%-41b %d KB\n", KB, KB)
	fmt.Printf("%-41b %d MB\n", MB, MB)
	fmt.Printf("%-41b %d GB\n", GB, GB)
	fmt.Printf("%-41b %d TB\n", TB, TB)

}
