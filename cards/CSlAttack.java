package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
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


public class CSlAttack extends CustomCard {
    public static final String ID = "RemiliaMod:CSlAttack";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 2 + RXiangDui.Xds;

    public CSlAttack() {
        this(0);
    }

    public CSlAttack(final int upgrades) {
        super("RemiliaMod:CSlAttack", CSlAttack.NAME, "images/cards/CSlAttack.png", COST, CSlAttack.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = CSlAttack.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        //this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, p));
        for (int i = 0; i < 3; i++) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        upgrade();

    }

    @Override
    public AbstractCard makeCopy() {
        return new CSlAttack(this.timesUpgraded);
    }

    @Override
    public void upgrade() {

        this.upgradeDamage(2 + this.timesUpgraded);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CSlAttack.NAME + "+" + this.timesUpgraded;
        //this.upgradeBaseCost(1);
        //升级所有A打击代码?
        this.upgradeMagicNumber(1);
        this.initializeTitle();

    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CSlAttack");
        NAME = CSlAttack.cardStrings.NAME;
        DESCRIPTION = CSlAttack.cardStrings.DESCRIPTION;
    }
}
