package com.info.privateroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Activity1 extends AppCompatActivity {
    private TextView textView11;
    private TextView textView12;
    private EditText editText1;
    private Button buttonGonder1;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("mesaj");

        textView11=findViewById(R.id.textView11);
        textView12=findViewById(R.id.textView12);
        editText1=findViewById(R.id.editText1);
        buttonGonder1=findViewById(R.id.buttonGonder1);


        buttonGonder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String editTextMesaj= editText1.getText().toString();
                textView11.setText(editTextMesaj);
                editText1.setText("");

                Map<String,Object> bilgiler = new HashMap<>();
                bilgiler.put("mesaj",editTextMesaj);
                myRef.child("-MMBxeRaf1IruCZFzbZx").updateChildren(bilgiler);
            }
        });

        Query MesajlarSorgu = myRef.orderByChild("mesaj_atan").equalTo("2");
        MesajlarSorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()){
                    Mesajlar mesaj=d.getValue(Mesajlar.class);
                    String key = d.getKey();
                    mesaj.setMesaj_key(key);

                    String gelenmesaj = mesaj.getMesaj().toString();
                    textView12.setText(gelenmesaj);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }
}