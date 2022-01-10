package routes

import (
	"log"
	"net/http"

	"github.com/hfantin/api-rest/controllers"
)

func HandleFunc() {
	http.HandleFunc("/", controllers.Home)
	http.HandleFunc("/api/personalidades", controllers.TodasPersonalidades)
	log.Fatal(http.ListenAndServe(":8000", nil))

}
