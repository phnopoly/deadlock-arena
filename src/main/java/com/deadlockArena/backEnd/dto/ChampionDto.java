package com.deadlockArena.backEnd.dto;

import java.io.Serializable;

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
	public int [ ] drinkPotion(final boolean hp) {
		if (hp) {
			if (this.potionInventory.getHpPotions().isEmpty()) {
				return null;
			}
			final HpPotion p = this.potionInventory.getHpPotions().get(0);
			this.currentHp = this.currentHp + p.getRecovery() >= this.maxHp ? this.maxHp
					: this.currentHp + p.getRecovery();
			this.potionInventory.getHpPotions().remove(0);
			return new int [ ] { p.getRecovery() , this.currentHp };
		} else {
			if (this.potionInventory.getMpPotions().isEmpty()) {
				return null;
			}
			final MpPotion p = this.potionInventory.getMpPotions().get(0);
			this.currentMp = this.currentMp + p.getRecovery() >= this.maxMp ? this.maxMp
					: this.currentMp + p.getRecovery();
			this.potionInventory.getHpPotions().remove(0);
			return new int [ ] { p.getRecovery() , this.currentMp };
		}
	}

	public void attack(final ChampionDto target) {
		//		final int damage = calculateNextDamage();
		//
		//		final boolean dodged = target.isDodgedHit();
		//		final boolean critical = isCriticalHit();
		//
		//		int finalDamage;
		//		if (!dodged) {
		//			finalDamage = damage - target.getDefense();
		//			if (critical) {
		//				finalDamage *= 2;
		//			}
		//			if (finalDamage < 0) { // handles negative damages
		//				finalDamage = 0;
		//			}
		//			// TO-DO move somewhere else
		//			//			target.setCurrentHp(target.getCurrentHp() - finalDamage);
		//			//			mainFrame.getGrid().checkForDeads(mainFrame);
		//			//			mainFrame.getAAS().shakeButton(targetButton);
		//			//			mainFrame.getAAS().playSound("melee");
		//			//			mainFrame.getMP().generateMove(mainFrame.getMessages(), mainFrame.getMove());
		//			//			mainFrame.getMP().generateMessage(mainFrame.getMessages(), this, target,
		//			//					new int [ ] { finalDamage }, new boolean [ ] { critical });
		//		} else {
		//			//			mainFrame.getAAS().playSound("dodge");
		//			//			mainFrame.getMP().generateMove(mainFrame.getMessages(), mainFrame.getMove());
		//			//			mainFrame.getMP().generateMessage(mainFrame.getMessages(), this, target);
		//		}

	}

	//	public int calculateNextDamage() {
	//		return Constants.RANDOM.nextInt((maxDmg - minDmg) + 1) + minDmg;
	//	}
	//
	//	public boolean isCriticalHit() {
	//		return Constants.RANDOM.nextInt(101) < critical;
	//	}
	//
	//	public boolean isDodgedHit() {
	//		return Constants.RANDOM.nextInt(101) < dodge;
	//	}

	public String evalColor() {
		if ((double) this.currentHp / this.maxHp <= .25) {
			return "red";
		} else if ((double) this.currentHp / this.maxHp <= .50) {
			return "yellow";
		} else {
			return "white";
		}
	}

	public boolean isDead() {
		if (this.currentHp < 0) {
			this.currentHp = 0;
		}
		return this.currentHp == 0;
	}

	public double evalFraction(final int i) {
		double frac = 0.0;
		switch (i) {
		case 0:
			frac = (double) this.currentSkill1CD / this.skill1CD;
			break;
		case 1:
			frac = (double) this.currentSkill2CD / this.skill2CD;
			break;
		case 2:
			frac = (double) this.currentSkill3CD / this.skill3CD;
			break;
		case 3:
			frac = (double) this.currentSkill4CD / this.skill4CD;
			break;
		case 4:
			frac = (double) this.currentSkill5CD / this.skill5CD;
			break;
		}
		return frac;
	}
}
