public class Main {
    public static void main(String[] args) {
        var v = new CalculateMathExpression("2X1+3X2+120");
        var l = new CalculateMathExpression("4X1+2X2+100");



        CalculateMathExpression.setObjectiveEquation("+6X1+4X2+0");
        CalculateMathExpression.Calculate(true);
    }
}