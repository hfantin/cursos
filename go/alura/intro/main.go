package main

import (
	"bufio"
	"fmt"
	"io"
	"io/ioutil"
	"net/http"
	"os"
	"strconv"
	"strings"
	"time"
)

const monitoramentos = 2
const delay = 5

func main() {
	exibeIntroducao()
	exibeEnv()

	for {
		exibeMenu()

		comando := leComando()

		switch comando {
		case 1:
			iniciarMonitoramento()
		case 2:
			fmt.Println("Exibindo Logs...")
			imprimeLogs()
		case 0:
			fmt.Println("Saindo do programa")
			os.Exit(0)
		default:
			fmt.Println("Não conheço este comando")
			os.Exit(-1)
		}
	}

}

func exibeEnv() {
	fmt.Println("Exibe variaveis de ambiente")
	teste := os.Getenv("teste")
	fmt.Println("variavel de teste", teste)
	// for _, e := range os.Environ() {
	// 	pair := strings.Split(e, "=")
	// 	fmt.Println(pair[0])
	// }

}

func exibeIntroducao() {
	// argsWithProg := os.Args        // todos os argumentos inclusive o proprio programa
	args := os.Args[1:] // todos os argumentos inclusive o proprio programa
	var nome string
	if len(args) > 0 {
		nome = args[0]
	} else {
		nome = "usuario"
	}
	versao := 1.2
	fmt.Println("Olá, sr.", nome)
	fmt.Println("Este programa está na versão", versao)
}

func exibeMenu() {
	fmt.Println("1- Iniciar Monitoramento")
	fmt.Println("2- Exibir Logs")
	fmt.Println("0- Sair do Programa")
}

func leComando() int {
	var comandoLido int
	fmt.Scan(&comandoLido)
	fmt.Println("O comando escolhido foi", comandoLido)
	fmt.Println("")

	return comandoLido
}

func iniciarMonitoramento() {
	fmt.Println("Monitorando...")
	sites := leSitesDoArquivo()

	for i := 0; i < monitoramentos; i++ {
		for i, site := range sites {
			fmt.Println("Testando site", i, ":", site)
			testaSite(site)
		}
		time.Sleep(delay * time.Second)
		fmt.Println("")
	}

	fmt.Println("")
}

func testaSite(site string) {
	resp, err := http.Get(site)

	if err != nil {
		fmt.Println("Ocorreu um erro:", err)
	}

	if resp.StatusCode == 200 {
		fmt.Println("Site:", site, "foi carregado com sucesso!")
		registraLog(site, true)
	} else {
		fmt.Println("Site:", site, "esta com problemas. Status Code:", resp.StatusCode)
		registraLog(site, false)
	}
}

func leSitesDoArquivo() []string {
	var sites []string
	// le arquivo inteiro
	// arquivo2, err2 := ioutil.ReadFile("sites.txt")
	// if err2 != nil {
	// 	fmt.Println("Ocorreu um erro:", err2)
	// }

	// fmt.Println(arquivo2)

	arquivo, err := os.Open("sites.txt")

	if err != nil {
		fmt.Println("Ocorreu um erro:", err)
	}

	leitor := bufio.NewReader(arquivo)
	for {
		linha, err := leitor.ReadString('\n')
		// remove espacos e outros caracteres, por exemplo, \n
		linha = strings.TrimSpace(linha)

		sites = append(sites, linha)

		if err == io.EOF {
			break
		}

	}

	arquivo.Close()
	return sites
}

func registraLog(site string, status bool) {

	arquivo, err := os.OpenFile("log.txt", os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)

	if err != nil {
		fmt.Println(err)
	}

	// a formatacao de data/hora no go segue um padrao diferente, consultar documentacao
	// https://golang.org/src/time/format.go
	arquivo.WriteString(time.Now().Format("02/01/2006 15:04:05.00000") + " - " + site + " - online: " + strconv.FormatBool(status) + "\n")

	arquivo.Close()
}

func imprimeLogs() {

	arquivo, err := ioutil.ReadFile("log.txt")

	if err != nil {
		fmt.Println(err)
	}

	fmt.Println(string(arquivo))

}