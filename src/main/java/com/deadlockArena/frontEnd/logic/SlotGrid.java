package com.deadlockArena.frontEnd.logic;

import java.awt.event.MouseListener;

import com.deadlockArena.Constants;
import com.deadlockArena.dto.ChampionDto;
import com.deadlockArena.exception.CornerCaseException;
import com.deadlockArena.exception.InstanceMismatchException;
import com.deadlockArena.frontEnd.graphics.DeadButton;
import com.deadlockArena.frontEnd.graphics.SlotButton;

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
	public SlotGrid(SlotButton [ ] [ ] slotButtons, String position) {
		super(slotButtons);
		this.position = position;
	}

	/**
	 * 
	 * @param deads - list of dead champion buttons
	 * @throws CornerCaseException
	 * @throws InstanceMismatchException
	 */
	public void checkForDeads(DeadButton [ ] deads) throws CornerCaseException {
		for (int i = 0; i < Constants.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < Constants.SLOT_COL_COUNT; j++) {
				ChampionDto champion = this.getJButton(i, j).getChampionDto();
				if (champion == null) {
					continue;
				} else if (champion.isDead()) {
					transferchampion(getJButton(i, j), deads);
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
	private void transferchampion(SlotButton slotButton, DeadButton [ ] deads) {
		ChampionDto h = slotButton.getChampionDto();

		for (int i = 0; i < deads.length; i++)
			if (deads [ i ].getChampionDto() == null) {
				deads [ i ].insertDead(h);
				break;
			}

		slotButton.setBackground(Constants.DEFAULT_BACKGROUND);
		slotButton.removeAll();
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
		for (int i = 0; i < super.jButtons.length; i++) {
			for (int j = 0; j < super.jButtons [ i ].length; j++) {
				if (super.jButtons [ i ] [ j ] != null) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public SlotButton getJButton(int i, int j) {
		return (SlotButton) super.jButtons [ i ] [ j ];
	}

	@Override
	public SlotButton [ ] [ ] getJButtons() {
		return (SlotButton [ ] [ ]) super.jButtons;
	}

	@Override
	public void addMouseListener(int mLNumber) {

		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons [ i ].length; j++) {
				this.getJButton(i, j)
						.addMouseListener(chooseMouseListener(mLNumber, this.getJButton(i, j)));
			}
		}
	}

	private MouseListener chooseMouseListener(int mLNumber, SlotButton slotButton) {
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
