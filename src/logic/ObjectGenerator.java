package logic;

import entity.base.Entity;
import entity.floor.BlueButton;
import entity.floor.GreenButton;
import entity.floor.LevelGate;
import entity.floor.OrangeButton;
import entity.floor.PurpleButton;
import entity.floor.RedButton;
import entity.item.Axe;
import entity.item.MagicWand;
import entity.item.Wood;
import entity.solid.Cactus;
import entity.solid.CoconutTree;
import entity.solid.SlimeFriend;
import entity.solid.SnowyTree;
import entity.solid.Tree;
import entity.solid.WinterTree;
import entity.solid.WoodenCrate;
import tile.CrateOnDeepStone;
import tile.CrateOnDirt;
import tile.CrateOnSand;
import tile.CrateOnStone;
import tile.DeepStone;
import tile.GradientStone;
import tile.Grass;
import tile.Ice;
import tile.Sand;
import tile.Snow;
import tile.Stone;
import tile.VolcanoDirt;
import tile.WaterOnDeepStone;
import tile.WaterOnDirt;
import tile.WaterOnSand;
import tile.WaterOnStone;
import tile.base.Tile;
import tile.platform.BlueEmptyPlatform;
import tile.platform.BluePlatform;
import tile.platform.GreenEmptyPlatform;
import tile.platform.GreenPlatform;
import tile.platform.OrangeEmptyPlatform;
import tile.platform.OrangePlatform;
import tile.platform.PurpleEmptyPlatform;
import tile.platform.PurplePlatform;
import tile.platform.RedEmptyPlatform;
import tile.platform.RedPlatform;

public class ObjectGenerator {
	
	
	public static Tile buildTile(int code) {
		Tile tile = null;
		
		switch (code) {
		case 0:			// Empty
			tile = null;
			break;
		
		case 1:			// Grass
			tile = new Grass();
			break;
			
		case 2:			// Ice
			tile = new Ice();
			break;
		
		case 3:			// Water on Dirt
			tile = new WaterOnDirt();
			break;
			
		case 4:			// Crate on Dirt
			tile = new CrateOnDirt();
			break;
			
		case 5:			// Red Platform
			tile = new RedPlatform();
			break;
			
		case 6:			// Red Empty Platform
			tile = new RedEmptyPlatform();
			break;
			
		case 7:			// Orange Platform
			tile = new OrangePlatform();
			break;
			
		case 8:			// Orange Empty Platform
			tile = new OrangeEmptyPlatform();
			break;
			
		case 9:			// Green Platform
			tile = new GreenPlatform();
			break;
			
		case 10:		// Green Empty Platform
			tile = new GreenEmptyPlatform();
			break;
			
		case 11:		// Blue Platform
			tile = new BluePlatform();
			break;
			
		case 12:		// Blue Empty Platform
			tile = new BlueEmptyPlatform();
			break;
			
		case 13:		// Purple Platform
			tile = new PurplePlatform();
			break;
			
		case 14:		// Purple Empty Platform
			tile = new PurpleEmptyPlatform();
			break;
			
		case 15:		// Sand
			tile = new Sand();
			break;
			
		case 16:		// Water on Sand
			tile = new WaterOnSand();
			break;
			
		case 17:		// Crate on Sand
			tile = new CrateOnSand();
			break;
			
		case 18:		// Stone
			tile = new Stone();
			break;
			
		case 19:		// Gradient Stone
			tile = new GradientStone();
			break;
			
		case 20:		// Deep Stone
			tile = new DeepStone();
			break;
		
		case 21:		// Snow
			tile = new Snow();
			break;
			
		case 22:		// Volcano Dirt
			tile = new VolcanoDirt();
			break;
			
		case 23:		// Water on Stone
			tile = new WaterOnStone();
			break;
			
		case 24:		// Crate on Stone
			tile = new CrateOnStone();
			break;
			
		case 25:		// Water on Deep Stone
			tile = new WaterOnDeepStone();
			break;
			
		case 26:		// Crate on Deep Stone
			tile = new CrateOnDeepStone();
			break;
		}
		
		return tile;
	}
	
	
	
