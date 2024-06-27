package hr.uniri.football;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {
    private List<Match> matches;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Match match);
    }

    public MatchAdapter(List<Match> matches, OnItemClickListener onItemClickListener) {
        this.matches = matches;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matches.get(position);
        holder.homeTeamTextView.setText(match.getHomeTeam());
        holder.awayTeamTextView.setText(match.getAwayTeam());
        holder.scoreHomeTextView.setText(match.getHomeScore() != null ? match.getHomeScore() : "0");
        holder.scoreAwayTextView.setText(match.getAwayScore() != null ? match.getAwayScore() : "0");
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(match));
    }


    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView homeTeamTextView;
        TextView awayTeamTextView;
        TextView scoreHomeTextView;
        TextView scoreAwayTextView;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeamTextView = itemView.findViewById(R.id.textViewHomeTeam);
            awayTeamTextView = itemView.findViewById(R.id.textViewAwayTeam);
            scoreHomeTextView = itemView.findViewById(R.id.textViewHomeScore);
            scoreAwayTextView = itemView.findViewById(R.id.textViewAwayScore);
        }
    }
}
