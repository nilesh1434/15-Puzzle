
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class GameLogic {
    public static boolean checkNextToEmpty(GameButton[][] arr, int row, int col, GridPane grid){
        if(row != 3) {
            if(arr[row+1][col].getEmpty()){
                swap(arr,row,col,row+1,col,grid);
                return true;
            }
        }
        if(row != 0) {
            if(arr[row-1][col].getEmpty()){
                swap(arr,row,col,row-1,col,grid);
                return true;
            }
        }
        if(col != 3) {
            if(arr[row][col+1].getEmpty()){
                swap(arr,row,col,row,col+1,grid);
                return true;
            }
        }
        if(col != 0) {
            if(arr[row][col-1].getEmpty()){
                swap(arr,row,col,row,col-1,grid);
                return true;
            }
        }
        return false;
    }

    public static void swap(GameButton[][] arr, int row, int col, int row1, int col1, GridPane grid) {

        arr[row1][col1] = new GameButton(row1, col1, arr[row][col].getNum());
        arr[row][col] = new GameButton(row, col, 0);
     /*   EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
				GameButton button = (GameButton) e.getSource();
				int row = button.getRow();
				int col = button.getCol();
				boolean nextToBlank = GameLogic.checkNextToEmpty(arr, row, col, grid);
				boolean isWon = GameLogic.checkWin(arr);
				if(!nextToBlank){
					System.out.println("Invalid Choice");
				}
				if (isWon) {
					JavaFXTemplate.callWinScene();
				}
			}
		};
        */
        grid.getChildren().remove(arr[row1][col1]);
        grid.getChildren().remove(arr[row][col]);
        grid.add(arr[row1][col1], row1, col1);
        grid.add(arr[row][col], row, col);

        arr[row1][col1].setMinSize(50, 50);
		arr[row1][col1].setOnAction(JavaFXTemplate.myHandler);

        arr[row][col].setMinSize(50, 50);
		arr[row][col].setOnAction(JavaFXTemplate.myHandler);
    }

    public static boolean checkWin(GameButton[][] arr){
        int z = 0;
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if (arr[y][x].getNum() == z) {
                    z++;
                } else {
                    
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean checkWinForTest(int[][] arr){
        int z = 0;
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if (arr[y][x] == z) {
                    z++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
