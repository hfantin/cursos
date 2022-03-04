package controller

import (
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/hfantin/cursos/gin-api-rest/database"
	"github.com/hfantin/cursos/gin-api-rest/models"
)

func ListarAlunos(c *gin.Context) {
	var alunos []models.Aluno
	database.DB.Find(&alunos)
	c.JSON(http.StatusOK, alunos)
}

func ObterAluno(c *gin.Context) {
	var aluno models.Aluno
	id := c.Params.ByName("id")
	database.DB.First(&aluno, id)
	if aluno.ID == 0 {
		c.JSON(http.StatusNotFound, gin.H{
			"error": fmt.Sprintf("aluno %s nao encontrado", id),
		})
		return
	}
	c.JSON(http.StatusOK, aluno)
}

func BuscarPorCpf(c *gin.Context) {
	var aluno models.Aluno
	cpf := c.Param("cpf")
	database.DB.Where(&models.Aluno{CPF: cpf}).First(&aluno)
	if aluno.ID == 0 {
		c.JSON(http.StatusNotFound, gin.H{
			"error": fmt.Sprintf("aluno com cpf %s nao encontrado", cpf),
		})
		return
	}
	c.JSON(http.StatusOK, aluno)
}

func ApagarAluno(c *gin.Context) {
	var aluno models.Aluno
	id := c.Params.ByName("id")
	res := database.DB.Delete(&aluno, id)
	if res.RowsAffected == 0 {
		c.JSON(http.StatusNotFound, gin.H{
			"error": fmt.Sprintf("aluno %s nao encontrado", id),
		})
		return
	}
	c.JSON(http.StatusOK, gin.H{"msg": fmt.Sprintf("aluno %s apagado com sucesso", id)})
}

func AlterarAluno(c *gin.Context) {
	var aluno models.Aluno
	id := c.Params.ByName("id")
	database.DB.First(&aluno, id)
	fmt.Println("atualizando", aluno)
	if aluno.ID == 0 {
		c.JSON(http.StatusNotFound, gin.H{
			"error": fmt.Sprintf("aluno %s nao encontrado", id),
		})
		return
	}
	if err := c.ShouldBindJSON(&aluno); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{
			"error": fmt.Sprintf("nao foi possivel criar o aluno %s", err.Error()),
		})
		return
	}
	fmt.Println("2 atualizando", aluno)
	res := database.DB.Model(&aluno).UpdateColumns(aluno)
	if res.RowsAffected == 0 {
		c.JSON(http.StatusNotFound, gin.H{
			"error": fmt.Sprintf("aluno %s nao encontrado", id),
		})
		return
	}
	fmt.Println("3 atualizando", aluno)
	c.JSON(http.StatusOK, gin.H{"msg": fmt.Sprintf("aluno %s atualizado com sucesso", id)})
}

func CriarAluno(c *gin.Context) {
	var aluno models.Aluno
	if err := c.ShouldBindJSON(&aluno); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{
			"error": fmt.Sprintf("nao foi possivel criar o aluno %s", err.Error()),
		})
		return
	}
	database.DB.Create(&aluno)
	c.JSON(http.StatusOK, aluno)
}

func Saudacao(c *gin.Context) {
	nome := c.Params.ByName("nome")
	c.JSON(http.StatusOK, gin.H{
		"digo": fmt.Sprintf("e ai %s, beleza", nome),
	})
}
