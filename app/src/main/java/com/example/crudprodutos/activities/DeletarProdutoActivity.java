package com.example.crudprodutos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.crudprodutos.R;

public class DeletarProdutoActivity extends AppCompatActivity {

    private EditText editTextCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_produto);

        this.editTextCodigo = findViewById(R.id.edit_text_deletar_codigo);

        Button botaoLimpar = findViewById(R.id.deletar_produto_limpar);
        botaoLimpar.setOnClickListener(v -> this.handleLimpar());

        Button botaoVoltar = findViewById(R.id.deletar_produto_voltar);
        botaoVoltar.setOnClickListener(v -> this.handleVoltar());
    }

    private void handleLimpar() {
        this.editTextCodigo.setText("");
    }

    private void handleVoltar() {
        this.finish();
    }
}