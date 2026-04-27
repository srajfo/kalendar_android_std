package com.example.kalendar_viewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    TextView txtMonth, btnPrev, btnNext;
    RecyclerView recyclerDays;
    DayListAdapter adapter;
    ArrayList<CalendarDay> dayList = new ArrayList<>();

    int currentYear, currentMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtMonth = findViewById(R.id.txtMonth);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        recyclerDays = findViewById(R.id.recyclerDays);

        // 🔥 ADAPTER SADA PRIMA CONTEXT
        adapter = new DayListAdapter(dayList, this);
        recyclerDays.setLayoutManager(new LinearLayoutManager(this));
        recyclerDays.setAdapter(adapter);

        Calendar cal = Calendar.getInstance();
        currentYear = cal.get(Calendar.YEAR);
        currentMonth = cal.get(Calendar.MONTH) + 1;

        updateMonthTitle();
        loadDays(currentYear, currentMonth);

        btnPrev.setOnClickListener(v -> {
            currentMonth--;
            if (currentMonth < 1) {
                currentMonth = 12;
                currentYear--;
            }
            updateMonthTitle();
            loadDays(currentYear, currentMonth);
        });

        btnNext.setOnClickListener(v -> {
            currentMonth++;
            if (currentMonth > 12) {
                currentMonth = 1;
                currentYear++;
            }
            updateMonthTitle();
            loadDays(currentYear, currentMonth);
        });
    }

    private void updateMonthTitle() {
        String[] months = {
                "Siječanj","Veljača","Ožujak","Travanj","Svibanj","Lipanj",
                "Srpanj","Kolovoz","Rujan","Listopad","Studeni","Prosinac"
        };
        txtMonth.setText(months[currentMonth - 1] + " " + currentYear);
    }

    private void loadDays(int year, int month) {

        String url = "http://10.0.2.2/kalendar/api_calendar_days.php?year="
                + year + "&month=" + month;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray daysArray = response.getJSONArray("days");
                        dayList.clear();

                        for (int i = 0; i < daysArray.length(); i++) {
                            JSONObject obj = daysArray.getJSONObject(i);

                            dayList.add(new CalendarDay(
                                    obj.getInt("day"),
                                    obj.getString("date"),
                                    obj.getInt("events")
                            ));
                        }

                        adapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace()
        );

        Volley.newRequestQueue(this).add(request);
    }

    // 🔥 FUNKCIJA KOJU ZOVE ADAPTER KADA KLIKNEŠ NA DAN
    public void openEventsPopup(String date) {
        EventsBottomSheet sheet = EventsBottomSheet.newInstance(date);
        sheet.show(getSupportFragmentManager(), "events_sheet");
    }
}
