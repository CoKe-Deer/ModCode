package powers;

import cards.CFPLL;
import cards.CFPMJ;
import cards.CMEJNS;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class PLLDDJ extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PLLDDJ";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    final AbstractPlayer p = AbstractDungeon.player;

    public PLLDDJ(final AbstractCreature owner, final int strengthAmount) {
        this.name = PLLDDJ.NAME;
        this.ID = "RemiliaMod:PLLDDJ";
        this.owner = owner;
        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PLLDDJ.png");
    }

    @Override
    public void onAfterUseCard(final AbstractCard usedCard, final UseCardAction action) {

    }

    @Override
    public void onGainedBlock(final float blockAmount) {
        /*if (blockAmount > 0.0f) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
            for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, mo, new StrengthPower(mo, 1), 1));
            }
        }*/
    }

    @Override
    public void updateDescription() {
        String ret = "";
        for (String a : this.DESCRIPTIONS) {
            ret = ret + " NL " + a;
        }
        this.description = ret;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PLLDDJ(p, 1), 1));

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PQNXXG");
        NAME = PLLDDJ.powerStrings.NAME;
        DESCRIPTIONS = PLLDDJ.powerStrings.DESCRIPTIONS;
    }

}
