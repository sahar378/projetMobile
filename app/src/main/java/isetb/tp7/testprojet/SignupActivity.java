package isetb.tp7.testprojet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import isetb.tp7.testprojet.model.User;
import isetb.tp7.testprojet.utils.Apis;
import isetb.tp7.testprojet.utils.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText nameInput, surnameInput, emailInput, passwordInput, confirmPasswordInput, phoneInput, cinInput, posteInput, addressInput, dobInput, startDateInput;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize inputs
        nameInput = findViewById(R.id.nameInput);
        surnameInput = findViewById(R.id.surnameInput); // Assuming you have a field for surname
        emailInput = findViewById(R.id.signupEmailInput);
        passwordInput = findViewById(R.id.signupPasswordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        phoneInput = findViewById(R.id.phoneInput); // New field for phone
        cinInput = findViewById(R.id.cinInput); // New field for CIN
        posteInput = findViewById(R.id.posteInput); // New field for Poste
        addressInput = findViewById(R.id.addressInput); // New field for address
        dobInput = findViewById(R.id.dobInput); // New field for Date of Birth
        startDateInput = findViewById(R.id.startDateInput); // New field for Start Date
        Button signupButton = findViewById(R.id.signupButton);
        TextView loginLink = findViewById(R.id.loginLink);

        // Initialize API interface
        userService = Apis.getService();

        // Handle Sign-Up Logic
        signupButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString().trim();
            String surname = surnameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();
            String confirmPassword = confirmPasswordInput.getText().toString();
            String phone = phoneInput.getText().toString().trim();
            String cin = cinInput.getText().toString().trim();
            String poste = posteInput.getText().toString().trim();
            String address = addressInput.getText().toString().trim();
            String dob = dobInput.getText().toString().trim();
            String startDate = startDateInput.getText().toString().trim();

            if (validateInputs(name, surname, email, password, confirmPassword, phone, cin, poste, address, dob, startDate)) {
                // Create a new User object with the data

                User user = new User(name, surname, email, name, password, phone, cin, poste, address, dob, startDate); // Assuming no image initially
                // Proceed with sign-up logic (e.g., send data to server)


                Call<User> call = userService.addUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Utilisateur ajouté avec succès", Toast.LENGTH_SHORT).show();
                            finish(); // Close the activity after success
                        } else {
                            Toast.makeText(SignupActivity.this, "Erreur lors de l'ajout: " + response.message(), Toast.LENGTH_SHORT).show();
                            Log.e("AddUtilisateur", "Erreur: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, "Erreur de connexion, veuillez réessayer", Toast.LENGTH_SHORT).show();
                        Log.e("Signup", "Failure: ", t);
                    }
                });
            }
        });

        loginLink.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private boolean validateInputs(String name, String surname, String email, String password, String confirmPassword,
                                   String phone, String cin, String poste, String address, String dob, String startDate) {
        // Check if fields are empty
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your first name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(surname)) {
            Toast.makeText(this, "Please enter your surname", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(cin)) {
            Toast.makeText(this, "Please enter your CIN", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(poste)) {
            Toast.makeText(this, "Please enter your job position", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Please enter your date of birth", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(startDate)) {
            Toast.makeText(this, "Please enter your start date", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Additional password checks (optional)
        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}