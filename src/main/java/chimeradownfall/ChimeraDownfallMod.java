package chimeradownfall;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import CardAugments.CardAugmentsMod;
import CardAugments.cardmods.AbstractAugment;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

@SpireInitializer
public class ChimeraDownfallMod implements EditStringsSubscriber, PostInitializeSubscriber {
    public static String modID = "chimeradownfall";

    public static void initialize() {
        BaseMod.subscribe(new ChimeraDownfallMod());
    }

    private static void loadLocalizedStrings(Class<?> stringClass) {
        BaseMod.loadCustomStringsFile(stringClass, modID + "Resources/localization/eng/"+stringClass.getSimpleName()+".json");
    }

    public void receiveEditStrings() {
        loadLocalizedStrings(UIStrings.class);
        loadLocalizedStrings(PowerStrings.class);
    }

    public void receivePostInitialize() {
        new AutoAdd(modID)
            .packageFilter("chimeradownfall.cardmods")
            .any(AbstractAugment.class, (info, abstractAugment) -> CardAugmentsMod.registerAugment(abstractAugment, modID));
    }

    public static String makeID(String str) {
        return modID + ":" + str;
    }
}