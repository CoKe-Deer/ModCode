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


public class CAattack_t extends CustomCard {
    public static final String ID = "RemiliaMod:CAattack_t";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5 * RXiangDui.Xds;
    private static final int DAMAGE_AMT = 5;
    public static final int BONUS = 2;
    public static final int UPG_BONUS = 3;
    private static int cs = 0;

    public CAattack_t() {
        this(0);
    }

    public CAattack_t(final int upgrades) {
        super("RemiliaMod:CAattack_t", CAattack_t.NAME, "images/cards/CAattack_t.png", COST, CAattack_t.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = CAattack_t.ATTACK_DMG;
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
        for (int i = 0; i < 2; i++) {

            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        upgrade();
        this.cs++;
        //打出X次后加入一张牌到手牌
        if (this.cs % 3 == 0) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CAattack_s(this.timesUpgraded).makeCopy(), 1, false));
        }
        //抽一张牌
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CAattack_t(this.timesUpgraded);
    }

    @Override
    public void upgrade() {

        this.upgradeDamage(3 + this.timesUpgraded);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CAattack_t.NAME + "+" + this.timesUpgraded;
        this.upgradeBaseCost(1);
        //升级所有A打击代码?
        this.upgradeMagicNumber(1);
        this.rawDescription = CAattack_t.UPGRADE_DESCRIPTION;

        this.initializeTitle();

    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CAattack_t");
        NAME = CAattack_t.cardStrings.NAME;
        DESCRIPTION = CAattack_t.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CAattack_t.cardStrings.UPGRADE_DESCRIPTION;
    }
}
