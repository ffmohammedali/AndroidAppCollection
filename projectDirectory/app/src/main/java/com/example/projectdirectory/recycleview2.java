package com.example.projectdirectory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;


public class recycleview2 extends AppCompatActivity {
    RecyclerView recyclerView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview2);
        recyclerView2 =findViewById(R.id.recycleview2);
        System.out.println("crush");
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
       // Object [] employees = new Object[100];
        ArrayList<Object> employees =new ArrayList<Object>(100);

        for (int c = 0 ; c< 100 ; c++)
        {
            if(c % 3 ==0)
            {
                employee x = new employee();
                x.name = "name:  employee " + c ;
                x.id = "id: 654536" + c ;
                employees.add(x);
           //     employees[c]= x;
            }else if(c % 3 ==1)
            {
                employeeImage y =new employeeImage("EMPLOYEE","I.D.","DEVELOPER");
                employees.add(y);
                //  employees[c]=y;

            }
            else  if(c % 3 ==2)
            {
                employeecheckbox z =new employeecheckbox(0,"checkbox od"+c);
                employees.add(z);
                //  employees[c] =z;

            }
           System.out.println( employees.get(c).toString());

        }

        customAdapter adapter = new customAdapter(employees,this);
        recyclerView2.setAdapter(adapter);





    }
}
