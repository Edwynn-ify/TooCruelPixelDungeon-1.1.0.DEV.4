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

package com.shatteredpixel.shatteredpixeldungeon.levels.traps;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Statue;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.StatueSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class GuardianTrap extends MobSummonTrap {

	{
		color = RED;
		shape = STARS;
	}

	@Override
	public void activate() {

		for (Mob mob : Dungeon.level.mobs()) {
			mob.beckon( pos );
		}

		if (Dungeon.level.heroFOV[pos]) {
			GLog.w( Messages.get(this, "alarm") );
			CellEmitter.center(pos).start( Speck.factory(Speck.SCREAM), 0.3f, 3 );
		}

		Sample.INSTANCE.play( Assets.Sounds.ALERT );

		summonMobs((Dungeon.scalingFactor() - 5)/5);
	}

	@Override
	protected MobSummonTrap.SpawnerActor getSpawner(int amount, int maxTries) {
		return new SpawnerActor(maxTries,amount);
	}

	public static class Guardian extends Statue {

		{
			spriteClass = GuardianSprite.class;

			EXP = 0;
			state = WANDERING;
		}

		public Guardian(){
			super();

			weapon.enchant(null);
			weapon.degrade(weapon.level());
		}

		@Override
		public void beckon(int cell) {
			//Beckon works on these ones, unlike their superclass.
			notice();

			if (state != HUNTING) {
				state = WANDERING;
			}
			target = cell;
		}

	}

	public static class GuardianSprite extends StatueSprite {

		public GuardianSprite(){
			super();
			tint(0, 0, 1, 0.2f);
		}

		@Override
		public void resetColor() {
			super.resetColor();
			tint(0, 0, 1, 0.2f);
		}
	}


	public static class SpawnerActor extends MobSummonTrap.SpawnerActor{

		public SpawnerActor(){
			super();
		}
		public SpawnerActor(int tries, int count) {
			super(tries, count);
		}

		@Override
		protected boolean spawnMob() {
			Guardian guardian = new Guardian();
			int pos = Dungeon.level.randomRespawnCell(guardian );
			if (pos == -1) return false;
			guardian.state = guardian.WANDERING;
			guardian.pos(pos);
			GameScene.add(guardian);
			guardian.beckon(Dungeon.hero.pos());
			mobsToPlace.add(guardian);
			return true;
		}
	}
}
