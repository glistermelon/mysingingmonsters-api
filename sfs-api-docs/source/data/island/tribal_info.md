# Tribe Info

## Source

[Island Data](../island) contains an instance of the object documented here.

## Type

`SFSObject`

## Contents

| Key | Value Type | Value Description |
|-|-|-|
| `chief_name` | `utf_string` | The display name of the chief user. |
| `user_island_id` | `long` | The island's ID. |
| `chief` | `long` | The chief user's ID. |
| `date_created` | `long` | When the tribe was created, in unix milliseconds. |
| `members` | `long` | The number of members the tribe has. |
| `ends_on` | `long` | Presumably when the tribal rewards/competition period ends. |
| `name` | `utf_string` | The name of the tribe. |
| `rank` | `long` | The rank of the client-side user in the tribe, **not the global rank**. |

### Undetermined Key-Values

| Key | Value Type | Known Values | Extra Info |
|-|-|-|-|
| `last_updated` | `long` | | A unix timestamp in milliseconds. |