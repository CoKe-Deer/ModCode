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
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import patches.AbstractCardEnum;
import relics.RXiangDui;


public class CJNNPSJ extends CustomCard {
    public static final String ID = "RemiliaMod:CJNNPSJ";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static int COST = 0;
    private static final int ATTACK_DMG = 450;


    public CJNNPSJ() {
        this(0);
    }


    public CJNNPSJ(final int upgrades) {
        super("RemiliaMod:CJNNPSJ", CJNNPSJ.NAME, "images/cards/CJNNPSJ.png", COST, CJNNPSJ.DESCRIPTION, CardType.SKILL, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = CJNNPSJ.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.isMultiDamage = true;
        this.baseMagicNumber = -3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("RemiliaMod:PFWZL")) {
            if (p.getPower("RemiliaMod:PFWZL").amount >= 12) {
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

        CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_nvpsj", 0F);

        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, mo, new StrengthPower(mo, this.magicNumber), this.magicNumber));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 14), 14));

        final Remilia remilia = (Remilia) AbstractDungeon.player;
        remilia.PLAssYA(5);

        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));

        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "RemiliaMod:PFWZL", 12));
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
        return new CJNNPSJ(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(CJNNPSJ.ATTACK_DMG + CJNNPSJ.ATTACK_DMG + (this.timesUpgraded * 4));
        this.upgradeBaseCost(0);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.upgradeMagicNumber(this.magicNumber - 1);
        this.name = CJNNPSJ.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }


    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CJNNPSJ");
        NAME = CJNNPSJ.cardStrings.NAME;
        DESCRIPTION = CJNNPSJ.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CJNNPSJ.cardStrings.UPGRADE_DESCRIPTION;
    }
}
