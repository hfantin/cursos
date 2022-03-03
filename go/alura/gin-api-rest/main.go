package main

import (
	"github.com/hfantin/cursos/gin-api-rest/models"
	"github.com/hfantin/cursos/gin-api-rest/routes"
)

func main() {
	models.Alunos = []models.Aluno{
		{Nome: "a", CPF: "111222333", RG: "12345"},
		{Nome: "b", CPF: "444555666", RG: "99999"},
	}
	routes.HandleRequest()
}
