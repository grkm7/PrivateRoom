package com.info.privateroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivitySon7Mesaj extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<Mesajlar> mesajlarListe;
    private MesajlarAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private String gelenisim;
    private String girilenMesaj;

    private EditText PlainTextMesaj;
    private TextView textViewMesajAtan;
    private Button buttonGönder;

    private int id_sayac=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son7_mesaj);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("mesaj");

        rv=findViewById(R.id.rv);
        PlainTextMesaj=findViewById(R.id.PlainTextMesaj);
        textViewMesajAtan=findViewById(R.id.textViewMesajAtan);
        buttonGönder=findViewById(R.id.buttonGönder);



        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        mesajlarListe = new ArrayList<>();

        gelenisim=getIntent().getStringExtra("name").toString();
        Log.e("isim",gelenisim);
        textViewMesajAtan.setText(gelenisim+" yazıyor..");



        buttonGönder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girilenMesaj=PlainTextMesaj.getText().toString();
                databaseGuncelle1(girilenMesaj);
                id_sayac++;



            }
        });

        adapter= new MesajlarAdapter(this,mesajlarListe);
        rv.setAdapter(adapter);
        tumKelimeler();

    }
    public void tumKelimeler(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mesajlarListe.clear();
                for(DataSnapshot d: snapshot.getChildren()){
                    Mesajlar mesaj = d.getValue(Mesajlar.class);
                    mesaj.setMesaj_key(d.getKey());
                    if(mesaj.getMesaj_atan().equals("şifre")) {
                        continue;

                    }else{
                        mesajlarListe.add(mesaj);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void databaseGuncelle1(final String gelenMesaj){
        final ArrayList<String> mesajListesi= new ArrayList<>();
        //String mesaj;
        Query dg2den1e= myRef.orderByChild("mesaj_id").equalTo(String.valueOf(id_sayac-4));
        dg2den1e.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mesajlarListe.clear();
                for(DataSnapshot d: snapshot.getChildren()){
                    Mesajlar mesaj = d.getValue(Mesajlar.class);
                    mesaj.setMesaj_key(d.getKey());


                    myRef.child(mesaj.getMesaj_key()).removeValue();
                    /*Map<String,Object> bilgiler = new HashMap<>();
                    bilgiler.put("mesaj_atan",mesajListesi.get(0));
                    myRef.child("-MMBxeRaf1IruCZFzbZx").updateChildren(bilgiler);*/

                    Mesajlar mesaj20=new Mesajlar("",String.valueOf(id_sayac),gelenMesaj,id_sayac+1);
                    myRef.push().setValue(mesaj20);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}