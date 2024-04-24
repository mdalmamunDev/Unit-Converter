package com.example.unitconverterBy_Al_Mamun;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;


public class Gadgets {
    public static String MY_WEB_LINK = "https://md-al-mamun--workshop.000webhostapp.com/";

    public static String[] makeOneDArray(String[][] arr) {
        String[] oneDArr = new String[arr.length];
        for (int i=0; i<arr.length; i++) {
            oneDArr[i] = arr[i][0];
        }
        return oneDArr;
    }

    public static String[] makeOneDArray(String[][] arr, int index) {
        String[] oneDArr = new String[arr.length];
        for (int i=0; i<arr.length; i++) {
            oneDArr[i] = arr[i][0];
        }
        String temp = oneDArr[0];
        oneDArr[0] = oneDArr[index];
        oneDArr[index] = temp;
        return oneDArr;
    }

    public static void openActivity(Context context, Class clas, String key, String value) {
        Intent i = new Intent(context, clas);
        i.putExtra(key, value);
        context.startActivity(i);
    }
    public static void openActivity(Context context, Class clas) {
        Intent i = new Intent(context, clas);
        context.startActivity(i);
    }
    public static void openActivity(Context context, String uri) {
        Uri u = Uri.parse(uri);
        Intent i = new Intent(Intent.ACTION_VIEW, u);
        context.startActivity(i);
    }


    public static void takeInputs (View view, TextView inputTop, TextView inputBottom, Boolean inputIsTop, Context context) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = "";
        if (inputIsTop){
            dataToCalculate = inputTop.getText().toString();
        } else{
            dataToCalculate = inputBottom.getText().toString();
        }

        if (buttonText.equals("C")){
            dataToCalculate = "0";
            inputTop.setText("0");
            inputBottom.setText("0");
            return;
        } else if (buttonText.equals("X")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);

