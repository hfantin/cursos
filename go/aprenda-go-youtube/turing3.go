package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func productExceptSelf(nums []int) []int {
	n := make([]int, 0)
	for i := len(nums) - 1; i >= 0; i-- {
		n = append(n, nums[i]*6)
	}
	return n
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
	nums := []int{}

	reader := bufio.NewReader(os.Stdin)
	// read elements
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

	printResults(productExceptSelf(nums))
}
