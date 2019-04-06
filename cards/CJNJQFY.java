package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;

public class CJNJQFY extends CustomCard
{
    public static final String ID = "RemiliaMod:CJNJQFY";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int DRAW = 2;
    private static final int DISCARD = 1;

    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public CJNJQFY() {
        super("RemiliaMod:CJNJQFY", CJNJQFY.NAME, "images/cards/CJNJQFY.png", COST, CJNJQFY.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        final int n = CJNJQFY.BLOCK;
        this.baseBlock = n;
        this.block = n;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 1, false));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeBaseCost(1);
            //this.upgradeMagicNumber(1);
            this.upgradeBlock(3);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CJNJQFY();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNJQFY");
        NAME = CJNJQFY.cardStrings.NAME;
        DESCRIPTION = CJNJQFY.cardStrings.DESCRIPTION;
    }
}