            if (dataToCalculate.equals("")) {
                dataToCalculate = "0";
            }
        } else if (buttonText.equals("E") || buttonText.equals("E-")) {
            if (!charAtString(dataToCalculate, 'E') && !dataToCalculate.equals("0")) {
                dataToCalculate += buttonText;
            }
        } else if (buttonText.equals(".")) {
            if (charAtString(dataToCalculate, '.')) {
                return;
            }
            dataToCalculate += buttonText;
        } else if (buttonText.equals("00") && dataToCalculate.equals("0")) {
            return;
        }else if (dataToCalculate.equals("0") || dataToCalculate.equals("00")) {
            dataToCalculate = buttonText;
        } else if (removeComa(dataToCalculate).length() < 15){
            dataToCalculate += buttonText;
        } else if (removeComa(dataToCalculate).length() >= 15){
            Toast.makeText(context, "Maximum (" + removeComa(dataToCalculate).length() + ") digits are entered", Toast.LENGTH_SHORT).show();
        }

        if (inputIsTop){
            inputTop.setText(setComa(removeComa(dataToCalculate)));
        } else {
            inputBottom.setText(setComa(removeComa(dataToCalculate)));
        }
    }

    public static void  calculate(View view, String unitsAndValues[][], TextView inputTop, TextView inputBottom, boolean inputIsTop, String unit_1, String unit_2) {

        Button button = (Button) view;
        if (! button.getText().equals("C")) {
            String topInputs = removeComa(inputTop.getText().toString());
            String bottomInputs = removeComa(inputBottom.getText().toString());
            double topNumbers = Double.parseDouble(topInputs);
            double bottomNumbers = Double.parseDouble(bottomInputs);
            double resultConst = 0d; // as a miter value
            double finalResult = 0d;
            double numToCla;
            String u1, u2;

            if (inputIsTop) {
                u1 = unit_1;
                u2 = unit_2;
                numToCla = topNumbers;
            } else {
                u1 = unit_2;
                u2 = unit_1;
                numToCla = bottomNumbers;
            }

            //convert to primary unit
            for (int i = 0; i < unitsAndValues.length; i++) {
                if (unitsAndValues[i][0] == u1) {
                    if (unitsAndValues[0][1].equals("")) {
                        switch (i) {
                            case 0:
                                resultConst = numToCla;
                                break;
                            case 1:
                                resultConst = (numToCla - 32) * 5 / 9;
                                break;
                            case 2:
                                resultConst = numToCla - 273.15d;
                                break;
                            case 3:
                                resultConst = (numToCla - 491.67d) * 5 / 9;
                                break;
                            case 4:
                                resultConst = numToCla * 1.25d;
                        }
                    } else {
                        Double d = Double.parseDouble(unitsAndValues[i][1]);
                        resultConst = numToCla / d;
                        break;
                    }
                }
            }
            // set result unit
            for (int i = 0; i < unitsAndValues.length; i++) {
                if (unitsAndValues[i][0] == u2) {
                    if (unitsAndValues[0][1].equals("")) {
                        switch (i) {
                            case 0:
                                finalResult = resultConst;
                                break;
                            case 1:
                                finalResult = (resultConst * 9 / 5) + 32;
                                break;
                            case 2:
                                finalResult = resultConst + 273.15d;
                                break;
                            case 3:
                                finalResult = resultConst * 9 / 5 + 491.67d;
                                break;
                            case 4:
                                finalResult = resultConst / 1.25d;
                        }
                    } else {
                        Double d = Double.parseDouble(unitsAndValues[i][1]);
                        finalResult = resultConst * d;
                        break;
                    }
                }
            }

            float finalResultFloat = (float) finalResult;
            if (finalResultFloat == 0.0f) {
                setResultText(inputIsTop, inputTop, inputBottom, "0");
            } else if (finalResultFloat - (int) finalResultFloat == 0.0f) {
                setResultText(inputIsTop, inputTop, inputBottom, setComa((int) finalResult));
            }else {
                setResultText(inputIsTop, inputTop, inputBottom, setComa(finalResultFloat));
            }
        }
    }
    public static void setResultText(boolean inputIsTop, TextView inputTop, TextView inputBottom, String text) {
        if (inputIsTop) {
            inputBottom.setText(text);
        } else {
            inputTop.setText(text);
        }
    }



    public static boolean charAtString(String s, char c) {
        boolean bool = false;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == c) {
                bool = true;
            }
        }
        return bool;
    }

    public static String setComa (float finalResultFloat) {
        String text = String.valueOf(finalResultFloat);
        String newText = "";
        String text2 = text;

        if (text.equals("Infinity") || charAtString(text, 'E')) {
            newText = text;
            return newText;
        }
        for (int i = text.length()-1; i>=0; i--) {
            if (text.charAt(i) == '.') {
                newText = text.substring(i, text.length());
                text2 = text.substring(0, i);
                break;
            }
        }
        for (int c=text2.length()-1; c>=0; c--) {
            int r = text2.length() - c; // example: (0, 1, 2, 3) -> (4, 3, 2, 1)
            newText = text2.charAt(c) + newText;
            if (r%3 == 0 && c != 0) {
                newText = "," + newText;
            }
        }
        return newText;
    }
    public static String setComa (int finalResultFloat) {
        String text = String.valueOf(finalResultFloat);
        String newText = "";
        String text2 = text;

        if (text.equals("Infinity") || charAtString(text, 'E')) {
            newText = text;
            return newText;
        }
        for (int i = text.length()-1; i>=0; i--) {
            if (text.charAt(i) == '.') {
                newText = text.substring(i, text.length());
                text2 = text.substring(0, i);
                break;
            }
        }
        for (int c=text2.length()-1; c>=0; c--) {
            int r = text2.length() - c; // example: (0, 1, 2, 3) -> (4, 3, 2, 1)
            newText = text2.charAt(c) + newText;
            if (r%3 == 0 && c != 0) {
                newText = "," + newText;
            }
        }
        return newText;
    }
    public static String setComa (String finalString) {
        String text = finalString;
        String newText = "";
        String text2 = text;

        if (text.equals("Infinity") || charAtString(text, 'E')) {
            newText = text;
            return newText;
        }
        for (int i = text.length()-1; i>=0; i--) {
            if (text.charAt(i) == '.') {
                newText = text.substring(i, text.length());
                text2 = text.substring(0, i);
                break;
            }
        }
        for (int c=text2.length()-1; c>=0; c--) {
            int r = text2.length() - c; // example: (0, 1, 2, 3) -> (4, 3, 2, 1)
            newText = text2.charAt(c) + newText;
            if (r%3 == 0 && c != 0) {
                newText = "," + newText;
            }
        }
        return newText;
    }

    public static String removeComa (String text) {
        String newText = "";
        for (int i=text.length()-1; i >= 0; i--) {
            if (text.charAt(i) != ',') {
                newText = text.charAt(i) + newText;
            }
        }
        return newText;
    }
}
