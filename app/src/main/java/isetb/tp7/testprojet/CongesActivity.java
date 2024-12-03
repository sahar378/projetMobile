package isetb.tp7.testprojet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CongesActivity extends AppCompatActivity {
    private EditText editTextDate, editTextDuree, editTextTitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conges);


        editTextDate = findViewById(R.id.editTextDate);
        editTextDuree = findViewById(R.id.editTextDuree);
        editTextTitre = findViewById(R.id.editTextTitre);

    }
    public void enregistrerDonnees(View view) {
        String date = editTextDate.getText().toString().trim();
        String duree = editTextDuree.getText().toString().trim();
        String titre = editTextTitre.getText().toString().trim();

        if (date.isEmpty() || duree.isEmpty() || titre.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("CongesPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("date", date);
        editor.putString("duree", duree);
        editor.putString("titre", titre);

        editor.apply();

        // Log the saved data
        Log.d("CongesActivity", "Données enregistrées: Date=" + date + ", Durée=" + duree + ", Titre=" + titre);

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();

        // Return to MainActivity
        finish();
    }
}