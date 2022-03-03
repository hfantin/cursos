package routes

import (
	"log"
	"net/http"

	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
	"github.com/hfantin/api-rest/controllers"
	"github.com/hfantin/api-rest/middleware"
)

func HandleFunc() {
	r := mux.NewRouter()
	api := r.PathPrefix("/api").Subrouter()
	api.Use(middleware.ContentTypeMiddleware)
	api.HandleFunc("/personalidades", controllers.ListarPersonalidades).Methods("Get")
	api.HandleFunc("/personalidades/{id}", controllers.ObterPersonalidade).Methods("Get")
	api.HandleFunc("/personalidades", controllers.CriarPersonalidade).Methods("Post")
	api.HandleFunc("/personalidades/{id}", controllers.AtualizarPersonalidade).Methods("Put")
	api.HandleFunc("/personalidades/{id}", controllers.ExcluirPersonalidade).Methods("Delete")
	r.HandleFunc("/home", controllers.Home).Methods("Get")
	r.PathPrefix("/").Handler(http.FileServer(http.Dir("static/build")))
	// log.Fatal(http.ListenAndServe(":8000", r))
	log.Fatal(http.ListenAndServe(":8000", handlers.CORS(handlers.AllowedOrigins([]string{"*"}))(r)))

}
