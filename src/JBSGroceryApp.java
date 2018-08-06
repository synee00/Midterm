/*Jasmine Allen, Brian Seyferth, Sharde Smith
 * Program: User can buy items from store and simulate a transaction.
 * August 3, 2018
 */


import java.util.*;
import java.util.List;
public class JBSGroceryApp {

	public static void main(String[] args) {
		//Create new array lift for inventory and declares variables
		Scanner scan = new Scanner(System.in);
		List<Inventory> ogInventory = new ArrayList <Inventory>();
		ArrayList<Integer> cart = new ArrayList <Integer>();
		ArrayList<Integer> cartQuantity = new ArrayList <Integer>();
		String appRestart = "yes";
		String isShopping = "yes";
		String choice = null;
		
		//reads file and fills inventory ArrayList
		ogInventory = GroceryFileUtil.readFile();
		
		//Do while loop to setup the program restart option.
		do {
			
			//prints out initial welcome statement
			System.out.println("Welcome to JBS Gourmet Gorocery!");
			printInventory(ogInventory);
			
			//clear cart for new transactions
			cart.clear();
			cartQuantity.clear();
			
			//his do while loop makes sure the user can keep shopping until they checkout
			do {
				
				//prints menu and collects a valid response from user
				printMenu();
				choice = Validator.getStringMatchingRegex(scan,"What would you like to do next (a-d)? ", "[a-dA-D]");
				
				//switch statement sets up the different options based on valid user response.
				//methods are used to call the corresponding selection.
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
				
				isShopping = choice;
				
				//continue shopping unless they select d checkout
			} while (!isShopping.matches("[dD].*"));
			
			//prints out a thank you for shopping message
			System.out.println("Thank you for shopping with us!");
			
			//asks if they would like to start over and restarts accordingly
			appRestart = Validator.getStringMatchingRegex(scan, "Would you like to shop with us again?", "[a-z, A-Z].*");
		}while(appRestart.matches("[yY].*"));
		
		
		//prints out an exit message to alert user program is over.
		System.out.println("Goodbye. Come again!");
		
	}
	
	// This is the method to print out your cart or "show your cart".
	private static void printCart(List<Inventory> ogInventory, ArrayList<Integer>cart ,ArrayList<Integer>cartQuantity){
	
		int count = 0;
		System.out.println();
		//used tabs to format the cart labels out 	
		System.out.printf("%-32s %-6s        %s\n","Item", "Quantity", "Price Per Item");
		System.out.println("");
		//used the tabs to format the quantity and prices of the cart
		for (int b : cart) {
			System.out.printf("%-32s %s %s    %s%.2f\n", ogInventory.get(b).getName() ,"x", cartQuantity.get(count++) ,"\t $", ogInventory.get(b).getPrice());
			System.out.println();
			System.out.println();
		}
	}
	//adds item to cart via arraylists
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
	
	//prints the store inventory
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
	
	//prints the store menu options
	private static void printMenu() {

		System.out.println("╔=========================================╗");
		System.out.println("\ta.) \tAdd to cart  \n"
				+ "\tb.) \tShow me my cart! \n"
				+ "\tc.) \tShow me your items! \n"
				+ "\td.) \tI want to checkout! ");
		
		System.out.println("╚=========================================╝");
	}
	
	//checks out when user is ready
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
		String cardExp = null;
		String cardCvv = null;
		String collect1 = null;
		boolean cardExp1 = true;
		String checkNum = null;
		
		//print initial list so user knows list. Calculates total tax and grand total.
		System.out.println();

		
		//prints out the top of the receipt
		System.out.println("JBS Gourmet Grocery Receipt");
		System.out.printf("%-32s %-6s        %s\n","Item", "Quantity", "Price Per Item");
		System.out.println("***************************************************************");
		
		//prints out the list of items in the cart, there quantity, and price
		for (int i =0; i<list2.size(); i++) {
			System.out.printf("%-32s %s %s    %s%.2f\n",list1.get((list2.get(i))).getName(), "x", list3.get(i), "\t $", list1.get((list2.get(i))).getPrice());
			sum = (list3.get(i) * list1.get((list2.get(i))).getPrice()) + sum;			
		}
		
		//calculates tax and grand total and rounds correctly for visibility and calculations.
		tax = sum * .06;
		grandTotal = tax + sum;
		grandTotal = (double)Math.round(grandTotal * 100d) / 100d;
		System.out.println();
		
