package characters;

import java.util.ArrayList;

import basemod.abstracts.*;
import cards.Defend;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import patches.AbstractCardEnum;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.*;
import basemod.animations.*;
import com.megacrit.cardcrawl.core.*;
import com.badlogic.gdx.math.*;

import com.megacrit.cardcrawl.screens.*;
import com.megacrit.cardcrawl.characters.*;

import patches.RemiliaEnum;
import relics.RXiangDui;


public class Remilia extends CustomPlayer {
    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String MY_CHARACTER_SHOULDER_2 = "images/Remilia/shoulder.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "images/Remilia/shoulder2.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "images/Remilia/corpse.png"; // dead corpse


    private static final String ID = "Remilia_Scarlet_Mod:Remilia";
    private static final CharacterStrings characterStrings;
    private static final Color buster;
    public static final String[] orbTextures;

    public int cf = 0;

    //public AbstractServant Servant;
    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString("Remilia_Scarlet_Mod:Remilia");
        buster = CardHelper.getColor(254.0f, 32.0f, 22.0f);
        orbTextures = new String[]{"images/Remilia/orb/layer1.png", "images/Remilia/orb/layer2.png", "images/Remilia/orb/layer3.png", "images/Remilia/orb/layer4.png", "images/Remilia/orb/layer5.png", "images/Remilia/orb/layer6.png", "images/Remilia/orb/layer1d.png", "images/Remilia/orb/layer2d.png", "images/Remilia/orb/layer3d.png", "images/Remilia/orb/layer4d.png", "images/Remilia/orb/layer5d.png"};
    }


    public Remilia(final String name) {
        //super(name,RemiliaEnum.);
        super(name, RemiliaEnum.Remilia_Scarlet_Mod, orbTextures, "images/Remilia/orb/vfx.png", (String) null, (String) null);
        //super(name, RemiliaEnum.MOD_NAME_COLOR, orbTextures, "images/Remilia/orb/layer2.png", new SpriterAnimation("images/Remilia/idle/Kiyohime.scml"));
        //super(name, RemiliaEnum.Remilia, orbTextures, "images/Remilia/orb/layer2.png", new G3DJAnimation(MY_CHARACTER_SKELETON_JSON));

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 0.0F, 5.0F, 240.0f, 300.0f, new EnergyManager(ENERGY_PER_TURN));
        //this.img = ImageMaster.loadImage( "images/Remilia/animate/XSQ/zz_0017_1.png");
        PLAssYA(1);
        //this.img = ImageMaster.loadImage( "images/Remilia/animate/XSQ/zz_0017_1.png");

    }

    public void PLAssYA(int a) {
        try {
            System.out.println("正在获取CF" + this.cf);
            if (a == this.cf) {
                System.out.println("==a");
                return;
            }
            float scele = 0.8F;
            AnimationState.TrackEntry e = null;
            String MY_CHARACTER_SKELETON_ATLAS = "images/Remilia/animate/Remilia.atlas"; // spine animation atlas
            String MY_CHARACTER_SKELETON_JSON = ""; // spine animation json
            if (a == 1) {
                MY_CHARACTER_SKELETON_ATLAS = "images/Remilia/animate/Remiliazz2.atlas";
                MY_CHARACTER_SKELETON_JSON = "images/Remilia/animate/Remiliazz2.json";
                scele = 0.3F;
            } else if (a == 2) {
                MY_CHARACTER_SKELETON_JSON = "images/Remilia/animate/Remilia_RemiliaXsq.json";
            } else if (a == 3) {
                MY_CHARACTER_SKELETON_ATLAS = "images/Remilia/animate/Remilia_byc.atlas";
                MY_CHARACTER_SKELETON_JSON = "images/Remilia/animate/Remilia_byc.json";
                scele = 0.4F;
            } else if (a == 4) {
                MY_CHARACTER_SKELETON_ATLAS = "images/Remilia/animate/Remilia_BYZLC.atlas";
                MY_CHARACTER_SKELETON_JSON = "images/Remilia/animate/Remilia_BYZLC.json";
                scele = 0.9F;
                //System.out.println("载入4动画");
            } else if (a == 5) {
                MY_CHARACTER_SKELETON_ATLAS = "images/Remilia/animate/Remilia_WYSJ.atlas";
                MY_CHARACTER_SKELETON_JSON = "images/Remilia/animate/Remilia_WYSJ.json";
                scele = 0.18F;
                //System.out.println("载入4动画");
            }else if (a == 6) {
                MY_CHARACTER_SKELETON_ATLAS = "images/Remilia/animate/Remilia_WYRF.atlas";
                MY_CHARACTER_SKELETON_JSON = "images/Remilia/animate/Remilia_WYRF.json";
                scele = 0.1F;
                //System.out.println("载入4动画");
            }else if (a == 7) {
                MY_CHARACTER_SKELETON_ATLAS = "images/Remilia/animate/Remilia_MP.atlas";
                MY_CHARACTER_SKELETON_JSON = "images/Remilia/animate/Remilia_MP.json";
                scele = 0.26F;
                //System.out.println("载入4动画");
            }
            loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, scele); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
            //this.loadAnimation("images/Remilia/animate/Remilia_wzz.atlas", "images/Remilia/animate/Remilia_wzz.json", 1.6F);
            //e = this.state.setAnimation(0, "Sprite", true);
            if (a == 1) {
                e = this.state.setAnimation(0, "Sprite", true);
            } else if (a == 2) {
                e = this.state.setAnimation(0, "RemiliaXsq", true);
            } else if (a == 3) {
                e = this.state.setAnimation(0, "Sprite", true);
                //e = this.state.addAnimation(0, "Sprite",true,1);
            } else if (a == 4) {
                e = this.state.setAnimation(0, "Sprite", true);
                //System.out.println("载入4动画2");
            } else if (a == 5) {
                e = this.state.setAnimation(0, "Sprite", false);
                //System.out.println("载入4动画2");
            }else if (a == 6) {
                e = this.state.setAnimation(0, "Sprite", true);
                //System.out.println("载入4动画2");
            }else if (a == 7) {
                e = this.state.setAnimation(0, "Sprite", true);
                //System.out.println("载入4动画2");
            }
            e.setTime(e.getEndTime() * MathUtils.random());
            System.out.println("！=a");
            cf = a;
        } catch (Exception e) {
            System.out.println("恭喜你，遇到了百年难得一遇的问题，你是核心玩家");
            e.printStackTrace();
        }
    }
