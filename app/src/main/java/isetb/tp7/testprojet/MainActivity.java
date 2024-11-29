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
        deleteUser(user);
    }

    private void showEditDialog(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_user, null);

        // Initialize EditTexts with current user data
        EditText editNom = dialogView.findViewById(R.id.edit_user_nom);
        EditText editPrenom = dialogView.findViewById(R.id.edit_user_prenom);

        editNom.setText(user.getNom());
        editPrenom.setText(user.getPrenom());

        builder.setTitle("Edit User")
                .setPositiveButton("Update", (dialog, which) -> {
                    user.setNom(editNom.getText().toString());
                    user.setPrenom(editPrenom.getText().toString());
                    updateUser(user);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
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
                    fetchUsers(); // Refresh the list
                    Toast.makeText(MainActivity.this, "Utilisateur mis à jour", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Échec de la mise à jour", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteUser(User user) {
        UserService userService = Apis.getService();

        userService.deleteUser(user.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    fetchUsers(); // Refresh the list
                    Toast.makeText(MainActivity.this, "Utilisateur supprimé", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Échec de la suppression", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
