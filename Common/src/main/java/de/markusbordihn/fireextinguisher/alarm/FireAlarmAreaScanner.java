/*
 * Copyright 2026 Markus Bordihn
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

package de.markusbordihn.fireextinguisher.alarm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;

public class FireAlarmAreaScanner {

  private FireAlarmAreaScanner() {}

  public static List<BlockPos> findLoadedBlocks(
      ServerLevel serverLevel,
      BlockPos center,
      int radiusX,
      int radiusY,
      int radiusZ,
      Predicate<BlockState> filter) {
    List<BlockPos> matches = new ArrayList<>();
    int minX = center.getX() - radiusX;
    int maxX = center.getX() + radiusX;
    int minY = Math.max(serverLevel.getMinBuildHeight(), center.getY() - radiusY);
    int maxY = Math.min(serverLevel.getMaxBuildHeight() - 1, center.getY() + radiusY);
    int minZ = center.getZ() - radiusZ;
    int maxZ = center.getZ() + radiusZ;
    if (minY > maxY) {
      return matches;
    }

    BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
    for (int chunkX = minX >> 4; chunkX <= maxX >> 4; chunkX++) {
      for (int chunkZ = minZ >> 4; chunkZ <= maxZ >> 4; chunkZ++) {
        LevelChunk chunk = serverLevel.getChunkSource().getChunkNow(chunkX, chunkZ);
        if (chunk == null) {
          continue;
        }

        int chunkMinX = Math.max(minX, chunkX << 4);
        int chunkMaxX = Math.min(maxX, (chunkX << 4) + 15);
        int chunkMinZ = Math.max(minZ, chunkZ << 4);
        int chunkMaxZ = Math.min(maxZ, (chunkZ << 4) + 15);
        for (int sectionY = minY >> 4; sectionY <= maxY >> 4; sectionY++) {
          LevelChunkSection section =
              chunk.getSection(serverLevel.getSectionIndexFromSectionY(sectionY));
          if (section.hasOnlyAir()) {
            continue;
          }

          int sectionMinY = Math.max(minY, sectionY << 4);
          int sectionMaxY = Math.min(maxY, (sectionY << 4) + 15);
          for (int y = sectionMinY; y <= sectionMaxY; y++) {
            for (int x = chunkMinX; x <= chunkMaxX; x++) {
              for (int z = chunkMinZ; z <= chunkMaxZ; z++) {
                blockPos.set(x, y, z);
                if (filter.test(chunk.getBlockState(blockPos))) {
                  matches.add(blockPos.immutable());
                }
              }
            }
          }
        }
      }
    }
    return matches;
  }
}
