package ie.gmit.sw.ai;

import javafx.application.Application;

/**
* 
*
* @author Jina Kim
*
* 
* 
*
*/

public class Runner {
	
	public static void main(String[] args) throws Exception {
		/*
		 * PLEASE READ CAREFULLY
		 * ---------------------
		 * If you need to load FCL functions to the application or
		 * train, configure and load a neural network, then it is 
		 * best to do all of this before loading the UI. Launching
		 * a UI in any language or framework and then starting a 
		 * long running task in the same thread is guaranteed to
		 * freeze the screen and crash the programme.
		 * 
		 * NB: you can assume that the JavaFX 15 API is already
		 * available and configured on the MODULE-PATH (NOT THE 
		 * CLASSPATH). 
		 */
		
		  //Add long-running initialisation instructions here.
		
		
		
		/*
		 * Launch the JavaFX UI only when all the long-running AI 
		 * configuration tasks have been completed. Use the arrow 
		 * keys to move the player character and the 'Z' key to 
		 * toggle the zoom in / out.
		 */
		
		
		//call getInstance to retrieve the object available from the class
		EnemyFactory object = EnemyFactory.getInstance();
				

		//show the message
		object.printMessage(); 
			    
		//EnemyFactory enemyFactory = new EnemyFactory();

	    //get an object of Circle and call its draw method.
		Enemy e = object.getEnemy("CharacterTask");

	    //call draw method of Circle
	    e.enemyWorld();
		
	    
		Application.launch(GameWindow.class, args);
		
		
	}
}