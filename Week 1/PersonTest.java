class Person {

   // Properties of the class...
   private String name;
   public int    age;
   private String city;
   
   // Constructor of the class...
  
   // Methods of the class...
   public void talk() {
      System.out.println("Hi, my name's " + name);
      System.out.println("and my age is " + age);
      System.out.println("and I live in "+ city);
	  commentAboutAge();
   }
   public void commentAboutAge() {
      if (age < 5) {
         System.out.println("baby");
      }
      if (age == 5) {
         System.out.println("time to start school");
      }
	  if (age > 60) {
		 System.out.println("old person");
	  }
   }
	public void growOlder() {
		age = age+1;
		System.out.println(age);
	}
	
	public void growOlderBy(int years) {
		age = age + years;
		System.out.println(age);
	}
	
	public void giveKnighthood() {
		name = "Sir "+name;
		System.out.println(name);
	}
}

class PersonTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      Person ls = new Person();
	  Person wp = new Person();


      ls.talk();
      wp.talk();
	  
	  wp.growOlder();
	  wp.giveKnighthood();
	  
	  System.out.println(ls.age);
	  
	  ls.growOlderBy(10);
	  
	
	
   }

}

