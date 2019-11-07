
package com.androidteste.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androidteste.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GraficoActivity extends AppCompatActivity implements OnChartValueSelectedListener
 {
 
    private PieChart mChart;
    private float total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        mChart = (PieChart) findViewById(R.id.grafico);
        mChart.setUsePercentValues(true);
//        mChart.getDescription().setEnabled(false);
//        mChart.setExtraOffsets(5, 10, 5, 5);

//        mChart.setDragDecelerationFrictionCoef(0.95f);

//        mChart.setCenterTextTypeface(mTfLight);
//        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
//        mChart.setHoleColor(Color.parseColor("#c0c0c0"));
//        mChart.setBackgroundColor(Color.parseColor("#c0c0c0"));

//        mChart.setTransparentCircleColor(Color.WHITE);
//        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);
        mChart.setCenterText("tosco");
        mChart.setCenterTextSize(30f);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData(4, 100);

//        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);


        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setDrawSlicesUnderHole(true);
//        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        String mParties [] = {"teste 1", "teste 2", "teste 3", "teste 4", "teste 5"};

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        total = 0f;
        for (int i = 0; i < count ; i++) {
            Float valor = (float) ((Math.random() * mult) + mult / 5);
            total += valor;
            entries.add(new PieEntry(valor, mParties[i % mParties.length]));
        }



        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("", "selecionou " + e + ", h=" + h);
        float valorAtual = e.getY();
        NumberFormat df = DecimalFormat.getInstance();
        df.setMaximumFractionDigits(2);
        mChart.setCenterText(""+ df.format((valorAtual * 100) / total) + " %");
    }

    @Override
    public void onNothingSelected() {
        Log.i("", "não selecionou nada");

    }

}

