package chimeradownfall.cardmods.hermit;

import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import chimeradownfall.ChimeraDownfallMod;
import hermit.cards.PistolWhip;
import hermit.characters.hermit;
import hermit.powers.Bruise;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HardHittingMod extends AbstractAugment {
    public static final String ID = ChimeraDownfallMod.makeID(HardHittingMod.class.getSimpleName());
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    public static final String[] CARD_TEXT = CardCrawlGame.languagePack.getUIString(ID).EXTRA_TEXT;

    public static final int EFFECT = 2;
    private boolean modifiedBase;

    @Override
    public boolean validCard(AbstractCard card) {
        return characterCheck(p -> p instanceof hermit) && card.cost >= 0 && targetsEnemy(card);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (card instanceof PistolWhip) {
            card.baseMagicNumber += EFFECT;
            card.magicNumber += EFFECT;
            modifiedBase = true;
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (modifiedBase)
            return rawDescription;
        return insertAfterText(rawDescription, String.format(CARD_TEXT[0], EFFECT));
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if (!modifiedBase && target != null)
            addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new Bruise(target, EFFECT)));
    }

    @Override public AugmentRarity getModRarity() {return AugmentRarity.UNCOMMON;}
    @Override public String getPrefix() {return TEXT[0];}
    @Override public String getSuffix() {return TEXT[1];}
    @Override public String getAugmentDescription() {return TEXT[2];}
    @Override public AbstractCardModifier makeCopy() {return new HardHittingMod();}
    @Override public String identifier(AbstractCard card) {return ID;}
}