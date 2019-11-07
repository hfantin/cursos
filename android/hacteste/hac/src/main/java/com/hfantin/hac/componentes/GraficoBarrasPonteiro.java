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
import android.util.Log;
import android.view.View;

import com.hfantin.hac.hgfandroidcomponentes.R;
import com.hfantin.hac.utils.Utils;

/**
 * Created by f3900699 on 08/06/17.
 */

public class GraficoBarrasPonteiro extends View {
    private static final String TAG = GraficoBarrasPonteiro.class.getSimpleName();
    private static final int COR_ETIQUETA_RECEITAS = Color.parseColor("#cc869c81");
    private static final int COR_ETIQUETA_REFERENCIA = Color.parseColor("#cc81849c");
    private static final int COR_ETIQUETA_TOTAL = Color.parseColor("#80d20606");
    private static final int MINIMO_BARRA = Utils.dpToPx(3);
    private static final int ESPACO_ENTRE_ETIQUETAS = Utils.dpToPx(10);
    private static final int PADDING_HORIZONTAL_TEXTO_ETIQUETAS = Utils.dpToPx(10);
    private static final int PADDING_VERTICAL_TEXTO_ETIQUETAS = Utils.dpToPx(5);
    private static final int PADDING_ESQUERDA_CAIXA_TEXTO = Utils.dpToPx(5);
    private static final String ESPACO = " ";
    private final int centroImagem;
    private final int topoBarraProgresso;
    // paints
    private Paint paintPonteiro;
    private Paint paintTexto;
    private Paint paintCaixaTexto;
    // atributos
    private Bitmap iconePonteiro;
    private boolean ajustarTamanhoIcone;
    private boolean exibirBarraMinima;
    private Drawable iconePonteiroDrawable;
    private Float alturaBarras;
    private Float etiquetaPadding;
    private Float tamanhoTextoValores;
    private int percentualAlerta;
    private String textoValorReceitas;
    private String textoValorReferencia;
    private String textoValorTotal;
    // shapes
    private Drawable okBackground;
    private Drawable alertaBackground;
    private Drawable cuidadoBackground;
    private int etiquetaReceitasBackground;
    private int etiquetaReferenciaBackground;
    private int etiquetaTotalBackground;

    // outros
    private final boolean desenharPonteiro;
    private final boolean desenharEtiquetaReferencia;
    private double posicaoPonteiro;
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

