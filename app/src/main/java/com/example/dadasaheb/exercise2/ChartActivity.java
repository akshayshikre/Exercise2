package com.example.dadasaheb.exercise2;

/**
 * Created by admin on 27/12/17.
 */



import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;

public class ChartActivity extends AppCompatActivity implements
        OnChartGestureListener, OnChartValueSelectedListener {

    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    FloatingActionButton fab1,fab2,fab3;
    TextView linetv,tickertv,alerttv;
    private LineChart mChart;

    Button z;

//    private SeekBar mSeekBarX, mSeekBarY;
//    private TextView tvX, tvY;

    @Override
    protected void onResume() {
        super.onResume();
        // we're going to simulate real time with thread that append data to the graph
        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (int i = 0; i < 100; i++) {

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                }
            }
        }).start();
    }

    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
    }
    // add random data to graph
    private void addEntry() {
//            // here, we choose to display max 10 points on the viewport and we scroll to end
//            //series.appendData(new DataPoint(lastX++, RANDOM.nextDouble() * 10d), true, 10);
//            float val = (float) (Math.random() * 100) + 3;
//            mChart.getData().addEntry(new Entry(mChart.getData().getXMax(), val, getResources().getDrawable(R.drawable.star)),
//                    mChart.getData().getEntryCount()+1);
//            mChart.getData().notifyDataChanged(); // let the data know a dataSet changed
//            mChart.notifyDataSetChanged(); // let the chart know it's data changed
//            mChart.moveViewToX(mChart.getData().getEntryCount());
//            Log.i("graph","last");
//            //int temp=(int)mChart.getYMax();
//
//            //setData(temp + 1, 100);
//
//            // redraw
//            //mChart.invalidate();

        LineData data = mChart.getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }

            data.addEntry(new Entry(set.getEntryCount(), (float) (Math.random() * 100) + 30f), 0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            mChart.notifyDataSetChanged();

            // limit the number of visible entries
            mChart.setVisibleXRangeMaximum(40);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            mChart.setScaleXEnabled(false);
            mChart.setScaleEnabled(false);
            mChart.disableScroll();
            mChart.setDragEnabled(false);
            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
            mv.setChartView(mChart); // For bounds control
            mChart.setMarker(mv);
            //mChart.canScrollHorizontally();
            mChart.moveViewToX(data.getEntryCount());
                //mChart.invalidate();
            // this automatically refreshes the chart (calls invalidate())
            // mChart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        setContentView(R.layout.linegraph);

        z=(Button)findViewById(R.id.zoom);
        t1=(TextView)findViewById(R.id.l1t1);
        t2=(TextView)findViewById(R.id.l1t2);
        t3=(TextView)findViewById(R.id.l1t3);
        t4=(TextView)findViewById(R.id.l1t4);
        t5=(TextView)findViewById(R.id.l2t1);
        t6=(TextView)findViewById(R.id.l2t2);
        t7=(TextView)findViewById(R.id.l2t3);
        t8=(TextView)findViewById(R.id.l2t4);

        linetv=(TextView) findViewById(R.id.fablinetv);
        tickertv=(TextView)findViewById(R.id.fabtickertv);
        alerttv=(TextView)findViewById(R.id.fabalerttv);

        linetv.setTypeface(font);
        tickertv.setTypeface(font);
        alerttv.setTypeface(font);

        fab1= (FloatingActionButton) findViewById(R.id.fabline);
        fab2= (FloatingActionButton) findViewById(R.id.fabticker);
        fab3=(FloatingActionButton)findViewById(R.id.fabalert);

        fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
        fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
        fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));

        linetv.setTextColor(getResources().getColor(R.color.colorwhite));
        tickertv.setTextColor(getResources().getColor(R.color.colorPrimary));
        alerttv.setTextColor(getResources().getColor(R.color.colorPrimary));

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
                fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));

                linetv.setTextColor(getResources().getColor(R.color.colorwhite));
                tickertv.setTextColor(getResources().getColor(R.color.colorPrimary));
                alerttv.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));
                fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));

                linetv.setTextColor(getResources().getColor(R.color.colorPrimary));
                tickertv.setTextColor(getResources().getColor(R.color.colorwhite));
                alerttv.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorButtonsBackLayout)));
                fab3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorFabTint)));

                linetv.setTextColor(getResources().getColor(R.color.colorPrimary));
                tickertv.setTextColor(getResources().getColor(R.color.colorPrimary));
                alerttv.setTextColor(getResources().getColor(R.color.colorwhite));
            }
        });


        t1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                  t1.setText(getString(R.string.text1));
                //  Toast.makeText(ChartActivity.this,"click on text1 ",Toast.LENGTH_SHORT).show();

                String styledText = "<b><u>6h</u></b>";
                t1.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                t1.setTextColor(getResources().getColor(R.color.colorFabTint));
                t2.setText("24h");
                t2.setTextColor(Color.BLACK);
                t3.setText("1w");
                t3.setTextColor(Color.BLACK);
                t4.setText("1m");
                t4.setTextColor(Color.BLACK);
