package main

import (
	"fmt"
	"log"
	"net/http"
)

type Config struct {
	Env *Env
}

func main() {
	env, err := LoadConfig(".")
	if err != nil {
		log.Panic("cannot load environment variables")
	}

	app := Config{Env: env}

	log.Printf("Starting broker service on port %s\n", env.ServerPort)

	// define http server
	srv := &http.Server{
		Addr:    fmt.Sprintf(":%s", env.ServerPort),
		Handler: app.Routes(),
	}

	// start the server
	err = srv.ListenAndServe()
	if err != nil {
		log.Panic(err)
	}
}
