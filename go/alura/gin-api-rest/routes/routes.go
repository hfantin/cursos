package routes

import (
	"github.com/gin-gonic/gin"
	"github.com/hfantin/cursos/gin-api-rest/controller"
)

func HandleRequest() {
	r := gin.Default()
	r.GET("/alunos", controller.ExibeAluno)
	r.GET("/:nome", controller.Saudacao)
	r.Run(":5000")
}
