package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches.AbstractCardEnum;

public class CWYW extends CustomCard {
    public static final String ID = "RemiliaMod:CWYW";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int flightAmt = 1;


    public CWYW() {
        super("RemiliaMod:CWYW", CWYW.NAME, "images/cards/CWYW.png", COST, CWYW.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.SELF);
        //this.exhaust = true;
        this.magicNumber = flightAmt;
        this.baseMagicNumber = flightAmt;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        CardCrawlGame.sound.playA("RemiliaStringsMod:CWYW", 0F);

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction( new CSZBS(), 1, false));

    }

    @Override
    public void upgrade() {
        this.upgradeName();
        this.upgradeBaseCost(0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CWYW();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CWYW");
        NAME = CWYW.cardStrings.NAME;
        DESCRIPTION = CWYW.cardStrings.DESCRIPTION;
    }
}
