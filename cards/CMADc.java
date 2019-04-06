package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import relics.RXiangDui;


public class CMADc extends CustomCard {
    public static final String ID = "RemiliaMod:CMADc";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 4;
    private static final int ATTACK_DMG = 1;
    private static final int DAMAGE_AMT = 5;
    public static final int BONUS = 2;
    public static final int UPG_BONUS = 3;

    public CMADc() {
        this(0);
    }

    public CMADc(final int upgrades) {
        super("RemiliaMod:CMADc", CMADc.NAME, "images/cards/CMADc.png", COST, CMADc.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = CMADc.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;

        this.tags.add(CardTags.STRIKE);

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

            for (int i = 0; i < 10; i++) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
            upgrade();
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "RemiliaMod:PFWZL", 1));
            //抽一张牌
            //AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));

    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("RemiliaMod:PFWZL")) {
            if (p.getPower("RemiliaMod:PFWZL").amount >= 1) {
                return true;
            }
        }
        this.cantUseMessage = this.UPGRADE_DESCRIPTION;
        System.out.println(this.UPGRADE_DESCRIPTION);
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new CMADc(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(1 + this.timesUpgraded);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CMADc.NAME + "+" + this.timesUpgraded;
        //this.upgradeBaseCost(1);
        //升级所有A打击代码?

        this.initializeTitle();

    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CMADc");
        NAME = CMADc.cardStrings.NAME;
        DESCRIPTION = CMADc.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CMADc.cardStrings.UPGRADE_DESCRIPTION;
    }
}
