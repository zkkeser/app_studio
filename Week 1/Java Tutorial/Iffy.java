public class Iffy {
   private int anIntProp = 42;
   public static void main(String[] args) {
      Iffy me = new Iffy();
      me.callSomeMethods();
   }

   public void callSomeMethods() {
      // add statements here to exercise the other methods ...
	  warnIfNegative(1);
	  resetIfNegative(1);
	  System.out.println(isInRangeIfLess(5,10,1));
   }

   /**
    * Print out a warning if the value is negative
    * otherwise don't do anything.
    */
   public void warnIfNegative(int theValue) {
      if(theValue < 0){
      System.out.println("Caution - negative value given (" + theValue + ")");
	  }
	  //brackets were missing
   }
  
   /**
    * Print out a warning if the value of anIntProp is negative
    * and also set the value to zero.
    * Otherwise don't do anything.
    */
   public void resetIfNegative(int anIntProp) {
      if(anIntProp < 0)
         System.out.println("Caution - negative value given (" + anIntProp + ")");
      anIntProp = 0;
	  //there was no argument given so I added int anIntProp.
   }

   /**
    * return true if value is between upperBound and LowerBound
    * (or equal to either bound) otherwise return false.
    */
   public boolean isInRange(int value, int upperBound, int lowerBound) {
    if(lowerBound <= value && value <= upperBound ){
      return true;
	}
    else{
      return false;
	}
   }

   public boolean isInRangeIfLess(int value, int upperBound, int lowerBound) {
		return (lowerBound <= value && value <= upperBound);
   }
}
