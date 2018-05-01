/*
 * TableMain by Sean Hill 06/29/17
 */
import java.util.Scanner;

public class TableMain {

	public static void main(String[] args) {
		final String DEF_STRING = "192.168.0.";
		
		String choice = "";
		Network forgetWork = new Network();
		Scanner key = new Scanner(System.in);
		
		do{
			System.out.println("Please select a choice (0/1/2/q)");
			System.out.println("0) Display Nodes");
			System.out.println("1) Display a route table");
			System.out.println("2) Alter network");
			System.out.println("q) Quit");
			choice = key.nextLine();
			
			//if-else chain based on choice
			if(choice.equals("0")) {
				System.out.println("Nodes");
				System.out.println(forgetWork);
			}else if(choice.equals("1")) {
				System.out.println("Please select a node");
				choice = key.nextLine();
				String target = DEF_STRING + choice;
				System.out.println("Seq \t\tDestination \t Port \t Cost \t Time");
				System.out.println(forgetWork.findAll(target));
			} else if (choice.equals("2")) {
				forgetWork.alter();
			}else if (choice.toLowerCase().equals("q")) {
				System.out.println("Goodbye @(*0*)@");
			}
		}while(!choice.toLowerCase().equals("q"));
		key.close();
	}
	

}
