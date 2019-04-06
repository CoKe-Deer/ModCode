import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomSavable;
import basemod.abstracts.CustomSavableRaw;
import basemod.interfaces.*;

import cards.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.green.CJNGSYD;
import com.megacrit.cardcrawl.cards.red.Corruption;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import event.CDSJ09;
import event.CDSJ10;
import event.CDSJ11;
import monsters.Byz;
import monsters.Smww;
import patches.AbstractCardEnum;
import characters.Remilia;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import patches.RemiliaEnum;
import relics.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static basemod.BaseMod.logger;

@SpireInitializer
public class Remilia_Scarlet_Mod implements EditCharactersSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditRelicsSubscriber, EditCardsSubscriber, PostInitializeSubscriber {


    private static final Color buster;
    private static final String attackCard = "images/512/bg_attack.png";
    private static final String skillCard = "images/512/bg_skill.png";
    private static final String powerCard = "images/512/bg_power.png";
    private static final String energyOrb = "images/512/card_orb.png";
    private static final String attackCardPortrait = "images/1024/bg_attack.png";
    private static final String skillCardPortrait = "images/1024/bg_skill.png";
    private static final String powerCardPortrait = "images/1024/bg_power.png";
    private static final String energyOrbPortrait = "images/1024/card_orb.png";
    private static final String charButton = "images/charSelect/button.png";
    private static final String charPortrait = "images/charSelect/portrait.jpg";

    static {
        buster = CardHelper.getColor(254.0f, 32.0f, 22.0f);
    }

