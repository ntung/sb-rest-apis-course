package com.itersdesktop.javatechs.springboot;

public class LengthConverter {
    static void km2Mile(ConversionDetails details) {
        float km = details.getFromValue();
        float miles = km * 0.621371f;
        details.setToValue(miles);
    }

    static void mile2Km(ConversionDetails details) {
        float miles = details.getFromValue();
        float km = miles * 1.60934f;
        details.setToValue(km);
    }
}
