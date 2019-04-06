package powers;

import cards.CHMF;
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

public class PHWYB extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PHWYB";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    final AbstractPlayer p = AbstractDungeon.player;

    public PHWYB(final AbstractCreature owner, final int strengthAmount) {
        this.name = PHWYB.NAME;
        this.ID = "RemiliaMod:PHWYB";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PHWYB.png");
    }

    @Override
    public void onAfterUseCard(final AbstractCard usedCard, final UseCardAction action) {
        if (usedCard.type == AbstractCard.CardType.ATTACK) {
            if (p.currentHealth > 0) {
                p.heal(1);
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = PHWYB.DESCRIPTIONS[0] + "\n" + PHWYB.DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        if (p.hasRelic("RemiliaMod:RHW")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PHWYB(p, 1), 1));
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CHMF(), this.amount, false));

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PHWYB");
        NAME = PHWYB.powerStrings.NAME;
        DESCRIPTIONS = PHWYB.powerStrings.DESCRIPTIONS;
    }

}
