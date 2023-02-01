package main

import (
	"context"
	"log"

	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

var client *mongo.Client

type Config struct {
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

}

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
