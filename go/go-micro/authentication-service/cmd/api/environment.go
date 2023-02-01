package main

import (
	"github.com/spf13/viper"
)

type Env struct {
	ServerPort string `mapstructure:"SERVER_PORT"`
	DSN        string `mapstructure:"DSN"`
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
