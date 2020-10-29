package com.deadlockArena.frontEnd.graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;

import com.deadlockArena.Constants;
import com.deadlockArena.frontEnd.exception.CornerCaseException;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BooleanButton extends Button {
	private static final long serialVersionUID = -4636335771363956811L;

	private String fileName;
	private ImageIcon icon;
	private boolean on;

	public BooleanButton(String fileName) throws CornerCaseException {
		String start = "";
		if (fileName.equals("pics/music")) {
			start = "offIcon.png";
			this.on = false;
		} else if (fileName.equals("pics/sound")) {
			start = "onIcon.png";
			this.on = true;
		} else if (fileName.equals("pics/loop")) {
			start = "onIcon.png";
			this.on = true;
		} else {
			throw new CornerCaseException("BooleanButton: Incorrect filename input");
		}
		ImageView iv = new ImageView(new Image(new File(fileName + start).toURI().toString()));
		iv.setFitHeight(Constants.PIXEL / 2);
		iv.setFitWidth(Constants.PIXEL / 2);
		super.setGraphic(iv);
//		addMouseListener(mL);
		this.fileName = fileName;
	}

	MouseListener mL = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			BooleanButton bb = BooleanButton.this;

//			if (bb.isOn())
//				bb.setIcon(new ImageIcon(new ImageIcon(fileName + "OffIcon.png").getImage()
//						.getScaledInstance(Constants.PIXEL / 2, Constants.PIXEL / 2, Image.SCALE_SMOOTH)));
//			else
//				bb.setIcon(new ImageIcon(new ImageIcon(fileName + "OnIcon.png").getImage()
//						.getScaledInstance(Constants.PIXEL / 2, Constants.PIXEL / 2, Image.SCALE_SMOOTH)));

			bb.invert();
			// evalFunctionality();
		}
	};

	void invert() {
		on = !on;
	}

	void evalFunctionality(AnimationAndSound aAS) {
		String func = fileName.substring(fileName.lastIndexOf("/") + 1);
		switch (func) {
		case "sound":
			break;
		case "music":
			if (on) {
				aAS.stopMusic();
				if (aAS.getLoopButton().isOn())
					aAS.loopMusic();
				else
					aAS.startMusic();
			} else {
				aAS.stopMusic();
			}
			break;
		case "loop":
//			if (isEnabled()) {
//				aAS.stopMusic();
//				if (on) {
//					aAS.getSoundtrackButton().setEnabled(false);
//					if (aAS.getMusicButton().isOn())
//						aAS.loopMusic();
//				} else {
//					aAS.getSoundtrackButton().setEnabled(true);
//					if (aAS.getMusicButton().isOn())
//						aAS.startMusic();
//				}
//			}
		default:
		}
	}

}
