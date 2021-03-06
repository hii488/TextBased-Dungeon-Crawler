package me.laurence.dungeonCrawler.entities.living.hostile;

import me.laurence.dungeonCrawler.entities.living.EntityLiving;

//This is not actually needed - look at EntityList to see another way to create generic entities.
public class EntityBat extends EntityLiving{
	
	public EntityBat(){
		super();
		this.name = "bat";
		stats.setAtk(1);
		this.canPassThrough = true;
		this.charCode = 'b';
		this.health = 3;
		stats.setMaxHealth(3);
		stats.setMoveRange(2);
	}
	
	protected EntityBat(EntityBat e){
		super(e);
	}
	
	@Override
	public float getSpawnChance(float currentDifficulty) {
		return 0;
	}

	@Override
	public EntityBat clone() {
		return new EntityBat(this);
	}
}
