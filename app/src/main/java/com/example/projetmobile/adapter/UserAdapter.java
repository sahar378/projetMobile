package com.example.projetmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.R;
import com.example.projetmobile.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<User> list;

    // Constructeur
    public UserAdapter(List<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = list.get(position);

        // Définir les actions pour les boutons Edit et Delete
        View.OnClickListener editClickListener = v -> editUser(user, position);
        View.OnClickListener deleteClickListener = v -> deleteUser(user, position);

        // Appeler la méthode bind avec les écouteurs
        holder.bind(user, editClickListener, deleteClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Méthode pour modifier un utilisateur
    private void editUser(User user, int position) {
        // Implémentez ici la logique pour modifier l'utilisateur
        System.out.println("Edit User: " + user.getUsername());
    }

    // Méthode pour supprimer un utilisateur
    private void deleteUser(User user, int position) {
        // Supprimez l'utilisateur de la liste
        list.remove(position);
        notifyItemRemoved(position);
        System.out.println("Deleted User: " + user.getUsername());
    }
}
