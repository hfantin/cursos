package routes

import (
	"github.com/gin-gonic/gin"
	"github.com/hfantin/cursos/gin-api-rest/controller"
)

func HandleRequest() {
	r := gin.Default()
	r.GET("/alunos", controller.ListarAlunos)
	r.GET("/alunos/:id", controller.ObterAluno)
	r.GET("/alunos/cpf/:cpf", controller.BuscarPorCpf)
	r.POST("/alunos", controller.CriarAluno)
	r.PATCH("/alunos/:id", controller.AlterarAluno)
	r.DELETE("/alunos/:id", controller.ApagarAluno)
	r.GET("/saudacao/:nome", controller.Saudacao)
	r.Run(":5000")
}
