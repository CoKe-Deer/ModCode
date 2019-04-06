package relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.ModalChoice;
import cards.*;
import characters.Remilia;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import powers.PFWZL;

import java.util.ArrayList;

public class RFkXt extends AbstractClickRelic {
    public static final String ID = "RemiliaMod:RFkXt";

    public RFkXt() {
        super("RemiliaMod:RFkXt", new Texture("images/relics/RFkXt.png"), RelicTier.STARTER, LandingSound.SOLID);

    }

    @Override
    public void onRightClick() {
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PFWZL(AbstractDungeon.player, 1),1));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CPFWZL(), 1, false));
    }

    @Override
    public void atPreBattle() {
        //this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PFWZL(AbstractDungeon.player, 1)));

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
        final Remilia remilia = (Remilia) AbstractDungeon.player;
        remilia.PLAssYA(1);
        this.counter = -1;

    }


    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (card.cardID.indexOf("CMABianfuzhen") == -1) {
            System.out.println("蝙蝠阵判断");
            fkth("CMABianfuzhen");
        }
        if(card.cardID.indexOf("CMADc") == -1){
            System.out.println("DC判断");
            fkth("CMADc");
        }
    }
    private void fkth(String cdst) {
        ArrayList<AbstractCard> bf = new ArrayList<>();
        for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.indexOf(cdst) != -1) {
                bf.add(c);
                if (bf.size() == 3) {
                    break;
                }
            }
        }
        this.counter = bf.size();
        if (bf.size() >= 3) {
            for (final AbstractCard c : bf) {
                // AbstractDungeon.actionManager.addToBottom(new ExhaustAction(c,AbstractDungeon.player,1,false));
                AbstractDungeon.player.hand.moveToExhaustPile(c);
            }
            if (cdst.indexOf("CMABianfuzhen") != -1) {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CMAXSq(), 1, false));

                CardCrawlGame.sound.playA("RemiliaStringsMod:Remilia_MY", MathUtils.random(0.0f, 0.1f));
            }else if(cdst.indexOf("CMADc")!=-1){
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new CMAXBY(), 1, false));
                CardCrawlGame.sound.playA("RemiliaStringsMod:Lancer_BattleStart_1", MathUtils.random(0.0f, 0.1f));
            }

        }
    }

    @Override
    public void onVictory() {
        final Remilia remilia = (Remilia) AbstractDungeon.player;
        remilia.PLAssYA(1);
    }


}
