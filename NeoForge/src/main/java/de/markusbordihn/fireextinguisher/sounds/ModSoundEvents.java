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

package de.markusbordihn.fireextinguisher.sounds;

import de.markusbordihn.fireextinguisher.Constants;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
  public static final DeferredRegister<SoundEvent> SOUNDS =
      DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Constants.MOD_ID);

  public static final Holder<SoundEvent> FIRE_ALARM_BELL =
      SOUNDS.register(
          "fire_alarm_bell",
          () ->
              SoundEvent.createVariableRangeEvent(
                  new ResourceLocation(Constants.MOD_ID, "fire_alarm_bell")));
  public static final Holder<SoundEvent> FIRE_ALARM_SIREN =
      SOUNDS.register(
          "fire_alarm_siren",
          () ->
              SoundEvent.createVariableRangeEvent(
                  new ResourceLocation(Constants.MOD_ID, "fire_alarm_siren")));
  public static final Holder<SoundEvent> FIRE_ALARM_SMOKE_DETECTOR =
      SOUNDS.register(
          "fire_alarm_smoke_detector",
          () ->
              SoundEvent.createVariableRangeEvent(
                  new ResourceLocation(Constants.MOD_ID, "fire_alarm_smoke_detector")));

  protected ModSoundEvents() {}
}
