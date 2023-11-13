package com.example.crudprodutos.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudprodutos.R;

import java.util.Objects;

public class DeletarProdutoActivity extends AppCompatActivity {

    private EditText editTextCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_produto);

        this.editTextCodigo = findViewById(R.id.edit_text_deletar_codigo);

        Button botaoCadastrar = findViewById(R.id.deletar_produto_deletar);
        botaoCadastrar.setOnClickListener(v -> {
            String codigo = editTextCodigo.getText().toString().isEmpty() ? "" : editTextCodigo.getText().toString();
            this.handleDeletar(codigo);
        });

        Button botaoLimpar = findViewById(R.id.deletar_produto_limpar);
        botaoLimpar.setOnClickListener(v -> this.handleLimpar());

        Button botaoVoltar = findViewById(R.id.deletar_produto_voltar);
        botaoVoltar.setOnClickListener(v -> this.handleVoltar());
    }

    private void handleDeletar(String codigo) {
        if (Boolean.TRUE.equals(this.validarFormulario(codigo))) {
            this.finish();
            Toast.makeText(getApplicationContext(), "Produto deletado com sucesso.", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean validarFormulario(String codigo) {
        if (Boolean.TRUE.equals(Objects.isNull(codigo) || codigo.isEmpty())) {
            Toast.makeText(getApplicationContext(), "Código do produto está vazio ou nulo.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void handleLimpar() {
        this.editTextCodigo.setText("");
    }

    private void handleVoltar() {
        this.finish();
    }
}