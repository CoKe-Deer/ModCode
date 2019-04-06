package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import patches.AbstractCardEnum;
import relics.RXiangDui;


public class CFTAttack extends CustomCard {
    public static final String ID = "RemiliaMod:CFTAttack";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6 + RXiangDui.Xds;

    public CFTAttack() {
        this(1);
    }

    public CFTAttack(final int upgrades) {
        super("RemiliaMod:CFTAttack", CFTAttack.NAME, "images/cards/CFTAttack.png", COST, CFTAttack.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = CFTAttack.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        //this.tags.add(CardTags.STRIKE);

    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), this.magicNumber));
        upgrade();
        //抽一张牌
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));


    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("Flight2")) {
            return true;
        }
        this.cantUseMessage = this.UPGRADE_DESCRIPTION;
        System.out.println(this.UPGRADE_DESCRIPTION);
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new CFTAttack(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(3 + this.timesUpgraded);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CFTAttack.NAME + "+" + this.timesUpgraded;
        this.upgradeBaseCost(0);
        //升级所有A打击代码?
        this.upgradeMagicNumber(1);

        this.initializeTitle();

    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CFTAttack");
        NAME = CFTAttack.cardStrings.NAME;
        DESCRIPTION = CFTAttack.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CFTAttack.cardStrings.UPGRADE_DESCRIPTION;
    }
}
