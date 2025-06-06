package com.example.project55;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FlavorAdapter extends RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder> {

    private final List<Flavor> flavors;
    private final OnFlavorClickListener listener;

    public interface OnFlavorClickListener {
        void onFlavorClick(Flavor flavor);
    }

    public FlavorAdapter(List<Flavor> flavors, OnFlavorClickListener listener) {
        this.flavors = flavors;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FlavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flavor, parent, false);
        return new FlavorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlavorViewHolder holder, int position) {
        Flavor flavor = flavors.get(position);
        holder.bind(flavor);
    }

    @Override
    public int getItemCount() {
        return flavors.size();
    }

    class FlavorViewHolder extends RecyclerView.ViewHolder {

        ImageView flavorImage;
        TextView flavorName;

        FlavorViewHolder(View itemView) {
            super(itemView);
            flavorImage = itemView.findViewById(R.id.flavorImage);
            flavorName = itemView.findViewById(R.id.flavorName);
        }

        void bind(Flavor flavor) {
            flavorName.setText(formatFlavorName(flavor.name()));

            // Set appropriate image depending on flavor
            switch (flavor) {
                case COLA:
                    flavorImage.setImageResource(R.drawable.cola);
                    break;
                case TEA:
                    flavorImage.setImageResource(R.drawable.tea);
                    break;
                case JUICE:
                    flavorImage.setImageResource(R.drawable.juice);
                    break;
                case STRAWBERRY_LEMONADE:
                    flavorImage.setImageResource(R.drawable.strawberry_lemonade);
                    break;
                case ORANGE:
                    flavorImage.setImageResource(R.drawable.orange);
                    break;
                case PEACH:
                    flavorImage.setImageResource(R.drawable.peach);
                    break;
                case MANGO:
                    flavorImage.setImageResource(R.drawable.mango);
                    break;
                case RASPBERRY:
                    flavorImage.setImageResource(R.drawable.raspberry);
                    break;
                case CHERRY:
                    flavorImage.setImageResource(R.drawable.cherry);
                    break;
                case LEMONADE:
                    flavorImage.setImageResource(R.drawable.lemonade);
                    break;
                case LIME:
                    flavorImage.setImageResource(R.drawable.lime);
                    break;
                case MANGO_LEMONADE:
                    flavorImage.setImageResource(R.drawable.mango_lemonade);
                    break;
                case PEACH_TEA:
                    flavorImage.setImageResource(R.drawable.peach_tea);
                    break;
                case RASPBERRY_TEA:
                    flavorImage.setImageResource(R.drawable.raspberry_tea);
                    break;
                case PINEAPPLE:
                    flavorImage.setImageResource(R.drawable.pineapple);
                    break;
            }

            itemView.setOnClickListener(v -> {
                listener.onFlavorClick(flavor);

                // 🛎️ Add Toast popup here
                Toast.makeText(
                        itemView.getContext(),
                        formatFlavorName(flavor.name()) + " selected",
                        Toast.LENGTH_SHORT
                ).show();
            });
        }

        // Helper method to format names nicely
        private String formatFlavorName(String rawName) {
            String formatted = rawName.replace("_", " ").toLowerCase();
            return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
        }
    }
}