    public Remilia_Scarlet_Mod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.Remilia_Scarlet_Mod, buster, buster, buster, buster, buster, buster, buster, attackCard, skillCard, powerCard, energyOrb, attackCardPortrait, skillCardPortrait, powerCardPortrait, energyOrbPortrait);
        /*try {
            this.addSaveField();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void initialize() throws IOException {
        Remilia_Scarlet_Mod Remilia = new Remilia_Scarlet_Mod();

    }


    private String getLanguageString(final Settings.GameLanguage language) {
        switch (language) {
            case ZHS:
                return "zhs";
            case ZHT: {
                return "zht";
            }
            default: {
                return "eng";
            }
        }
    }

    public void receiveEditStrings() {
        final String languageString = "RemiliaStrings/Language/" + this.getLanguageString(Settings.language);
        final String characterStrings = Gdx.files.internal(String.valueOf(languageString) + "/character.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings((Class) CharacterStrings.class, characterStrings);
        final String relicStrings = Gdx.files.internal(String.valueOf(languageString) + "/relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings((Class) RelicStrings.class, relicStrings);
        final String powerStrings = Gdx.files.internal(String.valueOf(languageString) + "/powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings((Class) PowerStrings.class, powerStrings);
        final String cardStrings = Gdx.files.internal(String.valueOf(languageString) + "/cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings((Class) CardStrings.class, cardStrings);

        final String eventStrings = Gdx.files.internal(String.valueOf(languageString) + "/events.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings((Class) EventStrings.class, eventStrings);

        final String monstersStrings = Gdx.files.internal(String.valueOf(languageString) + "/monsters.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings((Class) MonsterStrings.class, monstersStrings);
    }


    private HashMap<String, Sfx> getSoundsMap() {
        return (HashMap<String, Sfx>) ReflectionHacks.getPrivate((Object) CardCrawlGame.sound, (Class) SoundMaster.class, "map");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("begin editing characters");

        logger.info("add Remilia");
        BaseMod.addCharacter(new Remilia(CardCrawlGame.playerName),
                charButton,
                charPortrait,
                RemiliaEnum.Remilia_Scarlet_Mod);

        logger.info("done editing characters");
    }

    @Override
    public void receiveEditKeywords() {
        final Gson gson = new Gson();
        final String languageString = "RemiliaStrings/Language/" + this.getLanguageString(Settings.language);
        final String keywordStrings = Gdx.files.internal(String.valueOf(languageString) + "/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        final Type typeToken = new TypeToken<Map<String, Keyword>>() {
        }.getType();
        final Map<String, Keyword> keywords = (Map<String, Keyword>) gson.fromJson(keywordStrings, typeToken);
        keywords.forEach((k, v) -> BaseMod.addKeyword(v.NAMES, v.DESCRIPTION));
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Scratch());
        BaseMod.addCard(new Defend());

        //A连段 物理攻击卡
        BaseMod.addCard(new CAattack());
        BaseMod.addCard(new CAattack_t());
        BaseMod.addCard(new CAattack_s());

        //能力卡与特殊攻击卡
        BaseMod.addCard(new CPFWZL()); //白色能力
        BaseMod.addCard(new CPZQ()); //蓝色能力
        BaseMod.addCard(new CFx()); //金色能力

        BaseMod.addCard(new CSlAttack());//白色
        BaseMod.addCard(new CFxAttack());//蓝色
        BaseMod.addCard(new CFTAttack());//金色

        //魔法攻击牌
        BaseMod.addCard(new CMABianfuzhen());//白色
        BaseMod.addCard(new CMADc());//蓝色
        BaseMod.addCard(new CMAXSq());//衍生牌
        BaseMod.addCard(new CMAXBY());//衍生牌
        BaseMod.addCard(new CMODSq());//金色
        BaseMod.addCard(new CMADBY());//金色

        //技能卡
        BaseMod.addCard(new CJNGSYD());//白色
        BaseMod.addCard(new CJNJQFY());//蓝色
        BaseMod.addCard(new CJNFWZL());//金色

        BaseMod.addCard(new CJNSP());//蓝色消耗一张
        BaseMod.addCard(new CJNCJFJ());//金卡消耗三张获得3
        BaseMod.addCard(new CJNHS());//金色 垃圾箱


        //红雾异变
        BaseMod.addCard(new CPHwYb());//蓝色
        BaseMod.addCard(new CHMF());//金色

        //吸血鬼
        BaseMod.addCard(new CPXsg());//金色
        BaseMod.addCard(new CFPMJ());//白色
        BaseMod.addCard(new CFPLL());//白色

        //B系列
        BaseMod.addCard(new CDB());//金色
        BaseMod.addCard(new CD6B());//白色
        BaseMod.addCard(new CJDB());//白色

        //特殊技能牌 双发 伤害加倍
        BaseMod.addCard(new CJNCMSS());//蓝色
        BaseMod.addCard(new CJNSJDF());//金色

        BaseMod.addCard(new CJNYSFZ());//白色测试
        BaseMod.addCard(new CJNXRFZ());//白色测试
        BaseMod.addCard(new CJNLLFZ());//白色测试
        BaseMod.addCard(new CJNMJFZ());//白色测试

        //配合反转效果的能力
        BaseMod.addCard(new CPBZMF());//白色测试
        BaseMod.addCard(new CFPDMJ());//大敏捷测试
        BaseMod.addCard(new CPFPDLL());//大力量测试
        BaseMod.addCard(new CPQHZZ());//毒测试

        //16夜外援
        BaseMod.addCard(new CWYXD());//小刀
        BaseMod.addCard(new CJNWYXD());//白色两把小刀
        BaseMod.addCard(new CJNWYXD2());//蓝色四把小刀 抽一张牌
        BaseMod.addCard(new CJNWYXD3());//金色六把小刀 抽一张牌 一层符文之力
        BaseMod.addCard(new CPWYXD());//金色 无限小刀能力

        BaseMod.addCard(new CJNNPSJ());//金色技能 世界

        //八云紫外援
        BaseMod.addCard(new CBYZLC());//列车

        //姆Q外援
        BaseMod.addCard(new CWYRF());//日符
        BaseMod.addCard(new CHQS());//火球


        //法术水晶和保留手牌
        BaseMod.addCard(new CXXSJ());//
        BaseMod.addCard(new CZXSJ());//
        BaseMod.addCard(new CDXSJ());//
        BaseMod.addCard(new CPJHZZ());//

        //魔炮
        BaseMod.addCard(new CMPA());//
        BaseMod.addCard(new CMPB());//
        BaseMod.addCard(new CMPC());//
        BaseMod.addCard(new CMMDMP());//


        //永江依玖外援
        BaseMod.addCard(new CYYRK());//羽衣若空
        BaseMod.addCard(new CYYRS());//羽衣若时

        //芙兰朵露
        BaseMod.addCard(new CGJM());//目

        //易伤攻击和能力
        BaseMod.addCard(new CPYSGJ());
        BaseMod.addCard(new CPYSNL());
        BaseMod.addCard(new CPXRNL());


        BaseMod.addCard(new CFHGJ());//腐化攻击
        BaseMod.addCard(new CFHFY());//腐化防御

        //彩蛋
        BaseMod.addCard(new CMEJNS());
        BaseMod.addCard(new CWYW());
        BaseMod.addCard(new CSZBS());
        BaseMod.addCard(new CYMZW());
        BaseMod.addCard(new CSX());

        //八云紫BOSS
        BaseMod.addCard(new PLY());
    }


    @Override
    public void receiveEditRelics() {
        // RelicLibrary.add(new RXiangDui());
        try {
            BaseMod.addRelicToCustomPool(new RXiangDui(), AbstractCardEnum.Remilia_Scarlet_Mod);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // RelicLibrary.add(new RFkXt());
        BaseMod.addRelicToCustomPool(new RFkXt(), AbstractCardEnum.Remilia_Scarlet_Mod);

        BaseMod.addRelicToCustomPool(new RHW(), AbstractCardEnum.Remilia_Scarlet_Mod);

        BaseMod.addRelicToCustomPool(new RWYXD(), AbstractCardEnum.Remilia_Scarlet_Mod);

        BaseMod.addRelicToCustomPool(new RWYXT(), AbstractCardEnum.Remilia_Scarlet_Mod);

        BaseMod.addRelicToCustomPool(new RLLDDJ(), AbstractCardEnum.Remilia_Scarlet_Mod);

    }

    @Override
    public void receivePostInitialize() {
        final HashMap<String, Sfx> reflectedMap = this.getSoundsMap();
        reflectedMap.put("RemiliaStringsMod:SELECT", new Sfx("RemiliaStrings/sounds/Remilia_SELECT.ogg"));
        reflectedMap.put("RemiliaStringsMod:Lancer_BattleStart_1", new Sfx("RemiliaStrings/sounds/Remilia_BattleStart_1.ogg"));
        reflectedMap.put("RemiliaStringsMod:Lancer_BattleStart_2", new Sfx("RemiliaStrings/sounds/Remilia_BattleStart_2.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_DSHXX", new Sfx("RemiliaStrings/sounds/Remilia_DSHXX.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_GR", new Sfx("RemiliaStrings/sounds/Remilia_GR.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_HW", new Sfx("RemiliaStrings/sounds/Remilia_HW.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_MY", new Sfx("RemiliaStrings/sounds/Remilia_MY.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_DSQ", new Sfx("RemiliaStrings/sounds/Remilia_DSQ.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_XSQ", new Sfx("RemiliaStrings/sounds/Remilia_XSQ.ogg"));

        reflectedMap.put("RemiliaStringsMod:Remilia_XBY", new Sfx("RemiliaStrings/sounds/Remilia_XBY.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_DBY", new Sfx("RemiliaStrings/sounds/Remilia_DBY.ogg"));


        reflectedMap.put("RemiliaStringsMod:Remilia_YYRS", new Sfx("RemiliaStrings/sounds/Remilia_YYRS.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_HQ", new Sfx("RemiliaStrings/sounds/Remilia_HQ.ogg"));


        reflectedMap.put("RemiliaStringsMod:Remilia_qweq", new Sfx("RemiliaStrings/sounds/Remilia_qweq.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_nvpsj", new Sfx("RemiliaStrings/sounds/Remilia_nvpsj.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_WYRF", new Sfx("RemiliaStrings/sounds/Remilia_WYRF.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_Mp", new Sfx("RemiliaStrings/sounds/Remilia_Mp.ogg"));

        reflectedMap.put("RemiliaStringsMod:MEJNS", new Sfx("RemiliaStrings/sounds/Remilia_MEJNS.ogg"));
        reflectedMap.put("RemiliaStringsMod:MEJNS2", new Sfx("RemiliaStrings/sounds/Remilia_MEJNS2.ogg"));

        reflectedMap.put("RemiliaStringsMod:CWYW", new Sfx("RemiliaStrings/sounds/Remilia_CWYW.ogg"));
        reflectedMap.put("RemiliaStringsMod:CYMZW", new Sfx("RemiliaStrings/sounds/Remilia_CYMZW.ogg"));

        reflectedMap.put("RemiliaStringsMod:LLBSBB", new Sfx("RemiliaStrings/sounds/Remilia_LLBSBB.ogg"));



        reflectedMap.put("RemiliaStringsMod:Remilia_SF", new Sfx("RemiliaStrings/sounds/Remilia_SF.ogg"));
        reflectedMap.put("RemiliaStringsMod:Remilia_yyrs1", new Sfx("RemiliaStrings/sounds/Remilia_yyrs1.ogg"));

        BaseMod.addMonster(Smww.ID, () -> new Smww());

        BaseMod.addMonster(Byz.ID, () -> new Byz());

        BaseMod.addEvent(CDSJ09.ID, CDSJ09.class, Exordium.ID);
        BaseMod.addEvent(CDSJ10.ID, CDSJ10.class, TheBeyond.ID);
        BaseMod.addEvent(CDSJ11.ID, CDSJ11.class, TheBeyond.ID);

        //BaseMod.addEvent(CDSJ10.ID, CDSJ10.class, Exordium.ID);
        //BaseMod.addEvent(CDSJ11.ID, CDSJ11.class, Exordium.ID);


    }

    private void addSaveField() throws IOException {
        final SpireConfig config = new SpireConfig("RemiliaMod", "Common");

        BaseMod.addSaveField("RemiliaMod:RemiliaXDS", (CustomSavableRaw) new CustomSavable<String>() {
            public Class<String> savedType() {
                return String.class;
            }

            public String onSave() {
                String s = String.valueOf(RXiangDui.Zjy);
                config.setInt("Zjy", Integer.valueOf(s));
                try {
                    config.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("保存成功:" + s);
                return s;
            }

            public void onLoad(final String x) {
                if (x == null || x.isEmpty()) {
                    config.setInt("Zjy", 0);
                    try {
                        config.save();
                        System.out.println("初始化完成");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                int w = config.getInt("Zjy");
                System.out.println("读取成功:" + w);

            }
        });
    }
}
