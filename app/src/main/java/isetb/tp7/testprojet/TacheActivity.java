package isetb.tp7.testprojet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import isetb.tp7.testprojet.adapter.TacheAdapter;
import isetb.tp7.testprojet.model.Tache;

public class TacheActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    TacheDatabase db;
    TacheAdapter adapter;
    ArrayList<Tache> list = new ArrayList<>();
    ImageButton imageButton;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_tache);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageButton = findViewById(R.id.btnadd);
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(TacheActivity.this, AddTacheActivity.class);
            startActivity(intent);
        });
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new TacheDatabase(this);
        initializeAdapter();
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateAdapterData();
    }
    private void initializeAdapter() {
        list = db.getAllTache();

        if (list.isEmpty()) {
            Toast.makeText(this, "Empty database", Toast.LENGTH_LONG).show();
        }
        adapter = new TacheAdapter(list, new TacheAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Tache tache, int position) {
                showEditDialog(tache, position);
            }

            @Override
            public void onDeleteClick(Tache tache, int position) {
                showDeleteDialog(tache, position);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void updateAdapterData() {
        list.clear();
        list.addAll(db.getAllTache());

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            // Reinitialize adapter in case it wasn't set up
            initializeAdapter();
        }
    }
    private void showDeleteDialog(Tache tache, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Task");
        builder.setMessage("Are you sure you want to delete this task?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            if (position >= 0 && position < list.size()) {
                db.removeTache(tache.getIdt());
                list.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, list.size());

                Toast.makeText(this, "Task deleted successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void showEditDialog(Tache tache, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");
        View view = getLayoutInflater().inflate(R.layout.dialog_edit_tache, null);
        EditText editName = view.findViewById(R.id.nomt);
        EditText editDescription = view.findViewById(R.id.description);

        editName.setText(tache.getNomt());
        editDescription.setText(tache.getDescrption());

        builder.setView(view);
        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = editName.getText().toString();
            String newDescription = editDescription.getText().toString();

            if (newName.isEmpty() || newDescription.isEmpty()) {
                Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
                return;
            }

            tache.setNomt(newName);
            tache.setDescrption(newDescription);

            db.updateTache(tache, tache.getIdt());
            list.set(position, tache);
            adapter.notifyItemChanged(position);

            Toast.makeText(this, "Task updated successfully!", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }





}
