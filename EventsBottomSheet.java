package com.example.kalendar_viewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventsBottomSheet extends BottomSheetDialogFragment {

    private static final String ARG_DATE = "date";
    private String date;

    RecyclerView recycler;
    EventsAdapter adapter;
    ArrayList<EventItem> list = new ArrayList<>();

    public static EventsBottomSheet newInstance(String date) {
        EventsBottomSheet sheet = new EventsBottomSheet();
        Bundle b = new Bundle();
        b.putString(ARG_DATE, date);
        sheet.setArguments(b);
        return sheet;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = getArguments().getString(ARG_DATE);

        recycler = view.findViewById(R.id.recyclerEvents);
        adapter = new EventsAdapter(list, getContext());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        loadEvents();
    }

    private void loadEvents() {
        String url = "http://10.0.2.2/kalendar/api_events_by_day.php?date=" + date;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray arr = response.getJSONArray("events");
                        list.clear();

                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject o = arr.getJSONObject(i);

                            list.add(new EventItem(
                                    o.getString("title"),
                                    o.getString("description"),
                                    o.getString("start"),
                                    o.getString("end"),
                                    o.getString("created_by")
                            ));
                        }

                        adapter.notifyDataSetChanged();

                    } catch (Exception e) { e.printStackTrace(); }
                },
                error -> error.printStackTrace()
        );

        Volley.newRequestQueue(getContext()).add(req);
    }
}
