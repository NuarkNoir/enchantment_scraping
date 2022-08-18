package xyz.nuark.enchantmentscraping;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import xyz.nuark.enchantmentscraping.setup.ClientSetup;
import xyz.nuark.enchantmentscraping.setup.ModSetup;
import xyz.nuark.enchantmentscraping.setup.Registration;

@Mod(EnchantmentScraping.MOD_ID)
public class EnchantmentScraping {
    public static final String MOD_ID = "enchantmentscraping";
    public static final Logger LOGGER = LogUtils.getLogger();


    public EnchantmentScraping() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModSetup.setup();
        Registration.register(eventBus);

        eventBus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(ClientSetup::init));
    }
}
