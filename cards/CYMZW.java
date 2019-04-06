package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import patches.AbstractCardEnum;

public class CYMZW extends CustomCard {
    public static final String ID = "RemiliaMod:CYMZW";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 8;
    private static final int ATTACK_DMG = 1;//6
    private int flightAmt = 1;


    public CYMZW() {
        super("RemiliaMod:CYMZW", CYMZW.NAME, "images/cards/CYMZW.png", COST, CYMZW.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.ENEMY);
        //this.exhaust = true;
        this.magicNumber = flightAmt;
        this.baseMagicNumber = flightAmt;
        this.baseDamage = CYMZW.ATTACK_DMG;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        CardCrawlGame.sound.playA("RemiliaStringsMod:CYMZW", 0F);
        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2f));
        }
        for (int i = 0; i < 80; i++) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CYMZW.NAME + "+" + this.timesUpgraded;
        if(this.timesUpgraded==1){
            this.rawDescription = "大螺丝并不想理你，并将自己的出场费用加了两";
            this.updateCost(2);
        }else{
            this.rawDescription = "大螺丝感受到了你的诚意，给自己出场费加了三";
            this.updateCost(3);
        }


        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new CYMZW();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CYMZW");
        NAME = CYMZW.cardStrings.NAME;
        DESCRIPTION = CYMZW.cardStrings.DESCRIPTION;
    }
}
