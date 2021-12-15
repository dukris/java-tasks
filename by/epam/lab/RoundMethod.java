package by.epam.lab;

public enum RoundMethod {
    FLOOR {
        double roundFunction(double d) {
            return Math.floor(d);
        }
    },
    ROUND {
        double roundFunction(double d) {
            return Math.round(d);
        }
    },
    CEIL {
        double roundFunction(double d) {
            return Math.ceil(d);
        }
    };

    abstract double roundFunction(double value);

    public int round(double roundedValue, int d) {
        int tenPow = myPow(d);
        return (int) roundFunction(roundedValue / tenPow) * tenPow;
    }

    private int myPow(int grade) {
        int result = 1;
        for(int i=1; i <= grade; i++){
            result *= 10;
        }
        return result;
    }
}
