package com.example.crudprodutos.writers;

import android.content.Context;

import com.example.crudprodutos.contracts.IWriter;
import com.example.crudprodutos.models.Produto;
import com.example.crudprodutos.models.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class UsuarioWriter implements IWriter<String, Usuario> {
    private final Context context;

    public UsuarioWriter(Context context) {
        this.context = context;
    }

    @Override
    public String obterCaminhoArquivo() {
        return "usuarios.json";
    }

    @Override
    public void escreverEntidadesNoArquivo(Map<String, Usuario> entidades) {
        criarArquivoSeNaoExiste(context);
        String caminhoArquivo = obterCaminhoArquivo();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(caminhoArquivo, Context.MODE_PRIVATE)))) {
            for (Map.Entry<String, Usuario> entry : entidades.entrySet()) {
                String jsonProduto = converterParaJson(entry.getValue());
                writer.write(jsonProduto);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private String converterParaJson(Usuario usuario) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", usuario.getLogin());
        jsonObject.put("password", usuario.getPassword());
        return jsonObject.toString();
    }

    @Override
    public void criarArquivoSeNaoExiste(Context context) {
        if (!arquivoExiste(context)) {
            try (FileOutputStream fos = context.openFileOutput(obterCaminhoArquivo(), Context.MODE_PRIVATE)) {
                JSONObject usuarioPadrao = new JSONObject();
                usuarioPadrao.put("login", "admin");
                usuarioPadrao.put("password", "admin");
                fos.write(usuarioPadrao.toString().getBytes());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
