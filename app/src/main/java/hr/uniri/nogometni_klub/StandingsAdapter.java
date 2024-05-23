package hr.uniri.nogometni_klub;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {

    private Context context;
    private ArrayList<Standings> standingsList;

    StandingsAdapter(Context context, ArrayList<Standings> standingsList) {
        this.context = context;
        this.standingsList = standingsList;
    }

    @NonNull
    @Override
    public StandingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.standings_item, parent, false);
        return new StandingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsViewHolder holder, int position) {
        Standings standings = standingsList.get(position);
        holder.imeKluba.setText(standings.getImeKluba());
        holder.rank.setText(String.valueOf(standings.getRank()));
        holder.bodovi.setText(String.valueOf(standings.getBodovi()));
        Log.d("THIS", String.valueOf(standings.getRank()));
    }

    @Override
    public int getItemCount() {
        return standingsList.size();
    }

    class StandingsViewHolder extends RecyclerView.ViewHolder {

        TextView imeKluba, bodovi, rank;

        StandingsViewHolder(@NonNull View itemView) {
            super(itemView);
            rank =itemView.findViewById(R.id.rank);
            imeKluba = itemView.findViewById(R.id.ime_kluba);
            bodovi = itemView.findViewById(R.id.bodovi);
        }
    }
}
