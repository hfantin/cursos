package main

import (
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/gin-gonic/gin"
	"github.com/hfantin/cursos/gin-api-rest/controller"
	"github.com/stretchr/testify/assert"
)

func Setup() *gin.Engine {
	return gin.Default()
}
func TestVerificaStatusCodeDaSaudacaoComParametro(t *testing.T) {
	r := Setup()
	r.GET("/saudacao/:nome", controller.Saudacao)
	req, _ := http.NewRequest("GET", "/saudacao/hamilton", nil)
	resp := httptest.NewRecorder()
	r.ServeHTTP(resp, req)
	assert.Equal(t, http.StatusOK, resp.Code, "deveriam ser iguais")
	expected := `{"digo":"e ai hamilton, beleza"}`
	body, _ := ioutil.ReadAll(resp.Body)
	assert.Equal(t, expected, string(body), "deveriam ser iguais")

}
