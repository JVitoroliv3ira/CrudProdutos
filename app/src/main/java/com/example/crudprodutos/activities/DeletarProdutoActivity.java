package com.example.crudprodutos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.crudprodutos.R;

public class DeletarProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_produto);

        Button botaoVoltar = findViewById(R.id.deletar_produto_voltar);
        botaoVoltar.setOnClickListener(v -> this.handleVoltar());
    }

    private void handleVoltar() {
        this.finish();
    }
}