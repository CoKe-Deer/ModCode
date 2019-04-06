package powers;

import cards.CFPLL;
import cards.CFPMJ;
import cards.CMEJNS;
import cards.CWYXD;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class PWXXD extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PWXXD";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    final AbstractPlayer p = AbstractDungeon.player;

    public PWXXD(final AbstractCreature owner, final int strengthAmount) {
        this.name = PWXXD.NAME;
        this.ID = "RemiliaMod:PWXXD";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PWXXD.png");
    }

    @Override
    public void onAfterUseCard(final AbstractCard usedCard, final UseCardAction action) {

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
        if (p.hasRelic("RemiliaMod:RWYXD")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PWXXD(p, 1), 1));
        }
        AbstractCard s = new CWYXD().makeCopy();
        s.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(s, this.amount));


    }


    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PWXXD");
        NAME = PWXXD.powerStrings.NAME;
        DESCRIPTIONS = PWXXD.powerStrings.DESCRIPTIONS;
    }

}
