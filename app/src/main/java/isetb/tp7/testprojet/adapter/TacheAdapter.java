package isetb.tp7.testprojet.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import isetb.tp7.testprojet.R;
import isetb.tp7.testprojet.model.Tache;

public class TacheAdapter extends RecyclerView.Adapter<MyViewHolder>{
    public interface OnItemClickListener {
        void onEditClick(Tache tache, int position);

        void onDeleteClick(Tache tache, int position);
    }

    private final ArrayList<Tache> list;
    private final OnItemClickListener onItemClickListener;

    public TacheAdapter(ArrayList<Tache> list, OnItemClickListener onItemClickListener) {
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tache_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tache tache = list.get(position);
        holder.bind(tache);
        holder.editbtn.setOnClickListener(v -> onItemClickListener.onEditClick(tache, position));
        holder.deletebtn.setOnClickListener(v -> onItemClickListener.onDeleteClick(tache, position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
