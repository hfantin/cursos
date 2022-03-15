# grpc in go
## requirements 
- install protobuffer from [here](https://github.com/protocolbuffers/protobuf/releases)

## links 
- [grpc tutorial](https://grpc.io/docs/languages/go/quickstart/)

## gerar codigo 
 > protoc --go_out=. --go_opt=paths=source_relative --go-grpc_out=. --go-grpc_opt=paths=source_relative proto/helloworld.proto