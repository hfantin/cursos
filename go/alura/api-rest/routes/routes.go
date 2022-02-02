package routes

import (
	"log"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/hfantin/api-rest/controllers"
)

func HandleFunc() {
	r := mux.NewRouter()
	r.HandleFunc("/", controllers.Home)
	r.HandleFunc("/api/personalidades", controllers.ListarPersonalidades).Methods("Get")
	r.HandleFunc("/api/personalidades/{id}", controllers.ObterPersonalidade).Methods("Get")
	r.HandleFunc("/api/personalidades", controllers.CriarPersonalidade).Methods("Post")
	r.HandleFunc("/api/personalidades/{id}", controllers.AtualizarPersonalidade).Methods("Put")
	r.HandleFunc("/api/personalidades/{id}", controllers.ExcluirPersonalidade).Methods("Delete")
	log.Fatal(http.ListenAndServe(":8000", r))

}
