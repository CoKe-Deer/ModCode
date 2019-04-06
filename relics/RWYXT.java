package relics;

import cards.CMAXBY;
import cards.CMAXSq;
import cards.CMMDMP;
import cards.CPFWZL;
import characters.Remilia;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import powers.PFWZL;

import java.util.ArrayList;
import java.util.Iterator;

public class RWYXT extends AbstractClickRelic {
    public static final String ID = "RemiliaMod:RWYXT";

    public RWYXT() {
        super("RemiliaMod:RWYXT", new Texture("images/relics/RWYXT.png"), RelicTier.STARTER, LandingSound.SOLID);
        this.counter = 0;
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void atPreBattle() {
        final Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator();
        while (i.hasNext()) {
            final AbstractCard e = i.next();
            if (e.cardID.equals("RemiliaMod:CMPA") || e.cardID.equals("RemiliaMod:CMPB") || e.cardID.equals("RemiliaMod:CMPC")) {
                i.remove();
                ++this.counter;
            }
        }
        if (this.counter >= 5) {
            final AbstractCard c = new CMMDMP();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(CardLibrary.getCopy(c.cardID), Settings.WIDTH / 2, Settings.HEIGHT / 2));

            this.counter = 0;
        }
    }

    @Override
    public String getUpdatedDescription() {
        String ret = "";
        for (String a : this.DESCRIPTIONS) {
            ret = ret + "\n" +
                    "\n" +
                    "\r  " + a;
        }
        //ret=ret+ "\n你当前相对为："+Xds+"\n你当前总经验为："+Zjy;
        return ret;
    }

    @Override
    public void atTurnStart() {

    }

    private static int cou = 0;

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (card.cardID.indexOf("CWYXD") != -1) {
            cou++;
        }
        if (cou == 3) {
            if (AbstractDungeon.player.currentHealth > 0) {
                AbstractDungeon.player.heal(1);
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PFWZL(AbstractDungeon.player, 2), 2));
            cou = 0;
        }

    }


    @Override
    public void onVictory() {

    }


}
