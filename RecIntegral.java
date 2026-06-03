

public class RecIntegral {
    private double a;
    private double b;
    private double step;
    private double result;

       /**
     * Конструктор с проверкой диапазона и условия a < b
     */
    public RecIntegral(double a, double b, double step, double result) 
            throws InvalidRecIntegralException {
        
        setA(a);
        setB(b);
        setStep(step);
        
        if (a >= b) {
            throw new InvalidRecIntegralException("Нижний предел (a) должен быть меньше верхнего предела (b)");
        }
        
        this.result = result;
    }
        public void setA(double a) throws InvalidRecIntegralException {
        if (a < 0.000001 || a > 1000000) {
            throw new InvalidRecIntegralException(
                "Значение a должно быть в диапазоне от 0.000001 до 1000000");
        }
        this.a = a;
    }

    public void setB(double b) throws InvalidRecIntegralException {
        if (b < 0.000001 || b > 1000000) {
            throw new InvalidRecIntegralException(
                "Значение b должно быть в диапазоне от 0.000001 до 1000000");
        }
        this.b = b;
    }

    public void setStep(double step) throws InvalidRecIntegralException {
        if (step < 0.000001 || step > 1000000) {
            throw new InvalidRecIntegralException(
                "Значение шага должно быть в диапазоне от 0.000001 до 1000000");
        }
        this.step = step;
    }
    

    // ==================== ГЕТТЕРЫ ====================

    public double getA() { return a; }
    public double getB() { return b; }
    public double getStep() { return step; }
    public double getResult() { return result; }

    /**
     * Метод вычисления интеграла методом трапеций
     */
    public double calculate() {
        double sq = 0.0;

        // Защита от некорректных данных (дополнительная)
        if (this.b <= this.a || this.step <= 0) {
            return Double.NaN;
        }

        double x = this.a;

        while (x < this.b) {
            double nextX = x + this.step;
            if (nextX > this.b) {
                nextX = this.b;
            }

            double h = nextX - x;

            double f_left = f(x);
            double f_right = f(nextX);

            if (Double.isNaN(f_left) || Double.isNaN(f_right) ||
                Double.isInfinite(f_left) || Double.isInfinite(f_right)) {
                return Double.NaN;
            }

            sq += h / 2.0 * (f_left + f_right);
            x = nextX;
        }

        this.result = sq;
        return sq;
    }

    /**
     * Подынтегральная функция: e^x / x
     */
    private double f(double x) {
        if (Math.abs(x) < 1e-12) {
            return Double.NaN; 
        }
        return Math.exp(x) / x;
    }

    /**
     * Метод для отображения в таблице
     */
    public Object[] toTableRow() {
        String resultStr = (Double.isNaN(result) || result == 0.0 && a == 0.0)
                ? "Ошибка (особенность в x=0)"
                : String.format("%.6f", result);

        return new Object[]{
            String.format("%.6f", a),
            String.format("%.6f", b),
            String.format("%.6f", step),
            resultStr
        };
    }
}