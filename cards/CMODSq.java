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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import patches.AbstractCardEnum;
import relics.RXiangDui;


public class CMODSq extends CustomCard {
    public static final String ID = "RemiliaMod:CMODSq";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static int COST = 1;
    private static final int ATTACK_DMG = 30 + RXiangDui.Xds;


    public CMODSq() {
        this(0);
    }


    public CMODSq(final int upgrades) {
        super("RemiliaMod:CMODSq", CMODSq.NAME, "images/cards/CMODSq.png", COST, CMODSq.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = CMODSq.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.isMultiDamage = true;
        this.baseMagicNumber = 1 * RXiangDui.Xds;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("RemiliaMod:PFWZL") && p.hasPower("RemiliaMod:PHWYB")) {
            if (p.getPower("RemiliaMod:PFWZL").amount >= 4) {
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

        CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_DSQ", 0F);
        for (int i = 0; i < 4; i++) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        final Remilia remilia = (Remilia) AbstractDungeon.player;
        remilia.PLAssYA(2);

        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));

        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "RemiliaMod:PFWZL", 4));
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
        return new CMODSq(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(CMODSq.ATTACK_DMG  + CMODSq.ATTACK_DMG  + (this.timesUpgraded*4));
        this.upgradeBaseCost(0);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CMODSq.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }


    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CMODSq");
        NAME = CMODSq.cardStrings.NAME;
        DESCRIPTION = CMODSq.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CMODSq.cardStrings.UPGRADE_DESCRIPTION;
    }
}
