import java.util.LinkedList;

public class SimplexAlgo {
    private boolean forMax;
    private final int LINES;
    private final int COLUMNS;
    private final LinkedList<LinkedList<Integer>> COFS;
    private final LinkedList<LinkedList<String>> ALL_VARS_OF_EACH_EXP;
    private final LinkedList<String> ALL_VARS;
    private final LinkedList<Integer> B;
    private double[][] Matrix;

    public SimplexAlgo(LinkedList<LinkedList<Integer>> COFS, LinkedList<LinkedList<String>> ALL_VARS_OF_EACH_EXP, LinkedList<String> ALL_VARS, LinkedList<Integer> b , boolean forMax) {
        this.COFS = COFS;
        this.ALL_VARS_OF_EACH_EXP = ALL_VARS_OF_EACH_EXP;
        this.ALL_VARS = ALL_VARS;
        this.forMax = forMax;

        LINES = COFS.size();
        COLUMNS = ALL_VARS.size()+ LINES;
        B = b;

        addNewVars(LINES);

        Matrix = new double[LINES+1][COLUMNS+1];//column + 1: for b column // lines+1 : for z row
        FillInMatrix();

    }

    public void ShowMatrix(){
        for (int i = 0; i < LINES+1; i++){
            int j;
            for (j = 0; j < COLUMNS; j++)
                System.out.printf("%.3f\t|\t",Matrix[i][j] );
            System.out.printf("%.3f\t|\t\n",Matrix[i][j]);


        }

    }
    public void ShowB(){
        System.out.println(B);
    }

    public void StartAlgo(){
        int i = 0;
        while (!isAllNegatif(Matrix[LINES])){
            System.out.println("Modification N:"+i);
            UpdateMatrix();
            ShowMatrix();
            i++;
        }
    }
    private void UpdateMatrix(){
        double[] Pivot = Pivot();
        double[][] newMat = new double[LINES+1][COLUMNS+1];
        for (var v :
                Pivot) {
            System.out.print(v + "\t");
        }
        System.out.println();

        int row = (int) Pivot[1];
        for (int i = 0; i < COLUMNS+1; i++)
            newMat[row][i] = Matrix[row][i] / Pivot[0];

        for (int i = 0; i < LINES+1; i++) {
            if(i==row) continue;
            for (int j = 0; j < COLUMNS + 1; j++) {
                newMat[i][j] = Matrix[i][j] - Matrix[(int)Pivot[1]][j] * Matrix[i][(int)Pivot[2]] / Pivot[0];
            }
        }

        Matrix = newMat;
    }

    private boolean isAllNegatif(double[] table){
        for (var v : table)
            if (v > 0) return false;
        return true;
    }
    private double[] Pivot(){
        double[] pivot = new double[3];

        int[] MinMax = getMinMixOfTable(Matrix[LINES]);
        int pivotColumn = forMax? MinMax[1]:MinMax[0] ;

        double[] Bdivided = new double[B.size()];

        for (int i = 0; i < Bdivided.length; i++) {
            Bdivided[i] = B.get(i)/Matrix[i][pivotColumn];
        }

        int[] MinMaxRow = getMinMixOfTable(Bdivided);
        int pivotRow = forMax? MinMaxRow[0]:MinMaxRow[1] ;


        pivot[0] = Matrix[pivotRow][pivotColumn];
        pivot[1] = pivotRow;
        pivot[2] = pivotColumn;
        return pivot;
    }

    private int[] getMinMixOfTable(double[] table){
        int[] t = new int[2]; // min t[0] && max t[1]
        double min = table[0];
        double max = table[0];

        for (int i = 0; i < table.length; i++) {
            if(table[i] == 0) continue;

            if (min > table[i]){
                min = table[i];
                t[0] = i;
            }

            if (max < table[i]){
                max = table[i];
                t[1] = i;
            }
        }
        return t;
    }
    private void addNewVars(int LinesNumber){
        for (int i = 0; i < LinesNumber; i++) {
            char c =(char) ('a'+i);
            ALL_VARS.add( c + "");
        }
    }
    private void FillInMatrix(){
      LinkedList<Integer> v;
      int n = 0;
      boolean bool;
        int i;
        for ( i = 0; i < LINES; i++) {
            v = COFS.get(i);
            bool = false;
            int j;
            for (j = 0; j < COLUMNS; j++) {
                if (j < v.size() )
                    Matrix[i][j] = v.get(j);
                else if (!bool && j - v.size() > n -1){
                    Matrix[i][j] = 1;
                    n++; bool=true;
                }
                else
                    Matrix[i][j] = 0;

            }
            Matrix[i][j] = B.get(i);
        }

        int j;
        for (j = 0; j < COLUMNS + 1; j++) {
            if(j+1<CalculateMathExpression.CoefObjectif.size())
                Matrix[i][j] = CalculateMathExpression.CoefObjectif.get(j);
            else
                Matrix[i][j] = 0;
        }

        Matrix[i][j-1] = CalculateMathExpression.CoefObjectif.getLast();
    }



}
