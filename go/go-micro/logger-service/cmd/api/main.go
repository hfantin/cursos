package main

import (
	"context"
	"fmt"
	"log"
	"net/http"
	"time"

	"github.com/hfantin/logger/data"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

var client *mongo.Client

type Config struct {
	Models data.Models
}

func main() {
	// define environment variables
	env, err := LoadConfig(".")
	log.Println("Starting logger service on port", env.ServerPort)
	if err != nil {
		log.Panic("cannot load environment variables")
	}
	// connect to mongo
	mongoClient, err := connectToMongo(env)
	if err != nil {
		log.Panic(err)
	}
	client = mongoClient

	// create a context in order to disconnect
	ctx, cancel := context.WithTimeout(context.Background(), 15*time.Second)

	defer cancel()

	// close connection

	defer func() {
		if err = client.Disconnect(ctx); err != nil {
			panic(err)
		}
	}()

	app := Config{
		Models: data.New(client),
	}
	// start web server
	// go app.serve(env)
	srv := &http.Server{
		Addr:    fmt.Sprintf(":%s", env.ServerPort),
		Handler: app.Routes(),
	}

	err = srv.ListenAndServe()
	if err != nil {
		log.Panic()
	}

}

// func (app *Config) serve(env *Env) {
// 	srv := &http.Server{
// 		Addr:    fmt.Sprintf(":%s", env.ServerPort),
// 		Handler: app.Routes(),
// 	}

// 	err := srv.ListenAndServe()
// 	if err != nil {
// 		log.Panic()
// 	}
// }

func connectToMongo(env *Env) (*mongo.Client, error) {
	// create connection options
	clientOptions := options.Client().ApplyURI(env.MongoUrl)
	clientOptions.SetAuth(options.Credential{
		Username: env.MongoUser,
		Password: env.MongoPassword,
	})

	// connect
	c, err := mongo.Connect(context.TODO(), clientOptions)
	if err != nil {
		log.Println("Error connecting:", err)
		return nil, err
	}

	return c, nil
}
