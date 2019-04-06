package powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PFHFY extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PFHFY";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    final AbstractPlayer p = AbstractDungeon.player;

    public PFHFY(final AbstractCreature owner, final int strengthAmount) {
        this.name = PFHFY.NAME;
        this.ID = "RemiliaMod:PFHFY";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PFHFY.png");
    }


    @Override
    public void updateDescription() {
        this.description = PFHFY.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {

    }

    @Override
    public void onExhaust(final AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 8));
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
    }


    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PFHFY");
        NAME = PFHFY.powerStrings.NAME;
        DESCRIPTIONS = PFHFY.powerStrings.DESCRIPTIONS;
    }

}
