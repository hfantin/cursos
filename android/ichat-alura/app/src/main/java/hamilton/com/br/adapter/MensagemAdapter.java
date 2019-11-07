package hamilton.com.br.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hamilton.com.br.ichat_alura.R;
import hamilton.com.br.model.Mensagem;

/**
 * Created by hamilton on 03/02/17.
 */

public class MensagemAdapter extends BaseAdapter {

    private Integer idDoCliente;
    private List<Mensagem> mensagens;
    private Activity activity;
    @BindView(R.id.tv_texto)
    TextView texto;
    @BindView(R.id.iv_avatar_mensagem)
    ImageView avatar;

    @Inject
    public Picasso picasso;

    public MensagemAdapter(Integer idDoClient, List<Mensagem> mensagens, Activity activity) {
        this.idDoCliente = idDoClient;
        this.mensagens = mensagens;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int i) {
        return mensagens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem, viewGroup, false);
//        texto = (TextView) linha.findViewById(R.id.tv_texto);
        ButterKnife.bind(this, linha);

        Mensagem mensagem = getItem(i);
        texto.setText(mensagem.getText());

        picasso.with(activity)
                .load("https://api.adorable.io/avatars/285/" + mensagem.getId() + ".png")
                .into(avatar);

        if(idDoCliente!= null && !idDoCliente.equals(mensagem.getId())){
            linha.setBackgroundColor(Color.LTGRAY);
        }

        return linha;
    }
}
