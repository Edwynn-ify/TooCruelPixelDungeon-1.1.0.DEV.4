/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items.potions;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfArcaneArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfDragonsBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfIcyTouch;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfToxicEssence;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDivineInspiration;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDragonsBreath;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfEarthenArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfMagicalSight;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfMastery;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfShielding;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfShroudingFog;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfSnapFreeze;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfStormClouds;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashMap;

public class AlchemicalCatalyst extends Potion {
	
	{
		image = ItemSpriteSheet.POTION_CATALYST;
		
	}
	
	private static HashMap<Class<? extends Potion>, Float> potionChances = new HashMap<>();
	static{
		potionChances.put(PotionOfHealing.class,        2f);
		potionChances.put(PotionOfMindVision.class,     2f);
		potionChances.put(PotionOfFrost.class,          2f);
		potionChances.put(PotionOfLiquidFlame.class,    2f);
		potionChances.put(PotionOfToxicGas.class,       2f);
		potionChances.put(PotionOfHaste.class,          2f);
		potionChances.put(PotionOfInvisibility.class,   2f);
		potionChances.put(PotionOfLevitation.class,     2f);
		potionChances.put(PotionOfParalyticGas.class,   2f);
		potionChances.put(PotionOfPurity.class,         2f);
		potionChances.put(PotionOfExperience.class,     2f);
		potionChances.put(PotionOfStrength.class,       2f);
		potionChances.put(PotionOfCleansing.class,      2f);
		potionChances.put(PotionOfDivineInspiration.class,      2f);
		potionChances.put(PotionOfDragonsBreath.class,      2f);
		potionChances.put(PotionOfEarthenArmor.class,      2f);
		potionChances.put(PotionOfMagicalSight.class,      2f);
		potionChances.put(PotionOfMastery.class,      2f);
		potionChances.put(PotionOfShielding.class,      2f);
		potionChances.put(PotionOfShroudingFog.class,      2f);
		potionChances.put(PotionOfSnapFreeze.class,      2f);
		potionChances.put(PotionOfStormClouds.class,      2f);
		potionChances.put(ElixirOfAquaticRejuvenation.class,      2f);
		potionChances.put(ElixirOfArcaneArmor.class,      2f);
		potionChances.put(ElixirOfDragonsBlood.class,      2f);
		potionChances.put(ElixirOfHoneyedHealing.class,      2f);
		potionChances.put(ElixirOfIcyTouch.class,      2f);
		potionChances.put(ElixirOfMight.class,      2f);
		potionChances.put(ElixirOfToxicEssence.class,      2f);





	}
	
	@Override
	public void apply(Hero hero) {
		Potion p = Reflection.newInstance(Random.chances(potionChances));
		while (Challenges.PHARMACOPHOBIA.enabled() && p instanceof PotionOfHealing){
			p = Reflection.newInstance(Random.chances(potionChances));
		}
		p.anonymize();
		p.apply(hero);
	}
	
	@Override
	public void shatter(int cell) {
		Potion p = Reflection.newInstance(Random.chances(potionChances));
		p.anonymize();
		curItem = p;
		p.shatter(cell);
	}
	
	@Override
	public boolean isKnown() {
		return true;
	}
	
	@Override
	public int value() {
		return 40 * quantity;
}

	@Override
	public int energyVal() {
		return 8 * quantity;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			boolean potion = false;
			boolean secondary = false;
			
			for (Item i : ingredients){
				if (i instanceof Plant.Seed || i instanceof Runestone){
					secondary = true;
				//if it is a regular or exotic potion
				} else if (ExoticPotion.regToExo.containsKey(i.getClass())
						|| ExoticPotion.regToExo.containsValue(i.getClass())) {
					potion = true;
				}
			}
			
			return potion && secondary;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			for (Item i : ingredients){
				if (i instanceof Plant.Seed){
					return 0;
				} else if (i instanceof Runestone){
					return 1;
				}
			}
			return 0;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			
			for (Item i : ingredients){
				i.quantity(i.quantity()-1);
			}
			
			return sampleOutput(null);
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new AlchemicalCatalyst();
		}
	}
	
}
