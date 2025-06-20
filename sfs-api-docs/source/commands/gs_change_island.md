# Change Island

## Command String

`gs_change_island`

## Client-to-Server

The client is supposed to keep track of an active island, which a user on the mobile app would be viewing. The request indicates that the user would like to switch their active island to a different island.

```{warning}
The server will return an error if the client attempts to switch to the island it already has active.
```

### Parameters

| Key | Value Type | Description |
|-|-|-|
| `user_island_id` | `long` | The unique ID (not the type ID) of the [island](../data/island) to switch to. |

## Server-to-Client

Always sent in response to a change island request. 

### Success Parameters

| Key | Value Type | Description |
|-|-|-|
| `success` | `bool` (`true`) | Whether the active island was changed successfully. |
| `user_island_id` | `long` | The server echoes the ID sent in the request. |

### Failure Parameters

| Key | Value Type | Description |
|-|-|-|
| `success` | `bool` (`false`) | Whether the active island was changed successfully. |
| `message` | `utf_string` | An error message. |