		//check to ensure the grand total is not 0, if so it cuts to the end.
		if (grandTotal > 0) {

			//prints out results of tax and grand totals
			System.out.printf("%-32s %s%.2f\n", "Total:", "$" , sum );
			System.out.printf("%-32s %s%.2f\n","Tax:",  "$", tax );
			System.out.printf("%-32s %s%.2f\n","Grand Total:", "$" , grandTotal );
			System.out.println("***************************************************************");
			
			//collects the payment method and validates it exists
			System.out.println("How would you like to pay? (cash, credit, check)");
			payType = scnr.nextLine();
			
			//while loop sets up a non response in in the loop.
			while (payCheck == true) {
				
				//cash option
				if (payType.equals("cash")) {
					
					//collects amount from customer, validates it is correct format, and calculates change
					do {
					collect1 = Validator.getStringMatchingRegex(scnr, "How much cash do you have? (0.00 format)", "([\\d]*)\\.([\\d]{2})");
					collect = Double.parseDouble(collect1);
					change = collect - grandTotal;
					if (collect < grandTotal) {
						System.out.println("You must have enough to cover the Grand Total.");
						}
					} while (collect < grandTotal);
					
					
					//prints the final receipt
					System.out.println("Your final receipt:");
					System.out.println("***************************************************************");
					System.out.println("JBS Gourmet Grocery Receipt");
					System.out.printf("%-32s %-6s        %s\n","Item", "Quantity", "Price Per Item");
					System.out.println("***************************************************************");
					for (int i =0; i<list2.size(); i++) {
						System.out.printf("%-32s %s %s    %s%.2f\n",list1.get((list2.get(i))).getName(), "x", list3.get(i), "\t $", list1.get((list2.get(i))).getPrice());
						}
					System.out.println();
					System.out.printf("%-32s %s%.2f\n", "Total:", "$" , sum );
					System.out.printf("%-32s %s%.2f\n","Tax:",  "$", tax );
					System.out.printf("%-32s %s%.2f\n","Grand Total:", "$" , grandTotal );
					System.out.printf("%-32s %s%.2f\n", "Amount Tendered:", "$" , collect );
					System.out.printf("%-32s %s%.2f\n", "Change:", "$" , change );
					payCheck = false;
					
				//credit option
				} else if (payType.equals("credit")) {
					
					//collects valid card number in correct format
					cardNum = Validator.getStringMatchingRegex(scnr,"Please enter the card number. (#### #### #### ####)","[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}");
					
					//collects and validate that the month and year entered makes sense
					do {
					cardExp = Validator.getStringMatchingRegex(scnr, "Please enter the card expirtion date. (MMYY)", "[0-9]{4}");
					cardExp1 = Validator.isValidExpiration(cardExp);
					} while (cardExp1 == false);
					
					//collects and validates CVV
					cardCvv = Validator.getStringMatchingRegex(scnr, "Please enter the 3 digit passcode on the back of the card. (###)", "[0-9]{3}");
					
					//prints final credit receipt
					System.out.println("Your final receipt:");
					System.out.println("***************************************************************");
					System.out.println("JBS Gourmet Grocery Receipt");
					System.out.printf("%-32s %-6s        %s\n","Item", "Quantity", "Price Per Item");
					System.out.println("***************************************************************");
					for (int i =0; i<list2.size(); i++) {
						System.out.printf("%-32s %s %s    %s%.2f\n",list1.get((list2.get(i))).getName(), "x", list3.get(i), "\t $", list1.get((list2.get(i))).getPrice());
						}
					System.out.println();
					System.out.printf("%-32s %s%.2f\n", "Total:", "$" , sum );
					System.out.printf("%-32s %s%.2f\n","Tax:",  "$", tax );
					System.out.printf("%-32s %s%.2f\n","Grand Total:", "$" , grandTotal );
					System.out.printf("%-25s %s\n", "Charged to card: " , cardNum );
					payCheck = false;
					
				//check option
				} else if (payType.equals("check")) {
					
					//collects and validates the check number.
					checkNum = Validator.getStringMatchingRegex(scnr,"Please enter the check number. (####)","[0-9]{4}");
					
					//prints the final receipt for a check.
					System.out.println("Your final receipt:");
					System.out.println("***************************************************************");
					System.out.println("JBS Gourmet Grocery Receipt");
					System.out.printf("%-32s %-6s        %s\n","Item", "Quantity", "Price Per Item");
					System.out.println("***************************************************************");
					for (int i =0; i<list2.size(); i++) {
						System.out.printf("%-32s %s %s    %s%.2f\n",list1.get((list2.get(i))).getName(), "x", list3.get(i), "\t $", list1.get((list2.get(i))).getPrice());
					}
					System.out.println();
					System.out.printf("%-31s %s%.2f\n", "Total:", "$" , sum );
					System.out.printf("%-31s %s%.2f\n","Tax:",  "$", tax );
					System.out.printf("%-31s %s%.2f\n","Grand Total:", "$" , grandTotal );
					System.out.printf("%-31s %s\n","Charged to check number: " , checkNum );
					payCheck = false;
					
				//if the payment method doesn't match an option we restart the loop and back to the top
				} else {
					System.out.println("Please try your response again.");
					payCheck = true;
				}
			
			}
			
			//this else statement lets someone know there cart is empty if the grand total is 0.
		} else {
			System.out.println("Your cart is empty.");
		}
		
		
	}
}
