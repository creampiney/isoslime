package logic;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import entity.base.Actable;
import entity.base.Entity;
import tile.base.Tile;
import tile.base.Walkable;

public class Map {
	
	private int startRow;
	private int startCol;
	
	private Tile[][] tiles;
	private Entity[][] floorEntities;
	private Entity[][] entities;

	
	private int bgCode;
	
	private ArrayList<Actable> actableEntity;
	
	public Map(String filename) {
		tiles = new Tile[10][10];
		floorEntities = new Entity[10][10];
		entities = new Entity[10][10];
		actableEntity = new ArrayList<Actable>();
		createMap(filename);
	}

	
	public void createMap(String filename) {
		BufferedReader s = null;
		try {
			InputStream is = ClassLoader.getSystemResourceAsStream("data/map/"+filename+".txt");
			s = new BufferedReader(new InputStreamReader(is));
			
			String[] startPos = s.readLine().split(",");
			startRow = Integer.parseInt(startPos[0]);
			startCol = Integer.parseInt(startPos[1]);
			
			int code;
			// Tiles
			for(int i = 0; i < 10; i++) {
				String line = s.readLine();
				String[] lineElement = line.split(",");
				for(int j = 0; j < 10; j++) {
					code = Integer.parseInt(lineElement[j]);
					Tile tile = ObjectGenerator.buildTile(code);
					this.tiles[i][j] = tile;
				}
				
			}
			
			// Bg
			bgCode = Integer.parseInt(s.readLine());
			
			// Floor Entities
			for(int i = 0; i < 10; i++) {
				String line = s.readLine();
				String[] lineElement = line.split(",");
				for(int j = 0; j < 10; j++) {
					code = Integer.parseInt(lineElement[j]);
					Entity entity = ObjectGenerator.buildEntity(code, i, j);
					this.floorEntities[i][j] = entity;
					if (entity instanceof Actable) {
						actableEntity.add((Actable) entity);
					}
				}
				
			}
			
			code = Integer.parseInt(s.readLine());
			// Entities
			for(int i = 0; i < 10; i++) {
				String line = s.readLine();
				String[] lineElement = line.split(",");
				for(int j = 0; j < 10; j++) {
					code = Integer.parseInt(lineElement[j]);
					Entity entity = ObjectGenerator.buildEntity(code, i, j);
					this.entities[i][j] = entity;
				}
				
			}
			
			String line = s.readLine();
			String[] lineElement = line.split(",");
			GameLogic.setMagicWandAmount(Integer.parseInt(lineElement[0]));
			GameLogic.setAxeAmount(Integer.parseInt(lineElement[1]));
			GameLogic.setWoodAmount(Integer.parseInt(lineElement[2]));
			
			s.close();
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Cannot find file!");
		}
		catch (Exception e) {
			System.out.println("File Error!");
			System.out.println(e);
		}
	}
	
	public int getStartRow() {
		return startRow;
	}
	
	public int getStartCol() {
		return startCol;
	}
	
	public boolean isMoveValid(int posRow, int posCol) {
		return (tiles[posRow][posCol] instanceof Walkable);
	}
	
	public boolean isTilePlaced(int posRow, int posCol) {
		return tiles[posRow][posCol] != null;
	}
	
	public boolean isEntityPlaced(int posRow, int posCol) {
		return entities[posRow][posCol] != null;
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	public Entity[][] getFloorEntities() {
		return floorEntities;
	}

	public Entity[][] getEntities() {
		return entities;
	}
	
	public ArrayList<Actable> getActableEntity() {
		return actableEntity;
	}
	
	public void setTile(int posRow, int posCol, Tile tile) {
		tiles[posRow][posCol] = tile;
	}
	
	public void setFloorEntity(int posRow, int posCol, Entity entity) {
		floorEntities[posRow][posCol] = entity;
	}
	
	public void setEntity(int posRow, int posCol, Entity entity) {
		entities[posRow][posCol] = entity;
	}
	

	public int getBgCode() {
		return bgCode;
	}

	
}
