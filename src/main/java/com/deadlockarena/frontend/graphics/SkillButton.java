package com.deadlockarena.frontend.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SkillButton extends JButton {
	private static final long serialVersionUID = -498760128876833290L;

	private BufferedImage color;
	private BufferedImage gray;
	private double fraction;

	public SkillButton() {
		//		setBackground(Constants.DEFAULT_BACKGROUND);
		try {
			this.gray = ImageIO.read(new File("pics/gray.png"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void setSkillButton(final String filename, final double fraction) {
		if (filename == null) {
			this.color = null;
			this.repaint();
			return;
		}
		this.fraction = fraction;
		try {
			this.color = ImageIO.read(new File(filename));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	//	@Override
	//	public void paintComponent(final Graphics g) {
	//		if (this.color == null) {
	//			super.paintComponent(g);
	//			return;
	//		}
	//		final Graphics2D g2d = (Graphics2D) g.create();
	//		g2d.drawImage(this.gray, 0, 0, this);
	//		g2d.drawImage(this.maskedEffect(), 0, 0, this);
	//		g2d.dispose();
	//	}
	//
	//	public BufferedImage maskedEffect() {
	//		final int width = this.color.getWidth();
	//		final int height = this.color.getHeight();
	//		final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	//		final Graphics2D g2d = img.createGraphics();
	//		final Arc2D.Double expose = new Arc2D.Double(-(width / 2d), -(height / 2d), width * 2d, height * 2d, 90,
	//				-(360.0d * this.fraction), Arc2D.PIE);
	//		g2d.fill(expose);
	//		g2d.setComposite(AlphaComposite.SrcIn);
	//		g2d.drawImage(this.color, 0, 0, this);
	//		g2d.dispose();
	//		return img;
	//	}

}
