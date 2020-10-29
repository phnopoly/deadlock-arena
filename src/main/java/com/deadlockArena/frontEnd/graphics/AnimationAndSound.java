package com.deadlockArena.frontEnd.graphics;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JButton;

import com.deadlockArena.frontEnd.Constants;
import com.deadlockArena.frontEnd.exception.CornerCaseException;

import javafx.scene.control.Button;
import lombok.Data;

@Data
public class AnimationAndSound {

	private AudioInputStream inputStreamSound;
	private AudioInputStream inputStreamMusic;
	private Clip music;
	private String[] soundtracks;
	private boolean changedMusic;
	private final int DELAY = 20;

	private BooleanButton soundButton, musicButton, loopButton;
	private Button soundtrackButton;
	private String soundtrack;

	public AnimationAndSound() {
		this.changedMusic = false;
		// initSoundTracks();

		// this.soundtrackButton = new Button("Soundtracks");
		//		soundtrackButton.setFont(Constants.BASIC_FONT);
		//		soundtrackButton.setEnabled(false);
		//		soundtrackButton.addMouseListener(new MouseAdapter() {
		//			public void mousePressed(MouseEvent e) {
		//				if (soundtrackButton.isEnabled()) {
		//					String selected = (String) JOptionPane.showInputDialog(null, "Please choose a song", "Soundtracks",
		//							JOptionPane.QUESTION_MESSAGE, null, soundtracks, soundtrack);
		//					if (selected != null) {
		//						soundtrack = selected;
		//						if (loopButton.isOn()) {
		//							changedMusic = true;
		//							stopMusic();
		//							loopMusic();
		//						} else {
		//							stopMusic();
		//							initMusic();
		//							startMusic();
		//						}
		//					} else
		//						return;
		//				}
		//			}
		//		});
		try {
			this.soundButton = new BooleanButton("pics/sound");
			this.musicButton = new BooleanButton("pics/music");
			this.loopButton = new BooleanButton("pics/loop");
		} catch (final CornerCaseException exc) {
			exc.printStackTrace();
		}
		this.initMusic();
	}

	// TO-DO use sshConnect to access the files
	//	private void initSoundTracks() {
	//		File folder = new File("music/");
	//		File [ ] listOfFiles = folder.listFiles();
	//
	//		soundtracks = new String [ listOfFiles.length ];
	//		for (int i = 0; i < listOfFiles.length; i++) {
	//			soundtracks [ i ] = listOfFiles [ i ].getName().replace(".wav", "");
	//		}
	//		soundtrack = soundtracks [ JavaData.random.nextInt(soundtracks.length) ];
	//	}

	public void shakeButton(final JButton button) {
		//		final Point point = button.getLocation();
		//		final Runnable r = new Runnable() {
		//			@Override
		//			public void run() {
		//				for (int i = 0; i < 5; i++) {
		//					try {
		//						button.setLocation(new Point(point.x - 10, point.y));
		//						Thread.sleep(AnimationAndSound.this.DELAY);
		//						button.setLocation(point);
		//						Thread.sleep(AnimationAndSound.this.DELAY);
		//						button.setLocation(new Point(point.x + 10, point.y));
		//						Thread.sleep(AnimationAndSound.this.DELAY);
		//						button.setLocation(point);
		//						Thread.sleep(AnimationAndSound.this.DELAY);
		//					} catch (final InterruptedException exc) {
		//						exc.printStackTrace();
		//					}
		//				}
		//			}
		//		};
		//		final Thread t = new Thread(r);
		//		t.start();
	}

	public void playSound(final String soundType) {
		if (this.soundButton.isOn()) {
			final String sound = this.selectSount(soundType);
			try {
				this.inputStreamSound = AudioSystem.getAudioInputStream(new File("sound/" + sound + ".wav"));
				final Clip clip = AudioSystem.getClip();
				clip.open(this.inputStreamSound);
				clip.loop(0);
			} catch (final Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	public void loopMusic() {
		final Runnable r = new Runnable() {
			@Override
			public void run() {
				while (true) {
					AnimationAndSound.this.shufflePlaylist();
					final byte[] buffer = new byte[4096];
					for (final String fileString : AnimationAndSound.this.soundtracks) {
						try {
							AnimationAndSound.this.inputStreamMusic = AudioSystem
									.getAudioInputStream(new File("music/" + fileString + ".wav"));
							final AudioFormat format = AnimationAndSound.this.inputStreamMusic.getFormat();
							final SourceDataLine line = AudioSystem.getSourceDataLine(format);
							line.open(format);
							line.start();
							while (AnimationAndSound.this.inputStreamMusic.available() > 0) {
								if (!AnimationAndSound.this.loopButton.isOn() || !AnimationAndSound.this.musicButton.isOn() || AnimationAndSound.this.changedMusic) {
									return;
								}
								final int len = AnimationAndSound.this.inputStreamMusic.read(buffer);
								line.write(buffer, 0, len);
							}
							line.drain();
							line.close();
						} catch (final Exception exc) {
							exc.printStackTrace();
						}
					}
				}
			}
		};
		if (!this.loopButton.isOn() || !this.musicButton.isOn() || this.changedMusic) {
			this.changedMusic = false;
			return;
		}
		final Thread loopingMusic = new Thread(r);
		loopingMusic.start();

	}

	private void initMusic() {
		if (this.soundtrack == null) {
			return;
		}
		try {
			this.inputStreamMusic = AudioSystem.getAudioInputStream(new File("music/" + this.soundtrack + ".wav"));
		} catch (final Exception exc) {
			exc.printStackTrace();
		}
	}

	public void startMusic() {
		if (this.musicButton.isOn()) {
			try {
				this.music = AudioSystem.getClip();
				this.initMusic();
				this.music.open(this.inputStreamMusic);
				this.music.loop(Clip.LOOP_CONTINUOUSLY);
				this.controlVolume(this.music, -15);
			} catch (final Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	public void stopMusic() {
		if (this.music != null) {
			this.music.close();
		}
	}

	private void controlVolume(final Clip c, final float f) {
		final FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(f);
		// Reduce volume by f decibels.
	}

	private String selectSount(final String soundType) {
		String sound = "";
		switch (soundType) {
		case "melee":
			switch (Constants.RANDOM.nextInt(2)) {
			case 0:
				sound = "melee1";
				break;
			case 1:
				sound = "melee2";
				break;
			}
			break;
		case "dodge":
			sound = "dodge";
			break;
		case "select":
			sound = "80921__justinbw__buttonchime02up";
			break;
		}
		return sound;
	}

	private void shufflePlaylist() {
		final int n = this.soundtracks.length;
		Constants.RANDOM.nextInt();
		for (int i = 0; i < n; i++) {
			final int change = i + Constants.RANDOM.nextInt(n - i);
			final String placeHolder = this.soundtracks[i];
			this.soundtracks[i] = this.soundtracks[change];
			this.soundtracks[change] = placeHolder;
		}
	}
}
