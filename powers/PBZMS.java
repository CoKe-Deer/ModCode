package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;

public class PBZMS extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PBZMS";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    final AbstractPlayer p = AbstractDungeon.player;

    public PBZMS(final AbstractCreature owner, final int strengthAmount) {
        this.name = PBZMS.NAME;
        this.ID = "RemiliaMod:PBZMS";
        this.owner = owner;
        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PBZMS.png");
    }

    @Override
    public void onAfterUseCard(final AbstractCard usedCard, final UseCardAction action) {

    }

    @Override
    public void onGainedBlock(final float blockAmount) {

    }

    @Override
    public void updateDescription() {
        String ret = "";
        for (String a : this.DESCRIPTIONS) {
            ret = ret + "\n" + a;
        }
        this.description = ret;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new OfferingEffect(), 0.5f));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 6));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PBZMS(p, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PhantasmalPower(p, 1), 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DoubleTapPower(p, 1), 2));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, 4, false), 4, true, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 4, false), 4, true, AbstractGameAction.AttackEffect.NONE));

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PBZMS");
        NAME = PBZMS.powerStrings.NAME;
        DESCRIPTIONS = PBZMS.powerStrings.DESCRIPTIONS;
    }

}
