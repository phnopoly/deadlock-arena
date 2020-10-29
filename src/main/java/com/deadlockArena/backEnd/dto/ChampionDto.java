package com.deadlockArena.backEnd.dto;

import java.io.Serializable;

import com.deadlockArena.Constants;
import com.deadlockArena.backEnd.entity.HpPotion;
import com.deadlockArena.backEnd.entity.MpPotion;
import com.deadlockArena.backEnd.entity.PotionInventory;
import com.deadlockArena.backEnd.entity.StatusBox;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChampionDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = -1463870193808259649L;

	protected String name;
	protected String description;
	protected int logic;

	protected int maxHp;
	protected int maxMp;
	protected int minDmg;
	protected int maxDmg;
	protected int defense;
	protected int critical;
	protected int dodge;

	protected String skill1;
	protected String skill2;
	protected String skill3;
	protected String skill4;
	protected String skill5;

	protected String skill1Description;
	protected String skill2Description;
	protected String skill3Description;
	protected String skill4Description;
	protected String skill5Description;

	protected int skill1MpCost;
	protected int skill2MpCost;
	protected int skill3MpCost;
	protected int skill4MpCost;
	protected int skill5MpCost;

	protected int skill1CD;
	protected int skill2CD;
	protected int skill3CD;
	protected int skill4CD;
	protected int skill5CD;

	protected int currentHp;
	protected int currentMp;
	protected int currentDmgMin;
	protected int currentDmgMax;
	protected int currentDefense;
	protected int currentCritical;
	protected int currentDodge;

	protected int currentSkill1CD;
	protected int currentSkill2CD;
	protected int currentSkill3CD;
	protected int currentSkill4CD;
	protected int currentSkill5CD;

	protected PotionInventory potionInventory;

	protected StatusBox statusBox;

	// TO-DO evaluate the return type
	public int [ ] drinkPotion(boolean hp) {
		if (hp) {
			if (potionInventory.getHpPotions().isEmpty()) {
				return null;
			}
			HpPotion p = potionInventory.getHpPotions().get(0);
			this.currentHp = currentHp + p.getRecovery() >= maxHp ? maxHp
					: currentHp + p.getRecovery();
			potionInventory.getHpPotions().remove(0);
			return new int [ ] { p.getRecovery() , currentHp };
		} else {
			if (potionInventory.getMpPotions().isEmpty()) {
				return null;
			}
			MpPotion p = potionInventory.getMpPotions().get(0);
			this.currentMp = currentMp + p.getRecovery() >= maxMp ? maxMp
					: currentMp + p.getRecovery();
			potionInventory.getHpPotions().remove(0);
			return new int [ ] { p.getRecovery() , currentMp };
		}
	}

	public void attack(ChampionDto target) {
		int damage = calculateNextDamage();

		boolean dodged = target.isDodgedHit();
		boolean critical = isCriticalHit();

		int finalDamage;
		if (!dodged) {
			finalDamage = damage - target.getDefense();
			if (critical) {
				finalDamage *= 2;
			}
			if (finalDamage < 0) { // handles negative damages
				finalDamage = 0;
			}
			// TO-DO move somewhere else
//			target.setCurrentHp(target.getCurrentHp() - finalDamage);
//			mainFrame.getGrid().checkForDeads(mainFrame);
//			mainFrame.getAAS().shakeButton(targetButton);
//			mainFrame.getAAS().playSound("melee");
//			mainFrame.getMP().generateMove(mainFrame.getMessages(), mainFrame.getMove());
//			mainFrame.getMP().generateMessage(mainFrame.getMessages(), this, target,
//					new int [ ] { finalDamage }, new boolean [ ] { critical });
		} else {
//			mainFrame.getAAS().playSound("dodge");
//			mainFrame.getMP().generateMove(mainFrame.getMessages(), mainFrame.getMove());
//			mainFrame.getMP().generateMessage(mainFrame.getMessages(), this, target);
		}

	}

	public int calculateNextDamage() {
		return Constants.RANDOM.nextInt((maxDmg - minDmg) + 1) + minDmg;
	}

	public boolean isCriticalHit() {
		return Constants.RANDOM.nextInt(101) < critical;
	}

	public boolean isDodgedHit() {
		return Constants.RANDOM.nextInt(101) < dodge;
	}

	public String evalColor() {
		if ((double) currentHp / maxHp <= .25) {
			return "red";
		} else if ((double) currentHp / maxHp <= .50) {
			return "yellow";
		} else {
			return "white";
		}
	}

	public boolean isDead() {
		if (currentHp < 0) {
			currentHp = 0;
		}
		return currentHp == 0;
	}

	public double evalFraction(int i) {
		double frac = 0.0;
		switch (i) {
		case 0:
			frac = (double) currentSkill1CD / skill1CD;
			break;
		case 1:
			frac = (double) currentSkill2CD / skill2CD;
			break;
		case 2:
			frac = (double) currentSkill3CD / skill3CD;
			break;
		case 3:
			frac = (double) currentSkill4CD / skill4CD;
			break;
		case 4:
			frac = (double) currentSkill5CD / skill5CD;
			break;
		}
		return frac;
	}
}
