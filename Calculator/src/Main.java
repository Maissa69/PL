public class Main {
    public static void main(String[] args) {
        System.out.println("test 2 : ");
        var v = new CalculateMathExpression("3X1+5X2+78");
        var l = new CalculateMathExpression("4X1+X2+36");

        CalculateMathExpression.setObjectiveEquation("+5X1+4X2+0");
        CalculateMathExpression.Calculate(true);
/*

        System.out.println("test 3 : ");
        var v = new CalculateMathExpression("3X1+10X2+5X3+120");
        var l = new CalculateMathExpression("5X1+2X2+8X3+6");
        var Ml = new CalculateMathExpression("8X1+10X2+3X3+105");

        CalculateMathExpression.setObjectiveEquation("+3X1+4X2+X3+0");
        CalculateMathExpression.Calculate(true);

         System.out.println("test 2 : ");
        var v = new CalculateMathExpression("3X1+5X2+78");
        var l = new CalculateMathExpression("4X1+X2+36");

        CalculateMathExpression.setObjectiveEquation("+5X1+4X2+0");
        CalculateMathExpression.Calculate(true);

        System.out.println("test 1 : ");
        var v = new CalculateMathExpression("2X1+X2+3");
        var l = new CalculateMathExpression("3X1+5X2+9");
        var ml = new CalculateMathExpression("+X1+3X2+5");

        CalculateMathExpression.setObjectiveEquation("+X1+4X2+0");
        CalculateMathExpression.Calculate(true);*/
    }
}