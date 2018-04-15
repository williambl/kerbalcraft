package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.KerbalCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModBlocks {

	public static BlockStager STAGER;
	public static BlockSASActivator SAS_ACTIVATOR;
	public static BlockRCSActivator RCS_ACTIVATOR;
	public static BlockGearActivator GEAR_ACTIVATOR;
	public static BlockLightActivator LIGHT_ACTIVATOR;
	public static BlockBrakeActivator BRAKE_ACTIVATOR;
	public static BlockAborter ABORTER;
	public static BlockThrottleSetter THROTTLE_SETTER;
	public static BlockActionGroupActivator[] ACTION_GROUP_ACTIVATORS = new BlockActionGroupActivator[10];

	public static void AddBlocks () {
		STAGER = new BlockStager("stager", MapColor.BLACK);
		SAS_ACTIVATOR = new BlockSASActivator("sas_activator", MapColor.BLACK);
		RCS_ACTIVATOR = new BlockRCSActivator("rcs_activator", MapColor.BLACK);
		GEAR_ACTIVATOR = new BlockGearActivator("gear_activator", MapColor.BLACK);
		LIGHT_ACTIVATOR = new BlockLightActivator("light_activator", MapColor.BLACK);
		BRAKE_ACTIVATOR = new BlockBrakeActivator("brake_activator", MapColor.BLACK);
		ABORTER = new BlockAborter("aborter", MapColor.BLACK);
		THROTTLE_SETTER = new BlockThrottleSetter("throttle_setter", MapColor.BLACK);

		for (int i = 0; i < 10; i++) {
			ACTION_GROUP_ACTIVATORS[i] = new BlockActionGroupActivator("action_group_activator_" + i, MapColor.BLACK, i);
			System.out.println(ACTION_GROUP_ACTIVATORS[i]);
		}
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

		/**
		 * Register this mod's {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			AddBlocks();
			final IForgeRegistry<Block> registry = event.getRegistry();

			event.getRegistry().registerAll(
					STAGER,
					SAS_ACTIVATOR,
					RCS_ACTIVATOR,
					GEAR_ACTIVATOR,
					LIGHT_ACTIVATOR,
					BRAKE_ACTIVATOR,
					ABORTER,
					THROTTLE_SETTER
			);
			event.getRegistry().registerAll(ACTION_GROUP_ACTIVATORS);
		}

		/**
		 * Register this mod's {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlocks(RegistryEvent.Register<Item> event) {

			final ItemBlock[] items = {
					new ItemBlock(STAGER),
					new ItemBlock(SAS_ACTIVATOR),
					new ItemBlock(RCS_ACTIVATOR),
					new ItemBlock(GEAR_ACTIVATOR),
					new ItemBlock(LIGHT_ACTIVATOR),
					new ItemBlock(BRAKE_ACTIVATOR),
					new ItemBlock(ABORTER),
					new ItemBlock(THROTTLE_SETTER)
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) {
				registry.register(item.setRegistryName(item.getBlock().getRegistryName()));
				ITEM_BLOCKS.add(item);
			}

			for (BlockActionGroupActivator block : ACTION_GROUP_ACTIVATORS) {
				final ItemBlock item = (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
				registry.register(item);
				ITEM_BLOCKS.add(item);
			}
		}

		/**
		 * Register this mod's ItemBlock Models.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlockModels(ModelRegistryEvent event) {
		}

		public static void registerTileEntities() {
		}

		private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {
			GameRegistry.registerTileEntity(tileEntityClass, KerbalCraft.MODID + ":" + tileEntityClass.getSimpleName().replaceFirst("TileEntity", ""));
		}

	}
}
