package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;

public class CJNWYXD2 extends CustomCard
{
    public static final String ID = "RemiliaMod:CJNWYXD2";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 2;
    private static final int DRAW = 2;
    private static final int DISCARD = 1;

    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public CJNWYXD2() {
        super("RemiliaMod:CJNWYXD2", CJNWYXD2.NAME, "images/cards/CJNWYXD2.png", COST, CJNWYXD2.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 6;
        final int n = CJNWYXD2.BLOCK;
        this.baseBlock = n;
        this.block = n;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust=true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractCard s = new CWYXD().makeCopy();
        if (!this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(s, this.magicNumber));
        }else{
            s.upgrade();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(s, this.magicNumber));
        }
        //抽一张牌
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeBaseCost(1);
            //this.upgradeMagicNumber(1);
            this.upgradeBlock(3);
            this.rawDescription = CJNWYXD2.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CJNWYXD2();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNWYXD2");
        NAME = CJNWYXD2.cardStrings.NAME;
        DESCRIPTION = CJNWYXD2.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CJNWYXD2.cardStrings.UPGRADE_DESCRIPTION;
    }
}
