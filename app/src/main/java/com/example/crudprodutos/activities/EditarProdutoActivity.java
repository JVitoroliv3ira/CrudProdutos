package com.example.crudprodutos.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudprodutos.R;
import com.example.crudprodutos.models.Produto;
import com.example.crudprodutos.readers.ProdutoReader;
import com.example.crudprodutos.writers.ProdutoWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditarProdutoActivity extends AppCompatActivity {

    private EditText editTextCodigo;
    private EditText editTextNome;
    private EditText editTextDescricao;
    private EditText editTextQuantidade;

    private ProdutoReader reader;
    private ProdutoWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        this.reader = new ProdutoReader(getApplicationContext());
        this.writer = new ProdutoWriter(getApplicationContext());

        this.editTextCodigo = findViewById(R.id.edit_text_editar_codigo);
        this.editTextNome = findViewById(R.id.edit_text_editar_nome);
        this.editTextDescricao = findViewById(R.id.edit_text_editar_descricao);
        this.editTextQuantidade = findViewById(R.id.edit_text_editar_quantidade);

        Button botaoEditar = findViewById(R.id.editar_produto_editar);
        botaoEditar.setOnClickListener(v -> {
            String codigo = editTextCodigo.getText().toString();
            String nome = editTextNome.getText().toString();
            String descricao = editTextDescricao.getText().toString();
            int quantidade;
            try {
                quantidade = Integer.parseInt(editTextQuantidade.getText().toString());
            } catch (NumberFormatException e) {
                quantidade = 0;
            }

            Produto produto = new Produto(codigo, nome, descricao, quantidade);
            this.handleEditar(produto);
        });

        Button botaoLimpar = findViewById(R.id.editar_produto_limpar);
        botaoLimpar.setOnClickListener(v -> this.handleLimpar());

        Button botaoVoltar = findViewById(R.id.editar_produto_voltar);
        botaoVoltar.setOnClickListener(v -> this.finish());
    }

    private void handleEditar(Produto produto) {
        if (produto.getCodigo().isEmpty()) {
            showToast("Código do produto é obrigatório.");
            return;
        }

        Map<String, Produto> produtos = lerProdutos();
        Produto produtoExistente = produtos.get(produto.getCodigo());

        if (produtoExistente == null) {
            showToast("Produto não encontrado.");
            return;
        }

        atualizarProduto(produto, produtoExistente);
        salvarProdutos(produtos);
        showToast("Produto editado com sucesso.");
        this.finish();
    }

    private void atualizarProduto(Produto produtoNovo, Produto produtoExistente) {
        if (!produtoNovo.getNome().isEmpty()) produtoExistente.setNome(produtoNovo.getNome());
        if (!produtoNovo.getDescricao().isEmpty()) produtoExistente.setDescricao(produtoNovo.getDescricao());
        if (produtoNovo.getQuantidade() >= 0) produtoExistente.setQuantidade(produtoNovo.getQuantidade());
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Map<String, Produto> lerProdutos() {
        return this.reader.lerObjetosDoArquivo();
    }

    private void salvarProdutos(Map<String, Produto> produtos) {
        this.writer.escreverEntidadesNoArquivo(produtos);
    }


    private void handleLimpar() {
        this.editTextCodigo.setText("");
        this.editTextNome.setText("");
        this.editTextDescricao.setText("");
        this.editTextQuantidade.setText("");
    }
}