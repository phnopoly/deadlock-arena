package com.deadlockArena;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.deadlockArena.backEnd.dto.ChampionDto;
import com.deadlockArena.frontEnd.graphics.AnimationAndSound;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

@Component
public class Constants {

	public static final AnimationAndSound AAS = new AnimationAndSound();

	public static final String[][] CHAMPIONS = { { "Berserker", "Assassin", "Bard" },
			{ "Dancer", "Chemist", "Guardian" }, { "Engineer", "Cyborg", "Monk" },
			{ "HolyKnight", "Dragoon", "Pyromancer" }, { "Knight", "Reaper", "Sniper" },
			{ "Screamer", "Trickster", "SwordMage" } };

	public static final int PIXEL = 96;
	public static final double SCALE_FACTOR = 2 / 3.0;
	public static final double BUTTON_SIZE_SCALE = 4 / 5.0;
	public static final double BASE = Constants.PIXEL * Constants.SCALE_FACTOR * Constants.BUTTON_SIZE_SCALE;
	public static final double[] sC1 = { 630, 120 };
	public static final double[] sC2 = { 630, 445 };
	public static final double[] bC = { 125, 125 };
	public static final double[] BerserkerCoord = { Constants.bC[0] + Constants.BASE * 0, Constants.bC[1] + Constants.BASE * 0 };
	public static final double[] DancerCoord = { Constants.bC[0] + Constants.BASE * 0, Constants.bC[1] + Constants.BASE * 1 };
	public static final double[] EngineerCoord = { Constants.bC[0] + Constants.BASE * 0, Constants.bC[1] + Constants.BASE * 2 };
	public static final double[] HolyKnightCoord = { Constants.bC[0] + Constants.BASE * 0, Constants.bC[1] + Constants.BASE * 3 };
	public static final double[] KnightCoord = { Constants.bC[0] + Constants.BASE * 0, Constants.bC[1] + Constants.BASE * 4 };
	public static final double[] ScreamerCoord = { Constants.bC[0] + Constants.BASE * 0, Constants.bC[1] + Constants.BASE * 5 };
	public static final double[] AssassinCoord = { Constants.bC[0] + Constants.BASE * 1, Constants.bC[1] + Constants.BASE * 0 };
	public static final double[] ChemistCoord = { Constants.bC[0] + Constants.BASE * 1, Constants.bC[1] + Constants.BASE * 1 };
	public static final double[] CyborgCoord = { Constants.bC[0] + Constants.BASE * 1, Constants.bC[1] + Constants.BASE * 2 };
	public static final double[] DragoonCoord = { Constants.bC[0] + Constants.BASE * 1, Constants.bC[1] + Constants.BASE * 3 };
	public static final double[] ReaperCoord = { Constants.bC[0] + Constants.BASE * 1, Constants.bC[1] + Constants.BASE * 4 };
	public static final double[] TricksterCoord = { Constants.bC[0] + Constants.BASE * 1, Constants.bC[1] + Constants.BASE * 5 };
	public static final double[] BardCoord = { Constants.bC[0] + Constants.BASE * 2, Constants.bC[1] + Constants.BASE * 0 };
	public static final double[] GuardianCoord = { Constants.bC[0] + Constants.BASE * 2, Constants.bC[1] + Constants.BASE * 1 };
	public static final double[] MonkCoord = { Constants.bC[0] + Constants.BASE * 2, Constants.bC[1] + Constants.BASE * 2 };
	public static final double[] PyromancerCoord = { Constants.bC[0] + Constants.BASE * 2, Constants.bC[1] + Constants.BASE * 3 };
	public static final double[] SniperCoord = { Constants.bC[0] + Constants.BASE * 2, Constants.bC[1] + Constants.BASE * 4 };
	public static final double[] SwordMageCoord = { Constants.bC[0] + Constants.BASE * 2, Constants.bC[1] + Constants.BASE * 5 };

