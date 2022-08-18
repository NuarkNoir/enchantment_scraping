package xyz.nuark.enchantmentscraping.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.nuark.enchantmentscraping.block.entity.custom.ScrapingStationBlockEntity;

public class ScrapingStationBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE_BASE = Shapes.box(0.0, 0.0, 0.0, 1.0, 0.375, 1.0);
    private static final VoxelShape SHAPE_RODS_V = Shapes.or(
            Shapes.box(0.4375, 0.375, 0.0625, 0.5625, 0.8125, 0.1875),
            Shapes.box(0.4375, 0.375, 0.8125, 0.5625, 0.8125, 0.9375),
            Shapes.box(0.5, 0.5625, 0.1875, 0.5, 0.75, 0.8125)
    );
    private static final VoxelShape SHAPE_RODS_H = Shapes.or(
            Shapes.box(0.8125, 0.375, 0.4375, 0.9375, 0.8125, 0.5625),
            Shapes.box(0.0625, 0.375, 0.4375, 0.1875, 0.8125, 0.5625),
            Shapes.box(0.1875, 0.5625, 0.5, 0.8125, 0.75, 0.5)
    );
    private static final VoxelShape SHAPE_N = Shapes.or(SHAPE_BASE, SHAPE_RODS_H);
    private static final VoxelShape SHAPE_W = Shapes.or(SHAPE_BASE, SHAPE_RODS_V);
    private static final VoxelShape SHAPE_S = Shapes.or(SHAPE_BASE, SHAPE_RODS_H);
    private static final VoxelShape SHAPE_E = Shapes.or(SHAPE_BASE, SHAPE_RODS_V);

    public ScrapingStationBlock() {
        super(Properties.of(Material.HEAVY_METAL).lightLevel((p_187435_) -> 14));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof ScrapingStationBlockEntity) {
                ((ScrapingStationBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos,
                                 Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(blockPos);
            if (entity instanceof ScrapingStationBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer) player), (ScrapingStationBlockEntity) entity, blockPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide()) {
            return (lvl, pos, stt, te) -> {
                if (te instanceof ScrapingStationBlockEntity scraper) scraper.tickServer();
            };
        }
        return null;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> SHAPE_N;
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            case EAST -> SHAPE_E;
        };
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new ScrapingStationBlockEntity(blockPos, blockState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
