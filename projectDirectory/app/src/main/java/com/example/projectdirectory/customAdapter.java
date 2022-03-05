package com.example.projectdirectory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Object> listemployee;

    private Context context;




    public customAdapter(ArrayList<Object> listemployee, Context context) {
        this.listemployee = listemployee;

        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case 0 :
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
                return  new ViewHolder(v);

            case 1 :
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
                return new ViewHolder2(v1);
            case 2 :
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.employeecheckbox,parent,false);
                return new ViewHolder3(v2);


            default:
    return null;
        }

    }
 String TAG ="test";
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

 switch (holder.getItemViewType())
 {

     case 0 :

         final employee  listemploye =(employee)listemployee.get(position);

         ((ViewHolder) holder).textView1.setText(listemploye.getName());
         ((ViewHolder) holder).textView2.setText(listemploye.getId());

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Toast.makeText(context, listemploye.getName(), Toast.LENGTH_SHORT).show();
             }
         });

         break;


     case 1 :
         final employeeImage listemployeeImage1 = (employeeImage) listemployee.get(position);
         ((ViewHolder2) holder).textView4.setText(listemployeeImage1.getName());
       ((ViewHolder2) holder).textView3.setText(listemployeeImage1.getId());
        ((ViewHolder2) holder).textView5.setText(listemployeeImage1.getDesignation());
        break;

     case 2 : {
         final employeecheckbox listemployeecheckbox = (employeecheckbox) listemployee.get(position);
         ((ViewHolder3) holder).textView6.setText(listemployeecheckbox.getName());
            if(listemployeecheckbox.ischeck){
                ((ViewHolder3) holder).chckbox.setChecked(true);
            }else {
                ((ViewHolder3) holder).chckbox.setChecked(false);
            }

         ((ViewHolder3) holder).chckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isPressed()) {
                     ((employeecheckbox) listemployee.get(position)).setIscheck(isChecked);
                     Log.i(TAG, "onCheckedChanged: " + isChecked);
               }
             }
         });
     }
//listemployeeImage1.getName()
 }


    }



    @Override
    public int getItemCount() {
       // int r = listemployee.length + listemployeeImage.length;
        return listemployee.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0)
        {
            return 0;
        }
        else  if (position % 3 == 1)
        {
            return 1;
        }
        else
        {
            return 2;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView1;
        public TextView textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 =itemView.findViewById(R.id.textView2);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder
    {

        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            textView3 = itemView.findViewById(R.id.item2Text1);
            textView4 =itemView.findViewById(R.id.item2Text2);
            textView5 =itemView.findViewById(R.id.item2Text3);
        }

    }
    public class ViewHolder3 extends RecyclerView.ViewHolder
    {

        public TextView textView6;
        public CheckBox chckbox;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            textView6 = itemView.findViewById(R.id.textView5);
            chckbox = itemView.findViewById(R.id.checkboxRecycleBT);
        }

    }

}
