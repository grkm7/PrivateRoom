package com.info.privateroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView textViewSifre;
    private Button buttonSifre;

    private Button button1;

    private EditText PlainTextİsim;
    private Button buttonismiOnayla;

    private View  tasarim;
    private EditText editTextTasarim;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private String sifre;
    private String editTextMesaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("mesaj");

        button1=findViewById(R.id.button1);
        textViewSifre=findViewById(R.id.textViewSifre);
        buttonSifre=findViewById(R.id.buttonSifre);
        PlainTextİsim=findViewById(R.id.PlainTextİsim);
        buttonismiOnayla=findViewById(R.id.buttonismiOnayla);



        buttonSifre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query SifreSorgu = myRef.orderByChild("mesaj_atan").equalTo("şifre");
                SifreSorgu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot d:snapshot.getChildren()){
                            Mesajlar mesaj=d.getValue(Mesajlar.class);
                            String key = d.getKey();

                            mesaj.setMesaj_key(key);
                            sifre=mesaj.getMesaj().toString();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                tasarim = getLayoutInflater().inflate(R.layout.alert_tasarim,null);
                editTextTasarim = tasarim.findViewById(R.id.editTextTasarim);
                AlertDialog.Builder ad= new AlertDialog.Builder(MainActivity.this);
                ad.setMessage("Sifreyi Gir");
                ad.setView(tasarim);

                ad.setPositiveButton("Giriş", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String girilensifre = editTextTasarim.getText().toString();
                        Log.e("mesaj",girilensifre);

                        if(sifre.equals(girilensifre)){
                            textViewSifre.setText("Şifre Doğru");
                            PlainTextİsim.setVisibility(View.VISIBLE);
                            buttonismiOnayla.setVisibility(View.VISIBLE);
                        }else{
                            textViewSifre.setText("Şifre Yanlış");
                        }
                    }
                });
                ad.setNegativeButton("Son 5 Mesaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this,ActivitySon7Mesaj.class));
                    }
                });

                ad.create().show();

            }
        });
        buttonismiOnayla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextMesaj= PlainTextİsim.getText().toString();
                button1.setVisibility(View.VISIBLE);

                /*Map<String,Object> bilgiler = new HashMap<>();
                bilgiler.put("mesaj_atan",editTextMesaj);
                myRef.child("-MMBxeRaf1IruCZFzbZx").updateChildren(bilgiler);
                */
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,ActivitySon7Mesaj.class);
                intent.putExtra("name",editTextMesaj);
                startActivity(intent);
            }
        });


    }
}