	public static final double[][] coords = { Constants.BerserkerCoord, { Constants.sC1[0] + Constants.BASE * 0, Constants.sC1[1] + Constants.BASE * 0 }, Constants.CyborgCoord,
			{ Constants.sC1[0] + Constants.BASE * 0, Constants.sC1[1] + Constants.BASE * 1 }, Constants.AssassinCoord, { Constants.sC1[0] + Constants.BASE * 0, Constants.sC1[1] + Constants.BASE * 2 },
			Constants.TricksterCoord, { Constants.sC1[0] + Constants.BASE * 1, Constants.sC1[1] + Constants.BASE * 1 }, Constants.MonkCoord,
			{ Constants.sC1[0] + Constants.BASE * 1, Constants.sC1[1] + Constants.BASE * 2 }, Constants.BardCoord, { Constants.sC1[0] + Constants.BASE * 1, Constants.sC1[1] + Constants.BASE * 3 },
			Constants.SwordMageCoord, { Constants.sC1[0] + Constants.BASE * 2, Constants.sC1[1] + Constants.BASE * 0 }, Constants.ChemistCoord,
			{ Constants.sC1[0] + Constants.BASE * 2, Constants.sC1[1] + Constants.BASE * 1 }, Constants.SniperCoord, { Constants.sC1[0] + Constants.BASE * 2, Constants.sC1[1] + Constants.BASE * 2 },
			Constants.KnightCoord, { Constants.sC2[0] + Constants.BASE * 3, Constants.sC2[1] + Constants.BASE * 3 }, Constants.ScreamerCoord,
			{ Constants.sC2[0] + Constants.BASE * 3, Constants.sC2[1] + Constants.BASE * 1 }, Constants.PyromancerCoord, { Constants.sC2[0] + Constants.BASE * 0, Constants.sC2[1] + Constants.BASE * 0 },
			Constants.DancerCoord, { Constants.sC2[0] + Constants.BASE * 1, Constants.sC2[1] + Constants.BASE * 2 }, Constants.EngineerCoord,
			{ Constants.sC2[0] + Constants.BASE * 3, Constants.sC2[1] + Constants.BASE * 2 }, Constants.DragoonCoord, { Constants.sC2[0] + Constants.BASE * 2, Constants.sC2[1] + Constants.BASE * 3 },
			Constants.GuardianCoord, { Constants.sC2[0] + Constants.BASE * 0, Constants.sC2[1] + Constants.BASE * 1 }, Constants.HolyKnightCoord,
			{ Constants.sC2[0] + Constants.BASE * 1, Constants.sC2[1] + Constants.BASE * 0 }, Constants.ReaperCoord, { Constants.sC2[0] + Constants.BASE * 2, Constants.sC2[1] + Constants.BASE * 0 }, };

	public static final int CAP_TURN = 4;

	public static final int SELECT_ROW_COUNT = 6;
	public static final int SELECT_COL_COUNT = 3;
	public static final int SELECT_COUNT = Constants.SELECT_ROW_COUNT * Constants.SELECT_COL_COUNT;

	public static final int SLOT_ROW_COUNT = 2;
	public static final int SLOT_COL_COUNT = 3;
	public static final int SLOT_COUNT = Constants.SLOT_ROW_COUNT * Constants.SLOT_COL_COUNT;

	public static final Random RANDOM = new Random();

	// public static final LineBorder MOVE_BORDER = new LineBorder(Color.blue, 5);
	// public static final LineBorder ATTACK_BORDER = new LineBorder(Color.red, 5);
	//	public static final Border DEFAULT_BORDER = UIManager.getBorder("Button.border");

	public static final Border FANCY_BORDER = new Border(
			new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	public static final Font LARGE_FONT = Font.font("Verdana", 40);

	public static final Paint DEFAULT_BACKGROUND = Color.BLACK;
	public static final String DEFAULT_COLOR = "-fx-background-color: black;";
	// public static final Color DEFAULT_BUTTON_BACKGROUND = new
	// JButton().getBackground();

	public static final Font BASIC_FONT = Font.font("Verdana", 20);
	//	public static final Font CHAMPION_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 40);
	//	public static final Font SELECT_BUTTON_CHAMPION_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	//	public static final Font PANEL_EAST_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 18);

	public static final String PICS_PATH = "pics/";

	public static final String STATUS_STRING = "<html>" + "HP: ? / ?<br/>" + "MP: ? / ?<br/>" + "Damage: ? - ?<br/>"
			+ "Defense: ?<br/>" + "Critical: ?<br/>" + "Dodge : ?" + "</html>";

	//	public static final String DEFAULT_STATUS_STRING = "<html>" + "HP: ?<br/>" + "MP: ?<br/>"
	//			+ "Damage: ? - ?<br/>" + "Defense: ?<br/>" + "Critical: ?<br/>" + "Dodge : ?"
	//			+ "</html>";

	public static String getStatsText(final ChampionDto championDto) {
		return "<html>" + "<font color=" + championDto.evalColor() + ">HP: " + championDto.getCurrentHp() + " / "
				+ championDto.getMaxHp() + "</font><br/>" + "MP: " + championDto.getCurrentMp() + " / "
				+ championDto.getMaxMp() + "<br/>" + "Damage: " + championDto.getMinDmg() + " - "
				+ championDto.getMaxDmg() + "<br/>" + "Defense: " + championDto.getDefense() + "<br/>" + "Critical: "
				+ championDto.getCritical() + "%<br/>" + "Dodge: " + championDto.getDodge() + "%" + "</html>";
	}

}
