package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.city.BronzeOrb;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import patches.AbstractCardEnum;
import relics.RLLDDJ;
import relics.RXiangDui;

public class CAattack extends CustomCard {
    public static final String ID = "RemiliaMod:CAattack";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 0;
    private static final int ATTACK_DMG = 2 + RXiangDui.Xds;
    private static int cs = 0;

    public CAattack() {
        this(0);
    }

    public CAattack(final int upgrades) {
        super("RemiliaMod:CAattack", CAattack.NAME, "images/cards/CAattack.png", COST, CAattack.DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Remilia_Scarlet_Mod, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = CAattack.ATTACK_DMG;
        this.timesUpgraded = upgrades;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (m != null) {
            //AbstractDungeon.actionManager.addToBottom(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2f));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.cs++;
        //打出X次后加入一张牌到手牌
        if (this.cs % 3 == 0) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CAattack_t(this.timesUpgraded).makeCopy(), 1, false));
            //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CFx().makeCopy(), 1, false));
        }

        //抽一张牌
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        upgrade();

        /*if (p.hasRelic("RemiliaMod:RLLDDJ")) {
        } else {
            System.out.println("没有诅咒遗物，添加");
            //p.relics.add(new RLLDDJ().makeCopy());
            new RLLDDJ().instantObtain();
        }*/
    }

    @Override
    public AbstractCard makeCopy() {
        return new CAattack(this.timesUpgraded);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(2 + this.timesUpgraded);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = CAattack.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();

    }


    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaMod:CAattack");
        NAME = CAattack.cardStrings.NAME;
        DESCRIPTION = CAattack.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = CAattack.cardStrings.UPGRADE_DESCRIPTION;
    }
}
