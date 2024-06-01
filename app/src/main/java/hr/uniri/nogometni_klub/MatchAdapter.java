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

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> match_ID, domaci_klub, gost_klub,rezultat;

    public MatchAdapter(Activity activity, Context context, ArrayList<String> match_ID, ArrayList<String> domaci_klub,
                         ArrayList<String> gost_klub, ArrayList<String> rezultat) {
        this.activity = activity;
        this.context = context;
        this.match_ID = match_ID;
        this.domaci_klub = domaci_klub;
        this.gost_klub =gost_klub;
        this.rezultat = rezultat;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_match_table, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.match_ID_txt.setText(match_ID.get(position));
        holder.domaci_klub_txt.setText(domaci_klub.get(position));
        holder.gost_klub_txt.setText(gost_klub.get(position));
        holder.rezultat_txt.setText(rezultat.get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, UpdateMatchActivity.class);
                    intent.putExtra("id", match_ID.get(currentPosition));
                    intent.putExtra("domacin", domaci_klub.get(currentPosition));
                    intent.putExtra("gost", gost_klub.get(currentPosition));
                    intent.putExtra("rez", rezultat.get(currentPosition));
                    activity.startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return match_ID.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView match_ID_txt, domaci_klub_txt, gost_klub_txt, rezultat_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            match_ID_txt = itemView.findViewById(R.id.match_ID_txt);
            domaci_klub_txt = itemView.findViewById(R.id.domaci_klub_txt);
            gost_klub_txt = itemView.findViewById(R.id.gost_klub_txt);
            rezultat_txt = itemView.findViewById(R.id.rezultat_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            // Animate RecyclerView
            Animation translate_anim = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
