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

        Button editarProdutoButton = findViewById(R.id.editar_produto);
        editarProdutoButton.setOnClickListener(v -> this.irParaEditarProduto());

        Button listarProdutosButton = findViewById(R.id.listar_produtos);
        listarProdutosButton.setOnClickListener(v -> this.irParaListarProdutos());

        Button deletarProdutoButton = findViewById(R.id.deletar_produto);
        deletarProdutoButton.setOnClickListener(v -> this.irParaDeletarProduto());
    }

    private void irParaCadastrarProdutos() {
        Intent intent = new Intent(this, CadastrarProdutoActivity.class);
        startActivity(intent);
    }

    private void irParaEditarProduto() {
        Intent intent = new Intent(this, EditarProdutoActivity.class);
        startActivity(intent);
    }

    private void irParaListarProdutos() {
        Intent intent = new Intent(this, ListarProdutosActivity.class);
        startActivity(intent);
    }

    private void irParaDeletarProduto() {
        Intent intent = new Intent(this, DeletarProdutoActivity.class);
        startActivity(intent);
    }
}