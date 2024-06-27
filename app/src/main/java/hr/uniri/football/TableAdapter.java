package hr.uniri.football;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private List<Table> tables;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Table table);
    }

    public TableAdapter(List<Table> tables, OnItemClickListener onItemClickListener) {
        this.tables = tables;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Table table = tables.get(position);
        holder.bind(table, position + 1, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPosition;
        TextView textViewName;
        TextView textViewWins;
        TextView textViewLosses;
        TextView textViewDraws;
        TextView textViewPoints;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPosition = itemView.findViewById(R.id.txtPosition);
            textViewName = itemView.findViewById(R.id.txtName);
            textViewWins = itemView.findViewById(R.id.txtWins);
            textViewLosses = itemView.findViewById(R.id.txtLoses);
            textViewDraws = itemView.findViewById(R.id.txtDraws);
            textViewPoints = itemView.findViewById(R.id.txtPoints);
        }

        public void bind(Table table, int position, OnItemClickListener onItemClickListener) {
            textViewPosition.setText(String.valueOf(position));
            textViewName.setText(table.getName());
            textViewWins.setText(String.valueOf(table.getWins()));
            textViewLosses.setText(String.valueOf(table.getLosses()));
            textViewDraws.setText(String.valueOf(table.getDraws()));
            textViewPoints.setText(String.valueOf(table.getPoints()));

            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(table));
        }
    }
}
