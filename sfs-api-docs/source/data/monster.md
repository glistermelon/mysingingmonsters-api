# Monster Data

## Source

[Island Data](island) contains a list of the object documented here.

## Type

`SFSObject`

## Contents

| Key | Value Type | Value Description |
|-|-|-|
| `flip` | `int` | Whether the monster is flipped (0/1). |
| `happiness` | `int` | The percentage happiness of the monster. |
| `island` | `long` | The ID (not the index) of the monster's island. |
| `in_hotel` | `int` | Whether the monster is in the hotel (0/1). |
| `last_collection` | `long` | The last time when currency was collected from the monster, in unix milliseconds. |
| `last_feeding` | `long` | The last time when the monster was fed, in unix milliseconds. |
| `level` | `int` | The monster's level. |
| `muted` | `int` | Whether the monster is muted (0/1). |
| `name` | `utf_string` | The name of the monster. |
| `pos_x` | `int` | The $x$ position of the monster. |
| `pos_y` | `int` | The $y$ position of the monster. |
| `monster` | `int` | The monster's monster ID. |
| `user_monster_id` | `long` | The unique ID of the monster. |

### Undetermined Key-Values

| Key | Value Type | Known Values | Extra Info |
|-|-|-|-|
| `volume` | `double` |
| `costume` | `sfs_object` |