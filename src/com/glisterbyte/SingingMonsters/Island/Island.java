package com.glisterbyte.SingingMonsters.Island;

import java.util.ArrayList;
import java.util.List;

import com.glisterbyte.SingingMonsters.Castle;
import com.glisterbyte.SingingMonsters.Market;
import com.glisterbyte.SingingMonsters.Structure;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.glisterbyte.SingingMonsters.Monster.Monster;

public class Island {
    String name;
    int likes;
    int dislikes;
    Castle castle;
    List<Monster> monsters = new ArrayList<>();
    List<Structure> structures = new ArrayList<>();
    String description;
    boolean skinEnabled;
    boolean skinUnlocked;
    String skinDescription;
    Market market;
    public List<Monster> getMonsters() { return monsters; }
    public int getLikes() { return likes; }
    public int getDislikes() { return dislikes; }
    public int getRating() { return getLikes() - getDislikes(); }
    public Island(SFSObject data) throws IslandException {}
}
