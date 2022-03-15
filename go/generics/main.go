package main

import "fmt"

// type constraint
type Number interface {
	int64 | float64 | int
}

func main() {
	fmt.Println("generics!")
	ints := map[string]int64{
		"first":  34,
		"second": 12,
	}

	// Initialize a map for the float values
	floats := map[string]float64{
		"first":  35.98,
		"second": 26.99,
	}

	fmt.Printf("Non-Generic Sums: %v and %v\n",
		SumInts(ints),
		SumFloats(floats))

	fmt.Printf("Generic Sums: %v and %v\n",
		SumIntsOrFloats(ints),
		SumIntsOrFloats(floats))

	fmt.Println("pot of 3 is ", pot(3))
	fmt.Println("pot of 3.3 is ", pot(3.9))
}

// SumInts adds together the values of m.
func SumInts(m map[string]int64) int64 {
	var s int64
	for _, v := range m {
		s += v
	}
	return s
}

// SumFloats adds together the values of m.
func SumFloats(m map[string]float64) float64 {
	var s float64
	for _, v := range m {
		s += v
	}
	return s
}

// func SumIntsOrFloats[K comparable, V int64 | float64](m map[K]V) V {
func SumIntsOrFloats[K comparable, V Number](m map[K]V) V {
	//
	var s V
	for _, v := range m {
		s += v
	}
	return s
}

func pot[T Number](n T) T {
	return n * n
}