//                t5.setText("1h");
//                t6.setText("2h");
//                t7.setText("4h");
//                t8.setText("8h");


            }
        }));
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                t2.setText(getString(R.string.text2));
                String styledText = "<b><u>24h</u></b>";
                t2.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                t2.setTextColor(getResources().getColor(R.color.colorFabTint));
                t1.setText("6h");
                t1.setTextColor(Color.BLACK);
                t3.setText("1w");
                t3.setTextColor(Color.BLACK);
                t4.setText("1m");
                t4.setTextColor(Color.BLACK);
//                t5.setText("1h");
//                t6.setText("2h");
//                t7.setText("4h");
//                t8.setText("8h");

            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                t3.setText(getString(R.string.text3));
                String styledText = "<b><u>1w</u></b>";
                t3.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                t3.setTextColor(getResources().getColor(R.color.colorFabTint));
                t2.setText("24h");
                t2.setTextColor(Color.BLACK);
                t1.setText("6h");
                t1.setTextColor(Color.BLACK);
                t4.setText("1m");
                t4.setTextColor(Color.BLACK);
//                t5.setText("1h");
//                t6.setText("2h");
//                t7.setText("4h");
//                t8.setText("8h");
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                t4.setText(getString(R.string.text4));
                String styledText = "<b><u>1m</u></b>";
                t4.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                t4.setTextColor(getResources().getColor(R.color.colorFabTint));
                t2.setText("24h");
                t2.setTextColor(Color.BLACK);
                t3.setText("1w");
                t3.setTextColor(Color.BLACK);
                t1.setText("6h");
                t1.setTextColor(Color.BLACK);
//                t5.setText("1h");
//                t6.setText("2h");
//                t7.setText("4h");
//                t8.setText("8h");
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                t5.setText(getString(R.string.text5));
                String styledText = "<b><u>1h</u></b>";
                t5.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                t5.setTextColor(getResources().getColor(R.color.colorFabTint));
//                t2.setText("24h");
//                t3.setText("1w");
//                t4.setText("1m");
//                t1.setText("6h");
                t6.setText("2h");
                t6.setTextColor(Color.BLACK);
                t7.setText("4h");
                t7.setTextColor(Color.BLACK);
                t8.setText("8h");
                t8.setTextColor(Color.BLACK);
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                t6.setText(getString(R.string.text6));
                String styledText = "<b><u>2h</u></b>";
                t6.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                t6.setTextColor(getResources().getColor(R.color.colorFabTint));
//                t2.setText("24h");
//                t3.setText("1w");
//                t4.setText("1m");
                t5.setText("1h");
                t5.setTextColor(Color.BLACK);
//                t1.setText("6h");
                t7.setText("4h");
                t7.setTextColor(Color.BLACK);
                t8.setText("8h");
                t8.setTextColor(Color.BLACK);
            }
        });
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                t7.setText(getString(R.string.text7));
                String styledText = "<b><u>4h</u></b>";
                t7.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                t7.setTextColor(getResources().getColor(R.color.colorFabTint));
//                t2.setText("24h");
//                t3.setText("1w");
//                t4.setText("1m");
                t5.setText("1h");
                t5.setTextColor(Color.BLACK);
                t6.setText("2h");
                t6.setTextColor(Color.BLACK);
//                t1.setText("6h");
                t8.setText("8h");
                t8.setTextColor(Color.BLACK);
            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                t8.setText(getString(R.string.text8));
                String styledText = "<b><u>8h</u></b>";
                t8.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                t8.setTextColor(getResources().getColor(R.color.colorFabTint));
//                t2.setText("24h");
//                t3.setText("1w");
//                t4.setText("1m");
                t5.setText("1h");
                t5.setTextColor(Color.BLACK);
                t6.setText("2h");
                t6.setTextColor(Color.BLACK);
                t7.setText("4h");
                t7.setTextColor(Color.BLACK);
//                t1.setText("6h");
            }
        });
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);
//        setSupportActionBar(myToolbar);

//        tvX = (TextView) findViewById(R.id.tvXMax);
//        tvY = (TextView) findViewById(R.id.tvYMax);

//        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
//        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);

//        mSeekBarX.setProgress(45);
//        mSeekBarY.setProgress(100);

//        mSeekBarY.setOnSeekBarChangeListener(this);
//        mSeekBarX.setOnSeekBarChangeListener(this);

        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

//        // x-axis limit line
//        LimitLine llXAxis = new LimitLine(10f, "Index 10");
//        llXAxis.setLineWidth(4f);
//        llXAxis.enableDashedLine(10f, 10f, 0f);
//        llXAxis.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
//        llXAxis.setTextSize(10f);
//
        XAxis xAxis=mChart.getXAxis();