	public static Entity buildEntity(int code, int posRow, int posCol) {
		Entity entity = null;
		
		switch (code) {
		case 0:			// Empty
			entity = null;
			break;
		
		case 1:			// Level Gate
			entity = new LevelGate(posRow, posCol);
			break;
			
		case 2:			// Wooden Crate
			entity = new WoodenCrate(posRow, posCol);
			break;
			
		case 3:			// Snowy Tree
			entity = new SnowyTree(posRow, posCol);
			break;
			
		case 4:			// Red Button
			entity = new RedButton(posRow, posCol);
			break;
			
		case 5:			// Orange Button
			entity = new OrangeButton(posRow, posCol);
			break;
			
		case 6:			// Green Button
			entity = new GreenButton(posRow, posCol);
			break;
			
		case 7:			// Blue Button
			entity = new BlueButton(posRow, posCol);
			break;
			
		case 8:			// Purple Button
			entity = new PurpleButton(posRow, posCol);
			break;
			
		case 9:			// Tree
			entity = new Tree(posRow, posCol);
			break;
			
		case 10:			// Coconut Tree
			entity = new CoconutTree(posRow, posCol);
			break;
			
		case 11:			// Cactus
			entity = new Cactus(posRow, posCol);
			break;
			
		case 12:			// Winter Tree
			entity = new WinterTree(posRow, posCol);
			break;
			
		case 13:			// (Item) Magic Wand
			entity = new MagicWand(posRow, posCol);
			break;
			
		case 14:			// (Item) Axe
			entity = new Axe(posRow, posCol);
			break;
			
		case 15:			// (Item) Wood
			entity = new Wood(posRow, posCol);
			break;
			
		case 16:			// (Decoration) Slime Friend
			entity = new SlimeFriend(posRow, posCol);
			break;
			
		}
		
	
		return entity;
	}
	
	public static String buildStoryText(int level) {
		switch(level) {
		case 1:
			return "Once upon a time, a slime was finding his friend.";
		case 2:
			return "But he lost in the forest, so he was trying to find exit.";
		case 3:
			return "Me : This pond is so beautiful.";
		case 4:
			return "This forest was filled with many crates.";
		case 5:
			return "Me : Why are there many crates?";
		case 6:
			return "Me : What will happen if I push these crates in the pond?";
		case 7:
			return "He continually pushed crates and made crate bridge.";
		case 8:
			return "He found u-shape river and he must cross it.";
		case 9:
			return "Me : What are those? Axe?";
		case 10:
			return "He cut trees and made bridge with his woods.";
		case 11:
			return "Me : Oh, a large cliff!!!";
		case 12:
			return "*Mumbling to himself while travelling*";
		case 13:
			return "After a long travelling, he found a beach.";
		case 14:
			return "Look like there was no one in the beach.";
		case 15:
			return "By the way, this beach is small.";
		case 16:
			return "Me : I need to go to another side of beach, but how can I go?";
		case 17:
			return "Me : Who left these wands? Is it magic wands?";
		case 18:
			return "Suddenly, he walked into desert unconsciously";
		case 19:
			return "Me : Why is it very hot?";
		case 20:
			return "Because the sand subside, the desert is full of holes and cliffs.";
		case 21:
			return "He ran very fast because of hot weather.";
		case 22:
			return "Why he was running, he found a large cave and tried to discover inside.";
		case 23:
			return "Me : This cave is very deep!";
		case 24:
			return "The puzzles were waiting for him.";
		case 25:
			return "Me : These puzzles were too easy for me.";
		case 26:
			return "He still didn't know where he must go.";
		case 27:
			return "But his sense told that he was going in the right way.";
		case 28:
			return "Me : I really hate these crates. Who left them in this narrow cave?";
		case 29:
			return "While he was observing the cave, he found a light at exit.";
		case 30:
			return "Me : Time to end the game. Hahaha";
		case 31:
			return "He found the exit. He ran out very quickly.";
		case 32:
			return "Me : Oh, the floor is very slippery.";
		case 33:
			return "Because of cold weather, some water turn into ice.";
		case 34:
			return "This river started turning into ice.";
		case 35:
			return "Me : I should push that crate to that sensor.";
		case 36:
			return "Suddenly, the floor subside and remain a few land.";
		case 37:
			return "Look like he found some ice skating rink.";
		case 38:
			return "Let's play some ice skate!";
		case 39:
			return "Me : Help me! I can't stop!";
		case 40:
			return "The snow started falling!";
		case 41:
			return "While he was travelling, he noticed something.";
		case 42:
			return "He heard his friend slightly.";
		case 43:
			return "Me : Is that my friend?";
		case 44:
			return "Me : Where are you?";
		case 45:
			return "He didn't receive any response.";
		case 46:
			return "Suddenly, he saw something like his friend.";
		case 47:
			return "He ran toward his friend rapidly.";
		case 48:
			return "*Still running*";
		case 49:
			return "Me : Is that my friend?";
		case 50:
			return "His friend : Hey, I'm here!";
		default:
			return "";
		}
	}
}
