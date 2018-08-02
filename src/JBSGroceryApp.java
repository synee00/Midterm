import java.util.*;

public class JBSGroceryApp {

	public static void main(String[] args) {
		//Create new array lift for inventory 
		Scanner scan = new Scanner(System.in);
		List<Inventory> ogInventory = new ArrayList <Inventory>();
		ArrayList<Integer> cart = new ArrayList <Integer>();
		ArrayList<Integer> cartQuantity = new ArrayList <Integer>();
		String appRestart = "yes";
		String isShopping = "yes";
		double subtotal;
		double grandTotal;
		String paymentType = null;
		String choice = null;
		
		//fill inventory
		//ogInventory.add(new Inventory("Escargot" , "Main course" , "Fancy delicious snails", 50.99));
		ogInventory = GroceryFileUtil.readFile();
		
		
		
		
		//Start program
		
		//do {
		//Start shopping
		
			System.out.println("Welcome to JBS Gourmet Gorocery!");
			printInventory(ogInventory);
			printMenu();
			choice = Validator.getStringMatchingRegex(scan,"What would you like to do next (a-d)? ", "[a-dA-D]");
			
			switch(choice.toLowerCase())
			{
				case "a": addInventory();
					break;
				case "b": printCart();
					break;
				case "c": printInventory(ogInventory);
					break;
				case "d": checkout(scan, ogInventory, cart, cartQuantity);
					break;
				default:
					break;
			}
			
			
		//add cart
		
		//finish shopping? update loop
		
		//subtotal, tax, grand total print
		
		//get type of payment
		
		//give change if applicable
		
		//print receipt to file
		
		//shop again? loop update
			
			
			
			
		//} while (//restartApp loop);
	}

	private static void printCart() {
		// TODO Auto-generated method stub
		
	}

	private static void addInventory() {
		// TODO Auto-generated method stub
		
	}

	public static void printInventory(List<Inventory> item) {
		
		for (Inventory a: item) {
			System.out.println(a);
		}	
	}
	
	private static void printMenu() {

		System.out.println("a.) \tAdd to cart  \n"
				+ "b.) \tShow me my cart! \n"
				+ "c.) \tShow me your items! \n"
				+ "d.) \tI want to checkout! \n");
	}
	
	
	private static void checkout(Scanner scnr, List<Inventory> list1, ArrayList<Integer> list2, ArrayList<Integer> list3) {
		
		//declare variables
		double sum = 0;
		double tax = 0;
		double grandTotal = 0;
		String payType = null; 
		double change = 0;
		boolean payCheck = true;
		double collect = 0;
		String cardNum = null;
		String checkNum = null;
		
		//print initial list so user knows list. Calculates total tax and grand total.
		System.out.println("JBS Gourmet Grocery Receipt");
		System.out.println("Item/t/t/tQuantity/t/t/tCost Per");
		System.out.println("*********************************************");
		for (int i =0; i<list2.size(); i++) {
			System.out.println(list1.get(i).getName() + "/t/t/t" + list3.get(i) + "/t/t/t" + list1.get(i).getPrice());
			sum = (list3.get(i) * list1.get(i).getPrice()) + sum;			
		}
		tax = sum * .06;
		grandTotal = tax + sum;
		
		//prints out results
		System.out.println("/t/t/t/t/t/t/t Total: $" + sum );
		System.out.println("/t/t/t/t/t/t/t Tax: $" + tax );
		System.out.println("/t/t/t/t/t/t/t Grand Total: $" + grandTotal );
		
		System.out.println("*********************************************");
		
		//collects the payment method and validates it exists
		System.out.println("How would you like to pay? (cash, credit, check)");
		payType = scnr.nextLine();
		
		//while loop sets up a non response in in the loop.
		while (payCheck == true) {
			
			//cash option
			if (payType.equals("cash")) {
				collect = Validator.getDouble(scnr, "How much cash do you have? (0.00 format)", grandTotal, 1e9);
				change = collect - grandTotal;
				
				System.out.println("Your final receipt:");
				System.out.println("*********************************************");
				System.out.println("JBS Gourmet Grocery Receipt");
				System.out.println("Item/t/t/tQuantity/t/t/tCost Per");
				System.out.println("*********************************************");
				
				for (int j =0; j<list2.size(); j++) {
					
					System.out.println(list1.get(j).getName() + "/t/t/t" + list3.get(j) + "/t/t/t" + list1.get(j).getPrice());
					sum = (list3.get(j) * list1.get(j).getPrice()) + sum;			
				}
				
				System.out.println("/t/t/t/t/t/t/t Total: $" + sum );
				System.out.println("/t/t/t/t/t/t/t Tax: $" + tax );
				System.out.println("/t/t/t/t/t/t/t Grand Total: $" + grandTotal );
				System.out.println("/t/t/t/t/t/t/t Amount Tendered: $" + collect );
				System.out.println("/t/t/t/t/t/t/t Change: $" + change );
				payCheck = false;
				
			//credit option
			} else if (payType.equals("credit")) {
				
				cardNum = Validator.getStringMatchingRegex(scnr,"Please enter the card number. (#### #### #### ####)","[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}");
						
				System.out.println("Your final receipt:");
				System.out.println("*********************************************");
				System.out.println("JBS Gourmet Grocery Receipt");
				System.out.println("Item/t/t/tQuantity/t/t/tCost Per");
				System.out.println("*********************************************");
				
				for (int j =0; j<list2.size(); j++) {
					
					System.out.println(list1.get(j).getName() + "/t/t/t" + list3.get(j) + "/t/t/t" + list1.get(j).getPrice());
					sum = (list3.get(j) * list1.get(j).getPrice()) + sum;			
				}
				
				System.out.println("/t/t/t/t/t/t/t Total: $" + sum );
				System.out.println("/t/t/t/t/t/t/t Tax: $" + tax );
				System.out.println("/t/t/t/t/t/t/t Grand Total: $" + grandTotal );
				System.out.println("/t/t/t/ Charged to card: " + cardNum );
				
				
				payCheck = false;
				
			//check option
			} else if (payType.equals("check")) {
				
				checkNum = Validator.getStringMatchingRegex(scnr,"Please enter the check number. ()","[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}");
				
				System.out.println("Your final receipt:");
				System.out.println("*********************************************");
				System.out.println("JBS Gourmet Grocery Receipt");
				System.out.println("Item/t/t/tQuantity/t/t/tCost Per");
				System.out.println("*********************************************");
				
				for (int j =0; j<list2.size(); j++) {
					
					System.out.println(list1.get(j).getName() + "/t/t/t" + list3.get(j) + "/t/t/t" + list1.get(j).getPrice());
					sum = (list3.get(j) * list1.get(j).getPrice()) + sum;			
				}
				
				System.out.println("/t/t/t/t/t/t/t Total: $" + sum );
				System.out.println("/t/t/t/t/t/t/t Tax: $" + tax );
				System.out.println("/t/t/t/t/t/t/t Grand Total: $" + grandTotal );
				System.out.println("/t/t/t/ Charged to check number: " + checkNum );
				
				payCheck = false;
				
			//if the payment method doesnt match
			} else {
				System.out.println("Please try your response again.");
				payCheck = true;
			}
		
		}
		
		
	}
}
