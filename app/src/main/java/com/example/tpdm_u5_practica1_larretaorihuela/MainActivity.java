package com.example.tpdm_u5_practica1_larretaorihuela;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity  extends AppCompatActivity {

    final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    static final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    Base base;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        base=new Base(this, "Base1", null, 1);
        insertar_datos();
        solicitarPermisos();


    }//onCreate



    public void permisos_enviar(){
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);

        if(checkPermission(Manifest.permission.SEND_SMS) && checkPermission(Manifest.permission.RECEIVE_SMS)){
            Toast.makeText(this, "Thankyou for permitting!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

        }
    }
    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
    private void solicitarPermisos() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //Entra si el permiso esta denegado, ya que será diferente a permiso OTORGADO
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_SMS},3);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //Entra si el permiso esta denegado, ya que será diferente a permiso OTORGADO
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.RECEIVE_SMS},4);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            //Entra si el permiso esta denegado, ya que será diferente a permiso OTORGADO
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_PHONE_STATE},1);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //Entra si el permiso esta denegado, ya que será diferente a permiso OTORGADO
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.SEND_SMS},2);
        }
    }

    void insertar_datos(){
        SQLiteDatabase db = this.base.getWritableDatabase();
        db.execSQL("INSERT INTO Horoscopo VALUES('aries','Crees que no quieres algo que tienes.')");
        db.execSQL("INSERT INTO Horoscopo VALUES('tauro','Por más que te mires todos los días en el espejo, no verás los cambios que se están produciendo.')");
        db.execSQL("INSERT INTO Horoscopo VALUES('geminis','La violencia y la agresión siempre son signos de temor.')");
        db.execSQL("INSERT INTO Horoscopo VALUES('cancer','Todos estamos experimentando continuos cambios de un modo u otro. P')");
        db.execSQL("INSERT INTO Horoscopo VALUES('leo','Parece irónico que, a pesar de esto, la madurez se otorgue sólo por el paso de los años. ')");
        db.execSQL("INSERT INTO Horoscopo VALUES('virgo','Hay gente que necesita que le digan lo que deben hacer.')");
        db.execSQL("INSERT INTO Horoscopo VALUES('libra','A veces elegir el camino más corto hará que el viaje sea más agradable.')");
        db.execSQL("INSERT INTO Horoscopo VALUES('escorpio','Vivimos en un mundo en el que todos hablan pero nadie parece escuchar. ')");
        db.execSQL("INSERT INTO Horoscopo VALUES('sagitario','Dicen que la curiosidad mató al gato.')");
        db.execSQL("INSERT INTO Horoscopo VALUES('capricornio','odos somos capaces de pasar de ser lindos gatitos a rugir como leones sólo en cuestión de segundos.')");
        db.execSQL("INSERT INTO Horoscopo VALUES('acuario','Cada vez que nuestros líderes hablan, sus palabras son grabadas y quedan para la eternidad. ')");
        db.execSQL("INSERT INTO Horoscopo VALUES('picis','El problema del consejo ‘no vivas por encima de tus posibilidades’ es que surge cuando la gente sobrepasa sus propios límites.')");

    }
}
