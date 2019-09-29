-- cria tabela
create table produtos (
	id serial primary key, 
	nome varchar, 
	descricao varchar,
	preco decimal, 
	quantidade integer 
)

-- inserir valores
insert into produtos
	(nome, descricao, preco, quantidade) 
values 
	('Heineken', 'Lata 350 ml', 3.59, 97), 
	('Roleta Russa', 'Lata 350 ml', 9.87, 53),
	('Faxe', 'Lata 1L', 13.41, 47),
	('Guinness', 'Lata 400ml', 14.53, 18)