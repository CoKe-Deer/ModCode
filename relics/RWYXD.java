package relics;

import basemod.abstracts.CustomRelic;
import cards.CMMDMP;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.Iterator;

public class RWYXD extends CustomRelic {
    public static final String ID = "RemiliaMod:RWYXD";

    public RWYXD() {
        super("RemiliaMod:RWYXD", new Texture("images/relics/RWYXD.png"), RelicTier.BOSS, LandingSound.SOLID);
        this.counter = -1;
    }


    @Override
    public void atPreBattle() {
        this.flash();

    }

    @Override
    public void atBattleStart() {
        this.flash();

        //AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public String getUpdatedDescription() {
        String ret = "";
        for (String a : this.DESCRIPTIONS) {
            ret = ret + "\n" + a;
        }
        //ret=ret+ "\n你当前相对为："+Xds+"\n你当前总经验为："+Zjy;
        return ret;
    }

    @Override
    public void atTurnStart() {
        //this.counter = -1;
    }

    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {

    }


    @Override
    public void onVictory() {

    }


}
