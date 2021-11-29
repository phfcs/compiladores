import java.util.LinkedList;
// 29/11/2021
// Pedro Henrique F. Cardoso dos Santos
public class rpn {

	public static void main(String[] args) {
		evalRPN("10 s +");
	}
		private static void evalRPN(String expr){
			LinkedList<Double> stack = new LinkedList<Double>();
			for (String token : expr.split("\\s")){
				if (token.equals("*")) {
					System.out.print("Token [type=PLUS");
					double segundoOperador = stack.pop();
					double primeiroOperador = stack.pop();
					stack.push(primeiroOperador * segundoOperador);
				} else if (token.equals("/")) {
					System.out.print("Token [type=PLUS");
					double segundoOperador = stack.pop();
					double primeiroOperador = stack.pop();
					stack.push(primeiroOperador / segundoOperador);
				} else if (token.equals("-")) {
					System.out.print("Token [type=PLUS");
					double segundoOperador = stack.pop();
					double primeiroOperador = stack.pop();
					stack.push(primeiroOperador - segundoOperador);
				} else if (token.equals("+")) {
					System.out.print("Token [type=PLUS");
					double segundoOperador = stack.pop();
					double primeiroOperador = stack.pop();
					stack.push(primeiroOperador + segundoOperador);
				} else if (token.equals("^")) {
					System.out.print("Token [type=PLUS");
					double segundoOperador = stack.pop();
					double primeiroOperador = stack.pop();
					stack.push(Math.pow(primeiroOperador, segundoOperador));
				} else {
					System.out.print("Token [type=NUM]");
					try {
						stack.push(Double.parseDouble(token+""));
					} catch (NumberFormatException e) {
	    					System.out.println("\nError: Unexpected character: " + token);
	    					return;
					}
				}
				System.out.println(",lexeme="+token+"]");
			}
			if (stack.size() > 1) {
				System.out.println("Erro, muitos operandores: " + stack);
				return;
			}
			System.out.println("Saida: " + stack.pop());
		}
	}
	 