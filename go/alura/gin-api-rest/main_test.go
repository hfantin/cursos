package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"strconv"
	"testing"

	"github.com/DATA-DOG/go-sqlmock"
	"github.com/gin-gonic/gin"
	"github.com/hfantin/cursos/gin-api-rest/controller"
	"github.com/hfantin/cursos/gin-api-rest/database"
	"github.com/hfantin/cursos/gin-api-rest/models"
	"github.com/stretchr/testify/assert"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

var ID int

func SetupDasRotasDeTeste() *gin.Engine {
	gin.SetMode(gin.TestMode)
	rotas := gin.Default()
	return rotas
}

func openMockDB(t *testing.T) sqlmock.Sqlmock {
	db, mock, err := sqlmock.New()
	if err != nil {
		t.Fatalf("an error '%s' was not expected when opening a stub database connection", err)
	}
	// defer db.Close()
	dialector := postgres.New(postgres.Config{
		DSN:                  "sqlmock_db_0",
		DriverName:           "postgres",
		Conn:                 db,
		PreferSimpleProtocol: true,
	})
	database.DB, err = gorm.Open(dialector, &gorm.Config{})
	if err != nil {
		t.Errorf("Failed to open gorm v2 db, got error: %v", err)
	}

	if database.DB == nil {
		t.Error("gorm db is null")
	}

	// mock.ExpectBegin()
	// mock.ExpectExec("UPDATE products").WillReturnResult(sqlmock.NewResult(1, 1))
	// mock.ExpectExec("INSERT INTO product_viewers").WithArgs(2, 3).WillReturnResult(sqlmock.NewResult(1, 1))
	// mock.ExpectCommit()

	// postgres.Open(db.)
	// gdb, err := gorm.Open("postgres", db)

	// database.DB = gdb

	//
	// "SELECT * FROM "alunos" WHERE "alunos"."deleted_at" IS NULL" with expected regexp
	// "SELECT * FROM "alunos" WHERE "alunos"."deleted_at" IS NULL"
	return mock

}

func CriaAlunoMock() {
	aluno := models.Aluno{Nome: "Nome do Aluno Teste", CPF: "12345678901", RG: "123456789"}
	database.DB.Create(&aluno)
	ID = int(aluno.ID)
}

func DeletaAlunoMock() {
	var aluno models.Aluno
	database.DB.Delete(&aluno, ID)
}

func TestVerificaStatusCodeDaSaudacaoComParametro(t *testing.T) {
	r := SetupDasRotasDeTeste()
	r.GET("/saudacao/:nome", controller.Saudacao)
	req, _ := http.NewRequest("GET", "/saudacao/hamilton", nil)
	resp := httptest.NewRecorder()
	r.ServeHTTP(resp, req)
	assert.Equal(t, http.StatusOK, resp.Code, "deveriam ser iguais")
	expected := `{"digo":"e ai hamilton, beleza"}`
	body, _ := ioutil.ReadAll(resp.Body)
	assert.Equal(t, expected, string(body), "deveriam ser iguais")
}

// com mock do db
func TestListaTodosOsAlunosHanlder(t *testing.T) {
	mock := openMockDB(t)
	rows := sqlmock.NewRows([]string{"nome", "cpf", "rg"}).AddRow("jovem", "12345678900", "999888777")
	mock.ExpectQuery(`^SELECT (.*) FROM "alunos" WHERE "alunos"."deleted_at" IS NULL$`).WillReturnRows(rows)
	r := SetupDasRotasDeTeste()
	r.GET("/alunos", controller.ListarAlunos)
	req, _ := http.NewRequest("GET", "/alunos", nil)
	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)
	assert.Equal(t, http.StatusOK, resposta.Code)
}

func TestBucaAlunoPorCPFHandler(t *testing.T) {
	database.Conectar()
	CriaAlunoMock()
	defer DeletaAlunoMock()
	r := SetupDasRotasDeTeste()
	r.GET("/alunos/cpf/:cpf", controller.BuscarPorCpf)
	req, _ := http.NewRequest("GET", "/alunos/cpf/12345678901", nil)
	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)
	assert.Equal(t, http.StatusOK, resposta.Code)
}

func TestBuscaAlunoPorIDHandler(t *testing.T) {
	database.Conectar()
	CriaAlunoMock()
	defer DeletaAlunoMock()
	r := SetupDasRotasDeTeste()
	r.GET("/alunos/:id", controller.ObterAluno)
	pathDaBusca := "/alunos/" + strconv.Itoa(ID)
	req, _ := http.NewRequest("GET", pathDaBusca, nil)
	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)
	var alunoMock models.Aluno
	json.Unmarshal(resposta.Body.Bytes(), &alunoMock)
	assert.Equal(t, "Nome do Aluno Teste", alunoMock.Nome, "Os nomes devem ser iguais")
	assert.Equal(t, "12345678901", alunoMock.CPF)
	assert.Equal(t, "123456789", alunoMock.RG)
	assert.Equal(t, http.StatusOK, resposta.Code)
}

func TestDeletaAlunoHandler(t *testing.T) {
	database.Conectar()
	CriaAlunoMock()
	r := SetupDasRotasDeTeste()
	r.DELETE("/alunos/:id", controller.ApagarAluno)
	pathDeBusca := "/alunos/" + strconv.Itoa(ID)
	req, _ := http.NewRequest("DELETE", pathDeBusca, nil)
	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)
	assert.Equal(t, http.StatusOK, resposta.Code)
}

func TestEditaUmAlunoHandler(t *testing.T) {
	database.Conectar()
	CriaAlunoMock()
	defer DeletaAlunoMock()
	r := SetupDasRotasDeTeste()
	r.PATCH("/alunos/:id", controller.AlterarAluno)
	aluno := models.Aluno{Nome: "Nome do Aluno Teste", CPF: "47123456789", RG: "123456700"}
	valorJson, _ := json.Marshal(aluno)
	fmt.Println("json", string(valorJson))
	pathParaEditar := "/alunos/" + strconv.Itoa(ID)
	req, _ := http.NewRequest("PATCH", pathParaEditar, bytes.NewBuffer(valorJson))
	resposta := httptest.NewRecorder()
	r.ServeHTTP(resposta, req)
	var alunoMockAtualizado models.Aluno
	fmt.Println("body", resposta.Body)
	json.Unmarshal(resposta.Body.Bytes(), &alunoMockAtualizado)
	fmt.Println("mock", alunoMockAtualizado)
	assert.Equal(t, "47123456789", alunoMockAtualizado.CPF)
	// assert.Equal(t, "123456700", alunoMockAtualizado.RG)
	// assert.Equal(t, "Nome do Aluno Teste", alunoMockAtualizado.Nome)
}
