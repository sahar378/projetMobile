package isetb.tp7.testprojet;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import isetb.tp7.testprojet.model.Tache;


public class AddTacheActivity extends AppCompatActivity{
    EditText taskName, taskDescription;
    Button addButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false); // Updated Edge-to-Edge
        setContentView(R.layout.activity_add_tache);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        taskName = findViewById(R.id.e1);
        taskDescription = findViewById(R.id.e2);
        addButton = findViewById(R.id.btn1);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = taskName.getText().toString();
                String description = taskDescription.getText().toString();

                if (name.isEmpty() || description.isEmpty()) {
                    taskName.setError("Nom requis");
                    taskDescription.setError("Desqcription requise");

                    return;
                }


                try {
                    Tache tache = new Tache(name, description);
                    TacheDatabase db = new TacheDatabase(AddTacheActivity.this);
                    if (db.addTache(tache)) {
                        finish(); // Close the activity after successful addition
                    } else {
                        taskName.setError("Insertion échoée");
                    }
                } catch (NumberFormatException ex) {
                    taskDescription.setError("Description échouée");
                }
            }
        });
    }

}
