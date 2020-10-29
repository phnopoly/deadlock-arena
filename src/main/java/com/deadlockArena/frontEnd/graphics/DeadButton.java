package com.deadlockArena.frontEnd.graphics;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.deadlockArena.Constants;
import com.deadlockArena.backEnd.dto.ChampionDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeadButton extends JButton {
	static final long serialVersionUID = -1629109863348198757L;
	private ChampionDto championDto;

	public DeadButton() {
		super();
		Image newImg = new ImageIcon("pics/DefaultIcon.png ").getImage()
				.getScaledInstance(Constants.PIXEL / 2, Constants.PIXEL / 2, Image.SCALE_SMOOTH);
		setPreferredSize(new Dimension(Constants.PIXEL / 2, Constants.PIXEL / 2));
		setIcon(new ImageIcon(newImg));
		this.championDto = null;
	}

	public void insertDead(ChampionDto championDto) {
		this.championDto = championDto;
		setIcon(new ImageIcon(new ImageIcon("pics/" + championDto + "Icon.png").getImage()
				.getScaledInstance(Constants.PIXEL / 2, Constants.PIXEL / 2, Image.SCALE_SMOOTH)));
	}

}
