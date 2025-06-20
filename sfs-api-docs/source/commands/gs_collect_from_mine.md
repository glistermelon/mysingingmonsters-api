# Collect from Mine

## Command String

`gs_collect_from_mine`

## Client-to-Server

Indicates to the server that the user would like to attempt to collect from the diamond mine on the [active island](gs_change_island).

### Parameters

No parameters are passed.

## Server-to-Client

Always sent in response to a collection request.
If the collection is successful, `gs_update_structure` is also sent afterwards.

### Success Parameters

| Key | Value Type | Description |
|-|-|-|
| `success` | `bool` (`true`) | Whether the collection was successful. |

### Failure Parameters

| Key | Value Type | Description |
|-|-|-|
| `success` | `bool` (`false`) | Whether the collection was successful. |
| `message` | `utf_string` | An error message. |

#### Known Error Messages

* `"Player has no mine"`
* `"Mine is not ready yet"`