package com.glisterbyte.SingingMonsters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glisterbyte.Localization.Language;
import com.glisterbyte.Localization.LocalizedResources;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsStructureInfo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StructureType {

    private final int structureId;
    private final int entityId;

    private final StructureCategory category;

    private final MultiCurrencyValue cost;
    private final Size size;
    private final Duration removalDuration;
    private final String nameCode;
    private final String descCode;

    private final int buildXpReward;

    private final boolean movable;
    private final boolean sellable;

    private final int upgradeStructureId;
    private final List<IslandType> allowedIslandTypes;

    public StructureType(SfsStructureInfo sfsInfo) {

        structureId = sfsInfo.structureId;
        entityId = sfsInfo.entityId;

        category = StructureCategory.fromTypeString(sfsInfo.structureType);

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
        removalDuration = Duration.ofSeconds(sfsInfo.buildTime);
        nameCode = sfsInfo.name;
        descCode = sfsInfo.description;

        buildXpReward = sfsInfo.xp;

        movable = sfsInfo.movable == 1;
        sellable = sfsInfo.sellable == 1;

        upgradeStructureId = sfsInfo.upgradesTo;

        if (sfsInfo.allowedOnIsland == null) allowedIslandTypes = null;
        else {
            allowedIslandTypes = new ArrayList<>();
            for (int typeId : sfsInfo.allowedOnIsland) {
                allowedIslandTypes.add(IslandType.fromId(typeId));
            }
        }

    }

    public static StructureType fromId(int typeId) {
        return Cache.getStructureTypeByTypeId(typeId);
    }

    public int getTypeId() {
        return structureId;
    }

    public int getEntityId() {
        return entityId;
    }

    public StructureCategory getStructureCategory() {
        return category;
    }

    public MultiCurrencyValue getCost() {
        return cost;
    }

    public Size getSize() {
        return size;
    }

    public Duration getRemovalDuration() {
        return removalDuration;
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

    public int getBuildXpReward() {
        return buildXpReward;
    }

    public boolean isMovable() {
        return movable;
    }

    public boolean isSellable() {
        return sellable;
    }

    public StructureType getUpgradeStructureId() {
        return Cache.getStructureTypeByTypeId(upgradeStructureId);
    }

    public List<IslandType> getAllowedIslandTypes() {
        return allowedIslandTypes == null ? IslandType.allIslandTypes() : allowedIslandTypes;
    }

    // The following is auto-generated via misc/java-info-gen/monsters.py.

    public static StructureType nursery() {
        return fromId(1);
    }

    public static StructureType breedingStructure() {
        return fromId(2);
    }

    public static StructureType mine() {
        return fromId(6);
    }

    public static StructureType basicCastle() {
        return fromId(7);
    }

    public static StructureType modestCastle() {
        return fromId(8);
    }

    public static StructureType comfortableCastle() {
        return fromId(9);
    }

    public static StructureType spaciousCastle() {
        return fromId(10);
    }

    public static StructureType luxuriousCastle() {
        return fromId(11);
    }

    public static StructureType basicCastle2() {
        return fromId(12);
    }

    public static StructureType modestCastle2() {
        return fromId(13);
    }

    public static StructureType comfortableCastle2() {
        return fromId(14);
    }

    public static StructureType spaciousCastle2() {
        return fromId(15);
    }

    public static StructureType luxuriousCastle2() {
        return fromId(16);
    }

    public static StructureType basicCastle3() {
        return fromId(17);
    }

    public static StructureType modestCastle3() {
        return fromId(18);
    }

    public static StructureType comfortableCastle3() {
        return fromId(19);
    }

    public static StructureType spaciousCastle3() {
        return fromId(20);
    }

    public static StructureType luxuriousCastle3() {
        return fromId(21);
    }

    public static StructureType basicCastle4() {
        return fromId(22);
    }

    public static StructureType modestCastle4() {
        return fromId(23);
    }

    public static StructureType comfortableCastle4() {
        return fromId(24);
    }

    public static StructureType spaciousCastle4() {
        return fromId(25);
    }

    public static StructureType luxuriousCastle4() {
        return fromId(26);
    }

    public static StructureType basicCastle5() {
        return fromId(27);
    }

    public static StructureType modestCastle5() {
        return fromId(28);
    }

    public static StructureType comfortableCastle5() {
        return fromId(29);
    }

    public static StructureType spaciousCastle5() {
        return fromId(30);
    }

    public static StructureType luxuriousCastle5() {
        return fromId(31);
    }

    public static StructureType smallBakery() {
        return fromId(32);
    }

    public static StructureType mediumBakery() {
        return fromId(33);
    }

    public static StructureType largeBakery() {
        return fromId(34);
    }

    public static StructureType reflectingPool() {
        return fromId(64);
    }

    public static StructureType bassStringBridge() {
        return fromId(65);
    }

    public static StructureType digger() {
        return fromId(66);
    }

    public static StructureType hollowLog() {
        return fromId(67);
    }

    public static StructureType wildBagpipe() {
        return fromId(68);
    }

    public static StructureType flappyFlag() {
        return fromId(69);
    }

    public static StructureType fossilosaurus() {
        return fromId(70);
    }

    public static StructureType eerieRemains() {
        return fromId(71);
    }

    public static StructureType leafySeaDragon() {
        return fromId(72);
    }

    public static StructureType cozeeCabin() {
        return fromId(73);
    }

    public static StructureType treeHut() {
        return fromId(74);
    }

    public static StructureType trumplite() {
        return fromId(75);
    }

    public static StructureType meldablend() {
        return fromId(76);
    }

    public static StructureType bottomlessPit() {
        return fromId(77);
    }

    public static StructureType toob() {
        return fromId(78);
    }

    public static StructureType pipesofCicado() {
        return fromId(79);
    }

    public static StructureType harpsitree() {
        return fromId(80);
    }

    public static StructureType guitree() {
        return fromId(81);
    }

    public static StructureType castanevine() {
        return fromId(82);
    }

    public static StructureType smunkinPatch() {
        return fromId(83);
    }

    public static StructureType spurritStatue() {
        return fromId(84);
    }

    public static StructureType directionstoNowhere() {
        return fromId(85);
    }

    public static StructureType travelersSign() {
        return fromId(86);
    }

    public static StructureType fireBush() {
        return fromId(87);
    }

    public static StructureType barblooStatue() {
        return fromId(88);
    }

    public static StructureType squeedStatue() {
        return fromId(89);
    }

    public static StructureType dragoonStatue() {
        return fromId(90);
    }

    public static StructureType stritchSkin() {
        return fromId(91);
    }

    public static StructureType yumYumTree() {
        return fromId(93);
    }

    public static StructureType beeyootTree() {
        return fromId(94);
    }

    public static StructureType fuzzleTree() {
        return fromId(95);
    }

    public static StructureType puffleTree() {
        return fromId(96);
    }

    public static StructureType zuffleTree() {
        return fromId(97);
    }

    public static StructureType pineyTree() {
        return fromId(98);
    }

    public static StructureType saggleTree() {
        return fromId(99);
    }

    public static StructureType bloofiTree() {
        return fromId(100);
    }

    public static StructureType crumplerTree() {
        return fromId(101);
    }

    public static StructureType razzliTree() {
        return fromId(102);
    }

    public static StructureType babayagTower() {
        return fromId(103);
    }

    public static StructureType treeForteTower() {
        return fromId(104);
    }

    public static StructureType dragonTower() {
        return fromId(105);
    }

    public static StructureType smallRock() {
        return fromId(106);
    }

    public static StructureType mediumRock() {
        return fromId(107);
    }

    public static StructureType bigRock() {
        return fromId(108);
    }

    public static StructureType smallTree() {
        return fromId(109);
    }

    public static StructureType mediumTree() {
        return fromId(110);
    }

    public static StructureType bigTree() {
        return fromId(111);
    }

    public static StructureType smallRock2() {
        return fromId(112);
    }

    public static StructureType mediumRock2() {
        return fromId(113);
    }

    public static StructureType bigRock2() {
        return fromId(114);
    }

    public static StructureType smallTree2() {
        return fromId(115);
    }

    public static StructureType mediumTree2() {
        return fromId(116);
    }

    public static StructureType bigTree2() {
        return fromId(117);
    }

    public static StructureType smallRock3() {
        return fromId(118);
    }

    public static StructureType mediumRock3() {
        return fromId(119);
    }

    public static StructureType bigRock3() {
        return fromId(120);
    }

    public static StructureType smallTree3() {
        return fromId(121);
    }

    public static StructureType mediumTree3() {
        return fromId(122);
    }

    public static StructureType bigTree3() {
        return fromId(123);
    }

    public static StructureType timeMachine() {
        return fromId(124);
    }

    public static StructureType smallRock4() {
        return fromId(125);
    }

    public static StructureType mediumRock4() {
        return fromId(126);
    }

    public static StructureType bigRock4() {
        return fromId(127);
    }

    public static StructureType smallTree4() {
        return fromId(128);
    }

    public static StructureType mediumTree4() {
        return fromId(129);
    }

    public static StructureType bigTree4() {
        return fromId(130);
    }

    public static StructureType smallRock5() {
        return fromId(131);
    }

    public static StructureType mediumRock5() {
        return fromId(132);
    }

    public static StructureType bigRock5() {
        return fromId(133);
    }

    public static StructureType smallTree5() {
        return fromId(134);
    }

    public static StructureType mediumTree5() {
        return fromId(135);
    }

    public static StructureType bigTree5() {
        return fromId(136);
    }

    public static StructureType crystalCastle() {
        return fromId(137);
    }

    public static StructureType extravagantCastle() {
        return fromId(138);
    }

    public static StructureType extravagantCastle2() {
        return fromId(139);
    }

    public static StructureType extravagantCastle3() {
        return fromId(140);
    }

    public static StructureType extravagantCastle4() {
        return fromId(141);
    }

    public static StructureType extravagantCastle5() {
        return fromId(142);
    }

    public static StructureType checkeredBubbleritePath() {
        return fromId(143);
    }

    public static StructureType mossyBubbleritePath() {
        return fromId(144);
    }

    public static StructureType bubbleritePath() {
        return fromId(145);
    }

    public static StructureType blueFlowerPath() {
        return fromId(146);
    }

    public static StructureType moshroomPath() {
        return fromId(147);
    }

    public static StructureType leafstrewnPath() {
        return fromId(148);
    }

    public static StructureType inverdigusPath() {
        return fromId(149);
    }

    public static StructureType worndownPath() {
        return fromId(150);
    }

    public static StructureType inverdigusSprig() {
        return fromId(151);
    }

    public static StructureType icyPath() {
        return fromId(152);
    }

    public static StructureType frozenPuddlePath() {
        return fromId(153);
    }

    public static StructureType hairtuftPath() {
        return fromId(154);
    }

    public static StructureType purpleHairtuftPath() {
        return fromId(155);
    }

    public static StructureType auroraPath() {
        return fromId(156);
    }

    public static StructureType blueBubbleritePath() {
        return fromId(157);
    }

    public static StructureType cryosanthemum() {
        return fromId(158);
    }

    public static StructureType fuzzyPath() {
        return fromId(159);
    }

    public static StructureType globulePath() {
        return fromId(160);
    }

    public static StructureType floatingRockPath() {
        return fromId(161);
    }

    public static StructureType dandifloretPath() {
        return fromId(162);
    }

    public static StructureType yuckaPath() {
        return fromId(163);
    }

    public static StructureType thistlePath() {
        return fromId(164);
    }

    public static StructureType effervefence() {
        return fromId(165);
    }

    public static StructureType greenCoralPath() {
        return fromId(166);
    }

    public static StructureType purpleCoralPath() {
        return fromId(167);
    }

    public static StructureType seastarPath() {
        return fromId(168);
    }

    public static StructureType spottedCoralPath() {
        return fromId(169);
    }

    public static StructureType puddlyPath() {
        return fromId(170);
    }

    public static StructureType greenBubbleritePath() {
        return fromId(171);
    }

    public static StructureType oceanicOutpost() {
        return fromId(172);
    }

    public static StructureType ninetilePath() {
        return fromId(173);
    }

    public static StructureType blackBubbleritePath() {
        return fromId(174);
    }

    public static StructureType firechillPath() {
        return fromId(175);
    }

    public static StructureType woodplankPath() {
        return fromId(176);
    }

    public static StructureType onetilePath() {
        return fromId(177);
    }

    public static StructureType joulygoodPath() {
        return fromId(178);
    }

    public static StructureType sizzlerPath() {
        return fromId(179);
    }

    public static StructureType barrockade() {
        return fromId(180);
    }

    public static StructureType amberedThing() {
        return fromId(181);
    }

    public static StructureType floofyNest() {
        return fromId(182);
    }

    public static StructureType tubFountain() {
        return fromId(183);
    }

    public static StructureType unityTree() {
        return fromId(185);
    }

    public static StructureType miniMine() {
        return fromId(186);
    }

    public static StructureType storageShed() {
        return fromId(188);
    }

    public static StructureType storageBarn() {
        return fromId(189);
    }

    public static StructureType storageWarehouse() {
        return fromId(190);
    }

    public static StructureType humbleHotel() {
        return fromId(191);
    }

    public static StructureType classyHotel() {
        return fromId(192);
    }

    public static StructureType grandHotel() {
        return fromId(193);
    }

    public static StructureType smallRock6() {
        return fromId(194);
    }

    public static StructureType mediumRock6() {
        return fromId(195);
    }

    public static StructureType bigRock6() {
        return fromId(196);
    }

    public static StructureType smallTree6() {
        return fromId(197);
    }

    public static StructureType mediumTree6() {
        return fromId(198);
    }

    public static StructureType bigTree6() {
        return fromId(199);
    }

    public static StructureType basicCastle6() {
        return fromId(200);
    }

    public static StructureType modestCastle6() {
        return fromId(201);
    }

    public static StructureType comfortableCastle6() {
        return fromId(202);
    }

    public static StructureType spaciousCastle6() {
        return fromId(203);
    }

    public static StructureType luxuriousCastle6() {
        return fromId(204);
    }

    public static StructureType extravagantCastle6() {
        return fromId(205);
    }

    public static StructureType wishingTorch() {
        return fromId(206);
    }

    public static StructureType maximumMine() {
        return fromId(207);
    }

    public static StructureType smallRock7() {
        return fromId(208);
    }

    public static StructureType mediumRock7() {
        return fromId(209);
    }

    public static StructureType bigRock7() {
        return fromId(210);
    }

    public static StructureType smallTree7() {
        return fromId(211);
    }

    public static StructureType mediumTree7() {
        return fromId(212);
    }

    public static StructureType bigTree7() {
        return fromId(213);
    }

    public static StructureType basicCastle7() {
        return fromId(214);
    }

    public static StructureType modestCastle7() {
        return fromId(215);
    }

    public static StructureType comfortableCastle7() {
        return fromId(216);
    }

    public static StructureType spaciousCastle7() {
        return fromId(217);
    }

    public static StructureType luxuriousCastle7() {
        return fromId(218);
    }

    public static StructureType extravagantCastle7() {
        return fromId(219);
    }

    public static StructureType tealBubbleritePath() {
        return fromId(220);
    }

    public static StructureType purpureusBubbleritePath() {
        return fromId(221);
    }

    public static StructureType particulatePath() {
        return fromId(222);
    }

    public static StructureType runicPath() {
        return fromId(223);
    }

    public static StructureType glyphicPath() {
        return fromId(224);
    }

    public static StructureType fracturePath() {
        return fromId(225);
    }

    public static StructureType waferockPath() {
        return fromId(226);
    }

    public static StructureType lignalinePath() {
        return fromId(227);
    }

    public static StructureType reddiscPath() {
        return fromId(228);
    }

    public static StructureType yellowdiscPath() {
        return fromId(229);
    }

    public static StructureType deciburPath() {
        return fromId(230);
    }

    public static StructureType leafcrunchPath() {
        return fromId(231);
    }

    public static StructureType paradiseCastle() {
        return fromId(232);
    }

    public static StructureType paradiseCastle2() {
        return fromId(233);
    }

    public static StructureType paradiseCastle3() {
        return fromId(234);
    }

    public static StructureType paradiseCastle4() {
        return fromId(235);
    }

    public static StructureType paradiseCastle5() {
        return fromId(236);
    }

    public static StructureType paradiseCastle6() {
        return fromId(237);
    }

    public static StructureType paradiseCastle7() {
        return fromId(238);
    }

    public static StructureType enhancedNursery() {
        return fromId(239);
    }

    public static StructureType enhancedBreedingStructure() {
        return fromId(240);
    }

    public static StructureType recordingStudio() {
        return fromId(241);
    }

    public static StructureType smallRock8() {
        return fromId(242);
    }

    public static StructureType mediumRock8() {
        return fromId(243);
    }

    public static StructureType bigRock8() {
        return fromId(244);
    }

    public static StructureType smallTree8() {
        return fromId(245);
    }

    public static StructureType mediumTree8() {
        return fromId(246);
    }

    public static StructureType bigTree8() {
        return fromId(247);
    }

    public static StructureType smallRock9() {
        return fromId(248);
    }

    public static StructureType mediumRock9() {
        return fromId(249);
    }

    public static StructureType bigRock9() {
        return fromId(250);
    }

    public static StructureType smallTree9() {
        return fromId(251);
    }

    public static StructureType mediumTree9() {
        return fromId(252);
    }

    public static StructureType bigTree9() {
        return fromId(253);
    }

    public static StructureType smallRock10() {
        return fromId(254);
    }

    public static StructureType mediumRock10() {
        return fromId(255);
    }

    public static StructureType bigRock10() {
        return fromId(256);
    }

    public static StructureType smallTree10() {
        return fromId(257);
    }

    public static StructureType mediumTree10() {
        return fromId(258);
    }

    public static StructureType bigTree10() {
        return fromId(259);
    }

    public static StructureType smallRock11() {
        return fromId(260);
    }

    public static StructureType mediumRock11() {
        return fromId(261);
    }

    public static StructureType bigRock11() {
        return fromId(262);
    }

    public static StructureType smallTree11() {
        return fromId(263);
    }

    public static StructureType mediumTree11() {
        return fromId(264);
    }

    public static StructureType bigTree11() {
        return fromId(265);
    }

    public static StructureType smallRock12() {
        return fromId(266);
    }

    public static StructureType mediumRock12() {
        return fromId(267);
    }

    public static StructureType bigRock12() {
        return fromId(268);
    }

    public static StructureType smallTree12() {
        return fromId(269);
    }

    public static StructureType mediumTree12() {
        return fromId(270);
    }

    public static StructureType bigTree12() {
        return fromId(271);
    }

    public static StructureType smallRock13() {
        return fromId(272);
    }

    public static StructureType mediumRock13() {
        return fromId(273);
    }

    public static StructureType bigRock13() {
        return fromId(274);
    }

    public static StructureType smallTree13() {
        return fromId(275);
    }

    public static StructureType mediumTree13() {
        return fromId(276);
    }

    public static StructureType bigTree13() {
        return fromId(277);
    }

    public static StructureType smallRock14() {
        return fromId(278);
    }

    public static StructureType mediumRock14() {
        return fromId(279);
    }

    public static StructureType bigRock14() {
        return fromId(280);
    }

    public static StructureType smallTree14() {
        return fromId(281);
    }

    public static StructureType mediumTree14() {
        return fromId(282);
    }

    public static StructureType bigTree14() {
        return fromId(283);
    }

    public static StructureType glowbe() {
        return fromId(284);
    }

    public static StructureType fuzer() {
        return fromId(285);
    }

    public static StructureType bonusNursery() {
        return fromId(286);
    }

    public static StructureType bonusBreedingStructure() {
        return fromId(288);
    }

    public static StructureType kayna() {
        return fromId(289);
    }

    public static StructureType mysteriousMonster() {
        return fromId(290);
    }

    public static StructureType thumpiesTotemToo() {
        return fromId(291);
    }

    public static StructureType mountKnottshurr() {
        return fromId(292);
    }

    public static StructureType freedThing() {
        return fromId(293);
    }

    public static StructureType bossMonument() {
        return fromId(294);
    }

    public static StructureType starRocks() {
        return fromId(295);
    }

    public static StructureType starRocks2() {
        return fromId(296);
    }

    public static StructureType starRocks3() {
        return fromId(297);
    }

    public static StructureType starRocks4() {
        return fromId(298);
    }

    public static StructureType starRocks5() {
        return fromId(299);
    }

    public static StructureType miniMine2() {
        return fromId(300);
    }

    public static StructureType composerCastle() {
        return fromId(301);
    }

    public static StructureType enhancedGrandHotel() {
        return fromId(302);
    }

    public static StructureType hyperEnhancedGrandHotel() {
        return fromId(303);
    }

    public static StructureType sweetstreamzTree() {
        return fromId(304);
    }

    public static StructureType ockuloTree() {
        return fromId(305);
    }

    public static StructureType spatialSapling() {
        return fromId(306);
    }

    public static StructureType swurleeTree() {
        return fromId(307);
    }

    public static StructureType theThunker() {
        return fromId(308);
    }

    public static StructureType hammock() {
        return fromId(309);
    }

    public static StructureType bingoBangoBongos() {
        return fromId(310);
    }

    public static StructureType strikingBulbs() {
        return fromId(311);
    }

    public static StructureType elmencoStump() {
        return fromId(312);
    }

    public static StructureType innertuba() {
        return fromId(313);
    }

    public static StructureType smunkit() {
        return fromId(314);
    }

    public static StructureType pUSystem() {
        return fromId(315);
    }

    public static StructureType coldGlobe() {
        return fromId(316);
    }

    public static StructureType givutawaiTree() {
        return fromId(317);
    }

    public static StructureType smallSpooktacleRock() {
        return fromId(318);
    }

    public static StructureType mediumSpooktacleRock() {
        return fromId(319);
    }

    public static StructureType bigSpooktacleRock() {
        return fromId(320);
    }

    public static StructureType smallSpooktacleTree() {
        return fromId(321);
    }

    public static StructureType mediumSpooktacleTree() {
        return fromId(322);
    }

    public static StructureType bigSpooktacleTree() {
        return fromId(323);
    }

    public static StructureType smallFestivalofYayRock() {
        return fromId(324);
    }

    public static StructureType mediumFestivalofYayRock() {
        return fromId(325);
    }

    public static StructureType bigFestivalofYayRock() {
        return fromId(326);
    }

    public static StructureType smallFestivalofYayTree() {
        return fromId(327);
    }

    public static StructureType mediumFestivalofYayTree() {
        return fromId(328);
    }

    public static StructureType bigFestivalofYayTree() {
        return fromId(329);
    }

    public static StructureType smallSeasonofLoveRock() {
        return fromId(330);
    }

    public static StructureType mediumSeasonofLoveRock() {
        return fromId(331);
    }

    public static StructureType bigSeasonofLoveRock() {
        return fromId(332);
    }

    public static StructureType smallSeasonofLoveTree() {
        return fromId(333);
    }

    public static StructureType mediumSeasonofLoveTree() {
        return fromId(334);
    }

    public static StructureType bigSeasonofLoveTree() {
        return fromId(335);
    }

    public static StructureType smallEggsTravaganzaRock() {
        return fromId(336);
    }

    public static StructureType mediumEggsTravaganzaRock() {
        return fromId(337);
    }

    public static StructureType bigEggsTravaganzaRock() {
        return fromId(338);
    }

    public static StructureType smallEggsTravaganzaTree() {
        return fromId(339);
    }

    public static StructureType mediumEggsTravaganzaTree() {
        return fromId(340);
    }

    public static StructureType bigEggsTravaganzaTree() {
        return fromId(341);
    }

    public static StructureType smallSummerSongRock() {
        return fromId(342);
    }

    public static StructureType mediumSummerSongRock() {
        return fromId(343);
    }

    public static StructureType bigSummerSongRock() {
        return fromId(344);
    }

    public static StructureType smallSummerSongTree() {
        return fromId(345);
    }

    public static StructureType mediumSummerSongTree() {
        return fromId(346);
    }

    public static StructureType bigSummerSongTree() {
        return fromId(347);
    }

    public static StructureType enhancedStorageWarehouse() {
        return fromId(348);
    }

    public static StructureType hyperEnhancedStorageWarehouse() {
        return fromId(349);
    }

    public static StructureType creepyCauldron() {
        return fromId(350);
    }

    public static StructureType frightfulFripperies() {
        return fromId(351);
    }

    public static StructureType inverdigusCemetery() {
        return fromId(352);
    }

    public static StructureType smunkoLanternPatch() {
        return fromId(353);
    }

    public static StructureType basicCastle8() {
        return fromId(354);
    }

    public static StructureType modestCastle8() {
        return fromId(355);
    }

    public static StructureType comfortableCastle8() {
        return fromId(356);
    }

    public static StructureType spaciousCastle8() {
        return fromId(357);
    }

    public static StructureType luxuriousCastle8() {
        return fromId(358);
    }

    public static StructureType extravagantCastle8() {
        return fromId(359);
    }

    public static StructureType paradiseCastle8() {
        return fromId(360);
    }

    public static StructureType smallRock15() {
        return fromId(361);
    }

    public static StructureType mediumRock15() {
        return fromId(362);
    }

    public static StructureType bigRock15() {
        return fromId(363);
    }

    public static StructureType smallTree15() {
        return fromId(364);
    }

    public static StructureType mediumTree15() {
        return fromId(365);
    }

    public static StructureType bigTree15() {
        return fromId(366);
    }

    public static StructureType smallRock16() {
        return fromId(367);
    }

    public static StructureType mediumRock16() {
        return fromId(368);
    }

    public static StructureType bigRock16() {
        return fromId(369);
    }

    public static StructureType smallTree16() {
        return fromId(370);
    }

    public static StructureType mediumTree16() {
        return fromId(371);
    }

    public static StructureType bigTree16() {
        return fromId(372);
    }

    public static StructureType waxeritePath() {
        return fromId(373);
    }

    public static StructureType burngundyPath() {
        return fromId(374);
    }

    public static StructureType florislavaPath() {
        return fromId(375);
    }

    public static StructureType goldsealPath() {
        return fromId(376);
    }

    public static StructureType ignoxiousPath() {
        return fromId(377);
    }

    public static StructureType redBubbleritePath() {
        return fromId(378);
    }

    public static StructureType unsnuffableOutcropping() {
        return fromId(379);
    }

    public static StructureType coldmanColloquy() {
        return fromId(380);
    }

    public static StructureType lamppost() {
        return fromId(381);
    }

    public static StructureType gingerbreadBluff() {
        return fromId(382);
    }

    public static StructureType yaytideTrimmings() {
        return fromId(383);
    }

    public static StructureType luffBalloons() {
        return fromId(384);
    }

    public static StructureType gargleoyle() {
        return fromId(385);
    }

    public static StructureType curiousCultivar() {
        return fromId(386);
    }

    public static StructureType chokkolitFountain() {
        return fromId(387);
    }

    public static StructureType floofyBasket() {
        return fromId(388);
    }

    public static StructureType rottenEgg() {
        return fromId(389);
    }

    public static StructureType ginormousCarrot() {
        return fromId(390);
    }

    public static StructureType multiversalPhabberjay() {
        return fromId(391);
    }

    public static StructureType basicCastle9() {
        return fromId(392);
    }

    public static StructureType modestCastle9() {
        return fromId(393);
    }

    public static StructureType comfortableCastle9() {
        return fromId(394);
    }

    public static StructureType spaciousCastle9() {
        return fromId(395);
    }

    public static StructureType luxuriousCastle9() {
        return fromId(396);
    }

    public static StructureType extravagantCastle9() {
        return fromId(397);
    }

    public static StructureType paradiseCastle9() {
        return fromId(398);
    }

    public static StructureType smallRock17() {
        return fromId(399);
    }

    public static StructureType mediumRock17() {
        return fromId(400);
    }

    public static StructureType bigRock17() {
        return fromId(401);
    }

    public static StructureType smallTree17() {
        return fromId(402);
    }

    public static StructureType mediumTree17() {
        return fromId(403);
    }

    public static StructureType bigTree17() {
        return fromId(404);
    }

    public static StructureType smallRock18() {
        return fromId(405);
    }

    public static StructureType mediumRock18() {
        return fromId(406);
    }

    public static StructureType bigRock18() {
        return fromId(407);
    }

    public static StructureType smallTree18() {
        return fromId(408);
    }

    public static StructureType mediumTree18() {
        return fromId(409);
    }

    public static StructureType bigTree18() {
        return fromId(410);
    }

    public static StructureType turquoiseglassPath() {
        return fromId(411);
    }

    public static StructureType orangeglassPath() {
        return fromId(412);
    }

    public static StructureType greenglassPath() {
        return fromId(413);
    }

    public static StructureType purpleglassPath() {
        return fromId(414);
    }

    public static StructureType redglassPath() {
        return fromId(415);
    }

    public static StructureType yellowglassPath() {
        return fromId(416);
    }

    public static StructureType blueglassPath() {
        return fromId(417);
    }

    public static StructureType glassblownPlate() {
        return fromId(418);
    }

    public static StructureType beachChair() {
        return fromId(419);
    }

    public static StructureType granularCastle() {
        return fromId(420);
    }

    public static StructureType hutofaThousandPerfumes() {
        return fromId(421);
    }

    public static StructureType hotTub() {
        return fromId(422);
    }

    public static StructureType basicCastle10() {
        return fromId(423);
    }

    public static StructureType modestCastle10() {
        return fromId(424);
    }

    public static StructureType comfortableCastle10() {
        return fromId(425);
    }

    public static StructureType spaciousCastle10() {
        return fromId(426);
    }

    public static StructureType luxuriousCastle10() {
        return fromId(427);
    }

    public static StructureType extravagantCastle10() {
        return fromId(428);
    }

    public static StructureType paradiseCastle10() {
        return fromId(429);
    }

    public static StructureType smallRock19() {
        return fromId(430);
    }

    public static StructureType mediumRock19() {
        return fromId(431);
    }

    public static StructureType bigRock19() {
        return fromId(432);
    }

    public static StructureType smallTree19() {
        return fromId(433);
    }

    public static StructureType mediumTree19() {
        return fromId(434);
    }

    public static StructureType bigTree19() {
        return fromId(435);
    }

    public static StructureType smallRock20() {
        return fromId(436);
    }

    public static StructureType mediumRock20() {
        return fromId(437);
    }

    public static StructureType bigRock20() {
        return fromId(438);
    }

    public static StructureType smallTree20() {
        return fromId(439);
    }

    public static StructureType mediumTree20() {
        return fromId(440);
    }

    public static StructureType bigTree20() {
        return fromId(441);
    }

    public static StructureType mahrouneScalePath() {
        return fromId(442);
    }

    public static StructureType orkidScalePath() {
        return fromId(443);
    }

    public static StructureType flawlessScalePath() {
        return fromId(444);
    }

    public static StructureType flawfulScalePath() {
        return fromId(445);
    }

    public static StructureType indyygoScalePath() {
        return fromId(446);
    }

    public static StructureType flawedScalePath() {
        return fromId(447);
    }

    public static StructureType tentacleEruption() {
        return fromId(448);
    }

    public static StructureType tentacleParabola() {
        return fromId(449);
    }

    public static StructureType inverdigusFern() {
        return fromId(450);
    }

    public static StructureType jarhead() {
        return fromId(451);
    }

    public static StructureType mountainMorsel() {
        return fromId(452);
    }

    public static StructureType thumpiesTotem() {
        return fromId(453);
    }

    public static StructureType wubboxStockpile() {
        return fromId(454);
    }

    public static StructureType tentacleBreach() {
        return fromId(455);
    }

    public static StructureType spooktacleTravelersSign() {
        return fromId(456);
    }

    public static StructureType spooktacleSmunkinPatch() {
        return fromId(457);
    }

    public static StructureType smallFeastEmberRock() {
        return fromId(458);
    }

    public static StructureType mediumFeastEmberRock() {
        return fromId(459);
    }

    public static StructureType bigFeastEmberRock() {
        return fromId(460);
    }

    public static StructureType smallFeastEmberTree() {
        return fromId(461);
    }

    public static StructureType mediumFeastEmberTree() {
        return fromId(462);
    }

    public static StructureType bigFeastEmberTree() {
        return fromId(463);
    }

    public static StructureType basicCastle11() {
        return fromId(464);
    }

    public static StructureType modestCastle11() {
        return fromId(465);
    }

    public static StructureType comfortableCastle11() {
        return fromId(466);
    }

    public static StructureType spaciousCastle11() {
        return fromId(467);
    }

    public static StructureType luxuriousCastle11() {
        return fromId(468);
    }

    public static StructureType extravagantCastle11() {
        return fromId(469);
    }

    public static StructureType paradiseCastle11() {
        return fromId(470);
    }

    public static StructureType smallRock21() {
        return fromId(471);
    }

    public static StructureType mediumRock21() {
        return fromId(472);
    }

    public static StructureType bigRock21() {
        return fromId(473);
    }

    public static StructureType smallTree21() {
        return fromId(474);
    }

    public static StructureType mediumTree21() {
        return fromId(475);
    }

    public static StructureType bigTree21() {
        return fromId(476);
    }

    public static StructureType smallRock22() {
        return fromId(477);
    }

    public static StructureType mediumRock22() {
        return fromId(478);
    }

    public static StructureType bigRock22() {
        return fromId(479);
    }

    public static StructureType smallTree22() {
        return fromId(480);
    }

    public static StructureType mediumTree22() {
        return fromId(481);
    }

    public static StructureType bigTree22() {
        return fromId(482);
    }

    public static StructureType skipstonePath() {
        return fromId(483);
    }

    public static StructureType skiprockPath() {
        return fromId(484);
    }

    public static StructureType skippebblePath() {
        return fromId(485);
    }

    public static StructureType roobyootyPath() {
        return fromId(486);
    }

    public static StructureType tacitrinePath() {
        return fromId(487);
    }

    public static StructureType passapphirePath() {
        return fromId(488);
    }

    public static StructureType ticklepinkPath() {
        return fromId(489);
    }

    public static StructureType ephemeraldPath() {
        return fromId(490);
    }

    public static StructureType basicCastle12() {
        return fromId(491);
    }

    public static StructureType modestCastle12() {
        return fromId(492);
    }

    public static StructureType comfortableCastle12() {
        return fromId(493);
    }

    public static StructureType spaciousCastle12() {
        return fromId(494);
    }

    public static StructureType luxuriousCastle12() {
        return fromId(495);
    }

    public static StructureType extravagantCastle12() {
        return fromId(496);
    }

    public static StructureType paradiseCastle12() {
        return fromId(497);
    }

    public static StructureType smallRock23() {
        return fromId(498);
    }

    public static StructureType mediumRock23() {
        return fromId(499);
    }

    public static StructureType bigRock23() {
        return fromId(500);
    }

    public static StructureType smallTree23() {
        return fromId(501);
    }

    public static StructureType mediumTree23() {
        return fromId(502);
    }

    public static StructureType bigTree23() {
        return fromId(503);
    }

    public static StructureType smallRock24() {
        return fromId(504);
    }

    public static StructureType mediumRock24() {
        return fromId(505);
    }

    public static StructureType bigRock24() {
        return fromId(506);
    }

    public static StructureType smallTree24() {
        return fromId(507);
    }

    public static StructureType mediumTree24() {
        return fromId(508);
    }

    public static StructureType bigTree24() {
        return fromId(509);
    }

    public static StructureType ammonicePath() {
        return fromId(510);
    }

    public static StructureType plantipedePath() {
        return fromId(511);
    }

    public static StructureType boneSigilPath() {
        return fromId(512);
    }

    public static StructureType spinousPath() {
        return fromId(513);
    }

    public static StructureType northosseousPath() {
        return fromId(514);
    }

    public static StructureType southosseousPath() {
        return fromId(515);
    }

    public static StructureType eeeweeosseousPath() {
        return fromId(516);
    }

    public static StructureType diagosseousPath() {
        return fromId(517);
    }

    public static StructureType vegidianCastle() {
        return fromId(518);
    }

    public static StructureType polishedVegidianCastle() {
        return fromId(519);
    }

    public static StructureType carvedVegidianCastle() {
        return fromId(520);
    }

    public static StructureType froziumCastle() {
        return fromId(521);
    }

    public static StructureType polishedFroziumCastle() {
        return fromId(522);
    }

    public static StructureType carvedFroziumCastle() {
        return fromId(523);
    }

    public static StructureType skyliteCastle() {
        return fromId(524);
    }

    public static StructureType polishedSkyliteCastle() {
        return fromId(525);
    }

    public static StructureType carvedSkyliteCastle() {
        return fromId(526);
    }

    public static StructureType aquanineCastle() {
        return fromId(527);
    }

    public static StructureType polishedAquanineCastle() {
        return fromId(528);
    }

    public static StructureType carvedAquanineCastle() {
        return fromId(529);
    }

    public static StructureType stonyxCastle() {
        return fromId(530);
    }

    public static StructureType polishedStonyxCastle() {
        return fromId(531);
    }

    public static StructureType carvedStonyxCastle() {
        return fromId(532);
    }

    public static StructureType conservatory() {
        return fromId(533);
    }

    public static StructureType messHall() {
        return fromId(534);
    }

    public static StructureType nursery2() {
        return fromId(535);
    }

    public static StructureType starRocks6() {
        return fromId(536);
    }

    public static StructureType starRocks7() {
        return fromId(537);
    }

    public static StructureType starRocks8() {
        return fromId(538);
    }

    public static StructureType starRocks9() {
        return fromId(539);
    }

    public static StructureType starRocks10() {
        return fromId(540);
    }

    public static StructureType renownedConservatory() {
        return fromId(541);
    }

    public static StructureType prestigiousConservatory() {
        return fromId(542);
    }

    public static StructureType campaignTrophy() {
        return fromId(544);
    }

    public static StructureType messyMessHall() {
        return fromId(545);
    }

    public static StructureType extraMessyMessHall() {
        return fromId(546);
    }

    public static StructureType simbullTree() {
        return fromId(547);
    }

    public static StructureType gaungTree() {
        return fromId(548);
    }

    public static StructureType logDrum() {
        return fromId(549);
    }

    public static StructureType tamborineTrampoline() {
        return fromId(550);
    }

    public static StructureType twihorn() {
        return fromId(551);
    }

    public static StructureType campaignTrophy2() {
        return fromId(552);
    }

    public static StructureType campaignTrophy3() {
        return fromId(553);
    }

    public static StructureType campaignTrophy4() {
        return fromId(554);
    }

    public static StructureType campaignTrophy5() {
        return fromId(555);
    }

    public static StructureType campaignTrophy6() {
        return fromId(556);
    }

    public static StructureType campaignTrophy7() {
        return fromId(557);
    }

    public static StructureType campaignTrophy8() {
        return fromId(558);
    }

    public static StructureType campaignTrophy9() {
        return fromId(559);
    }

    public static StructureType campaignTrophy10() {
        return fromId(560);
    }

    public static StructureType campaignTrophy11() {
        return fromId(561);
    }

    public static StructureType campaignTrophy12() {
        return fromId(562);
    }

    public static StructureType campaignTrophy13() {
        return fromId(563);
    }

    public static StructureType campaignTrophy14() {
        return fromId(564);
    }

    public static StructureType campaignTrophy15() {
        return fromId(565);
    }

    public static StructureType campaignTrophy16() {
        return fromId(566);
    }

    public static StructureType campaignTrophy17() {
        return fromId(567);
    }

    public static StructureType campaignTrophy18() {
        return fromId(568);
    }

    public static StructureType campaignTrophy19() {
        return fromId(569);
    }

    public static StructureType campaignTrophy20() {
        return fromId(570);
    }

    public static StructureType campaignTrophy21() {
        return fromId(571);
    }

    public static StructureType campaignTrophy22() {
        return fromId(572);
    }

    public static StructureType campaignTrophy23() {
        return fromId(573);
    }

    public static StructureType campaignTrophy24() {
        return fromId(574);
    }

    public static StructureType campaignTrophy25() {
        return fromId(575);
    }

    public static StructureType campaignTrophy26() {
        return fromId(576);
    }

    public static StructureType campaignTrophy27() {
        return fromId(577);
    }

    public static StructureType campaignTrophy28() {
        return fromId(578);
    }

    public static StructureType campaignTrophy29() {
        return fromId(579);
    }

    public static StructureType campaignTrophy30() {
        return fromId(580);
    }

    public static StructureType campaignTrophy31() {
        return fromId(581);
    }

    public static StructureType campaignTrophy32() {
        return fromId(582);
    }

    public static StructureType basicCastle13() {
        return fromId(583);
    }

    public static StructureType modestCastle13() {
        return fromId(584);
    }

    public static StructureType comfortableCastle13() {
        return fromId(585);
    }

    public static StructureType spaciousCastle13() {
        return fromId(586);
    }

    public static StructureType luxuriousCastle13() {
        return fromId(587);
    }

    public static StructureType extravagantCastle13() {
        return fromId(588);
    }

    public static StructureType paradiseCastle13() {
        return fromId(589);
    }

    public static StructureType smallRock25() {
        return fromId(590);
    }

    public static StructureType mediumRock25() {
        return fromId(591);
    }

    public static StructureType bigRock25() {
        return fromId(592);
    }

    public static StructureType smallTree25() {
        return fromId(593);
    }

    public static StructureType mediumTree25() {
        return fromId(594);
    }

    public static StructureType bigTree25() {
        return fromId(595);
    }

    public static StructureType smallRock26() {
        return fromId(596);
    }

    public static StructureType mediumRock26() {
        return fromId(597);
    }

    public static StructureType bigRock26() {
        return fromId(598);
    }

    public static StructureType smallTree26() {
        return fromId(599);
    }

    public static StructureType mediumTree26() {
        return fromId(600);
    }

    public static StructureType bigTree26() {
        return fromId(601);
    }

    public static StructureType enhancedNursery2() {
        return fromId(602);
    }

    public static StructureType sparkulPath() {
        return fromId(603);
    }

    public static StructureType glittuhPath() {
        return fromId(604);
    }

    public static StructureType ocherporePath() {
        return fromId(605);
    }

    public static StructureType siennaporePath() {
        return fromId(606);
    }

    public static StructureType cyanoporePath() {
        return fromId(607);
    }

    public static StructureType ingroanPath() {
        return fromId(608);
    }

    public static StructureType angroanPath() {
        return fromId(609);
    }

    public static StructureType eksboksPath() {
        return fromId(610);
    }

    public static StructureType craftServicesTable() {
        return fromId(611);
    }

    public static StructureType critterFountain() {
        return fromId(612);
    }

    public static StructureType costumeRack() {
        return fromId(613);
    }

    public static StructureType spurritofAdventureModel() {
        return fromId(614);
    }

    public static StructureType monstrolympicTorch() {
        return fromId(615);
    }

    public static StructureType bronzeChampionsCrown() {
        return fromId(616);
    }

    public static StructureType silverChampionsCrown() {
        return fromId(617);
    }

    public static StructureType goldChampionsCrown() {
        return fromId(618);
    }

    public static StructureType starRocks11() {
        return fromId(619);
    }

    public static StructureType campaignTrophy33() {
        return fromId(620);
    }

    public static StructureType starRocks12() {
        return fromId(621);
    }

    public static StructureType starRocks13() {
        return fromId(622);
    }

    public static StructureType campaignTrophy34() {
        return fromId(623);
    }

    public static StructureType basicCastle14() {
        return fromId(624);
    }

    public static StructureType modestCastle14() {
        return fromId(625);
    }

    public static StructureType comfortableCastle14() {
        return fromId(626);
    }

    public static StructureType spaciousCastle14() {
        return fromId(627);
    }

    public static StructureType luxuriousCastle14() {
        return fromId(628);
    }

    public static StructureType extravagantCastle14() {
        return fromId(629);
    }

    public static StructureType paradiseCastle14() {
        return fromId(630);
    }

    public static StructureType smallRock27() {
        return fromId(631);
    }

    public static StructureType mediumRock27() {
        return fromId(632);
    }

    public static StructureType bigRock27() {
        return fromId(633);
    }

    public static StructureType smallTree27() {
        return fromId(634);
    }

    public static StructureType mediumTree27() {
        return fromId(635);
    }

    public static StructureType bigTree27() {
        return fromId(636);
    }

    public static StructureType smallRock28() {
        return fromId(637);
    }

    public static StructureType mediumRock28() {
        return fromId(638);
    }

    public static StructureType bigRock28() {
        return fromId(639);
    }

    public static StructureType smallTree28() {
        return fromId(640);
    }

    public static StructureType mediumTree28() {
        return fromId(641);
    }

    public static StructureType bigTree28() {
        return fromId(642);
    }

    public static StructureType campaignTrophy35() {
        return fromId(643);
    }

    public static StructureType etherealPocketPath() {
        return fromId(644);
    }

    public static StructureType magicalPocketPath() {
        return fromId(645);
    }

    public static StructureType gatePocketPath() {
        return fromId(646);
    }

    public static StructureType teleportalSignPath() {
        return fromId(647);
    }

    public static StructureType rubbleroughPath() {
        return fromId(648);
    }

    public static StructureType glowcirclePath() {
        return fromId(649);
    }

    public static StructureType glowtargetPath() {
        return fromId(650);
    }

    public static StructureType glowpatchyPath() {
        return fromId(651);
    }

    public static StructureType fandemoniumStageMaquette() {
        return fromId(652);
    }

    public static StructureType campaignTrophy36() {
        return fromId(653);
    }

    public static StructureType basicCastle15() {
        return fromId(654);
    }

    public static StructureType modestCastle15() {
        return fromId(655);
    }

    public static StructureType comfortableCastle15() {
        return fromId(656);
    }

    public static StructureType spaciousCastle15() {
        return fromId(657);
    }

    public static StructureType luxuriousCastle15() {
        return fromId(658);
    }

    public static StructureType extravagantCastle15() {
        return fromId(659);
    }

    public static StructureType paradiseCastle15() {
        return fromId(660);
    }

    public static StructureType smallRock29() {
        return fromId(661);
    }

    public static StructureType mediumRock29() {
        return fromId(662);
    }

    public static StructureType bigRock29() {
        return fromId(663);
    }

    public static StructureType smallTree29() {
        return fromId(664);
    }

    public static StructureType mediumTree29() {
        return fromId(665);
    }

    public static StructureType bigTree29() {
        return fromId(666);
    }

    public static StructureType smallRock30() {
        return fromId(667);
    }

    public static StructureType mediumRock30() {
        return fromId(668);
    }

    public static StructureType bigRock30() {
        return fromId(669);
    }

    public static StructureType smallTree30() {
        return fromId(670);
    }

    public static StructureType mediumTree30() {
        return fromId(671);
    }

    public static StructureType bigTree30() {
        return fromId(672);
    }

    public static StructureType spooktaclePath() {
        return fromId(673);
    }

    public static StructureType festivalofYayPath() {
        return fromId(674);
    }

    public static StructureType seasonofLovePath() {
        return fromId(675);
    }

    public static StructureType eggsTravaganzaPath() {
        return fromId(676);
    }

    public static StructureType summerSongPath() {
        return fromId(677);
    }

    public static StructureType feastEmberPath() {
        return fromId(678);
    }

    public static StructureType anniversaryMonthPath() {
        return fromId(679);
    }

    public static StructureType upperFringePath() {
        return fromId(680);
    }

    public static StructureType lowerFringePath() {
        return fromId(681);
    }

    public static StructureType campaignTrophy37() {
        return fromId(682);
    }

    public static StructureType BlastOffLaunchpad() {
        return fromId(683);
    }

    public static StructureType wubHubEdifice() {
        return fromId(684);
    }

    public static StructureType MonsterRallyRacetrack() {
        return fromId(685);
    }

    public static StructureType CannonChaosContraption() {
        return fromId(686);
    }

    public static StructureType gobbleyInflatable() {
        return fromId(687);
    }

    public static StructureType monsterScareglowls() {
        return fromId(688);
    }

    public static StructureType carnivorousCornucopia() {
        return fromId(689);
    }

    public static StructureType oversizedPies() {
        return fromId(690);
    }

    public static StructureType beatHereafterPath() {
        return fromId(691);
    }

    public static StructureType amberCastle() {
        return fromId(692);
    }

    public static StructureType amberCastle2() {
        return fromId(693);
    }

    public static StructureType amberCastle3() {
        return fromId(694);
    }

    public static StructureType amberCastle4() {
        return fromId(695);
    }

    public static StructureType amberCastle5() {
        return fromId(696);
    }

    public static StructureType amberCastle6() {
        return fromId(697);
    }

    public static StructureType amberCastle7() {
        return fromId(698);
    }

    public static StructureType smallRock31() {
        return fromId(699);
    }

    public static StructureType mediumRock31() {
        return fromId(700);
    }

    public static StructureType bigRock31() {
        return fromId(701);
    }

    public static StructureType smallTree31() {
        return fromId(702);
    }

    public static StructureType mediumTree31() {
        return fromId(703);
    }

    public static StructureType bigTree31() {
        return fromId(704);
    }

    public static StructureType smallRock32() {
        return fromId(705);
    }

    public static StructureType mediumRock32() {
        return fromId(706);
    }

    public static StructureType bigRock32() {
        return fromId(707);
    }

    public static StructureType smallTree32() {
        return fromId(708);
    }

    public static StructureType mediumTree32() {
        return fromId(709);
    }

    public static StructureType bigTree32() {
        return fromId(710);
    }

    public static StructureType crucible() {
        return fromId(711);
    }

    public static StructureType crescendoMoonPath() {
        return fromId(712);
    }

    public static StructureType pyroziteCastle() {
        return fromId(713);
    }

    public static StructureType polishedPyroziteCastle() {
        return fromId(714);
    }

    public static StructureType carvedPyroziteCastle() {
        return fromId(715);
    }

    public static StructureType pyroziteCastle2() {
        return fromId(716);
    }

    public static StructureType polishedPyroziteCastle2() {
        return fromId(717);
    }

    public static StructureType carvedPyroziteCastle2() {
        return fromId(718);
    }

    public static StructureType contemporaryAirSigilPath() {
        return fromId(719);
    }

    public static StructureType contemporaryPlantSigilPath() {
        return fromId(720);
    }

    public static StructureType contemporaryEarthSigilPath() {
        return fromId(721);
    }

    public static StructureType contemporaryWaterSigilPath() {
        return fromId(722);
    }

    public static StructureType contemporaryColdSigilPath() {
        return fromId(723);
    }

    public static StructureType contemporaryFireSigilPath() {
        return fromId(724);
    }

    public static StructureType traditionalAirSigilPath() {
        return fromId(725);
    }

    public static StructureType traditionalPlantSigilPath() {
        return fromId(726);
    }

    public static StructureType traditionalEarthSigilPath() {
        return fromId(727);
    }

    public static StructureType traditionalWaterSigilPath() {
        return fromId(728);
    }

    public static StructureType traditionalColdSigilPath() {
        return fromId(729);
    }

    public static StructureType traditionalFireSigilPath() {
        return fromId(730);
    }

    public static StructureType cloverspellPath() {
        return fromId(731);
    }

    public static StructureType echoesofEcoPath() {
        return fromId(732);
    }

    public static StructureType perplexplorePath() {
        return fromId(733);
    }

    public static StructureType lifeFormulaPath() {
        return fromId(734);
    }

    public static StructureType skyPaintingPath() {
        return fromId(735);
    }

    public static StructureType nebuloxCastle() {
        return fromId(736);
    }

    public static StructureType polishedNebuloxCastle() {
        return fromId(737);
    }

    public static StructureType carvedNebuloxCastle() {
        return fromId(738);
    }

    public static StructureType auroriumCastle() {
        return fromId(739);
    }

    public static StructureType polishedAuroriumCastle() {
        return fromId(740);
    }

    public static StructureType carvedAuroriumCastle() {
        return fromId(741);
    }

    public static StructureType quarritzCastle() {
        return fromId(742);
    }

    public static StructureType polishedQuarritzCastle() {
        return fromId(743);
    }

    public static StructureType carvedQuarritzCastle() {
        return fromId(744);
    }

    public static StructureType confettiteCastle() {
        return fromId(745);
    }

    public static StructureType polishedConfettiteCastle() {
        return fromId(746);
    }

    public static StructureType carvedConfettiteCastle() {
        return fromId(747);
    }

    public static StructureType mindBogglePath() {
        return fromId(748);
    }

    public static StructureType anniversaryMonument1st() {
        return fromId(749);
    }

    public static StructureType anniversaryMonument2nd() {
        return fromId(750);
    }

    public static StructureType anniversaryMonument3rd() {
        return fromId(751);
    }

    public static StructureType anniversaryMonument4th() {
        return fromId(752);
    }

    public static StructureType anniversaryMonument5th() {
        return fromId(753);
    }

    public static StructureType anniversaryMonument6th() {
        return fromId(754);
    }

    public static StructureType anniversaryMonument7th() {
        return fromId(755);
    }

    public static StructureType anniversaryMonument8th() {
        return fromId(756);
    }

    public static StructureType anniversaryMonument9th() {
        return fromId(757);
    }

    public static StructureType anniversaryMonument10th() {
        return fromId(758);
    }

    public static StructureType miniatureIslandComplex() {
        return fromId(759);
    }

    public static StructureType optiglowbe() {
        return fromId(760);
    }

    public static StructureType shrini() {
        return fromId(761);
    }

    public static StructureType orbofAklino() {
        return fromId(762);
    }

    public static StructureType kabsWax() {
        return fromId(763);
    }

    public static StructureType machoMonument() {
        return fromId(764);
    }

    public static StructureType harmonorbHolder() {
        return fromId(765);
    }

    public static StructureType caveCrystalTeleporter() {
        return fromId(766);
    }

    public static StructureType englobulatedFUM() {
        return fromId(767);
    }

    public static StructureType oddoMobile() {
        return fromId(768);
    }

    public static StructureType amberCastle8() {
        return fromId(769);
    }

    public static StructureType basicCastle16() {
        return fromId(770);
    }

    public static StructureType modestCastle16() {
        return fromId(771);
    }

    public static StructureType comfortableCastle16() {
        return fromId(772);
    }

    public static StructureType spaciousCastle16() {
        return fromId(773);
    }

    public static StructureType luxuriousCastle16() {
        return fromId(774);
    }

    public static StructureType extravagantCastle16() {
        return fromId(775);
    }

    public static StructureType paradiseCastle16() {
        return fromId(776);
    }

    public static StructureType smallRock33() {
        return fromId(777);
    }

    public static StructureType mediumRock33() {
        return fromId(778);
    }

    public static StructureType bigRock33() {
        return fromId(779);
    }

    public static StructureType smallTree33() {
        return fromId(780);
    }

    public static StructureType mediumTree33() {
        return fromId(781);
    }

    public static StructureType bigTree33() {
        return fromId(782);
    }

    public static StructureType smallRock34() {
        return fromId(783);
    }

    public static StructureType mediumRock34() {
        return fromId(784);
    }

    public static StructureType bigRock34() {
        return fromId(785);
    }

    public static StructureType smallTree34() {
        return fromId(786);
    }

    public static StructureType mediumTree34() {
        return fromId(787);
    }

    public static StructureType bigTree34() {
        return fromId(788);
    }

    public static StructureType elcricPath() {
        return fromId(789);
    }

    public static StructureType elgnairtPath() {
        return fromId(790);
    }

    public static StructureType erauqsPath() {
        return fromId(791);
    }

    public static StructureType ssorcPath() {
        return fromId(792);
    }

    public static StructureType norewopPath() {
        return fromId(793);
    }

    public static StructureType mythicalMoonPath() {
        return fromId(794);
    }

    public static StructureType smallBeatHereafterRock() {
        return fromId(795);
    }

    public static StructureType mediumBeatHereafterRock() {
        return fromId(796);
    }

    public static StructureType bigBeatHereafterRock() {
        return fromId(797);
    }

    public static StructureType smallBeatHereafterTree() {
        return fromId(798);
    }

    public static StructureType mediumBeatHereafterTree() {
        return fromId(799);
    }

    public static StructureType bigBeatHereafterTree() {
        return fromId(800);
    }

    public static StructureType colossEye() {
        return fromId(801);
    }

    public static StructureType krystilliumCastle() {
        return fromId(802);
    }

    public static StructureType polishedKrystilliumCastle() {
        return fromId(803);
    }

    public static StructureType carvedKrystilliumCastle() {
        return fromId(804);
    }

    public static StructureType krystilliumCastle2() {
        return fromId(805);
    }

    public static StructureType polishedKrystilliumCastle2() {
        return fromId(806);
    }

    public static StructureType carvedKrystilliumCastle2() {
        return fromId(807);
    }

    public static StructureType smallCrescendoMoonRock() {
        return fromId(808);
    }

    public static StructureType mediumCrescendoMoonRock() {
        return fromId(809);
    }

    public static StructureType bigCrescendoMoonRock() {
        return fromId(810);
    }

    public static StructureType smallCrescendoMoonTree() {
        return fromId(811);
    }

    public static StructureType mediumCrescendoMoonTree() {
        return fromId(812);
    }

    public static StructureType bigCrescendoMoonTree() {
        return fromId(813);
    }

    public static StructureType colossEye2() {
        return fromId(814);
    }

    public static StructureType smallCloverspellRock() {
        return fromId(815);
    }

    public static StructureType mediumCloverspellRock() {
        return fromId(816);
    }

    public static StructureType bigCloverspellRock() {
        return fromId(817);
    }

    public static StructureType smallCloverspellTree() {
        return fromId(818);
    }

    public static StructureType mediumCloverspellTree() {
        return fromId(819);
    }

    public static StructureType bigCloverspellTree() {
        return fromId(820);
    }

    public static StructureType enhancedCrucible() {
        return fromId(821);
    }

    public static StructureType amberCastle9() {
        return fromId(822);
    }

    public static StructureType smallEchoesofEcoRock() {
        return fromId(823);
    }

    public static StructureType mediumEchoesofEcoRock() {
        return fromId(824);
    }

    public static StructureType bigEchoesofEcoRock() {
        return fromId(825);
    }

    public static StructureType smallEchoesofEcoTree() {
        return fromId(826);
    }

    public static StructureType mediumEchoesofEcoTree() {
        return fromId(827);
    }

    public static StructureType bigEchoesofEcoTree() {
        return fromId(828);
    }

    public static StructureType colossEye3() {
        return fromId(829);
    }

    public static StructureType smallPerplexploreRock() {
        return fromId(830);
    }

    public static StructureType mediumPerplexploreRock() {
        return fromId(831);
    }

    public static StructureType bigPerplexploreRock() {
        return fromId(832);
    }

    public static StructureType smallPerplexploreTree() {
        return fromId(833);
    }

    public static StructureType mediumPerplexploreTree() {
        return fromId(834);
    }

    public static StructureType bigPerplexploreTree() {
        return fromId(835);
    }

    public static StructureType smallLifeFormulaRock() {
        return fromId(836);
    }

    public static StructureType mediumLifeFormulaRock() {
        return fromId(837);
    }

    public static StructureType bigLifeFormulaRock() {
        return fromId(838);
    }

    public static StructureType smallLifeFormulaTree() {
        return fromId(839);
    }

    public static StructureType mediumLifeFormulaTree() {
        return fromId(840);
    }

    public static StructureType bigLifeFormulaTree() {
        return fromId(841);
    }

    public static StructureType campaignTrophy38() {
        return fromId(842);
    }

    public static StructureType campaignTrophy39() {
        return fromId(843);
    }

    public static StructureType campaignTrophy40() {
        return fromId(844);
    }

    public static StructureType campaignTrophy41() {
        return fromId(845);
    }

    public static StructureType campaignTrophy42() {
        return fromId(846);
    }

    public static StructureType colossEye4() {
        return fromId(847);
    }

    public static StructureType phoniMine() {
        return fromId(848);
    }

    public static StructureType smallSkyPaintingRock() {
        return fromId(849);
    }

    public static StructureType mediumSkyPaintingRock() {
        return fromId(850);
    }

    public static StructureType bigSkyPaintingRock() {
        return fromId(851);
    }

    public static StructureType smallSkyPaintingTree() {
        return fromId(852);
    }

    public static StructureType mediumSkyPaintingTree() {
        return fromId(853);
    }

    public static StructureType bigSkyPaintingTree() {
        return fromId(854);
    }

    public static StructureType colossEye5() {
        return fromId(855);
    }

    public static StructureType attunementStructure() {
        return fromId(856);
    }

    public static StructureType synthesizer() {
        return fromId(857);
    }

    public static StructureType basicCastle17() {
        return fromId(858);
    }

    public static StructureType modestCastle17() {
        return fromId(859);
    }

    public static StructureType comfortableCastle17() {
        return fromId(860);
    }

    public static StructureType spaciousCastle17() {
        return fromId(861);
    }

    public static StructureType luxuriousCastle17() {
        return fromId(862);
    }

    public static StructureType extravagantCastle17() {
        return fromId(863);
    }

    public static StructureType smallMindBoggleRock() {
        return fromId(867);
    }

    public static StructureType mediumMindBoggleRock() {
        return fromId(868);
    }

    public static StructureType bigMindBoggleRock() {
        return fromId(869);
    }

    public static StructureType smallMindBoggleTree() {
        return fromId(870);
    }

    public static StructureType mediumMindBoggleTree() {
        return fromId(871);
    }

    public static StructureType bigMindBoggleTree() {
        return fromId(872);
    }

    public static StructureType spiritofCreation() {
        return fromId(873);
    }

    public static StructureType anniversaryMonument11th() {
        return fromId(874);
    }

    public static StructureType smallRock35() {
        return fromId(875);
    }

    public static StructureType mediumRock35() {
        return fromId(876);
    }

    public static StructureType bigRock35() {
        return fromId(877);
    }

    public static StructureType smallTree35() {
        return fromId(878);
    }

    public static StructureType mediumTree35() {
        return fromId(879);
    }

    public static StructureType bigTree35() {
        return fromId(880);
    }

    public static StructureType smallRock36() {
        return fromId(881);
    }

    public static StructureType mediumRock36() {
        return fromId(882);
    }

    public static StructureType bigRock36() {
        return fromId(883);
    }

    public static StructureType smallTree36() {
        return fromId(884);
    }

    public static StructureType mediumTree36() {
        return fromId(885);
    }

    public static StructureType bigTree36() {
        return fromId(886);
    }

    public static StructureType makeshiftAltar() {
        return fromId(888);
    }

    public static StructureType heartstringsStirring() {
        return fromId(889);
    }

    public static StructureType ladyCalaca() {
        return fromId(890);
    }

    public static StructureType littleDrummer() {
        return fromId(891);
    }

    public static StructureType refinedSynthesizer() {
        return fromId(892);
    }

    public static StructureType starRocks14() {
        return fromId(893);
    }

    public static StructureType starRocks15() {
        return fromId(894);
    }

    public static StructureType starRocks16() {
        return fromId(895);
    }

    public static StructureType campaignTrophy43() {
        return fromId(896);
    }

    public static StructureType campaignTrophy44() {
        return fromId(897);
    }

    public static StructureType campaignTrophy45() {
        return fromId(898);
    }

    public static StructureType campaignTrophy46() {
        return fromId(899);
    }

    public static StructureType campaignTrophy47() {
        return fromId(900);
    }

    public static StructureType campaignTrophy48() {
        return fromId(901);
    }

    public static StructureType campaignTrophy49() {
        return fromId(902);
    }

    public static StructureType meeboidPath() {
        return fromId(903);
    }

    public static StructureType stromalPath() {
        return fromId(904);
    }

    public static StructureType photoreceptorPath() {
        return fromId(905);
    }

    public static StructureType endothelialPath() {
        return fromId(906);
    }

    public static StructureType glialPath() {
        return fromId(907);
    }

    public static StructureType monstrochondrialPath() {
        return fromId(908);
    }

    public static StructureType clarinedge() {
        return fromId(909);
    }

    public static StructureType ottavine() {
        return fromId(910);
    }

    public static StructureType stoneDrum() {
        return fromId(911);
    }

    public static StructureType strummyache() {
        return fromId(912);
    }

    public static StructureType grennitchsGambitTrophy() {
        return fromId(913);
    }

    public static StructureType frigilsFeatTrophy() {
        return fromId(914);
    }

    public static StructureType zeffreesZenithTrophy() {
        return fromId(915);
    }

    public static StructureType hyddrydsHurdleTrophy() {
        return fromId(916);
    }

    public static StructureType sollumsStratagemTrophy() {
        return fromId(917);
    }

    public static StructureType floatingLanterns() {
        return fromId(918);
    }

    public static StructureType jadeLion() {
        return fromId(919);
    }

    public static StructureType magicEnvelope() {
        return fromId(920);
    }

    public static StructureType peonyWheel() {
        return fromId(921);
    }

    public static StructureType nexusNucleus() {
        return fromId(922);
    }

    public static StructureType hyperRefinedSynthesizer() {
        return fromId(923);
    }

    public static StructureType greatTreeBawnzi() {
        return fromId(924);
    }

    public static StructureType stairShaper() {
        return fromId(925);
    }

    public static StructureType giganticMetalShoe() {
        return fromId(926);
    }

    public static StructureType potofEnchantedCoins() {
        return fromId(927);
    }

    public static StructureType cloverContraption() {
        return fromId(928);
    }

    public static StructureType greenCupcakes() {
        return fromId(929);
    }

    public static StructureType superHyperRefinedSynthesizer() {
        return fromId(930);
    }

    public static StructureType amberCastle10() {
        return fromId(931);
    }

    public static StructureType quaintOuthouse() {
        return fromId(932);
    }

    public static StructureType greatfruitTree() {
        return fromId(933);
    }

    public static StructureType plentifulPlanter() {
        return fromId(934);
    }

    public static StructureType goldenSimian() {
        return fromId(935);
    }

    public static StructureType armillarySphere() {
        return fromId(936);
    }

    public static StructureType helmetRack() {
        return fromId(937);
    }

    public static StructureType campfire() {
        return fromId(938);
    }

    public static StructureType expeditionTent() {
        return fromId(939);
    }

    public static StructureType megaSuperHyperRefinedSynthesizer() {
        return fromId(940);
    }

    public static StructureType ultraMegaSuperHyperRefinedSynthesizer() {
        return fromId(941);
    }

    public static StructureType hummatiteCastle() {
        return fromId(942);
    }

    public static StructureType gravenGhazt() {
        return fromId(943);
    }

    public static StructureType versegazingTelescope() {
        return fromId(944);
    }

    public static StructureType teleportal() {
        return fromId(945);
    }

    public static StructureType blueprintWorkshop() {
        return fromId(946);
    }

    public static StructureType augmentedNexusNucleus() {
        return fromId(947);
    }

    public static StructureType glowbeGuise() {
        return fromId(948);
    }

    public static StructureType fireworksStand() {
        return fromId(949);
    }

    public static StructureType kaleidoscope() {
        return fromId(950);
    }

    public static StructureType prismGateReplica() {
        return fromId(951);
    }

    public static StructureType brainTeaserTome() {
        return fromId(952);
    }

    public static StructureType confoundingCryptex() {
        return fromId(953);
    }

    public static StructureType floatingLibrary() {
        return fromId(954);
    }

    public static StructureType bibleoTheque() {
        return fromId(955);
    }

    public static StructureType triplankPath() {
        return fromId(956);
    }

    public static StructureType treecookiePath() {
        return fromId(957);
    }

    public static StructureType footprintPath() {
        return fromId(958);
    }

    public static StructureType icecracklePath() {
        return fromId(959);
    }

    public static StructureType bagpipePath() {
        return fromId(960);
    }

    public static StructureType steamspringPath() {
        return fromId(961);
    }

    public static StructureType pearlescentPath() {
        return fromId(962);
    }

    public static StructureType jellysquashPath() {
        return fromId(963);
    }

    public static StructureType rockmeltPath() {
        return fromId(964);
    }

    public static StructureType gratemindPath() {
        return fromId(965);
    }

    public static StructureType purpleMatterPath() {
        return fromId(966);
    }

    public static StructureType portholePath() {
        return fromId(967);
    }

    public static StructureType reticulatedPath() {
        return fromId(968);
    }

    public static StructureType bubbleVesselPath() {
        return fromId(969);
    }

    public static StructureType dyafrramPath() {
        return fromId(970);
    }

    public static StructureType wirecrossPath() {
        return fromId(971);
    }

    public static StructureType seasonalSigilPath() {
        return fromId(972);
    }

    public static StructureType woodgougePath() {
        return fromId(973);
    }

    public static StructureType callingCardPath() {
        return fromId(974);
    }

    public static StructureType greenberryPath() {
        return fromId(975);
    }

    public static StructureType firefissurePath() {
        return fromId(976);
    }

    public static StructureType magbroilPath() {
        return fromId(977);
    }

    public static StructureType platformPath() {
        return fromId(978);
    }

    public static StructureType fritformPath() {
        return fromId(979);
    }

    public static StructureType psyswirlPath() {
        return fromId(980);
    }

    public static StructureType telepathicPath() {
        return fromId(981);
    }

    public static StructureType façadePath() {
        return fromId(982);
    }

    public static StructureType faerieRingPath() {
        return fromId(983);
    }

    public static StructureType clasticRockPath() {
        return fromId(984);
    }

    public static StructureType ammonaughtyPath() {
        return fromId(985);
    }

    public static StructureType glowrificePath() {
        return fromId(986);
    }

    public static StructureType dustdollopPath() {
        return fromId(987);
    }

    public static StructureType comedoPath() {
        return fromId(988);
    }

    public static StructureType tasselrugPath() {
        return fromId(989);
    }

    public static StructureType repmubPath() {
        return fromId(990);
    }

    public static StructureType rekaepsPath() {
        return fromId(991);
    }

    public static StructureType conservatoryColumnCast1by4() {
        return fromId(992);
    }

    public static StructureType conservatoryColumnCast2by4() {
        return fromId(993);
    }

    public static StructureType conservatoryColumnCast3by4() {
        return fromId(994);
    }

    public static StructureType conservatoryColumnCast4by4() {
        return fromId(995);
    }

    public static StructureType anniversaryMonument12th() {
        return fromId(996);
    }

    public static StructureType smallVegetableRock() {
        return fromId(997);
    }

    public static StructureType mediumVegetableRock() {
        return fromId(998);
    }

    public static StructureType bigVegetableRock() {
        return fromId(999);
    }

    public static StructureType smallVegetableTree() {
        return fromId(1000);
    }

    public static StructureType mediumVegetableTree() {
        return fromId(1001);
    }

    public static StructureType bigVegetableTree() {
        return fromId(1002);
    }

    public static StructureType smallSettlementRock() {
        return fromId(1003);
    }

    public static StructureType mediumSettlementRock() {
        return fromId(1004);
    }

    public static StructureType bigSettlementRock() {
        return fromId(1005);
    }

    public static StructureType smallSettlementTree() {
        return fromId(1006);
    }

    public static StructureType mediumSettlementTree() {
        return fromId(1007);
    }

    public static StructureType bigSettlementTree() {
        return fromId(1008);
    }

    public static StructureType smallNestRock() {
        return fromId(1009);
    }

    public static StructureType mediumNestRock() {
        return fromId(1010);
    }

    public static StructureType bigNestRock() {
        return fromId(1011);
    }

    public static StructureType smallNestTree() {
        return fromId(1012);
    }

    public static StructureType mediumNestTree() {
        return fromId(1013);
    }

    public static StructureType bigNestTree() {
        return fromId(1014);
    }

    public static StructureType smallLagoonRock() {
        return fromId(1015);
    }

    public static StructureType mediumLagoonRock() {
        return fromId(1016);
    }

    public static StructureType bigLagoonRock() {
        return fromId(1017);
    }

    public static StructureType smallLagoonTree() {
        return fromId(1018);
    }

    public static StructureType mediumLagoonTree() {
        return fromId(1019);
    }

    public static StructureType bigLagoonTree() {
        return fromId(1020);
    }

    public static StructureType smallTempleRock() {
        return fromId(1021);
    }

    public static StructureType mediumTempleRock() {
        return fromId(1022);
    }

    public static StructureType bigTempleRock() {
        return fromId(1023);
    }

    public static StructureType smallTempleTree() {
        return fromId(1024);
    }

    public static StructureType mediumTempleTree() {
        return fromId(1025);
    }

    public static StructureType bigTempleTree() {
        return fromId(1026);
    }

    public static StructureType dishHarmonizer() {
        return fromId(1027);
    }

    public static StructureType eggCup() {
        return fromId(1028);
    }

    public static StructureType isletRock() {
        return fromId(1029);
    }

    public static StructureType isletTree() {
        return fromId(1030);
    }

    public static StructureType polarityAmplifier() {
        return fromId(1033);
    }

}