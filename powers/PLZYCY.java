package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PLZYCY extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PLZYCY";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PLZYCY(final AbstractCreature owner, final int strengthAmount) {
        this.name = PLZYCY.NAME;
        this.ID = "RemiliaMod:PLZYCY";
        this.owner = owner;
        this.amount = strengthAmount;
        this.updateDescription();
        this.img = new Texture("images/powers/YCYPower.png");
    }
    @Override
    public void updateDescription() {
        this.description = PLZYCY.DESCRIPTIONS[0] + " NL " + this.amount + PLZYCY.DESCRIPTIONS[1] + " NL " + this.amount + PLZYCY.DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PLZYCY");
        NAME = PLZYCY.powerStrings.NAME;
        DESCRIPTIONS = PLZYCY.powerStrings.DESCRIPTIONS;
    }

}
