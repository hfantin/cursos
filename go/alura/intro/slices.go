package main

import "log"

func main() {
	log.Println("Slices")
	// array
	var array [4]string // a ultima posicao esta vazia
	array[0] = "Hamilton"
	array[1] = "Mari"
	array[2] = "Samuca"
	log.Println("array", array, "tamanho", len(array), "capacidade", cap(array))
	// simples
	simples := []string{"Hamilton", "Mari", "Samuca"}
	log.Println("simples", simples, "tamanho", len(simples), "capacidade", cap(simples))
	// antes do append a capacidade era 3, depois dobrou o tamanho inicial, indo para 6
	simples = append(simples, "Gato")
	log.Println("simples", simples, "tamanho", len(simples), "capacidade", cap(simples))
	// make
	// commake := make([]string, 0, 0) //func make([]T, len, cap) []T - se omitir capacidade, o default é igual a len
	commake := make([]string, 0) //func make([]T, len, cap) []T - se omitir capacidade, o default é igual a len
	log.Println("commake", commake, "tamanho", len(commake), "capacidade", cap(commake))
	commake = append(commake, "Hamilton", "Mari", "Samuca")
	log.Println("commake", commake, "tamanho", len(commake), "capacidade", cap(commake))

}
