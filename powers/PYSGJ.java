package powers;

import cards.CSX;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class PYSGJ extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PYSGJ";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PYSGJ(final AbstractCreature owner, final int strengthAmount) {
        this.name = PYSGJ.NAME;
        this.ID = "RemiliaMod:PYSGJ";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PYSGJ.png");
    }

    @Override
    public void updateDescription() {
        this.description = PYSGJ.DESCRIPTIONS[0] + this.amount + PYSGJ.DESCRIPTIONS[1];

    }


    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();

    }

    @Override
    public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new VulnerablePower(target, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PYSGJ");
        NAME = PYSGJ.powerStrings.NAME;
        DESCRIPTIONS = PYSGJ.powerStrings.DESCRIPTIONS;
    }

}
