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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by japjot on 2/22/18.
 */

public class Feedadapter extends RecyclerView.Adapter<Feedadapter.CustomViewHolder>{

    private Context context;
    private ArrayList<Event> events;

    public Feedadapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Event event = events.get(position);

        holder.eventEmail.setText(event.getEmail());
        Log.d("error:", event.eventName);

        holder.constraintLayout.setOnClickListener(holder);
        holder.eventNameTextView.setText(event.eventName);
//        String dateString = new SimpleDateFormat("hh/mm " + "z").format(new Date(event.date));
        holder.eventDate.setText(event.getDate());
        holder.eventEmail.setText(event.email);
        holder.numInterested.setText(Integer.toString(event.numInterested));

        Glide.with(context).load(event.getImageURL()).into(holder.eventImage);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView eventNameTextView;
        TextView eventDate;
        TextView eventEmail;
        TextView numInterested;

        ImageView eventImage;

        ConstraintLayout constraintLayout;

        public CustomViewHolder (View view){
            super(view);
            this.constraintLayout = view.findViewById(R.id.clayout);
            this.eventNameTextView = view.findViewById(R.id.nameTextView);
            this.eventDate = view.findViewById(R.id.dateTextView);
            this.eventEmail = view.findViewById(R.id.emailTextView);
            this.numInterested = view.findViewById(R.id.textView11);

            this.eventImage = view.findViewById(R.id.eventImage);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("Event", events.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }

}
