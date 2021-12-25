import java.util.*;
public class calculadora {
 
    // Commands
    private static final String QUIT = "sair";
    private static final String VAR = "variavel";
    private static final String CLEAR = "limpar";

    private static ArrayList<String> variableNames = new ArrayList<String>();
    private static ArrayList<Double> variableValues = new ArrayList<Double>();
    private static Stack<Double> operationStack = new Stack<Double>();

    public static void main(String[] args) {
        System.out.println("Calculadora de Pedro Santos");
        Scanner scan = new Scanner(System.in);
        System.out.print("->");
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            evaluate(s);
            System.out.print("->");
        }
    }

    public static void evaluate(String s) {

        if(s.equals(QUIT)) {
            System.exit(0);
        } else if(s.equals(VAR)) {
            for(int i = 0; i < variableNames.size(); i++) {
                System.out.println("\t" + variableNames.get(i) + ": " + variableValues.get(i));
            }
        } else if(s.equals(CLEAR)) {
            variableNames.clear();
            variableValues.clear();
        } else {
            calculate(s);
        }
    }

    public static void calculate(String s) {
        ArrayList<String> input = new ArrayList<String>();
        Collections.addAll(input, s.trim().split(" "));
        input.removeAll(Arrays.asList(null, ""));
        if(input.size() == 1) {
            double d = getValue(s);
            if(!Double.isNaN(d)) System.out.println("\t" + d);
            return;
        }

        boolean hasVariableAssignment = input.contains("=");
        String var = "";
        int startIndex = 0;
        if(hasVariableAssignment) {
            var = input.get(0);
            startIndex = 2;
        } else {
            startIndex = 0;
        }
        for(int i = startIndex; i < input.size(); i++) {
            String n = input.get(i);
            if(isOperator(n)) {
                if(operationStack.size() > 1) {
                    operationStack.push(doOperation(n));
                } else {
                    System.out.println("\tOperação Invalida");
                    return;
                }
            } else {
                double d = getValue(n);
                if(!Double.isNaN(d)) {
                    operationStack.push(d);
                } else {
                    operationStack.clear();
                    return;
                }
            }
        }
        double result = operationStack.pop();
        if(operationStack.size() > 0) {
            System.out.println("\tOperação Invalida!");
            operationStack.clear();
            return;
        }

        if(hasVariableAssignment) {
            replaceAddValue(var, result);
        }
        System.out.println("\t" + result);
    }

    public static double doOperation(String s) {
        char op = s.charAt(0);
        double a = operationStack.pop();
        double b = operationStack.pop();
        switch (op) {
            case '+':
                return b + a;
            case '-':
                return b - a;
            case '*':
                return b * a;
            case '/':
                return b / a;
            default:
                return Double.NaN;
        }
    }

    public static double getValue(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ex) {
            try {
                return variableValues.get(variableNames.indexOf(s));
            } catch (Exception e) {
                System.out.printf("\t%s Não encontrado.\n", s);
                return Double.NaN;
            }
        }
    }

    public static void replaceAddValue(String var, double value) {

        if(variableNames.contains(var)) {
            int index = variableNames.indexOf(var);
            variableValues.set(index, value);
        } else {
            variableNames.add(var);
            variableValues.add(value);
        }
    }

    public static boolean isOperator(String s) {
        char c = s.charAt(0);
        return c == '+' || c == '-' || c == '/' || c == '*';
    }
}