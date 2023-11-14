package com.example.crudprodutos.contracts;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public interface IReader<K, E> {
    String obterCaminhoArquivo();

    Map<K, E> lerObjetosDoArquivo();

    default boolean arquivoExiste(Context context) {
        String caminhoArquivo = obterCaminhoArquivo();
        File file = context.getFileStreamPath(caminhoArquivo);
        return file.exists();
    }

    default void criarArquivoSeNaoExiste(Context context) {
        if (!arquivoExiste(context)) {
            try (FileOutputStream fos = context.openFileOutput(obterCaminhoArquivo(), Context.MODE_PRIVATE)) {
                fos.write("".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
