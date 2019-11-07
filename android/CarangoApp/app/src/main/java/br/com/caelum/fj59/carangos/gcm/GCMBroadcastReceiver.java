package br.com.caelum.fj59.carangos.gcm;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.LeilaoActivity;
import br.com.caelum.fj59.carangos.activity.MainActivity;
import br.com.caelum.fj59.carangos.infra.MyLog;

/**
 * Created by Hamilton on 22/09/16.
 */

public class GCMBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MyLog.i("Chegou a mensagem do GCM!!!");
        String mensagem = (String) intent.getExtras().getSerializable("message");
        MyLog.i("--> mensagem: " + mensagem);
        if(appEstaRodando(context)){
            Toast.makeText(context, "Leilão: " + mensagem, Toast.LENGTH_SHORT).show();
        }else{
            Intent irParaLeilao = new Intent(context, LeilaoActivity.class);
            PendingIntent acaoPendente = PendingIntent.getActivity(context, 0, irParaLeilao, PendingIntent.FLAG_CANCEL_CURRENT);
            irParaLeilao.putExtra("idDaNotificacao", Constantes.ID_NOTIFICACAO);
            MyLog.i("--> exibe notificação");
            Notification notificacao = new Notification.Builder(context)
                    .setContentTitle("Um novo leilão começou")
                    .setContentText("Leilão: " + mensagem)
                    .setSmallIcon(R.drawable.ic_camaro)
                    .setContentIntent(acaoPendente)
                    .setAutoCancel(true)
                    .build();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(Constantes.ID_NOTIFICACAO, notificacao);
        }
    }

    private boolean appEstaRodando(Context context){
        MyLog.i("Verifica se app está rodando");
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        MyLog.i("tasks.isEmpty() " + tasks.isEmpty());
        if(!tasks.isEmpty()){
            MyLog.i("entrou no if");
            ComponentName topActivity = tasks.get(0).topActivity;
            MyLog.i("topActivity=" + topActivity.getClassName());
            if(!topActivity.getPackageName().equals(context.getPackageName())){
                MyLog.i("--> retorna falso");
                return false;
            }
        }
        MyLog.i("--> retorna true");
        return true;
    }
}
