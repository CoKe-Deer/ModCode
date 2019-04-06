package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import relics.RXiangDui;


public class CFxAttack extends CustomCard {
    public static final String ID = "RemiliaMod:CFxAttack";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 12 + RXiangDui.Xds;

    public CFxAttack() {
        this(0);
    }

    public CFxAttack(final int upgrades) {
        super("RemiliaMod:CFxAttack", CFxAttack.NAME, "images/cards/CFxAttack.png", COST, CFxAttack.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = CFxAttack.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        //this.tags.add(CardTags.STRIKE);

    }

    public static int countCards() {
        int count = 0;
        for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            if (isStrike(c)) {
                ++count;
            }
        }
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (isStrike(c)) {
                ++count;
            }
        }
        for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (isStrike(c)) {
                ++count;
            }
        }
        return count;
    }

    public static boolean isStrike(final AbstractCard c) {
        return c.hasTag(CardTags.STRIKE);
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
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
        return new CFxAttack(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(3 + this.timesUpgraded);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CFxAttack.NAME + "+" + this.timesUpgraded;
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CFxAttack");
        NAME = CFxAttack.cardStrings.NAME;
        DESCRIPTION = CFxAttack.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CFxAttack.cardStrings.UPGRADE_DESCRIPTION;
    }
}