//        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        //xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());
//        //xAxis.addLimitLine(llXAxis); // add x-axis limit line
//
//
//        //Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//
//        LimitLine ll1 = new LimitLine(150f, "Upper Limit");
//        ll1.setLineWidth(4f);
//        ll1.enableDashedLine(10f, 10f, 0f);
//        ll1.setLabelPosition(LimitLabelPosition.RIGHT_TOP);
//        ll1.setTextSize(10f);
//        //ll1.setTypeface(tf);
//
//        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
//        ll2.setLineWidth(4f);
//        ll2.enableDashedLine(10f, 10f, 0f);
//        ll2.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
//        ll2.setTextSize(10f);
//        //`ll2.setTypeface(tf);
//



        // mChart.getAxisLeft().setDrawGridLines(false);
//       mChart.getXAxis().setDrawGridLines(false);



        //  mChart.getXAxis().setEnabled(true);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getXAxis().disableAxisLineDashedLine();
        mChart.getXAxis().setDrawAxisLine(false);
        mChart.getXAxis().disableAxisLineDashedLine();
        mChart.getXAxis().setAxisLineWidth(3f);
        mChart.getXAxis().setGridColor(0);
        mChart.getXAxis().setAxisLineColor(0x1c2030);
        mChart.getXAxis().disableGridDashedLine();

        mChart.getXAxis().setEnabled(true);
        mChart.getAxisLeft().setEnabled(true);              //by me
        mChart.getAxisLeft().setAxisLineColor(0xffffff);   //by me
        mChart.getAxisRight().setEnabled(false);

        mChart.setDrawGridBackground(false);
        mChart.setGridBackgroundColor(Color.TRANSPARENT);

        mChart.bringToFront();
        mChart.invalidate();



//        YAxis rightYAxis = mChart.getAxisRight();
//        rightYAxis.setEnabled(true);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.addLimitLine(ll1);
//        leftAxis.addLimitLine(ll2);

        leftAxis.setAxisMaximum(200f);  //by me
        leftAxis.setAxisMinimum(0f);   //by me -50f
        //     leftAxis.setYOffset(20f);

        // leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);
//
//        // limit lines are drawn behind data (and not on top)
//        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        // add data

        setData(45, 100);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

        mChart.animateX(2500);
        //mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(LegendForm.NONE);   //by akshay

        // // dont forget to refresh the drawing
        // mChart.invalidate();

