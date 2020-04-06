import { NegociacaoController } from './controllers/negociacaocontroller';

const controller = new NegociacaoController();
$('.form').submit(controller.adiciona.bind(controller));
