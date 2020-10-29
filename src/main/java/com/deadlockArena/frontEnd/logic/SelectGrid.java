package com.deadlockArena.frontEnd.logic;

import com.deadlockArena.frontEnd.graphics.SelectButton;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectGrid extends Grid {

	public SelectGrid(SelectButton[][] selectButtons) {
		super(selectButtons);
	}

	@Override
	public SelectButton getButton(int i, int j) {
		return (SelectButton) super.buttons[i][j];
	}

	@Override
	public SelectButton[][] getButtons() {
		return (SelectButton[][]) super.buttons;
	}

	@Override
	public void addMouseListener(int mLNumber) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void addMouseListener(int mLNumber) {
//		for (int i = 0; i < super.jButtons.length; i++) {
//			for (int j = 0; j < super.jButtons [ i ].length; j++) {
//				this.getJButton(i, j).addMouseListener(this.getJButton(i, j).getML());
//			}
//		}
//	}

}
