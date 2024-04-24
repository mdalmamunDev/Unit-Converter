package com.example.unitconverterBy_Al_Mamun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertActivity extends AppCompatActivity implements View.OnClickListener{

    UnitsAndValues arrOfUv = new UnitsAndValues();

    public static String unit_1, unit_2 = "Miter";
    TextView inputTop, inputBottom;
    ImageView infoBtn;
    RelativeLayout backBtn;
    Button numBtn_0, numBtn_1, numBtn_2, numBtn_3, numBtn_4, numBtn_5, numBtn_6, numBtn_7, numBtn_8, numBtn_9, numBtn_00, numBtn_dot, numBtn_C, numBtn_cross, numBtn_E, numBtnMinusE;
    Spinner spinner_1, spinner_2;
    String[][] unitsAndValues;
    boolean inputIsTop = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        TextView activityName = findViewById(R.id.activity_name);
        Intent intent = getIntent();
        String currActivity = intent.getStringExtra("activity");
        activityName.setText(currActivity);

        // set units and values
       switch (currActivity) {
           case "Length":
               unitsAndValues = arrOfUv.lengthUV;
               break;
           case "Area":
               unitsAndValues = arrOfUv.areaUV;
               break;
           case "Volume":
               unitsAndValues = arrOfUv.volumeUV;
               break;
           case "Weight":
               unitsAndValues = arrOfUv.weightUV;
               break;
           case "Temperature":
               unitsAndValues = arrOfUv.tempUV;
               break;
           case "Speed":
               unitsAndValues = arrOfUv.speedUV;
               break;
           case "Currency":
               unitsAndValues = arrOfUv.currencyUV;
               break;
           case "Power":
               unitsAndValues = arrOfUv.powerUV;
               break;
           case "Pressure":
               unitsAndValues = arrOfUv.pressureUV;
               break;

       }


        String[] unitsTop = Gadgets.makeOneDArray(unitsAndValues);
        String[] unitsDown = Gadgets.makeOneDArray(unitsAndValues, 1);
        inputTop = findViewById(R.id.input_top);
        inputBottom = findViewById(R.id.input_bottom);
        spinner_1 = findViewById(R.id.spinner_1);
        spinner_2 = findViewById(R.id.spinner_2);

        assignId(backBtn, R.id.back_btn);
        assignId(infoBtn, R.id.info_btn);
        assignId(inputTop, R.id.input_top);
        assignId(inputBottom, R.id.input_bottom);
        assignId(numBtn_0, R.id.numBtn_0);
        assignId(numBtn_00, R.id.numBtn_00);
        assignId(numBtn_1, R.id.numBtn_1);
        assignId(numBtn_2, R.id.numBtn_2);
        assignId(numBtn_3, R.id.numBtn_3);
        assignId(numBtn_4, R.id.numBtn_4);
        assignId(numBtn_5, R.id.numBtn_5);
        assignId(numBtn_6, R.id.numBtn_6);
        assignId(numBtn_7, R.id.numBtn_7);
        assignId(numBtn_8, R.id.numBtn_8);
        assignId(numBtn_9, R.id.numBtn_9);
        assignId(numBtn_dot, R.id.numBtn_dot);
        assignId(numBtn_cross, R.id.numBtn_cross);
        assignId(numBtn_C, R.id.numBtn_C);
        assignId(numBtn_E, R.id.numBtn_E);
        assignId(numBtnMinusE, R.id.numBtnEMinus);

        setSpinner(spinner_1, unitsTop);
        setSpinner(spinner_2, unitsDown);
    }

    void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    void assignId(TextView v, int id) {
        v = findViewById(id);
        v.setOnClickListener(this);
    }
    void assignId(ImageView v, int id) {
        v = findViewById(id);
        v.setOnClickListener(this);
    }
    void assignId(RelativeLayout v, int id) {
        v = findViewById(id);
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.back_btn || view.getId()==R.id.info_btn) {
            if (view.getId() == R.id.back_btn) {
                // << back
                finish();
            } else if (view.getId() == R.id.info_btn) {
                // >> go info
                Gadgets.openActivity(ConvertActivity.this, Information.class, "info_from", "Convert");
            }
        } else if (view.getId()==R.id.input_top || view.getId()==R.id.input_bottom) {
            // set inout field
            TextView v = (TextView) view;
            v.setTextColor(Color.parseColor("#2196F3"));

            //making toast
            String msg = "";

            Toast toast = new Toast(ConvertActivity.this);
            View toastView = getLayoutInflater().inflate(R.layout.custom_toast_layout, (ViewGroup) findViewById(R.id.view_container));
            toast.setView(toastView);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0,0);
            TextView toastMsg = toastView.findViewById(R.id.toast_msg);

            if (v == inputTop) {
                msg = unit_1 + " => " + unit_2;
                inputBottom.setTextColor(ContextCompat.getColor(ConvertActivity.this, R.color.def));
                inputIsTop = true;
            } else if (v == inputBottom) {
                msg = unit_2 + " => " + unit_1;
                inputTop.setTextColor(ContextCompat.getColor(ConvertActivity.this, R.color.def));
                inputIsTop = false;
            }

            toastMsg.setText(msg);
            toast.show();
       } else {
            try {
                // take inputs
                Gadgets.takeInputs(view, inputTop,inputBottom, inputIsTop, ConvertActivity.this);
                // calculate
                Gadgets.calculate(view, unitsAndValues, inputTop, inputBottom, inputIsTop, unit_1, unit_2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setSpinner(Spinner spinner, String[] array) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ConvertActivity.this, R.layout.spinner_layout, array);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getId() == R.id.spinner_1){unit_1 = array[i];
                } else {unit_2 = array[i];}

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}