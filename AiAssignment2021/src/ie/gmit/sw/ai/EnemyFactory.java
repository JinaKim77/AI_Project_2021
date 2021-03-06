package ie.gmit.sw.ai;

/**
* 
*
* @author Jina Kim
*
* This Factory class Contains private type constructor, getInstance method and normal method().
* 
*
*/

public class EnemyFactory {

	    //create an object of EnemyFactory
		private static EnemyFactory eFactory = new EnemyFactory();

		//private constructor so that I cannot instantiate the class
	    private EnemyFactory() {
	    	
	    }

	    //returns the only available object
	    public static EnemyFactory getInstance() {
	        return eFactory;
	    }
	    
	    public void printMessage(){
	        System.out.println("== This message is from enemy fatory class  == \n");
	    }
	    
	    //use getEnemy method to get object of type enemy
	    public Enemy getEnemy(String enemyType){
	       if(enemyType == null){
	          return null;
	       }		
	       if(enemyType.equalsIgnoreCase("CharacterTask")){
	    	   return new CharacterTask(null, (char) 0, 0, 0, null);
	       } 
	       
	       return null;
	    }
	    
}
