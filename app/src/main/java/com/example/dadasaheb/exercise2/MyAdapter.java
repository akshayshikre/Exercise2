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

import static com.example.dadasaheb.exercise2.MainActivity.selectedButton;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Coin> myrows;
    Context ctx;
    public MyAdapter(Context c, ArrayList<Coin> coinList) {
        myrows= coinList;
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
        holder.nametv.setText(myrows.get(position).Name);
        if(selectedButton.equals("btc"))
        {holder.pricetv.setText("฿ "+myrows.get(position).Price_BTC);
        holder.changetv.setText(myrows.get(position).Change_BTC);
        Picasso.with(ctx).load(myrows.get(position).ImageUrl).rotate(0).into(holder.img1);
        }
        else if(selectedButton.equals("inr"))
        {holder.pricetv.setText("₹ "+myrows.get(position).Price_ETH);
        holder.changetv.setText(myrows.get(position).Change_ETH);
        Picasso.with(ctx).load(myrows.get(position).ImageUrl).rotate(0).into(holder.img1);
        }
        else if(selectedButton.equals("euro"))
        {holder.pricetv.setText("€ "+myrows.get(position).Price_EUR);
        holder.changetv.setText(myrows.get(position).Change_EUR);
        Picasso.with(ctx).load(myrows.get(position).ImageUrl).rotate(0).into(holder.img1);
        }
        else if(selectedButton.equals("usd"))
        {holder.pricetv.setText("$ "+myrows.get(position).Price_USD);
        holder.changetv.setText(myrows.get(position).Change_USD);
        Picasso.with(ctx).load(myrows.get(position).ImageUrl).rotate(0).into(holder.img1);
        }


       // if(myrows.get(position).up==false) {
            holder.img.setImageResource(R.drawable.down_red_arrow);
            holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorDownRed));
       // }
       //else{
        //    holder.img.setImageResource(R.drawable.up_green_arrow);
         //   holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorUpGreen));
       // }
        holder.imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(ctx,ChartActivity.class);
                in.putExtra("Name",holder.nametv.getText().toString());
                in.putExtra("Price",holder.pricetv.getText().toString());
                in.putExtra("Change",holder.changetv.getText().toString());
                in.putExtra("UP",myrows.get(position).SortOrder);
                ctx.startActivity(in);
            }
        });

    }
    public void updateList(ArrayList<Coin> newlist) {
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
        TextView nametv,pricetv,changetv;
        ImageView img,img1,imgnext;
        public MyViewHolder(View itemView) {
            super(itemView);
            nametv = (TextView) itemView.findViewById(R.id.namerow);
            pricetv = (TextView) itemView.findViewById(R.id.pricerow);
            changetv = (TextView) itemView.findViewById(R.id.changerow);
            img=(ImageView) itemView.findViewById(R.id.imgrow);
            img1=(ImageView) itemView.findViewById(R.id.imgrow1);
            imgnext=(ImageView) itemView.findViewById(R.id.imgnextrow);
        }
    }
}