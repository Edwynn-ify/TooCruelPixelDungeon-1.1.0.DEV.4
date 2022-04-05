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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Greatshield extends MeleeWeapon {

	{
		image = ItemSpriteSheet.GREATSHIELD;

		tier(5);
	}

	@Override
	public int max(int lvl) {
		return  Math.round(2.5f*(buffedTier() +1)) +     //15 base, down from 30
				lvl*(buffedTier() +2);                   //+3 per level, down from +6
	}

	@Override
	public int defenseFactor( Char owner ) {
		return 8+4*buffedLvl();    //6 extra defence, plus 3 per level;
	}
	
	public String statsInfo(){
		if (isIdentified()){
			return Messages.get(this, "stats_desc", 8+4*buffedLvl());
		} else {
			return Messages.get(this, "typical_stats_desc", 6);
		}
	}
}