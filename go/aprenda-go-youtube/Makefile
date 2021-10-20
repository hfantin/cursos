all: build-linux build-mac build-win build-arm

build-linux:
	@echo '- building linux...'
	@GOOS=linux go build -o bin/$(APP_NAME)

build-arm:
	@echo '- building arm...'
	@GOOS=linux GOARCH=amd64 CGO_ENABLED=0 go build -o bin/$(APP_NAME).arm

build-mac:
	@echo '- building mac...'
	@GOOS=darwin GOARCH=amd64 CGO_ENABLED=0 go build -o bin/$(APP_NAME).app

build-win:
	@echo '- building windows...'
	@GOOS=windows GOARCH=386 CGO_ENABLED=0 go build -o bin/$(APP_NAME).exe


GOOS=linux GOARCH=amd64 CGO_ENABLED=0 go build hello.go -o hello.arm


env GOOS=linux GOARCH=arm GOARM=5 go build


const singleValue = values.reduce((acc, value) => acc + value, 0);

