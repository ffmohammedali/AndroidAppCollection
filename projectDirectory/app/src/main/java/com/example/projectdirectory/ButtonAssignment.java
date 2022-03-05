package com.example.projectdirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.projectdirectory.R.id.fBT;

public class ButtonAssignment extends AppCompatActivity {
private ImageView img1;
private TextView txt1;
private Button btn1;
private RadioGroup rdroup1;
private CheckBox check1;
    private CheckBox check2;
    private CheckBox check3;
    private Spinner spinner1;
    private FloatingActionButton fButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_assignment);
        img1 =findViewById(R.id.img1);
        txt1 = findViewById(R.id.text1);
       btn1 = findViewById(R.id.BT1);
       rdroup1 =findViewById(R.id.radiogroup1);
       check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        fButton = findViewById(R.id.fBT);
spinner1 =findViewById(R.id.spinner1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setText("Image clicked");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setText("custom button clicked");
            }
        });

        rdroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.option1 :
                        txt1.setText("radio button clicked : 01");
                        break;
                    case R.id.option2 :

                        txt1.setText("radio button clicked : 02");
                        break;

                    case R.id.option3 :

                        txt1.setText("radio button clicked : 03");
                        break;


                }
            }
        });

        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check1.isChecked() ==true )
                {
                    txt1.setText("checkbox1");
                }
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check2.isChecked() ==true )
                {
                    txt1.setText("checkbox2");
                }
            }
        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check3.isChecked() ==true )
                {
                    txt1.setText("checkbox3");
                }
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] res = getResources().getStringArray(R.array.test);
                txt1.setText(res[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
fButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        txt1.setText("float button");
    }
});


    }
}
