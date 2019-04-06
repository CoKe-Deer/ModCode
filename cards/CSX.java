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

public class CSX extends CustomCard {
    public static final String ID = "RemiliaMod:CSX";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 7;
    private int flightAmt = 1;

    public CSX() {
        this(0);
    }

    public CSX(final int upgrades) {
        super("RemiliaMod:CSX", CSX.NAME, "images/cards/CSX.png", COST, CSX.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int value = Integer.MAX_VALUE-8000;
        AbstractDungeon.player.increaseMaxHp(value, false);

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(6);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CSX(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CSX");
        NAME = CSX.cardStrings.NAME;
        DESCRIPTION = CSX.cardStrings.DESCRIPTION;
    }
}
