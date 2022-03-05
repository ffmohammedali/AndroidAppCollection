
package com.example.projectdirectory;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

public class customadaptersinglerecycle extends RecyclerView.Adapter<customadaptersinglerecycle.ViewHolder> {

    public customadaptersinglerecycle(employee[] listemployee,Context context) {
        this.listemployee = listemployee;
        this.context = context;
    }

    private employee [] listemployee;
    private Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return  new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final employee  listemploye =listemployee[position];
        holder.textView1.setText(listemploye.getName());
        holder.textView2.setText(listemploye.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listemploye.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public int getItemCount() {
        return listemployee.length;
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
}