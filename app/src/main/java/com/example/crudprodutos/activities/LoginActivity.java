package com.example.crudprodutos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudprodutos.R;
import com.example.crudprodutos.models.Usuario;
import com.example.crudprodutos.readers.UsuarioReader;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private UsuarioReader reader;
    private EditText editTextLogin;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.reader = new UsuarioReader(getApplicationContext());

        this.editTextLogin = findViewById(R.id.edit_text_login);
        this.editTextPassword = findViewById(R.id.edit_text_password);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> {
            String login = editTextLogin.getText().toString().isEmpty() ? "" : editTextLogin.getText().toString();
            String password = editTextPassword.getText().toString().isEmpty() ? "" : editTextPassword.getText().toString();
            autenticar(new Usuario(login, password));
        });
    }

    private void autenticar(Usuario usuario) {
        if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
            Toast.makeText(getApplicationContext(), "O campo de login não pode ser vazio.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            Toast.makeText(getApplicationContext(), "O campo de senha não pode ser vazio.", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, Usuario> usuarios = this.reader.lerObjetosDoArquivo();
        if (!usuarios.containsKey(usuario.getLogin())) {
            Toast.makeText(getApplicationContext(), "Usuário não existe na base de dados.", Toast.LENGTH_SHORT).show();
            return;
        }
        Usuario usuarioEncontrado = usuarios.get(usuario.getLogin());
        if (usuarioEncontrado == null || !usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
            Toast.makeText(getApplicationContext(), "Credenciais incorretas. Por favor, verifique-as e tente novamente.", Toast.LENGTH_SHORT).show();
            return;
        }
        this.irParaMenuActivity();
    }

    private void irParaMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}