package com.example.meusmedicos.controllers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.meusmedicos.models.Consulta;
import com.example.meusmedicos.views.consulta.ShowConsultas;
import com.example.meusmedicos.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JULIE on 09/08/2015.
 */
public class BrodcastReceiverAux extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("Script", "-> Alarme");
        procurarNotificacao(context);

    }

    public void procurarNotificacao(Context context){
        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        ArrayList<Consulta> s = Controller.getConsultas();
        ArrayList<String> todayConsultas = new ArrayList<String>();
        Calendar actualTime = Calendar.getInstance();
        actualTime.setTime(new Date());

        for (Consulta item : s) {
            if (item.getLembrar()){
                if (actualTime.compareTo(item.getDate()) == 0){
                    todayConsultas.add("Consulta com: " + item.getMedico() + " " + item.getEspecialidade());
                }
            }
        }

        if (todayConsultas.size() != 0){
            gerarNotificacao(context, new Intent(context, ShowConsultas.class), "Nova Consulta", "MeusMedicos", todayConsultas);
        }

    }

    public void gerarNotificacao(Context context, Intent intent, CharSequence ticker, CharSequence titulo, ArrayList descricao){
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(titulo);

        builder.setSmallIcon(R.drawable.logo_meus_medicos);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.meus_medicos));
        builder.setContentIntent(p);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        String[] desc = new String[descricao.size()];
        for(int i = 0; i < desc.length; i++){
            style.addLine(desc[i]);
        }
        builder.setStyle(style);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.ic_launcher, n);

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        }
        catch(Exception e){}
    }



}