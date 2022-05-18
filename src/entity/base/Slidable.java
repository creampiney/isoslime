package entity.base;

public interface Slidable {
	
	public boolean slide(String key);
	
	public void setPlayerSlip(String key);
	
	public boolean checkMoveValid(String key, boolean requireCondition);

	public void movingAnimation(int newPosRow, int newPosCol, String key);
	
	public void moveEntity(int newPosRow, int newPosCol, String key);

	public boolean isAllowSlide();
}
