package powers;

import cards.CFPLL;
import cards.CFPMJ;
import cards.CMEJNS;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class PQNXXG extends AbstractPower {
    public static final String POWER_ID = "RemiliaMod:PQNXXG";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    final AbstractPlayer p = AbstractDungeon.player;

    public PQNXXG(final AbstractCreature owner, final int strengthAmount) {
        this.name = PQNXXG.NAME;
        this.ID = "RemiliaMod:PQNXXG";
        this.owner = owner;

        this.amount = strengthAmount;

        this.updateDescription();
        this.img = new Texture("images/powers/PQNXXG.png");
    }

    @Override
    public void onAfterUseCard(final AbstractCard usedCard, final UseCardAction action) {
        if (usedCard.type == AbstractCard.CardType.ATTACK) {
            if (p.currentHealth > 0) {
                p.heal(5 + this.amount);
            }
        }
    }

    @Override
    public void updateDescription() {
        String ret = "";
        for (String a : this.DESCRIPTIONS) {
            ret = ret + "\n" + a;
        }
        this.description = ret;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PQNXXG(p, 1), 1));

        AbstractCard s = new CFPLL().makeCopy();
        s.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(s, 1));
        s = new CFPMJ().makeCopy();
        s.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(s, 1));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));

        if (p.hasPower("Strength")) {
            int cs = p.getPower("Strength").amount;
            System.out.println(cs);
            if (cs >= 22) {
                if (!cd) {
                    cd = true;
                    CardCrawlGame.sound.playA("RemiliaStringsMod:MEJNS2", 0F);
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CMEJNS(), 1));
                }
            }
        }
    }

    public static boolean cd = false;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaMod:PQNXXG");
        NAME = PQNXXG.powerStrings.NAME;
        DESCRIPTIONS = PQNXXG.powerStrings.DESCRIPTIONS;
    }

}
