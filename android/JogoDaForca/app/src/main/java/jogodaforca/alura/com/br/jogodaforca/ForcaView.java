package jogodaforca.alura.com.br.jogodaforca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by hamilton on 26/10/16.
 */

public class ForcaView extends PlanoCartesiano {

    private ForcaController forcaController;
    /**
     * Responsavel por armazenar todas as figuras geométricas a serem desenhadas
     */
    private Path pathForca;
    private Paint paintForca;

    private enum Membro{braco, perna}
    private enum Lado{direito, esquerdo}

    public ForcaView(Context context) {
        super(context);
    }


    public ForcaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPathForca(new Path());
        getPathForca().rewind();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        setDesenhaPlanoCartesiano(true);
        plotaArmacaoForca();
        if(getForcaController() != null){
            switch (getForcaController().getQuantidadeErros()){
                case 0:
                    plotaCabeca();
                    break;
                case 1:
                    plotaCorpo();
                    break;
                case 2:
                    plotaMembro(Membro.braco, Lado.esquerdo);
                    break;
                case 3:
                    plotaMembro(Membro.braco, Lado.direito);
                    break;
                case 4:
                    plotaMembro(Membro.perna, Lado.esquerdo);
                    break;
                case 5:
                    plotaMembro(Membro.perna, Lado.direito);
                    break;
            }
        }
//        plotaTracos();
        drawLetrasCorretas(canvas);
        canvas.drawPath(getPathForca(), getPaintForca());
    }

    private void plotaArmacaoForca(){
        getPathForca().moveTo(toPixel(1), toPixel(9));
        getPathForca().lineTo(toPixel(3), toPixel(9));

        getPathForca().moveTo(toPixel(2), toPixel(9));
        getPathForca().lineTo(toPixel(2), toPixel(1));
        // o cursor já se encontra na posição x = 2 e y = 1,
        // então não é preciso posiciona-lo novamente, basta passar o deslocamento à direita - x=5 e y=0(mantem-se constante)
        getPathForca().rLineTo(toPixel(5), 0);
        // mesma coisa, somente deslocando o eixo y em 1 posição:
        getPathForca().rLineTo(0, toPixel(1));

    }

    private void plotaCabeca(){
        getPathForca().addCircle(toPixel(7), toPixel(3), toPixel(1), Path.Direction.CW);
    }

    private void plotaCorpo(){
        getPathForca().moveTo(toPixel(7), toPixel(4));
        getPathForca().lineTo(toPixel(7), toPixel(7));
    }

    private void plotaMembro( Membro membro, Lado lado){
        final int posicaoDoCorpo = 7; // posicao do corpo no eixo x
        final int alturaDoBraco = 5; // define o Y onde será desenhado o braço do nosso boneco
        final int alturaDaPerna = 7; // define o Y onde será desenhado a perna do nosso boneco
        int alturaFinal;

        // primeiro ponto
        if(membro == Membro.braco){
            getPathForca().moveTo(toPixel(posicaoDoCorpo), toPixel(alturaDoBraco));
            alturaFinal = alturaDoBraco + 1;
        }else{
            getPathForca().moveTo(toPixel(posicaoDoCorpo), toPixel(alturaDaPerna));
            alturaFinal = alturaDaPerna + 1;
        }

        if(lado == Lado.direito){
            getPathForca().lineTo(toPixel(posicaoDoCorpo + 1), toPixel(alturaFinal));
        }else{
            getPathForca().lineTo(toPixel(posicaoDoCorpo - 1), toPixel(alturaFinal));
        }

    }

    public Paint getPaintForca() {
        paintForca = new Paint();
        paintForca.setColor(Color.BLACK);
        paintForca.setStyle(Paint.Style.STROKE);
        paintForca.setStrokeWidth(12);
        return paintForca;
    }

    private Paint getPaintTraco(){
        Paint paintTraco = new Paint();
        paintTraco.setColor(Color.BLACK);
        paintTraco.setStyle(Paint.Style.FILL);
        paintTraco.setStrokeWidth(2);
        paintTraco.setTextSize(100);
        return paintTraco;
    }

//    private void plotaTracos(){
//        Log.i("ForcaView", "ForcaView plotaTracos");
//        int eixoX = toPixel(3);
//        getPathForca().moveTo(eixoX + 10, toPixel(10));
//        eixoX += 35; // alinhamento
//        if(getForcaController() != null){
//            for(int i=0; i<=getForcaController().getPalavraAteAgora().length()-1; i++){
//                getPathForca().rMoveTo(10, 0);
//                getPathForca().rLineTo(toPixel(1), 0);
//            }
//        }
//        getPaintTraco().setStyle(Paint.Style.STROKE);
//    }
    private void drawLetrasCorretas(Canvas canvas){
        int eixoX = toPixel(1);
        getPathForca().moveTo(eixoX + 10, toPixel(10));
        eixoX += 35; // alinhamento
        if(getForcaController() != null){
            for(int i=0; i<=getForcaController().getPalavraAteAgora().length()-1; i++){
                char c =getForcaController().getPalavraAteAgora().charAt(i);
                canvas.drawText(c+"",
                        eixoX + ((toPixel(1) + 10) * i), toPixel(10) - 15, getPaintTraco());
            }
        }
  }

    public Path getPathForca() {
        return pathForca;
    }
    public void setPathForca(Path pathForca) {
        this.pathForca = pathForca;
    }
    public ForcaController getForcaController() {
        return forcaController;
    }
    public void setForcaController(ForcaController forcaController) {
        getPathForca().rewind();
        this.forcaController = forcaController;
    }
}
