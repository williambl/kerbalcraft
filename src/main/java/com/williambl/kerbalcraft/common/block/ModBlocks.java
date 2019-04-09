package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.IIndicator;
import com.williambl.kerbalcraft.KerbalCraft;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

@GameRegistry.ObjectHolder("kerbalcraft")
public class ModBlocks {

    @GameRegistry.ObjectHolder("stager")
    public static BlockEffector STAGER;
    @GameRegistry.ObjectHolder("sas_activator")
    public static BlockEffector SAS_ACTIVATOR;
    @GameRegistry.ObjectHolder("rcs_activator")
    public static BlockEffector RCS_ACTIVATOR;
    @GameRegistry.ObjectHolder("gear_activator")
    public static BlockEffector GEAR_ACTIVATOR;
    @GameRegistry.ObjectHolder("light_activator")
    public static BlockEffector LIGHT_ACTIVATOR;
    @GameRegistry.ObjectHolder("brake_activator")
    public static BlockEffector BRAKE_ACTIVATOR;
    @GameRegistry.ObjectHolder("aborter")
    public static BlockEffector ABORTER;
    @GameRegistry.ObjectHolder("throttle_setter")
    public static BlockEffector THROTTLE_SETTER;

    public static BlockEffector[] ACTION_GROUP_ACTIVATORS = new BlockEffector[10];

    @GameRegistry.ObjectHolder("sas_indicator")
    public static BlockIndicator SAS_INDICATOR;
    @GameRegistry.ObjectHolder("rcs_indicator")
    public static BlockIndicator RCS_INDICATOR;
    @GameRegistry.ObjectHolder("gear_indicator")
    public static BlockIndicator GEAR_INDICATOR;
    @GameRegistry.ObjectHolder("light_indicator")
    public static BlockIndicator LIGHT_INDICATOR;
    @GameRegistry.ObjectHolder("brake_indicator")
    public static BlockIndicator BRAKE_INDICATOR;


    static Block[] AddEffectors() {
        Block[] effectors = new Block[]{

                new BlockEffector("stager", MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    if (power == 0) return;
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        vessel.getControl().activateNextStage();
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }),

                new BlockEffector("sas_activator", MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        vessel.getControl().setSAS(power > 0);
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }),

                new BlockEffector("rcs_activator", MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        vessel.getControl().setRCS(power > 0);
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }),

                new BlockEffector("gear_activator", MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        vessel.getControl().setGear(power > 0);
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }),

                new BlockEffector("light_activator", MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        vessel.getControl().setLights(power > 0);
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }),

                new BlockEffector("brake_activator", MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        vessel.getControl().setBrakes(power > 0);
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }),

                new BlockEffector("aborter", MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        vessel.getControl().setAbort(power > 0);
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }),

                new BlockEffector("throttle_setter", MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        vessel.getControl().setThrottle((float) power / 15);
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                })};

        for (int i = 0; i < 10; i++) {
            final int j = i; //Sorry, I know this looks ugly, but it's needed in order for the lambda below to work
            ACTION_GROUP_ACTIVATORS[i] = new BlockEffector("action_group_activator_" + i, MapColor.BLACK, (state, worldIn, pos, blockIn, fromPos) -> {
                int power = worldIn.getStrongPower(pos);
                try {
                    SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                    vessel.getControl().setActionGroup(j, power > 0);
                } catch (RPCException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(ACTION_GROUP_ACTIVATORS[i]);
        }

        return ArrayUtils.addAll(effectors, ACTION_GROUP_ACTIVATORS);

    }

    public static Block[] AddIndicators() {
        Block[] indicators = new Block[]{
                new BlockIndicator("sas_indicator", MapColor.BLACK, (state, blockAccess, pos, side, strongPower) -> {
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        return vessel.getControl().getSAS() ? 0 : 1;
                    } catch (RPCException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }),

                new BlockIndicator("rcs_indicator", MapColor.BLACK, (state, blockAccess, pos, side, strongPower) -> {
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        return vessel.getControl().getRCS() ? 0 : 1;
                    } catch (RPCException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }),

                new BlockIndicator("gear_indicator", MapColor.BLACK, (state, blockAccess, pos, side, strongPower) -> {
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        return vessel.getControl().getGear() ? 0 : 1;
                    } catch (RPCException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }),

                new BlockIndicator("light_indicator", MapColor.BLACK, (state, blockAccess, pos, side, strongPower) -> {
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        return vessel.getControl().getLights() ? 0 : 1;
                    } catch (RPCException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }),

                new BlockIndicator("brake_indicator", MapColor.BLACK, (state, blockAccess, pos, side, strongPower) -> {
                    try {
                        SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
                        return vessel.getControl().getBrakes() ? 0 : 1;
                    } catch (RPCException e) {
                        e.printStackTrace();
                        return 0;
                    }
                })};

        return indicators;

    }

    static Block[] AddBlocks() {
        return ArrayUtils.addAll(AddEffectors(), AddIndicators());
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

            event.getRegistry().registerAll(AddBlocks());
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
                    new ItemBlock(THROTTLE_SETTER),

                    new ItemBlock(SAS_INDICATOR),
                    new ItemBlock(RCS_INDICATOR),
                    new ItemBlock(GEAR_INDICATOR),
                    new ItemBlock(LIGHT_INDICATOR),
                    new ItemBlock(BRAKE_INDICATOR)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final ItemBlock item : items) {
                registry.register(item.setRegistryName(item.getBlock().getRegistryName()));
                ITEM_BLOCKS.add(item);
            }

            for (BlockEffector block : ACTION_GROUP_ACTIVATORS) {
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
            STAGER.initModel();
            SAS_ACTIVATOR.initModel();
            RCS_ACTIVATOR.initModel();
            GEAR_ACTIVATOR.initModel();
            LIGHT_ACTIVATOR.initModel();
            BRAKE_ACTIVATOR.initModel();
            ABORTER.initModel();
            THROTTLE_SETTER.initModel();

            SAS_INDICATOR.initModel();
            RCS_INDICATOR.initModel();
            GEAR_INDICATOR.initModel();
            LIGHT_INDICATOR.initModel();
            BRAKE_INDICATOR.initModel();
        }

        public static void registerTileEntities() {
        }

        private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {
            GameRegistry.registerTileEntity(tileEntityClass, KerbalCraft.MODID + ":" + tileEntityClass.getSimpleName().replaceFirst("TileEntity", ""));
        }

    }
}
