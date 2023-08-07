package com.example.steptofitapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TrainingModel> modelArrayList;

    public TrainingAdapter(Context context, ArrayList<TrainingModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.training_item_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTrainingMuscle.setText(modelArrayList.get(position).getName());
        holder.imgTraining.setImageResource(modelArrayList.get(position).getImg());
        int index=position;
        holder.llTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,TrainingMuscleActivity.class);
                intent.putExtra("muscle_name",modelArrayList.get(index).getName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTrainingMuscle;
        ImageView imgTraining;
        LinearLayout llTraining;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTrainingMuscle=itemView.findViewById(R.id.txtTrainingMuscle);
            imgTraining=itemView.findViewById(R.id.imgTraining);
            llTraining=itemView.findViewById(R.id.llTraining);
        }
    }
}
