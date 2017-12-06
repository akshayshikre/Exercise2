package com.example.dadasaheb.exercise2;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<row> myrows;
    Context ctx;
    public MyAdapter(Context c, ArrayList<row> rows) {
        myrows=rows;
        ctx=c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nametv.setText(myrows.get(position).name);
        holder.pricetv.setText(myrows.get(position).price);
        holder.volumetv.setText(myrows.get(position).volume);
        holder.changetv.setText(myrows.get(position).change);
        if(myrows.get(position).up==false) {
            holder.img.setImageResource(R.drawable.down_red_arrow);
            holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorDownRed));
        }
        else{
            holder.img.setImageResource(R.drawable.up_green_arrow);
            holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorUpGreen));
        }

    }
    public void updateList(ArrayList<row> newlist) {
        myrows.clear();
        myrows.addAll(newlist);
        this.notifyDataSetChanged();
        this.notify();
    }

    @Override
    public int getItemCount() {
        return myrows.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nametv,pricetv,volumetv,changetv;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            nametv = (TextView) itemView.findViewById(R.id.namerow);
            pricetv = (TextView) itemView.findViewById(R.id.pricerow);
            volumetv = (TextView) itemView.findViewById(R.id.volumerow);
            changetv = (TextView) itemView.findViewById(R.id.changerow);
            img=(ImageView) itemView.findViewById(R.id.imgrow);
        }
    }
}