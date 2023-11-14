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
        if (!produto.getCodigo().isEmpty()) {
            Map<String, Produto> produtos = this.reader.lerObjetosDoArquivo();
            if (produtos.containsKey(produto.getCodigo())) {
                Produto produtoExistente = produtos.get(produto.getCodigo());

                if (produtoExistente != null) {
                    if (!produto.getNome().isEmpty()) produtoExistente.setNome(produto.getNome());
                    if (!produto.getDescricao().isEmpty())
                        produtoExistente.setDescricao(produto.getDescricao());
                    if (produto.getQuantidade() > 0)
                        produtoExistente.setQuantidade(produto.getQuantidade());

                    this.writer.escreverEntidadesNoArquivo(produtos);
                    Toast.makeText(getApplicationContext(), "Produto editado com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Produto não encontrado.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Produto não encontrado.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Código do produto é obrigatório.", Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }

    private void handleLimpar() {
        this.editTextCodigo.setText("");
        this.editTextNome.setText("");
        this.editTextDescricao.setText("");
        this.editTextQuantidade.setText("");
    }
}