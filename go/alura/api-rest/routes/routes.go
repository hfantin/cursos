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
	r.Use(middleware.ContentTypeMiddleware)
	// r.HandleFunc("/", controllers.Home)
	r.HandleFunc("/api/personalidades", controllers.ListarPersonalidades).Methods("Get")
	r.HandleFunc("/api/personalidades/{id}", controllers.ObterPersonalidade).Methods("Get")
	r.HandleFunc("/api/personalidades", controllers.CriarPersonalidade).Methods("Post")
	r.HandleFunc("/api/personalidades/{id}", controllers.AtualizarPersonalidade).Methods("Put")
	r.HandleFunc("/api/personalidades/{id}", controllers.ExcluirPersonalidade).Methods("Delete")
	r.PathPrefix("/").Handler(http.FileServer(http.Dir("static/build")))
	// log.Fatal(http.ListenAndServe(":8000", r))
	log.Fatal(http.ListenAndServe(":8000", handlers.CORS(handlers.AllowedOrigins([]string{"*"}))(r)))

}
