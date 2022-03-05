package com.example.projectdirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class recycleView extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        recyclerView =findViewById(R.id.recycleview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        employee [] employees = new employee[100];

        for (int c = 0 ; c< 100 ; c++)
        {
            employee x = new employee();
            x.name = "name:  employee " + c ;
            x.id = "id: 654536" + c ;
            employees[c]= x;
        }
        customadaptersinglerecycle adapter = new customadaptersinglerecycle(employees,this);
        recyclerView.setAdapter(adapter);





    }
}
