import java.util.*;
import java.util.List;
public class JBSGroceryApp {

	private static String format;


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
				case "a": addToCart(scan, ogInventory, cart, cartQuantity);
					break;
				case "b": printCart(ogInventory, cart, cartQuantity);
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
	

	private static void printCart(List<Inventory> ogInventory, ArrayList<Integer>cart ,ArrayList<Integer>cartQuantity){
	
		int count = 0;
		
		for (int b : cart) {
			System.out.println(ogInventory.get(b).getName());
			System.out.println(ogInventory.get(b).getPrice());
			System.out.println(cartQuantity.get(count++));
		}
	}

	private static void addToCart(Scanner scan, List<Inventory> ogInventory, ArrayList<Integer> cart, ArrayList<Integer> cartQuantity) {
		
		int choice = Validator.getInt(scan, 
				"Which item would you like to order (1-12)? ", 1, 12);
		int quantity = Validator.getInt(scan, 
				"How many would you like? ", 1, 99);
		System.out.println();
		
		//add item index to cart
		cart.add(choice-1);
		//add the amount to cart
		cartQuantity.add(quantity);
		
		System.out.println("Added to cart!");
		
	}

	public static void printInventory(List<Inventory> item) {	
		int count = 1;
		String[] category = {"Appetizer", "Main course", "Dessert", "Drink"};
		int catIndex = 0;
		
		for (Inventory a: item) {
			
			if(category[catIndex].equalsIgnoreCase(a.getCategory()))
			{
				System.out.println();
				System.out.println(category[catIndex]);
				System.out.println();
				catIndex++;
			}
			
			System.out.println(count + ". " + a);
			count++;
		}	
		System.out.println();
	}
	
	private static void printMenu() {

		System.out.println("╔=========================================╗");
		System.out.println("\ta.) \tAdd to cart  \n"
				+ "\tb.) \tShow me my cart! \n"
				+ "\tc.) \tShow me your items! \n"
				+ "\td.) \tI want to checkout! ");
		
		System.out.println("╚=========================================╝");
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
