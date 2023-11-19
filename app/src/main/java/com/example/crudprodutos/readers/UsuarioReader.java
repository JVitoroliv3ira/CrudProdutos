package com.example.crudprodutos.readers;

import android.content.Context;

import com.example.crudprodutos.contracts.IReader;
import com.example.crudprodutos.models.Produto;
import com.example.crudprodutos.models.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UsuarioReader implements IReader<String, Usuario> {

    private final Context context;

    public UsuarioReader(Context context) {
        this.context = context;
    }

    @Override
    public String obterCaminhoArquivo() {
        return "usuarios.json";
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

    @Override
    public Map<String, Usuario> lerObjetosDoArquivo() {
        criarArquivoSeNaoExiste(context);

        Map<String, Usuario> usuarios = new HashMap<>();
        String caminhoArquivo = obterCaminhoArquivo();

        try (FileInputStream fis = context.openFileInput(caminhoArquivo);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String linha;
            while ((linha = br.readLine()) != null) {
                JSONObject jsonObject = new JSONObject(linha);
                Usuario usuario = new Usuario(
                        jsonObject.getString("login"),
                        jsonObject.getString("password")
                );
                usuarios.put(usuario.getLogin(), usuario);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
