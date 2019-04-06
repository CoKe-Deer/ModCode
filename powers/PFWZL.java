package powers;

import cards.CSX;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PFWZL extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PFWZL";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PFWZL(final AbstractCreature owner, final int strengthAmount) {
        this.name = PFWZL.NAME;
        this.ID = "RemiliaMod:PFWZL";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PFWZL.png");
    }

    @Override
    public void updateDescription() {
        this.description = PFWZL.DESCRIPTIONS[0];
    }

    private boolean sd = false;

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        if (this.amount > 50 && !sd) {
            sd = true;
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CSX(), 1, false));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PFWZL");
        NAME = PFWZL.powerStrings.NAME;
        DESCRIPTIONS = PFWZL.powerStrings.DESCRIPTIONS;
    }

}
