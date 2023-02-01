package main

import (
	"github.com/spf13/viper"
)

type Env struct {
	ServerPort    string `mapstructure:"SERVER_PORT"`
	MongoUrl      string `mapstructure:"MONGO_URL"`
	MongoUser     string `mapstructure:"MONGO_USER"`
	MongoPassword string `mapstructure:"MONGO_PASSWORD"`
	RpcPort       string `mapstructure:"RPC_PORT"`
	GrpcPort      string `mapstructure:"GRPC_PORT"`
}

func LoadConfig(path string) (*Env, error) {
	viper.AddConfigPath(path)
	viper.SetConfigName("app")
	viper.SetConfigType("env")

	viper.AutomaticEnv()

	err := viper.ReadInConfig()
	if err != nil {
		return nil, err
	}

	var env Env
	err = viper.Unmarshal(&env)
	return &env, err
}
