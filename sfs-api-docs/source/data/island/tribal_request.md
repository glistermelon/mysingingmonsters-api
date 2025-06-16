# Tribal Request Data

## Source

[Island Data](../island) contains an array of the object documented here.

## Type

`SFSObject`

## Contents

| Key | Value Type | Value Description |
|-|-|-|
| `tribe` | `long` | The ID of the client-side user's tribe. |
| `date_created` | `long` | Most likely when the request was sent. For the tribe owner, this value equals the island's `date_created`. |
| `user` | `long` | The ID of the requesting user. |
| `status` | `utf_string` | The status of the request. | `pending` |

### Undetermined Key-Values

| Key | Value Type | Known Values | Extra Info |
|-|-|-|-|
| `last_updated` | `long` | | A unix timestamp in milliseconds. |