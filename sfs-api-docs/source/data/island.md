# Island Data

## Source

[Player Data](player) contains a list of the object documented here.

## Type

`SFSObject`

## Contents

```{toctree}
:hidden:
:titlesonly:

island/tribal_info
island/tribal_request
island/tribal_quest
```

```{note}
Keys beginning with `tribal` are only present in Tribal Island data.
```

| Key | Value Type | Value Description |
|-|-|-|
| `date_created` | `long` | When the player got the island, in unix millseconds. |
| `dislikes` | `int` | How many dislikes the island has. |
| `island` | `int` | The index of the island (see below). |
| `light_torch_flag` | `bool` | Whether the island is marked for torches to be lit by friends. |
| `likes` | `int` | How many likes the island has. |
| `monsters` | `sfs_array` | An array of [monsters](monster). |
| `structures` | `sfs_array` | An array of [structures](structure). |
| `tribal_id` | `long` | The ID of the tribe. |
| `tribal_island_data` | [Information about the tribe](island/tribal_info) |
| `tribal_requests` | `sfs_array` | An array of [requests to join the tribe](island/tribal_request). |
| `tribal_quests` | `sfs_array` | An array of the current [tribal quests](island/tribal_quest). |
| `user` | The ID of the user who owns the island. |
| `user_island_id` | `long` | The island's ID. |
| `warp_speed` | `double` | The time warp multiplier. |

### Island Indices

```{warning}
I don't know if these actually correspond correctly but if anyone really wants to get into the decompilation to find out, check out `game::player::addIsland`. I got these numbers by decompiling `assets/scripts/IslandData.lua`.
```

| Index | Name |
|-|-|
| 1 | Plant Island |
| 2 | Cold Island |
| 3 | Air Island |
| 4 | Water Island |
| 5 | Earth Island |
| 6 | Gold Island |
| 7 | Ethereal Island |
| 8 | Shugabush Island |
| 9 | Tribal Island |
| 10 | Wublin Island |
| 11 | Composer Island |
| 12 | Celestial Island |
| 13 | Fire Haven |
| 14 | Fire Oasis |
| 15 | Psychic Island |
| 16 | Faerie Island |
| 17 | Bone Island |
| 18 | Light Island |
| 19 | Magical Sanctum |
| 20 | The Colossingum |
| 21 | Seasonal Shanty |
| 22 | Amber Island |
| 23 | Mythical Island |
| 24 | Ethereal Workshop |
| 25 | Magical Nexus |
| 26 | Plasma Islet |
| 101 | Mirror Plant Island |
| 102 | Mirror Cold Island |
| 103 | Mirror Air Island |
| 104 | Mirror Water Island |
| 105 | Mirror Earth Island |
| 115 | Mirror Psychic Island |
| 116 | Mirror Faerie Island |
| 117 | Mirror Bone Island |
| 118 | Mirror Light Island |

### Undetermined Key-Values

| Key | Value Type | Known Values | Extra Info |
|-|-|-|-|
| `eggs` | `sfs_array` |
| `type` | `int` | | Has always matched `island` for me. Maybe it differs for mirror islands? |
| `fuzer` | `sfs_array` |
| `last_baked` | `sfs_array` |
| `baking` | `sfs_array` |
| `costumes_owned` | `utf_string` |
| `last_player_level` | `int` | `-1` |
| `costume_data` | `sfs_object` | | Contains a `costumes` `sfs_array`.
| `torches` | `sfs_array` |
| `breeding` | `sfs_array` |