package com.deadlockArena.frontEnd.graphics;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.deadlockArena.Constants;
import com.deadlockArena.dto.ChampionDto;

import javafx.scene.control.Button;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectButton extends Button {
	private static final long serialVersionUID = 8876199740027195332L;

	private boolean selected;
	private ChampionDto championDto;
	private Color color;
	private JLabel championLabel, championPicture;
	private ImageIcon originalSizeImage, normalImage, grayedImage;
	private MouseListener mL;

	public SelectButton() {
//		super.setFont(Constants.BASIC_FONT);
//		super.setPreferredSize(new Dimension(Constants.PIXEL * 4 / 5, Constants.PIXEL * 4 / 5));
		super.setPrefSize(Constants.PIXEL * 3 / 5, Constants.PIXEL * 3 / 5);
//		ImageView iv = new ImageView(new Image(new File(fileName + start).toURI().toString()));
//		iv.setFitHeight(Constants.PIXEL / 2);
//		iv.setFitWidth(Constants.PIXEL / 2);
//		super.setGraphic(iv);

		this.selected = false;
		// this.championDto = championDto;

//		this.setGraphics();
//		this.evaluateColorByLogic();
//		this.setupMouseListeners(game);
	}

//	public SelectButton(Game game, ChampionDto championDto) {
//		super.setFont(Constants.BASIC_FONT);
//		super.setPreferredSize(new Dimension(Constants.PIXEL * 4 / 5, Constants.PIXEL * 4 / 5));
//		this.selected = false;
//		// this.championDto = championDto;
//
//		this.setGraphics();
//		this.evaluateColorByLogic();
//		this.setupMouseListeners(game);
//	}
//
//	private void setGraphics() {
//
//		this.originalSizeImage = new ImageIcon(
//				this.getClass().getClassLoader().getResource(championDto.getName() + "Icon.png"));
//		this.normalImage = new ImageIcon(originalSizeImage.getImage().getScaledInstance(Constants.PIXEL / 2,
//				Constants.PIXEL / 2, Image.SCALE_SMOOTH));
//		this.grayedImage = new ImageIcon(GrayFilter.createDisabledImage(normalImage.getImage()));
//
//		super.setLayout(null);
//		this.championPicture = new JLabel(normalImage);
//		this.championPicture.setBounds(20, 20, Constants.PIXEL / 2, Constants.PIXEL / 2);
//		super.add(championPicture);
//
//		this.championLabel = new JLabel(championDto.getName());
//		this.championLabel.setForeground(Constants.DEFAULT_BACKGROUND);
//		this.championLabel.setFont(Constants.SELECT_BUTTON_CHAMPION_FONT);
//		this.championLabel.setBounds(5, 5, Constants.PIXEL, 10);
//		super.add(championLabel);
//	}
//
//	private void evaluateColorByLogic() {
//		switch (championDto.getLogic()) {
//		case 1:
//			this.color = Color.pink;
//			break;
//		case 2:
//			this.color = Color.green;
//			break;
//		case 3:
//			this.color = Color.cyan;
//			break;
//		}
//		super.setBackground(color);
//	}
//
//	private void setupMouseListeners(Game game) {
//		MainFrame mainFrame = game.getMainFrame();
//		SelectGrid selectGrid = game.getSelectGrid();
//		SelectButton thisButton = this;
//		this.mL = new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (thisButton.isEnabled()) {
//					mainFrame.getAAS().playSound("select");
//					mainFrame.displayPreview(championDto, originalSizeImage);
//					selectGrid.disableAll();
//					thisButton.setSelected(true);
//					game.setCurrentSelect(thisButton);
//					game.selectMLMousePressed();
//				}
//			}
//
//			public void mouseExited(MouseEvent e) {
//				if (thisButton.isEnabled()) {
//					mainFrame.unDisplayPreview(color);
//					thisButton.setBackground(thisButton.getColor());
//				}
//			}
//
//			public void mouseEntered(MouseEvent e) {
//				if (thisButton.isEnabled()) {
//					mainFrame.displayPreview(championDto, originalSizeImage);
//					thisButton.setBackground(Color.gray);
//				}
//			}
//		};
//	}

}
