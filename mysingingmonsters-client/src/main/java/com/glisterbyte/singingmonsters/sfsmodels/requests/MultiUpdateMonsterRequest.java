package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.List;

/**
 * Updates monster happiness.
 *
 * @afterFields
 * The `entity_array` is an array of the following object:
 * ```
 * {
 *   user_monster_id: long?
 *   neighbors: array<NeighborData>
 * }
 * \n
 * NeighborData {
 *   entity_id: integer
 *   distance: integer
 * }
 * ```
 * Each item in `entity_array` represents either a monster or a structure
 * and how it is positioned relative to other monsters and structures.
 * If an item corresponds to a monster, `user_monster_id` should be present, and will be the monster's user monster ID.
 * Otherwise, if an item corresponds to a structure, `user_monster_id` should not be present.
 * Each item's `neighbors` array should only include distances less than or equal to 2,
 * so farther monsters or structures are disregarded.
 *  `entity_id` is the entity ID of either a [structure type](/data/StructureType)
 *  or [monster species](/data/MonsterSpecies).
 */
@SfsCmd("gs_multi_neighbors")
public class MultiUpdateMonsterRequest extends SfsRequestModel {

    @SfsArrayElementType(SFSObject.class)
    public List<SFSObject> entity_array;

    /**
     * I haven't seen the authentic client pass true even when happiness updates.
     * Maybe if you buy a unity tree? I haven't tried it.
     */
    public boolean island_needs_happiness_update;

}