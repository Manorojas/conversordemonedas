public class Conversor {
    private double conversionRate;
    private double conversionResult;

    public Conversor(double conversionRate, double conversionResult) {
        this.conversionRate = conversionRate;
        this.conversionResult = conversionResult;
    }

    public Conversor(ConversorResultado miConversorResultado) {
        this.conversionRate = Double.parseDouble(miConversorResultado.conversion_rate());
        this.conversionResult = Double.parseDouble(miConversorResultado.conversion_result());

    }

    public double getConversionRate() {
        return conversionRate;
    }

    public double getConversionResult() {
        return conversionResult;
    }
}
