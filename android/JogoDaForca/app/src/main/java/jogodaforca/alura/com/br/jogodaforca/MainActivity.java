package jogodaforca.alura.com.br.jogodaforca;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Button btJogar;
    private Button btIniciar;
    private EditText etLetra;
    private TextView tvLetrasUsadas;
    private ForcaView forcaView;
    private ForcaController forcaController;
    private String [] palavra = new String[]{"cerveja", "cachaça", "whisky", "vodka", "vinho", "conhaque", "liquor", "pinga", "aguardente", "grapa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btJogar = (Button) findViewById(R.id.btJogar);
        btIniciar = (Button) findViewById(R.id.btIniciar);
        etLetra = (EditText) findViewById(R.id.etLetra);
        tvLetrasUsadas = (TextView) findViewById(R.id.tvLetraUsadas);
        forcaView = (ForcaView) findViewById(R.id.fvJogo);
        init();
    }

    private void init(){
        etLetra.setSingleLine();

        etLetra.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    jogar();
                    return true;
                }
                return false;
            }
        });

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setForcaController(new ForcaController(palavra[new Random().nextInt(palavra.length)]));
                forcaView.setForcaController(getForcaController());
                forcaView.invalidate();
                etLetra.getText().clear();
                etLetra.setEnabled(true);
                btJogar.setEnabled(true);
                btIniciar.setEnabled(false);
            }
        });

        btJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              jogar();
            }
        });
    }

    private void jogar(){
        //So joga se existir letra
        if(!etLetra.getText().toString().trim().isEmpty()){
            Log.i("Main", "Main " + etLetra.getText().toString());
            try{
                getForcaController().joga(etLetra.getText().toString().trim().charAt(0));
                tvLetrasUsadas.setText("Letras usadas: " + getForcaController().getLetrasUsadas().toString());
                forcaView.invalidate(); // refresh na view
                etLetra.getText().clear();
                if(getForcaController().isTerminou()){
                    btJogar.setEnabled(false);
                    btIniciar.setEnabled(true);
                    etLetra.setEnabled(false);
                    if(getForcaController().isMorreu()){
                        Toast.makeText(getApplicationContext(), "Ha ha! Perdeu otário!", Toast.LENGTH_LONG).show();
                    }else{
                        if(getForcaController().isGanhou()){
                            Toast.makeText(getApplicationContext(), "Ganhou!!!", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                etLetra.getText().clear();
            }

        }else{
            Toast.makeText(getApplicationContext(), "Digita uma letra ae caraio!", Toast.LENGTH_LONG).show();
        }
        etLetra.requestFocus();
    }

    public ForcaController getForcaController() {
        return forcaController;
    }
    public void setForcaController(ForcaController forcaController) {
        this.forcaController = forcaController;
    }
}
