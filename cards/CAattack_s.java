package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import relics.RXiangDui;


public class CAattack_s extends CustomCard
{
    public static final String ID = "RemiliaMod:CAattack_t";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 10* RXiangDui.Xds;
    private static final int DAMAGE_AMT = 5;
    public static final int BONUS = 2;
    public static final int UPG_BONUS = 3;

    public CAattack_s() {
        this(0);
    }

    public CAattack_s(final int upgrades) {
        super("RemiliaMod:CAattack_s", CAattack_s.NAME, "images/cards/CAattack_s.png", COST, CAattack_s.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = CAattack_s.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.exhaust = true;
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
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        upgrade();
        //抽一张牌
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));

    }

    @Override
    public AbstractCard makeCopy() {
        return new CAattack_s(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(3 + this.timesUpgraded+ RXiangDui.Xds);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CAattack_s.NAME + "+" + this.timesUpgraded;
        this.upgradeBaseCost(0);
        //升级所有A打击代码?
        this.upgradeMagicNumber(1);
        this.rawDescription = CAattack_s.UPGRADE_DESCRIPTION;
        this.initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CAattack_s");
        NAME = CAattack_s.cardStrings.NAME;
        DESCRIPTION = CAattack_s.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CAattack_s.cardStrings.UPGRADE_DESCRIPTION;
    }
}
