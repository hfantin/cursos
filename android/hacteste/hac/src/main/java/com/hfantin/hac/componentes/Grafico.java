package com.hfantin.hac.componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.hfantin.hac.hgfandroidcomponentes.R;
import com.hfantin.hac.utils.Utils;

/**
 * Created by f3900699 on 08/06/17.
 */

public class Grafico extends View {

    private static final String TAG = Grafico.class.getSimpleName();

    // cores
    private static final int COR_ETIQUETA_REFERENCIA = Color.parseColor("#cc81849c");
    private static final int COR_ETIQUETA_TOTAL = Color.parseColor("#80d20606");
    private static final int MINIMO_BARRA = Utils.dpToPx(3);
    private static final int ESPACO_ENTRE_ETIQUETAS = Utils.dpToPx(10);
    private static final int PADDING_HORIZONTAL_TEXTO_ETIQUETAS = Utils.dpToPx(10);
    private static final int PADDING_VERTICAL_TEXTO_ETIQUETAS = Utils.dpToPx(5);
    private static final int PADDING_ESQUERDA_CAIXA_TEXTO = Utils.dpToPx(5);
    // paints
    private Paint paintPonteiro;
    private Paint paintTexto;
    private Paint paintCaixaTexto;
    // atributos
    private boolean exibirBarraMinima;
    private boolean inverterEtiquetas;
    private int percentualAlerta;
    private String textoValorReferencia;
    private String textoValorTotal;
    private Float alturaBarras;
    private Float tamanhoTextoValores;
    private Float etiquetaPadding;
    private boolean ajustarTamanhoIcone;
    private Bitmap iconePonteiro;
    private Drawable iconePonteiroDrawable;
    // shapes
    private Drawable okBackground;
    private Drawable alertaBackground;
    private Drawable cuidadoBackground;
    // outros
    private double posicaoPonteiro;
    private final int centroImagem;
    private double percentualOk;
    private int posicaoDireitaOk;
    private int larguraOk;
    private int posicaoBasePadraoBarraPx;
    private int posicaoTopoPadraoBarraPx;
    // valores
    private Double valorSugerido;
    private Double valorOk;
    private Double valorTotal;
    private Double valorAtual;

