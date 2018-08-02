import java.util.*;

public class JBSGroceryApp {

	public static void main(String[] args) {
		//Create new array lift for inventory 
		Scanner scan = new Scanner(System.in);
		List<Inventory> ogInventory = new ArrayList <Inventory>();
		ArrayList<String> cart = new ArrayList < String>();
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
				case "d": checkout(scan, cart, cartQuantity, paymentType);
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

	private static void checkout(Scanner scan, ArrayList<String> cart, ArrayList<Integer> cartQuantity,
			String paymentType) {
		// TODO Auto-generated method stub
		
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
}
