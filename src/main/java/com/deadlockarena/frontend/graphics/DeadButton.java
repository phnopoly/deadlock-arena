package com.deadlockarena.frontend.graphics;




import javax.swing.JButton;

import com.deadlockarena.backend.dto.ChampionDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeadButton extends JButton {
	static final long serialVersionUID = -1629109863348198757L;
	private ChampionDto championDto;

	public DeadButton() {
		super();
		//		final Image newImg = new ImageIcon("pics/DefaultIcon.png ").getImage()
		//				.getScaledInstance(Constants.PIXEL / 2, Constants.PIXEL / 2, Image.SCALE_SMOOTH);
		//		this.setPreferredSize(new Dimension(Constants.PIXEL / 2, Constants.PIXEL / 2));
		//		setIcon(new ImageIcon(newImg));
		this.championDto = null;
	}

	//	public void insertDead(final ChampionDto championDto) {
	//		this.championDto = championDto;
	//		this.setIcon(new ImageIcon(new ImageIcon("pics/" + championDto + "Icon.png").getImage()
	//				.getScaledInstance(Constants.PIXEL / 2, Constants.PIXEL / 2, Image.SCALE_SMOOTH)));
	//	}

}
