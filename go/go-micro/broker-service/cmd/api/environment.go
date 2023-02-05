package main

import (
	"log"

	"github.com/spf13/viper"
)

type Env struct {
	ServerPort       string `mapstructure:"SERVER_PORT"`
	AuthServiceUrl   string `mapstructure:"AUTH_SERVICE_URL"`
	LoggerServiceUrl string `mapstructure:"LOGGER_SERVICE_URL"`
}

func LoadConfig(path string) (*Env, error) {
	viper.AddConfigPath(path)
	viper.SetConfigName(".env")
	viper.SetConfigType("env")

	viper.AutomaticEnv()

	err := viper.ReadInConfig()
	if err != nil {
		log.Println("file doesnot exist")
		return nil, err
	}

	var env Env
	err = viper.Unmarshal(&env)
	return &env, err
}
