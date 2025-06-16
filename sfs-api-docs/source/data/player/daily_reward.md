# Daily Rewards Data

## Source

[Player Data](../player) contains a list of the object documented here.

## Type

`SFSObject`

## Contents

```{important}
Since the first listing in the data I've pulled has values of -1, 0, and `none`, my suspicion is that the first entry is the reward for a "one day streak", AKA just logging on at all even if you've never logged on before.
```

| Key | Value Type | Value Description | Known/Example Values |
|-|-|-|-|
| `amt` | `int` | The amount of currency awarded. |
| `type` | `utf_string` | The type of currency awarded. | `none`, `coins`, `relics`, `diamonds`, `keys`, `food` |

### Undetermined Key-Values

| Key | Value Type | Known Values | Extra Info |
|-|-|-|-|
| `bonus_entity` | `int` | `-1` |