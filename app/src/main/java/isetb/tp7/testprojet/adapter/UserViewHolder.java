package isetb.tp7.testprojet.adapter;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import isetb.tp7.testprojet.model.User;
import isetb.tp7.testprojet.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private ImageView userImage;
    private TextView firstName;
    private TextView lastName;

    private ImageButton btnEdit;  // Ajout de la variable pour le bouton Edit
    private ImageButton btnDelete; // Ajout de la variable pour le bouton Delete
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userImage = itemView.findViewById(R.id.user_image);
        firstName = itemView.findViewById(R.id.fn);
        lastName = itemView.findViewById(R.id.ln);
        btnEdit = itemView.findViewById(R.id.edit_icon);
        btnDelete = itemView.findViewById(R.id.delete_icon);
    }

    public void bind(User user, View.OnClickListener onEditClickListener,
                     View.OnClickListener onDeleteClickListener) {
        firstName.setText(user.getNom());
        lastName.setText(user.getPrenom());

        userImage.setOnClickListener(v -> showUserInfoDialog(user, v));
        // Ajout des listeners pour les boutons
        btnEdit.setOnClickListener(onEditClickListener);
        btnDelete.setOnClickListener(onDeleteClickListener);
    }



    private void showUserInfoDialog(User user, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        LayoutInflater inflater = LayoutInflater.from(view.getContext());
        View dialogView = inflater.inflate(R.layout.dialog_user_info, null);
        ImageView dialogImage = dialogView.findViewById(R.id.dialog_user_image);
        TextView dialogInfo = dialogView.findViewById(R.id.dialog_user_info);

        dialogInfo.setText("Name: " + user.getNom()
                + "\nPrenom: " + user.getPrenom()
                + "\nEmail: " + user.getEmail()
                + "\nUsername: " + user.getUsername()
                + "\nTelephone: " + user.getTelephone()
                + "\nCIN: " + user.getCin()
                + "\nDate Naissance: " + user.getDateNaissance()
                + "\nDate Debut Travail: " + user.getDateDebutTravail()
                + "\nPoste: " + user.getPoste()
                + "\nAdresse: " + user.getAdresseComplet());

        builder.setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }


}
