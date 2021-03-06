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

public class DepthLimitedSearch {
	private static final int limit = 10;
	public DepthLimitedSearch(Maze maze) {
		search(maze.getStartNode(), limit, 0);		
	}

	public double search(Node node, int bound, int depth){
		if (depth > bound){
			System.out.println("Maximum Depth Reached. Failed to locate goal node.");
			//System.exit(0);
			//System.out.println(Double.MAX_VALUE);
			//node.setVisited(true);
			return Double.MAX_VALUE;
			//return 0;
			
		}
		if (node.isGoalNode()){
			System.out.println("Reached goal node :" + node.getNodeName()+" is at "+"row:" + node.getRow() +", and col: "+node.getCol() +"\n");
			//System.exit(0);
			//System.out.println(Double.MIN_VALUE);
			return Double.MIN_VALUE;
			//return 1;
		}else{
			if (!node.isVisited()){
				node.setVisited(true);
				System.out.println("\n"+"Node Name : "+node);
				Node[] children = node.children();
				for (int i = 0; i < children.length; i++) {
					search(children[i], bound, depth + 1);
				}
			}
			//System.out.println(Double.MAX_VALUE);
			return Double.MAX_VALUE;
			//return depth;
		}
	}

	//public static void main(String[] args) {
		//Maze maze = Maze.getInstance();
		//DepthLimitedSearch search = new DepthLimitedSearch(maze);
	//}
}
