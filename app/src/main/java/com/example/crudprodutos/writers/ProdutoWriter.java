package com.example.crudprodutos.writers;

import android.content.Context;

import com.example.crudprodutos.contracts.IWriter;
import com.example.crudprodutos.models.Produto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class ProdutoWriter implements IWriter<String, Produto> {

    private final Context context;

    public ProdutoWriter(Context context) {
        this.context = context;
    }

    @Override
    public String obterCaminhoArquivo() {
        return "produtos.json";
    }

    @Override
    public void escreverEntidadesNoArquivo(Map<String, Produto> mapaProdutos) {
        criarArquivoSeNaoExiste(context);
        String caminhoArquivo = obterCaminhoArquivo();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(caminhoArquivo, Context.MODE_PRIVATE)))) {
            for (Map.Entry<String, Produto> entry : mapaProdutos.entrySet()) {
                String jsonProduto = converterParaJson(entry.getValue());
                writer.write(jsonProduto);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private String converterParaJson(Produto produto) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("codigo", produto.getCodigo());
        jsonObject.put("nome", produto.getNome());
        jsonObject.put("descricao", produto.getDescricao());
        jsonObject.put("quantidade", produto.getQuantidade());
        return jsonObject.toString();
    }
}
