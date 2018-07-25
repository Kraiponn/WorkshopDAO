package com.ksn.kraiponn.workshopdao.activity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.ksn.kraiponn.workshopdao.R;

import java.util.Random;

import dmax.dialog.SpotsDialog;

public class ChartActivity extends AppCompatActivity {
    private GraphView graphView;
    private EditText edtNumX;
    private EditText edtNumY;
    private Button btnAddPt;
    private LineGraphSeries xySeries;
    private static final Random mRandom = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        initInstance();
    }

    private void initInstance() {
        graphView = findViewById(R.id.graphView);
        edtNumX = findViewById(R.id.edtNumX);
        edtNumY = findViewById(R.id.edtNumY);
        btnAddPt = findViewById(R.id.btnAddPt);
        //initGraph();

        handler = new Handler();

        btnAddPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*double x = Double.parseDouble(edtNumX.getText().toString());
                double y = Double.parseDouble(edtNumY.getText().toString());
                xySeries.appendData(
                        new DataPoint(x,y),
                        true, 1000);*/
                addEntry();

            }
        });

        series = new LineGraphSeries<DataPoint>();
        series.setColor(Color.BLUE);
        series.setDataPointsRadius(35);
        series.setDrawDataPoints(true);
        series.setThickness(15);
        graphView.addSeries(series);

        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(10);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScrollable(true);
    }

    private void initGraph() {
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(
                new DataPoint[]{
                        new DataPoint(0, 7),
                        new DataPoint(10, 140),
                        new DataPoint(25, 47),
                        new DataPoint(45, 127),
                        new DataPoint(75, 82)
                }
        );
        graphView.addSeries(series1);
        series1.setTitle("Temp S1");
        series1.setColor(Color.GREEN);
        series1.setDrawDataPoints(true);
        series1.setDataPointsRadius(20);
        series1.setThickness(9);

        /*Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        series1.setCustomPaint(paint);*/

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(
                new DataPoint[]{
                        //new DataPoint(0, 7),
                        new DataPoint(5, 160),
                        new DataPoint(15, 25),
                        new DataPoint(35, 75),
                        new DataPoint(100, 45)
                }
        );
        graphView.addSeries(series2);
        //series2.setShape(PointsGraphSeries.Shape.POINT);
        series2.setTitle("IR Sensor");
        series2.setColor(Color.RED);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(15);
        series2.setThickness(9);

        graphView.setTitle("Temp Result");
        //graphView.setCursorMode(true);
        graphView.setTitleColor(Color.RED);
        graphView.setTitleTextSize(72);
        //graphView.getViewport().setBackgroundColor(Color.BLACK);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        graphView.getViewport().setScrollableY(true);

        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView.getLegendRenderer().setPadding(5);
        graphView.getLegendRenderer().setMargin(7);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    // add random data to graph
    private void addEntry() {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        series.appendData(new DataPoint(lastX++, mRandom.nextDouble() * 10d),
                true, 100);
        //
    }


}
