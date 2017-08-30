package me.laurence.dungeonCrawler.entities.living;

import java.awt.Point;

import me.laurence.dungeonCrawler.DungeonCrawler;
import me.laurence.dungeonCrawler.ai.AIEmpty;
import me.laurence.dungeonCrawler.ai.IEntityAI;
import me.laurence.dungeonCrawler.entities.Entity;
import me.laurence.dungeonCrawler.general.AbilityStats;
import me.laurence.dungeonCrawler.handlers.PrintHandler;
import me.laurence.dungeonCrawler.inventory.InventoryEquips;
import me.laurence.dungeonCrawler.items.equippables.ItemEquippable;

abstract public class EntityLiving extends Entity{
	protected AbilityStats stats = new AbilityStats();
	protected IEntityAI ai = new AIEmpty();
	protected InventoryEquips inventory = (InventoryEquips) new InventoryEquips().setMaxSize(5);
	protected int health;
	// TODO: Statuses
	
	public EntityLiving(){}
	protected EntityLiving(EntityLiving e){
		super(e);
		this.ai = e.ai;
		this.stats = e.stats.clone();
		this.health = e.health;
		this.inventory = e.inventory.clone();
	}
	
	public void attack(Point position){
		DungeonCrawler.getFloor().getEntityAt(position).onHit(this);
	}
	
	@Override
	public void onHit(Entity e) {
		if(e instanceof EntityLiving){
			this.health -= (((EntityLiving) e).getEffectiveAtk() - this.getEffectiveDef());
			PrintHandler.println(this.getName() + " was hit, health now: " + this.getHealth());
			
			if(this.health <= 0) destroy(e);
		}
	}
	
	public void onDestroy(Entity e){};
	public void onInteract(Entity e) {}
	public void onWalkOn(Entity e) {}
	
	public void equip(ItemEquippable i, boolean fromInventory){
		if(fromInventory) inventory.removeItem(i);
		ItemEquippable i2 = inventory.equipItem(i);
		if(i2 != null) inventory.addItem(i2);
	}
	
	public void equip(String s, boolean fromInventory){
		if(fromInventory) inventory.removeItem(s);
		ItemEquippable i2 = inventory.equipItem(s);
		if(i2 != null) inventory.addItem(i2);
	}
	
	public int getBaseMaxHealth() {
		return stats.maxHealth;
	}

	public int getEffectiveMaxHealth(){
		return this.getBaseMaxHealth() + inventory.getMaxHealth();
	}
	
	public EntityLiving setBaseMaxHealth(int maxHealth) {
		stats.maxHealth = maxHealth;
		return this;
	}

	public int getHealth() {
		return this.health;
	}

	public EntityLiving setHealth(int health) {
		this.health = health > stats.maxHealth ? stats.maxHealth : health;
		return this;
	}

	public int getBaseMoveRange() {
		return stats.moveRange;
	}

	public int getEffectiveMoveRange(){
		return this.getBaseMoveRange() + inventory.getMoveRange();
	}
	
	public EntityLiving setBaseMoveRange(int moveRange) {
		stats.moveRange = moveRange;
		return this;
	}

	public int getBaseAttackRange() {
		return stats.attackRange;
	}

	public int getEffectiveAttackRange(){
		return this.getBaseAttackRange() + inventory.getAttackRange();
	}
	
	public EntityLiving setBaseAttackRange(int attackRange) {
		stats.attackRange = attackRange;
		return this;
	}

	public int getBaseDef() {
		return stats.def;
	}

	public int getEffectiveDef(){
		return this.getBaseDef() + inventory.getDef();
	}
	
	public EntityLiving setBaseDef(int def) {
		stats.def = def;
		return this;
	}

	public int getBaseAtk() {
		return stats.atk;
	}

	public int getEffectiveAtk(){
		return this.getBaseAtk() + inventory.getAtk();
	}
	
	public EntityLiving setBaseAtk(int atk) {
		stats.atk = atk;
		return this;
	}

	public int getBaseSatk() {
		return stats.satk;
	}

	public int getEffectiveSatk(){
		return this.getBaseSatk() + inventory.getSatk();
	}
	
	public EntityLiving setBaseSatk(int satk) {
		stats.satk = satk;
		return this;
	}

	public int getBaseSdef() {
		return stats.sdef;
	}

	public int getEffectiveSdef(){
		return this.getBaseSdef() + inventory.getSdef();
	}
	
	public EntityLiving setBaseSdef(int sdef) {
		stats.sdef = sdef;
		return this;
	}
	
	public IEntityAI getAi() {
		return ai;
	}

	public EntityLiving setAi(IEntityAI ai) {
		this.ai = ai;
		return this;
	}

	public InventoryEquips getInventory() {
		return inventory;
	}

	public EntityLiving setInventory(InventoryEquips inventory) {
		this.inventory = inventory;
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ai == null) ? 0 : ai.hashCode());
		result = prime * result + health;
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityLiving other = (EntityLiving) obj;
		if (ai == null) {
			if (other.ai != null)
				return false;
		} else if (!ai.equals(other.ai))
			return false;
		if (health != other.health)
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		return true;
	}
	
	abstract public EntityLiving clone();
	
}
