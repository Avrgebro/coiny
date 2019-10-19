package com.arsenic.coiny.Activities.More;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.arsenic.coiny.R;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.util.ArrayList;
import java.util.Date;

public class Transactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        ImageView upnav = (ImageView) findViewById(R.id.trans_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Create Timeline rows List
        ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();

        TimelineRow r1 = new TimelineRow(0);
        r1.setTitle("S/500 - 14/10/2019");
        r1.setDescription("Pago a Juan");
        r1.setBellowLineColor(Color.argb(255, 97, 86, 178));
        r1.setBellowLineSize(4);
        r1.setBackgroundColor(Color.argb(255, 97, 86, 178));
        r1.setBackgroundSize(25);
        r1.setDateColor(Color.argb(255, 0, 0, 0));
        r1.setTitleColor(Color.argb(255, 200, 0, 0));
        r1.setDescriptionColor(Color.argb(255, 200, 0, 0));
        timelineRowsList.add(r1);

        TimelineRow r2 = new TimelineRow(0);
        r2.setTitle("S/35 - 10/10/2019");
        r2.setDescription("Pago recibido de Arturo");
        r2.setBellowLineColor(Color.argb(255, 97, 86, 178));
        r2.setBellowLineSize(4);
        r2.setBackgroundColor(Color.argb(255, 97, 86, 178));
        r2.setBackgroundSize(25);
        r2.setDateColor(Color.argb(255, 0, 0, 0));
        r2.setTitleColor(Color.argb(255, 0, 0, 0));
        r2.setDescriptionColor(Color.argb(255, 0, 0, 0));
        timelineRowsList.add(r2);

        TimelineRow r3 = new TimelineRow(0);
        r3.setTitle("S/35 - 06/10/2019");
        r3.setDescription("Pago recibido de Josselyn");
        r3.setBellowLineColor(Color.argb(255, 97, 86, 178));
        r3.setBellowLineSize(4);
        r3.setBackgroundColor(Color.argb(255, 97, 86, 178));
        r3.setBackgroundSize(25);
        r3.setDateColor(Color.argb(255, 0, 0, 0));
        r3.setTitleColor(Color.argb(255, 0, 0, 0));
        r3.setDescriptionColor(Color.argb(255, 0, 0, 0));
        timelineRowsList.add(r3);

        TimelineRow r4 = new TimelineRow(0);
        r4.setTitle("S/125 - 28/09/2019");
        r4.setDescription("Pago de Servicio Luz");
        r4.setBellowLineColor(Color.argb(255, 97, 86, 178));
        r4.setBellowLineSize(4);
        r4.setBackgroundColor(Color.argb(255, 97, 86, 178));
        r4.setBackgroundSize(25);
        r4.setDateColor(Color.argb(255, 0, 0, 0));
        r4.setTitleColor(Color.argb(255, 200, 0, 0));
        r4.setDescriptionColor(Color.argb(255, 200, 0, 0));
        timelineRowsList.add(r4);

        ArrayAdapter<TimelineRow> myAdapter = new TimelineViewAdapter(this, 0, timelineRowsList,
                //if true, list will be sorted by date
                false);

// Get the ListView and Bind it with the Timeline Adapter
        ListView myListView = (ListView) findViewById(R.id.timeline_listView);
        myListView.setAdapter(myAdapter);
    }
}
