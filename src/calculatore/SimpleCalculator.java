package calculatore;

import java.util.Scanner;

public class SimpleCalculator {

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		System.out.println("Basic Arithmetic Calculator");
		
		System.out.println("Enter the first number:");
		int num1= scan.nextInt();
		System.out.println("Enter the second number: ");
		int num2= scan.nextInt();
		System.out.println("Choose an operation (+,-,*,/)");
		char operation = scan.next().charAt(0);
		
		int result = 0;
		
			switch (operation){ 
				case'+':
					result = num1 + num2;
				break;
				
				case'-':
					result = num1 - num2; 
				break;
				
				case'*':
					result = num1 * num2;
				break;
				
				case'/':
					if (num2!=0) {result = num1 / num2;
		
					}else { System.out.println("Error. Can't divide with 0");
					} 
				break;
				default: System.out.println("Error: invalid operation");
			scan.close();
		}System.out.printf("Result: "+ result);
		

	}

}
