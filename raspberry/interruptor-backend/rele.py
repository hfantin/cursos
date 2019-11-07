import RPi.GPIO as GPIO    
import sys

def inicializaPlaca(): 
	GPIO.setmode(GPIO.BOARD) 
	GPIO.setwarnings(False) 

def definePinoComoSaida(numeroPino):
	GPIO.setup(numeroPino, GPIO.OUT)

def escreveParaPorta(numeroPino, estadoPorta):
	GPIO.output(numeroPino, estadoPorta)

def obterEstadoPorta(numeroPino):
        return GPIO.input(numeroPino)


#inicializaPlaca()
#definePinoComoSaida(7)
#escreveParaPorta(7, 0)
#print('estado: ', obterEstadoPorta(7))
