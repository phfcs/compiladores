    import java.util.Scanner;
    import java.util.Stack;

    public class RPN2 
    {
    private Stack<Integer> stack;

     public RPN2()
        {
            stack = new Stack<>(); //creates stack 
        }
    void clearStack(){ //add clear stach method 
            stack.clear();
        }
     public static void main(String[] args)
        {
            String expression, again;
            int result;

            Scanner keyboard = new Scanner(System.in);
            RPN2 evaluator = new RPN2();
            do
            {  
                evaluator.clearStack();
                RPN2 evaluator = new RPN2();
                System.out.println("Enter a valid post-fix expression one token " +
                                   "at a time with a space between each token (e.g. 5 4 + 3 2 1 - + *)");
                System.out.println("Each token must be an integer or an operator (+,-,*,/)");
                expression = keyboard.nextLine();

                result = evaluator.evaluate(expression);
                System.out.println();
                System.out.println("That expression equals " + result);

                System.out.print("Evaluate another expression [Y/N]? ");
                again = keyboard.nextLine();
                System.out.println();
            }
            while (again.equalsIgnoreCase("y"));
       }

    public int evaluate(String expr) 
        {
            int op1, op2, result = 0;
            String token;
            Scanner parser = new Scanner(expr);     

            while (parser.hasNext())        
            {
                token = parser.next();          

                if (isOperator(token))  //if operator pop 
                {
                    op2 = (stack.pop()).intValue();
                    op1 = (stack.pop()).intValue();
                    result = evaluateSingleOperator(token.charAt(0), op1, op2);     //
                    stack.push(new Integer(result));
                }
                else
                    stack.push(new Integer(Integer.parseInt(token)));       
            }

            return result;
        }

     private boolean isOperator(String token)
        {
            return ( token.equals("+") || token.equals("-") ||
                     token.equals("*") || token.equals("/") || token.equals("%") );

        }

     private int evaluateSingleOperator(char operation, int op1, int op2)
        {
            int result = 0;

            switch (operation)
            {
                case '+':
                    result = op1 + op2;
                    break;
                case '-':
                    result = op1 - op2;
                    break;
                case '*':
                    result = op1 * op2;
                    break;
                case '/':
                    result = op1 / op2;
                    break;
                case '%':
                    result = op1 % op2;
                    break;
            }

            return result;
        }

}