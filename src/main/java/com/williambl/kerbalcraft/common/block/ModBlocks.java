package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.KerbalCraft;
import com.williambl.kerbalcraft.common.tileentity.TileEntityIndicator;
import krpc.client.services.SpaceCenter;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
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
    @GameRegistry.ObjectHolder("yaw_left_steerer")
    public static BlockEffector YAW_LEFT_STEERER;
    @GameRegistry.ObjectHolder("yaw_right_steerer")
    public static BlockEffector YAW_RIGHT_STEERER;
    @GameRegistry.ObjectHolder("pitch_down_steerer")
    public static BlockEffector PITCH_DOWN_STEERER;
    @GameRegistry.ObjectHolder("pitch_up_steerer")
    public static BlockEffector PITCH_UP_STEERER;
    @GameRegistry.ObjectHolder("roll_left_steerer")
    public static BlockEffector ROLL_LEFT_STEERER;
    @GameRegistry.ObjectHolder("roll_right_steerer")
    public static BlockEffector ROLL_RIGHT_STEERER;


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
    @GameRegistry.ObjectHolder("comms_indicator")
    public static BlockIndicator COMMS_INDICATOR;
    @GameRegistry.ObjectHolder("orbit_indicator")
    public static BlockIndicator ORBIT_INDICATOR;
    @GameRegistry.ObjectHolder("descending_indicator")
    public static BlockIndicator DESCENDING_INDICATOR;
    @GameRegistry.ObjectHolder("liquidfuel_indicator")
    public static BlockIndicator LIQUIDFUEL_INDICATOR;
    @GameRegistry.ObjectHolder("oxidiser_indicator")
    public static BlockIndicator OXIDISER_INDICATOR;
    @GameRegistry.ObjectHolder("monopropellant_indicator")
    public static BlockIndicator MONOPROPELLANT_INDICATOR;
    @GameRegistry.ObjectHolder("xenon_indicator")
    public static BlockIndicator XENON_INDICATOR;
    @GameRegistry.ObjectHolder("solidfuel_indicator")
    public static BlockIndicator SOLIDFUEL_INDICATOR;
    @GameRegistry.ObjectHolder("electriccharge_indicator")
    public static BlockIndicator ELECTRICCHARGE_INDICATOR;


    public static BlockEffector[] ACTION_GROUP_ACTIVATORS = new BlockEffector[10];


    private static Block[] AddEffectors() {
        Block[] effectors = new Block[]{

                new BlockEffector("stager", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);
                    if (power == 0) return;

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().activateNextStage();
                }),

                new BlockEffector("sas_activator", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setSAS(power > 0);
                }),

                new BlockEffector("rcs_activator", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setRCS(power > 0);
                }),

                new BlockEffector("gear_activator", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setGear(power > 0);
                }),

                new BlockEffector("light_activator", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setLights(power > 0);
                }),

                new BlockEffector("brake_activator", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setBrakes(power > 0);
                }),

                new BlockEffector("aborter", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setAbort(power > 0);

                }),

                new BlockEffector("throttle_setter", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setThrottle((float) power / 15);
                }),

                new BlockEffector("yaw_left_steerer", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setYaw(-((float) power / 15));
                }),

                new BlockEffector("yaw_right_steerer", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setYaw((float) power / 15);
                }),

                new BlockEffector("pitch_down_steerer", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setPitch(-((float) power / 15));
                }),

                new BlockEffector("pitch_up_steerer", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setPitch((float) power / 15);
                }),

                new BlockEffector("roll_left_steerer", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setRoll(-((float) power / 15));
                }),

                new BlockEffector("roll_right_steerer", (state, worldIn, pos, blockIn, fromPos) -> {
                    int power = worldIn.getStrongPower(pos);

                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    vessel.getControl().setRoll((float) power / 15);
                })
        };

        for (int i = 0; i < 10; i++) {
            final int j = i; //Sorry, I know this looks ugly, but it's needed in order for the lambda below to work
            ACTION_GROUP_ACTIVATORS[i] = new BlockEffector("action_group_activator_" + i, (state, worldIn, pos, blockIn, fromPos) -> {
                int power = worldIn.getStrongPower(pos);

                SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                vessel.getControl().setActionGroup(j, power > 0);
            });
            System.out.println(ACTION_GROUP_ACTIVATORS[i]);
        }

        return ArrayUtils.addAll(effectors, ACTION_GROUP_ACTIVATORS);

    }

    private static Block[] AddIndicators() {
        Block[] indicators = new Block[]{
                new BlockIndicator("sas_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getControl().getSAS();
                }),

                new BlockIndicator("rcs_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getControl().getRCS();
                }),

                new BlockIndicator("gear_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getControl().getGear();
                }),

                new BlockIndicator("light_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getControl().getLights();
                }),

                new BlockIndicator("brake_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getControl().getBrakes();
                }),

                new BlockIndicator("comms_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getComms().getCanCommunicate();
                }),

                new BlockIndicator("orbit_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getSituation() == SpaceCenter.VesselSituation.ORBITING;
                }),

                new BlockIndicator("descending_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.flight(vessel.getReferenceFrame()).getVerticalSpeed() < 0;
                }),

                new BlockIndicator("liquidfuel_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getResources().amount("LiquidFuel") > 0;
                }),

                new BlockIndicator("oxidiser_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getResources().amount("Oxidiser") > 0;
                }),

                new BlockIndicator("monopropellant_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getResources().amount("MonoPropellant") > 0;
                }),

                new BlockIndicator("xenon_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getResources().amount("XenonGas") > 0;
                }),

                new BlockIndicator("solidfuel_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getResources().amount("SolidFuel") > 0;
                }),

                new BlockIndicator("electriccharge_indicator", (state, world, pos) -> {
                    SpaceCenter.Vessel vessel = KerbalCraft.connectionManager.getSpaceCenter().getActiveVessel();
                    return vessel.getResources().amount("ElectricCharge") > 0;
                }),
        };

        return indicators;

    }

    private static Block[] AddBlocks() {
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

            registerTileEntities();
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
                    new ItemBlock(YAW_LEFT_STEERER),
                    new ItemBlock(YAW_RIGHT_STEERER),
                    new ItemBlock(PITCH_DOWN_STEERER),
                    new ItemBlock(PITCH_UP_STEERER),
                    new ItemBlock(ROLL_LEFT_STEERER),
                    new ItemBlock(ROLL_RIGHT_STEERER),

                    new ItemBlock(SAS_INDICATOR),
                    new ItemBlock(RCS_INDICATOR),
                    new ItemBlock(GEAR_INDICATOR),
                    new ItemBlock(LIGHT_INDICATOR),
                    new ItemBlock(BRAKE_INDICATOR),
                    new ItemBlock(COMMS_INDICATOR),
                    new ItemBlock(ORBIT_INDICATOR),
                    new ItemBlock(DESCENDING_INDICATOR),
                    new ItemBlock(LIQUIDFUEL_INDICATOR),
                    new ItemBlock(OXIDISER_INDICATOR),
                    new ItemBlock(MONOPROPELLANT_INDICATOR),
                    new ItemBlock(XENON_INDICATOR),
                    new ItemBlock(SOLIDFUEL_INDICATOR),
                    new ItemBlock(ELECTRICCHARGE_INDICATOR)
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
            YAW_LEFT_STEERER.initModel();
            YAW_RIGHT_STEERER.initModel();
            PITCH_DOWN_STEERER.initModel();
            PITCH_UP_STEERER.initModel();
            ROLL_LEFT_STEERER.initModel();
            ROLL_RIGHT_STEERER.initModel();

            SAS_INDICATOR.initModel();
            RCS_INDICATOR.initModel();
            GEAR_INDICATOR.initModel();
            LIGHT_INDICATOR.initModel();
            BRAKE_INDICATOR.initModel();
            COMMS_INDICATOR.initModel();
            ORBIT_INDICATOR.initModel();
            DESCENDING_INDICATOR.initModel();
            LIQUIDFUEL_INDICATOR.initModel();
            OXIDISER_INDICATOR.initModel();
            MONOPROPELLANT_INDICATOR.initModel();
            XENON_INDICATOR.initModel();
            SOLIDFUEL_INDICATOR.initModel();
            ELECTRICCHARGE_INDICATOR.initModel();

            for (BlockEffector actionGroupActivator :
                    ACTION_GROUP_ACTIVATORS) {
                actionGroupActivator.initModel();
            }
        }

        public static void registerTileEntities() {
            registerTileEntity(TileEntityIndicator.class);
        }

        private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {
            GameRegistry.registerTileEntity(tileEntityClass, KerbalCraft.MODID + ":" + tileEntityClass.getSimpleName().replaceFirst("TileEntity", ""));
        }

    }
}
