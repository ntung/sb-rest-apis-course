package com.itersdesktop.javatechs.springboot;

public class ConversionDetails {
    private String fromUnit;
    private String toUnit;
    private float fromValue;
    private float toValue;

    public String toString() {
        return "From " + fromValue + " " + fromUnit + " To " + toValue + " " + toUnit;
    }
    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public float getFromValue() {
        return fromValue;
    }

    public void setFromValue(float fromValue) {
        this.fromValue = fromValue;
    }

    public float getToValue() {
        return toValue;
    }

    public void setToValue(float toValue) {
        this.toValue = toValue;
    }
}
