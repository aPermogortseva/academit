package permogortseva.temperature;

public class TemperatureConverter {
    double inputValue;
    double outputValue;

    String inputScale;
    String outputScale;

    public TemperatureConverter(double inputValue, String inputScale, String outputScale) {
        this.inputValue = inputValue;
        this.inputScale = inputScale;
        this.outputScale = outputScale;
    }

    private double convertCelsiusToFahrenheit() {
        outputValue = (inputValue * 1.8) + 32;

        return outputValue;
    }

    private double convertCelsiusToKelvin() {
        outputValue = inputValue + 273.15;

        return outputValue;
    }

    private double convertKelvinToCelsius() {
        outputValue = inputValue - 273.15;

        return outputValue;
    }

    private double convertKelvinToFahrenheit() {
        outputValue = (inputValue - 273.15) * 1.8 + 32;

        return outputValue;
    }

    private double convertFahrenheitToKelvin() {
        outputValue = (inputValue - 32) / 1.8 + 273.15;

        return outputValue;
    }

    private double convertFahrenheitToCelsius() {
        outputValue = (inputValue - 32) / 1.8;

        return outputValue;
    }

    public double convert() {
        if (inputScale.equals("Цельсий")) {
            if (outputScale.equals("Кельвин")) {
                return convertCelsiusToKelvin();
            } else if (outputScale.equals("Фаренгейт")) {
                return convertCelsiusToFahrenheit();
            } else {
                return inputValue;
            }
        } else if (inputScale.equals("Кельвин")) {
            if (outputScale.equals("Цельсий")) {
                return convertKelvinToCelsius();
            } else if (outputScale.equals("Фаренгейт")) {
                return convertKelvinToFahrenheit();
            } else return inputValue;
        } else if (outputScale.equals("Цельсий")) {
            return convertFahrenheitToCelsius();
        } else if (outputScale.equals("Кельвин")) {
            return convertFahrenheitToKelvin();
        } else {
            return inputValue;
        }
    }
}
