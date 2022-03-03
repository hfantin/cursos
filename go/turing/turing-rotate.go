package main

import "fmt"

func main() {
	fmt.Println("main")
	// a := []int{34, 23, 1, 24, 75, 33, 54, 8}
	a := []int{10, 20, 30}
	k := 15
	max := -1
	for i := 0; i < len(a); i++ {
		for j := 0; j < len(a); j++ {
			if i < j {
				sum := a[i] + a[j]
				if sum < k && sum > max {
					max = sum
				}
			}

		}
	}
	fmt.Println(max)
}
