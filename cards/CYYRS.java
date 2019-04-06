package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.EchoPower;
import patches.AbstractCardEnum;


public class CYYRS extends CustomCard {
    public static final String ID = "RemiliaMod:CYYRS";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static int COST = 0;
    private static final int ATTACK_DMG =2;


    public CYYRS() {
        this(0);
    }


    public CYYRS(final int upgrades) {
        super("RemiliaMod:CYYRS", CYYRS.NAME, "images/cards/CYYRS.png", COST, CYYRS.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.SELF);
        this.baseDamage = CYYRS.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("RemiliaMod:PFWZL") ) {
            if (p.getPower("RemiliaMod:PFWZL").amount >= 5) {
                return true;
            }
        }
        this.cantUseMessage = this.UPGRADE_DESCRIPTION;
        System.out.println(this.UPGRADE_DESCRIPTION);
        return false;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (m != null) {
            //AbstractDungeon.actionManager.addToBottom(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2f));
        }

        CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_yyrs1", 0F);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EchoPower(p, this.damage), 1));

        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "RemiliaMod:PFWZL", 5));


        //kiyohime.PLAssYA(Remilia.MY_CHARACTER_SKELETON_ATLAS,Remilia.MY_CHARACTER_SKELETON_JSON,0.8F);

        //打出X次后加入一张牌到手牌
        //if(this.timesUpgraded%8==0){
        //    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CMADc(), 1, false));
        //}
        //抽一张牌

    }

    @Override
    public AbstractCard makeCopy() {
        return new CYYRS(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeMagicNumber(2);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CYYRS.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();

    }


    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CYYRS");
        NAME = CYYRS.cardStrings.NAME;
        DESCRIPTION = CYYRS.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CYYRS.cardStrings.UPGRADE_DESCRIPTION;
    }
}
