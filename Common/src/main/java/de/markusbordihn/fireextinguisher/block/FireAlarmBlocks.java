/*
 * Copyright 2023 Markus Bordihn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.markusbordihn.fireextinguisher.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class FireAlarmBlocks {

  public static final Block FIRE_SPRINKLER =
      new FireSprinklerBlock(
          Properties.of()
              .mapColor(MapColor.STONE)
              .instrument(NoteBlockInstrument.BASEDRUM)
              .requiresCorrectToolForDrops()
              .strength(3.5F));
  public static final Block FIRE_ALARM_SWITCH =
      new FireAlarmSwitchBlock(
          Properties.of()
              .noCollission()
              .strength(0.5F)
              .sound(SoundType.STONE)
              .pushReaction(PushReaction.DESTROY));
  public static final Block FIRE_ALARM_BELL =
      new FireAlarmBellBlock(
          Properties.of()
              .mapColor(MapColor.GOLD)
              .instrument(NoteBlockInstrument.BELL)
              .requiresCorrectToolForDrops()
              .strength(3.0F, 6.0F)
              .sound(SoundType.METAL)
              .lightLevel(FireAlarmBellBlock::getLightEmission));
  public static final Block FIRE_ALARM_SIREN =
      new FireAlarmSirenBlock(
          Properties.of()
              .mapColor(MapColor.GOLD)
              .instrument(NoteBlockInstrument.BELL)
              .requiresCorrectToolForDrops()
              .strength(3.0F, 6.0F)
              .sound(SoundType.METAL)
              .lightLevel(FireAlarmSirenBlock::getLightEmission));
  public static final Block FIRE_ALARM_SMOKE_DETECTOR =
      new FireAlarmSmokeDetectorBlock(
          Properties.of()
              .mapColor(MapColor.WOOD)
              .instrument(NoteBlockInstrument.BASS)
              .strength(0.2F)
              .sound(SoundType.WOOD)
              .ignitedByLava()
              .lightLevel(FireAlarmSmokeDetectorBlock::getLightEmission));
  public static final Block FIRE_ALARM_SMOKE_DETECTOR_SILENT =
      new FireAlarmSmokeDetectorSilentBlock(
          Properties.of()
              .mapColor(MapColor.WOOD)
              .instrument(NoteBlockInstrument.BASS)
              .strength(0.2F)
              .sound(SoundType.WOOD)
              .ignitedByLava()
              .lightLevel(FireAlarmSmokeDetectorSilentBlock::getLightEmission));

  protected FireAlarmBlocks() {}
}
