package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;

public class CJNCJFJ extends CustomCard {
    public static final String ID = "RemiliaMod:CJNCJFJ";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int DRAW = 3;
    private static final int DISCARD = 1;

    public CJNCJFJ() {
        super("RemiliaMod:CJNCJFJ", CJNCJFJ.NAME, "images/cards/CJNCJFJ.png", COST, CJNCJFJ.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hand.size() >= 4) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 3, false));
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(3));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CJNCJFJ();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNCJFJ");
        NAME = CJNCJFJ.cardStrings.NAME;
        DESCRIPTION = CJNCJFJ.cardStrings.DESCRIPTION;
    }
}
