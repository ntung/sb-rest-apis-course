package net.biomodels.jummp.core.service.fileformat;

public class UnitConverter {
    public static void convert(ConversionDetails details) throws UnitConversionException {
        String fromUnit = details.getFromUnit();
        String toUnit = details.getToUnit();

        if (fromUnit.equals("km") && toUnit.equals("mile")) {
            LengthConverter.km2Mile(details);
        } else if (fromUnit.equals("mile") && toUnit.equals("km")) {
            LengthConverter.mile2Km(details);
        } else {
            throw new UnitConversionException("Invalid From and To Units");
        }
    }
}
