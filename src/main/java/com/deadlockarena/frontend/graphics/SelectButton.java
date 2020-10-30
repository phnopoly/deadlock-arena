package com.deadlockarena.frontend.graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.deadlockarena.backend.dto.ChampionDto;
import com.deadlockarena.frontend.Constants;
import com.deadlockarena.frontend.DeadlockArenaFrontEnd;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectButton extends Button {
	private static final long serialVersionUID = 8876199740027195332L;

	private boolean selected;
	private ChampionDto championDto;
	private String color;
	private JLabel championLabel, championPicture;
	private ImageView originalSizeImage;
	private ImageIcon normalImage;
	private ImageIcon grayedImage;
	private MouseListener mL;

	public SelectButton(final DeadlockArenaFrontEnd dafe, final String sampleUrl) {
		super.setFont(Constants.BASIC_FONT);
		super.setPrefSize(Constants.PIXEL * 3 / 5, Constants.PIXEL * 3 / 5);
		final Image i = new Image(new File(sampleUrl).toURI().toString());
		this.originalSizeImage = new ImageView(i);
		this.originalSizeImage.setFitHeight(Constants.PIXEL / 2);
		this.originalSizeImage.setFitWidth(Constants.PIXEL / 2);
		super.setGraphic(this.originalSizeImage);

		this.selected = false;
		// this.championDto = championDto;

		this.setGraphics();
		this.evaluateColorByLogic();
		this.setupMouseListeners(dafe);
	}

	// public SelectButton(Game game, ChampionDto championDto) {
	// super.setFont(Constants.BASIC_FONT);
	// super.setPreferredSize(new Dimension(Constants.PIXEL * 4 / 5, Constants.PIXEL
	// * 4 / 5));
	// this.selected = false;
	// // this.championDto = championDto;
	//
	// this.setGraphics();
	// this.evaluateColorByLogic();
	// this.setupMouseListeners(game);
	// }

	private void setGraphics() {

		// this.originalSizeImage = new ImageIcon(
		// this.getClass().getClassLoader().getResource(this.championDto.getName() +
		// "Icon.png"));
		// this.normalImage = new
		// ImageIcon(this.originalSizeImage.getImage().getScaledInstance(Constants.PIXEL
		// / 2,
		// Constants.PIXEL / 2, Image.SCALE_SMOOTH));
		// this.grayedImage = new
		// ImageIcon(GrayFilter.createDisabledImage(this.normalImage.getImage()));
		//
		// super.setLayout(null);
		// this.championPicture = new JLabel(this.normalImage);
		// this.championPicture.setBounds(20, 20, Constants.PIXEL / 2, Constants.PIXEL /
		// 2);
		// super.add(this.championPicture);
		//
		// this.championLabel = new JLabel(this.championDto.getName());
		// this.championLabel.setForeground(Constants.DEFAULT_BACKGROUND);
		// this.championLabel.setFont(Constants.SELECT_BUTTON_CHAMPION_FONT);
		// this.championLabel.setBounds(5, 5, Constants.PIXEL, 10);
		// super.add(this.championLabel);
	}

	private void evaluateColorByLogic() {
		// TO-DO : get logic
		//		switch (this.championDto.getLogic()) {
		switch (2) {
		case 1:
			this.color = "fx-background-color: pink;";
			break;
		case 2:
			this.color = "fx-background-color: green;";
			break;
		case 3:
			this.color = "fx-background-color: cyan;";
			break;
		}
		super.setStyle(this.color);
	}

	private void setupMouseListeners(final DeadlockArenaFrontEnd dafe) {

		final SelectButton thisButton = this;
		this.mL = new MouseAdapter() {
			public void mousePressed(final MouseEvent e) {
				if (!thisButton.isDisable()) {
					Constants.AAS.playSound("select");
					dafe.displayPreview(thisButton.championDto, thisButton.originalSizeImage);
					dafe.getSelectGrid().disableAll();
					thisButton.setSelected(true);
					dafe.setCurrentSelect(thisButton);
					dafe.selectMLMousePressed();
				}
			}

			public void mouseExited(final MouseEvent e) {
				if (!thisButton.isDisabled()) {
					dafe.unDisplayPreview(thisButton.color);
					thisButton.setStyle(thisButton.getColor());
				}
			}

			public void mouseEntered(final MouseEvent e) {
				if (!thisButton.isDisabled()) {
					dafe.displayPreview(thisButton.championDto, thisButton.originalSizeImage);
					thisButton.setStyle("fx-background-color: gray");
				}
			}
		};
	}

}
