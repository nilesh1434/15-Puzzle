import java.util.ArrayList;
import java.util.concurrent.Callable;

public class A_IDS_A_15solver implements Callable<ArrayList<Node>>{
	
	/*
	 * @author Mark Hallenbeck
	 * Copyright© 2014, Mark Hallenbeck, All Rights Reservered.
	 */
	private ArrayList<Node> nxt10StepsRt;
	private boolean finished = false;
	
	public A_IDS_A_15solver(int heuristic, int[] arr){
		
		//UserInterface puzzle = new UserInterface();			//class for reading in puzzle from user
		
		Node startState = new Node(arr);		//node contains the start state of puzzle
		
		startState.setDepth(0);
				
		if(heuristic == 1) {
			System.out.println("\nStarting A* Search with heuristic #1....This may take a while\n\n");
			A_Star(startState, "heuristicOne");				//A* search with heuristic 1 (misplaced tiles)
		}
		
		if(heuristic == 2) {
			System.out.println("\nStarting A* Search with heuristic #2....This may take a while\n\n");
			A_Star(startState, "heuristicTwo");							//A* search with heuristic 2 (manhattan)
		}
		
		
		
		synchronized (this) {
			finished = true;
			this.notify();
		}
		
//		System.out.println("\nThanks for using me to solve your 15 puzzle......Goodbye");
//		System.exit(1);
	}

/**
 * Method takes node with the start state as well as which heuristic to use and initializes a DB_Solver2 object(A* search).
 * It then solves the puzzle and prints out some metadata and the solution path
 * @param startState
 * @param heuristic
 */
	public void A_Star(Node startState, String heuristic){
		
				
		DB_Solver2 start_A_Star = new DB_Solver2(startState, heuristic);	//DB_Solver class initialized with startState node
		
				
		Long start = System.currentTimeMillis();

		Node solution = start_A_Star.findSolutionPath();	//returns the node that contains the solved puzzle
		
		Long end = System.currentTimeMillis();

		//System.out.println("\n******Run Time for A* "+ heuristic + " is: "+ (end-start) + " milliseconds**********");
		
		if(solution == null)								//no solution was found
		{
			System.out.println("\nThere did not exist a solution to your puzzle with A* search\n");
		}
		else											//found a solution so, get the path and print it
		{
			//ArrayList<Node> solutionPath 
			
			nxt10StepsRt = start_A_Star.getSolutionPath(solution);
			//printSolution(nxt10StepsRt);
			
			//System.out.println("\n$$$$$$$$$$$$$$ the solution path is "+ solutionPath.size()+ " moves long\n");
		}
	}
	
	public ArrayList<Node> getList() throws InterruptedException {
		synchronized (this) {
			if(!finished) {
				this.wait();
			}
		}
		return nxt10StepsRt;
		
	}
	
	public boolean isDone() {
		return finished;
	}
	

	public void printSolution(ArrayList<Node> path){
		System.out.print("\n\n");
		
		System.out.println("**************Initial State******************");
		for(int i=0; i<path.size(); i++){
			
			printState(path.get(i));
			
			if(i != (path.size() - 1))
				System.out.print("\nNext State => "+i+"\n\n");
			
		}
		System.out.println("\n**************Goal state****************");
	}

	public void printState(Node node){
	
		int[] puzzleArray = node.getKey();
		
		for(int i =0; i< puzzleArray.length; i++){
			System.out.printf("%4d ",puzzleArray[i]);
			if(i == 3 || i == 7 || i == 11)
				System.out.print("\n");
		}
	}

	@Override
	public ArrayList<Node> call() throws Exception {
		// TODO Auto-generated method stub
		return nxt10StepsRt;
	}

}
