package com.example.projectdirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectdirectory.AwesomeTMDB.AwesomeTMDB;
import com.example.projectdirectory.MusicPlayer.MusicPlayer;
import com.example.projectdirectory.async_task.SimpleAsyncTaskActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button assignment191119BT;
    private Button dataSaveAssignmentBT;
    private Button transitionNavigationBT;
    private Button textViewScrollView;
    private Button idCard;
    private Button imageView;

    private Button buttonAssgn;
    private Button menupickerBT;

    private Button navigation1;
    private Button navigation2;
    private Button recycleview1;
    private Button recycleview2;
    private Button drawablestyleBT;
    private Button nineBT;
    private Button async;
    private Button awesomeBT;
    private Button musicpalyerBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicpalyerBT =findViewById(R.id.musicPlayerBT);
        musicpalyerBT.setOnClickListener(this);

        awesomeBT =findViewById(R.id.apiTestBT);
        awesomeBT.setOnClickListener(this);

        async =findViewById(R.id.AsyncBT);
        async.setOnClickListener(this);
        nineBT = findViewById(R.id.ninepatchBT);
        nineBT.setOnClickListener(this);

        drawablestyleBT = findViewById(R.id.drawableStyleBT);
        drawablestyleBT.setOnClickListener(this);

        recycleview1 =findViewById(R.id.recycleview1BT);
        recycleview1.setOnClickListener(this);

        recycleview2 = findViewById(R.id.recycleview2BT);
        recycleview2.setOnClickListener(this);

        navigation2 =findViewById(R.id.navigationAssignment2);
        navigation2.setOnClickListener(this);

        navigation1 =findViewById(R.id.navigationAssignment1);
        navigation1.setOnClickListener(this);

        assignment191119BT = findViewById(R.id.assignment191119BT);
        assignment191119BT.setOnClickListener(this);

        dataSaveAssignmentBT =findViewById(R.id.dataSaveAssignmentBT);
        dataSaveAssignmentBT.setOnClickListener(this);

        transitionNavigationBT =findViewById(R.id.transitionNavigationBT);
        transitionNavigationBT.setOnClickListener(this);
        menupickerBT = findViewById(R.id.menupickersBT);
        menupickerBT.setOnClickListener(this);

        textViewScrollView = findViewById(R.id.textViewScrollViewBT);
        textViewScrollView.setOnClickListener(this);

        idCard = findViewById(R.id.idcardBT);
        idCard.setOnClickListener(this);

        imageView = findViewById(R.id.imageViewBT);
        imageView.setOnClickListener(this);
        buttonAssgn= findViewById(R.id.buttonAssignmentBT);
        buttonAssgn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == assignment191119BT.getId()){
            Intent intent = new Intent(this, implicitIntentAssignment.class);
            startActivity(intent);
        }
       else if (v.getId() == dataSaveAssignmentBT.getId()){
            Intent intent = new Intent(this, assignment_datasave.class);
            startActivity(intent);
        }
        else if (v.getId() == transitionNavigationBT.getId()){
            Intent intent = new Intent(this,transitionNavigation.class);
            startActivity(intent);
        }
        else if (v.getId() == textViewScrollView.getId()){
            Intent intent = new Intent(this,textViewScrollView.class);
            startActivity(intent);
        }
        else if (v.getId() == idCard.getId()){
            Intent intent = new Intent(this,idCardViewer.class);
            startActivity(intent);
        }
        else if (v.getId() == imageView.getId()){
            Intent intent = new Intent(this,imageViewer.class);
            startActivity(intent);
        }
        else if (v.getId() == buttonAssgn.getId()){
            Intent intent = new Intent(this,ButtonAssignment.class);
            startActivity(intent);
        }
        else if (v.getId() == menupickerBT.getId()){
            Intent intent = new Intent(this,menu_pickers.class);
            startActivity(intent);
        }
        else if (v.getId() == navigation1.getId()){
            Intent intent = new Intent(this,userNavigation1.class);
            startActivity(intent);
        }
        else if (v.getId() == navigation2.getId()){
            Intent intent = new Intent(this,navigation2.class);
            startActivity(intent);
        }
        else if (v.getId() == recycleview1.getId()){
            Intent intent = new Intent(this,recycleView.class);
            startActivity(intent);
        }
        else if (v.getId() == recycleview2.getId()){
            Intent intent = new Intent(this,recycleview2.class);
            startActivity(intent);
        }
        else if (v.getId() == drawablestyleBT.getId()){
            Intent intent = new Intent(this,drawable_style_theme.class);
            startActivity(intent);
        }
        else if (v.getId() == nineBT.getId()){
            Intent intent = new Intent(this,ninepatch.class);
            startActivity(intent);
        }
        else if (v.getId() == async.getId()){
            Intent intent = new Intent(this, SimpleAsyncTaskActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == awesomeBT.getId()){
            Intent intent = new Intent(this, AwesomeTMDB.class);
            startActivity(intent);
        }
        else if (v.getId() == musicpalyerBT.getId()){
            Intent intent = new Intent(this, MusicPlayer.class);
            startActivity(intent);
        }

    }
}
