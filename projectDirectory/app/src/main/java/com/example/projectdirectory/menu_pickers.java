package com.example.projectdirectory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class menu_pickers extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, PopupMenu.OnMenuItemClickListener {
    private Button bt1;
    private EditText dateBT;
    private EditText timepicker;
   private Button popupBt;
   private Button customDBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pickers);
        bt1 = findViewById(R.id.button1);
        dateBT = findViewById(R.id.dateBT2);

        timepicker = findViewById(R.id.timeBT3);
        popupBt = findViewById(R.id.popupBT);
        customDBT =findViewById(R.id.customdialogBT);
        customDBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        /////custom dialog //////

customDialog dialog =new customDialog();
dialog.show(getSupportFragmentManager(),"custom dialog");



            }
        });
        popupBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pm = new PopupMenu(menu_pickers.this,v);
                pm.setOnMenuItemClickListener(menu_pickers.this);
           pm.inflate(R.menu.menu_context);
           pm.show();
            }
        });
        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dp = new TimePickerFragment();
                dp.show(getSupportFragmentManager(),"Timepicker");


            }
        });
        dateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dp = new DatePickerFragment();
                dp.show(getSupportFragmentManager(),"datepicker");
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShowAlert(v);
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context,menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.itemfavorite :
                Toast.makeText(getApplicationContext(),"favorite",Toast.LENGTH_SHORT).show();

                break;
            case R.id.itemBlank :
                Toast.makeText(getApplicationContext(),"Blank item",Toast.LENGTH_SHORT).show();
                break;

            case R.id.iteminfo :
                Toast.makeText(getApplicationContext(),"info clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemShopping :
                Toast.makeText(getApplicationContext(),"cart mode on",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.MINUTE,minute);
        c2.set(Calendar.HOUR_OF_DAY,hourOfDay);
        timepicker.setText("Hour : "+ hourOfDay +" minute : "+ minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c =Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String dateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dateBT.setText(dateString);
    }

    public void onClickShowAlert(View view)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(menu_pickers.this);
        alertDialog.setTitle("Error 4004");
        alertDialog.setMessage("No Internet Connection");
        alertDialog.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        alertDialog.setNegativeButton("send report",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),"sending report... ",Toast.LENGTH_SHORT).show();
            }
        });


        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.context_edit :
                Toast.makeText(getApplicationContext(),"edit selected",Toast.LENGTH_LONG).show();

                break;

            case R.id.context_share :
                Toast.makeText(getApplicationContext(),"share selected",Toast.LENGTH_LONG).show();
                break;

        }
        return false;
    }
}
