package br.com.caleum.cadastro.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caleum.cadastro.R;
import br.com.caleum.cadastro.persistencia.AlunoDAO;

/**
 * Created by Hamilton on 13/09/16.
 */
public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle b = intent.getExtras();
        Object [] mensagens = (Object[]) b.get("pdus");
        byte [] mensagem = (byte[]) mensagens[0];
        String formato = (String) b.get("format");
        SmsMessage sms = SmsMessage.createFromPdu(mensagem, formato);
        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);
        if(dao.isAluno(telefone)){
            Toast.makeText(context, R.string.chegouSms + sms.getMessageBody(), Toast.LENGTH_SHORT).show();
            MediaPlayer mp = MediaPlayer.create(context,R.raw.cogumelo);
            mp.start();
        }

//        Toast.makeText(context, R.string.chegouSms, Toast.LENGTH_SHORT).show();
    }

}
