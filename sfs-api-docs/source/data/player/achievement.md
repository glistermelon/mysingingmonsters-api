# Achievement

## Source

[Player Data](../player)

## Type

`SFSObject`

## Contents

| Key | Value Type | Value Description | Known/Example Values |
|-|-|-|-|
| `achievement` | `utf_string` | An upper-snake-cased code for the achievement. | See below. |
| `user` | `int` | The user's ID. |

### Achievement Codes

These are all the values I've seen for achievements. I haven't done any packet sniffing with my main account yet so my current knowledge is very limited. For the most part, I think it's pretty easy to tell which codes represent what achievements.

* `ACH_FIRST_MONSTER`
* `ACH_BREED_MONSTERS`
* `ACH_ISLAND_X`, where `X` is a number. Values of `X` that I've seen are 2, 3, 4, and 5, but not 1 or 0.
* `ACH_REACH_LEVEL_10`

### Undetermined Key-Values

| Key | Value Type | Known Values | Extra Info |
|-|-|-|-|
| `google_achieve_id` | `utf_string` |
| `user_achievement_id` | `long` |
| `gp_posted` | `int` |
| `user_quest` | `int` |
| `fb_posted` | `int` |
| `gc_posted` | `int` |