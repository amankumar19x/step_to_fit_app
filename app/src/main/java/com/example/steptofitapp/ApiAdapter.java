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

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ApiModel> modelArrayList;

    public ApiAdapter(Context context, ArrayList<ApiModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.api_data_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtExerciseName.setText(modelArrayList.get(position).getName());
        holder.txtExerciseEquipment.setText(modelArrayList.get(position).getEquipment());
        holder.imgExercise.setImageResource(modelArrayList.get(position).getImg());
        int index=position;
        holder.llExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,ExerciseTutorialActivity.class);
                intent.putExtra("name",modelArrayList.get(index).getName());
                intent.putExtra("type",modelArrayList.get(index).getType());
                intent.putExtra("muscle",modelArrayList.get(index).getMuscle());
                intent.putExtra("equipment",modelArrayList.get(index).getEquipment());
                intent.putExtra("difficulty",modelArrayList.get(index).getDifficulty());
                intent.putExtra("instructions",modelArrayList.get(index).getInstructions());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgExercise;
        TextView txtExerciseName;
        TextView txtExerciseEquipment;
        LinearLayout llExercise;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgExercise=itemView.findViewById(R.id.imgExercise);
            txtExerciseName=itemView.findViewById(R.id.txtExerciseName);
            txtExerciseEquipment=itemView.findViewById(R.id.txtExerciseEquipment);
            llExercise=itemView.findViewById(R.id.llExercise);
        }
    }
}
