package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.PQNXXG;

public class CPXsg extends CustomCard {
    public static final String ID = "RemiliaMod:CPXsg";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 5;
    private int flightAmt = 1;

    public CPXsg() {
        this(0);
    }

    public CPXsg(final int upgrades) {
        super("RemiliaMod:CPXsg", CPXsg.NAME, "images/cards/CPXsg.png", COST, CPXsg.DESCRIPTION, CardType.POWER, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        CardCrawlGame.sound.playA("RemiliaStringsMod:Lancer_BattleStart_2", 0.0F);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PQNXXG(p, this.flightAmt), this.flightAmt));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeBaseCost(4);
            this.upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CPXsg(this.timesUpgraded);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CPXsg");
        NAME = CPXsg.cardStrings.NAME;
        DESCRIPTION = CPXsg.cardStrings.DESCRIPTION;
    }
}