/*
    public void changeAbstractServant(int a) {
       switch (a){
           case 1:
               this.animation= new SpriterAnimation("Kiyohime/images/char/idle/Kiyohime_Lancer.scml";
               break;
           case 2:
               this.animation= new SpriterAnimation("Kiyohime/images/char/idle/Kiyohime_Lancer2.scml";
               break;
        }
        this.animation = this.Servant.animation;
    }*/


    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("RemiliaMod:Scratch");
        retVal.add("RemiliaMod:Scratch");
        retVal.add("RemiliaMod:Scratch");
        retVal.add("RemiliaMod:Scratch");
        retVal.add("RemiliaMod:Defend");
        retVal.add("RemiliaMod:Defend");
        retVal.add("RemiliaMod:Defend");
        retVal.add("RemiliaMod:Defend");
        retVal.add("RemiliaMod:CAattack");
        /*
        retVal.add("RemiliaMod:CFxAttack");
        retVal.add("RemiliaMod:CMAXBY");
        retVal.add("RemiliaMod:CMAXSq");
        retVal.add("RemiliaMod:CMODSq");
        retVal.add("RemiliaMod:CJDB");
        retVal.add("RemiliaMod:CFTAttack");
        retVal.add("RemiliaMod:CPHwYb");
        retVal.add("RemiliaMod:CFx");*/
        //for (int i = 0; i < 3; i++) {
        //     retVal.add("RemiliaMod:CMADc");
        //     retVal.add("RemiliaMod:CMABianfuzhen");
        // }
        //retVal.add("RemiliaMod:CYMZW");
        //retVal.add("RemiliaMod:CWYW");
        /*retVal.add("RemiliaMod:CJNLLFZ");
        retVal.add("RemiliaMod:CJNMJFZ");
        retVal.add("RemiliaMod:CJNYSFZ");
        retVal.add("RemiliaMod:CJNXRFZ");
        retVal.add("RemiliaMod:CJNCMSS");
        retVal.add("RemiliaMod:CJNSJDF");
        retVal.add("RemiliaMod:CWYRF");
        retVal.add("RemiliaMod:CBYZLC");
       retVal.add("RemiliaMod:CMPA");
       retVal.add("RemiliaMod:CMPA");
        retVal.add("RemiliaMod:CGJM");
        retVal.add("RemiliaMod:CPYSGJ");
        retVal.add("RemiliaMod:CPYSNL");
        retVal.add("RemiliaMod:CYYRS");*/
        return retVal;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        //retVal.add("TheAbacus");
        //UnlockTracker.markRelicAsSeen("TheAbacus");
        retVal.add("RemiliaMod:RXiangDui");
        retVal.add("RemiliaMod:RFkXt");
        retVal.add("RemiliaMod:RWYXT");
        UnlockTracker.markRelicAsSeen("RemiliaMod:RWYXT");

        retVal.add("Juzu Bracelet");
        UnlockTracker.markRelicAsSeen("Juzu Bracelet");

        //retVal.add("RemiliaMod:RLLDDJ");
        //UnlockTracker.markRelicAsSeen("RemiliaMod:RLLDDJ");
        UnlockTracker.markRelicAsSeen("RemiliaMod:RXiangDui");
        UnlockTracker.markRelicAsSeen("RemiliaMod:RFkXt");
        return retVal;
    }

    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;

    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo(Remilia.characterStrings.NAMES[0], Remilia.characterStrings.TEXT[0],
                STARTING_HP, MAX_HP, 0, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    public String getTitle(final AbstractPlayer.PlayerClass arg0) {
        return Remilia.characterStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.Remilia_Scarlet_Mod;
    }

    @Override
    public Color getCardRenderColor() {
        return buster;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Defend();
    }

    @Override
    public Color getCardTrailColor() {
        return buster;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("RemiliaStringsMod:SELECT", MathUtils.random(0.0f, 0.1f));
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "RemiliaStringsMod:SELECT";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Remilia(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return buster;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    @Override
    public String getVampireText() {
        return "在一条昏暗的街上，你遇见几个戴着兜帽的人在进行某种黑暗的仪式。当你靠近时，他们全都同时转身面对你，让你觉得十分诡异。 其中个子最高的一个微微一笑，露出了长长的尖牙，向你伸出了一只苍白而瘦长的手： NL ~“给我滚，你已经是吸血鬼了。”，你不明白怎么回事，只好选择离开~";
    }

}