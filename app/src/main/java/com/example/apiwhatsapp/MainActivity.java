package com.example.apiwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whatsappintent.R;
import com.hbb20.CountryCodePicker;


public class MainActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText numero, mensagem;
    Button sendbtn;
    String messagestr, phonestr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccp = findViewById(R.id.countryCode);
        numero = findViewById(R.id.numero_cel);
        mensagem = findViewById(R.id.mensagem_text);
        sendbtn = findViewById(R.id.btn_enviar);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messagestr = mensagem.getText().toString();
                phonestr = numero.getText().toString();


                if (!messagestr.isEmpty() && !phonestr.isEmpty()) {

                    ccp.registerCarrierNumberEditText(numero);
                    phonestr = ccp.getFullNumber();

                    Uri webpage = Uri.parse("https://api.whatsapp.com/send?phone="+phonestr+"&text="+messagestr);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webIntent);


                }else{
                    Toast.makeText(MainActivity.this, "Gentileza, preencha os campos a cima ", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

}