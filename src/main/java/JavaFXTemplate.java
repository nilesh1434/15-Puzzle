import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;


public class JavaFXTemplate extends Application {

//	HashMap<String, Scene> sceneMap;
	
	private GameButton gridBtns;
	private Button startGame, newPuzzle, AI1, AI2, showSoln, exitBtn, newGame, howToPlayBtn, backBtn;
	private TextField t1 = new TextField();
	private GridPane grid;
	private HBox gameSceneBtns;
	private VBox start, inGameScene;
	private Scene scene, gameScene, howToPlayScene, endScene, solnScene;
	//Stage primaryStage;
	private Image bg; //The image used for the background
	private ImageView bgView;
	private BorderPane bp;
	private PauseTransition pause2 = new PauseTransition(Duration.seconds(.5));
	private ArrayList<Node> nxt10Steps = new ArrayList<>();
	private int mainCount = 0;
	private int countr = 0;
	private Node node;
	private A_IDS_A_15solver ids = null;
	static EventHandler<ActionEvent> myHandler;
	private GameButton[][] arr = new GameButton[4][4];
	private int[] gameState = new int[16];
	private int[] z;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to JavaFX");
		myHandler = new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
				GameButton button = (GameButton) e.getSource();
				showSoln.setDisable(true);
				AI1.setDisable(false);
				AI2.setDisable(false);
				t1.setText("");
				int row = button.getRow();
				int col = button.getCol();
				boolean nextToBlank = GameLogic.checkNextToEmpty(arr, row, col, grid);
				boolean isWon = GameLogic.checkWin(arr);
				setGameState();
				if(!nextToBlank){
					System.out.println("Invalid Choice");
				}
				if (isWon) {
					primaryStage.setScene(winGui());
				}
			//	primaryStage.show();
			}
		};
		
		bg = new Image("welcome.jpg");
		t1.setEditable(false);
		bgView = new ImageView(bg);
		bp = new BorderPane();
		startGame = new Button("Start Game");
		newPuzzle = new Button("New Puzzle");
		AI1 = new Button("Solve with AI H1");
		AI2 = new Button("Solve with AI H2");
		showSoln = new Button("Show Solution");
		exitBtn = new Button("Exit");
		newGame = new Button("New Game");
		howToPlayBtn = new Button("How To Play");
		backBtn = new Button("Back");
		gameSceneBtns = new HBox(newPuzzle, AI1, AI2, showSoln, howToPlayBtn, exitBtn);
		gameSceneBtns.setAlignment(Pos.TOP_CENTER);
		start = new VBox(startGame);
		start.setAlignment(Pos.TOP_CENTER);
		primaryStage.setTitle("15 Puzzle");
//		arr = new GameButton[4][4];
		grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.CENTER);
		addGrid(grid);
		bp.getChildren().addAll(bgView);
