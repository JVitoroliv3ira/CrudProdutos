package com.example.crudprodutos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudprodutos.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> autenticar());
    }

    private void autenticar() {
        // TODO: Implementar verificacao do login do usuario
        this.irParaMenuActivity();
    }

    private void irParaMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}