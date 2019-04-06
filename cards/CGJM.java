package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import patches.AbstractCardEnum;


public class CGJM extends CustomCard {
    public static final String ID = "RemiliaMod:CGJM";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static int COST = 0;
    private static final int ATTACK_DMG = 25;


    public CGJM() {
        this(0);
    }


    public CGJM(final int upgrades) {
        super("RemiliaMod:CGJM", CGJM.NAME, "images/cards/CGJM.png", COST, CGJM.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = CGJM.ATTACK_DMG;
        this.timesUpgraded = upgrades;

        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("RemiliaMod:PFWZL")) {
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
        int b = 0;
        if (m.hasPower("Vulnerable")) {
            b = m.getPower("Vulnerable").amount * 10;
        }
        b = b + this.damage;
        CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_HQ", 0F);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, b, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), 0, true, AbstractGameAction.AttackEffect.NONE));

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
        return new CGJM(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(15);
        this.upgradeMagicNumber(1);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CGJM.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();

    }


    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CGJM");
        NAME = CGJM.cardStrings.NAME;
        DESCRIPTION = CGJM.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CGJM.cardStrings.UPGRADE_DESCRIPTION;
    }
}
