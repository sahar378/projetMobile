package isetb.tp7.testprojet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import isetb.tp7.testprojet.model.LoginRequest;
import isetb.tp7.testprojet.model.User;
import isetb.tp7.testprojet.utils.Client;
import isetb.tp7.testprojet.utils.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String BASE_URL = "http://10.0.2.2:8080/";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginButton);
        TextView signupLink = findViewById(R.id.signupLink);
        TextView forgotPasswordLink = findViewById(R.id.forgotPasswordLink);


        // Créer une instance de UserService
        UserService userService = Client.getRetrofit(BASE_URL).create(UserService.class);

        // Navigate to SignupActivity
        signupLink.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        // Placeholder login functionality
        loginButton.setOnClickListener(view -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Vérifier si les champs sont remplis
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Créer un objet LoginRequest
            LoginRequest loginRequest = new LoginRequest(email, password);

            // Faire la requête Retrofit
            Call<User> call = userService.login(loginRequest);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        User loggedInUser = response.body();

                        Log.d("LoginSuccess", "User: " + response.body().getEmail());
                        Toast.makeText(LoginActivity.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("userName", loggedInUser.getUsername());
                        startActivity(intent);
                    } else {
                        Log.e("LoginError", "Code: " + response.code());
                        Toast.makeText(LoginActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("LoginFailure", "Erreur: " + t.getMessage());
                    Toast.makeText(LoginActivity.this, "Erreur réseau, réessayez plus tard", Toast.LENGTH_SHORT).show();
                }
            });

        });


        // Navigate to ResetPasswordActivity
        /*forgotPasswordLink.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
        });*/
    }
}