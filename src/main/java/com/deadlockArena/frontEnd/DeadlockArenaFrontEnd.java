package com.deadlockArena.frontEnd;

import java.util.Stack;

import com.deadlockArena.backEnd.dto.ChampionDto;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeadlockArenaFrontEnd extends Application {

	private static final String JSON_URL = "https://api.myjson.com/bins/3jwmh";

	//	WebResource webResource = client.resource("http://localhost:8080/UIBakerRESTJerseyServer/rest/ttocservice/")
	//			.path(value);

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

	private Stack<Button[]> orderList;
	private Scene mainScene;

	public static void main(final String[] args) {

		// Game g = new Game();
		// g.executePhase1();
		// g.executePhase2();
		Application.launch(args);
	}

	@Override
	public void start(final Stage mainStage) throws Exception {
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
		this.mainScene = new Scene(this.mainLayout, 500, 600);
		mainStage.setScene(this.mainScene);
		mainStage.show();

		// primaryStage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// primaryStage.setLayout(new BorderLayout());
		// // super.setLocationRelativeTo(null);
		// primaryStage.pack();
		// primaryStage.setVisible(true);

		this.orderList = new Stack<>();

		this.addPanels();
		try {
			this.addSelectButtons(this.selectGrid);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.addSlotButtons(this.slotGrid1, this.slotGrid2);

		this.selectGrid.addMouseListener(1);
		this.slotGrid1.addMouseListener(1);
	}

	public void addPanels() {
		this.paneWest = new BorderPane();
		this.paneWest.setStyle("-fx-background-color: red;");
		{
			this.paneWest_a = new GridPane();
			this.paneWest_a.setStyle("-fx-background-color: blue;");
			this.paneWest_a.add(new Button(), 0, 0);

			this.paneWest_b = new GridPane();
			this.paneWest_b.setStyle("-fx-background-color: purple;");
			this.paneWest_b.setPadding(new Insets(5, 5, 5, 5));
			this.paneWest_b.setVgap(10);
			this.paneWest_b.setHgap(10);
			{
				this.iconLabel = new Label("icon label");
				this.iconLabel.setTextFill(Color.WHITE);

				this.stats = new Label("stats label");
				this.stats.setFont(Font.font("Verdana", 20));
				this.stats.setTextFill(Color.WHITE);

				this.description = new Label("description");
				this.description.setTextFill(Color.WHITE);
				this.description.setFont(Font.font("Verdana", 20));
				this.description.setPrefSize(50, 50);
				// description.setWrapStyleWord(true);
				// description.setLineWrap(true);
				// description.setBackground(Constants.DEFAULT_BACKGROUND);

				this.championLabel = new Label("champion label");
				this.championLabel.setFont(Font.font("Verdana", 20));
				this.championLabel.setTextFill(Color.WHITE);
				// championLabel.setText("");
			}
			this.paneWest_b.add(this.iconLabel, 0, 0, 1, 1);
			this.paneWest_b.add(this.description, 1, 0);
			this.paneWest_b.add(this.stats, 0, 1);
			this.paneWest_b.add(this.championLabel, 1, 1);
		}

		this.paneWest.setTop(this.paneWest_a);
		this.paneWest.setBottom(this.paneWest_b);

		// this.selectGrid.addMouseListener(1);
		// this.slotGrid1.addMouseListener(1);

		// ---------------------------------------------------------------------------
		this.paneCenter = new BorderPane();
		this.paneCenter.setStyle("-fx-background-color: red;");
		{
			this.paneCenter_a = new GridPane();
			this.paneCenter_a.setStyle("-fx-background-color: pink;");
			this.paneCenter_b = new GridPane();
			this.paneCenter_b.setStyle("-fx-background-color: orange;");
			this.paneCenter_c = new BorderPane();
			this.paneCenter_c.setStyle("-fx-background-color: beige;");
		}
		this.paneCenter.setTop(this.paneCenter_a);
		this.paneCenter.setBottom(this.paneCenter_b);
		this.paneCenter.setCenter(this.paneCenter_c);
		// ---------------------------------------------------------------------------
		this.paneNorth = new BorderPane();
		this.paneNorth.setStyle(Constants.DEFAULT_COLOR);
		{
			this.titleLabel = new Label("Deadlock Arena");
			this.titleLabel.setFont(Constants.LARGE_FONT);
			this.titleLabel.setTextFill(Color.RED);
		}
		this.paneNorth.setLeft(this.titleLabel);
		// ---------------------------------------------------------------------------
		this.paneSouth = new FlowPane();
		this.paneSouth.setStyle(Constants.DEFAULT_COLOR);
		{
			this.playerLabel = new Label("Player 1");
			this.playerLabel.setFont(Constants.LARGE_FONT);
			this.playerLabel.setTextFill(Color.RED);
			this.cancel = new CancelButton();
		}
		this.paneSouth.getChildren().add(Constants.AAS.getSoundButton());
		this.paneSouth.getChildren().add(Constants.AAS.getMusicButton());
		this.paneSouth.getChildren().add(Constants.AAS.getLoopButton());
		// this.paneSouth.getChildren().add(Constants.AAS.getSoundtrackButton());
		this.paneSouth.getChildren().add(this.playerLabel);
		this.paneSouth.getChildren().add(this.cancel);
		// ---------------------------------------------------------------------------

		this.paneWest.setPrefSize(600, 650);
		this.paneCenter.setPrefSize(400, 650);
		this.paneSouth.setPrefSize(1000, 50);

		this.mainLayout.setLeft(this.paneWest);
		this.mainLayout.setCenter(this.paneCenter);
		this.mainLayout.setTop(this.paneNorth);
		this.mainLayout.setBottom(this.paneSouth);
	}

	public void addSelectButtons(final SelectGrid selectGrid) throws Exception {
		for (int i = 0; i < selectGrid.getButtons().length; i++) {
			for (int j = 0; j < selectGrid.getButtons()[i].length; j++) {
				// ChampionDto c = microservice.getChampion(Constants.CHAMPIONS [ i ] [ j ]);
				// SelectButton selectButton = new SelectButton(new ChampionDto());
				// TO-DO : replace with Champion info and refactor SelectButton constructor
				final String sampleURLStr = "pics/" + Constants.CHAMPIONS[i][j] + "Icon.png";
				final SelectButton selectButton = new SelectButton(this, sampleURLStr);
				selectGrid.setButton(i, j, selectButton);
				this.paneWest_a.add(selectButton, j, i);
			}
		}
	}

	public void addSlotButtons(final SlotGrid slotGrid1, final SlotGrid slotGrid2) {
		for (int player = 1; player <= 2; player++) {
			final GridPane pane = player == 2 ? this.paneCenter_a : this.paneCenter_b;
			final Insets insets = player == 2 ? new Insets(50, 50, 10, 50) : new Insets(10, 50, 50, 50);
			final SlotGrid slotGrid = player == 2 ? slotGrid2 : slotGrid1;
			pane.setPadding(insets);
			for (int i = 0; i < Constants.SLOT_ROW_COUNT; i++) {
				for (int j = 0; j < Constants.SLOT_COL_COUNT; j++) {
					final String position = player == 2 ? "top" : "bottom";
					final SlotButton slotButton = new SlotButton(position, new Coordinate(i, j));
					slotGrid.setButton(i, j, slotButton);
					pane.add(slotButton, j, i);
				}
			}
		}
	}

	public void displayPreview(final ChampionDto championDto, final ImageView iv) {
		this.iconLabel.setGraphic(iv);
		this.stats.setText(Constants.getStatsText(championDto));
		this.description.setText(championDto.getDescription());
		this.championLabel.setText(championDto.getName());
	}

	public void unDisplayPreview(final String color) {
		this.iconLabel.setGraphic(null);
		this.stats.setText("");
		this.description.setText("");
		this.championLabel.setText("");
	}

	public void selectMLMousePressed() {
		this.slotGrid1.enableAll();
	}

}
