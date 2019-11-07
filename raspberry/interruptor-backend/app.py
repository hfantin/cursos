from flask import Flask, jsonify, url_for, make_response, request, abort
from rele  import inicializaPlaca, definePinoComoSaida, escreveParaPorta, obterEstadoPorta

reles = []

def init():

    inicializaPlaca()

    definePinoComoSaida(7)
    definePinoComoSaida(11)

    escreveParaPorta(7, 0)
    escreveParaPorta(11, 0)

    reles.append({'id': 7, 'ligado': bool(obterEstadoPorta(7))})
    reles.append({'id': 11, 'ligado': bool(obterEstadoPorta(11))})

init()
app = Flask(__name__)


@app.route('/api/v1/reles', methods=['GET'])
def getReles():
#	return jsonify({'reles': [makePublicRele(rele) for rele in reles]})
        return jsonify({'reles': reles})

@app.route('/api/v1/reles/<int:id>', methods=['GET'])
def getRele(id):
    rele = [rele for rele in reles if rele['id'] == id]
    if len(rele) == 0:
        abort(404)
    return jsonify({'rele': rele[0]})

#@app.route('/api/v1/reles/<int:id>', methods=['PUT'])
#def ascenderRele(id):
@app.route('/api/v1/reles', methods=['PUT'])
def alterarRele():
    print('alterarRele() - ', request.json)
    if not 'id' in request.json:
        abort(400)
    rele = [rele for rele in reles if rele['id'] == request.json['id']]
    if len(rele) == 0:
        abort(404)
    if not request.json:
        print('nao possui request.json - abortar')
        abort(400)
    if 'ligado' in request.json and type(request.json['ligado']) is not bool:
        print('abortar ', type(request.json['ligado'])) 
        abort(400)
    item = rele[0]
    item['ligado'] = request.json.get('ligado', reles[0]['ligado'])
    escreveParaPorta(item['id'], item['ligado'])
#    print('estado da porta', obterEstadoPorta(item['id']))
    return jsonify({'rele': item})

@app.route('/api/v1/reles/<int:id>', methods=['DELETE'])
def deleteRele(id):
    print(request.json)
    return jsonify({'result': 'Funcao DELETE nao implementada'})

@app.route('/api/v1/reles/<int:id>', methods=['POST'])
def criarRele(id):
    print(request.json)
    return jsonify({'result': 'Funcao POST nao implementada'})


@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Not found'}), 404)

def makePublicRele(rele):
    novoRele = {}
    for field in rele:
        if field == 'id':
            novoRele['uri'] = url_for('getRele', id=rele['id'], _external=True)
        else:
            novoRele[field] = rele[field]
    return novoRele


if __name__ == '__main__':
	app.run(debug=True, host='0.0.0.0')

