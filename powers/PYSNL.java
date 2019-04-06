package powers;

import cards.CSX;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class PYSNL extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PYSNL";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PYSNL(final AbstractCreature owner, final int strengthAmount) {
        this.name = PYSNL.NAME;
        this.ID = "RemiliaMod:PYSNL";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PYSNL.png");
    }

    @Override
    public void updateDescription() {
        this.description = PYSNL.DESCRIPTIONS[0] + this.amount + PYSNL.DESCRIPTIONS[1];
    }


    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.owner, new VulnerablePower(mo, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PYSNL");
        NAME = PYSNL.powerStrings.NAME;
        DESCRIPTIONS = PYSNL.powerStrings.DESCRIPTIONS;
    }

}