    public Grafico(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray atributos = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Grafico, 0, 0);
        try {
            exibirBarraMinima = atributos.getBoolean(R.styleable.Grafico_exibir_barra_minima, false);
            inverterEtiquetas = atributos.getBoolean(R.styleable.Grafico_inverter_etiquetas, true);
            percentualAlerta = atributos.getInteger(R.styleable.Grafico_percentual_alerta, 0);
            textoValorReferencia = atributos.getString(R.styleable.Grafico_texto_valor_referencia);
            textoValorTotal = atributos.getString(R.styleable.Grafico_texto_valor_total);
            alturaBarras = atributos.getDimension(R.styleable.Grafico_altura_barras, 22);
            tamanhoTextoValores = atributos.getDimension(R.styleable.Grafico_tamanho_texto_valores, 9);
            etiquetaPadding = atributos.getDimension(R.styleable.Grafico_etiqueta_padding, 2);
            ajustarTamanhoIcone = atributos.getBoolean(R.styleable.Grafico_ajustar_tamanho_icone, false);
            iconePonteiroDrawable = atributos.getDrawable(R.styleable.Grafico_icone_ponteiro);
            okBackground = atributos.getDrawable(R.styleable.Grafico_ok_background);
            alertaBackground = atributos.getDrawable(R.styleable.Grafico_alerta_background);
            cuidadoBackground = atributos.getDrawable(R.styleable.Grafico_cuidado_background);
        } finally {
            atributos.recycle();
        }
        // valores default
        if (textoValorReferencia == null) {
            textoValorReferencia = "";
        }
        if (textoValorTotal == null) {
            textoValorTotal = "";
        }
        // obtem ponteiro
        this.posicaoPonteiro = 0;
        if (iconePonteiroDrawable != null && iconePonteiroDrawable instanceof BitmapDrawable) {
            this.iconePonteiro = ((BitmapDrawable) iconePonteiroDrawable).getBitmap();
            if(ajustarTamanhoIcone){
                this.iconePonteiro = Bitmap.createScaledBitmap(this.iconePonteiro, alturaBarras.intValue(), alturaBarras.intValue(), true);
            }
        }else{
            this.iconePonteiro = BitmapFactory.decodeResource(getResources(), R.drawable.triangle);
        }
        this.centroImagem = iconePonteiro.getWidth() / 2;
        this.paintPonteiro = new Paint();
        int descontoAlturaPonteiro = iconePonteiro.getHeight() / 2;
        this.posicaoTopoPadraoBarraPx = descontoAlturaPonteiro;
        this.posicaoBasePadraoBarraPx = posicaoTopoPadraoBarraPx + alturaBarras.intValue();
        // backgrounds
        if(okBackground == null){
            okBackground = ContextCompat.getDrawable(context, R.drawable.verde_gradiente);
        }
        if(alertaBackground == null){
            alertaBackground = ContextCompat.getDrawable(context, R.drawable.amarelo_gradiente);
        }
        if(cuidadoBackground == null){
            cuidadoBackground = ContextCompat.getDrawable(context, R.drawable.vermelho_gradiente);
        }
        // valores
        valorOk = 0.0;
        valorAtual = 0.0;
        valorSugerido = 0.0;
        valorTotal = 0.0;
        // inicia paints
        paintTexto = new Paint();
        paintCaixaTexto = new Paint();
        paintTexto.setAntiAlias(true);
        paintTexto.setStrokeWidth(1);
        paintTexto.setColor(Color.WHITE);
        paintTexto.setTextSize(tamanhoTextoValores.intValue());
        paintCaixaTexto.setAntiAlias(true);
        paintCaixaTexto.setStrokeWidth(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 100;
        int desiredHeight = 100;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }
        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }
        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int largura, int altura, int larguraAntigo, int alturaAntigo) {
        super.onSizeChanged(largura, altura, larguraAntigo, alturaAntigo);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        super.onDraw(canvas);
        calcularPercentualOk();
        calcularPosicaoFinalOk(canvas);
        calcularMinimoBarra(canvas);
        desenharBarraOk(canvas);
        desenharBarraAlerta(canvas);
        desenharBarraCuidado(canvas);
        desenharPonteiro(canvas);
        desenharEtiquetas(canvas);
    }

    private void calcularPercentualOk() {
        percentualOk = 0d;
        if (valorSugerido != null && valorTotal != null && valorAtual != null) {
            double divisor = valorTotal > valorAtual ? valorTotal : valorAtual;
            percentualOk = valorOk / divisor;
        }
    }

    private void calcularPosicaoFinalOk(Canvas canvas) {
        larguraOk = (int) Math.round(percentualOk * canvas.getWidth());
        if (larguraOk >= canvas.getWidth()) {
            larguraOk = canvas.getWidth();
        }
    }

    private void calcularMinimoBarra(Canvas canvas) {
        if (exibirBarraMinima) {
            if (larguraOk <= MINIMO_BARRA) {
                larguraOk = MINIMO_BARRA;
            } else if (larguraOk >= canvas.getWidth() - MINIMO_BARRA) {
                larguraOk -= MINIMO_BARRA;
            }
        }
    }

    private void desenharPonteiro(Canvas canvas) {
        posicaoPonteiro = valorAtual / valorTotal;
        posicaoPonteiro *= canvas.getWidth();
        if (posicaoPonteiro >= canvas.getWidth()) {
            posicaoPonteiro = canvas.getWidth();
        }
        posicaoPonteiro -= centroImagem;
        canvas.drawBitmap(iconePonteiro, (float) posicaoPonteiro, 0, paintPonteiro);
    }

    private void desenharBarraOk(Canvas canvas) {
        int esquerda = 0;
        int numerador = 100 - percentualAlerta;
        double divisor = (double) numerador / 100;
        posicaoDireitaOk = (int) (larguraOk * divisor);
        okBackground.setBounds(esquerda, posicaoTopoPadraoBarraPx, posicaoDireitaOk, posicaoBasePadraoBarraPx);
        okBackground.draw(canvas);
    }

    private void desenharBarraAlerta(Canvas canvas) {
        alertaBackground.setBounds(posicaoDireitaOk, posicaoTopoPadraoBarraPx, larguraOk, posicaoBasePadraoBarraPx);
        alertaBackground.draw(canvas);
    }

    private void desenharBarraCuidado(Canvas canvas) {
        cuidadoBackground.setBounds(larguraOk < 0 ? 0 : larguraOk, posicaoTopoPadraoBarraPx, canvas.getWidth(), posicaoBasePadraoBarraPx);
        cuidadoBackground.draw(canvas);
    }

    private void desenharEtiquetas(Canvas canvas) {
        // calcula valores da etiqueta de referencia;
        String textoEtiquetaReferencia = textoValorReferencia;
        Rect caixaTextoReferencia = obterCaixaTexto(textoEtiquetaReferencia, paintTexto);
        float larguraCaixaReferencia = caixaTextoReferencia.width() + PADDING_HORIZONTAL_TEXTO_ETIQUETAS;
        float alturaCaixaReferencia = caixaTextoReferencia.height() + PADDING_VERTICAL_TEXTO_ETIQUETAS;
        double valorTotalOuAtual = valorTotal > valorAtual ? valorTotal : valorAtual;
        float posicaoDireitaEtiquetaReferencia = (float) (valorSugerido / valorTotalOuAtual);
        posicaoDireitaEtiquetaReferencia *= canvas.getWidth();
        if (posicaoDireitaEtiquetaReferencia >= canvas.getWidth()) {
            posicaoDireitaEtiquetaReferencia = canvas.getWidth();
        }
        float posicaoTopoEtiquetaReferencia = posicaoBasePadraoBarraPx + ESPACO_ENTRE_ETIQUETAS;
        // calcula valores da etiqueta de total;
        String textoEtiquetaTotal = textoValorTotal; // + Utils.price(valorTotal);
        Rect caixaTextoTotal = obterCaixaTexto(textoEtiquetaTotal, paintTexto);
        float larguraCaixaTotal = caixaTextoTotal.width() + PADDING_HORIZONTAL_TEXTO_ETIQUETAS;
        float alturaCaixaTotal = caixaTextoTotal.height() + PADDING_VERTICAL_TEXTO_ETIQUETAS;
        double percentualDireitaCuidado = valorAtual> valorTotal ? valorTotal / valorAtual : 1.0;
        float posicaoDireitaEtiquetaTotal = percentualAlerta == 0 ? (int)(percentualDireitaCuidado * (canvas.getWidth() - 1)) : larguraOk * 0.97f;
        float posicaoTopoEtiquetaTotal = posicaoTopoEtiquetaReferencia + ESPACO_ENTRE_ETIQUETAS + alturaCaixaTotal;
        // desenha etiquetas
        if(inverterEtiquetas) {
            // obtem posicoes da etiqueta de referencia
            Rect retanguloReferencia = obterRetangulo(canvas, posicaoDireitaEtiquetaReferencia, posicaoTopoEtiquetaReferencia, larguraCaixaReferencia, alturaCaixaReferencia, false);
            int posicaoLinhaReferencia = (int) posicaoDireitaEtiquetaReferencia;
            // obtem posicoes da etiqueta de total
            // verifica se houve inversao de lado do primeiro retangulo
            boolean inverterCaixaTotal = posicaoDireitaEtiquetaReferencia - larguraCaixaReferencia < 0 && posicaoDireitaEtiquetaTotal + larguraCaixaTotal < canvas.getWidth() - 1;
            Rect retanguloTotal = obterRetangulo(canvas, posicaoDireitaEtiquetaTotal, posicaoTopoEtiquetaTotal, larguraCaixaTotal, alturaCaixaTotal, inverterCaixaTotal);
            int posicaoLinhaTotal = (int) posicaoDireitaEtiquetaTotal;
            // Altera topo - verifica se alinha de total passa no meio da caixa de referencia
            if (posicaoLinhaTotal < retanguloReferencia.right && posicaoLinhaTotal > retanguloReferencia.left) {
                int tmpTop = retanguloTotal.top;
                int tmpBottom = retanguloTotal.bottom;
                retanguloTotal.top = retanguloReferencia.top;
                retanguloTotal.bottom = retanguloReferencia.bottom;
                retanguloReferencia.top = tmpTop;
                retanguloReferencia.bottom = tmpBottom;
            }
            // desenha etiqueta de referencia
            paintCaixaTexto.setColor(COR_ETIQUETA_REFERENCIA);
            canvas.drawRect(retanguloReferencia, paintCaixaTexto);
            int topotextoReferencia = (int) (retanguloReferencia.top + (alturaCaixaReferencia / 5) * 4);
            canvas.drawText(textoEtiquetaReferencia, retanguloReferencia.left + PADDING_ESQUERDA_CAIXA_TEXTO, topotextoReferencia, paintTexto);
            canvas.drawLine(posicaoLinhaReferencia, retanguloReferencia.top, posicaoLinhaReferencia, posicaoBasePadraoBarraPx, paintCaixaTexto);
            // desenha etiqueta de total
            paintCaixaTexto.setColor(COR_ETIQUETA_TOTAL);
            canvas.drawRect(retanguloTotal, paintCaixaTexto);
            int topotextoTotal = (int) (retanguloTotal.top + (alturaCaixaTotal / 5) * 4);
            canvas.drawText(textoEtiquetaTotal, retanguloTotal.left + PADDING_ESQUERDA_CAIXA_TEXTO, topotextoTotal, paintTexto);
            canvas.drawLine(posicaoLinhaTotal, retanguloTotal.top, posicaoLinhaTotal, posicaoBasePadraoBarraPx, paintCaixaTexto);
        }else{
            desenharRetanguloComTexto(canvas, posicaoDireitaEtiquetaReferencia, posicaoTopoEtiquetaReferencia, larguraCaixaReferencia, alturaCaixaReferencia, COR_ETIQUETA_REFERENCIA, textoEtiquetaReferencia);
            desenharRetanguloComTexto(canvas, posicaoDireitaEtiquetaTotal, posicaoTopoEtiquetaTotal, larguraCaixaTotal, alturaCaixaTotal, COR_ETIQUETA_TOTAL, textoEtiquetaTotal);
        }
    }

    private Rect obterRetangulo(Canvas canvas, float posicaoDireita, float posicaoTopo, float larguraCaixa, float alturaCaixa, boolean inverterLado) {
        int esquerda,
            direita,
            topo = (int) (posicaoTopo - etiquetaPadding),
            base = (int) (posicaoTopo + alturaCaixa + etiquetaPadding);
        float posicaoEsquerda = posicaoDireita - larguraCaixa;
        if (posicaoEsquerda < 0 || inverterLado) {
            esquerda = (int) (posicaoDireita - etiquetaPadding);
            direita = (int) (posicaoDireita + larguraCaixa + etiquetaPadding);
        } else { // inverte lado da caixa
            esquerda = (int) (posicaoEsquerda- etiquetaPadding);
            direita = (int) (posicaoDireita + etiquetaPadding);
        }
        if (direita > canvas.getWidth()) {
            direita = canvas.getWidth();
            esquerda = (int) (direita - larguraCaixa);
        }
        return  new Rect(esquerda, topo, direita, base);
    }

    @Deprecated
    private void desenharRetanguloComTexto(Canvas canvas, float posicaoDireita, float posicaoTopo, float larguraCaixa, float alturaCaixa, int cor, String texto) {
        paintCaixaTexto.setColor(cor);
        int esquerda,
            direita,
            topo = (int) (posicaoTopo - etiquetaPadding),
            base = (int) (posicaoTopo + alturaCaixa + etiquetaPadding);
        int textoEsquerda = (int) posicaoDireita,
                textoTopo = (int) (posicaoTopo + (alturaCaixa / 5) * 4),
                posicaoLinha = (int) posicaoDireita;
        if ((int) (posicaoDireita - larguraCaixa) > 0) {
            esquerda = (int) (posicaoDireita - larguraCaixa - etiquetaPadding);
            direita = (int) (posicaoDireita + etiquetaPadding);
            textoEsquerda -= larguraCaixa;
        } else {
            esquerda = (int) (posicaoDireita - etiquetaPadding);
            direita = (int) (posicaoDireita + larguraCaixa + etiquetaPadding);
        }
        if (direita > canvas.getWidth()) {
            direita = canvas.getWidth();
            esquerda = (int) (direita - larguraCaixa);
            textoEsquerda = esquerda;
            if (posicaoLinha < canvas.getWidth() - 1) {
                posicaoLinha = esquerda;
            }
        }
//        caixa de texto arredondada
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
//            canvas.drawRoundRect(esquerda, topo, direita, base, RAIO_CAIXA_TEXTO, RAIO_CAIXA_TEXTO, paintCaixaTexto);
//        }
        canvas.drawRect(esquerda, topo, direita, base, paintCaixaTexto);
        canvas.drawText(texto, textoEsquerda + PADDING_ESQUERDA_CAIXA_TEXTO, textoTopo, paintTexto);
        canvas.drawLine(posicaoLinha, posicaoTopo, posicaoLinha, posicaoBasePadraoBarraPx, paintCaixaTexto);
    }

    private Rect obterCaixaTexto(String texto, Paint paint) {
        Rect caixa = new Rect();
        paint.getTextBounds(texto, 0, texto.length(), caixa);
        paint.measureText(texto);
        return caixa;
    }

    public Double getValorSugerido() {
        return valorSugerido;
    }

    public void setValorSugerido(Double valorSugerido) {
        this.valorSugerido = valorSugerido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getPercentualAlerta() {
        return percentualAlerta;
    }

    public void setPercentualAlerta(int percentualAlerta) {
        this.percentualAlerta = percentualAlerta;
    }

    public String getTextoValorReferencia() {
        return textoValorReferencia;
    }

    public void setTextoValorReferencia(String textoValorReferencia) {
        this.textoValorReferencia = textoValorReferencia;
    }

    public String getTextoValorTotal() {
        return textoValorTotal;
    }

    public void setTextoValorTotal(String textoValorTotal) {
        this.textoValorTotal = textoValorTotal;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Double getValorOk() {
        return valorOk;
    }

    public void setValorOk(Double valorOk) {
        this.valorOk = valorOk;
    }
}