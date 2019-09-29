// http://golang.org/pkg/net/http/

package main

import (
	"fmt"
	"net/http"
	"time"
)

func main() {
	http.HandleFunc(
		"/tempo",
		func(w http.ResponseWriter, r *http.Request) {
			fmt.Fprintf(w, "%s",
				time.Now().Format("2006-01-02 15:04:05"))
		})
	http.HandleFunc(
		"/",
		func(w http.ResponseWriter, r *http.Request) {
			fmt.Fprintf(w, "%s",
				"<p>teste</p>")
		})
	http.ListenAndServe(":8080", nil)
}
