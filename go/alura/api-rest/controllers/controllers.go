package controllers

import (
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/hfantin/api-rest/database"
	"github.com/hfantin/api-rest/models"
)

func Home(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Home Page")
}

func ListarPersonalidades(w http.ResponseWriter, r *http.Request) {
	var p []models.Personalidade
	database.DB.Find(&p)
	json.NewEncoder(w).Encode(p)
}

func ObterPersonalidade(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id := vars["id"]
	var p models.Personalidade
	database.DB.First(&p, id)
	json.NewEncoder(w).Encode(p)
}

func CriarPersonalidade(w http.ResponseWriter, r *http.Request) {
	var p models.Personalidade
	json.NewDecoder(r.Body).Decode(&p)
	database.DB.Create(&p)
	json.NewEncoder(w).Encode(&p)
}

func AtualizarPersonalidade(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id := vars["id"]
	var p models.Personalidade
	database.DB.First(&p, id)
	json.NewDecoder(r.Body).Decode(&p)
	database.DB.Save(&p)
	json.NewEncoder(w).Encode(&p)
}

func ExcluirPersonalidade(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id := vars["id"]
	var p models.Personalidade
	result := database.DB.Delete(&p, id)
	fmt.Println("deu certo: ", result.RowsAffected)
	if result.RowsAffected == 0 {
		json.NewEncoder(w).Encode(map[string]string{"error": "personalidade não encontrada"})
		return
	}
	json.NewEncoder(w).Encode(map[string]string{"msg": "excluido com sucesso"})
}

// func sendError(w http.ResponseWriter, message string) {
// 	sendJson(w, http.StatusInternalServerError, map[string]string{"error": message})
// }

// func sendJson(w http.ResponseWriter, code int, payload interface{}) {
// 	w.Header().Set("Content-Type", "application/json")
// 	w.WriteHeader(code)
// 	if payload != nil {
// 		response, _ := json.Marshal(payload)
// 		w.Write(response)
// 	}
// }
