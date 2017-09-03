package me.laurence.dungeonCrawler.handlers;

import me.laurence.dungeonCrawler.DungeonCrawler;
import me.laurence.dungeonCrawler.GameData;
import me.laurence.dungeonCrawler.entities.Entity;
import me.laurence.dungeonCrawler.general.Floor;
import me.laurence.dungeonCrawler.inventory.Inventory;
import me.laurence.dungeonCrawler.items.Item;
import me.laurence.dungeonCrawler.items.equippables.ItemEquippable;

public class PrintHandler {
	
	public static void print(Object s){
		System.out.print(s);
	}
	
	public static void println(Object s){
		System.out.println(s);
	}
	
	public static void printFloor(Floor f){
		char[][] s = new char[f.dimensions.y][f.dimensions.x];
		
		for(int i = 0; i < f.dimensions.y; i++) for(int j = 0; j < f.dimensions.x; j++) s[i][j] = '.';
		
		for(Entity e : f.entities){
			s[e.getPosition().y][e.getPosition().x] = e.getCharCode();
		}
		
		println("\n");
		for(char[] c : s){
			for(char c2 : c) print(c2);
			println("");
		}
	}

	public static void printHelp() {
		for(String s : InputHandler.commandDesc.keySet()){
			println(s + "\t:\t" + InputHandler.commandDesc.get(s));
		}
	}
	
	public static void printStats(){
		println("Difficulty\t:\t" + GameData.Dungeon.difficulty);
		println("Current Floor\t:\t" + GameData.Dungeon.floor);
		println("Lowest Floor\t:\t" + GameData.Dungeon.lowestFloor);
	}

	public static void printEntityInfo(Entity e) {
		println(e.getCharCode() + ":" + e.getName() + "\t:\t(" + e.getPosition().x + ", " + e.getPosition().y + ")");
	}
	
	public static void printPlayerInventory(){
		println("Inventory:");
		for(Item i : DungeonCrawler.player.getInventory().getContents()){
			println(i.getName());
		}
		
		println("\nEquipped Items:");
		for(ItemEquippable i : DungeonCrawler.player.getInventory().getEquippedContents()){
			if(i != null) println(i.getName());
		}
	}
	
	public static void printInventory(Inventory i){
		println("Contents of " + i.getName() + ":");
		for(Item item : i.getContents()){
			println(item.getName());
		}
	}
	
}
