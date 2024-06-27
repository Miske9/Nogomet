package hr.uniri.football;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> players;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Player player);
    }

    public PlayerAdapter(List<Player> players, OnItemClickListener onItemClickListener) {
        this.players = players;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.nameTextView.setText(player.getName());
        holder.surnameTextView.setText(player.getSurname());
        holder.categoryTextView.setText(player.getCategory());
        holder.positionTextView.setText(player.getPosition());
        holder.numberTextView.setText(String.valueOf(player.getNumber()));
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(player));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView surnameTextView;
        TextView categoryTextView;
        TextView positionTextView;
        TextView numberTextView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            surnameTextView = itemView.findViewById(R.id.textViewSurname);
            categoryTextView = itemView.findViewById(R.id.textViewCategory);
            positionTextView = itemView.findViewById(R.id.textViewPosition);
            numberTextView = itemView.findViewById(R.id.textViewNumber);
        }
    }
}
