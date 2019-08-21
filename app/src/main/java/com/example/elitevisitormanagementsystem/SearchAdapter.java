package com.example.elitevisitormanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<MyHolder> {
    private ArrayList<Search> searchList;
    private Context context;

    public SearchAdapter(ArrayList<Search> searchList) {

        this.searchList = searchList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        context = parent.getContext();
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final Search searchItem = searchList.get(position);
        holder.iv_logo.setImageDrawable(context.getDrawable(R.drawable.sampleimage));
        holder.tv_name.setText(searchItem.getTitle());
        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     Intent intent = new Intent(context, VisitiorInfoActivity.class);
                                                     intent.putExtra("title",searchItem.getTitle());
                                                     context.startActivity(intent);
                                                 }
                                             }
        );

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}


class MyHolder extends RecyclerView.ViewHolder {
    ImageView iv_logo;
    TextView tv_name;
    CardView cardLayout;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        iv_logo = itemView.findViewById(R.id.imageview_id);
        tv_name = itemView.findViewById(R.id.textview_id);
        cardLayout = itemView.findViewById(R.id.cardLayout);

    }
}