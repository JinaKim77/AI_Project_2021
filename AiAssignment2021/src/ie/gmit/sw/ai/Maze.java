package ie.gmit.sw.ai;

/**
* 
*
* @author Jina Kim
*
* 
* 
*
*/

public class Maze {
	public static Maze maze = new Maze();
	private Node player;
	private Node enemy;
	
	//public static Maze getInstance(){
		//return maze;
	//}
	
	public Maze(){
		enemy = new Node("Enemy node");
		player = new Node("Player node");
		
		enemy = new Node(enemy.getNodeName());
		player = new Node(player.getNodeName());
		player.setGoalNode(true);
		
		enemy.addChildNode(player);
		player.addChildNode(enemy);
		
	}
	
	// == Player ==
	public Maze(int row, int col){
		enemy = new Node("Enemy node");
		player = new Node("Player node");
		
		enemy = new Node(enemy.getNodeName(),row,col);
		player = new Node(player.getNodeName(),row,col);
		player.setGoalNode(true);
		
		enemy.addChildNode(player);
		player.addChildNode(enemy);
		
	}
	
	public Node getStartNode(){
		return enemy;
	}
	
	public Node setGoalNode(){
		return player;
	}
}