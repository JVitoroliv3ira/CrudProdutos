package com.example.crudprodutos.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudprodutos.R;
import com.example.crudprodutos.models.Produto;

import java.util.Objects;

public class CadastrarProdutoActivity extends AppCompatActivity {

    private EditText editTextCodigo;
    private EditText editTextNome;
    private EditText editTextDescricao;
    private EditText editTextQuantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        this.editTextCodigo = findViewById(R.id.edit_text_cadastrar_codigo);
        this.editTextNome = findViewById(R.id.edit_text_cadastrar_nome);
        this.editTextDescricao = findViewById(R.id.edit_text_cadastrar_descricao);
        this.editTextQuantidade = findViewById(R.id.edit_text_cadastrar_estoque);

        Button botaoCadastrar = findViewById(R.id.cadastrar_produto_cadastrar);
        botaoCadastrar.setOnClickListener(v -> {
            String codigo = editTextCodigo.getText().toString().isEmpty() ? "" : editTextCodigo.getText().toString();
            String nome = editTextNome.getText().toString().isEmpty() ? "" : editTextNome.getText().toString();
            String descricao = editTextDescricao.getText().toString().isEmpty() ? "" : editTextDescricao.getText().toString();

            int quantidade;
            try {
                quantidade = Integer.parseInt(this.editTextQuantidade.getText().toString());
            } catch (NumberFormatException e) {
                quantidade = 0;
            }

            Produto produto = new Produto(codigo, nome, descricao, quantidade);
            this.handleCadastrar(produto);
        });

        Button botaoLimpar = findViewById(R.id.cadastrar_produto_limpar);
        botaoLimpar.setOnClickListener(v -> this.handleLimpar());

        Button botaoVoltar = findViewById(R.id.cadastrar_produto_voltar);
        botaoVoltar.setOnClickListener(v -> this.handleVoltar());
    }

    private void handleCadastrar(Produto produto) {
        if (Boolean.TRUE.equals(produtoValido(produto))) {
            this.finish();
            Toast.makeText(getApplicationContext(), "Produto cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleLimpar() {
        this.editTextCodigo.setText("");
        this.editTextNome.setText("");
        this.editTextDescricao.setText("");
        this.editTextQuantidade.setText("");
    }

    private void handleVoltar() {
        this.finish();
    }

    private Boolean produtoValido(Produto produto) {
        Context context = getApplicationContext();

        if (Boolean.TRUE.equals(Objects.isNull(produto.getCodigo()) || produto.getCodigo().isEmpty())) {
            Toast.makeText(context, "Código do produto está vazio ou nulo.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Boolean.TRUE.equals(Objects.isNull(produto.getNome()) || produto.getNome().isEmpty())) {
            Toast.makeText(context, "Nome do produto está vazio ou nulo.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Boolean.TRUE.equals(Objects.isNull(produto.getDescricao()) || produto.getDescricao().isEmpty())) {
            Toast.makeText(context, "Descrição do produto está vazia ou nula.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Boolean.TRUE.equals(Objects.isNull(produto.getQuantidade()) || produto.getQuantidade() <= 0)) {
            Toast.makeText(context, "Quantidade do produto é inválida.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}