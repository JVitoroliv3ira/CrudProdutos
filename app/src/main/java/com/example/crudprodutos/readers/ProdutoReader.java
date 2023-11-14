package com.example.crudprodutos.readers;

import android.content.Context;

import com.example.crudprodutos.contracts.IReader;
import com.example.crudprodutos.models.Produto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ProdutoReader implements IReader<String, Produto> {

    private final Context context;

    public ProdutoReader(Context context) {
        this.context = context;
    }

    @Override
    public String obterCaminhoArquivo() {
        return "produtos.json";
    }

    @Override
    public Map<String, Produto> lerObjetosDoArquivo() {
        criarArquivoSeNaoExiste(context);

        Map<String, Produto> produtos = new HashMap<>();
        String caminhoArquivo = obterCaminhoArquivo();

        try (FileInputStream fis = context.openFileInput(caminhoArquivo);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String linha;
            while ((linha = br.readLine()) != null) {
                JSONObject jsonObject = new JSONObject(linha);
                Produto produto = new Produto(
                        jsonObject.getString("codigo"),
                        jsonObject.getString("nome"),
                        jsonObject.getString("descricao"),
                        jsonObject.getInt("quantidade")
                );
                produtos.put(produto.getCodigo(), produto);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}
