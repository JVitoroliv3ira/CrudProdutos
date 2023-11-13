package com.example.crudprodutos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudprodutos.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button cadastrarProdutoButton = findViewById(R.id.cadastrar_produto);
        cadastrarProdutoButton.setOnClickListener(v -> this.irParaCadastrarProdutos());
    }

    private void irParaCadastrarProdutos() {
        Intent intent = new Intent(this, CadastrarProdutoActivity.class);
        startActivity(intent);
    }
}