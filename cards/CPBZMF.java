package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower2;
import patches.AbstractCardEnum;
import powers.PBZMS;

public class CPBZMF extends CustomCard {
    public static final String ID = "RemiliaMod:CPBZMF";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 4;
    private int flightAmt = 1;

    public CPBZMF() {
        this(0);
    }

    public CPBZMF(final int upgrades) {
        super("RemiliaMod:CPBZMF", CPBZMF.NAME, "images/cards/CPBZMF.png", COST, CPBZMF.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        CardCrawlGame.sound.playA("RemiliaStringsMod:LLBSBB", 0F);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PBZMS(p, this.flightAmt), this.flightAmt));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(3);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CPBZMF(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPBZMF");
        NAME = CPBZMF.cardStrings.NAME;
        DESCRIPTION = CPBZMF.cardStrings.DESCRIPTION;
    }
}
