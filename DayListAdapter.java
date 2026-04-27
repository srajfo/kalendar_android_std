package com.example.kalendar_viewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.DayHolder> {

    ArrayList<CalendarDay> days;
    Context ctx;

    public DayListAdapter(ArrayList<CalendarDay> days, Context ctx) {
        this.days = days;
        this.ctx = ctx;
    }

    @Override
    public DayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false);
        return new DayHolder(v);
    }

    @Override
    public void onBindViewHolder(DayHolder holder, int position) {
        CalendarDay d = days.get(position);

        holder.txtDate.setText(formatDate(d.date));
        holder.txtDayName.setText(getDayName(d.date));

        if (d.events > 0)
            holder.txtEvents.setText("Događaja: " + d.events);
        else
            holder.txtEvents.setText("Nema događaja");

        // 🔥 Klik na dan → otvori popup s događajima
        holder.itemView.setOnClickListener(v -> {
            if (ctx instanceof SecondActivity) {
                ((SecondActivity) ctx).openEventsPopup(d.date);
            }
        });
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    class DayHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtDayName, txtEvents;

        public DayHolder(View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtDayName = itemView.findViewById(R.id.txtDayName);
            txtEvents = itemView.findViewById(R.id.txtEvents);
        }
    }

    private String getDayName(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = sdf.parse(dateStr);

            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", new Locale("hr"));
            return dayFormat.format(date);

        } catch (Exception e) {
            return "";
        }
    }

    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat output = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            return output.format(input.parse(dateStr));
        } catch (Exception e) {
            return dateStr;
        }
    }
}
