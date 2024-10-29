// Raghav Sharma & Nilesh Jain (Partners)
// CS 342 Project 2
// Professor Haze
// 03/08/2021

import javafx.scene.control.Button;

public class GameButton extends Button {
	private int row;
	private int col;
	private int num;
	private boolean empty;

	GameButton (int r, int c, int n) {
		this.row = r;
		this.col = c;
		this.num = n;
		
		if(n != 0){
			this.setText(Integer.toString(n));
			this.empty = false;
		} else {
			this.empty = true;
		}
		if (this.empty) {
			this.setStyle("-fx-background-color:lightgrey;");
		} else {
			this.setStyle("-fx-background-color:tan;");
		}
	
	}

	//allows for the undo

	//getters
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int r) {
		row = r;
	}

	public void setCol(int c) {
		col = c;
	}

	public int getNum(){
		return num;
	}
	public void setNum(int n){
		num = n;
	}

	public boolean getEmpty(){
		return empty;
	}

	public void setEmpty(boolean b){
		empty = b;
	}
	/*
	//checks to see if the button is at the bottom of the board
	public boolean atBottom() {
		if(column == 5) {
			return true;
		}
		return false;
	}
*/
};