//        mChart.bringToFront();
//        mChart.invalidate();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.line, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleIcons: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setDrawIcons(!set.isDrawIconsEnabled());
                }

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if(mChart.getData() != null) {
                    mChart.getData().setHighlightEnabled(!mChart.getData().isHighlightEnabled());
                    mChart.invalidate();
                }
                break;
            }
            case R.id.actionToggleFilled: {

                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleCircles: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawCirclesEnabled())
                        set.setDrawCircles(false);
                    else
                        set.setDrawCircles(true);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleCubic: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.CUBIC_BEZIER);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleStepped: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.STEPPED
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.STEPPED);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHorizontalCubic: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.HORIZONTAL_BEZIER);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionTogglePinch: {
                if (mChart.isPinchZoomEnabled())
                    mChart.setPinchZoom(false);
                else
                    mChart.setPinchZoom(true);

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                mChart.setAutoScaleMinMaxEnabled(!mChart.isAutoScaleMinMaxEnabled());
                mChart.notifyDataSetChanged();
                break;
            }
            case R.id.animateX: {
                mChart.animateX(3000);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(3000, Easing.EasingOption.EaseInCubic);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(3000, 3000);
                break;
            }
            case R.id.actionSave: {
                if (mChart.saveToPath("title" + System.currentTimeMillis(), "")) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();

                // mChart.saveToGallery("title"+System.currentTimeMillis())
                break;
            }

            case R.id.back_to_normal: {


                mChart.clear();
                mChart.invalidate();

                mChart.setDrawGridBackground(false);

                // no description text
                mChart.getDescription().setEnabled(false);

                // enable touch gestures
                mChart.setTouchEnabled(true);

                // enable scaling and dragging
                mChart.setDragEnabled(true);
                mChart.setScaleEnabled(true);
                // mChart.setScaleXEnabled(true);
                // mChart.setScaleYEnabled(true);

                // if disabled, scaling can be done on x- and y-axis separately
                mChart.setPinchZoom(true);

                // set an alternative background color
                // mChart.setBackgroundColor(Color.GRAY);

                // create a custom MarkerView (extend MarkerView) and specify the layout
                // to use for it
                MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
                mv.setChartView(mChart); // For bounds control
                mChart.setMarker(mv); // Set the marker to the chart

//        // x-axis limit line
//        LimitLine llXAxis = new LimitLine(10f, "Index 10");
//        llXAxis.setLineWidth(4f);
//        llXAxis.enableDashedLine(10f, 10f, 0f);
//        llXAxis.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
//        llXAxis.setTextSize(10f);
//
                XAxis xAxis=mChart.getXAxis();

//        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        //xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());
//        //xAxis.addLimitLine(llXAxis); // add x-axis limit line
//
//
//        //Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//
//        LimitLine ll1 = new LimitLine(150f, "Upper Limit");
//        ll1.setLineWidth(4f);
//        ll1.enableDashedLine(10f, 10f, 0f);
//        ll1.setLabelPosition(LimitLabelPosition.RIGHT_TOP);
//        ll1.setTextSize(10f);
//        //ll1.setTypeface(tf);
//
//        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
//        ll2.setLineWidth(4f);
//        ll2.enableDashedLine(10f, 10f, 0f);
//        ll2.setLabelPosition(LimitLabelPosition.RIGHT_BOTTOM);
//        ll2.setTextSize(10f);
//        //`ll2.setTypeface(tf);
//



                // mChart.getAxisLeft().setDrawGridLines(false);
//       mChart.getXAxis().setDrawGridLines(false);



                //  mChart.getXAxis().setEnabled(true);
                mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                mChart.getXAxis().disableAxisLineDashedLine();
                mChart.getXAxis().setDrawAxisLine(false);
                mChart.getXAxis().disableAxisLineDashedLine();
                mChart.getXAxis().setAxisLineWidth(3f);
                mChart.getXAxis().setGridColor(0);
                mChart.getXAxis().setAxisLineColor(0x1c2030);
                mChart.getXAxis().disableGridDashedLine();

                mChart.getXAxis().setEnabled(true);
                mChart.getAxisLeft().setEnabled(true);              //by me
                mChart.getAxisLeft().setAxisLineColor(0xffffff);   //by me
                mChart.getAxisRight().setEnabled(false);

                mChart.setDrawGridBackground(false);
                mChart.setGridBackgroundColor(Color.TRANSPARENT);

                mChart.bringToFront();
                mChart.invalidate();



//        YAxis rightYAxis = mChart.getAxisRight();
//        rightYAxis.setEnabled(true);
                YAxis leftAxis = mChart.getAxisLeft();
                leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.addLimitLine(ll1);
//        leftAxis.addLimitLine(ll2);

                leftAxis.setAxisMaximum(200f);  //by me
                leftAxis.setAxisMinimum(0f);   //by me -50f
                //     leftAxis.setYOffset(20f);

                // leftAxis.enableGridDashedLine(10f, 10f, 0f);
                leftAxis.setDrawZeroLine(true);
//
//        // limit lines are drawn behind data (and not on top)
//        leftAxis.setDrawLimitLinesBehindData(true);

                mChart.getAxisRight().setEnabled(false);

                //mChart.getViewPortHandler().setMaximumScaleY(2f);
                //mChart.getViewPortHandler().setMaximumScaleX(2f);

                // add data

                setData(45, 100);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

                mChart.animateX(2500);
                //mChart.invalidate();

                // get the legend (only possible after setting data)
                Legend l = mChart.getLegend();

                // modify the legend ...
                l.setForm(LegendForm.NONE);   //by akshay

                // // dont forget to refresh the drawing
                // mChart.invalidate();

//        mChart.bringToFront();
//        mChart.invalidate();
                break;
            }

        }
        return true;
    }

//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//        tvX.setText("" + (mSeekBarX.getProgress() + 1));
//        tvY.setText("" + (mSeekBarY.getProgress()));
//
//        setData(mSeekBarX.getProgress() + 1, mSeekBarY.getProgress());
//
//        // redraw
//        mChart.invalidate();
//    }

//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        // TODO Auto-generated method stub
//
//    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) + 3;
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "");



            set1.setDrawIcons(false);


            //our code
            set1.setDrawCircles(false);
            set1.setDrawValues(false);

            set1.disableDashedLine();
            set1.setColor(R.color.colorPrimary);
            set1.setLineWidth(2f);                   //by akash



            // set the line to be drawn like this "- - - - - -"
           /* set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);*/
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            }
            else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

        Log.i("DoubleTap", "Chart double-tapped.");

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
        z.setVisibility(View.VISIBLE);
        z.setEnabled(true);

//       if(mChart.z)
//       {
//           z.setVisibility(View.GONE);
//           z.setEnabled(false);
//
//       }

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleX() + ", high: " + mChart.getHighestVisibleX());
        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin() + ", xmax: " + mChart.getXChartMax() + ", ymin: " + mChart.getYChartMin() + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }


    public void resetZoom(View view) {

        mChart.fitScreen();
        z.setVisibility(View.GONE);
        z.setEnabled(false);

    }


}