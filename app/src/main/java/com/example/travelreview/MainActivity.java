package com.example.travelreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.travelreview.Adapter.DataAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        ArrayList<ItemTravel> arrayList = new ArrayList<>();
        arrayList.add(new ItemTravel(R.drawable.causonghan,"Cầu sông Hàn"));
        arrayList.add(new ItemTravel(R.drawable.caurong,"Cầu Rồng"));
        arrayList.add(new ItemTravel(R.drawable.cautinhyeu,"Cầu tình yêu"));
        arrayList.add(new ItemTravel(R.drawable.cauvang,"Cầu vàng"));
        arrayList.add(new ItemTravel(R.drawable.cocobay,"Cocobay"));
        DataAdapter adapter = new DataAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
