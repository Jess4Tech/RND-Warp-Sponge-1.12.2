package io.github.jess4tech.rngwarp.rngwarp.commands;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.teleport.TeleportHelperFilter;

public class Filter implements TeleportHelperFilter {
	
	@Override
	public String getId() {
		return "Custom Filter";
	}

	@Override
	public String getName() {
		return "Air, Cactus, Fire, Lava and Water";
	}

	@Override
	public boolean isSafeFloorMaterial(BlockState blockState) {
		if (blockState.equals(BlockState.builder().blockType(BlockTypes.WATER).build()))
			return false;
		else if (blockState.equals(BlockState.builder().blockType(BlockTypes.LEAVES).build()))
			return false;
		else if (blockState.equals(BlockState.builder().blockType(BlockTypes.LEAVES2).build()))
			return false;
		return true;
	}

	@Override
	public boolean isSafeBodyMaterial(BlockState blockState) {
		if (blockState.equals(BlockState.builder().blockType(BlockTypes.WATER).build()))
			return false;
		else if (blockState.equals(BlockState.builder().blockType(BlockTypes.LEAVES).build()))
			return false;
		else if (blockState.equals(BlockState.builder().blockType(BlockTypes.LEAVES2).build()))
			return false;
		return true;
	}
	
}
