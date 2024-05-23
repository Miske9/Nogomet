package hr.uniri.nogometni_klub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> player_ID, ime_igraca, prezime_igraca, godine_igraca, pozicija_igraca;

    public PlayerAdapter(Activity activity, Context context, ArrayList<String> player_ID, ArrayList<String> ime_igraca,
                         ArrayList<String> prezime_igraca, ArrayList<String> godine_igraca, ArrayList<String> pozicija_igraca) {
        this.activity = activity;
        this.context = context;
        this.player_ID = player_ID;
        this.ime_igraca = ime_igraca;
        this.prezime_igraca = prezime_igraca;
        this.godine_igraca = godine_igraca;
        this.pozicija_igraca = pozicija_igraca;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.table, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.player_ID_txt.setText(player_ID.get(position));
        holder.ime_igraca_txt.setText(ime_igraca.get(position));
        holder.prezime_igraca_txt.setText(prezime_igraca.get(position));
        holder.godine_igraca_txt.setText(godine_igraca.get(position));
        holder.pozicija_igraca_txt.setText(pozicija_igraca.get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("id", player_ID.get(currentPosition));
                    intent.putExtra("ime", ime_igraca.get(currentPosition));
                    intent.putExtra("prezime", prezime_igraca.get(currentPosition));
                    intent.putExtra("godine", godine_igraca.get(currentPosition));
                    intent.putExtra("pozicija", pozicija_igraca.get(currentPosition));
                    activity.startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return player_ID.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView player_ID_txt, ime_igraca_txt, prezime_igraca_txt, godine_igraca_txt, pozicija_igraca_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            player_ID_txt = itemView.findViewById(R.id.player_ID_txt);
            ime_igraca_txt = itemView.findViewById(R.id.ime_igraca_txt);
            prezime_igraca_txt = itemView.findViewById(R.id.prezime_igraca_txt);
            godine_igraca_txt = itemView.findViewById(R.id.godine_igraca_txt);
            pozicija_igraca_txt = itemView.findViewById(R.id.pozicija_igraca_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            Animation translate_anim = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
