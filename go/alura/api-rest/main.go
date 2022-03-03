package main

import (
	"log"

	"github.com/hfantin/api-rest/database"
	"github.com/hfantin/api-rest/routes"
)

func main() {
	log.Println("Iniciando servidor...")
	database.Conectar()
	routes.HandleFunc()
}
