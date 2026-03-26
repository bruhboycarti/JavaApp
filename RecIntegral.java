/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 */
// RecIntegral.java
public class RecIntegral {
    private double a;
    private double b;
    private double step;
    private double result;

    public RecIntegral(double a, double b, double step, double result) {
        this.a = a;
        this.b = b;
        this.step = step;
        this.result = result;
    }

    // Геттеры
    public double getA() { return a; }
    public double getB() { return b; }
    public double getStep() { return step; }
    public double getResult() { return result; }
    public void setA(double a) { this.a = a; }
    public void setB(double b) { this.b = b; }
    public void setStep(double step) { this.step = step; }
    public void setResult(double result) { this.result = result; }
    
    // Сеттер для результата (нужен при вычислении)

public double calculate() {
    double sq = 0.0;
    
    // защита от некорректных входных данных
    if (this.b <= this.a || this.step <= 0) {
        return Double.NaN;
    }

    double x = this.a;
    
    while (x < this.b) {
        // определяем правую границу текущей трапеции
        double nextX = x + this.step;
        if (nextX > this.b) {
            nextX = this.b;
        }
        
        double h = nextX - x;           // реальная ширина текущего интервала
        
        double f_left  = f(x);          // значение в левой точке
        double f_right = f(nextX);      // значение в правой точке
        
        // если где-то деление на ноль или очень близко к нулю → ошибка
        if (Double.isNaN(f_left) || Double.isNaN(f_right) || 
            Double.isInfinite(f_left) || Double.isInfinite(f_right)) {
            return Double.NaN;
        }
        
        // формула трапеций для одного интервала
        sq += h / 2.0 * (f_left + f_right);
        
        x = nextX;
    }
    
    this.result = sq;   // сохраняем результат в поле класса
    return sq;
}


/**
 * Подынтегральная функция: e^x / x
 */
private double f(double x) {
    if (Math.abs(x) < 1e-12) {     // защита от деления на очень малое число
        return Double.NaN;
    }
    return Math.exp(x) / x;
}
    
    // Метод преобразования в массив для JTable
    public Object[] toTableRow() {
    String resultStr = Double.isNaN(result) 
        ? "Ошибка (особенность в x=0)" 
        : String.format("%.6f", result);
    
    return new Object[] { a, b, step, resultStr };
}
}
