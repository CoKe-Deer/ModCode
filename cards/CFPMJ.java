package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches.AbstractCardEnum;

public class CFPMJ extends CustomCard {
    public static final String ID = "RemiliaMod:CFPMJ";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int flightAmt = 1;

    public CFPMJ() {
        this(0);
    }

    public CFPMJ(final int upgrades) {
        super("RemiliaMod:CFPMJ", CFPMJ.NAME, "images/cards/CFPMJ.png", COST, CFPMJ.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.SELF);
        //this.exhaust = true;
        this.magicNumber = flightAmt;
        this.baseMagicNumber = flightAmt;
        this.isEthereal = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        //RXiangDui.SetZjy(50);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -1), -1));
        //AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Dexterity", 1));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
        //AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Strength", 1));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(0);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CFPMJ(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CFPMJ");
        NAME = CFPMJ.cardStrings.NAME;
        DESCRIPTION = CFPMJ.cardStrings.DESCRIPTION;
    }
}
