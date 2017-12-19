
package com.example.dadasaheb.exercise2;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
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
import com.github.mikephil.charting.utils.Utils;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends DemoBase implements
        OnChartGestureListener, OnChartValueSelectedListener {

    Button b1,b2,b3,b4,b5,b6,b7,b8;
    FloatingActionButton fab1,fab2,fab3;
    TextView linetv,tickertv,alerttv;
    private LineChart mChart;
//    private SeekBar mSeekBarX, mSeekBarY;
//    private TextView tvX, tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        setContentView(R.layout.linegraph);

        b1=(Button)findViewById(R.id.l1b1);
        b2=(Button)findViewById(R.id.l1b2);
        b3=(Button)findViewById(R.id.l1b3);
        b4=(Button)findViewById(R.id.l1b4);
        b5=(Button)findViewById(R.id.l2b1);
        b6=(Button)findViewById(R.id.l2b2);
        b7=(Button)findViewById(R.id.l2b3);
        b8=(Button)findViewById(R.id.l2b4);

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
        b1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        }));
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        mChart.getAxisLeft().setGridColor(0);
        mChart.getAxisLeft().setAxisLineColor(0x1c2030);

        mChart.getXAxis().setEnabled(true);
        mChart.getAxisLeft().setEnabled(true);
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
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(-50f);
        leftAxis.setYOffset(20f);
        // leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);

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
        l.setForm(LegendForm.LINE);

        // // dont forget to refresh the drawing
        // mChart.invalidate();
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
            set1 = new LineDataSet(values, "DataSet 1");


            set1.setDrawIcons(false);


            //our code
            set1.setDrawCircles(false);
            set1.setDrawValues(false);

            set1.disableDashedLine();
            set1.setColor(R.color.colorPrimary);
            set1.setLineWidth(2f);



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
}