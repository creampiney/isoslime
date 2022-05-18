package logic;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {
	
	private static AudioClip audioRunner;
	
	public static AudioClip movingAudio = new AudioClip(ClassLoader.getSystemResource("audio/move.wav").toString());
	public static AudioClip fallingAudio = new AudioClip(ClassLoader.getSystemResource("audio/falling.wav").toString());
	public static AudioClip magicWandAudio = new AudioClip(ClassLoader.getSystemResource("audio/magicwand.wav").toString());
	public static AudioClip choppingAudio = new AudioClip(ClassLoader.getSystemResource("audio/chopping.wav").toString());
	public static AudioClip woodAudio = new AudioClip(ClassLoader.getSystemResource("audio/placewood.wav").toString());
	public static AudioClip clickAudio = new AudioClip(ClassLoader.getSystemResource("audio/clickbutton.wav").toString());
	public static AudioClip levelCompleteAudio = new AudioClip(ClassLoader.getSystemResource("audio/levelcomplete.wav").toString());
	public static AudioClip gameOverAudio = new AudioClip(ClassLoader.getSystemResource("audio/gameover.wav").toString());


	public static void resetAudioRunner() {
		if (audioRunner != null) {
			audioRunner.stop();
		}
	}
	
	
	public static void runMainMenuAudio() {
		resetAudioRunner();
		audioRunner = new AudioClip(ClassLoader.getSystemResource("audio/enchanted.wav").toString());
		audioRunner.setCycleCount(MediaPlayer.INDEFINITE);
		audioRunner.play();
	}
	
	public static void runGameAudio() {
		resetAudioRunner();
		audioRunner = new AudioClip(ClassLoader.getSystemResource("audio/thebardstale.wav").toString());
		audioRunner.setCycleCount(MediaPlayer.INDEFINITE);
		audioRunner.play();
	}

	
	
	







}
