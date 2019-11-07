import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import '../css/meucss.css';

// testes com js do bootstrap
import 'bootstrap/js/modal.js';

import { NegociacaoController } from './controllers/NegociacaoController.js';
// import { Negociacao } from './domain/index.js';

const controller = new NegociacaoController();


// teste do jquery
$('h1').on('click', ()=>alert('teste do jquery'));

console.log('funcao acionada pelo bootstrap');
console.log($('h1').modal);

/* o trecho abaixo foi para os decorators BindEvent e Controller

const $ = document.querySelector.bind(document);
$('.form').addEventListener('submit', controller.adiciona.bind(controller));
$('#botao-apaga').addEventListener('click', controller.apaga.bind(controller));
$('#botao-importa').addEventListener('click', controller.importaNegociacoes.bind(controller));

*/

// .addEventListener('click', debounce(() => {
//     console.log('EXECUTOU A OPERAÇÃO DO DEBOUNCE...');
//     controller.importaNegociacoes();
// }, 1000));

// teste de requisicao com post
/*
const negociacao = new Negociacao(new Date(), 1, 200);
const headers = new Headers();
headers.set('Content-Type', 'application/json');
const body = JSON.stringify(negociacao);
const method = 'POST';
const config = { 
    method,
    headers,
    body 
};
fetch(`${SERVICE_URL}/negociacoes`, config)
.then(() => console.log('Dado enviado com sucesso'));

*/