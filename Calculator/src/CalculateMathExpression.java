
import java.util.LinkedList;
import java.util.List;

public class CalculateMathExpression {
    private static Point[] points;

    public static LinkedList<String> varsObjectif = new LinkedList<>();
    public static LinkedList<Integer> CoefObjectif = new LinkedList<>();
    public static String ObjectiveEquation;
    public static LinkedList<Integer> B = new LinkedList<>();
    public static LinkedList<LinkedList<Integer>> Coefs =new LinkedList<>();
    public static LinkedList<LinkedList<String>> allVarsOfEachExp =new LinkedList<>();
    public static LinkedList<String> allVars =new LinkedList<>();


    public  LinkedList<String> vars = new LinkedList<>();
    public  LinkedList<Integer> Coef = new LinkedList<>();
    public  LinkedList<String> Exps = new LinkedList<>();
    public CalculateMathExpression(String Exp){
        addEquation(Exp);
    }




    public static void  Calculate(boolean forMax){
        System.out.println("Objectif :");
        System.out.println(CoefObjectif);
        System.out.println("Matrix :");
        var v = new SimplexAlgo(CalculateMathExpression.Coefs,CalculateMathExpression.allVarsOfEachExp,
                CalculateMathExpression.allVars, CalculateMathExpression.B,forMax);
        v.ShowMatrix();
        v.ShowB();

        System.out.println("New Matrix :");
        v.StartAlgo(CoefObjectif.size()-1);
        v.ShowSolutions();



        System.out.println("ALL vars : ");
        for (var p :
                CalculateMathExpression.allVars) {
            System.out.println(p);
        }

       /* var pp = SimplexAlgo.getPoint(0,Coefs.get(0),B.get(0));
        System.out.println(pp.Y);*/ // Comment Point
    }


    private void addEquation(String Exp){
        Exps.add(Exp);
        ExtractAllCoef(Exp);
        Coefs.add(this.Coef);
        allVarsOfEachExp.add(this.vars);
        AllVarsWithOutRep(this.vars);
    }

    public void AllVarsWithOutRep(LinkedList<String> vars ){
        boolean bool;
        for (var v : vars) {
            bool = false;
            for (var va : allVars)
                if ( va.equals(v)) {
                    bool = true;
                     break;
                }
            if (!bool) allVars.add(v);
        }
    }


    public static String getObjectiveEquation() {
        return ObjectiveEquation;
    }
    public static void setObjectiveEquation(String objectiveEquation ) {
        ObjectiveEquation = objectiveEquation;
        ExtractAllCoefObjectif(objectiveEquation);

    }




    private void ExtractAllCoef(String Exp){
        LinkedList<Character> numbers = new LinkedList<>();

        for (int i = 0; i < Exp.length(); i++) {
            if(Exp.charAt(i) == 'X') {
                // Extract vars ...
                String str = "";

                while (Exp.charAt(i) != '-' && Exp.charAt(i) != '+'){
                    str += (Exp.charAt(i));
                    i++;

                    if (i ==  Exp.length()) break;
                }
                vars.add(str);
                numbers.add('|');
                i--;
            }else {
                // Extract Coefs ...
                numbers.add(Exp.charAt(i));
            }
        }

        Coefs(numbers);
        int j = Coef.size();

        var reverse = new Integer[j];
        for (int i = 0; i < reverse.length; i++) {
            j--;
            reverse[i] = Coef.get(j);
        }

        Coef.clear();
        Coef.addAll(List.of(reverse));

        B.add(Coef.getLast());
        Coef.removeLast();
    }
    private void Coefs(LinkedList<Character> numbers){
        int v = 0;
        double n = 0;
        for (int i = numbers.size() - 1 ; i > -1 ; i--) {

            if (numbers.get(i) < '0' || numbers.get(i) > '9'){

                if (numbers.get(i) == '-'){
                    v *= -1;
                    if (i==0 && numbers.get(i+1) == '|' ){
                        v = 0; n=0;
                        Coef.add(-1); continue;
                    }
                }

                if(numbers.get(i) != '|'){
                    i-=1;
                    if(i==-1|| numbers.get(i) == '|'  )
                       if (numbers.get(i+2) == '|'){
                           v = 0; n=0;
                           Coef.add(1); continue;
                       }
                }
                Coef.add(v);
                v = 0; n=0;
            }else {
                v += Integer.parseInt(String.valueOf(numbers.get(i))) * Math.pow(10,n);
                n++;
            }
        }

        if (v != 0) Coef.add(v);

        if (numbers.get(0) == '|') Coef.add(1);
    }


    private static void ExtractAllCoefObjectif(String Exp){
        LinkedList<Character> numbers = new LinkedList<>();

        for (int i = 0; i < Exp.length(); i++) {
            if(Exp.charAt(i) == 'X') {
                // Extract vars ...
                String str = "";

                while (Exp.charAt(i) != '-' && Exp.charAt(i) != '+'){
                    str += (Exp.charAt(i));
                    i++;

                    if (i ==  Exp.length()) break;
                }
                varsObjectif.add(str);
                numbers.add('|');
                i--;
            }else {
                // Extract Coefs ...
                numbers.add(Exp.charAt(i));
            }
        }

        CoefsObjectif(numbers);
        int j = CoefObjectif.size();

        var reverse = new Integer[j];
        for (int i = 0; i < reverse.length; i++) {
            j--;
            reverse[i] = CoefObjectif.get(j);
        }

        CoefObjectif.clear();
        CoefObjectif.addAll(List.of(reverse));
    }

    private static void CoefsObjectif(LinkedList<Character> numbers){
        int v = 0;
        double n = 0;
        for (int i = numbers.size() - 1 ; i > -1 ; i--) {

            if (numbers.get(i) < '0' || numbers.get(i) > '9'){

                if (numbers.get(i) == '-'){
                    v *= -1;
                    if (i==0 && numbers.get(i+1) == '|' ){
                        v = 0; n=0;
                        CoefObjectif.add(-1); continue;
                    }
                }

                if(numbers.get(i) != '|'){
                    i-=1;
                    if(i==-1|| numbers.get(i) == '|'  )
                        if (numbers.get(i+2) == '|'){
                            v = 0; n=0;
                            CoefObjectif.add(1); continue;
                        }
                }
                CoefObjectif.add(v);
                v = 0; n=0;
            }else {
                v += Integer.parseInt(String.valueOf(numbers.get(i))) * Math.pow(10,n);
                n++;
            }
        }

        if (v != 0) CoefObjectif.add(v);

        if (numbers.get(0) == '|') CoefObjectif.add(1);
    }
}
