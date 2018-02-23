package com.example.japjot.mdbsocials;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by japjot on 2/22/18.
 */

public class Feedadapter extends RecyclerView.Adapter<Feedadapter.CustomViewHolder>{

    private Context context;
    private ArrayList<Eventsclass.Event> events;

    public Feedadapter(Context context, ArrayList<Eventsclass.Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new CustomViewHolder(view);
    }

    public void setList(ArrayList<Eventsclass.Event> list){
        this.events = list;
    }

    public ArrayList<Eventsclass.Event> getEvents(){
        return events;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Eventsclass.Event event = events.get(position);

        holder.eventNameTextView.setText(event.eventName);
//        Glide.with(context).load(getURL(pokemon.number)).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        Log.d("error:", event.eventName);
        holder.eventDate.setText(event.date);
//        holder.eventImage.setImageResource(R.id.);
        holder.constraintLayout.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView eventNameTextView;
        TextView eventDate;
        TextView eventEmail;
        ImageView eventImage;
        ConstraintLayout constraintLayout;

        public CustomViewHolder (View view){
            super(view);
            this.constraintLayout = view.findViewById(R.id.clayout);
            this.eventNameTextView = (TextView) view.findViewById(R.id.nameTextView);
            this.eventDate = (TextView) view.findViewById(R.id.dateTextView);
            this.eventEmail = (TextView) view.findViewById(R.id.emailTextView);
//            this.eventIma
//            this.imageView = (ImageView) view.findViewById(R.id.ImageView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Eventsclass.Event event = events.get(getAdapterPosition());
                    //Intent stuff for when you click on it
                }
            });

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            context.startActivity(intent);
        }
    }

}
