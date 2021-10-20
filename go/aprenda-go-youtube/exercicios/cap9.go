package main

import "fmt"

func main() {
	fmt.Println("exercicios do capitulo 9 - slices e maps")
	// 1
	array := [5]int{1, 2, 3, 4, 5}
	for i, v := range array {
		fmt.Println(i, " - ", v)
	}
	fmt.Printf("array: %T\n\n", array)
	// 2
	slice := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
	for i, v := range slice {
		fmt.Println(i, " - ", v)
	}
	fmt.Printf("slice: %T\n", slice)
	// 3
	fmt.Println(slice[:3])
	fmt.Println(slice[4:])
	fmt.Println(slice[1:7])
	fmt.Println(slice[2:9])
	fmt.Println(slice[2 : len(slice)-1])
	// 4
	fmt.Println("")
	x := []int{42, 43, 45, 46, 47, 48, 49, 50, 51}
	x = append(x, 52)
	fmt.Println(x)
	x = append(x, 53, 54, 55)
	fmt.Println(x)
	y := []int{56, 57, 58, 59, 60}
	x = append(x, y...)
	fmt.Println(x)

	// 5
	fmt.Println("")
	z := []int{42, 43, 45, 46, 47, 48, 49, 50, 51}
	fmt.Println(append(z[:3], z[5:]...))
	// 6
	fmt.Println("")
	estados := make([]string, 0, 0)
	estados = append(estados, "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "etc")
	fmt.Println(estados, len(estados), cap(estados))
	// 7
	fmt.Println("")
	multi := [][]string{{"nome", "sobrenome"}, {"hamilton", "fantin"}, []string{"mariana", "goes"}}
	for _, v := range multi {
		fmt.Println(v)
	}

	// 8
	fmt.Println("")
	mapa := map[string][]string{"fantin_hamilton": {"xadrez", "programacao"}, "goes_mari": {"cerveja", "celular"}}
	for k, v := range mapa {
		fmt.Println(k)
		for i, h := range v {
			fmt.Println(i, " - ", h)
		}
	}

	// 9
	fmt.Println("")
	mapa["tosco"] = []string{"comida", "bebida"}
	for k, v := range mapa {
		fmt.Println(k)
		for i, h := range v {
			fmt.Println(i, " - ", h)
		}
	}

	// 10
	fmt.Println("ex 10-")
	delete(mapa, "tosco")
	for k, v := range mapa {
		fmt.Println(k)
		for i, h := range v {
			fmt.Println(i, " - ", h)
		}
	}

}
