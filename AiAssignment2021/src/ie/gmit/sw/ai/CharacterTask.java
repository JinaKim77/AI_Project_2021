package ie.gmit.sw.ai;

import java.util.concurrent.ThreadLocalRandom;
import javafx.concurrent.Task;


/*
 * CharacterTask represents a Runnable (Task is a JavaFX implementation
 * of Runnable) game character. The character wanders around the game
 * model randomly and can interact with other game characters using
 * implementations of the Command interface that it is composed with. 
 * 
 * You can change how this class behaves is a number of different ways:
 * 
 * 1) DON'T CHANGE THIS CLASS
 * 	  Configure the class constructor with an instance of Command. This can
 *    be a full implementation of a fuzzy controller or a neural network. You
 *    can also parameterise this class with a lambda expression for the 
 *    command object. 
 *    
 * 2) CHANGE THIS CLASS 
 * 	  You can extend this class and provide different implementations of the 
 * 	  call by overriding (not recommended). Alternatively, you can change the
 *    behaviour of the game character when it moves by altering the logic in
 *    the IF statement:  
 *    
 * 		if (model.isValidMove(row, col, temp_row, temp_col, enemyID)) {
 * 	    	//Implementation for moving to an occupied cell
 * 	    }else{
 *      	//Implementation for moving to an unoccupied cell
 *      } 
 */
 
public class CharacterTask extends Task<Void> implements Enemy{
	private static final int SLEEP_TIME = 1000; //Sleep for 300 ms
	private static ThreadLocalRandom rand = ThreadLocalRandom.current();
	private static final int DEFAULT_SIZE = 50;
	private boolean alive = true;
	private GameModel model;
	private char enemyID;
	private int row;
	private int col;
	private int enemyHealth;
	private int enemyDamage;
	private Colour colour;
	
	//Create Objects of fuzzyLogic and NN
	FuzzyLogicImp fuzzyImp = new FuzzyLogicImp();
	NeuralNetworkImp nnImp;
	
	GameWindow gamewindow = new GameWindow();
	int playerRow;
	int playerCol;
	int getHealth;
	int getDamage;
	int playerHealthRemaining;
	int playerGotDamage;
	Maze maze = new Maze(playerRow,playerCol);
	

	/*
	 * Configure each character with its own action. Use this functional interface
	 * as a hook or template to connect to your fuzzy logic and neural network. The
	 * method execute() of Command will execute when the Character cannot move to
	 * a random adjacent cell.
	 */
	
	private Command cmd;
	
	public CharacterTask(GameModel model, char enemyID, int row, int col, Command cmd) {
		this.model = model;
		this.enemyID = enemyID;
		this.row = row;
		this.col = col;
		this.cmd = cmd;
	}
	
