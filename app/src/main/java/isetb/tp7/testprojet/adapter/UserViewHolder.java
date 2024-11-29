package isetb.tp7.testprojet.adapter;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import isetb.tp7.testprojet.model.User;
import isetb.tp7.testprojet.R;

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
    }

    public void bind(User user) {
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
        }

    }
}
