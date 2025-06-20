# Player

## Command String

`gs_player`

## Client-to-Server

Requests that the server provide the the user's data.

### Parameters

| Key | Value Type | Description |
|-|-|-|
| `last_updated` | `utf_string` | I have only seen `" 0"` passed (yes, with the space).

## Server-to-Client

Most likely always in a response to a request, provides the user with their player data.

### Parameters

| Key | Value Type | Description |
|-|-|-|
| `player_object` | `sfs_object` | The user's [player data](../data/player). |