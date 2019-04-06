package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import patches.AbstractCardEnum;

public class CMEJNS extends CustomCard {
    public static final String ID = "RemiliaMod:CMEJNS";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 9;
    private int flightAmt = 1;


    public CMEJNS() {
        super("RemiliaMod:CMEJNS", CMEJNS.NAME, "images/cards/CMEJNS.png", COST, CMEJNS.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.magicNumber = flightAmt;
        this.baseMagicNumber = flightAmt;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        CardCrawlGame.sound.playA("RemiliaStringsMod:MEJNS", 0F);

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BarricadePower(p)));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 820));
        AbstractDungeon.player.increaseMaxHp(10, false);
        //AbstractDungeon.actionManager.addToBottom(p,new IntangiblePlayerPower(p,3280),3280);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 3280),3280));

        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new StrengthPower(mo, -999), -999, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        this.upgradeBaseCost(8);
    }

    @Override
    public AbstractCard makeCopy() {
        return new CMEJNS();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CMEJNS");
        NAME = CMEJNS.cardStrings.NAME;
        DESCRIPTION = CMEJNS.cardStrings.DESCRIPTION;
    }
}
