package com.deadlockarena.frontend;

import com.deadlockarena.frontend.graphics.MainFrame;
import com.deadlockarena.frontend.graphics.SelectButton;
import com.deadlockarena.frontend.graphics.SlotButton;
import com.deadlockarena.frontend.logic.MainLogic;
import com.deadlockarena.frontend.logic.SelectGrid;
import com.deadlockarena.frontend.logic.SlotGrid;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {
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

	public Game() {
		this.mainFrame = new MainFrame();
		this.mainLogic = new MainLogic();

		this.selectGrid = new SelectGrid(new SelectButton[Constants.SELECT_ROW_COUNT][Constants.SELECT_COL_COUNT]);
		this.slotGrid1 = new SlotGrid(new SlotButton[Constants.SLOT_ROW_COUNT][Constants.SLOT_COL_COUNT], "bottom");
		this.slotGrid2 = new SlotGrid(new SlotButton[Constants.SLOT_ROW_COUNT][Constants.SLOT_COL_COUNT], "top");

		this.player = 1;
		this.totalCount = 0;
		this.move = 0;
		this.currentCap = 1;
	}

	public void executePhase1() {
//		this.mainFrame.addPanels();
//		try {
//			this.mainFrame.addSelectButtons(this, selectGrid);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		this.mainFrame.addSlotButtons(this, slotGrid1, slotGrid2);

		this.selectGrid.addMouseListener(1);
		this.slotGrid1.addMouseListener(1);
		// this.slotGrid1.addMouseListener(SlotButton.mL2);
	}

	// TO-DO move some functionality here
	public void executePhase2() {

	}

	public void evalTurns(SlotButton slot) {
		this.move++;
		if (this.move == currentCap) {
			slot = null;
			if (player == 2) {
				mainLogic.updateAllCoolDowns(slotGrid2);
			} else {
				mainLogic.updateAllCoolDowns(slotGrid1);
			}
			// mainLogic.switchListeners(slotGrid1, slotGrid2, player,
			// mainFrame.getMessages());
//			mainFrame.clearSkillButtons(player);
//			mainFrame.clearPanelEast(player);
			if (currentCap < Constants.CAP_TURN) {
				currentCap++;
			}
			this.move = 0;
		}
	}

	public void switchPlayer() {
		this.player = this.player == 2 ? 1 : 2;
	}

	public void selectMLMousePressed() {
		this.slotGrid1.enableAll();
	}
}
