package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class PXRNL extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PXRNL";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PXRNL(final AbstractCreature owner, final int strengthAmount) {
        this.name = PXRNL.NAME;
        this.ID = "RemiliaMod:PXRNL";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PXRNL.png");
    }

    @Override
    public void updateDescription() {

        this.description = PXRNL.DESCRIPTIONS[0] + this.amount + PXRNL.DESCRIPTIONS[1];
    }


    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.owner, new WeakPower(mo, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PXRNL");
        NAME = PXRNL.powerStrings.NAME;
        DESCRIPTIONS = PXRNL.powerStrings.DESCRIPTIONS;
    }

}
