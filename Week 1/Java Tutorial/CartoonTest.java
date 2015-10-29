class CartoonCharacter{
	//Properties of the class
	private String name;
	public String favouriteColour;
	private int favouriteNumber;
	public static int count;
	
	//constructor of the class
	public CartoonCharacter(String aName, String aColour, int aNumber){
		name = aName;
		favouriteColour = aColour;
		favouriteNumber = aNumber;
		count = count + 1;
	}
	
	// Methods of the class
	public void displayMe() {
		System.out.println("Hello, my name is " + name);
		System.out.println("my favourite colour is " + favouriteColour);
		System.out.println("and my favourite number is " + favouriteNumber);
	}
	
	
}



class CartoonTest { 

   // The main method is the point of entry into the program...
   public static void main(String[] args) {
	   
	   CartoonCharacter fred 	= new CartoonCharacter("Fred Flintstone","Blue",5);
	   CartoonCharacter wilma 	= new CartoonCharacter("Wilma Flintstone","Blue",5);
	   CartoonCharacter barney 	= new CartoonCharacter("Barney Rubble","Blue",5);
	   
	   fred.displayMe();
	   wilma.displayMe();
	   barney.displayMe();
	   
	   System.out.println(barney.favouriteColour);
	   System.out.println(CartoonCharacter.count);
	   System.out.println(Math.PI);
   }
}

