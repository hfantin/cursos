package main

import (
	"fmt"
	"regexp"
	"strings"
)

func main() {
	expr := regexp.MustCompile("\\b\\w")
	texto := "antonio carlos jobim"
	// recebe funcao como parametro
	processado := expr.ReplaceAllStringFunc(
		texto,
		func(s string) string {
			return strings.ToUpper(s)
		})
	fmt.Println(processado)
}
