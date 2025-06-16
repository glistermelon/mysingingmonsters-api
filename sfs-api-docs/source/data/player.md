# Player Data

## Source

I don't remember where I pulled this data from.

## Type

`SFSObject`

## Contents

```{toctree}
:hidden:
:titlesonly:

player/achievement
player/daily_login
player/daily_reward
```

```{warning}
Analysis of `game::Player::initWithSFSObject` shows that there may be more undocumented key-values. There should be a tag containing `moniker_` but it isn't in my noob account data.
```

| Key | Value Type | Value Description | Known/Example Values |
|-|-|-|-|
| `achievements` | `sfs_array` | Any array of [achievements](player/achievement). | |
| `active_island_themes` | `sfs_array` | The user's active island themes. Nothing currently known about the value structure because there are no known examples besides an empty array. |
| `bbb_id` | `long` | User's BigBlueBubble ID |
| `country` | `utf_string` | Likely the home country recorded for the user, in [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) format. | `US` |
| `client_platform` | `utf_string` | The client's platform. | `ios` |
| `coins_actual` | `long` | The number of coins the user has. |
| `daily_cumulative_login` | `sfs_object` | [Information about the user's login streak](player/daily_login).
| `diamonds_actual` | `long` | The number of diamonds the user has. |
| `diamonds_spent` | `int` | Presumably the number of diamonds spent by the user. Unsure if there are any time constraints on this. |
| `display_name` | `utf_string` | The user's display name. |
| `ethereal_currency_actual` | `long` | The number of shards the user has. |
| `food_actual` | `long` | The amount of food/treats the user has. |
| `islands` | `sfs_array` | An array of [islands](island). |
| `keys` | `long` | The number of keys the user has. Maybe. |
| `keys_actual` | `long` | The number of keys the user has. |
| `last_client_version` | `utf_string` | The latest version of the app used by the client. Unsure if this updates before or after the user receives this info. | `4.3.0` |
| `last_login` | `long` | The user's last login time in unix milliseconds. |
| `level` | `int` | The user's level, as seen in the top left of the app. |
| `new_mail` | `bool` | If the user has mail. |
| `relics` | `long` | The number of relics the user has. Maybe. |  |
| `relics_actual` | `long` | The number of relics the user has. |  |
| `relic_diamond_cost` | `int` | The number of diamonds a user can exchange for a relic in the market. | |
| `scaled_daily_reward` | `sfs_array` | [The reward for each day of a user's login streak.](player/daily_reward) |
| `show_welcomeback` | `bool` | Whether to show the user the "Welcome Back" message shown to players who haven't been online for a long time. |
| `starpower` | `long` | The amount of starpower the user has. Maybe. |
| `starpower_actual` | `long` | The amount of starpower the user has. |
| `total_starpower_collected` | `long` | Likely the total amount of starpower collected by the user without regard to how much of it has been previously been exchanged in the starshop. |
| `user` | `long` | Probably the same as `user_id`. | Has always matched `user_id`. |
| `user_id` | `int` | The user's ID. In my case, very close to but not the same as the BigBlueBubble ID. Likely not private information because other players' user IDs are used to view their islands (like friends). |
| `xp` | `int` | The amount of XP the user has. TODO: is this cumulative? |

### Undetermined Key-Values

| Key | Value Type | Known Values | Extra Info |
|-|-|-|-|
| `friend_gift` | `long` |
| `purchases_amount` | `int` | `0` |
| `cachedRewardDay` | `int` | | Probably related to daily login streak awards. |
| `island_tutorials` | `int_array` |
| `nextDailyLogin` | `long` | | Looks like a unix timestamp, probably in milliseconds. |
| `daily_relic_purchase_count` | `int` |
| `purchases_total` | `int` |
| `is_admin` | `int` | | Why is this an int? |
| `egg_wildcards_actual`| `long` | | Presumably related to egg scratch cards, but not sure why this is a long instead of an int. |
| `next_relic_reset` | `long` | | Probably related to `daily_relic_purchase_count`. |
| `client_tutorial_setup` | `utf_string` | `breedingAddOn` |
| `pvpSeason0` | `sfs_object` | | Contains `schedule_started_on`, a `long` which looks like another timestamp. Also contains `campaign_id`, an `int`.
| `twitter_invite_reward` | `int` |
| `egg_wildcards` | `long` | | How does this differ from `egg_wildcards_actual`? |
| `pvpSeason1` | `sfs_object` | | Same as `pvpSeason0`, structure-wise. |
| `has_promo` | `bool` |
| `costumes` | `sfs_object` |
| `last_fb_post_reward` | `long` | | Looks like a timestamp. |
| `currencyScratchTime` | `long` | | Looks like a timestamp. |
| `speed_up_credit` | `long` |
| `mailbox` | `sfs_array` |
| `prev_tier` | `int` | `-1` |
| `unlocks` | `sfs_object` | | Contains an `entities` `int_array`, which is empty for me. |
| `rewards_total` | `int` | | If I had to guess, I would think this is the number of diamonds awarded to the user for doing offer-wall tasks. |
| `inventory` | `sfs_object` | | Has a single `items` `sfs_array`, empty for me. |
| `premium` | `int` |
| `has_scratch_off_m` | `bool` |
| `email_invite_reward` | `int` |
| `has_free_ad_scratch` | `bool` |
| `extra_ad_params` | `utf_string` || A URL-query-style list of key-value pairs. |
| `daily_bonus_amount` | `int` |
| `reward_day` | `int` |
`has_scratch_off_s` | `bool` | | How does this differ from `has_scratch_off_m`? |
| `timed_events` | `sfs_array` |
| `daily_bonus_type` | `utf_string` | `none` |
| `player_groups` | `int_array` | `[38, 40, 45]` |
| `date_created` | `long` |
| `monsterScratchTime` | `long` |
| `last_timed_theme` | `sfs_array` |
| `avatar` | `sfs_object` |
| `tracks` | `sfs_array` |
| `third_party_ads` | `bool` |
| `third_party_video_ads` | `bool` |
| `prev_rank` | `long` |
| `active_island` | `long` | A big-ass number, so not an island index. |
| `battle` | `sfs_object` |
| `fb_invite_reward` | `int` |
| `perma_campaigns_viewed` | `int_array` |