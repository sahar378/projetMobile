package com.example.projetmobile.adapter;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.R;
import com.example.projetmobile.model.User;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
    private TextView firstName;
    private TextView lastName;
    private TextView email;
    private TextView username;
    private TextView password;
    private TextView tel;
    private TextView cin;
    private TextView birthDate;
    private TextView startWork;
    private TextView poste;
    private TextView adress;
    private Button btnEdit;  // Ajout de la variable pour le bouton Edit
    private Button btnDelete; // Ajout de la variable pour le bouton Delete

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        firstName = itemView.findViewById(R.id.fn);
        lastName = itemView.findViewById(R.id.ln);
        email = itemView.findViewById(R.id.email);
        username = itemView.findViewById(R.id.username);
        password = itemView.findViewById(R.id.pwd);
        tel = itemView.findViewById(R.id.tel);
        cin = itemView.findViewById(R.id.cin);
        birthDate = itemView.findViewById(R.id.birthDate);
        startWork = itemView.findViewById(R.id.startWork);
        poste = itemView.findViewById(R.id.poste);
        adress = itemView.findViewById(R.id.adress);
        btnEdit = itemView.findViewById(R.id.btn_edit); // Récupération du bouton Edit
        btnDelete = itemView.findViewById(R.id.btn_delete); // Récupération du bouton Delete
    }

    public void bind(User user, View.OnClickListener onEditClickListener, View.OnClickListener onDeleteClickListener) {
        if (user != null) {
            firstName.setText(user.getNom() != null ? user.getNom() : "N/A");
            lastName.setText(user.getPrenom() != null ? user.getPrenom() : "N/A");
            email.setText(user.getEmail() != null ? user.getEmail() : "N/A");
            username.setText(user.getUsername() != null ? user.getUsername() : "N/A");
            password.setText(user.getPassword() != null ? user.getPassword() : "N/A");
            tel.setText(user.getTelephone() != null ? user.getTelephone() : "N/A");
            cin.setText(user.getCin() != null ? user.getCin() : "N/A");
            birthDate.setText(user.getDateNaissance() != null ? user.getDateNaissance().toString() : "N/A");
            startWork.setText(user.getDateDebutTravail() != null ? user.getDateDebutTravail().toString() : "N/A");
            poste.setText(user.getPoste() != null ? user.getPoste() : "N/A");
            adress.setText(user.getAdresseComplet() != null ? user.getAdresseComplet() : "N/A");

            // Ajout des listeners pour les boutons
            btnEdit.setOnClickListener(onEditClickListener);
            btnDelete.setOnClickListener(onDeleteClickListener);
        }
    }
}
