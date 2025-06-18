# Structure Data

## Source

[Island Data](island) contains a list of the object documented here.

## Type

`SFSObject`

## Contents

| Key | Value Type | Value Description |
|-|-|-|
| `date_created` | `long` | When the structure was created, in unix milliseconds. **Warning:** for some reason, not all structures have this key. |
| `flip` | `int` | Whether the structure is flipped (0/1). |
| `island` | `long` | The ID (not the index) of the structure's island. |
| `in_warehouse` | `int` | Whether the structure is warehoused (0/1). |
| `is_complete` | `int` | Whether the structure is finished upgrading (0/1). |
| `is_upgrading` | `int` | Whether the structure is upgrading (0/1). |
| `muted` | `int` | Whether the structure is muted (0/1). |
| `pos_x` | `int` | The $x$ position of the structure. |
| `pos_y` | `int` | The $y$ position of the structure. |
| `scale` | `double` | The scale factor of the structure. |
| `structure` | `int` | The structure's structure ID. |
| `user_structure_id` | `long` | The unique ID of the structure. |

### Undetermined Key-Values

| Key | Value Type | Known Values | Extra Info |
|-|-|-|-|
| `last_collection` | `long` | Looks like another unix timestamp in milliseconds. |