    public GraficoBarrasPonteiro(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray atributos = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Grafico, 0, 0);
        try {
            ajustarTamanhoIcone = atributos.getBoolean(R.styleable.Grafico_ajustar_tamanho_icone, false);
            alertaBackground = atributos.getDrawable(R.styleable.Grafico_alerta_background);
            alturaBarras = atributos.getDimension(R.styleable.Grafico_altura_barras, 22);
            cuidadoBackground = atributos.getDrawable(R.styleable.Grafico_cuidado_background);
            etiquetaReceitasBackground = atributos.getColor(R.styleable.Grafico_etiqueta_receitas_background, COR_ETIQUETA_RECEITAS);
            etiquetaReferenciaBackground = atributos.getColor(R.styleable.Grafico_etiqueta_referencia_background,  COR_ETIQUETA_REFERENCIA);
            etiquetaTotalBackground = atributos.getColor(R.styleable.Grafico_etiqueta_total_background, COR_ETIQUETA_TOTAL);
            desenharPonteiro = atributos.getBoolean(R.styleable.Grafico_desenhar_ponteiro, true);
            desenharEtiquetaReferencia = atributos.getBoolean(R.styleable.Grafico_desenhar_etiqueta_referencia, true);
            etiquetaPadding = atributos.getDimension(R.styleable.Grafico_etiqueta_padding, 2);
            exibirBarraMinima = atributos.getBoolean(R.styleable.Grafico_exibir_barra_minima, false);
            iconePonteiroDrawable = atributos.getDrawable(R.styleable.Grafico_icone_ponteiro);
            okBackground = atributos.getDrawable(R.styleable.Grafico_ok_background);
            percentualAlerta = atributos.getInteger(R.styleable.Grafico_percentual_alerta, 0);
            tamanhoTextoValores = atributos.getDimension(R.styleable.Grafico_tamanho_texto_valores, 9);
            textoValorReceitas = atributos.getString(R.styleable.Grafico_texto_valor_receitas) + ESPACO;
            textoValorReferencia = atributos.getString(R.styleable.Grafico_texto_valor_referencia) + ESPACO;
            textoValorTotal = atributos.getString(R.styleable.Grafico_texto_valor_total) + ESPACO;
        } finally {
            atributos.recycle();
        }
        // valores default
        if (textoValorReceitas == null) {
            textoValorReceitas = "";
        }
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
            if (ajustarTamanhoIcone) {
                this.iconePonteiro = Bitmap.createScaledBitmap(this.iconePonteiro, alturaBarras.intValue(), alturaBarras.intValue(), true);
            }
        } else {
            this.iconePonteiro = BitmapFactory.decodeResource(getResources(), R.drawable.triangle);
        }
        this.centroImagem = iconePonteiro.getWidth() / 2;
        this.paintPonteiro = new Paint();
        topoBarraProgresso = Utils.dpToPx(20);

        int descontoAlturaPonteiro = topoBarraProgresso + iconePonteiro.getHeight() / 2;
        this.posicaoTopoPadraoBarraPx = descontoAlturaPonteiro;
        this.posicaoBasePadraoBarraPx = posicaoTopoPadraoBarraPx + alturaBarras.intValue();
        // backgrounds
        if (okBackground == null) {
            okBackground = ContextCompat.getDrawable(context, R.drawable.verde_gradiente);
        }
        if (alertaBackground == null) {
            alertaBackground = ContextCompat.getDrawable(context, R.drawable.amarelo_gradiente);
        }
        if (cuidadoBackground == null) {
            cuidadoBackground = ContextCompat.getDrawable(context, R.drawable.vermelho_gradiente);
        }
        // valores
        valorOk = 0.0;
        valorAtual = 0.0;
        valorSugerido = 0.0;
        valorTotal = 0.0;
        // inicia paints
        paintTexto = new Paint();
        paintTexto.setAntiAlias(true);
        paintTexto.setStrokeWidth(1);
        paintTexto.setColor(Color.WHITE);
        paintTexto.setTextSize(tamanhoTextoValores.intValue());

        paintCaixaTexto = new Paint();
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
        Log.i(TAG, "onDraw");
        canvas.save();
        super.onDraw(canvas);
        if (valorSugerido == 0) {
            percentualAlerta = 1;
        }
        calcularPercentualOk();
        calcularPosicaoFinalOk(canvas);
        calcularMinimoBarra(canvas);

        desenharBarraOk(canvas);
        desenharBarraAlerta(canvas);
        desenharBarraCuidado(canvas);

        if(desenharPonteiro){
            desenharPonteiro(canvas);
        }
        desenharEtiquetas(canvas);
        desenharEtiquetaReceitas(canvas);

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

    private void desenharPonteiro(Canvas canvas) {
        posicaoPonteiro = valorAtual / valorTotal;
        posicaoPonteiro *= canvas.getWidth();
        if (posicaoPonteiro >= canvas.getWidth()) {
            posicaoPonteiro = canvas.getWidth();
        }
        posicaoPonteiro -= centroImagem;
        canvas.drawBitmap(iconePonteiro, (float) posicaoPonteiro, topoBarraProgresso, paintPonteiro);
    }

    private void desenharEtiquetas(Canvas canvas) {
        // calcula valores da etiqueta de referencia;
        Rect caixaTextoReferencia = obterCaixaTexto(textoValorReferencia, paintTexto);
        float larguraCaixaReferencia = caixaTextoReferencia.width() + PADDING_HORIZONTAL_TEXTO_ETIQUETAS;
        float alturaCaixaReferencia = caixaTextoReferencia.height() + PADDING_VERTICAL_TEXTO_ETIQUETAS;
        // calcula valores da etiqueta de total;
        Rect caixaTextoTotal = obterCaixaTexto(textoValorTotal, paintTexto);
        float larguraCaixaTotal = caixaTextoTotal.width() + PADDING_HORIZONTAL_TEXTO_ETIQUETAS;
        float alturaCaixaTotal = caixaTextoTotal.height() + PADDING_VERTICAL_TEXTO_ETIQUETAS;

        double valorTotalOuAtual = valorTotal > valorAtual ? valorTotal : valorAtual;
        float posicaoDireitaEtiquetaReferencia = (float) (valorSugerido / valorTotalOuAtual);
        posicaoDireitaEtiquetaReferencia *= canvas.getWidth();
        if (posicaoDireitaEtiquetaReferencia >= canvas.getWidth()) {
            posicaoDireitaEtiquetaReferencia = canvas.getWidth();
        }
        float posicaoTopoEtiquetaReferencia = posicaoBasePadraoBarraPx + ESPACO_ENTRE_ETIQUETAS;

        double percentualDireitaCuidado = valorAtual > valorTotal ? valorTotal / valorAtual : 1.0;
        float posicaoDireitaEtiquetaTotal = percentualAlerta == 0 ? (int) (percentualDireitaCuidado * (canvas.getWidth() - 1)) : larguraOk * 0.97f;
        float posicaoTopoEtiquetaTotal = posicaoTopoEtiquetaReferencia + ESPACO_ENTRE_ETIQUETAS + alturaCaixaTotal;
        if(!desenharEtiquetaReferencia){
            posicaoTopoEtiquetaTotal =  posicaoTopoEtiquetaReferencia;
        }
        // desenha etiquetas
        if (valorSugerido == 0) {
            posicaoDireitaEtiquetaTotal = (int) (percentualDireitaCuidado * (canvas.getWidth() - 1));
            desenharEtiquetaTotal(canvas, textoValorTotal, larguraCaixaTotal, alturaCaixaTotal, posicaoDireitaEtiquetaTotal, posicaoTopoEtiquetaReferencia);
        } else {
            desenharEtiquetasReferenciaETotal(canvas, textoValorReferencia, larguraCaixaReferencia, alturaCaixaReferencia, posicaoDireitaEtiquetaReferencia, posicaoTopoEtiquetaReferencia, textoValorTotal, larguraCaixaTotal, alturaCaixaTotal, posicaoDireitaEtiquetaTotal, posicaoTopoEtiquetaTotal);
        }
    }

    private void desenharEtiquetaTotal(Canvas canvas, String textoEtiquetaTotal, float larguraCaixaTotal, float alturaCaixaTotal, float posicaoDireitaEtiquetaTotal, float posicaoTopoEtiquetaTotal) {
        // obtem posicoes da etiqueta de total
//        // verifica se houve inversao de lado do primeiro retangulo
//        boolean inverterCaixaTotal = posicaoDireitaEtiquetaTotal + larguraCaixaTotal < canvas.getWidth() - 1;
        Rect retanguloTotal = obterRetangulo(canvas, posicaoDireitaEtiquetaTotal, posicaoTopoEtiquetaTotal, larguraCaixaTotal, alturaCaixaTotal, false);
        int posicaoLinhaTotal = (int) posicaoDireitaEtiquetaTotal;
        // desenha etiqueta de total
        paintCaixaTexto.setColor(etiquetaTotalBackground);
        int topotextoTotal = (int) (retanguloTotal.top + (alturaCaixaTotal / 5) * 4);
        canvas.drawRect(retanguloTotal, paintCaixaTexto);
        canvas.drawText(textoEtiquetaTotal, retanguloTotal.left + PADDING_ESQUERDA_CAIXA_TEXTO, topotextoTotal, paintTexto);
        canvas.drawLine(posicaoLinhaTotal, retanguloTotal.top, posicaoLinhaTotal, posicaoBasePadraoBarraPx, paintCaixaTexto);
    }

    private void desenharEtiquetasReferenciaETotal(Canvas canvas, String textoEtiquetaReferencia, float larguraCaixaReferencia, float alturaCaixaReferencia, float posicaoDireitaEtiquetaReferencia, float posicaoTopoEtiquetaReferencia, String textoEtiquetaTotal, float larguraCaixaTotal, float alturaCaixaTotal, float posicaoDireitaEtiquetaTotal, float posicaoTopoEtiquetaTotal) {
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
        if(desenharEtiquetaReferencia) {
            paintCaixaTexto.setColor(etiquetaReferenciaBackground);
            int topotextoReferencia = (int) (retanguloReferencia.top + (alturaCaixaReferencia / 5) * 4);
            canvas.drawRect(retanguloReferencia, paintCaixaTexto);
            canvas.drawText(textoEtiquetaReferencia, retanguloReferencia.left + PADDING_ESQUERDA_CAIXA_TEXTO, topotextoReferencia, paintTexto);
            canvas.drawLine(posicaoLinhaReferencia, retanguloReferencia.top, posicaoLinhaReferencia, posicaoBasePadraoBarraPx, paintCaixaTexto);
        }
        // desenha etiqueta de total
        paintCaixaTexto.setColor(etiquetaTotalBackground);
        int topotextoTotal = (int) (retanguloTotal.top + (alturaCaixaTotal / 5) * 4);
        canvas.drawRect(retanguloTotal, paintCaixaTexto);
        canvas.drawText(textoEtiquetaTotal, retanguloTotal.left + PADDING_ESQUERDA_CAIXA_TEXTO, topotextoTotal, paintTexto);
        canvas.drawLine(posicaoLinhaTotal, retanguloTotal.top, posicaoLinhaTotal, posicaoBasePadraoBarraPx, paintCaixaTexto);
    }

    private void desenharEtiquetaReceitas(Canvas canvas) {
        String texto = textoValorReceitas + Utils.price(getValorOk());
        Rect caixaTexto = obterCaixaTexto(texto, paintTexto);
        Rect retangulo = obterRetangulo(canvas, larguraOk, 0, caixaTexto, false);
        // desenha etiqueta de receitas
        paintCaixaTexto.setColor(etiquetaReceitasBackground);
        int topotextoTotal = retangulo.top + (retangulo.height() / 5) * 4;
        canvas.drawRect(retangulo, paintCaixaTexto);
        canvas.drawText(texto, retangulo.left + PADDING_ESQUERDA_CAIXA_TEXTO, topotextoTotal, paintTexto);
        canvas.drawLine(larguraOk, 0, larguraOk, posicaoTopoPadraoBarraPx, paintCaixaTexto);
    }

    private Rect obterRetangulo(Canvas canvas, float direita, float topo, Rect caixaTexto, boolean inverterLado) {
        float largura = caixaTexto.width() + PADDING_HORIZONTAL_TEXTO_ETIQUETAS;
        float altura = caixaTexto.height() + PADDING_VERTICAL_TEXTO_ETIQUETAS;
        return obterRetangulo(canvas, direita, topo, largura, altura, inverterLado);
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
            esquerda = (int) (posicaoEsquerda - etiquetaPadding);
            direita = (int) (posicaoDireita + etiquetaPadding);
        }
        if (direita > canvas.getWidth()) {
            direita = canvas.getWidth();
            esquerda = (int) (direita - larguraCaixa);
        }
        return new Rect(esquerda, topo, direita, base);
    }

    private Rect obterCaixaTexto(String texto, Paint paint) {
        Rect caixa = new Rect();
        paint.getTextBounds(texto, 0, texto.length(), caixa);
        paint.measureText(texto);
        return caixa;
    }

    //Getters e setters
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

    public String getTextoValorReceitas() {
        return textoValorReceitas;
    }

    public void setTextoValorReceitas(String textoValorReceitas) {
        this.textoValorReceitas = textoValorReceitas;
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