    @Override
    public Void call() throws Exception {
    	/*
    	 * This Task will remain alive until the call() method returns. This
    	 * cannot happen as long as the loop control variable "alive" is set
    	 * to true. You can set this value to false to "kill" the game 
    	 * character if necessary (or maybe unnecessary...).
    	 */
    	
    	//Enemy has only one life!
    	enemyHealth=rand.nextInt(10, 100);
    	enemyDamage=rand.nextInt(7);
    	
    	while (alive) {
        	Thread.sleep(SLEEP_TIME);

        	//Maze maze = new Maze(getRow,getCol);
        	maze = new Maze(playerRow,playerCol);
        	DepthLimitedSearch search = new DepthLimitedSearch(maze);
        	//System.out.println("Search : "+search);
        	
        	synchronized (model) {
        		//Randomly pick a direction up, down, left or right
        		//Enemy will be randomly placed in this game...
        		int temp_row = row, temp_col = col;
        		if (rand.nextBoolean()) {
            		temp_row += rand.nextBoolean() ? 1 : -1;
            	}else {
            		temp_col += rand.nextBoolean() ? 1 : -1;
            	}
            	
            	if (model.isValidMove(row, col, temp_row, temp_col, enemyID)) {
            		/*
            		 * This fires if the character can move to a cell, i.e. if it is not
            		 * already occupied. You can add extra logic here to invoke
            		 * behaviour when the computer controlled character is in the proximity
            		 * of the player or another character...
            		 */
            		model.set(temp_row, temp_col, enemyID);
            		model.set(row, col, '\u0020');
            		row = temp_row;
            		col = temp_col;
            		
            		// == Update the values for the player position ==
            		playerRow = gamewindow.updatedRow;
            		playerCol = gamewindow.updatedCol;
            		
            		// == Get player's health ==
            		getHealth = gamewindow.health;
            		getDamage = gamewindow.damage;
            		
            		
            		// == Enemy location ==
            		System.out.println("Enemy :"+enemyID+" is at Row :"+row+" and "+ "Col :"+col);
            		
          
            		// == Enemy with ID '\u0032' will follow the player
            		// == Red enemy will follow the player and kill the player eventually.
    				if(enemyID == '\u0032')
    				{
    					maze = new Maze(playerRow,playerCol);
    		        	DepthLimitedSearch search2 = new DepthLimitedSearch(maze);
    		        	
    					if(row - playerRow < 0) {
    						temp_row+=1;
    					}else {
    						temp_row+=-1;
    					}
    					if(col - playerCol < 0) {
    						temp_col+=1;
    					}else {
    						temp_col+=-1;
    					}
    					
    					model.set(temp_row, temp_col, enemyID);
                		model.set(row, col, '\u0020');
                		row = temp_row;
                		col = temp_col;
    				}
            	
            		// == Display message when the enemy met player ==
            		if(row-1 < playerRow && col-1 < playerCol) {
            			
            				// == Replace the enemy location when the enemy meets the player ==
        					//model.set(row, col, '\u0020');
        					//row = (int) (DEFAULT_SIZE * Math.random());
        					//col = (int) (DEFAULT_SIZE * Math.random());
            			
            			
            				System.out.println("Enemy id with "+enemyID+" : " +"<==========The player is close to you!==============>");
            				System.out.println("Enemy :"+enemyID+" is at Row :"+row+" and "+ "Col :"+col);
            				System.out.println("Player is at Row :"+playerRow+" and "+ "Col :"+playerCol);
            				
            				//Take the player's health
            				gamewindow.health-=enemyDamage;
            				gamewindow.damage+=1;
            				
            				//Take the enemy's health
            				enemyHealth-=10;
            				//enemyDamage+=10;
            				
            				
            				//Player health and damage
            				playerHealthRemaining=gamewindow.health;
            				playerGotDamage=gamewindow.damage;
            				
            				int currentHealth = playerHealthRemaining;
            				System.out.println("The player just lost 10 health!");
            				System.out.println("Now the player's health is "+playerHealthRemaining);
            				System.out.println("Now the player's damage is "+playerGotDamage);
            				double playerRisk = fuzzyImp.getRiskPlayer(playerHealthRemaining,playerGotDamage);
                    	    //System.out.println("Player's risk ---> "+" "+playerRisk);
                    	    
                    	    
                            if(playerRisk > 70) {
                            	System.out.println("Player's risk ---->"+"  "+ playerRisk+ " ==> Risk for the player is over 70, it's high");
                            }else if(playerRisk > 40) {
                            	System.out.println("Player's risk ---->"+"  "+ playerRisk+ " ==> Risk for the player is over 40");
                            }else {
                            	System.out.println("Player's risk ---->"+"  "+ playerRisk+ " ==> Risk for the player is less than 40");
                            }
                            
            				
            				
            				//If the player's health is 0 or less than 0, the game should be over"
            				if(currentHealth < 10) {
            					System.out.println("Game over!");	
            					gamewindow.setGameOver(true);
            					gamewindow.setPause(true);
            				}        
            				
            				// == Enemy ==
                    		//System.out.println("Enemy is at Row :"+row+" and "+ "Col :"+col);
                    		double enemyRisk = fuzzyImp.getRiskEnemy(enemyHealth,enemyDamage);
                    		System.out.println("Enemy Health is :"+enemyHealth);
                    		System.out.println("Enemy Damage is :"+enemyDamage);
                    		//System.out.println("Enemy("+enemyID+") risk ---> "+" "+enemyRisk);
                    		
                    	     if(enemyRisk > 70) {
                             	System.out.println("Enemy's risk ---->"+"  "+ enemyRisk+ " ==> Risk for the enemy is over 70, it's high");
                             }else {
                             	System.out.println("Enemy's risk ---->"+"  "+ enemyRisk+ " ==> Risk for the enemy is less than 70");
                             }
                    	     
                    	                      	     
                    	 	// == If the Enemy's health is 0 or less than 0, the game should be over ==
             				if(enemyHealth < 20) {
             					System.out.println("Stop this ememy("+enemyID+") movement!!!");
             					System.out.println("Enemy Died!");
                        		alive=false;  //Kill the enemy!                        		
             				}        
   
             				
                    		// == Neural Network ==
                    		new NeuralNetworkImp();

            			}else {
            				System.out.println("You are safe now.");
            			}
            		
            		
            	}else {  
            		/*
            		 * This fires if a move is not valid, i.e. if someone or some thing 
            		 * is in the way. Use implementations of Command to control how the
            		 * computer controls this character. 
            		 */
            		cmd.execute();
            	}
        	}
    	}
		return null;
    }

	@Override
	public void enemyWorld() {
		System.out.println("Inside CharacterTask::enemyWorld() method. \n");
		
	}
}