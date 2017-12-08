package com.example.dadasaheb.exercise2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.nametv.setText(myrows.get(position).name);
        holder.pricetv.setText(myrows.get(position).price);
        holder.volumetv.setText(myrows.get(position).volume);
        holder.changetv.setText(myrows.get(position).change);
        Picasso.with(ctx)
                .load(R.mipmap.ic_launcher_round)
                .rotate(180)
                .into(holder.img1);
        if(myrows.get(position).up==false) {
            holder.img.setImageResource(R.drawable.down_red_arrow);
            holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorDownRed));
        }
        else{
            holder.img.setImageResource(R.drawable.up_green_arrow);
            holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorUpGreen));
        }
        holder.imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(ctx,Main2Activity.class);
                in.putExtra("Name",holder.nametv.getText().toString());
                in.putExtra("Price",holder.pricetv.getText().toString());
                in.putExtra("Volume",holder.volumetv.getText().toString());
                in.putExtra("Change",holder.changetv.getText().toString());
                in.putExtra("UP",myrows.get(position).up);
                ctx.startActivity(in);
            }
        });

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
        ImageView img,img1,imgnext;
        public MyViewHolder(View itemView) {
            super(itemView);
            nametv = (TextView) itemView.findViewById(R.id.namerow);
            pricetv = (TextView) itemView.findViewById(R.id.pricerow);
            volumetv = (TextView) itemView.findViewById(R.id.volumerow);
            changetv = (TextView) itemView.findViewById(R.id.changerow);
            img=(ImageView) itemView.findViewById(R.id.imgrow);
            img1=(ImageView) itemView.findViewById(R.id.imgrow1);
            imgnext=(ImageView) itemView.findViewById(R.id.imgnextrow);
        }
    }
}