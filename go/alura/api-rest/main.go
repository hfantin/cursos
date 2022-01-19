package main

import (
	"fmt"

	"github.com/hfantin/api-rest/models"
	"github.com/hfantin/api-rest/routes"
)

func main() {
	models.Personalidades = []models.Personalidade{
		{Id: 1, Nome: "nome 1", Historia: "historia 1"},
		{Id: 2, Nome: "nome 2", Historia: "historia 2"},
	}
	fmt.Println("Iniciando servidor...")
	routes.HandleFunc()
}
