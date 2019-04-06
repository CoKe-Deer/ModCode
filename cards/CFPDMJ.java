package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches.AbstractCardEnum;

public class CFPDMJ extends CustomCard {
    public static final String ID = "RemiliaMod:CFPDMJ";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private int flightAmt = 1;

    public CFPDMJ() {
        this(0);
    }

    public CFPDMJ(final int upgrades) {
        super("RemiliaMod:CFPDMJ", CFPDMJ.NAME, "images/cards/CFPDMJ.png", COST, CFPDMJ.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.baseMagicNumber = flightAmt;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -6), -6));
        //AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Dexterity", 1));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 6), 6));
        //AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "Strength", 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //this.upgradeBaseCost(2);
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CFPDMJ(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CFPDMJ");
        NAME = CFPDMJ.cardStrings.NAME;
        DESCRIPTION = CFPDMJ.cardStrings.DESCRIPTION;
    }
}
