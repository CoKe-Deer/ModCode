package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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

public class CMABianfuzhen extends CustomCard {
    public static final String ID = "RemiliaMod:CMABianfuzhen";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static int COST = 1;
    private static final int ATTACK_DMG = 3 * RXiangDui.Xds;

    public CMABianfuzhen() {
        this(0);
    }


    public CMABianfuzhen(final int upgrades) {
        super("RemiliaMod:CMABianfuzhen", CMABianfuzhen.NAME, "images/cards/CMABianfuzhen.png", COST, CMABianfuzhen.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = CMABianfuzhen.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.isMultiDamage = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (m != null) {
            //AbstractDungeon.actionManager.addToBottom(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2f));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        upgrade();
        //打出X次后加入一张牌到手牌
        //if(this.timesUpgraded%8==0){
        //    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CMADc(), 1, false));
        //}
        //抽一张牌
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CMABianfuzhen(this.timesUpgraded);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(2 + this.timesUpgraded);
        //this.upgradeBaseCost(0);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CMABianfuzhen.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();


    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CMABianfuzhen");
        NAME = CMABianfuzhen.cardStrings.NAME;
        DESCRIPTION = CMABianfuzhen.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CMABianfuzhen.cardStrings.UPGRADE_DESCRIPTION;
    }
}
