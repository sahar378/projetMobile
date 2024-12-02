package isetb.tp7.testprojet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import isetb.tp7.testprojet.model.User;
import isetb.tp7.testprojet.utils.Apis;
import isetb.tp7.testprojet.utils.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    private EditText editNom, editPrenom, editEmail, editUsername, editTelephone, editCin, editDateNaissance, editDateDebutTravail, editPoste, editAdresse, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Initialiser les EditTexts
        editNom = findViewById(R.id.edit_user_nom);
        editPrenom = findViewById(R.id.edit_user_prenom);
        editEmail = findViewById(R.id.edit_user_email);
        editUsername = findViewById(R.id.edit_user_username);
        editTelephone = findViewById(R.id.edit_user_telephone);
        editCin = findViewById(R.id.edit_user_cin);
        editDateNaissance = findViewById(R.id.edit_user_date_naissance);
        editDateDebutTravail = findViewById(R.id.edit_user_date_debut_travail);
        editPoste = findViewById(R.id.edit_user_poste);
        editAdresse = findViewById(R.id.edit_user_adresse);
        editPassword = findViewById(R.id.edit_user_password);

        Button buttonAddUser = findViewById(R.id.button_add_user);

        buttonAddUser.setOnClickListener(v -> showConfirmationDialog());
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Add user")
                .setMessage("Are you sure about adding this user ?")
                .setPositiveButton("Yes", (dialog, which) -> addUser())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void addUser() {
        User user = new User();
        user.setNom(editNom.getText().toString());
        user.setPrenom(editPrenom.getText().toString());
        user.setEmail(editEmail.getText().toString());
        user.setUsername(editUsername.getText().toString());
        user.setTelephone(editTelephone.getText().toString());
        user.setCin(editCin.getText().toString());
        user.setDateNaissance(editDateNaissance.getText().toString());
        user.setDateDebutTravail(editDateDebutTravail.getText().toString());
        user.setPoste(editPoste.getText().toString());
        user.setAdresseComplet(editAdresse.getText().toString());
        user.setAdresseComplet(editPassword.getText().toString());


        UserService userService = Apis.getService();

        userService.addUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Fermer l'activité après ajout
                } else {
                    Toast.makeText(AddActivity.this, "Eror when adding user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Eror adding user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}