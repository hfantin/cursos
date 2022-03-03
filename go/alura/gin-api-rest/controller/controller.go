package controller

import (
	"fmt"

	"github.com/gin-gonic/gin"
	"github.com/hfantin/cursos/gin-api-rest/models"
)

func ExibeAluno(c *gin.Context) {
	// c.JSON(200, gin.H{
	// 	"id":   "1",
	// 	"nome": "eu",
	// })
	c.JSON(200, models.Alunos)
}

func Saudacao(c *gin.Context) {
	nome := c.Params.ByName("nome")
	c.JSON(200, gin.H{
		"digo": fmt.Sprintf("e ai %s, beleza", nome),
	})
}
