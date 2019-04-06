package cards;

import basemod.abstracts.CustomCard;
import characters.Remilia;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import patches.AbstractCardEnum;
import relics.RXiangDui;


public class CMMDMP extends CustomCard {
    public static final String ID = "RemiliaMod:CMMDMP";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static int COST = 0;
    private static final int ATTACK_DMG =2;


    public CMMDMP() {
        this(0);
    }


    public CMMDMP(final int upgrades) {
        super("RemiliaMod:CMMDMP", CMMDMP.NAME, "images/cards/CMMDMP.png", COST, CMMDMP.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = CMMDMP.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.isMultiDamage = true;
        this.baseMagicNumber = 1 * RXiangDui.Xds;
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

        CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_Mp", 0F);
        for (int i = 0; i < 100; i++) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1),1));

        final Remilia remilia = (Remilia) AbstractDungeon.player;
        remilia.PLAssYA(7);

        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));

        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "RemiliaMod:PFWZL", 5));
        this.upgrade();


        //kiyohime.PLAssYA(Remilia.MY_CHARACTER_SKELETON_ATLAS,Remilia.MY_CHARACTER_SKELETON_JSON,0.8F);

        //打出X次后加入一张牌到手牌
        //if(this.timesUpgraded%8==0){
        //    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CMADc(), 1, false));
        //}
        //抽一张牌

    }

    @Override
    public AbstractCard makeCopy() {
        return new CMMDMP(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(CMMDMP.ATTACK_DMG  + CMMDMP.ATTACK_DMG  + (this.timesUpgraded*4));
        this.upgradeBaseCost(0);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CMMDMP.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }


    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CMMDMP");
        NAME = CMMDMP.cardStrings.NAME;
        DESCRIPTION = CMMDMP.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CMMDMP.cardStrings.UPGRADE_DESCRIPTION;
    }
}
