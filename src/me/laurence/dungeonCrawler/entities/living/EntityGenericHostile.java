package me.laurence.dungeonCrawler.entities.living;

import java.awt.Point;

import me.laurence.dungeonCrawler.GameData;
import me.laurence.dungeonCrawler.ai.AIHostile;

public class EntityGenericHostile extends EntityLiving{

	protected float preferredDifficulty = 1;
	protected int score = 0;
	
	public EntityGenericHostile(){
		super();
		this.name = "generic";
		this.canPassThrough = false;
		this.charCode = 'g';
		
		this.ai = new AIHostile();
		((AIHostile) this.ai).e = this;
		this.position = new Point(0,0);
	}
	
	protected EntityGenericHostile(EntityGenericHostile e){
		super(e);
		((AIHostile) this.ai).e = this;
	}
	
	public void onDestroy(EntityLiving e){
		if(e instanceof EntityPlayer) GameData.Dungeon.currentScore += score * GameData.Dungeon.difficulty;
	}
	
	public float getPreferredDifficulty() {
		return preferredDifficulty;
	}

	public EntityGenericHostile setPreferredDifficulty(float preferredDifficulty) {
		this.preferredDifficulty = preferredDifficulty;
		return this;
	}
	
	public int getScore() {
		return score;
	}

	public EntityGenericHostile setScore(int score) {
		this.score = score;
		return this;
	}

	@Override // Uses modified Chi-squared/Chi distribution to get results - need to edit to stop large difficulties overloading factorial.
	public float getSpawnChance(float currentDifficulty) {
		float dif = currentDifficulty - 1;
		dif = dif > 0 ? dif : 0.1f;
		return (float) ((Math.pow(dif, preferredDifficulty -1) * Math.pow(Math.E, -dif))/(Math.pow(2, preferredDifficulty) * factorial(1, (int)(preferredDifficulty-1))));
	}

	@Override
	public EntityGenericHostile clone() {
		return new EntityGenericHostile(this);
	}

	public int factorial(int n, int x){
		if(x <= 1) return n;
		return factorial(n*x, x-1);
	}
	
}
