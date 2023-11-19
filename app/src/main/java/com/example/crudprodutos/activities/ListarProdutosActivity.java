package com.example.crudprodutos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crudprodutos.R;
import com.example.crudprodutos.adapters.ProdutoAdapter;
import com.example.crudprodutos.models.Produto;
import com.example.crudprodutos.readers.ProdutoReader;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProdutoAdapter adapter;
    private List<Produto> listaDeProdutos;
    private ProdutoReader reader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        this.reader = new ProdutoReader(getApplicationContext());
        this.listaDeProdutos = this.getProdutos();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProdutoAdapter(listaDeProdutos);
        recyclerView.setAdapter(adapter);
    }

    private List<Produto> getProdutos() {
        return new ArrayList<>(this.reader.lerObjetosDoArquivo().values());
    }
}