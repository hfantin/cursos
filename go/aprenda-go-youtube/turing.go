package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func calPoints(ops []string) int {
	var res int = 0
	// code here
	result := make([]int, 0, 0)
	for _, v := range ops {
		switch v {
		case "+":
			result = append(result, result[len(result)-1]+result[len(result)-2])
		case "C":
			result = result[:len(result)-1]
		case "D":
			result = append(result, 2*result[len(result)-1])
		default:
			if number, err := strconv.Atoi(v); err == nil {
				result = append(result, number)
			}
		}

	}
	for _, v := range result {
		res += v
	}
	return res
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	rawInput, _ := reader.ReadString('\n')
	rawInput = strings.Replace(rawInput, "\n", " ", -1)
	ops := strings.Split(rawInput, " ")
	// 5 2 c d +
	fmt.Println(calPoints(ops))
}
