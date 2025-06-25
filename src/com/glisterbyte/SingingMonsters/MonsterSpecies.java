package com.glisterbyte.SingingMonsters;

import com.glisterbyte.Localization.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonsterSpecies {

    private final String resourceKey;
    private final IslandSpecificMonsterSpecies defaultSpecies;
    private final Map<IslandType, IslandSpecificMonsterSpecies> speciesMap;
    private final List<Integer> speciesIds;

    public MonsterSpecies(String resourceKey, Map<String, IslandSpecificMonsterSpecies> data) {

        this.resourceKey = resourceKey;

        defaultSpecies = data.getOrDefault("default", null);

        speciesMap = new HashMap<>();
        for (var entry : data.entrySet()) {
            if (entry.getKey().equals("default")) continue;
            speciesMap.put(IslandType.fromString(entry.getKey()), entry.getValue());
        }

        speciesIds = new ArrayList<>();
        if (defaultSpecies != null) {
            speciesIds.add(defaultSpecies.getSpeciesId());
        }
        for (var value : data.values()) {
            speciesIds.add(value.getSpeciesId());
        }

    }

    public IslandSpecificMonsterSpecies onIsland(IslandType islandType) {
        return speciesMap.getOrDefault(islandType, defaultSpecies);
    }

    public IslandSpecificMonsterSpecies onDefaultIslands() {
        return defaultSpecies;
    }

    private IslandSpecificMonsterSpecies onRandomIsland() {
        return speciesMap.values().iterator().next();
    }

    public boolean matchesSpeciesId(Integer speciesId) {
        return speciesIds.contains(speciesId);
    }

    public String getName(Language language) {
        return onRandomIsland().getName(language);
    }

    public String getName() {
        return onRandomIsland().getName();
    }

    public String _getResourceKey() {
        return resourceKey;
    }

    // The following is auto-generated via misc/java-info-gen/monsters.py.

    public static MonsterSpecies tweedle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("tweedle");
    }

    public static MonsterSpecies potbelly() {
        return Cache.getGenericMonsterSpeciesByResourceKey("potbelly");
    }

    public static MonsterSpecies noggin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("noggin");
    }

    public static MonsterSpecies toejammer() {
        return Cache.getGenericMonsterSpeciesByResourceKey("toejammer");
    }

    public static MonsterSpecies mammott() {
        return Cache.getGenericMonsterSpeciesByResourceKey("mammott");
    }

    public static MonsterSpecies dandidoo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dandidoo");
    }

    public static MonsterSpecies cybop() {
        return Cache.getGenericMonsterSpeciesByResourceKey("cybop");
    }

    public static MonsterSpecies quibble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("quibble");
    }

    public static MonsterSpecies pango() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pango");
    }

    public static MonsterSpecies shrubb() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shrubb");
    }

    public static MonsterSpecies oaktopus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("oaktopus");
    }

    public static MonsterSpecies furcorn() {
        return Cache.getGenericMonsterSpeciesByResourceKey("furcorn");
    }

    public static MonsterSpecies fwog() {
        return Cache.getGenericMonsterSpeciesByResourceKey("fwog");
    }

    public static MonsterSpecies drumpler() {
        return Cache.getGenericMonsterSpeciesByResourceKey("drumpler");
    }

    public static MonsterSpecies maw() {
        return Cache.getGenericMonsterSpeciesByResourceKey("maw");
    }

    public static MonsterSpecies reedling() {
        return Cache.getGenericMonsterSpeciesByResourceKey("reedling");
    }

    public static MonsterSpecies spunge() {
        return Cache.getGenericMonsterSpeciesByResourceKey("spunge");
    }

    public static MonsterSpecies thumpies() {
        return Cache.getGenericMonsterSpeciesByResourceKey("thumpies");
    }

    public static MonsterSpecies pompom() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pompom");
    }

    public static MonsterSpecies congle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("congle");
    }

    public static MonsterSpecies pummel() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pummel");
    }

    public static MonsterSpecies clamble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("clamble");
    }

    public static MonsterSpecies bowgart() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bowgart");
    }

    public static MonsterSpecies trox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("trox");
    }

    public static MonsterSpecies shellbeat() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shellbeat");
    }

    public static MonsterSpecies quarrister() {
        return Cache.getGenericMonsterSpeciesByResourceKey("quarrister");
    }

    public static MonsterSpecies deedge() {
        return Cache.getGenericMonsterSpeciesByResourceKey("deedge");
    }

    public static MonsterSpecies riff() {
        return Cache.getGenericMonsterSpeciesByResourceKey("riff");
    }

    public static MonsterSpecies entbrat() {
        return Cache.getGenericMonsterSpeciesByResourceKey("entbrat");
    }

    public static MonsterSpecies mimic() {
        return Cache.getGenericMonsterSpeciesByResourceKey("mimic");
    }

    public static MonsterSpecies scups() {
        return Cache.getGenericMonsterSpeciesByResourceKey("scups");
    }

    public static MonsterSpecies punkleton() {
        return Cache.getGenericMonsterSpeciesByResourceKey("punkleton");
    }

    public static MonsterSpecies yool() {
        return Cache.getGenericMonsterSpeciesByResourceKey("yool");
    }

    public static MonsterSpecies schmoochle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("schmoochle");
    }

    public static MonsterSpecies wubbox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("wubbox");
    }

    public static MonsterSpecies blabbit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("blabbit");
    }

    public static MonsterSpecies ghazt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("ghazt");
    }

    public static MonsterSpecies reebro() {
        return Cache.getGenericMonsterSpeciesByResourceKey("reebro");
    }

    public static MonsterSpecies hoola() {
        return Cache.getGenericMonsterSpeciesByResourceKey("hoola");
    }

    public static MonsterSpecies shugabush() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shugabush");
    }

    public static MonsterSpecies grumpyre() {
        return Cache.getGenericMonsterSpeciesByResourceKey("grumpyre");
    }

    public static MonsterSpecies nebulob() {
        return Cache.getGenericMonsterSpeciesByResourceKey("nebulob");
    }

    public static MonsterSpecies arackulele() {
        return Cache.getGenericMonsterSpeciesByResourceKey("arackulele");
    }

    public static MonsterSpecies whisp() {
        return Cache.getGenericMonsterSpeciesByResourceKey("whisp");
    }

    public static MonsterSpecies jeeode() {
        return Cache.getGenericMonsterSpeciesByResourceKey("jeeode");
    }

    public static MonsterSpecies boodoo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("boodoo");
    }

    public static MonsterSpecies shugabass() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shugabass");
    }

    public static MonsterSpecies shuguitar() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shuguitar");
    }

    public static MonsterSpecies shugajo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shugajo");
    }

    public static MonsterSpecies shugarock() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shugarock");
    }

    public static MonsterSpecies shugabuzz() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shugabuzz");
    }

    public static MonsterSpecies shugabeats() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shugabeats");
    }

    public static MonsterSpecies shugavox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shugavox");
    }

    public static MonsterSpecies sox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("sox");
    }

    public static MonsterSpecies humbug() {
        return Cache.getGenericMonsterSpeciesByResourceKey("humbug");
    }

    public static MonsterSpecies kazilleon() {
        return Cache.getGenericMonsterSpeciesByResourceKey("kazilleon");
    }

    public static MonsterSpecies bellowfish() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bellowfish");
    }

    public static MonsterSpecies dragong() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dragong");
    }

    public static MonsterSpecies jellbilly() {
        return Cache.getGenericMonsterSpeciesByResourceKey("jellbilly");
    }

    public static MonsterSpecies fungpray() {
        return Cache.getGenericMonsterSpeciesByResourceKey("fungpray");
    }

    public static MonsterSpecies rareFurcorn() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFurcorn");
    }

    public static MonsterSpecies rareEntbrat() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareEntbrat");
    }

    public static MonsterSpecies rareSpunge() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareSpunge");
    }

    public static MonsterSpecies rareThumpies() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareThumpies");
    }

    public static MonsterSpecies rarePummel() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePummel");
    }

    public static MonsterSpecies rarePango() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePango");
    }

    public static MonsterSpecies rareFwog() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFwog");
    }

    public static MonsterSpecies rarePompom() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePompom");
    }

    public static MonsterSpecies rareMaw() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareMaw");
    }

    public static MonsterSpecies rareShrubb() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareShrubb");
    }

    public static MonsterSpecies rareCybop() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCybop");
    }

    public static MonsterSpecies rareClamble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareClamble");
    }

    public static MonsterSpecies rareCongle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCongle");
    }

    public static MonsterSpecies rareQuibble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareQuibble");
    }

    public static MonsterSpecies rareDandidoo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDandidoo");
    }

    public static MonsterSpecies rareOaktopus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareOaktopus");
    }

    public static MonsterSpecies rareReedling() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareReedling");
    }

    public static MonsterSpecies rareDeedge() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDeedge");
    }

    public static MonsterSpecies rareBowgart() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBowgart");
    }

    public static MonsterSpecies rareScups() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareScups");
    }

    public static MonsterSpecies rareRiff() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareRiff");
    }

    public static MonsterSpecies rareDrumpler() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDrumpler");
    }

    public static MonsterSpecies rareShellbeat() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareShellbeat");
    }

    public static MonsterSpecies rareTrox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTrox");
    }

    public static MonsterSpecies rareQuarrister() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareQuarrister");
    }

    public static MonsterSpecies rareMammott() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareMammott");
    }

    public static MonsterSpecies rarePotbelly() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePotbelly");
    }

    public static MonsterSpecies rareToejammer() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareToejammer");
    }

    public static MonsterSpecies rareTweedle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTweedle");
    }

    public static MonsterSpecies rareNoggin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareNoggin");
    }

    public static MonsterSpecies rareWubbox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWubbox");
    }

    public static MonsterSpecies brump() {
        return Cache.getGenericMonsterSpeciesByResourceKey("brump");
    }

    public static MonsterSpecies zynth() {
        return Cache.getGenericMonsterSpeciesByResourceKey("zynth");
    }

    public static MonsterSpecies poewk() {
        return Cache.getGenericMonsterSpeciesByResourceKey("poewk");
    }

    public static MonsterSpecies thwok() {
        return Cache.getGenericMonsterSpeciesByResourceKey("thwok");
    }

    public static MonsterSpecies dwumrohl() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dwumrohl");
    }

    public static MonsterSpecies zuuker() {
        return Cache.getGenericMonsterSpeciesByResourceKey("zuuker");
    }

    public static MonsterSpecies screemu() {
        return Cache.getGenericMonsterSpeciesByResourceKey("screemu");
    }

    public static MonsterSpecies rareGhazt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGhazt");
    }

    public static MonsterSpecies rareGrumpyre() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGrumpyre");
    }

    public static MonsterSpecies rareReebro() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareReebro");
    }

    public static MonsterSpecies rareJeeode() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareJeeode");
    }

    public static MonsterSpecies rareHumbug() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareHumbug");
    }

    public static MonsterSpecies rareFungpray() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFungpray");
    }

    public static MonsterSpecies rareDragong() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDragong");
    }

    public static MonsterSpecies dipsterDo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dipsterDo");
    }

    public static MonsterSpecies dipsterRe() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dipsterRe");
    }

    public static MonsterSpecies dipsterMi() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dipsterMi");
    }

    public static MonsterSpecies dipsterFa() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dipsterFa");
    }

    public static MonsterSpecies dipsterSol() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dipsterSol");
    }

    public static MonsterSpecies dipsterLa() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dipsterLa");
    }

    public static MonsterSpecies dipsterTi() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dipsterTi");
    }

    public static MonsterSpecies rareBellowfish() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBellowfish");
    }

    public static MonsterSpecies tympa() {
        return Cache.getGenericMonsterSpeciesByResourceKey("tympa");
    }

    public static MonsterSpecies rareHoola() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareHoola");
    }

    public static MonsterSpecies dermit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dermit");
    }

    public static MonsterSpecies gheegur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("gheegur");
    }

    public static MonsterSpecies whajje() {
        return Cache.getGenericMonsterSpeciesByResourceKey("whajje");
    }

    public static MonsterSpecies blipsqueak() {
        return Cache.getGenericMonsterSpeciesByResourceKey("blipsqueak");
    }

    public static MonsterSpecies creepuscule() {
        return Cache.getGenericMonsterSpeciesByResourceKey("creepuscule");
    }

    public static MonsterSpecies scargo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("scargo");
    }

    public static MonsterSpecies rarePunkleton() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePunkleton");
    }

    public static MonsterSpecies rareKazilleon() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareKazilleon");
    }

    public static MonsterSpecies glowbe() {
        return Cache.getGenericMonsterSpeciesByResourceKey("glowbe");
    }

    public static MonsterSpecies rareYool() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareYool");
    }

    public static MonsterSpecies rareBoodoo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBoodoo");
    }

    public static MonsterSpecies tawkerr() {
        return Cache.getGenericMonsterSpeciesByResourceKey("tawkerr");
    }

    public static MonsterSpecies parlsona() {
        return Cache.getGenericMonsterSpeciesByResourceKey("parlsona");
    }

    public static MonsterSpecies rareArackulele() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareArackulele");
    }

    public static MonsterSpecies rareSchmoochle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareSchmoochle");
    }

    public static MonsterSpecies rareJellbilly() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareJellbilly");
    }

    public static MonsterSpecies astropod() {
        return Cache.getGenericMonsterSpeciesByResourceKey("astropod");
    }

    public static MonsterSpecies rareSox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareSox");
    }

    public static MonsterSpecies pixolotl() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pixolotl");
    }

    public static MonsterSpecies rareBlabbit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBlabbit");
    }

    public static MonsterSpecies rareNebulob() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareNebulob");
    }

    public static MonsterSpecies bonapetite() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bonapetite");
    }

    public static MonsterSpecies rareWhisp() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWhisp");
    }

    public static MonsterSpecies maulch() {
        return Cache.getGenericMonsterSpeciesByResourceKey("maulch");
    }

    public static MonsterSpecies fleechwurm() {
        return Cache.getGenericMonsterSpeciesByResourceKey("fleechwurm");
    }

    public static MonsterSpecies maggpi() {
        return Cache.getGenericMonsterSpeciesByResourceKey("maggpi");
    }

    public static MonsterSpecies torrt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("torrt");
    }

    public static MonsterSpecies blasoom() {
        return Cache.getGenericMonsterSpeciesByResourceKey("blasoom");
    }

    public static MonsterSpecies hornacle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("hornacle");
    }

    public static MonsterSpecies attmoz() {
        return Cache.getGenericMonsterSpeciesByResourceKey("attmoz");
    }

    public static MonsterSpecies glaishur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("glaishur");
    }

    public static MonsterSpecies syncopite() {
        return Cache.getGenericMonsterSpeciesByResourceKey("syncopite");
    }

    public static MonsterSpecies galvana() {
        return Cache.getGenericMonsterSpeciesByResourceKey("galvana");
    }

    public static MonsterSpecies scaratar() {
        return Cache.getGenericMonsterSpeciesByResourceKey("scaratar");
    }

    public static MonsterSpecies loodvigg() {
        return Cache.getGenericMonsterSpeciesByResourceKey("loodvigg");
    }

    public static MonsterSpecies furnoss() {
        return Cache.getGenericMonsterSpeciesByResourceKey("furnoss");
    }

    public static MonsterSpecies plixie() {
        return Cache.getGenericMonsterSpeciesByResourceKey("plixie");
    }

    public static MonsterSpecies vhamp() {
        return Cache.getGenericMonsterSpeciesByResourceKey("vhamp");
    }

    public static MonsterSpecies floogull() {
        return Cache.getGenericMonsterSpeciesByResourceKey("floogull");
    }

    public static MonsterSpecies kayna() {
        return Cache.getGenericMonsterSpeciesByResourceKey("kayna");
    }

    public static MonsterSpecies stogg() {
        return Cache.getGenericMonsterSpeciesByResourceKey("stogg");
    }

    public static MonsterSpecies barrb() {
        return Cache.getGenericMonsterSpeciesByResourceKey("barrb");
    }

    public static MonsterSpecies flowah() {
        return Cache.getGenericMonsterSpeciesByResourceKey("flowah");
    }

    public static MonsterSpecies glowl() {
        return Cache.getGenericMonsterSpeciesByResourceKey("glowl");
    }

    public static MonsterSpecies repatillo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("repatillo");
    }

    public static MonsterSpecies tring() {
        return Cache.getGenericMonsterSpeciesByResourceKey("tring");
    }

    public static MonsterSpecies stoowarb() {
        return Cache.getGenericMonsterSpeciesByResourceKey("stoowarb");
    }

    public static MonsterSpecies rareStogg() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareStogg");
    }

    public static MonsterSpecies wynq() {
        return Cache.getGenericMonsterSpeciesByResourceKey("wynq");
    }

    public static MonsterSpecies sneyser() {
        return Cache.getGenericMonsterSpeciesByResourceKey("sneyser");
    }

    public static MonsterSpecies whaddle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("whaddle");
    }

    public static MonsterSpecies woolabee() {
        return Cache.getGenericMonsterSpeciesByResourceKey("woolabee");
    }

    public static MonsterSpecies phangler() {
        return Cache.getGenericMonsterSpeciesByResourceKey("phangler");
    }

    public static MonsterSpecies boskus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("boskus");
    }

    public static MonsterSpecies rareFloogull() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFloogull");
    }

    public static MonsterSpecies rareRepatillo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareRepatillo");
    }

    public static MonsterSpecies epicTweedle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicTweedle");
    }

    public static MonsterSpecies epicPotbelly() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicPotbelly");
    }

    public static MonsterSpecies epicNoggin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicNoggin");
    }

    public static MonsterSpecies epicToejammer() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicToejammer");
    }

    public static MonsterSpecies epicMammott() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicMammott");
    }

    public static MonsterSpecies epicDandidoo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicDandidoo");
    }

    public static MonsterSpecies epicCybop() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicCybop");
    }

    public static MonsterSpecies epicQuibble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicQuibble");
    }

    public static MonsterSpecies epicPango() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicPango");
    }

    public static MonsterSpecies epicShrubb() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicShrubb");
    }

    public static MonsterSpecies epicOaktopus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicOaktopus");
    }

    public static MonsterSpecies epicFurcorn() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicFurcorn");
    }

    public static MonsterSpecies epicFwog() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicFwog");
    }

    public static MonsterSpecies epicDrumpler() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicDrumpler");
    }

    public static MonsterSpecies epicMaw() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicMaw");
    }

    public static MonsterSpecies epicReedling() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicReedling");
    }

    public static MonsterSpecies epicSpunge() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicSpunge");
    }

    public static MonsterSpecies epicThumpies() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicThumpies");
    }

    public static MonsterSpecies epicPompom() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicPompom");
    }

    public static MonsterSpecies epicCongle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicCongle");
    }

    public static MonsterSpecies epicPummel() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicPummel");
    }

    public static MonsterSpecies epicClamble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicClamble");
    }

    public static MonsterSpecies epicBowgart() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBowgart");
    }

    public static MonsterSpecies epicTrox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicTrox");
    }

    public static MonsterSpecies epicShellbeat() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicShellbeat");
    }

    public static MonsterSpecies epicQuarrister() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicQuarrister");
    }

    public static MonsterSpecies epicDeedge() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicDeedge");
    }

    public static MonsterSpecies epicRiff() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicRiff");
    }

    public static MonsterSpecies epicEntbrat() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicEntbrat");
    }

    public static MonsterSpecies epicScups() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicScups");
    }

    public static MonsterSpecies epicPunkleton() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicPunkleton");
    }

    public static MonsterSpecies epicYool() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicYool");
    }

    public static MonsterSpecies epicSchmoochle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicSchmoochle");
    }

    public static MonsterSpecies epicBlabbit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBlabbit");
    }

    public static MonsterSpecies epicHoola() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicHoola");
    }

    public static MonsterSpecies rarePhangler() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePhangler");
    }

    public static MonsterSpecies rootitoot() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rootitoot");
    }

    public static MonsterSpecies yawstrich() {
        return Cache.getGenericMonsterSpeciesByResourceKey("yawstrich");
    }

    public static MonsterSpecies gjoob() {
        return Cache.getGenericMonsterSpeciesByResourceKey("gjoob");
    }

    public static MonsterSpecies theremind() {
        return Cache.getGenericMonsterSpeciesByResourceKey("theremind");
    }

    public static MonsterSpecies bonkers() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bonkers");
    }

    public static MonsterSpecies poppette() {
        return Cache.getGenericMonsterSpeciesByResourceKey("poppette");
    }

    public static MonsterSpecies yuggler() {
        return Cache.getGenericMonsterSpeciesByResourceKey("yuggler");
    }

    public static MonsterSpecies tapricorn() {
        return Cache.getGenericMonsterSpeciesByResourceKey("tapricorn");
    }

    public static MonsterSpecies rooba() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rooba");
    }

    public static MonsterSpecies periscorp() {
        return Cache.getGenericMonsterSpeciesByResourceKey("periscorp");
    }

    public static MonsterSpecies gloptic() {
        return Cache.getGenericMonsterSpeciesByResourceKey("gloptic");
    }

    public static MonsterSpecies rareFlowah() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFlowah");
    }

    public static MonsterSpecies rareWynq() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWynq");
    }

    public static MonsterSpecies gobbleygourd() {
        return Cache.getGenericMonsterSpeciesByResourceKey("gobbleygourd");
    }

    public static MonsterSpecies flootFly() {
        return Cache.getGenericMonsterSpeciesByResourceKey("flootFly");
    }

    public static MonsterSpecies hippityhop() {
        return Cache.getGenericMonsterSpeciesByResourceKey("hippityhop");
    }

    public static MonsterSpecies squot() {
        return Cache.getGenericMonsterSpeciesByResourceKey("squot");
    }

    public static MonsterSpecies wimmzies() {
        return Cache.getGenericMonsterSpeciesByResourceKey("wimmzies");
    }

    public static MonsterSpecies ziggurab() {
        return Cache.getGenericMonsterSpeciesByResourceKey("ziggurab");
    }

    public static MonsterSpecies cantorell() {
        return Cache.getGenericMonsterSpeciesByResourceKey("cantorell");
    }

    public static MonsterSpecies bridgit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bridgit");
    }

    public static MonsterSpecies claviGnat() {
        return Cache.getGenericMonsterSpeciesByResourceKey("claviGnat");
    }

    public static MonsterSpecies pladdie() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pladdie");
    }

    public static MonsterSpecies rareTring() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTring");
    }

    public static MonsterSpecies rareBoskus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBoskus");
    }

    public static MonsterSpecies rareWoolabee() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWoolabee");
    }

    public static MonsterSpecies clackula() {
        return Cache.getGenericMonsterSpeciesByResourceKey("clackula");
    }

    public static MonsterSpecies peckidna() {
        return Cache.getGenericMonsterSpeciesByResourceKey("peckidna");
    }

    public static MonsterSpecies denchuhs() {
        return Cache.getGenericMonsterSpeciesByResourceKey("denchuhs");
    }

    public static MonsterSpecies hawlo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("hawlo");
    }

    public static MonsterSpecies thrumble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("thrumble");
    }

    public static MonsterSpecies withur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("withur");
    }

    public static MonsterSpecies uuduk() {
        return Cache.getGenericMonsterSpeciesByResourceKey("uuduk");
    }

    public static MonsterSpecies banjaw() {
        return Cache.getGenericMonsterSpeciesByResourceKey("banjaw");
    }

    public static MonsterSpecies plinkajou() {
        return Cache.getGenericMonsterSpeciesByResourceKey("plinkajou");
    }

    public static MonsterSpecies rareWhaddle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWhaddle");
    }

    public static MonsterSpecies rareRootitoot() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareRootitoot");
    }

    public static MonsterSpecies rareBarrb() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBarrb");
    }

    public static MonsterSpecies rareGlowl() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGlowl");
    }

    public static MonsterSpecies rareThrumble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareThrumble");
    }

    public static MonsterSpecies rareSneyser() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareSneyser");
    }

    public static MonsterSpecies rareZiggurab() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareZiggurab");
    }

    public static MonsterSpecies sooza() {
        return Cache.getGenericMonsterSpeciesByResourceKey("sooza");
    }

    public static MonsterSpecies fluoress() {
        return Cache.getGenericMonsterSpeciesByResourceKey("fluoress");
    }

    public static MonsterSpecies gob() {
        return Cache.getGenericMonsterSpeciesByResourceKey("gob");
    }

    public static MonsterSpecies spytrap() {
        return Cache.getGenericMonsterSpeciesByResourceKey("spytrap");
    }

    public static MonsterSpecies pluckbill() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pluckbill");
    }

    public static MonsterSpecies rareSooza() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareSooza");
    }

    public static MonsterSpecies tooToo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("tooToo");
    }

    public static MonsterSpecies bulbo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bulbo");
    }

    public static MonsterSpecies fiddlement() {
        return Cache.getGenericMonsterSpeciesByResourceKey("fiddlement");
    }

    public static MonsterSpecies blowt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("blowt");
    }

    public static MonsterSpecies rareKayna() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareKayna");
    }

    public static MonsterSpecies epicGhazt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicGhazt");
    }

    public static MonsterSpecies rareTheremind() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTheremind");
    }

    public static MonsterSpecies xyster() {
        return Cache.getGenericMonsterSpeciesByResourceKey("xyster");
    }

    public static MonsterSpecies knucklehead() {
        return Cache.getGenericMonsterSpeciesByResourceKey("knucklehead");
    }

    public static MonsterSpecies frondley() {
        return Cache.getGenericMonsterSpeciesByResourceKey("frondley");
    }

    public static MonsterSpecies epicKayna() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicKayna");
    }

    public static MonsterSpecies dejajin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("dejajin");
    }

    public static MonsterSpecies rareWimmzies() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWimmzies");
    }

    public static MonsterSpecies larvaluss() {
        return Cache.getGenericMonsterSpeciesByResourceKey("larvaluss");
    }

    public static MonsterSpecies epicWhaddle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWhaddle");
    }

    public static MonsterSpecies cahoot() {
        return Cache.getGenericMonsterSpeciesByResourceKey("cahoot");
    }

    public static MonsterSpecies mushaboom() {
        return Cache.getGenericMonsterSpeciesByResourceKey("mushaboom");
    }

    public static MonsterSpecies rareGobbleygourd() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGobbleygourd");
    }

    public static MonsterSpecies osstax() {
        return Cache.getGenericMonsterSpeciesByResourceKey("osstax");
    }

    public static MonsterSpecies rareBanjaw() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBanjaw");
    }

    public static MonsterSpecies gday() {
        return Cache.getGenericMonsterSpeciesByResourceKey("gday");
    }

    public static MonsterSpecies epicStogg() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicStogg");
    }

    public static MonsterSpecies roarick() {
        return Cache.getGenericMonsterSpeciesByResourceKey("roarick");
    }

    public static MonsterSpecies rareGob() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGob");
    }

    public static MonsterSpecies enchantling() {
        return Cache.getGenericMonsterSpeciesByResourceKey("enchantling");
    }

    public static MonsterSpecies epicZiggurab() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicZiggurab");
    }

    public static MonsterSpecies jamBoree() {
        return Cache.getGenericMonsterSpeciesByResourceKey("jamBoree");
    }

    public static MonsterSpecies epicWubboxPlantIsland() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWubboxPlantIsland");
    }

    public static MonsterSpecies epicReebro() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicReebro");
    }

    public static MonsterSpecies rareYuggler() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareYuggler");
    }

    public static MonsterSpecies clavavera() {
        return Cache.getGenericMonsterSpeciesByResourceKey("clavavera");
    }

    public static MonsterSpecies epicWubboxColdIsland() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWubboxColdIsland");
    }

    public static MonsterSpecies epicGobbleygourd() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicGobbleygourd");
    }

    public static MonsterSpecies epicTring() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicTring");
    }

    public static MonsterSpecies carillong() {
        return Cache.getGenericMonsterSpeciesByResourceKey("carillong");
    }

    public static MonsterSpecies rareBridgit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBridgit");
    }

    public static MonsterSpecies yelmut() {
        return Cache.getGenericMonsterSpeciesByResourceKey("yelmut");
    }

    public static MonsterSpecies epicBoskus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBoskus");
    }

    public static MonsterSpecies rareHawlo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareHawlo");
    }

    public static MonsterSpecies ffidyll() {
        return Cache.getGenericMonsterSpeciesByResourceKey("ffidyll");
    }

    public static MonsterSpecies flumOx() {
        return Cache.getGenericMonsterSpeciesByResourceKey("flumOx");
    }

    public static MonsterSpecies epicWubboxAirIsland() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWubboxAirIsland");
    }

    public static MonsterSpecies epicJeeode() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicJeeode");
    }

    public static MonsterSpecies krillby() {
        return Cache.getGenericMonsterSpeciesByResourceKey("krillby");
    }

    public static MonsterSpecies rareTootoo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTootoo");
    }

    public static MonsterSpecies viveine() {
        return Cache.getGenericMonsterSpeciesByResourceKey("viveine");
    }

    public static MonsterSpecies edamimi() {
        return Cache.getGenericMonsterSpeciesByResourceKey("edamimi");
    }

    public static MonsterSpecies epicRootitoot() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicRootitoot");
    }

    public static MonsterSpecies spurrit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("spurrit");
    }

    public static MonsterSpecies monculus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("monculus");
    }

    public static MonsterSpecies pongPing() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pongPing");
    }

    public static MonsterSpecies rareFlootFly() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFlootFly");
    }

    public static MonsterSpecies whizbang() {
        return Cache.getGenericMonsterSpeciesByResourceKey("whizbang");
    }

    public static MonsterSpecies candelavra() {
        return Cache.getGenericMonsterSpeciesByResourceKey("candelavra");
    }

    public static MonsterSpecies epicGrumpyre() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicGrumpyre");
    }

    public static MonsterSpecies tiawa() {
        return Cache.getGenericMonsterSpeciesByResourceKey("tiawa");
    }

    public static MonsterSpecies epicWubboxWaterIsland() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWubboxWaterIsland");
    }

    public static MonsterSpecies epicWubboxEarthIsland() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWubboxEarthIsland");
    }

    public static MonsterSpecies booqwurm() {
        return Cache.getGenericMonsterSpeciesByResourceKey("booqwurm");
    }

    public static MonsterSpecies strombonin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("strombonin");
    }

    public static MonsterSpecies cataliszt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("cataliszt");
    }

    public static MonsterSpecies rareJamBoree() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareJamBoree");
    }

    public static MonsterSpecies sporerow() {
        return Cache.getGenericMonsterSpeciesByResourceKey("sporerow");
    }

    public static MonsterSpecies bleatnik() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bleatnik");
    }

    public static MonsterSpecies cranchee() {
        return Cache.getGenericMonsterSpeciesByResourceKey("cranchee");
    }

    public static MonsterSpecies rareGjoob() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGjoob");
    }

    public static MonsterSpecies rareWithur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWithur");
    }

    public static MonsterSpecies bisonorus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bisonorus");
    }

    public static MonsterSpecies epicGlowl() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicGlowl");
    }

    public static MonsterSpecies cherubble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("cherubble");
    }

    public static MonsterSpecies shlep() {
        return Cache.getGenericMonsterSpeciesByResourceKey("shlep");
    }

    public static MonsterSpecies rareYelmut() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareYelmut");
    }

    public static MonsterSpecies incisaur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("incisaur");
    }

    public static MonsterSpecies rareStrombonin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareStrombonin");
    }

    public static MonsterSpecies bowhead() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bowhead");
    }

    public static MonsterSpecies rareFlumOx() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFlumOx");
    }

    public static MonsterSpecies rareXyster() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareXyster");
    }

    public static MonsterSpecies rareYawstrich() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareYawstrich");
    }

    public static MonsterSpecies drummidary() {
        return Cache.getGenericMonsterSpeciesByResourceKey("drummidary");
    }

    public static MonsterSpecies rareKrillby() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareKrillby");
    }

    public static MonsterSpecies hyehehe() {
        return Cache.getGenericMonsterSpeciesByResourceKey("hyehehe");
    }

    public static MonsterSpecies wheezel() {
        return Cache.getGenericMonsterSpeciesByResourceKey("wheezel");
    }

    public static MonsterSpecies epicHumbug() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicHumbug");
    }

    public static MonsterSpecies tuskski() {
        return Cache.getGenericMonsterSpeciesByResourceKey("tuskski");
    }

    public static MonsterSpecies rareEdamimi() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareEdamimi");
    }

    public static MonsterSpecies rareDwumrohl() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDwumrohl");
    }

    public static MonsterSpecies rareHippityhop() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareHippityhop");
    }

    public static MonsterSpecies rareZynth() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareZynth");
    }

    public static MonsterSpecies rarePoewk() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePoewk");
    }

    public static MonsterSpecies rareThwok() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareThwok");
    }

    public static MonsterSpecies rareBrump() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBrump");
    }

    public static MonsterSpecies anglow() {
        return Cache.getGenericMonsterSpeciesByResourceKey("anglow");
    }

    public static MonsterSpecies pinghound() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pinghound");
    }

    public static MonsterSpecies gnarls() {
        return Cache.getGenericMonsterSpeciesByResourceKey("gnarls");
    }

    public static MonsterSpecies rarePongPing() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePongPing");
    }

    public static MonsterSpecies rareClavavera() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareClavavera");
    }

    public static MonsterSpecies rareWhajje() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWhajje");
    }

    public static MonsterSpecies epicSneyser() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicSneyser");
    }

    public static MonsterSpecies rareBlipsqueak() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBlipsqueak");
    }

    public static MonsterSpecies epicWhisp() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWhisp");
    }

    public static MonsterSpecies rareTiawa() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTiawa");
    }

    public static MonsterSpecies rareFluoress() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFluoress");
    }

    public static MonsterSpecies adultScaratar() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultScaratar");
    }

    public static MonsterSpecies adultLoodvigg() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultLoodvigg");
    }

    public static MonsterSpecies rareIncisaur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareIncisaur");
    }

    public static MonsterSpecies rareTympa() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTympa");
    }

    public static MonsterSpecies rareCarillong() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCarillong");
    }

    public static MonsterSpecies rareBisonorus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBisonorus");
    }

    public static MonsterSpecies rareCreepuscule() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCreepuscule");
    }

    public static MonsterSpecies yooreek() {
        return Cache.getGenericMonsterSpeciesByResourceKey("yooreek");
    }

    public static MonsterSpecies blarret() {
        return Cache.getGenericMonsterSpeciesByResourceKey("blarret");
    }

    public static MonsterSpecies auglur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("auglur");
    }

    public static MonsterSpecies meebkin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("meebkin");
    }

    public static MonsterSpecies gaddzooks() {
        return Cache.getGenericMonsterSpeciesByResourceKey("gaddzooks");
    }

    public static MonsterSpecies epicJamBoree() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicJamBoree");
    }

    public static MonsterSpecies knurv() {
        return Cache.getGenericMonsterSpeciesByResourceKey("knurv");
    }

    public static MonsterSpecies buzzinga() {
        return Cache.getGenericMonsterSpeciesByResourceKey("buzzinga");
    }

    public static MonsterSpecies rareFfidyll() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFfidyll");
    }

    public static MonsterSpecies rareAstropod() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareAstropod");
    }

    public static MonsterSpecies adultTorrt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultTorrt");
    }

    public static MonsterSpecies rareGheegur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGheegur");
    }

    public static MonsterSpecies epicThrumble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicThrumble");
    }

    public static MonsterSpecies rareViveine() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareViveine");
    }

    public static MonsterSpecies epicBoodoo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBoodoo");
    }

    public static MonsterSpecies rareRooba() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareRooba");
    }

    public static MonsterSpecies whaill() {
        return Cache.getGenericMonsterSpeciesByResourceKey("whaill");
    }

    public static MonsterSpecies bemeebeth() {
        return Cache.getGenericMonsterSpeciesByResourceKey("bemeebeth");
    }

    public static MonsterSpecies rareBonapetite() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBonapetite");
    }

    public static MonsterSpecies adultPlixie() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultPlixie");
    }

    public static MonsterSpecies epicClavavera() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicClavavera");
    }

    public static MonsterSpecies rareCandelavra() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCandelavra");
    }

    public static MonsterSpecies rareSpurrit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareSpurrit");
    }

    public static MonsterSpecies adultAttmoz() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultAttmoz");
    }

    public static MonsterSpecies epicBellowfish() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBellowfish");
    }

    public static MonsterSpecies rareScargo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareScargo");
    }

    public static MonsterSpecies flasque() {
        return Cache.getGenericMonsterSpeciesByResourceKey("flasque");
    }

    public static MonsterSpecies nitebear() {
        return Cache.getGenericMonsterSpeciesByResourceKey("nitebear");
    }

    public static MonsterSpecies rareMonculus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareMonculus");
    }

    public static MonsterSpecies adultHornacle() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultHornacle");
    }

    public static MonsterSpecies rareWhizbang() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareWhizbang");
    }

    public static MonsterSpecies epicWynq() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWynq");
    }

    public static MonsterSpecies rareFleechwurm() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFleechwurm");
    }

    public static MonsterSpecies epicCarillong() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicCarillong");
    }

    public static MonsterSpecies adultFurnoss() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultFurnoss");
    }

    public static MonsterSpecies rareKnucklehead() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareKnucklehead");
    }

    public static MonsterSpecies rareScreemu() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareScreemu");
    }

    public static MonsterSpecies adultGlaishur() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultGlaishur");
    }

    public static MonsterSpecies epicWoolabee() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWoolabee");
    }

    public static MonsterSpecies rareBooqwurm() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBooqwurm");
    }

    public static MonsterSpecies piplash() {
        return Cache.getGenericMonsterSpeciesByResourceKey("piplash");
    }

    public static MonsterSpecies vhenshun() {
        return Cache.getGenericMonsterSpeciesByResourceKey("vhenshun");
    }

    public static MonsterSpecies epicSox() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicSox");
    }

    public static MonsterSpecies rareBonkers() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBonkers");
    }

    public static MonsterSpecies rareTuskski() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTuskski");
    }

    public static MonsterSpecies adultBlasoom() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultBlasoom");
    }

    public static MonsterSpecies rareDermit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDermit");
    }

    public static MonsterSpecies epicFfidyll() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicFfidyll");
    }

    public static MonsterSpecies epicJellbilly() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicJellbilly");
    }

    public static MonsterSpecies epicFlowah() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicFlowah");
    }

    public static MonsterSpecies epicBarrb() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBarrb");
    }

    public static MonsterSpecies epicViveine() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicViveine");
    }

    public static MonsterSpecies rarePluckbill() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePluckbill");
    }

    public static MonsterSpecies xrt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("xrt");
    }

    public static MonsterSpecies pentumbra() {
        return Cache.getGenericMonsterSpeciesByResourceKey("pentumbra");
    }

    public static MonsterSpecies rareMaulch() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareMaulch");
    }

    public static MonsterSpecies adultSyncopite() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultSyncopite");
    }

    public static MonsterSpecies epicSpurrit() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicSpurrit");
    }

    public static MonsterSpecies epicPhangler() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicPhangler");
    }

    public static MonsterSpecies adultVhamp() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultVhamp");
    }

    public static MonsterSpecies rareZuuker() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareZuuker");
    }

    public static MonsterSpecies epicMonculus() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicMonculus");
    }

    public static MonsterSpecies rareHyehehe() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareHyehehe");
    }

    public static MonsterSpecies rareCherubble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCherubble");
    }

    public static MonsterSpecies epicNebulob() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicNebulob");
    }

    public static MonsterSpecies epicSooza() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicSooza");
    }

    public static MonsterSpecies teetertauter() {
        return Cache.getGenericMonsterSpeciesByResourceKey("teetertauter");
    }

    public static MonsterSpecies rareAnglow() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareAnglow");
    }

    public static MonsterSpecies rhysmuth() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rhysmuth");
    }

    public static MonsterSpecies rareClackula() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareClackula");
    }

    public static MonsterSpecies rarePixolotl() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePixolotl");
    }

    public static MonsterSpecies epicWhizbang() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWhizbang");
    }

    public static MonsterSpecies rareBuzzinga() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBuzzinga");
    }

    public static MonsterSpecies adultGalvana() {
        return Cache.getGenericMonsterSpeciesByResourceKey("adultGalvana");
    }

    public static MonsterSpecies epicRepatillo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicRepatillo");
    }

    public static MonsterSpecies oogiddy() {
        return Cache.getGenericMonsterSpeciesByResourceKey("oogiddy");
    }

    public static MonsterSpecies epicKazilleon() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicKazilleon");
    }

    public static MonsterSpecies rareGloptic() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGloptic");
    }

    public static MonsterSpecies epicBooqwurm() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBooqwurm");
    }

    public static MonsterSpecies rareCataliszt() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCataliszt");
    }

    public static MonsterSpecies rareBowhead() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBowhead");
    }

    public static MonsterSpecies titansoul() {
        return Cache.getGenericMonsterSpeciesByResourceKey("titansoul");
    }

    public static MonsterSpecies epicYawstrich() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicYawstrich");
    }

    public static MonsterSpecies epicArackulele() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicArackulele");
    }

    public static MonsterSpecies epicGjoob() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicGjoob");
    }

    public static MonsterSpecies rareBleatnik() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBleatnik");
    }

    public static MonsterSpecies rareGnarls() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGnarls");
    }

    public static MonsterSpecies rareOsstax() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareOsstax");
    }

    public static MonsterSpecies rareUuduk() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareUuduk");
    }

    public static MonsterSpecies rareFiddlement() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareFiddlement");
    }

    public static MonsterSpecies epicFloogull() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicFloogull");
    }

    public static MonsterSpecies rareClaviGnat() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareClaviGnat");
    }

    public static MonsterSpecies epicWubboxFireHaven() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWubboxFireHaven");
    }

    public static MonsterSpecies epicWubboxFireOasis() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWubboxFireOasis");
    }

    public static MonsterSpecies hairionetteMajor() {
        return Cache.getGenericMonsterSpeciesByResourceKey("hairionetteMajor");
    }

    public static MonsterSpecies hairionetteMinor() {
        return Cache.getGenericMonsterSpeciesByResourceKey("hairionetteMinor");
    }

    public static MonsterSpecies epicBuzzinga() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBuzzinga");
    }

    public static MonsterSpecies rareKnurv() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareKnurv");
    }

    public static MonsterSpecies epicStrombonin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicStrombonin");
    }

    public static MonsterSpecies rareCranchee() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCranchee");
    }

    public static MonsterSpecies rareBulbo() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareBulbo");
    }

    public static MonsterSpecies rareDrummidary() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDrummidary");
    }

    public static MonsterSpecies rarePoppette() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePoppette");
    }

    public static MonsterSpecies epicYelmut() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicYelmut");
    }

    public static MonsterSpecies lowb() {
        return Cache.getGenericMonsterSpeciesByResourceKey("lowb");
    }

    public static MonsterSpecies rareGday() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareGday");
    }

    public static MonsterSpecies epicDragong() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicDragong");
    }

    public static MonsterSpecies epicKrillby() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicKrillby");
    }

    public static MonsterSpecies rareCantorell() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareCantorell");
    }

    public static MonsterSpecies rareSporerow() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareSporerow");
    }

    public static MonsterSpecies epicFungpray() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicFungpray");
    }

    public static MonsterSpecies rareDenchuhs() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDenchuhs");
    }

    public static MonsterSpecies epicPongPing() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicPongPing");
    }

    public static MonsterSpecies rarePladdie() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePladdie");
    }

    public static MonsterSpecies owlesqueMajor() {
        return Cache.getGenericMonsterSpeciesByResourceKey("owlesqueMajor");
    }

    public static MonsterSpecies owlesqueMinor() {
        return Cache.getGenericMonsterSpeciesByResourceKey("owlesqueMinor");
    }

    public static MonsterSpecies epicDwumrohl() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicDwumrohl");
    }

    public static MonsterSpecies epicZynth() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicZynth");
    }

    public static MonsterSpecies epicPoewk() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicPoewk");
    }

    public static MonsterSpecies epicThwok() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicThwok");
    }

    public static MonsterSpecies epicBrump() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicBrump");
    }

    public static MonsterSpecies epicWubboxEtherealIsland() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicWubboxEtherealIsland");
    }

    public static MonsterSpecies rareSpytrap() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareSpytrap");
    }

    public static MonsterSpecies rareRoarick() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareRoarick");
    }

    public static MonsterSpecies rarePinghound() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePinghound");
    }

    public static MonsterSpecies epicAnglow() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicAnglow");
    }

    public static MonsterSpecies epicEdamimi() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicEdamimi");
    }

    public static MonsterSpecies rareMushaboom() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareMushaboom");
    }

    public static MonsterSpecies rarePlinkajou() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rarePlinkajou");
    }

    public static MonsterSpecies epicCherubble() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicCherubble");
    }

    public static MonsterSpecies rareMimic() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareMimic");
    }

    public static MonsterSpecies rareShlep() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareShlep");
    }

    public static MonsterSpecies rareTapricorn() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareTapricorn");
    }

    public static MonsterSpecies epicTiawa() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicTiawa");
    }

    public static MonsterSpecies epicZuuker() {
        return Cache.getGenericMonsterSpeciesByResourceKey("epicZuuker");
    }

    public static MonsterSpecies rareDejajin() {
        return Cache.getGenericMonsterSpeciesByResourceKey("rareDejajin");
    }

}