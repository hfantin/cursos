package main

import (
	"github.com/hfantin/cursos/gin-api-rest/database"
	"github.com/hfantin/cursos/gin-api-rest/routes"
)

func main() {
	database.Conectar()
	routes.HandleRequest()
}
