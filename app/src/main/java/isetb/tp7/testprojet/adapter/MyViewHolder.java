package isetb.tp7.testprojet.adapter;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import isetb.tp7.testprojet.R;
import isetb.tp7.testprojet.model.Tache;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView t1, t2;
    View editbtn, deletebtn;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = itemView.findViewById(R.id.n);
        t2 = itemView.findViewById(R.id.p);
        editbtn = itemView.findViewById(R.id.btnedit);
        deletebtn = itemView.findViewById(R.id.btndelete);
    }

    public void bind(Tache tache) {
        t1.setText(tache.getNomt());
        t2.setText(tache.getDescrption());
    }


}
