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
	r.HandleFunc("/api/personalidades", controllers.TodasPersonalidades).Methods("Get")
	r.HandleFunc("/api/personalidades/{id}", controllers.UmaPersonalidade).Methods("Get")
	log.Fatal(http.ListenAndServe(":8000", r))

}
