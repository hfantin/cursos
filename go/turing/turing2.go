package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func rotate(nums []int, k int) []int {
	init := nums[:k+1]
	nums = append(nums[k+1:], init...)
	return nums
}

func printResults(results []int) {
	reslen := len(results)
	fmt.Print("[")
	for index, element := range results {

		fmt.Printf("%v", element)

		if reslen != index+1 {
			fmt.Print(", ")
		}
	}
	fmt.Print("]")
}

func main() {
	var k int = 0
	nums := []int{}

	reader := bufio.NewReader(os.Stdin)
	// read array elements
	rawArray, _ := reader.ReadString('\n')
	rawArray = strings.Replace(rawArray, "\n", " ", -1)
	elements := strings.Split(rawArray, " ")
	// parse and fill array elements
	for index := range elements {
		element, err := strconv.Atoi(elements[index])
		if err == nil {
			nums = append(nums, element)
		}
	}
	// read no of steps to be right rotated
	kSteps, _ := reader.ReadString('\n')
	kSteps = strings.Replace(kSteps, "\n", "", -1)

	ks, err := strconv.Atoi(kSteps)
	fmt.Println("ks", ks, kSteps)
	if err == nil {
		k = ks
	} else {
		fmt.Println("err", err)
	}
	// call rotate function
	result := rotate(nums, k)
	// print the rotated array
	printResults(result)
}
