package powers;

import cards.CSX;
import cards.PLY;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import java.util.Iterator;

public class Yakumo_Yukari_Thousand_Cuts extends AbstractPower {
    public static final String POWER_ID = "Yakumo Yukari Thousand Cuts";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public Yakumo_Yukari_Thousand_Cuts(final AbstractCreature owner, final int strengthAmount) {
        this.name = Yakumo_Yukari_Thousand_Cuts.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PLY().makeCopy(), 10, false));
        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/Yakumo_Yukari_Thousand_Cuts.png");
    }

    @Override
    public void updateDescription() {
        this.description = Yakumo_Yukari_Thousand_Cuts.DESCRIPTIONS[0];
    }

    public void atStartOfTurn() {
        //AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Poison", Integer.MAX_VALUE - 8000));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, AbstractDungeon.player, new PoisonPower(this.owner, AbstractDungeon.player, 0),0, AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        this.flash();


        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this.owner, new PoisonPower(AbstractDungeon.player, this.owner, this.amount), this.amount, AbstractGameAction.AttackEffect.POISON));

        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.amount), this.amount));

    }

    @Override
    public void atStartOfTurnPostDraw() {

        this.flash();
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Yakumo Yukari Thousand Cuts");
        NAME = Yakumo_Yukari_Thousand_Cuts.powerStrings.NAME;
        DESCRIPTIONS = Yakumo_Yukari_Thousand_Cuts.powerStrings.DESCRIPTIONS;
    }

}
