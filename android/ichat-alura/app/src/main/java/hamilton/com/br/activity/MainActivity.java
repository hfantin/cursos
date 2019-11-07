package hamilton.com.br.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hamilton.com.br.adapter.MensagemAdapter;
import hamilton.com.br.app.ChatApplication;
import hamilton.com.br.callback.EnviarMensagemCallback;
import hamilton.com.br.callback.OuvirMensagemCallback;
import hamilton.com.br.component.ChatComponent;
import hamilton.com.br.event.FailureEvent;
import hamilton.com.br.event.MensagemEvent;
import hamilton.com.br.ichat_alura.R;
import hamilton.com.br.model.Mensagem;
import hamilton.com.br.service.ChatService;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;

    @BindView(R.id.btn_enviar)
    Button button;

    @BindView(R.id.et_texto)
    EditText editText;

    @BindView(R.id.lv_mensagens)
    ListView listaDeMensagens;

    @BindView(R.id.iv_avatar_usuario)
    ImageView avatar;

    private List<Mensagem> mensagens;

    private MensagemAdapter adapter;

    @Inject
    public ChatService chatService;

    private ChatComponent component;

    @Inject
    InputMethodManager inputMethodManager;

    @Inject
    EventBus eventBus;

    @Inject
    public Picasso picasso;

//
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Mensagem mensagem = (Mensagem) intent.getSerializableExtra("mensagem");
//            colocaNaLista(mensagem);
//        }
//    };

//    private static final String NOTEBOOK_HAMILTON = "http://192.168.25.14:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        listaDeMensagens = (ListView) findViewById(R.id.lv_mensagens);
//        button = (Button) findViewById(R.id.btn_enviar);
//        editText = (EditText) findViewById(R.id.et_texto);
        // recuperando as mensagens, caso haja alguma
        if (savedInstanceState != null) {
            mensagens = (List<Mensagem>) savedInstanceState.getSerializable("mensagens");
        } else {
            mensagens = new ArrayList<>();
        }
        adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        picasso.with(this)
                .load("https://api.adorable.io/avatars/285/" + idDoCliente + ".png")
                .into(avatar);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

       /*
       // Movido para o modulo
       Retrofit retrofit = new Retrofit.Builder()
                // Altere para o seu IP
                .baseUrl(NOTEBOOK_HAMILTON)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        chatService = retrofit.create(ChatService.class);

       */

        ouvirMensagem(null);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // enviar mensagem
//                chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString())).enqueue(new EnviarMensagemCallback());
//                editText.getText().clear();
//            }
//        });

        // registra local broadcast
//        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
//        localBroadcastManager.registerReceiver(receiver, new IntentFilter("nova_mensagem"));

        // vamos começar a ouvir o evento, por isso passamos o this
        eventBus.register(this);

    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem(){
        chatService.enviar(new Mensagem(idDoCliente, editText.getText().toString())).enqueue(new EnviarMensagemCallback());
        editText.getText().clear();
        // esconder teclado
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    @Subscribe
    public void colocaNaLista(MensagemEvent mensagemEvent) {
        Log.i(this.getClass().getSimpleName(), "Adicionando mensagem " + mensagemEvent.mensagem);
//        if(mensagemEvent.getMensagem()!= null
//                && mensagemEvent.getMensagem().getText() != null
//                && !mensagemEvent.getMensagem().getText().trim().isEmpty()) {
            mensagens.add(mensagemEvent.mensagem);
//        }
//        adapter.notifyDataSetChanged();
        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);
//        ouvirMensagem();

    }

    @Subscribe
    public void ouvirMensagem(MensagemEvent mensagemEvent) {
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagemCallback(eventBus, this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // desregistra broadcast
//        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
//        localBroadcastManager.unregisterReceiver(receiver);
        // deixando claro que não queremos mais ouvir o evento
        eventBus.unregister(this);
    }

    @Subscribe
    public void lidarCom(FailureEvent event) {
        ouvirMensagem(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("mensagens", (ArrayList<Mensagem>) mensagens);
    }
}