//		bp.setCenter(start);
		
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause2.setOnFinished(t->{
			primaryStage.setScene(scene);
		});
		pause.setOnFinished(s->{
			gameScene = inGameGUI();
			primaryStage.setScene(gameScene);
		});
		pause.play();
		
		scene = new Scene(bp, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		// if start game button is pressed
//		startGame.setOnAction(e->{
//			inGameScene = new VBox(200, gameSceneBtns, grid);
//			bg = new Image("inGamePic1.jpg");
//			bgView = new ImageView(bg);
//			bp.getChildren().addAll(bgView);
//			bp.setTop(inGameScene);
//			primaryStage.setScene(scene);
//		});
		
//		startGame.setOnAction(e->{
//			gameScene = inGameGUI();
//			primaryStage.setScene(gameScene);
//		});
		
		// automatically takes you from welcome to inGame scene
//		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		
		// if new puzzle is pressed
		newPuzzle.setOnAction(e->{
			showSoln.setDisable(true);
			AI1.setDisable(false);
			AI2.setDisable(false);
			grid.getChildren().clear();
			addGrid(grid);
		});
		
		ExecutorService ex = Executors.newFixedThreadPool(10);
		
		// if solve with AI H1 is pressed
		AI1.setOnAction(e->{
			AI1.setDisable(true);
			AI2.setDisable(true);
			Future<ArrayList<Node>> future = ex.submit((Callable<ArrayList<Node>>) new A_IDS_A_15solver(1, gameState));
			try {
				while (true) {
					if(future.isDone()) {
						nxt10Steps = future.get();
						break;
					}
				}
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			showSoln.setDisable(false);
			t1.setText("Solution With Heuristic 1 Is Ready!");
		});
		
		// if solve with AI H2 is pressed
		AI2.setOnAction(e->{
			AI1.setDisable(true);
			AI2.setDisable(true);
			Future<ArrayList<Node>> future = ex.submit((Callable<ArrayList<Node>>) new A_IDS_A_15solver(2, gameState));
			try {
				while (true) {
					if(future.isDone()) {
						nxt10Steps = future.get();
						break;
					}
				}
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			showSoln.setDisable(false);
			t1.setText("Solution With Heuristic 2 Is Ready!");
		});
		
		// if show solution is pressed
		showSoln.setDisable(true);
		showSoln.setOnAction(e->{
			showSoln.setDisable(true);
			t1.setText("");
			showSteps(primaryStage);
		});
		
		// if how to play button is pressed
		
		howToPlayBtn.setOnAction(e->{
			gameScene = howToPlayGui();
			primaryStage.setScene(gameScene);
		});
		
		// if back button is pressed
		
		backBtn.setOnAction(e->{
			gameScene = inGameGUI();
			primaryStage.setScene(gameScene);
		});
		
		newGame.setOnAction(e->{
			
			gameScene = inGameGUI();
			primaryStage.setScene(gameScene);
		});
		
		// if exit button is pressed
		exitBtn.setOnAction(e->{
			System.exit(0);
		});

	}
	
	// Adds puzzle grid
	public void addGrid(GridPane grid) {
		z = Puzzles.getRandomPuzzle();
		gameState = z;
		int count = 0;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					gridBtns = new GameButton(y, x, z[count]);
					gridBtns.setMinSize(50, 50);
					gridBtns.setOnAction(myHandler);
					arr[y][x] = gridBtns;
					grid.add(arr[y][x], y, x);
					count++;
				}
			}
		}
	
	public void showSteps(Stage primaryStage) {
		
		grid.getChildren().clear();
		node = nxt10Steps.get(0);
		z = node.getKey();
		countr = 0;
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				gridBtns = new GameButton(y, x, z[countr]);
				gridBtns.setMinSize(50, 50);
				gridBtns.setOnAction(myHandler);
				gridBtns.setDisable(true);
				arr[y][x] = gridBtns;
				grid.add(arr[y][x], y, x);
				countr++;
			}
		}
		
		PauseTransition end = new PauseTransition(Duration.seconds(1));
		end.setOnFinished(w->{
			boolean isWon = GameLogic.checkWin(arr);
			AI1.setDisable(false);
			AI2.setDisable(false);
			enableBoard();
			setGameState();
			if(isWon) {
				grid.getChildren().clear();
				addGrid(grid);
				primaryStage.setScene(winGui());
			}
			
		});
		
		
		if(nxt10Steps.size() >= 2) {
			PauseTransition step1 = new PauseTransition(Duration.seconds(1));
			step1.setOnFinished(r->{
				grid.getChildren().clear();
				node = nxt10Steps.get(1);
				z = node.getKey();
				countr = 0;
				for(int x = 0; x < 4; x++) {
					for(int y = 0; y < 4; y++) {
						gridBtns = new GameButton(y, x, z[countr]);
						gridBtns.setMinSize(50, 50);
						gridBtns.setOnAction(myHandler);
						gridBtns.setDisable(true);
						arr[y][x] = gridBtns;
						grid.add(arr[y][x], y, x);
						countr++;
					}
				}
				if(nxt10Steps.size() >= 3) {
					PauseTransition step2 = new PauseTransition(Duration.seconds(1));
					step2.setOnFinished(t->{
						grid.getChildren().clear();
						node = nxt10Steps.get(2);
						z = node.getKey();
						countr = 0;
						for(int x = 0; x < 4; x++) {
							for(int y = 0; y < 4; y++) {
								gridBtns = new GameButton(y, x, z[countr]);
								gridBtns.setMinSize(50, 50);
								gridBtns.setOnAction(myHandler);
								gridBtns.setDisable(true);
								arr[y][x] = gridBtns;
								grid.add(arr[y][x], y, x);
								countr++;
							}
						}
						if(nxt10Steps.size() >= 4) {
							PauseTransition step3 = new PauseTransition(Duration.seconds(1));
							step3.setOnFinished(u->{
								grid.getChildren().clear();
								node = nxt10Steps.get(3);
								z = node.getKey();
								countr = 0;
								for(int x = 0; x < 4; x++) {
									for(int y = 0; y < 4; y++) {
										gridBtns = new GameButton(y, x, z[countr]);
										gridBtns.setMinSize(50, 50);
										gridBtns.setOnAction(myHandler);
										gridBtns.setDisable(true);
										arr[y][x] = gridBtns;
										grid.add(arr[y][x], y, x);
										countr++;
									}
								}
								if(nxt10Steps.size() >= 5) {
									PauseTransition step4 = new PauseTransition(Duration.seconds(1));
									step4.setOnFinished(o->{
										grid.getChildren().clear();
										node = nxt10Steps.get(4);
										z = node.getKey();
										countr = 0;
										for(int x = 0; x < 4; x++) {
											for(int y = 0; y < 4; y++) {
												gridBtns = new GameButton(y, x, z[countr]);
												gridBtns.setMinSize(50, 50);
												gridBtns.setOnAction(myHandler);
												gridBtns.setDisable(true);
												arr[y][x] = gridBtns;
												grid.add(arr[y][x], y, x);
												countr++;
											}
										}
										if(nxt10Steps.size() >= 6) {
											PauseTransition step5 = new PauseTransition(Duration.seconds(1));
											step5.setOnFinished(p->{
												grid.getChildren().clear();
												node = nxt10Steps.get(5);
												z = node.getKey();
												countr = 0;
												for(int x = 0; x < 4; x++) {
													for(int y = 0; y < 4; y++) {
														gridBtns = new GameButton(y, x, z[countr]);
														gridBtns.setMinSize(50, 50);
														gridBtns.setOnAction(myHandler);
														gridBtns.setDisable(true);
														arr[y][x] = gridBtns;
														grid.add(arr[y][x], y, x);
														countr++;
													}
												}
												if(nxt10Steps.size() >= 7) {
													PauseTransition step6 = new PauseTransition(Duration.seconds(1));
													step6.setOnFinished(s->{
														grid.getChildren().clear();
														node = nxt10Steps.get(6);
														z = node.getKey();
														countr = 0;
														for(int x = 0; x < 4; x++) {
															for(int y = 0; y < 4; y++) {
																gridBtns = new GameButton(y, x, z[countr]);
																gridBtns.setMinSize(50, 50);
																gridBtns.setOnAction(myHandler);
																gridBtns.setDisable(true);
																arr[y][x] = gridBtns;
																grid.add(arr[y][x], y, x);
																countr++;
															}
														}
														if(nxt10Steps.size() >= 8) {
															PauseTransition step7 = new PauseTransition(Duration.seconds(1));
															step7.setOnFinished(b->{
																grid.getChildren().clear();
																node = nxt10Steps.get(7);
																z = node.getKey();
																countr = 0;
																for(int x = 0; x < 4; x++) {
																	for(int y = 0; y < 4; y++) {
																		gridBtns = new GameButton(y, x, z[countr]);
																		gridBtns.setMinSize(50, 50);
																		gridBtns.setOnAction(myHandler);
																		gridBtns.setDisable(true);
																		arr[y][x] = gridBtns;
																		grid.add(arr[y][x], y, x);
																		countr++;
																	}
																}
																if(nxt10Steps.size() >= 9) {
																	PauseTransition step8 = new PauseTransition(Duration.seconds(1));
																	step8.setOnFinished(n->{
																		grid.getChildren().clear();
																		node = nxt10Steps.get(8);
																		z = node.getKey();
																		countr = 0;
																		for(int x = 0; x < 4; x++) {
																			for(int y = 0; y < 4; y++) {
																				gridBtns = new GameButton(y, x, z[countr]);
																				gridBtns.setMinSize(50, 50);
																				gridBtns.setOnAction(myHandler);
																				gridBtns.setDisable(true);
																				arr[y][x] = gridBtns;
																				grid.add(arr[y][x], y, x);
																				countr++;
																			}
																		}
																		if(nxt10Steps.size() >= 10) {
																			PauseTransition step9 = new PauseTransition(Duration.seconds(1));
																			step9.setOnFinished(m->{
																				grid.getChildren().clear();
																				node = nxt10Steps.get(9);
																				z = node.getKey();
																				countr = 0;
																				for(int x = 0; x < 4; x++) {
																					for(int y = 0; y < 4; y++) {
																						gridBtns = new GameButton(y, x, z[countr]);
																						gridBtns.setMinSize(50, 50);
																						gridBtns.setOnAction(myHandler);
																						gridBtns.setDisable(true);
																						arr[y][x] = gridBtns;
																						grid.add(arr[y][x], y, x);
																						countr++;
																					}
																				}
																				if(nxt10Steps.size() >= 11) {
																					PauseTransition step10 = new PauseTransition(Duration.seconds(1));
																					step10.setOnFinished(j->{
																						grid.getChildren().clear();
																						node = nxt10Steps.get(10);
																						z = node.getKey();
																						countr = 0;
																						for(int x = 0; x < 4; x++) {
																							for(int y = 0; y < 4; y++) {
																								gridBtns = new GameButton(y, x, z[countr]);
																								gridBtns.setMinSize(50, 50);
																								gridBtns.setOnAction(myHandler);
																								gridBtns.setDisable(true);
																								arr[y][x] = gridBtns;
																								grid.add(arr[y][x], y, x);
																								countr++;
																							}
																						}
																						//end.play();
//																						boolean isWon = GameLogic.checkWin(arr);
//																						if(isWon) {
//																							primaryStage.setScene(winGui());
//																						}
//																						AI1.setDisable(false);
//																						AI2.setDisable(false);
//																						enableBoard();
//																						setGameState();
																						end.play();
																					});
																					step10.play();
																				} else {																						end.play();
																					end.play();
																				}
																				
																			});
																			step9.play();
																		} else {
																			end.play();
																		}
																	});
																	step8.play();
																} else {
																	end.play();
																}
															});
															step7.play();
														} else {
															end.play();
														}
													});
													step6.play();
												} else {
													end.play();
												}
											});
											step5.play();
										} else {
											end.play();
										}
									});
									step4.play();
								} else {
									end.play();
								}
							});
							step3.play();
						} else {
							end.play();
						}
					});
					step2.play();
				} else {
					end.play();
				}
			});
			step1.play();
		} else {
			end.play();
		}
		
	}
	
	public void enableBoard() {
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				arr[y][x].setDisable(false);
			}
		}
	}
	public void setGameState() {
		int count = 0;
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				gameState[count] = arr[y][x].getNum();
				count++;
			}
		}
	}
	
	public Scene inGameGUI() {
		BorderPane bp = new BorderPane();
		bg = new Image("inGamePic1.jpg");
		bgView = new ImageView(bg);
		bp.getChildren().addAll(bgView);
		bp.setTop(gameSceneBtns);
		bp.setCenter(grid);
		bp.setBottom(t1);
		return new Scene(bp, 700, 700);
	}
	
	// how to play GUI
		public Scene howToPlayGui() {
			BorderPane bp = new BorderPane();
			bg = new Image("HowToPlay.jpg");
			bgView = new ImageView(bg);
			bp.getChildren().addAll(bgView);
			bp.setBottom(backBtn);
			return new Scene(bp, 700, 700);
		}
		
	// win screen
		public Scene winGui() {
			BorderPane bp = new BorderPane();
			bg = new Image("win.jpg");
			bgView = new ImageView(bg);
			bp.getChildren().addAll(bgView);
			bp.setTop(newGame);
			return new Scene(bp, 700, 700);
		}

	//	public static void callWinScene(){
	//		pause2.play();
	//	}

}
