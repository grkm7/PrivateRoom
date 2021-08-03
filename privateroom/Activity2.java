package com.info.privateroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class Activity2 extends AppCompatActivity {
    private TextView textView21;
    private TextView textView22;
    private EditText editText2;
    private Button buttonGonder2;

    private FirebaseDatabase database;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("mesaj");

        textView21=findViewById(R.id.textView21);
        textView22=findViewById(R.id.textView22);
        editText2=findViewById(R.id.editText2);
        buttonGonder2=findViewById(R.id.buttonGonder2);

        buttonGonder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String editTextMesaj= editText2.getText().toString();
                textView22.setText(editTextMesaj);
                editText2.setText("");

                Map<String,Object> bilgiler = new HashMap<>();
                bilgiler.put("mesaj",editTextMesaj);
                myRef.child("-MMBxeRdBCK1xDiz3tmT").updateChildren(bilgiler);
            }
        });

        Query MesajlarSorgu = myRef.orderByChild("mesaj_atan").equalTo("1");
        MesajlarSorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()){
                    Mesajlar mesaj=d.getValue(Mesajlar.class);
                    String key = d.getKey();
                    mesaj.setMesaj_key(key);

                    String gelenmesaj = mesaj.getMesaj().toString();
                    textView21.setText(gelenmesaj);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}