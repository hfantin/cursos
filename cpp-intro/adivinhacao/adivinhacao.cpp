#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

int main() {
    cout << "*************************************" << endl; 
    cout << "* Bem vindos ao jogo da adivinhação *" << endl; 
    cout << "*************************************" << endl; 

    cout << "Escolha o seu nível de dificuldade:" << endl;
    cout << "Fácil (F), Médio (M) ou Difícil (D)" << endl;
    char dificuldade;
    cin >> dificuldade;

    int numero_de_tentativas;

    if(dificuldade == 'F'){
        numero_de_tentativas = 15;
    } else if(dificuldade == 'M'){
        numero_de_tentativas = 10;
    } else {
        numero_de_tentativas = 5;
    }

    // gera numero aleatorio
    srand(time(NULL));
    const int NUMERO_SECRETO = rand() % 100;

    int tentativas = 0;

    double pontos = 1000.0;

    bool nao_acertou = true;

    for(tentativas = 1; tentativas <= numero_de_tentativas; tentativas++) {
        int chute;
        cout << "Tentativa " << tentativas << endl; 
        cout << "Qual seu chute?" << endl; 
        cin >> chute;

        // pontuacao
        double pontos_perdidos = abs(chute - NUMERO_SECRETO)/2.0;
        pontos -= pontos_perdidos;

         if(chute == NUMERO_SECRETO) {   
            cout << "Parabéns, voce acertou o numero secreto!" << endl; 
            nao_acertou = false;
            break;
        } else if(chute > NUMERO_SECRETO) {
            cout << "Seu chute foi maior que o numero secreto..." << endl; 
        } else {
            cout << "Seu chute foi menor que o numero secreto..." << endl; 
        }
    }
    cout << "Fim de jogo! " <<  endl;
    if(nao_acertou) {
        cout << "Você perdeu! Tente novamente!" << endl;
    } else {
        cout << "Você acertou o número secreto em " << tentativas << " tentativas" << endl;
        cout.precision(2);
        cout << fixed;
        cout << "Sua pontuação foi de " << pontos << " pontos." << endl;
    }
}
