package main

import (
	"fmt"
	"os"
	"strings"
)

/*
execucao:

go run mapas.go Lorem ipsum dolor sit amet leo eu velit ante sagittis dolor \
turpis dis
*/

func main() {
	palavras := os.Args[1:]
	estatisticas := colherEstatisticas(palavras)
	imprimir(estatisticas)
}
func colherEstatisticas(palavras []string) map[string]int {
	estatisticas := make(map[string]int)
	for _, palavra := range palavras {
		inicial := strings.ToUpper(string(palavra[0]))
		contador := estatisticas[inicial]
		estatisticas[inicial] = contador + 1
		// if encontrado {
		// 	estatisticas[inicial] = contador + 1
		// } else {
		// 	estatisticas[inicial] = 1
		// }
	}
	return estatisticas
}
func imprimir(estatisticas map[string]int) {
	fmt.Println("Contagem de palavras iniciadas em cada letra:")
	for inicial, contador := range estatisticas {
		fmt.Printf("%s = %d\n", inicial, contador)
	}
}
