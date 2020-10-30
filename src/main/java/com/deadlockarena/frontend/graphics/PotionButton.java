package com.deadlockarena.frontend.graphics;


import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.deadlockarena.backend.dto.ChampionDto;
import com.deadlockarena.frontend.Constants;

import javafx.scene.input.MouseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PotionButton extends JButton {
	private static final long serialVersionUID = 3192411745294200580L;

	private JLabel jl;
	private boolean cp;
	private SlotButton sB;

	public PotionButton(final ImageIcon ic, final boolean cp, final MainFrame mainFrame) {
		super(ic);
		this.jl = new JLabel();
		//		jl.setForeground(Constants.DEFAULT_BACKGROUND);
		//		jl.setFont(Constants.BASIC_FONT);
		this.jl.setOpaque(false);
		this.jl.setBounds(0, 0, 20, 15);
		this.add(this.jl);
		this.cp = cp;
		this.addMouseListener(this.mL);

		this.setPreferredSize(new Dimension(Constants.PIXEL * 3 / 5, Constants.PIXEL * 3 / 5));
		this.setLayout(null);
		this.setEnabled(false);
		this.setDisabledIcon(ic);
	}

	public void onChampion(final SlotButton sB) {
		this.setEnabled(true);
		this.sB = sB;
		if (this.cp) {
			this.jl.setText("" + sB.getChampionDto().getPotionInventory().getHpPotions().size());
		} else {
			this.jl.setText("" + sB.getChampionDto().getPotionInventory().getMpPotions().size());
		}
	}

	public void offchampion() {
		this.setEnabled(false);
		this.sB = null;
		this.jl.setText("");
	}

	private MouseListener mL = new MouseAdapter() {
		public void mousePressed(final MouseEvent e) {
			if (PotionButton.this.sB == null) {
				return;
			}
			final ChampionDto c = PotionButton.this.getSB().getChampionDto();
			final int[] info = PotionButton.this.sB.getChampionDto().drinkPotion(PotionButton.this.cp);
			if (info == null) {
				return;
			}
			if (PotionButton.this.cp) {
				PotionButton.this.jl.setText("" + c.getPotionInventory().getHpPotions().size());
			} else {
				PotionButton.this.jl.setText("" + c.getPotionInventory().getMpPotions().size());
			}

			//            mainFrame.getMP().generateMove(mainFrame.getMessages(), mainFrame.getMove());
			//            mainFrame.getMP().generateMessage(mainFrame.getMessages(), c, info[0], info[1], cp);
			//            mainFrame.setPanelEast(sB, mainFrame.getPlayer());
			//            mainFrame.resetListeners();
			//            mainFrame.clearAllBorders();
			//            mainFrame.evalTurns();
		}
	};
}
