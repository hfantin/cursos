package main

import (
	"database/sql"
	"html/template"
	"log"
	"net/http"

	_ "github.com/lib/pq"
)

var tmpl = template.Must(template.ParseGlob("templates/*.html"))

type Produto struct {
	Nome       string
	Descricao  string
	Preco      float64
	Quantidade int
}

func main() {
	http.HandleFunc("/", Index)
	log.Println("Iniciando aplicacao na porta 8000")
	http.ListenAndServe(":8000", nil)

}
func Index(w http.ResponseWriter, r *http.Request) {
	db := conectarDB()
	cursor, err := db.Query("select * from produtos")
	if err != nil {
		panic(err.Error())
	}
	p := Produto{}
	produtos := []Produto{}
	for cursor.Next() {
		var id, quantidade int
		var nome, descricao string
		var preco float64

		err = cursor.Scan(&id, &nome, &descricao, &preco, &quantidade)
		if err != nil {
			panic(err.Error())
		}

		p.Nome = nome
		p.Descricao = descricao
		p.Preco = preco
		p.Quantidade = quantidade

		produtos = append(produtos, p)
	}

	tmpl.ExecuteTemplate(w, "Index", produtos)
	defer db.Close()
}

func conectarDB() *sql.DB {
	conexao := "user=guest dbname=test password=guest host=localhost sslmode=disable"
	db, err := sql.Open("postgres", conexao)
	if err != nil {
		panic(err.Error())
	}
	return db
}
