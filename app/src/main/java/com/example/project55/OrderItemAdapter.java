package com.example.project55;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderViewHolder> {

    private final List<MenuItem> items;
    private final Context context;
    private final Runnable onItemRemoved;

    public OrderItemAdapter(Context context, List<MenuItem> items, Runnable onItemRemoved) {
        this.context = context;
        this.items = items;
        this.onItemRemoved = onItemRemoved;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.itemText.setText(item.toString());

        holder.removeButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                MenuItem removedItem = items.get(adapterPosition);

                items.remove(adapterPosition);   // Remove from the adapter's list
                OrderManager.getInstance().getCurrentOrder().removeItem(removedItem); // Remove from OrderManager

                notifyItemRemoved(adapterPosition);
                onItemRemoved.run();   // Update totals
                Toast.makeText(context, "Item removed from order", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        Button removeButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.orderItemText);
            removeButton = itemView.findViewById(R.id.removeItemButton);
        }
    }
}
