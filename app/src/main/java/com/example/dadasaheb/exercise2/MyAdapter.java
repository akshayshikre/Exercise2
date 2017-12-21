package com.example.dadasaheb.exercise2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static com.example.dadasaheb.exercise2.MainActivity.selectedButton;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Coin> myrows;
    Context ctx;
    Typeface font;
    int comparereturn=0;

    public MyAdapter(Context c, ArrayList<Coin> coinList) {
        myrows= coinList;
        ctx=c;
        font = Typeface.createFromAsset(ctx.getAssets(), "fontawesome-webfont.ttf" );
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.nametv.setText(myrows.get(position).Name);
//        if(selectedButton.equals("btc"))
//        {holder.pricetv.setText("฿ "+String.format("%.4f", Float.valueOf(myrows.get(position).Price_BTC)));
//        holder.changetv.setText(String.format("%.4f", Float.valueOf(myrows.get(position).Change_BTC)));
//        Picasso.with(ctx).load(myrows.get(position).ImageUrl).rotate(0).into(holder.img1);
//        }
//        else if(selectedButton.equals("inr"))
//        {holder.pricetv.setText("₹ "+String.format("%.4f", Float.valueOf(myrows.get(position).Price_ETH)));
//        holder.changetv.setText(String.format("%.4f", Float.valueOf(myrows.get(position).Change_ETH)));
//        Picasso.with(ctx).load(myrows.get(position).ImageUrl).rotate(0).into(holder.img1);
//        }
//        else if(selectedButton.equals("euro"))
//        {holder.pricetv.setText("€ "+String.format("%.4f", Float.valueOf(myrows.get(position).Price_EUR)));
//        holder.changetv.setText(String.format("%.4f", Float.valueOf(myrows.get(position).Change_EUR)));
//        Picasso.with(ctx).load(myrows.get(position).ImageUrl).rotate(0).into(holder.img1);
//        }
//        else if(selectedButton.equals("usd"))
//        {holder.pricetv.setText("$ "+String.format("%.4f", Float.valueOf(myrows.get(position).Price_USD)));
//        holder.changetv.setText(String.format("%.4f", Float.valueOf(myrows.get(position).Change_USD)));
//        Picasso.with(ctx).load(myrows.get(position).ImageUrl).rotate(0).into(holder.img1);
//        }

        if(selectedButton.equals("btc"))
        {holder.pricetv.setText(""+ Float.valueOf(myrows.get(position).Price_BTC));
            holder.changetv.setText(myrows.get(position).Change_BTC);
            holder.pricetag.setText(R.string.fa_btc);
            comparereturn=Double.compare(Double.valueOf(myrows.get(position).Change_BTC),(Double)0.0);
        }
        else if(selectedButton.equals("inr"))
        {holder.pricetv.setText(""+Float.valueOf(myrows.get(position).Price_ETH));
            holder.changetv.setText(myrows.get(position).Change_ETH);
            holder.pricetag.setText(R.string.fa_inr);
            comparereturn=Double.compare(Double.valueOf(myrows.get(position).Change_ETH),(Double)0.0);
        }
        else if(selectedButton.equals("euro"))
        {holder.pricetv.setText(""+Float.valueOf(myrows.get(position).Price_EUR));
            holder.changetv.setText(myrows.get(position).Change_EUR);
            holder.pricetag.setText(R.string.fa_euro);
            comparereturn=Double.compare(Double.valueOf(myrows.get(position).Change_EUR),(Double)0.0);
        }
        else if(selectedButton.equals("usd"))
        {holder.pricetv.setText(""+Float.valueOf(myrows.get(position).Price_USD));
            holder.changetv.setText(myrows.get(position).Change_USD);
            holder.pricetag.setText(R.string.fa_usd);
            comparereturn=Double.compare(Double.valueOf(myrows.get(position).Change_USD),(Double)0.0);
        }
            if(comparereturn>0){
                holder.changearrow.setText(ctx.getString(R.string.fa_sort_up));
                holder.changearrow.setTextColor(ContextCompat.getColor(ctx,R.color.colorUpGreen));
                holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorUpGreen));
                holder.changetv.setText("+"+holder.changetv.getText());
            }else if(comparereturn<0){
                holder.changearrow.setText(ctx.getString(R.string.fa_sort_down));
                holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorDownRed));
                holder.changearrow.setTextColor(ContextCompat.getColor(ctx,R.color.colorDownRed));
            }else {
                holder.changearrow.setText(ctx.getString(R.string.fa_sort_up));
                holder.changearrow.setTextColor(ContextCompat.getColor(ctx,R.color.colorUpGreen));
                holder.changetv.setTextColor(ContextCompat.getColor(ctx, R.color.colorUpGreen));
                holder.changetv.setText("+"+holder.changetv.getText());
            }
        Picasso.with(ctx).load(myrows.get(position).ImageUrl)
                .transform(new CircleTransform()).rotate(0).into(holder.img1);

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
        TextView nametv,pricetv,changetv,changearrow,pricetag;
        ImageView img1,imgnext;
        public MyViewHolder(View itemView) {
            super(itemView);
            nametv = (TextView) itemView.findViewById(R.id.namerow);
            pricetv = (TextView) itemView.findViewById(R.id.pricerow);
            changetv = (TextView) itemView.findViewById(R.id.changerow);
            changearrow = (TextView) itemView.findViewById(R.id.changearrow);
            pricetag = (TextView) itemView.findViewById(R.id.pricetag);
            img1=(ImageView) itemView.findViewById(R.id.imgrow1);
            imgnext=(ImageView) itemView.findViewById(R.id.imgnextrow);

            changearrow.setTypeface(font);
            pricetag.setTypeface(font);
        }
    }
}