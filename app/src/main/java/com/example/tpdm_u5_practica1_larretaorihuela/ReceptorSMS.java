package com.example.tpdm_u5_practica1_larretaorihuela;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.telephony.SmsManager;
import java.util.Date;

public class ReceptorSMS extends BroadcastReceiver {
    SmsMessage[] sms;
    Base base;
    String formato,origen,cuerpo,enviotexto;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        base = new Base(context, "Base1", null, 1);
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            sms = null;

            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    sms = new SmsMessage[pdus.length];

                    for (int i = 0; i < sms.length; i++) {
                        formato = myBundle.getString("format");
                        sms[i] = SmsMessage.createFromPdu(((byte[]) pdus[i]), formato);
                        origen = sms[i].getOriginatingAddress();
                        cuerpo = sms[i].getMessageBody();
                        Long hora = sms[i].getTimestampMillis();
                        Date fecha = new Date(hora);

                        Toast.makeText(context, origen + " \n" + cuerpo + " \n" + hora + " \n" + fecha, Toast.LENGTH_LONG).show();

                        String strPhone = "XXXXXXXXXXX";

                        String strMessage = "LoremnIpsum";

                        SmsManager sms = SmsManager.getDefault();
                        buscarSigno(cuerpo.toLowerCase());
                        sms.sendTextMessage(strPhone, null, strMessage, null, null);
                    }
                } catch (Exception e) {
                }
            }
        }
    }
    public String buscarSigno(String sig) {
        try {
            SQLiteDatabase base = this.base.getReadableDatabase();
            String[] signo = {sig};
            Cursor c = base.rawQuery("SELECT * FROM Horoscopo WHERE signo = ?", signo);
            System.out.println(c.getCount());
            if (c.moveToFirst()) {
                return (c.getString(1));
            } else {
                return ("No se encontraron coincidencias");
            }
        } catch (SQLiteException e) {
            return (e.getMessage());
        }
    }

}

