# Update Properties

## Source

Frequently sent to the client, often alongside responses to client-initiated responses, to update relevant values.

## Type

`SFSObject`

## Contents

| Key | Value Type | Value Description |
|-|-|-|
| `last_collection` | `long?` | For relevant interactions (like collecting from a mine), the time of collection as a unix timestamp in milliseconds. |
| `coins_actual` | `long` | Updated user count count. |
| `diamonds_actual` | `long` | Updated user diamond count. |
| `food_actual` | `long` | Updated user food count. |
| `ethereal_currency_actual` | `long` | Updated user shard count. |
| `keys_actual` | `long` | Updated user key count. |
| `relics_actual` | `long` | Updated user relic count. |
| `starpower_actual` | `long` | Updated user starpower amount. |
| `xp` | `int` | Updated user XP. |
| `level` | `int` | Updated user level. |
| `relic_diamond_cost` | `int` | Updated exchange rate between relics and diamonds. |
| `earned_starpower` | `int` | (Probably) for relevant interactions, the amount of starpower earned. |

### Undetermined Key-Values

| Key | Value Type |
|-|-|
| `egg_wildcards_actual` | `long` |
| `daily_bonus_type` | `utf_string` |
| `daily_bonus_amount` | `int` |
| `has_free_ad_scratch` | `bool` |
| `daily_relic_purchase_count` | `long` |
| `next_relic_reset` | `long` |
| `premium` | `int` |
| `speed_up_credit` | `long` |
| `battle_xp` | `int` |
| `battle_level` | `int` |
| `medals` | `int` |