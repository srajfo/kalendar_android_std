package com.example.kalendar_viewer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventHolder> {

    ArrayList<EventItem> list;
    Context ctx;

    public EventsAdapter(ArrayList<EventItem> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_event, parent, false);
        return new EventHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        EventItem e = list.get(position);

        // Naslov
        holder.txtTitle.setText(e.title);

        // Vrijeme (formatirano)
        holder.txtTime.setText(formatTime(e.start) + " - " + formatTime(e.end));

        // Opis
        holder.txtDesc.setText(e.description);

        // Boja pozadine po korisniku
        if (e.created_by.equalsIgnoreCase("Rafael")) {
            holder.container.setBackgroundColor(Color.parseColor("#4A90E2")); // plavo
        } else {
            holder.container.setBackgroundColor(Color.parseColor("#FF80C0")); // rozo
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class EventHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtTime, txtDesc;
        View container;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.eventContainer);
            txtTitle = itemView.findViewById(R.id.txtEventTitle);
            txtTime = itemView.findViewById(R.id.txtEventTime);
            txtDesc = itemView.findViewById(R.id.txtEventDesc);
        }
    }

    private String formatTime(String dateTime) {
        try {
            SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            SimpleDateFormat out = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return out.format(in.parse(dateTime));
        } catch (Exception e) {
            return dateTime;
        }
    }
}
