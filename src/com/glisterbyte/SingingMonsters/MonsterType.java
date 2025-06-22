package com.glisterbyte.SingingMonsters;

import com.glisterbyte.Localization.Language;
import com.glisterbyte.Localization.LocalizedResources;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsMonsterInfo;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsMonsterLike;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MonsterType {

    private final int monsterId;
    private final int entityId;

    private final MultiCurrencyValue cost;
    private final Size size;
    private final Duration hatchDuration;
    private final String nameCode;
    private final String descCode;
    private final List<Integer> likedEntityIds;

    private final List<Element> elements;
    private final int hatchXpReward;
    private final int bedsRequired;
    private final List<String> defaultNames;

    public MonsterType(SfsMonsterInfo sfsInfo) {

        monsterId = sfsInfo.monsterId;
        entityId = sfsInfo.entityId;

        cost = new MultiCurrencyValue(
                sfsInfo.costCoins,
                sfsInfo.costKeys,
                sfsInfo.costRelics,
                sfsInfo.costDiamonds,
                sfsInfo.costStarpower,
                sfsInfo.costMedals,
                sfsInfo.costEthCurrency
        );

        size = new Size(sfsInfo.sizeX, sfsInfo.sizeY);
        hatchDuration = Duration.ofSeconds(sfsInfo.buildTime);
        nameCode = sfsInfo.name;
        descCode = sfsInfo.description;

        likedEntityIds = new ArrayList<>();
        for (SfsMonsterLike like : sfsInfo.happiness) {
            likedEntityIds.add(like.entity);
        }

        elements = new ArrayList<>();
        for (int i = 0; i < sfsInfo.genes.length(); i++) {
            char letter = sfsInfo.genes.charAt(i);
            Element element = Cache.getElementByLetter(letter);
            if (element == null) {
                throw new ElementNotFoundException(String.valueOf(letter));
            }
            elements.add(element);
        }

        hatchXpReward = sfsInfo.xp;
        bedsRequired = sfsInfo.beds;

        defaultNames = new ArrayList<>();
        defaultNames.addAll(sfsInfo.names);

    }

    public static MonsterType fromId(int typeId) {
        return Cache.getMonsterTypeByTypeId(typeId);
    }

    public int getTypeId() {
        return monsterId;
    }

    public int getEntityId() {
        return entityId;
    }

    public MultiCurrencyValue getCost() {
        return cost;
    }

    public Size getSize() {
        return size;
    }

    public Duration getHatchDuration() {
        return hatchDuration;
    }

    public String getName(Language language) {
        return LocalizedResources.getText(nameCode, language);
    }

    public String getName() {
        return LocalizedResources.getText(nameCode);
    }

    public String getDescription(Language language) {
        return LocalizedResources.getText(descCode, language);
    }

    public String getDescription() {
        return LocalizedResources.getText(descCode);
    }

    public List<Object> getLikes() {
        List<Object> likedEntities = new ArrayList<>();
        for (int entityId : likedEntityIds) {
            likedEntities.add(Cache.getEntityByEntityId(entityId));
        }
        return likedEntities;
    }

    public List<StructureType> getLikedStructures() {
        List<StructureType> likedStructures = new ArrayList<>();
        for (Object obj : getLikes()) {
            if (obj instanceof StructureType) {
                likedStructures.add((StructureType)obj);
            }
        }
        return likedStructures;
    }

    public List<MonsterType> getLikedMonsters() {
        List<MonsterType> likedMonsters = new ArrayList<>();
        for (Object obj : getLikes()) {
            if (obj instanceof MonsterType) {
                likedMonsters.add((MonsterType)obj);
            }
        }
        return likedMonsters;
    }

    public List<Element> getElements() {
        return elements;
    }

    public int getHatchXpReward() {
        return hatchXpReward;
    }

    public int getBedsRequired() {
        return bedsRequired;
    }

    public List<String> getDefaultNames() {
        return defaultNames;
    }

    // The following is auto-generated via misc/java-info-gen/monsters.py.

    public static MonsterType tweedle() {
        return fromId(1);
    }

    public static MonsterType potbelly() {
        return fromId(2);
    }

    public static MonsterType noggin() {
        return fromId(3);
    }

    public static MonsterType toeJammer() {
        return fromId(4);
    }

    public static MonsterType mammott() {
        return fromId(5);
    }

    public static MonsterType dandidoo() {
        return fromId(6);
    }

    public static MonsterType cybop() {
        return fromId(7);
    }

    public static MonsterType quibble() {
        return fromId(8);
    }

    public static MonsterType pango() {
        return fromId(9);
    }

    public static MonsterType shrubb() {
        return fromId(10);
    }

    public static MonsterType oaktopus() {
        return fromId(11);
    }

    public static MonsterType furcorn() {
        return fromId(12);
    }

    public static MonsterType fwog() {
        return fromId(13);
    }

    public static MonsterType drumpler() {
        return fromId(14);
    }

    public static MonsterType maw() {
        return fromId(15);
    }

    public static MonsterType reedling() {
        return fromId(16);
    }

    public static MonsterType spunge() {
        return fromId(17);
    }

    public static MonsterType thumpies() {
        return fromId(18);
    }

    public static MonsterType pompom() {
        return fromId(19);
    }

    public static MonsterType congle() {
        return fromId(20);
    }

    public static MonsterType pummel() {
        return fromId(21);
    }

    public static MonsterType clamble() {
        return fromId(22);
    }

    public static MonsterType bowgart() {
        return fromId(23);
    }

    public static MonsterType trox() {
        return fromId(24);
    }

    public static MonsterType shellbeat() {
        return fromId(25);
    }

    public static MonsterType quarrister() {
        return fromId(26);
    }

    public static MonsterType deedge() {
        return fromId(27);
    }

    public static MonsterType riff() {
        return fromId(28);
    }

    public static MonsterType entbrat() {
        return fromId(29);
    }

    public static MonsterType mimic() {
        return fromId(30);
    }

    public static MonsterType scups() {
        return fromId(31);
    }

    public static MonsterType punkleton() {
        return fromId(32);
    }

    public static MonsterType yool() {
        return fromId(33);
    }

    public static MonsterType schmoochle() {
        return fromId(34);
    }

    public static MonsterType wubboxPlantIsland() {
        return fromId(35);
    }

    public static MonsterType wubboxColdIsland() {
        return fromId(36);
    }

    public static MonsterType wubboxAirIsland() {
        return fromId(37);
    }

    public static MonsterType wubboxWaterIsland() {
        return fromId(38);
    }

    public static MonsterType wubboxEarthIsland() {
        return fromId(39);
    }

    public static MonsterType blabbit() {
        return fromId(49);
    }

    public static MonsterType ghazt() {
        return fromId(50);
    }

    public static MonsterType reebro() {
        return fromId(51);
    }

    public static MonsterType hoola() {
        return fromId(52);
    }

    public static MonsterType shugabush() {
        return fromId(53);
    }

    public static MonsterType grumpyre() {
        return fromId(54);
    }

    public static MonsterType nebulob() {
        return fromId(55);
    }

    public static MonsterType arackulele() {
        return fromId(56);
    }

    public static MonsterType whisp() {
        return fromId(57);
    }

    public static MonsterType jeeode() {
        return fromId(58);
    }

    public static MonsterType boodoo() {
        return fromId(59);
    }

    public static MonsterType legendaryPotbelly() {
        return fromId(60);
    }

    public static MonsterType legendaryMammott() {
        return fromId(61);
    }

    public static MonsterType legendaryQuibble() {
        return fromId(62);
    }

    public static MonsterType legendaryOaktopus() {
        return fromId(63);
    }

    public static MonsterType legendaryFurcorn() {
        return fromId(64);
    }

    public static MonsterType legendaryPompom() {
        return fromId(65);
    }

    public static MonsterType legendaryDeedge() {
        return fromId(66);
    }

    public static MonsterType legendaryShugabush() {
        return fromId(67);
    }

    public static MonsterType legendaryShugabass() {
        return fromId(68);
    }

    public static MonsterType legendaryShuguitar() {
        return fromId(69);
    }

    public static MonsterType legendaryShugajo() {
        return fromId(70);
    }

    public static MonsterType legendaryShugarock() {
        return fromId(71);
    }

    public static MonsterType legendaryShugabuzz() {
        return fromId(72);
    }

    public static MonsterType legendaryShugabeats() {
        return fromId(73);
    }

    public static MonsterType legendaryShugavox() {
        return fromId(74);
    }

    public static MonsterType sox() {
        return fromId(75);
    }

    public static MonsterType humbug() {
        return fromId(76);
    }

    public static MonsterType kazilleon() {
        return fromId(77);
    }

    public static MonsterType bellowfish() {
        return fromId(78);
    }

    public static MonsterType dragong() {
        return fromId(79);
    }

    public static MonsterType jellbilly() {
        return fromId(80);
    }

    public static MonsterType fungpray() {
        return fromId(81);
    }

    public static MonsterType wubboxEtherealIsland() {
        return fromId(82);
    }

    public static MonsterType rareFurcorn() {
        return fromId(83);
    }

    public static MonsterType legendaryRareFurcorn() {
        return fromId(84);
    }

    public static MonsterType rareEntbrat() {
        return fromId(85);
    }

    public static MonsterType rareSpunge() {
        return fromId(86);
    }

    public static MonsterType rareThumpies() {
        return fromId(87);
    }

    public static MonsterType rarePummel() {
        return fromId(88);
    }

    public static MonsterType rarePango() {
        return fromId(89);
    }

    public static MonsterType rareFwog() {
        return fromId(90);
    }

    public static MonsterType rarePompom() {
        return fromId(91);
    }

    public static MonsterType legendaryRarePompom() {
        return fromId(92);
    }

    public static MonsterType rareMaw() {
        return fromId(93);
    }

    public static MonsterType rareShrubb() {
        return fromId(94);
    }

    public static MonsterType rareCybop() {
        return fromId(95);
    }

    public static MonsterType rareClamble() {
        return fromId(96);
    }

    public static MonsterType rareCongle() {
        return fromId(97);
    }

    public static MonsterType rareQuibble() {
        return fromId(98);
    }

    public static MonsterType legendaryRareQuibble() {
        return fromId(99);
    }

    public static MonsterType rareDandidoo() {
        return fromId(100);
    }

    public static MonsterType rareOaktopus() {
        return fromId(101);
    }

    public static MonsterType legendaryRareOaktopus() {
        return fromId(102);
    }

    public static MonsterType rareReedling() {
        return fromId(103);
    }

    public static MonsterType rareDeedge() {
        return fromId(104);
    }

    public static MonsterType legendaryRareDeedge() {
        return fromId(105);
    }

    public static MonsterType rareBowgart() {
        return fromId(106);
    }

    public static MonsterType rareScups() {
        return fromId(107);
    }

    public static MonsterType rareRiff() {
        return fromId(108);
    }

    public static MonsterType rareDrumpler() {
        return fromId(109);
    }

    public static MonsterType rareShellbeat() {
        return fromId(110);
    }

    public static MonsterType rareTrox() {
        return fromId(111);
    }

    public static MonsterType rareQuarrister() {
        return fromId(112);
    }

    public static MonsterType rareMammott() {
        return fromId(113);
    }

    public static MonsterType legendaryRareMammott() {
        return fromId(114);
    }

    public static MonsterType rarePotbelly() {
        return fromId(115);
    }

    public static MonsterType legendaryRarePotbelly() {
        return fromId(116);
    }

    public static MonsterType rareToejammer() {
        return fromId(117);
    }

    public static MonsterType rareTweedle() {
        return fromId(118);
    }

    public static MonsterType rareNoggin() {
        return fromId(119);
    }

    public static MonsterType rareWubboxPlantIsland() {
        return fromId(120);
    }

    public static MonsterType rareWubboxColdIsland() {
        return fromId(121);
    }

    public static MonsterType rareWubboxAirIsland() {
        return fromId(122);
    }

    public static MonsterType rareWubboxWaterIsland() {
        return fromId(123);
    }

    public static MonsterType rareWubboxEarthIsland() {
        return fromId(124);
    }

    public static MonsterType brump() {
        return fromId(125);
    }

    public static MonsterType zynth() {
        return fromId(126);
    }

    public static MonsterType poewk() {
        return fromId(127);
    }

    public static MonsterType thwok() {
        return fromId(128);
    }

    public static MonsterType dwumrohl() {
        return fromId(129);
    }

    public static MonsterType zuuker() {
        return fromId(130);
    }

    public static MonsterType screemu() {
        return fromId(131);
    }

    public static MonsterType rareGhazt() {
        return fromId(132);
    }

    public static MonsterType rareGrumpyre() {
        return fromId(133);
    }

    public static MonsterType rareReebro() {
        return fromId(134);
    }

    public static MonsterType rareJeeode() {
        return fromId(135);
    }

    public static MonsterType rareHumbug() {
        return fromId(136);
    }

    public static MonsterType rareFungpray() {
        return fromId(137);
    }

    public static MonsterType rareDragong() {
        return fromId(138);
    }

    public static MonsterType dipsterDo() {
        return fromId(139);
    }

    public static MonsterType dipsterRe() {
        return fromId(140);
    }

    public static MonsterType dipsterMi() {
        return fromId(141);
    }

    public static MonsterType dipsterFa() {
        return fromId(142);
    }

    public static MonsterType dipsterSol() {
        return fromId(143);
    }

    public static MonsterType dipsterLa() {
        return fromId(144);
    }

    public static MonsterType dipsterTi() {
        return fromId(145);
    }

    public static MonsterType rareBellowfish() {
        return fromId(146);
    }

    public static MonsterType tympa() {
        return fromId(147);
    }

    public static MonsterType rareHoola() {
        return fromId(148);
    }

    public static MonsterType dermit() {
        return fromId(149);
    }

    public static MonsterType gheegur() {
        return fromId(150);
    }

    public static MonsterType whajje() {
        return fromId(151);
    }

    public static MonsterType blipsqueak() {
        return fromId(152);
    }

    public static MonsterType creepuscule() {
        return fromId(153);
    }

    public static MonsterType scargo() {
        return fromId(154);
    }

    public static MonsterType wubboxWublinIsland() {
        return fromId(155);
    }

    public static MonsterType rarePunkleton() {
        return fromId(156);
    }

    public static MonsterType rareKazilleon() {
        return fromId(157);
    }

    public static MonsterType tweedleComposerIsland() {
        return fromId(200);
    }

    public static MonsterType potbellyComposerIsland() {
        return fromId(201);
    }

    public static MonsterType nogginComposerIsland() {
        return fromId(202);
    }

    public static MonsterType toejammerComposerIsland() {
        return fromId(203);
    }

    public static MonsterType mammottComposerIsland() {
        return fromId(204);
    }

    public static MonsterType dandidooComposerIsland() {
        return fromId(205);
    }

    public static MonsterType cybopComposerIsland() {
        return fromId(206);
    }

    public static MonsterType quibbleComposerIsland() {
        return fromId(207);
    }

    public static MonsterType pangoComposerIsland() {
        return fromId(208);
    }

    public static MonsterType shrubbComposerIsland() {
        return fromId(209);
    }

    public static MonsterType oaktopusComposerIsland() {
        return fromId(210);
    }

    public static MonsterType furcornComposerIsland() {
        return fromId(211);
    }

    public static MonsterType fwogComposerIsland() {
        return fromId(212);
    }

    public static MonsterType drumplerComposerIsland() {
        return fromId(213);
    }

    public static MonsterType mawComposerIsland() {
        return fromId(214);
    }

    public static MonsterType reedlingComposerIsland() {
        return fromId(215);
    }

    public static MonsterType spungeComposerIsland() {
        return fromId(216);
    }

    public static MonsterType thumpiesComposerIsland() {
        return fromId(217);
    }

    public static MonsterType scupsComposerIsland() {
        return fromId(218);
    }

    public static MonsterType pompomComposerIsland() {
        return fromId(219);
    }

    public static MonsterType congleComposerIsland() {
        return fromId(220);
    }

    public static MonsterType pummelComposerIsland() {
        return fromId(221);
    }

    public static MonsterType clambleComposerIsland() {
        return fromId(222);
    }

    public static MonsterType bowgartComposerIsland() {
        return fromId(223);
    }

    public static MonsterType troxComposerIsland() {
        return fromId(224);
    }

    public static MonsterType shellbeatComposerIsland() {
        return fromId(225);
    }

    public static MonsterType quarristerComposerIsland() {
        return fromId(226);
    }

    public static MonsterType deedgeComposerIsland() {
        return fromId(227);
    }

    public static MonsterType riffComposerIsland() {
        return fromId(228);
    }

    public static MonsterType entbratComposerIsland() {
        return fromId(229);
    }

    public static MonsterType glowbeComposerIsland() {
        return fromId(230);
    }

    public static MonsterType rareYool() {
        return fromId(231);
    }

    public static MonsterType rareBoodoo() {
        return fromId(232);
    }

    public static MonsterType tawkerrPlantIsland() {
        return fromId(233);
    }

    public static MonsterType parlsonaPlantIsland() {
        return fromId(234);
    }

    public static MonsterType rareArackulele() {
        return fromId(235);
    }

    public static MonsterType rareSchmoochle() {
        return fromId(236);
    }

    public static MonsterType rareJellbilly() {
        return fromId(237);
    }

    public static MonsterType astropod() {
        return fromId(238);
    }

    public static MonsterType rareSox() {
        return fromId(239);
    }

    public static MonsterType pixolotl() {
        return fromId(240);
    }

    public static MonsterType rareBlabbit() {
        return fromId(241);
    }

    public static MonsterType rareNebulob() {
        return fromId(242);
    }

    public static MonsterType bonaPetite() {
        return fromId(243);
    }

    public static MonsterType rareWhisp() {
        return fromId(244);
    }

    public static MonsterType maulch() {
        return fromId(245);
    }

    public static MonsterType fleechwurm() {
        return fromId(246);
    }

    public static MonsterType tawkerrColdIsland() {
        return fromId(247);
    }

    public static MonsterType maggpiColdIsland() {
        return fromId(248);
    }

    public static MonsterType torrt() {
        return fromId(249);
    }

    public static MonsterType blasoom() {
        return fromId(250);
    }

    public static MonsterType hornacle() {
        return fromId(251);
    }

    public static MonsterType attmoz() {
        return fromId(252);
    }

    public static MonsterType glaishur() {
        return fromId(253);
    }

    public static MonsterType syncopite() {
        return fromId(254);
    }

    public static MonsterType galvana() {
        return fromId(255);
    }

    public static MonsterType scaratar() {
        return fromId(256);
    }

    public static MonsterType loodvigg() {
        return fromId(257);
    }

    public static MonsterType furnoss() {
        return fromId(258);
    }

    public static MonsterType plixie() {
        return fromId(259);
    }

    public static MonsterType vhamp() {
        return fromId(260);
    }

    public static MonsterType floogull() {
        return fromId(261);
    }

    public static MonsterType kaynaFireIsland() {
        return fromId(262);
    }

    public static MonsterType stogg() {
        return fromId(263);
    }

    public static MonsterType nogginFireIsland() {
        return fromId(264);
    }

    public static MonsterType dandidooFireIsland() {
        return fromId(265);
    }

    public static MonsterType reedlingFireIsland() {
        return fromId(266);
    }

    public static MonsterType shrubbFireIsland() {
        return fromId(267);
    }

    public static MonsterType barrb() {
        return fromId(268);
    }

    public static MonsterType potbellyFireIsland() {
        return fromId(269);
    }

    public static MonsterType tweedleFireIsland() {
        return fromId(270);
    }

    public static MonsterType cybopFireIsland() {
        return fromId(271);
    }

    public static MonsterType flowah() {
        return fromId(272);
    }

    public static MonsterType glowl() {
        return fromId(273);
    }

    public static MonsterType repatillo() {
        return fromId(274);
    }

    public static MonsterType tring() {
        return fromId(275);
    }

    public static MonsterType parlsonaAirIsland() {
        return fromId(276);
    }

    public static MonsterType stoowarb() {
        return fromId(277);
    }

    public static MonsterType rareStogg() {
        return fromId(278);
    }

    public static MonsterType wynq() {
        return fromId(279);
    }

    public static MonsterType quibbleFireIsland() {
        return fromId(280);
    }

    public static MonsterType congleFireIsland() {
        return fromId(281);
    }

    public static MonsterType sneyser() {
        return fromId(282);
    }

    public static MonsterType whaddle() {
        return fromId(283);
    }

    public static MonsterType woolabee() {
        return fromId(284);
    }

    public static MonsterType phangler() {
        return fromId(285);
    }

    public static MonsterType boskus() {
        return fromId(286);
    }

    public static MonsterType pangoFireIsland() {
        return fromId(287);
    }

    public static MonsterType toeJammerFireIsland() {
        return fromId(288);
    }

    public static MonsterType mawFireIsland() {
        return fromId(289);
    }

    public static MonsterType mammottFireIsland() {
        return fromId(290);
    }

    public static MonsterType rareTweedleFireIsland() {
        return fromId(291);
    }

    public static MonsterType rarePotbellyFireIsland() {
        return fromId(292);
    }

    public static MonsterType rareNogginFireIsland() {
        return fromId(293);
    }

    public static MonsterType rareDandidooFireIsland() {
        return fromId(294);
    }

    public static MonsterType rareCybopFireIsland() {
        return fromId(295);
    }

    public static MonsterType rareShrubbFireIsland() {
        return fromId(296);
    }

    public static MonsterType rareReedlingFireIsland() {
        return fromId(297);
    }

    public static MonsterType rareFloogull() {
        return fromId(298);
    }

    public static MonsterType oaktopusFireIsland() {
        return fromId(299);
    }

    public static MonsterType rareRepatillo() {
        return fromId(300);
    }

    public static MonsterType epicTweedle() {
        return fromId(301);
    }

    public static MonsterType epicPotbelly() {
        return fromId(302);
    }

    public static MonsterType epicNoggin() {
        return fromId(303);
    }

    public static MonsterType epicToejammer() {
        return fromId(304);
    }

    public static MonsterType epicMammott() {
        return fromId(305);
    }

    public static MonsterType epicDandidoo() {
        return fromId(306);
    }

    public static MonsterType epicCybop() {
        return fromId(307);
    }

    public static MonsterType epicQuibble() {
        return fromId(308);
    }

    public static MonsterType epicPango() {
        return fromId(309);
    }

    public static MonsterType epicShrubb() {
        return fromId(310);
    }

    public static MonsterType epicOaktopus() {
        return fromId(311);
    }

    public static MonsterType epicFurcorn() {
        return fromId(312);
    }

    public static MonsterType epicFwog() {
        return fromId(313);
    }

    public static MonsterType epicDrumpler() {
        return fromId(314);
    }

    public static MonsterType epicMaw() {
        return fromId(315);
    }

    public static MonsterType epicReedling() {
        return fromId(316);
    }

    public static MonsterType epicSpunge() {
        return fromId(317);
    }

    public static MonsterType epicThumpies() {
        return fromId(318);
    }

    public static MonsterType epicPompom() {
        return fromId(319);
    }

    public static MonsterType epicCongle() {
        return fromId(320);
    }

    public static MonsterType epicPummel() {
        return fromId(321);
    }

    public static MonsterType epicClamble() {
        return fromId(322);
    }

    public static MonsterType epicBowgart() {
        return fromId(323);
    }

    public static MonsterType epicTRox() {
        return fromId(324);
    }

    public static MonsterType epicShellbeat() {
        return fromId(325);
    }

    public static MonsterType epicQuarrister() {
        return fromId(326);
    }

    public static MonsterType epicDeedge() {
        return fromId(327);
    }

    public static MonsterType epicRiff() {
        return fromId(328);
    }

    public static MonsterType epicEntbrat() {
        return fromId(329);
    }

    public static MonsterType epicScups() {
        return fromId(331);
    }

    public static MonsterType epicPunkleton() {
        return fromId(332);
    }

    public static MonsterType epicYool() {
        return fromId(333);
    }

    public static MonsterType epicSchmoochle() {
        return fromId(334);
    }

    public static MonsterType epicBlabbit() {
        return fromId(349);
    }

    public static MonsterType epicHoola() {
        return fromId(352);
    }

    public static MonsterType rarePhangler() {
        return fromId(400);
    }

    public static MonsterType rootitoot() {
        return fromId(401);
    }

    public static MonsterType yawstrich() {
        return fromId(402);
    }

    public static MonsterType gjoob() {
        return fromId(403);
    }

    public static MonsterType theremind() {
        return fromId(404);
    }

    public static MonsterType bonkers() {
        return fromId(405);
    }

    public static MonsterType poppette() {
        return fromId(406);
    }

    public static MonsterType yuggler() {
        return fromId(407);
    }

    public static MonsterType tapricorn() {
        return fromId(408);
    }

    public static MonsterType rooba() {
        return fromId(409);
    }

    public static MonsterType periscorp() {
        return fromId(410);
    }

    public static MonsterType gloptic() {
        return fromId(411);
    }

    public static MonsterType rareFlowah() {
        return fromId(412);
    }

    public static MonsterType rareWynq() {
        return fromId(413);
    }

    public static MonsterType gobbleygourd() {
        return fromId(414);
    }

    public static MonsterType drumplerFireIsland() {
        return fromId(415);
    }

    public static MonsterType flootFly() {
        return fromId(416);
    }

    public static MonsterType hippityHop() {
        return fromId(417);
    }

    public static MonsterType squot() {
        return fromId(418);
    }

    public static MonsterType wimmzies() {
        return fromId(419);
    }

    public static MonsterType ziggurab() {
        return fromId(420);
    }

    public static MonsterType cantorell() {
        return fromId(421);
    }

    public static MonsterType bridgit() {
        return fromId(422);
    }

    public static MonsterType claviGnat() {
        return fromId(423);
    }

    public static MonsterType pladdie() {
        return fromId(424);
    }

    public static MonsterType epicDandidooFireIsland() {
        return fromId(425);
    }

    public static MonsterType rareTring() {
        return fromId(426);
    }

    public static MonsterType rareBoskus() {
        return fromId(427);
    }

    public static MonsterType rareToejammerFireIsland() {
        return fromId(428);
    }

    public static MonsterType rareMammottFireIsland() {
        return fromId(429);
    }

    public static MonsterType rareQuibbleFireIsland() {
        return fromId(430);
    }

    public static MonsterType rarePangoFireIsland() {
        return fromId(431);
    }

    public static MonsterType rareMawFireIsland() {
        return fromId(432);
    }

    public static MonsterType rareCongleFireIsland() {
        return fromId(433);
    }

    public static MonsterType rareWoolabee() {
        return fromId(434);
    }

    public static MonsterType fwogFireIsland() {
        return fromId(435);
    }

    public static MonsterType clackula() {
        return fromId(436);
    }

    public static MonsterType peckidna() {
        return fromId(437);
    }

    public static MonsterType denchuhs() {
        return fromId(438);
    }

    public static MonsterType hawlo() {
        return fromId(439);
    }

    public static MonsterType thrumble() {
        return fromId(440);
    }

    public static MonsterType withur() {
        return fromId(441);
    }

    public static MonsterType uuduk() {
        return fromId(442);
    }

    public static MonsterType banjaw() {
        return fromId(443);
    }

    public static MonsterType plinkajou() {
        return fromId(444);
    }

    public static MonsterType maggpiWaterIsland() {
        return fromId(445);
    }

    public static MonsterType parlsonaWaterIsland() {
        return fromId(446);
    }

    public static MonsterType rareWhaddle() {
        return fromId(447);
    }

    public static MonsterType epicTweedleFireIsland() {
        return fromId(448);
    }

    public static MonsterType epicPotbellyFireIsland() {
        return fromId(449);
    }

    public static MonsterType epicNogginFireIsland() {
        return fromId(450);
    }

    public static MonsterType epicToejammerFireIsland() {
        return fromId(451);
    }

    public static MonsterType epicMammottFireIsland() {
        return fromId(452);
    }

    public static MonsterType epicCybopFireIsland() {
        return fromId(453);
    }

    public static MonsterType epicQuibbleFireIsland() {
        return fromId(454);
    }

    public static MonsterType epicPangoFireIsland() {
        return fromId(455);
    }

    public static MonsterType epicShrubbFireIsland() {
        return fromId(456);
    }

    public static MonsterType epicMawFireIsland() {
        return fromId(457);
    }

    public static MonsterType epicReedlingFireIsland() {
        return fromId(458);
    }

    public static MonsterType epicCongleFireIsland() {
        return fromId(459);
    }

    public static MonsterType rareRootitoot() {
        return fromId(460);
    }

    public static MonsterType rareBarrb() {
        return fromId(461);
    }

    public static MonsterType rareGlowl() {
        return fromId(462);
    }

    public static MonsterType tawkerrEarthIsland() {
        return fromId(463);
    }

    public static MonsterType stoowarbEarthIsland() {
        return fromId(464);
    }

    public static MonsterType rareThrumble() {
        return fromId(465);
    }

    public static MonsterType rareSneyser() {
        return fromId(466);
    }

    public static MonsterType rareZiggurab() {
        return fromId(467);
    }

    public static MonsterType furcornFireIsland() {
        return fromId(468);
    }

    public static MonsterType sooza() {
        return fromId(469);
    }

    public static MonsterType fluoress() {
        return fromId(470);
    }

    public static MonsterType gob() {
        return fromId(471);
    }

    public static MonsterType spytrap() {
        return fromId(472);
    }

    public static MonsterType pluckbill() {
        return fromId(473);
    }

    public static MonsterType rareSooza() {
        return fromId(474);
    }

    public static MonsterType tooToo() {
        return fromId(475);
    }

    public static MonsterType bulbo() {
        return fromId(476);
    }

    public static MonsterType fiddlement() {
        return fromId(477);
    }

    public static MonsterType blowt() {
        return fromId(478);
    }

    public static MonsterType rareKayna() {
        return fromId(479);
    }

    public static MonsterType rareFurcornFireIsland() {
        return fromId(480);
    }

    public static MonsterType epicFurcornFireIsland() {
        return fromId(481);
    }

    public static MonsterType rareOaktopusFireIsland() {
        return fromId(482);
    }

    public static MonsterType epicOaktopusFireIsland() {
        return fromId(483);
    }

    public static MonsterType rareDrumplerFireIsland() {
        return fromId(484);
    }

    public static MonsterType epicDrumplerFireIsland() {
        return fromId(485);
    }

    public static MonsterType rareFwogFireIsland() {
        return fromId(486);
    }

    public static MonsterType epicFwogFireIsland() {
        return fromId(487);
    }

    public static MonsterType epicGhazt() {
        return fromId(488);
    }

    public static MonsterType rareTheremind() {
        return fromId(489);
    }

    public static MonsterType xyster() {
        return fromId(490);
    }

    public static MonsterType knucklehead() {
        return fromId(491);
    }

    public static MonsterType frondley() {
        return fromId(492);
    }

    public static MonsterType epicKayna() {
        return fromId(493);
    }

    public static MonsterType dejaJin() {
        return fromId(494);
    }

    public static MonsterType rareWimmzies() {
        return fromId(495);
    }

    public static MonsterType larvaluss() {
        return fromId(496);
    }

    public static MonsterType epicWhaddle() {
        return fromId(497);
    }

    public static MonsterType cahoot() {
        return fromId(498);
    }

    public static MonsterType mushaboom() {
        return fromId(499);
    }

    public static MonsterType rareGobbleygourd() {
        return fromId(500);
    }

    public static MonsterType osstax() {
        return fromId(501);
    }

    public static MonsterType rareBanjaw() {
        return fromId(502);
    }

    public static MonsterType gday() {
        return fromId(503);
    }

    public static MonsterType epicStogg() {
        return fromId(504);
    }

    public static MonsterType roarick() {
        return fromId(505);
    }

    public static MonsterType rareGob() {
        return fromId(506);
    }

    public static MonsterType enchantling() {
        return fromId(507);
    }

    public static MonsterType legendaryEpicPotbelly() {
        return fromId(508);
    }

    public static MonsterType legendaryEpicMammott() {
        return fromId(509);
    }

    public static MonsterType legendaryEpicQuibble() {
        return fromId(510);
    }

    public static MonsterType legendaryEpicOaktopus() {
        return fromId(511);
    }

    public static MonsterType legendaryEpicFurcorn() {
        return fromId(512);
    }

    public static MonsterType legendaryEpicPompom() {
        return fromId(513);
    }

    public static MonsterType legendaryEpicDeedge() {
        return fromId(514);
    }

    public static MonsterType rareWubboxEtherealIsland() {
        return fromId(515);
    }

    public static MonsterType schmoochleSeasonalIsland() {
        return fromId(516);
    }

    public static MonsterType epicZiggurab() {
        return fromId(517);
    }

    public static MonsterType yoolSeasonalIsland() {
        return fromId(518);
    }

    public static MonsterType blabbitSeasonalIsland() {
        return fromId(519);
    }

    public static MonsterType punkletonSeasonalIsland() {
        return fromId(520);
    }

    public static MonsterType hoolaSeasonalIsland() {
        return fromId(521);
    }

    public static MonsterType gobbleygourdSeasonalIsland() {
        return fromId(522);
    }

    public static MonsterType jamBoreeSeasonalIsland() {
        return fromId(523);
    }

    public static MonsterType epicWubboxPlantIsland() {
        return fromId(524);
    }

    public static MonsterType epicReebro() {
        return fromId(525);
    }

    public static MonsterType rarePunkletonSeasonalIsland() {
        return fromId(526);
    }

    public static MonsterType epicPunkletonSeasonalIsland() {
        return fromId(527);
    }

    public static MonsterType rareGobbleygourdSeasonalIsland() {
        return fromId(528);
    }

    public static MonsterType rareYuggler() {
        return fromId(529);
    }

    public static MonsterType clavaveraSeasonalIsland() {
        return fromId(530);
    }

    public static MonsterType epicWubboxColdIsland() {
        return fromId(531);
    }

    public static MonsterType epicGobbleygourd() {
        return fromId(532);
    }

    public static MonsterType epicGobbleygourdSeasonalIsland() {
        return fromId(533);
    }

    public static MonsterType rareYoolSeasonalIsland() {
        return fromId(534);
    }

    public static MonsterType epicYoolSeasonalIsland() {
        return fromId(535);
    }

    public static MonsterType epicTring() {
        return fromId(536);
    }

    public static MonsterType carillongSeasonalIsland() {
        return fromId(537);
    }

    public static MonsterType rareBridgit() {
        return fromId(538);
    }

    public static MonsterType kaynaAmberIsland() {
        return fromId(539);
    }

    public static MonsterType glowlAmberIsland() {
        return fromId(540);
    }

    public static MonsterType phanglerAmberIsland() {
        return fromId(541);
    }

    public static MonsterType floogullAmberIsland() {
        return fromId(542);
    }

    public static MonsterType ziggurabAmberIsland() {
        return fromId(543);
    }

    public static MonsterType yelmutAmberIsland() {
        return fromId(544);
    }

    public static MonsterType rareSchmoochleSeasonalIsland() {
        return fromId(545);
    }

    public static MonsterType epicSchmoochleSeasonalIsland() {
        return fromId(546);
    }

    public static MonsterType epicBoskus() {
        return fromId(547);
    }

    public static MonsterType rareHawlo() {
        return fromId(548);
    }

    public static MonsterType ffidyllSeasonalIsland() {
        return fromId(549);
    }

    public static MonsterType soozaAmberIsland() {
        return fromId(550);
    }

    public static MonsterType boskusAmberIsland() {
        return fromId(551);
    }

    public static MonsterType flumOxAmberIsland() {
        return fromId(552);
    }

    public static MonsterType epicWubboxAirIsland() {
        return fromId(553);
    }

    public static MonsterType rareBlabbitSeasonalIsland() {
        return fromId(554);
    }

    public static MonsterType epicBlabbitSeasonalIsland() {
        return fromId(555);
    }

    public static MonsterType epicJeeode() {
        return fromId(556);
    }

    public static MonsterType stoggAmberIsland() {
        return fromId(557);
    }

    public static MonsterType thrumbleAmberIsland() {
        return fromId(558);
    }

    public static MonsterType krillbyAmberIsland() {
        return fromId(559);
    }

    public static MonsterType rareTooToo() {
        return fromId(560);
    }

    public static MonsterType viveineSeasonalIsland() {
        return fromId(561);
    }

    public static MonsterType flowahAmberIsland() {
        return fromId(562);
    }

    public static MonsterType barrbAmberIsland() {
        return fromId(563);
    }

    public static MonsterType edamimiAmberIsland() {
        return fromId(564);
    }

    public static MonsterType epicRootitoot() {
        return fromId(565);
    }

    public static MonsterType spurritSeasonalIsland() {
        return fromId(566);
    }

    public static MonsterType monculusSeasonalIsland() {
        return fromId(567);
    }

    public static MonsterType repatilloAmberIsland() {
        return fromId(568);
    }

    public static MonsterType whaddleAmberIsland() {
        return fromId(569);
    }

    public static MonsterType pongPingAmberIsland() {
        return fromId(570);
    }

    public static MonsterType rareFlootFly() {
        return fromId(571);
    }

    public static MonsterType whizbangSeasonalIsland() {
        return fromId(572);
    }

    public static MonsterType rareKaynaAmberIsland() {
        return fromId(573);
    }

    public static MonsterType rareGlowlAmberIsland() {
        return fromId(574);
    }

    public static MonsterType rarePhanglerAmberIsland() {
        return fromId(575);
    }

    public static MonsterType rareBoskusAmberIsland() {
        return fromId(576);
    }

    public static MonsterType rareStoggAmberIsland() {
        return fromId(577);
    }

    public static MonsterType rareFlowahAmberIsland() {
        return fromId(578);
    }

    public static MonsterType candelavraAmberIsland() {
        return fromId(579);
    }

    public static MonsterType sneyserAmberIsland() {
        return fromId(580);
    }

    public static MonsterType rootitootAmberIsland() {
        return fromId(581);
    }

    public static MonsterType woolabeeAmberIsland() {
        return fromId(582);
    }

    public static MonsterType rareHoolaSeasonalIsland() {
        return fromId(583);
    }

    public static MonsterType epicHoolaSeasonalIsland() {
        return fromId(584);
    }

    public static MonsterType epicGrumpyre() {
        return fromId(585);
    }

    public static MonsterType wynqAmberIsland() {
        return fromId(586);
    }

    public static MonsterType tiawaAmberIsland() {
        return fromId(587);
    }

    public static MonsterType epicWubboxWaterIsland() {
        return fromId(588);
    }

    public static MonsterType epicWubboxEarthIsland() {
        return fromId(589);
    }

    public static MonsterType booqwurmSeasonalIsland() {
        return fromId(590);
    }

    public static MonsterType rareBarrbAmberIsland() {
        return fromId(591);
    }

    public static MonsterType rareFloogullAmberIsland() {
        return fromId(592);
    }

    public static MonsterType rareWhaddleAmberIsland() {
        return fromId(593);
    }

    public static MonsterType rareRepatilloAmberIsland() {
        return fromId(594);
    }

    public static MonsterType rareWoolabeeAmberIsland() {
        return fromId(595);
    }

    public static MonsterType rareWynqAmberIsland() {
        return fromId(596);
    }

    public static MonsterType strombonin() {
        return fromId(597);
    }

    public static MonsterType cataliszt() {
        return fromId(598);
    }

    public static MonsterType rareJamBoree() {
        return fromId(599);
    }

    public static MonsterType rareRootitootAmberIsland() {
        return fromId(600);
    }

    public static MonsterType rareZiggurabAmberIsland() {
        return fromId(601);
    }

    public static MonsterType rareSoozaAmberIsland() {
        return fromId(602);
    }

    public static MonsterType rareThrumbleAmberIsland() {
        return fromId(603);
    }

    public static MonsterType sporerow() {
        return fromId(604);
    }

    public static MonsterType bleatnik() {
        return fromId(605);
    }

    public static MonsterType cranchee() {
        return fromId(606);
    }

    public static MonsterType rareGJoob() {
        return fromId(607);
    }

    public static MonsterType tringAmberIsland() {
        return fromId(608);
    }

    public static MonsterType rareTringAmberIsland() {
        return fromId(609);
    }

    public static MonsterType rareWithur() {
        return fromId(610);
    }

    public static MonsterType clavavera() {
        return fromId(611);
    }

    public static MonsterType bisonorusAmberIsland() {
        return fromId(612);
    }

    public static MonsterType rareSneyserAmberIsland() {
        return fromId(613);
    }

    public static MonsterType epicGlowl() {
        return fromId(614);
    }

    public static MonsterType wubboxFireHaven() {
        return fromId(615);
    }

    public static MonsterType cherubble() {
        return fromId(616);
    }

    public static MonsterType shLep() {
        return fromId(617);
    }

    public static MonsterType rareYelmutAmberIsland() {
        return fromId(618);
    }

    public static MonsterType incisaurAmberIsland() {
        return fromId(619);
    }

    public static MonsterType rareStrombonin() {
        return fromId(620);
    }

    public static MonsterType bowheadAmberIsland() {
        return fromId(621);
    }

    public static MonsterType rareFlumOxAmberIsland() {
        return fromId(622);
    }

    public static MonsterType carillong() {
        return fromId(623);
    }

    public static MonsterType rareXyster() {
        return fromId(624);
    }

    public static MonsterType rareYawstrich() {
        return fromId(625);
    }

    public static MonsterType drummidaryAmberIsland() {
        return fromId(626);
    }

    public static MonsterType rareKrillbyAmberIsland() {
        return fromId(627);
    }

    public static MonsterType hyehehe() {
        return fromId(628);
    }

    public static MonsterType wheezel() {
        return fromId(629);
    }

    public static MonsterType epicHumbug() {
        return fromId(630);
    }

    public static MonsterType ffidyll() {
        return fromId(631);
    }

    public static MonsterType tuskskiAmberIsland() {
        return fromId(632);
    }

    public static MonsterType rareEdamimiAmberIsland() {
        return fromId(633);
    }

    public static MonsterType epicKaynaAmberIsland() {
        return fromId(634);
    }

    public static MonsterType rareDwumrohl() {
        return fromId(635);
    }

    public static MonsterType rareHippityHop() {
        return fromId(636);
    }

    public static MonsterType rareZynth() {
        return fromId(637);
    }

    public static MonsterType rarePoewk() {
        return fromId(638);
    }

    public static MonsterType rareThwok() {
        return fromId(639);
    }

    public static MonsterType rareBrump() {
        return fromId(640);
    }

    public static MonsterType anglow() {
        return fromId(641);
    }

    public static MonsterType pinghound() {
        return fromId(642);
    }

    public static MonsterType epicWhaddleAmberIsland() {
        return fromId(643);
    }

    public static MonsterType legendaryViveine() {
        return fromId(644);
    }

    public static MonsterType viveineAmberIsland() {
        return fromId(645);
    }

    public static MonsterType epicStoggAmberIsland() {
        return fromId(646);
    }

    public static MonsterType epicZiggurabAmberIsland() {
        return fromId(647);
    }

    public static MonsterType epicTringAmberIsland() {
        return fromId(648);
    }

    public static MonsterType epicBoskusAmberIsland() {
        return fromId(649);
    }

    public static MonsterType epicRootitootAmberIsland() {
        return fromId(650);
    }

    public static MonsterType epicGlowlAmberIsland() {
        return fromId(651);
    }

    public static MonsterType gnarlsAmberIsland() {
        return fromId(652);
    }

    public static MonsterType rarePongPingAmberIsland() {
        return fromId(653);
    }

    public static MonsterType rareClavavera() {
        return fromId(654);
    }

    public static MonsterType rareClavaveraSeasonalIsland() {
        return fromId(655);
    }

    public static MonsterType rareWhajje() {
        return fromId(656);
    }

    public static MonsterType rareWubboxFireHaven() {
        return fromId(657);
    }

    public static MonsterType spurrit() {
        return fromId(658);
    }

    public static MonsterType epicSneyser() {
        return fromId(659);
    }

    public static MonsterType epicSneyserAmberIsland() {
        return fromId(660);
    }

    public static MonsterType monculusEtherealIsland() {
        return fromId(661);
    }

    public static MonsterType monculusWublinIsland() {
        return fromId(662);
    }

    public static MonsterType rareBlipsqueak() {
        return fromId(663);
    }

    public static MonsterType epicWhisp() {
        return fromId(664);
    }

    public static MonsterType rareTiawaAmberIsland() {
        return fromId(665);
    }

    public static MonsterType rareFluoress() {
        return fromId(666);
    }

    public static MonsterType adultScaratar() {
        return fromId(667);
    }

    public static MonsterType adultLoodvigg() {
        return fromId(668);
    }

    public static MonsterType whizbang() {
        return fromId(669);
    }

    public static MonsterType goldIslandEpicWubbox01() {
        return fromId(670);
    }

    public static MonsterType goldIslandEpicWubbox02() {
        return fromId(671);
    }

    public static MonsterType goldIslandEpicWubbox03() {
        return fromId(672);
    }

    public static MonsterType goldIslandEpicWubbox04() {
        return fromId(673);
    }

    public static MonsterType goldIslandEpicWubbox05() {
        return fromId(674);
    }

    public static MonsterType rareIncisaurAmberIsland() {
        return fromId(675);
    }

    public static MonsterType rareTympa() {
        return fromId(676);
    }

    public static MonsterType rareCarillong() {
        return fromId(677);
    }

    public static MonsterType rareCarillongSeasonalIsland() {
        return fromId(678);
    }

    public static MonsterType booqwurm() {
        return fromId(679);
    }

    public static MonsterType rareBisonorusAmberIsland() {
        return fromId(680);
    }

    public static MonsterType rareCreepuscule() {
        return fromId(681);
    }

    public static MonsterType yooreek() {
        return fromId(682);
    }

    public static MonsterType blarret() {
        return fromId(683);
    }

    public static MonsterType auglur() {
        return fromId(684);
    }

    public static MonsterType meebkin() {
        return fromId(685);
    }

    public static MonsterType gaddzooks() {
        return fromId(686);
    }

    public static MonsterType epicJamBoreeSeasonalIsland() {
        return fromId(687);
    }

    public static MonsterType knurv() {
        return fromId(688);
    }

    public static MonsterType buzzinga() {
        return fromId(689);
    }

    public static MonsterType rareFfidyllSeasonalIsland() {
        return fromId(690);
    }

    public static MonsterType rareFfidyll() {
        return fromId(691);
    }

    public static MonsterType rareAstropod() {
        return fromId(692);
    }

    public static MonsterType adultTorrt() {
        return fromId(693);
    }

    public static MonsterType rareGheegur() {
        return fromId(694);
    }

    public static MonsterType epicThrumble() {
        return fromId(695);
    }

    public static MonsterType epicThrumbleAmberIsland() {
        return fromId(696);
    }

    public static MonsterType rareViveineSeasonalIsland() {
        return fromId(697);
    }

    public static MonsterType rareViveineAmberIsland() {
        return fromId(698);
    }

    public static MonsterType legendaryRareViveine() {
        return fromId(699);
    }

    public static MonsterType epicBoodoo() {
        return fromId(700);
    }

    public static MonsterType rareRooba() {
        return fromId(701);
    }

    public static MonsterType bisonorus() {
        return fromId(702);
    }

    public static MonsterType krillby() {
        return fromId(703);
    }

    public static MonsterType yelmut() {
        return fromId(704);
    }

    public static MonsterType rareKrillby() {
        return fromId(705);
    }

    public static MonsterType rareBisonorus() {
        return fromId(706);
    }

    public static MonsterType rareYelmut() {
        return fromId(707);
    }

    public static MonsterType whaill() {
        return fromId(708);
    }

    public static MonsterType incisaur() {
        return fromId(709);
    }

    public static MonsterType beMeebEth() {
        return fromId(710);
    }

    public static MonsterType flumOx() {
        return fromId(711);
    }

    public static MonsterType rareFlumOx() {
        return fromId(712);
    }

    public static MonsterType pongPing() {
        return fromId(713);
    }

    public static MonsterType edamimi() {
        return fromId(714);
    }

    public static MonsterType rareEdamimi() {
        return fromId(715);
    }

    public static MonsterType tiawa() {
        return fromId(716);
    }

    public static MonsterType rareTiawa() {
        return fromId(717);
    }

    public static MonsterType rarePongPing() {
        return fromId(718);
    }

    public static MonsterType rareIncisaur() {
        return fromId(719);
    }

    public static MonsterType rareBonaPetite() {
        return fromId(720);
    }

    public static MonsterType adultPlixie() {
        return fromId(721);
    }

    public static MonsterType gnarls() {
        return fromId(722);
    }

    public static MonsterType epicClavavera() {
        return fromId(723);
    }

    public static MonsterType epicClavaveraSeasonalIsland() {
        return fromId(724);
    }

    public static MonsterType candelavra() {
        return fromId(725);
    }

    public static MonsterType rareCandelavraAmberIsland() {
        return fromId(726);
    }

    public static MonsterType rareCandelavra() {
        return fromId(727);
    }

    public static MonsterType tuskski() {
        return fromId(728);
    }

    public static MonsterType bowhead() {
        return fromId(729);
    }

    public static MonsterType drummidary() {
        return fromId(730);
    }

    public static MonsterType rareSpurritSeasonalIsland() {
        return fromId(731);
    }

    public static MonsterType rareSpurrit() {
        return fromId(732);
    }

    public static MonsterType adultAttmoz() {
        return fromId(733);
    }

    public static MonsterType epicBellowfish() {
        return fromId(734);
    }

    public static MonsterType rareScargo() {
        return fromId(735);
    }

    public static MonsterType flasque() {
        return fromId(736);
    }

    public static MonsterType nitebear() {
        return fromId(737);
    }

    public static MonsterType rareMonculusSeasonalIsland() {
        return fromId(738);
    }

    public static MonsterType rareMonculusEtherealIsland() {
        return fromId(739);
    }

    public static MonsterType rareMonculusWublinIsland() {
        return fromId(740);
    }

    public static MonsterType adultHornacle() {
        return fromId(741);
    }

    public static MonsterType rareWhizbangSeasonalIsland() {
        return fromId(742);
    }

    public static MonsterType rareWhizbang() {
        return fromId(743);
    }

    public static MonsterType epicWynq() {
        return fromId(744);
    }

    public static MonsterType epicWynqAmberIsland() {
        return fromId(745);
    }

    public static MonsterType rareFleechwurm() {
        return fromId(746);
    }

    public static MonsterType epicCarillong() {
        return fromId(747);
    }

    public static MonsterType epicCarillongSeasonalIsland() {
        return fromId(748);
    }

    public static MonsterType adultFurnoss() {
        return fromId(749);
    }

    public static MonsterType rareKnucklehead() {
        return fromId(750);
    }

    public static MonsterType rareScreemu() {
        return fromId(751);
    }

    public static MonsterType adultGlaishur() {
        return fromId(752);
    }

    public static MonsterType epicWoolabee() {
        return fromId(753);
    }

    public static MonsterType epicWoolabeeAmberIsland() {
        return fromId(754);
    }

    public static MonsterType rareBooqwurmSeasonalIsland() {
        return fromId(755);
    }

    public static MonsterType rareBooqwurm() {
        return fromId(756);
    }

    public static MonsterType piplash() {
        return fromId(757);
    }

    public static MonsterType vhenshun() {
        return fromId(758);
    }

    public static MonsterType epicSox() {
        return fromId(759);
    }

    public static MonsterType rareBonkers() {
        return fromId(760);
    }

    public static MonsterType rareTuskskiAmberIsland() {
        return fromId(761);
    }

    public static MonsterType rareTuskski() {
        return fromId(762);
    }

    public static MonsterType adultBlasoom() {
        return fromId(763);
    }

    public static MonsterType rareDermit() {
        return fromId(764);
    }

    public static MonsterType epicFfidyllSeasonalIsland() {
        return fromId(765);
    }

    public static MonsterType epicFfidyll() {
        return fromId(766);
    }

    public static MonsterType epicJellbilly() {
        return fromId(767);
    }

    public static MonsterType epicFlowah() {
        return fromId(768);
    }

    public static MonsterType epicFlowahAmberIsland() {
        return fromId(769);
    }

    public static MonsterType epicBarrb() {
        return fromId(770);
    }

    public static MonsterType epicBarrbAmberIsland() {
        return fromId(771);
    }

    public static MonsterType epicViveineSeasonalIsland() {
        return fromId(772);
    }

    public static MonsterType legendaryEpicViveine() {
        return fromId(773);
    }

    public static MonsterType epicViveineAmberIsland() {
        return fromId(774);
    }

    public static MonsterType ghaztComposerIsland() {
        return fromId(775);
    }

    public static MonsterType rarePluckbill() {
        return fromId(776);
    }

    public static MonsterType xrt() {
        return fromId(777);
    }

    public static MonsterType pentumbra() {
        return fromId(778);
    }

    public static MonsterType rareMaulch() {
        return fromId(779);
    }

    public static MonsterType adultSyncopite() {
        return fromId(780);
    }

    public static MonsterType epicSpurritSeasonalIsland() {
        return fromId(781);
    }

    public static MonsterType epicSpurrit() {
        return fromId(782);
    }

    public static MonsterType epicPhangler() {
        return fromId(783);
    }

    public static MonsterType epicPhanglerAmberIsland() {
        return fromId(784);
    }

    public static MonsterType wubboxFireOasis() {
        return fromId(785);
    }

    public static MonsterType mimic2() {
        return fromId(786);
    }

    public static MonsterType adultVhamp() {
        return fromId(787);
    }

    public static MonsterType rareZuuker() {
        return fromId(788);
    }

    public static MonsterType epicMonculusSeasonalIsland() {
        return fromId(789);
    }

    public static MonsterType epicMonculusEtherealIsland() {
        return fromId(790);
    }

    public static MonsterType rareHyehehe() {
        return fromId(791);
    }

    public static MonsterType rareCherubble() {
        return fromId(792);
    }

    public static MonsterType epicNebulob() {
        return fromId(793);
    }

    public static MonsterType epicSooza() {
        return fromId(794);
    }

    public static MonsterType epicSoozaAmberIsland() {
        return fromId(795);
    }

    public static MonsterType teeterTauter() {
        return fromId(796);
    }

    public static MonsterType rareAnglow() {
        return fromId(797);
    }

    public static MonsterType rhysmuth() {
        return fromId(798);
    }

    public static MonsterType rareClackula() {
        return fromId(799);
    }

    public static MonsterType rarePixolotl() {
        return fromId(800);
    }

    public static MonsterType epicWhizbangSeasonalIsland() {
        return fromId(801);
    }

    public static MonsterType epicWhizbang() {
        return fromId(802);
    }

    public static MonsterType rareBuzzinga() {
        return fromId(803);
    }

    public static MonsterType adultGalvana() {
        return fromId(804);
    }

    public static MonsterType epicRepatillo() {
        return fromId(805);
    }

    public static MonsterType epicRepatilloAmberIsland() {
        return fromId(806);
    }

    public static MonsterType oogiddy() {
        return fromId(807);
    }

    public static MonsterType epicKazilleon() {
        return fromId(808);
    }

    public static MonsterType rareGloptic() {
        return fromId(809);
    }

    public static MonsterType epicBooqwurmSeasonalIsland() {
        return fromId(810);
    }

    public static MonsterType epicBooqwurm() {
        return fromId(811);
    }

    public static MonsterType rareCataliszt() {
        return fromId(812);
    }

    public static MonsterType rareBowhead() {
        return fromId(813);
    }

    public static MonsterType rareBowheadAmberIsland() {
        return fromId(814);
    }

    public static MonsterType lightIslandTitansoul() {
        return fromId(815);
    }

    public static MonsterType rareWubboxWublinIsland() {
        return fromId(816);
    }

    public static MonsterType epicYawstrich() {
        return fromId(817);
    }

    public static MonsterType epicArackulele() {
        return fromId(818);
    }

    public static MonsterType epicGjoob() {
        return fromId(819);
    }

    public static MonsterType rareBleatnik() {
        return fromId(820);
    }

    public static MonsterType rareGnarlsAmberIsland() {
        return fromId(821);
    }

    public static MonsterType rareGnarls() {
        return fromId(822);
    }

    public static MonsterType rareOsstax() {
        return fromId(823);
    }

    public static MonsterType rareUuduk() {
        return fromId(824);
    }

    public static MonsterType rareFiddlement() {
        return fromId(825);
    }

    public static MonsterType epicFloogull() {
        return fromId(826);
    }

    public static MonsterType epicFloogullAmberIsland() {
        return fromId(827);
    }

    public static MonsterType rareClaviGnat() {
        return fromId(828);
    }

    public static MonsterType rareWubboxFireOasis() {
        return fromId(829);
    }

    public static MonsterType epicWubboxFireHaven() {
        return fromId(830);
    }

    public static MonsterType epicWubboxFireOasis() {
        return fromId(831);
    }

    public static MonsterType hairionetteMajor() {
        return fromId(832);
    }

    public static MonsterType hairionetteMinor() {
        return fromId(833);
    }

    public static MonsterType epicBuzzinga() {
        return fromId(834);
    }

    public static MonsterType rareKnurv() {
        return fromId(835);
    }

    public static MonsterType epicStrombonin() {
        return fromId(836);
    }

    public static MonsterType rareCranchee() {
        return fromId(837);
    }

    public static MonsterType rareBulbo() {
        return fromId(838);
    }

    public static MonsterType rareDrummidaryAmberIsland() {
        return fromId(839);
    }

    public static MonsterType rareDrummidary() {
        return fromId(840);
    }

    public static MonsterType rarePoppette() {
        return fromId(853);
    }

    public static MonsterType epicYelmut() {
        return fromId(854);
    }

    public static MonsterType epicYelmutAmberIsland() {
        return fromId(855);
    }

    public static MonsterType lowb() {
        return fromId(856);
    }

    public static MonsterType rareGday() {
        return fromId(857);
    }

    public static MonsterType epicDragong() {
        return fromId(858);
    }

    public static MonsterType epicKrillby() {
        return fromId(859);
    }

    public static MonsterType epicKrillbyAmberIsland() {
        return fromId(860);
    }

    public static MonsterType rareCantorell() {
        return fromId(861);
    }

    public static MonsterType rareSporerow() {
        return fromId(862);
    }

    public static MonsterType epicFungpray() {
        return fromId(863);
    }

    public static MonsterType rareDenchuhs() {
        return fromId(864);
    }

    public static MonsterType epicPongPing() {
        return fromId(865);
    }

    public static MonsterType epicPongPingAmberIsland() {
        return fromId(866);
    }

    public static MonsterType rarePladdie() {
        return fromId(867);
    }

    public static MonsterType owlesqueMajor() {
        return fromId(868);
    }

    public static MonsterType owlesqueMinor() {
        return fromId(869);
    }

    public static MonsterType epicDwumrohl() {
        return fromId(870);
    }

    public static MonsterType epicZynth() {
        return fromId(871);
    }

    public static MonsterType epicPoewk() {
        return fromId(872);
    }

    public static MonsterType epicThwok() {
        return fromId(873);
    }

    public static MonsterType epicBrump() {
        return fromId(874);
    }

    public static MonsterType epicMonculusWublinIsland() {
        return fromId(875);
    }

    public static MonsterType epicWubboxEtherealIsland() {
        return fromId(876);
    }

    public static MonsterType rareSpytrap() {
        return fromId(877);
    }

    public static MonsterType rareRoarick() {
        return fromId(878);
    }

    public static MonsterType rarePinghound() {
        return fromId(879);
    }

    public static MonsterType epicAnglow() {
        return fromId(880);
    }

    public static MonsterType epicEdamimi() {
        return fromId(881);
    }

    public static MonsterType epicEdamimiAmberIsland() {
        return fromId(882);
    }

    public static MonsterType psychicIslandTitansoul() {
        return fromId(883);
    }

    public static MonsterType rareMushaboom() {
        return fromId(884);
    }

    public static MonsterType rarePlinkajou() {
        return fromId(885);
    }

    public static MonsterType epicCherubble() {
        return fromId(886);
    }

    public static MonsterType rareMimic() {
        return fromId(887);
    }

    public static MonsterType rareshLep() {
        return fromId(888);
    }

    public static MonsterType rareTapricorn() {
        return fromId(889);
    }

    public static MonsterType epicTiawa() {
        return fromId(891);
    }

    public static MonsterType epicTiawaAmberIsland() {
        return fromId(892);
    }

    public static MonsterType epicZuuker() {
        return fromId(893);
    }

    public static MonsterType rareDejaJin() {
        return fromId(894);
    }

}