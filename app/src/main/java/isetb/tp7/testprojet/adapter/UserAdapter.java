package isetb.tp7.testprojet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import isetb.tp7.testprojet.R;
import isetb.tp7.testprojet.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    List<User> list;
    private OnUserActionListener actionListener;



    public UserAdapter(List<User> list , OnUserActionListener actionListener) {
        this.list = list;
        this.actionListener = actionListener;


    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user= list.get(position);
        holder.bind(user, v -> actionListener.onEditUser(user), v -> actionListener.onDeleteUser(user));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface OnUserActionListener {
        void onEditUser(User user);
        void onDeleteUser(User user);
    }
}
