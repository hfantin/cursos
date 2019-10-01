package main

import (
	"log"
	"net/http"

	"github.com/hfantin/web/routes"
)

func main() {
	routes.CarregaRotas()
	log.Println("Inicializando servidor na porta 8000")
	http.ListenAndServe(":8000", nil)
}
