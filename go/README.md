## criar modulos
- go mod init github.com/hfantin/banco

## links
[go by examples](https://gobyexample.com)   
[variaveis em linha de comando](https://gobyexample.com/command-line-arguments)   
[variaveis de ambiente](https://gobyexample.com/environment-variables)   
[logs](https://www.datadoghq.com/blog/go-logging/)   

## estudar
-goroutines
-command line tools


# atualizar go para ultima versao no linux
1. sudo rm -rf /usr/local/go
2. rm -rf ~/go
3. wget "https://go.dev/dl/$(curl 'https://go.dev/VERSION?m=text').linux-amd64.tar.gz"
4. sudo tar -C /usr/local -xzf go<VERSION>.<SO>-<ARCH>.tar.gz

