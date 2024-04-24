package com.example.unitconverterBy_Al_Mamun;

public class UnitsAndValues {
    String[][] lengthUV =  {
            {"Miter", "1"},
            {"Inch", "39.3701"},
            {"Foot", "3.2808399"},
            {"KMiter", "0.001"},
            {"Mile", "0.000621371"}
    };
    String[][] areaUV = {
            {"Miter^2", "1"},
            {"Inch^2", String.valueOf(Math.pow(39.3701d, 2))},
            {"Foot^2", String.valueOf(Math.pow(3.2808399d, 2))},
            {"KMiter^2", String.valueOf(Math.pow(0.001d, 2))},
            {"Mile^2", String.valueOf(Math.pow(0.000621371d, 2))}
    };
    String[][] volumeUV = {
            {"Miter^3", "1"},
            {"Inch^3", String.valueOf(Math.pow(39.3701d, 3))},
            {"Foot^3", String.valueOf(Math.pow(3.2808399d, 3))},
            {"KMiter^3", String.valueOf(Math.pow(0.001d, 3))},
            {"Mile^3", String.valueOf(Math.pow(0.000621371d, 3))}
    };
    String[][] weightUV = {
            {"Kilogram(kg)", "1"},
            {"Gram(g)", "1000"},
            {"Pound(lb)", "2.2046226"},
            {"Ton(t)", "0.001"},
            {"Quintal(q)", "0.01"}
    };
    String[][] tempUV = {
            {"Celsius(C)", ""},
            {"Fahrenheit(F)", ""},
            {"Kelvin(K)", ""},
            {"Rankine(R)", ""},
            {"Reaumur(Re)", ""}
    };
    String[][] speedUV = {
            {"Speed of light(C)", "3.3356E-9"},
            {"Miter/Second(m/s)", "1"},
            {"KMiter/Second(km/s)", "0.001"},
            {"KMiter/Hour(km/h)", "3.6"},
            {"Mile/Hour(m/h)", "2.236936"}
    };
    String[][] currencyUV = {
            {"US Dollar(USD)", "1"},
            {"Bangladesh Taka(BDT)", "104"},
            {"Euro(EUR)", "0.9247"},
            {"Pound Sterling(GBP)", "0.8282"},
            {"Indian Rupee(INR)", "81.4555"},
            {"Iranian Rial(IRR)", "42022.5"},
            {"Japanese Yen(JPY)", "128.6945"},
            {"Brazilian Real(BRL)", "5.1821"},
            {"Chinese Yuan(CNY)", "6.9627"}
    };
    String[][] powerUV = {
            {"Watt(W)", "1"},
            {"KWatt(kW)", "0.001"},
            {"Metric Horsepower(PS)", "0.0013596216"},
            {"Imperial Horsepower(hp)", "0.0013410221"},
    };
    String[][] pressureUV = {
            {"Pascal(Pa)", "1"},
            {"Bar", "1E-5"},
            {"Pound Per Square Inch(psi)", "0.000145038"},
            {"Standard Atmosphere", "9.8692e-6"},
            {"Torr", "0.00750062"}
    };
}
