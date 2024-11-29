package isetb.tp7.testprojet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import isetb.tp7.testprojet.adapter.UserAdapter;
import isetb.tp7.testprojet.model.User;
import isetb.tp7.testprojet.utils.Apis;
import isetb.tp7.testprojet.utils.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.LayoutInflater;


public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserActionListener{

    private RecyclerView recyclerView;
    private List<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList,this);
        recyclerView.setAdapter(userAdapter);

        fetchUsers();
    }

    private void fetchUsers() {
        UserService userService = Apis.getService();
        Call<List<User>> call = userService.getAllUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userList.clear();
                    userList.addAll(response.body());
                    userAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("MainActivity", "Error fetching users", t);
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onEditUser(User user) {
        showEditDialog(user);
    }

    @Override
    public void onDeleteUser(User user) {
        confirmDeleteUser(user);
    }

    private void showEditDialog(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_user, null);

        // Déclaration des EditTexts pour chaque attribut
        EditText editNom = dialogView.findViewById(R.id.edit_user_nom);
        EditText editPrenom = dialogView.findViewById(R.id.edit_user_prenom);
        EditText editEmail = dialogView.findViewById(R.id.edit_user_email);
        EditText editUsername = dialogView.findViewById(R.id.edit_user_username);
        EditText editTelephone = dialogView.findViewById(R.id.edit_user_telephone);
        EditText editCin = dialogView.findViewById(R.id.edit_user_cin);
        EditText editDateNaissance = dialogView.findViewById(R.id.edit_user_date_naissance);
        EditText editDateDebutTravail = dialogView.findViewById(R.id.edit_user_date_debut_travail);
        EditText editPoste = dialogView.findViewById(R.id.edit_user_poste);
        EditText editAdresse = dialogView.findViewById(R.id.edit_user_adresse);

        // Remplissez les EditTexts avec les données existantes
        editNom.setText(user.getNom());
        editPrenom.setText(user.getPrenom());
        editEmail.setText(user.getEmail());
        editUsername.setText(user.getUsername());
        editTelephone.setText(user.getTelephone());
        editCin.setText(user.getCin());
        editDateNaissance.setText(user.getDateNaissance());
        editDateDebutTravail.setText(user.getDateDebutTravail());
        editPoste.setText(user.getPoste());
        editAdresse.setText(user.getAdresseComplet());

        builder.setTitle("Modifier Utilisateur")
                .setPositiveButton("Mettre à jour", (dialog, which) -> {
                    // Mettez à jour les informations de l'utilisateur ici
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

                    updateUser(user); // Appel à la méthode pour mettre à jour l'utilisateur dans la base de données
                })
                .setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss())
                .setView(dialogView)
                .create()
                .show();
    }

    private void updateUser(User user) {
        UserService userService = Apis.getService();

        userService.updateUser(user.getId(), user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    fetchUsers(); // Rafraîchir la liste des utilisateurs
                    Toast.makeText(MainActivity.this, "Utilisateur mis à jour", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Échec de la mise à jour", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur lors de la mise à jour", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void confirmDeleteUser(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Supprimer Utilisateur")
                .setMessage("Êtes-vous sûr de vouloir supprimer cet utilisateur ?")
                .setPositiveButton("Oui", (dialog, which) -> deleteUser(user))
                .setNegativeButton("Non", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void deleteUser(User user) {
        UserService userService = Apis.getService();

        userService.deleteUser(user.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    fetchUsers(); // Rafraîchir la liste des utilisateurs
                    Toast.makeText(MainActivity.this, "Utilisateur supprimé", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Échec de la suppression", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur lors de la suppression", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
