package middleware

import (
	"log"
	"net/http"
)

func ContentTypeMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		log.Printf("%s %s content type middleware\n", r.Method, r.URL)
		w.Header().Set("Content-Type", "application/json")
		next.ServeHTTP(w, r)
	})
}
