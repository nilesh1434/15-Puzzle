import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Puzzles {
	private static int[] puzzle1 = {1,6,5,2,13,4,7,3,9,0,15,14,8,10,12,11};
	private static int[] puzzle2 = {5,3,0,9,15,14,2,8,7,1,11,12,4,13,10,6};
	private static int[] puzzle3 = {1,5,2,3,0,12,4,6,8,14,9,7,10,11,13,15};
	private static int[] puzzle4 = {9,0,3,11,5,1,6,2,4,10,7,14,8,12,13,15};
	private static int[] puzzle5 = {1,5,3,9,10,6,2,13,4,7,14,11,8,12,15,0};
	private static int[] puzzle6 = {0,10,5,3,1,4,2,7,8,14,9,15,12,11,13,6};
	private static int[] puzzle7 = {1,9,7,5,2,6,3,11,0,13,10,12,4,8,14,15};
	private static int[] puzzle8 = {1,7,6,8,4,2,0,15,12,3,14,11,13,5,9,10};
	private static int[] puzzle9 = {11,8,0,3,10,5,9,2,1,4,7,14,12,6,13,15};
	private static int[] puzzle10 = {1,9,5,3,13,0,7,11,4,8,6,15,12,14,2,10};
	private static int[] puzzle11 = {1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	private static int last = 0;
	
	public static int[] getRandomPuzzle() {
		int rand = (int) (1 + Math.random()*10);
		while(rand == last) {
			rand = (int) (1 + Math.random()*10);
		}
		last = rand;
		if (rand == 1) {
			return puzzle1;
		}
		if (rand == 2) {
			return puzzle2;
		}
		if (rand == 3) {
			return puzzle3;
		}
		if (rand == 4) {
			return puzzle4;
		}
		if (rand == 5) {
			return puzzle5;
		}
		if (rand == 6) {
			return puzzle6;
		}
		if (rand == 7) {
			return puzzle7;
		}
		if (rand == 8) {
			return puzzle8;
		}
		if (rand == 9) {
			return puzzle9;
		}
		if (rand == 10) {
			return puzzle10;
		}
		return puzzle11;
	}
	
}
