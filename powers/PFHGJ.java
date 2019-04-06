package powers;

import cards.CSX;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PFHGJ extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PFHGJ";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PFHGJ(final AbstractCreature owner, final int strengthAmount) {
        this.name = PFHGJ.NAME;
        this.ID = "RemiliaMod:PFHGJ";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PFHGJ.png");
    }


    @Override
    public void updateDescription() {
        this.description = PFHGJ.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            action.exhaustCard = true;
        }
    }


    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PFHGJ");
        NAME = PFHGJ.powerStrings.NAME;
        DESCRIPTIONS = PFHGJ.powerStrings.DESCRIPTIONS;
    }

}
