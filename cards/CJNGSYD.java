package com.megacrit.cardcrawl.cards.green;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import patches.AbstractCardEnum;

public class CJNGSYD extends CustomCard
{
    public static final String ID = "RemiliaMod:CJNGSYD";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int DRAW = 3;
    private static final int DISCARD = 1;

    public CJNGSYD() {
        super("RemiliaMod:CJNGSYD", CJNGSYD.NAME, "images/cards/CJNGSYD.png", COST, CJNGSYD.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.magicNumber, false));
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
        return new CJNGSYD();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNGSYD");
        NAME = CJNGSYD.cardStrings.NAME;
        DESCRIPTION = CJNGSYD.cardStrings.DESCRIPTION;
    }
}
