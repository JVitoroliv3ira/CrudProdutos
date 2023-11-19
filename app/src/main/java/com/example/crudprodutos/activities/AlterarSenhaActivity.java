package com.example.crudprodutos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudprodutos.R;
import com.example.crudprodutos.models.Usuario;
import com.example.crudprodutos.readers.UsuarioReader;
import com.example.crudprodutos.writers.UsuarioWriter;

import java.util.Map;

public class AlterarSenhaActivity extends AppCompatActivity {

    private UsuarioReader reader;
    private UsuarioWriter writer;
    private EditText editTextLogin;
    private EditText editTextNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        this.reader = new UsuarioReader(getApplicationContext());
        this.writer = new UsuarioWriter(getApplicationContext());

        this.editTextLogin = findViewById(R.id.edit_text_alterar_password_login);
        this.editTextNewPassword = findViewById(R.id.edit_text_alterar_password_password);

        Button alterarSenhaButton = findViewById(R.id.alterar_senha_alterar);
        alterarSenhaButton.setOnClickListener(v -> alterarSenha());

        Button botaoVoltar = findViewById(R.id.alterar_senha_voltar);
        botaoVoltar.setOnClickListener(v -> this.handleVoltar());
    }

    private void handleVoltar() {
        this.finish();
    }

    private void alterarSenha() {
        String login = editTextLogin.getText().toString();
        String newPassword = editTextNewPassword.getText().toString();
        if (login.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Login e nova senha são obrigatórios.", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, Usuario> usuarios = this.reader.lerObjetosDoArquivo();
        if (!usuarios.containsKey(login)) {
            Toast.makeText(getApplicationContext(), "Usuário não existe.", Toast.LENGTH_SHORT).show();
            return;
        }
        Usuario usuario = usuarios.get(login);
        if (usuario == null) {
            Toast.makeText(getApplicationContext(), "Erro ao encontrar o usuário.", Toast.LENGTH_SHORT).show();
            return;
        }
        usuario.setPassword(newPassword);
        this.writer.escreverEntidadesNoArquivo(usuarios);
        Toast.makeText(getApplicationContext(), "Senha alterada com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }
}