package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;

/**
* 
*
* @author Jina Kim
*
* 
* 
*
*/

public class Node {
	private String nodeName;
	private List<Node> children = new ArrayList<Node>();
	private boolean visited = false;
	private boolean goalNode;
	private Colour colour = Colour.White;
	
	public int row;
	public int col;
	
	public Node(String name){
		this.nodeName = name;
	}
	
	public Node(String name, int row, int col){
		this.nodeName = name;
		this.row = row;
		this.col = col;
	}
	
	public Node(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public Node[] children(){
		return (Node[]) children.toArray(new Node[children.size()]);
	}
	
	public boolean isLeaf(){
		if (children.size() > 0){
			return false;
		}else{
			return true;
		}
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getChildNodeCount(){
		return children.size();
	}

	public void addChildNode(Node child){
		children.add(child);
	}
	
	public void removeChild(Node child){
		children.remove(child);
	}
	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public Colour getColour() {
		return colour;
	}

	public void colour(Colour colour) {
		this.colour = colour;
	}

	public boolean isGoalNode() {
		return goalNode;
	}

	public void setGoalNode(boolean goalNode) {
		this.goalNode = goalNode;
	}

	public String toString() {
		return this.nodeName;
	}
}