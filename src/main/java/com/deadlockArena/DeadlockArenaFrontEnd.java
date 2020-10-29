package com.deadlockArena;

import java.util.Stack;

import com.deadlockArena.frontEnd.graphics.AnimationAndSound;
import com.deadlockArena.frontEnd.graphics.CancelButton;
import com.deadlockArena.frontEnd.graphics.MainFrame;
import com.deadlockArena.frontEnd.graphics.SelectButton;
import com.deadlockArena.frontEnd.graphics.SlotButton;
import com.deadlockArena.frontEnd.logic.Coordinate;
import com.deadlockArena.frontEnd.logic.MainLogic;
import com.deadlockArena.frontEnd.logic.SelectGrid;
import com.deadlockArena.frontEnd.logic.SlotGrid;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DeadlockArenaFrontEnd extends Application {

	private MainFrame mainFrame;
	private MainLogic mainLogic;

	private SelectGrid selectGrid;
	private SlotGrid slotGrid1, slotGrid2;

	private SelectButton currentSelect;
	private SlotButton currentSlot;

	private int player;
	private int totalCount; // 0-18

	private int move;
	private int currentCap;

	// stage is the window.
	// scene is the panel.

	private BorderPane mainLayout;

	private BorderPane paneWest;
	private GridPane paneWest_a;
	private GridPane paneWest_b;

	private BorderPane paneCenter;
	private GridPane paneCenter_a;
	private GridPane paneCenter_b;
	private BorderPane paneCenter_c;
	private Scene paneCenter_c_a;
	private Scene paneCenter_c_b;

	private Scene paneEast;
	private Scene paneEast_a;
	private Scene paneEast_b;

	private BorderPane paneNorth;
	private FlowPane paneSouth;

	private Label iconLabel;
	private Label stats;
	private Label championLabel;
	private Label description;

	// panelNorth components
	private Label titleLabel;
	private Label playerLabel;

	// panelSouth components
	private CancelButton cancel;

	AnimationAndSound aAS;
	private Stack<Button[]> orderList;
	private Scene mainScene;

	public static void main(String[] args) {

//		Game g = new Game();
//		g.executePhase1();
//		g.executePhase2();
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		this.mainLogic = new MainLogic();

		this.selectGrid = new SelectGrid(new SelectButton[Constants.SELECT_ROW_COUNT][Constants.SELECT_COL_COUNT]);
		this.slotGrid1 = new SlotGrid(new SlotButton[Constants.SLOT_ROW_COUNT][Constants.SLOT_COL_COUNT], "bottom");
		this.slotGrid2 = new SlotGrid(new SlotButton[Constants.SLOT_ROW_COUNT][Constants.SLOT_COL_COUNT], "top");

		this.player = 1;
		this.totalCount = 0;
		this.move = 0;
		this.currentCap = 1;

		mainStage.setTitle("Deadlock Arena");
		mainStage.setWidth(900);
		mainStage.setHeight(700);

		this.mainLayout = new BorderPane();
		this.mainScene = new Scene(mainLayout, 500, 600);
		mainStage.setScene(this.mainScene);
		mainStage.show();

//		primaryStage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		primaryStage.setLayout(new BorderLayout());
//		// super.setLocationRelativeTo(null);
//		primaryStage.pack();
//		primaryStage.setVisible(true);

		this.aAS = new AnimationAndSound();
		this.orderList = new Stack<>();

		this.addPanels();
		try {
			addSelectButtons(selectGrid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.addSlotButtons(slotGrid1, slotGrid2);
	}

	public void addPanels() {
		this.paneWest = new BorderPane();
		this.paneWest.setStyle("-fx-background-color: red;");
		{
			paneWest_a = new GridPane();
			paneWest_a.setStyle("-fx-background-color: blue;");
			paneWest_a.add(new Button(), 0, 0);

			paneWest_b = new GridPane();
			paneWest_b.setStyle("-fx-background-color: purple;");
			paneWest_b.setPadding(new Insets(5, 5, 5, 5));
			paneWest_b.setVgap(10);
			paneWest_b.setHgap(10);
			{
				iconLabel = new Label("icon label");
				iconLabel.setTextFill(Color.WHITE);

				stats = new Label("stats label");
				stats.setFont(Font.font("Verdana", 20));
				stats.setTextFill(Color.WHITE);

				description = new Label("description");
				description.setTextFill(Color.WHITE);
				description.setFont(Font.font("Verdana", 20));
//				description.setPrefSize(50, 50);
//				description.setWrapStyleWord(true);
//				description.setLineWrap(true);
//				description.setBackground(Constants.DEFAULT_BACKGROUND);

				championLabel = new Label("champion label");
				championLabel.setFont(Font.font("Verdana", 20));
				championLabel.setTextFill(Color.WHITE);
//				championLabel.setText("");
			}
			paneWest_b.add(iconLabel, 0, 0, 1, 1);
			paneWest_b.add(description, 1, 0);
			paneWest_b.add(stats, 0, 1);
			paneWest_b.add(championLabel, 1, 1);
		}

		paneWest.setTop(paneWest_a);
		paneWest.setBottom(paneWest_b);

		this.selectGrid.addMouseListener(1);
		this.slotGrid1.addMouseListener(1);

		// ---------------------------------------------------------------------------
		paneCenter = new BorderPane();
		paneCenter.setStyle("-fx-background-color: red;");
		{
			paneCenter_a = new GridPane();
			paneCenter_a.setStyle("-fx-background-color: pink;");
			paneCenter_b = new GridPane();
			paneCenter_b.setStyle("-fx-background-color: orange;");
			paneCenter_c = new BorderPane();
			paneCenter_c.setStyle("-fx-background-color: beige;");
		}
		paneCenter.setTop(paneCenter_a);
		paneCenter.setBottom(paneCenter_b);
		paneCenter.setCenter(paneCenter_c);
		// ---------------------------------------------------------------------------
		paneNorth = new BorderPane();
		paneNorth.setStyle(Constants.DEFAULT_COLOR);
		{
			titleLabel = new Label("Deadlock Arena");
			titleLabel.setFont(Constants.LARGE_FONT);
			titleLabel.setTextFill(Color.RED);
		}
		paneNorth.setLeft(titleLabel);
		// ---------------------------------------------------------------------------
		paneSouth = new FlowPane();
		paneSouth.setStyle(Constants.DEFAULT_COLOR);
		{
			playerLabel = new Label("Player 1");
			playerLabel.setFont(Constants.LARGE_FONT);
			playerLabel.setTextFill(Color.RED);
			cancel = new CancelButton();
		}
		paneSouth.getChildren().add(aAS.getSoundButton());
		paneSouth.getChildren().add(aAS.getMusicButton());
		paneSouth.getChildren().add(aAS.getLoopButton());
		paneSouth.getChildren().add(aAS.getSoundtrackButton());
		paneSouth.getChildren().add(playerLabel);
		paneSouth.getChildren().add(cancel);
		// ---------------------------------------------------------------------------

		paneWest.setPrefSize(600, 650);
		paneCenter.setPrefSize(400, 650);
		paneSouth.setPrefSize(1000, 50);

		this.mainLayout.setLeft(paneWest);
		this.mainLayout.setCenter(paneCenter);
		this.mainLayout.setTop(paneNorth);
		this.mainLayout.setBottom(paneSouth);
	}

	public void addSelectButtons(SelectGrid selectGrid) throws Exception {
		for (int i = 0; i < selectGrid.getButtons().length; i++) {
			for (int j = 0; j < selectGrid.getButtons()[i].length; j++) {
//				ChampionDto c = microservice.getChampion(Constants.CHAMPIONS [ i ] [ j ]);
//				SelectButton selectButton = new SelectButton(new ChampionDto());
				SelectButton selectButton = new SelectButton();
				selectGrid.setButton(i, j, selectButton);
				this.paneWest_a.add(selectButton, j, i);
			}
		}
	}

	public void addSlotButtons(SlotGrid slotGrid1, SlotGrid slotGrid2) {
		for (int player = 1; player <= 2; player++) {
			GridPane pane = player == 2 ? this.paneCenter_a : this.paneCenter_b;
			Insets insets = player == 2 ? new Insets(50, 50, 10, 50) : new Insets(10, 50, 50, 50);
			SlotGrid slotGrid = player == 2 ? slotGrid2 : slotGrid1;
			pane.setPadding(insets);
			for (int i = 0; i < Constants.SLOT_ROW_COUNT; i++) {
				for (int j = 0; j < Constants.SLOT_COL_COUNT; j++) {
					SlotButton slotButton = new SlotButton(player == 2 ? "top" : "bottom", new Coordinate(i, j));
					slotGrid.setButton(i, j, slotButton);
					pane.add(slotButton, j, i);
				}
			}
		}
	}
}
