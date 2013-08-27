package projectzulu.common.world2.blueprints;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomItem;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.blueprint.Blueprint;

public class BlueprintCemetaryEdge implements Blueprint {

    List<BlockWithMeta> wallBlocks = new ArrayList<BlockWithMeta>(3);
    List<BlockWithMeta> flowerBlocks = new ArrayList<BlockWithMeta>(3);

    public BlueprintCemetaryEdge() {
        wallBlocks.add(new BlockWithMeta(Block.cobblestoneWall.blockID, 1, 2));
        wallBlocks.add(new BlockWithMeta(Block.cobblestoneWall.blockID, 0, 3));
        wallBlocks.add(new BlockWithMeta(0, 0, 3));
        flowerBlocks.add(new BlockWithMeta(Block.plantRed.blockID, 0, 1));
        flowerBlocks.add(new BlockWithMeta(Block.plantYellow.blockID, 0, 1));
        flowerBlocks.add(new BlockWithMeta(Block.tallGrass.blockID, 1, 10));
        flowerBlocks.add(new BlockWithMeta(0, 0, 8));
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {

        if (piecePos.posY == 0) {
            return new BlockWithMeta(Block.grass.blockID);
        } else if (piecePos.posY == 1) {
            /* North Facing Fence */
            if (cellIndexDirection == CellIndexDirection.NorthWall
                    || cellIndexDirection == CellIndexDirection.NorthWestCorner
                    || cellIndexDirection == CellIndexDirection.NorthEastCorner) {
                if (piecePos.posZ == 0) {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                }
            }

            /* South Facing Fence */
            if (cellIndexDirection == CellIndexDirection.SouthWall
                    || cellIndexDirection == CellIndexDirection.SouthWestCorner
                    || cellIndexDirection == CellIndexDirection.SouthEastCorner) {
                if (piecePos.posZ == cellSize - 1) {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                }
            }

            /* West Facing Fence */
            if (cellIndexDirection == CellIndexDirection.WestWall
                    || cellIndexDirection == CellIndexDirection.NorthWestCorner
                    || cellIndexDirection == CellIndexDirection.SouthWestCorner) {
                if (piecePos.posX == 0) {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                }
            }

            /* East Facing Fence */
            if (cellIndexDirection == CellIndexDirection.EastWall
                    || cellIndexDirection == CellIndexDirection.NorthEastCorner
                    || cellIndexDirection == CellIndexDirection.SouthEastCorner) {
                if (piecePos.posX == cellSize - 1) {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                }
            }
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, flowerBlocks);
        }
        return new BlockWithMeta(0);
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public String getIdentifier() {
        return "CemetaryEdge";
    }
}