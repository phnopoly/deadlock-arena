package com.deadlockArena.frontEnd.logic;


import java.awt.event.MouseListener;

import com.deadlockArena.backEnd.dto.ChampionDto;
import com.deadlockArena.frontEnd.Constants;
import com.deadlockArena.frontEnd.exception.CornerCaseException;
import com.deadlockArena.frontEnd.exception.InstanceMismatchException;
import com.deadlockArena.frontEnd.graphics.DeadButton;
import com.deadlockArena.frontEnd.graphics.SlotButton;

import javafx.scene.control.Button;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class SlotGrid extends Grid {

	private String position;

	/**
	 *
	 * @param slotButtons
	 * @param position
	 */
	public SlotGrid(final SlotButton[][] slotButtons, final String position) {
		super(slotButtons);
		this.position = position;
	}

	/**
	 *
	 * @param deads - list of dead champion buttons
	 * @throws CornerCaseException
	 * @throws InstanceMismatchException
	 */
	public void checkForDeads(final DeadButton[] deads) throws CornerCaseException {
		for (int i = 0; i < Constants.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < Constants.SLOT_COL_COUNT; j++) {
				final ChampionDto champion = this.getButton(i, j).getChampionDto();
				if (champion == null) {
					continue;
				} else if (champion.isDead()) {
					this.transferchampion(this.getButton(i, j), deads);
				} else {
					throw new CornerCaseException("checkForDeads() in Grid.class");
				}
			}
		}
	}

	/**
	 *
	 * @param sB    - slot button of champion to transfer
	 * @param deads - list of dead champion buttons
	 */
	private void transferchampion(final SlotButton slotButton, final DeadButton[] deads) {
		final ChampionDto h = slotButton.getChampionDto();

		for (final DeadButton dead : deads) {
			if (dead.getChampionDto() == null) {
				//				dead.insertDead(h);
				break;
			}
		}

		//		slotButton.setBackground(Constants.DEFAULT_BACKGROUND);
		//		slotButton.removeAll();
		slotButton.setChampionDto(null);
		//		slotButton.removeMouseListener(slotButton.getML2());
		//		slotButton.removeMouseListener(slotButton.getML4());

		//		mainFrame.clearSkillButtons(mainFrame.getPlayer()); // error prone
		//		mainFrame.clearPanelEast(mainFrame.getPlayer()); // error prone?
		//		mainFrame.setSlot(null);
	}

	/**
	 * Get the number of champions on the grid
	 *
	 * @return the number of ChampionDtos
	 * @throws InstanceMismatchException
	 */
	public int getNumberOfChampionDtos() {
		int count = 0;
		for (int i = 0; i < super.buttons.length; i++) {
			for (int j = 0; j < super.buttons[i].length; j++) {
				if (super.buttons[i][j] != null) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public SlotButton getButton(final int i, final int j) {
		return (SlotButton) super.buttons[i][j];
	}

	@Override
	public SlotButton[][] getButtons() {
		return (SlotButton[][]) super.buttons;
	}

	@Override
	public void addMouseListener(final int mLNumber) {

		for (final Button[] button : this.buttons) {
			for (final Button element : button) {
				//				this.getButton(i, j).addMouseListener(chooseMouseListener(mLNumber, this.getButton(i, j)));
			}
		}
	}

	private MouseListener chooseMouseListener(final int mLNumber, final SlotButton slotButton) {
		MouseListener mL = null;
		switch (mLNumber) {
		case 1:
			mL = slotButton.getML1();
			break;
		case 2:
			mL = slotButton.getML2();
			break;
		case 3:
			mL = slotButton.getML3();
			break;
		case 4:
			mL = slotButton.getML4();
			break;
		case 5:
			mL = slotButton.getML5();
			break;
		}
		return mL;
	}

}
