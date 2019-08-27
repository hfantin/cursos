# Projeto do curso springboot parte 1 do Alura

- para acessar o console do h2, utilize a url abaixo;   
> http://localhost:9000/h2-console

- teste usando o postman com as seguinte uri:   
[http://localhost:9000/v1/topicos](http://localhost:9000/v1/topicos)
payload:         
```
{
    "titulo": "Dúvida",
    "mensagem": "Erro ao criar projeto",
    "nomeCurso": "Spring Boot"
}
```

- para habilitar o suporte do spring à paginação, utilize a anotação @EnableSpringDataWebSupport na classe principal

- cache - usar provedor, Redis por exemplo
- cuidado com o cache, deve ser usado em tabelas que quase não mudam


### Links 

- [spring-boot-admin](https://github.com/codecentric/spring-boot-admin)
- [swagger](https://swagger.io)
- [https://springfox.github.io/springfox/](https://springfox.github.io/springfox/)
= [swagger-ui](http://localhost:9000/swagger-ui.html)