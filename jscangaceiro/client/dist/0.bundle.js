webpackJsonp([0],{27:function(a,b,c){"use strict";function d(a){return function(){var b=a.apply(this,arguments);return new Promise(function(a,c){function d(e,f){try{var g=b[e](f),h=g.value}catch(a){return void c(a)}return g.done?void a(h):Promise.resolve(h).then(function(a){d("next",a)},function(a){d("throw",a)})}return d("next")})}}Object.defineProperty(b,"__esModule",{value:!0}),c.d(b,"NegociacaoService",function(){return h});var e=c(6),f=c(2),g=c(1);let h=class{constructor(){this._http=new e.a}obtemNegociacoesDaSemanaTeste(){var a=this;return d(function*(){try{const b=yield a._http.get(`${server}negociacoes/semana`);return console.log("dados="+JSON.stringify(b)),b.map(function(a){return new g.a(new Date(a.data),a.quantidade,a.valor)})}catch(a){throw new f.a("N\xE3o foi poss\xEDvel obter as negocia\xE7\xF5es da semana teste")}})()}obtemNegociacoesDaSemana(){return this._http.get(`${server}negociacoes/semana`).then((a)=>a.map((a)=>new g.a(new Date(a.data),a.quantidade,a.valor)),()=>{throw new f.a("N\xE3o foi poss\xEDvel obter as negocia\xE7\xF5es da semana")})}obtemNegociacoesDaSemanaAnterior(){return this._http.get(`${server}negociacoes/anterior`).then((a)=>a.map((a)=>new g.a(new Date(a.data),a.quantidade,a.valor)),()=>{throw new f.a("N\xE3o foi poss\xEDvel obter as negocia\xE7\xF5es da semana anterior")})}obtemNegociacoesDaSemanaRetrasada(){return this._http.get(`${server}negociacoes/retrasada`).then((a)=>a.map((a)=>new g.a(new Date(a.data),a.quantidade,a.valor)),()=>{throw new f.a("N\xE3o foi poss\xEDvel obter as negocia\xE7\xF5es da semana retrasada")})}obtemNegociacoesDoPeriodo(){var a=this;return d(function*(){try{let b=yield Promise.all([a.obtemNegociacoesDaSemana(),a.obtemNegociacoesDaSemanaAnterior(),a.obtemNegociacoesDaSemanaRetrasada()]);return b.reduce(function(a,b){return a.concat(b)},[]).sort(function(c,a){return a.data.getTime()-c.data.getTime()})}catch(a){throw console.log(a),new f.a("N\xE3o foi poss\xEDvel obter as negocia\xE7\xF5es do per\xEDodo")}})()}obterNegociacoesDaSemanaOld(a){const b=new XMLHttpRequest;b.open("GET",`${server}negociacoes/semana`),b.onreadystatechange=()=>{if(4==b.readyState)if(200==b.status){const c=JSON.parse(b.responseText).map((a)=>new g.a(new Date(a.data),a.quantidade,a.valor));a(null,c)}else console.log(b.responseText),a("N\xE3o foi poss\xEDvel obter nas negocia\xE7\xF5es da semana",null)},b.send()}}}});