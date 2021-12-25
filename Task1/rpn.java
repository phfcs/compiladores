import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class rpn {
    

	public static void main (String[] args) throws FileNotFoundException {
		Stack<Double> stackUsable = new Stack<>();		
		Scanner input = new Scanner (new FileReader("CAMINHO"));
		
          while (input.hasNext()) {
			if (input.hasNextInt()) {
				stackUsable.push(input.nextInt());
			} else {
				char symbol = input.next().charAt(0);
				
                    int FirstValue = stackUsable.pop().intValue();
				int OtherValue = stackUsable.pop().intValue();
				
                    switch (symbol) {
                         case '+':
                              stackUsable.push(FirstValue+OtherValue);
                              break;
                         case '-':
                              stackUsable.push(FirstValue-OtherValue);
                              break;
                         case '*':
                              stackUsable.push(FirstValue*OtherValue);
                              break;
                         case '/':
                              stackUsable.push(FirstValue/OtherValue);
                              break;
                         default:
                              return Double.NaN;
				}
			}
		}
		System.out.println(stackUsable.get(0));
	}
     
}