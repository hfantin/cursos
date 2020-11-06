CREATE TABLE public.usuario (
	id smallserial PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(14) NOT NULL,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	role VARCHAR NULL
);

CREATE TABLE public.ordem (
	id smallserial PRIMARY KEY,
	preco decimal(6,2) NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	data date NOT NULL,
	status VARCHAR(30) NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT usuario_fk FOREIGN KEY (user_id) REFERENCES public.usuario (id)
);

INSERT INTO public.usuario (nome,cpf,username,"password", "role") VALUES
 ('Hamilton','191','hfantin','$2a$10$AcTl0KO4Hu7WDTBWlvGApunq4EOgDJHaP0PuVh0/iV2F.cOtwFh.G', 'user'),
 ('Alura Admin','272','alura','$2a$10$ppS2rnhQhNl8kPkHiNZPPeSVGtatJA6NEgyCCuTWWQlOqoEVuyhLe', 'admin');

