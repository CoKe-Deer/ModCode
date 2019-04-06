package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.PFWZL;

public class CJNWYXD3 extends CustomCard
{
    public static final String ID = "RemiliaMod:CJNWYXD3";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 2;

    public CJNWYXD3() {
        super("RemiliaMod:CJNWYXD3", CJNWYXD3.NAME, "images/cards/CJNWYXD3.png", COST, CJNWYXD3.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 6;
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PFWZL(AbstractDungeon.player, 1), 1));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeBaseCost(1);
            //this.upgradeMagicNumber(1);
            this.upgradeBlock(3);
            this.rawDescription = CJNWYXD3.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CJNWYXD3();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNWYXD3");
        NAME = CJNWYXD3.cardStrings.NAME;
        DESCRIPTION = CJNWYXD3.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CJNWYXD3.cardStrings.UPGRADE_DESCRIPTION;
    }
}
