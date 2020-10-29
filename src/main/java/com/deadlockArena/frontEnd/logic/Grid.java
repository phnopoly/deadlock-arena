package com.deadlockArena.frontEnd.logic;

import com.deadlockArena.Constants;
import com.deadlockArena.frontEnd.exception.CornerCaseException;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Grid {

	protected Button[][] buttons;

	public void enableAll() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
//				this.getButton(i, j).setEnabled(true);
			}
		}
	}

	public void disableAll() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
//				this.getButton(i, j).setEnabled(false);
			}
		}
	}

	public void enableAllIfSelected() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
//				if (!this.getButton(i, j).isSelected()) {
//					this.getButton(i, j).setEnabled(true);
//				}
			}
		}
	}

	public void clearBorders() {
		for (int i = 0; i < Constants.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < Constants.SLOT_COL_COUNT; j++) {
//				this.getButton(i, j).setBorder(Constants.DEFAULT_BORDER);
			}
		}
	}

	public abstract void addMouseListener(int mLNumber);

	/**
	 * Retrieve the slotButton in the 2D SlotButton array given the i'th and j'th
	 * coordinates.
	 * 
	 * @param i - i'th coordinate.
	 * @param j - j'th coordinate.
	 * @return the j button.
	 */
	public abstract Button getButton(int i, int j);

	/**
	 * Set the jButton of an element in jButtons.
	 * 
	 * @param i       - i'th coordinate.
	 * @param j       - j'th coordinate.
	 * @param jButton - the jButton to set it to.
	 */
	public void setButton(int i, int j, Button button) {
		this.buttons[i][j] = button;
	}

	/**
	 * Get the coordinates of the grid.
	 * 
	 * @param sB - the slotButton to be evaluated.
	 * @return the coordinate.
	 * @throws CornerCaseException
	 */
	public Coordinate getCoord(Button button) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				if (button.equals(buttons[i][j])) {
					return new Coordinate(i, j);
				}
			}
		}
		return null;
	}

}