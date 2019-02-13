package Questions;
/** Rally Health Coding Puzzle 
 *  @author Kavya Gupta (gupta.kavya@gmail.com)
 *  Date: 2019/01/15
 */
import java.util.ArrayList;
import java.util.List;

/*Write a program that accepts an integer (N) and sequences of digits, and fills in 0 or more operators (+, -,
and * -- we’re disallowing division for this exercise) to that sequence so that a mathematical expression is
generated that totals N. For example, consider the input sequence 2100100. Operators can be inserted
in a variety of ways that would evaluate to 2000. Here are some examples:
*/
public class RallyHealth {
		public static void main(String[] args) {
			String input = args[0];
			int target = Integer.parseInt(args[1]);
			List<String> result = fillBinaryOperators(input, target);
			if(result.isEmpty()){
				System.out.println("Impossible");
			}else{
				result.forEach(System.out::println);
			}
		}

		/*
		 * Returns a list of possible sequences after adding binary operators to the number
		 * 
		 * */
		public static List<String> fillBinaryOperators(String number, int target) {
			List<String> result = new ArrayList<String>();
			if (number == null || number.length() == 0)
				return result;
			fillOperatorMethod(result, "", number, target, 0, 0, 0);
			return result;
		}

		/*
		 * Recursively adds the binary operators to the each digit of the number. 
		 * 
		 * */
		public static void fillOperatorMethod(List<String> result, String sequence, String number, int target, int position, long evaluatedValue, long prevDigitMultiplied) {
			if (position == number.length()) {
				if (target == evaluatedValue)
					result.add(sequence);
				return;
			}
			for (int i = position; i < number.length(); i++) {
				if (i != position && number.charAt(position) == '0')
					break;
				long current = Long.parseLong(number.substring(position, i + 1));
				if (position == 0) {
					fillOperatorMethod(result, sequence + current, number, target, i + 1, current, current);
				} else {
					//Add
					fillOperatorMethod(result, sequence + "+" + current, number, target, i + 1, evaluatedValue + current, current);

					//Subtract
					fillOperatorMethod(result, sequence + "-" + current, number, target, i + 1, evaluatedValue - current, -current);

					//Multiply
					fillOperatorMethod(result, sequence + "*" + current, number, target, i + 1, evaluatedValue - prevDigitMultiplied + prevDigitMultiplied * current, prevDigitMultiplied * current);
				}
			}
		}
	}


