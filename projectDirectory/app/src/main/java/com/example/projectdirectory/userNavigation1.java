package com.example.projectdirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userNavigation1 extends AppCompatActivity {
private Button buttonNav1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_navigation1);

        buttonNav1 =findViewById(R.id.nav1BT);
        buttonNav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),userNavigation1child.class);
                startActivity(intent);
            }
        });
    }
}
