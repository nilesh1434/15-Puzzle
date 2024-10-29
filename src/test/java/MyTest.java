import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void testWin() {
		int[] puzzle0 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int[][] arr = new int[4][4];
		//GameButton gridBtns;
		int[] z = puzzle0;
		int count = 0;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					arr[y][x] = puzzle0[count];
					count++;
				}
			}
		assertEquals(GameLogic.checkWinForTest(arr), true);
	}
	
	@Test
	void testLoss() {
		int[] puzzle0 = {1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int[][] arr = new int[4][4];
		//GameButton gridBtns;
		int[] z = puzzle0;
		int count = 0;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					arr[y][x] = puzzle0[count];
					count++;
				}
			}
		assertEquals(GameLogic.checkWinForTest(arr), false);
	}
	
	@Test
	void testRandPuzzle() {
		int[] puzzle0 = Puzzles.getRandomPuzzle();
		int[][] arr = new int[4][4];
		//GameButton gridBtns;
		int[] z = puzzle0;
		int count = 0;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					arr[y][x] = puzzle0[count];
					count++;
				}
			}
		assertEquals(GameLogic.checkWinForTest(arr), false);
	}
	
	@Test
	void testNewPuzzle() {
		int[] puzzle0 = Puzzles.getRandomPuzzle();
		assertEquals(puzzle0.length, 16);
	}
	
	@Test
	void testNotSamePuzzle() {
		int[] puzzle0 = Puzzles.getRandomPuzzle();
		int[] puzzle1 = Puzzles.getRandomPuzzle();
		assertNotEquals(puzzle0, puzzle1);
	}
	
	@Test
	void testNotTestPuzzle() {
		int[] puzzle0 = Puzzles.getRandomPuzzle();
		int[] l = {1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		assertNotEquals(puzzle0, l);
	}
	
	
	
	@Test
	void NodeTest1() {
		int[] l = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		Node n = new Node(l);
		int[][] arr = new int[4][4];
		int[] z = n.getKey();
		int count = 0;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					arr[y][x] = z[count];
					count++;
				}
			}
		assertEquals(GameLogic.checkWinForTest(arr), true);
	}
	
	@Test
	void NodeTest2() {
		int[] puzzle0 = Puzzles.getRandomPuzzle();
		Node n = new Node(puzzle0);
		int[][] arr = new int[4][4];
		int[] z = n.getKey();
		int count = 0;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					arr[y][x] = puzzle0[count];
					count++;
				}
			}
		assertEquals(GameLogic.checkWinForTest(arr), false);
	}
	
	@Test
	void AI1() {
		int[] puzzle0 = Puzzles.getRandomPuzzle();
		A_IDS_A_15solver sol = new A_IDS_A_15solver(1, puzzle0);
		ExecutorService ex = Executors.newFixedThreadPool(10);

		Future<ArrayList<Node>> future = ex.submit((Callable<ArrayList<Node>>) new A_IDS_A_15solver(1, puzzle0));
		ArrayList<Node> a = null;
		try {
			while(true) {
				if(future.isDone()) {
				a = future.get();
				break;
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[][] arr = new int[4][4];
		int[] z = a.get(a.size()-1).getKey();
		int count = 0;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					arr[y][x] = puzzle0[count];
					count++;
				}
			}
		assertEquals(GameLogic.checkWinForTest(arr), false); 
	}
	
	@Test
	void AI2() {
		int[] puzzle0 = Puzzles.getRandomPuzzle();
		A_IDS_A_15solver sol = new A_IDS_A_15solver(2, puzzle0);

		ExecutorService ex = Executors.newFixedThreadPool(10);

		Future<ArrayList<Node>> future = ex.submit((Callable<ArrayList<Node>>) new A_IDS_A_15solver(2, puzzle0));
		ArrayList<Node> a = null;
		try {
			while(true) {
				if(future.isDone()) {
				try {
					a = future.get();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[][] arr = new int[4][4];
		int[] z = a.get(a.size()-1).getKey();
		int count = 0;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					arr[y][x] = puzzle0[count];
					count++;
				}
			}
		assertEquals(GameLogic.checkWinForTest(arr), false);
	}
}
