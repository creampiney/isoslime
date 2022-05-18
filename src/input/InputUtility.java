package input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {

	public static double mouseX,mouseY;
	public static boolean mouseOnScreen = true;
	private static boolean isLeftDown = false;
	private static boolean isLeftClickedLastTick = false;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>(); 
	//private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();
	private static KeyCode keyTriggered;
	//private static ArrayList<KeyCode> keyTriggerFlag = new ArrayList<>();
	
	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}
	
	public static boolean getKeyTriggered(KeyCode keycode) {
		//return keyTriggered.contains(keycode);
		return keycode.equals(keyTriggered);
	}
	
	public static void clearKeyTriggered() {
		keyTriggered = null;
		//keyTriggered.clear();
	}
	
	public static void setKeyPressed(KeyCode keycode,boolean pressed) {
		if(pressed){
			if(!keyPressed.contains(keycode)){
				keyPressed.add(keycode);
				keyTriggered = keycode;
			}
		}else{
			keyPressed.remove(keycode);
			//keyTriggered.remove(keycode);
			keyTriggered = null;
		}
		//System.out.println("Pressed " + keyPressed);
		System.out.println("Triggered " + keyTriggered);
	}
	
	public static void mouseLeftDown(){
		isLeftDown = true;
		isLeftClickedLastTick = true;
	}
	
	public static void mouseLeftRelease(){
		isLeftDown = false;
	}
	
	public static boolean isLeftClickTriggered(){
		return isLeftClickedLastTick;
	}
	
	public static void updateInputState(){
		clearKeyTriggered();
		isLeftClickedLastTick = false;
	}
	
}
