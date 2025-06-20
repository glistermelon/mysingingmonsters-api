# Buy Island

## Command String

`gs_buy_island`

## Client-to-Server

Indicates that the user would like to buy an island.

### Parameters

| Key | Value Type | Description |
|-|-|-|
| `island_type` | `int` | The [type ID](island-type-ids) of the island the user wants to buy. |
| `island_name` | `utf_string` | If purchasing Tribal Island, the name of the tribe to be created. Otherwise, any value is accepted, but the authentic client would pass an empty string (`""`).
| `starpower_purchase` | `bool` | Was there ever an island purchasable via starpower? The authentic client always passes `false` and I haven't tried passing `true`. |

## Server-to-Client

Always sent in response to a buy island request, indicating whether it was successful. If so, the [data for the new island](../data/island) is provided.

### Success Parameters

| Key | Value Type | Description |
|-|-|-|
| `success` | `bool` (`true`) | Whether the island was bought successfully. |
| `properties` | `sfs_object` | [Update properties](../data/update_properties). |
| `user_island` | `sfs_object` | The bought [island's  data](../data/island). |

#### Undocumented Parameters

On success, the server will also pass `songs` and `tracks`, which I have ignored because they seem primarily relevant to the function of the actual client app. One of them includes data like the time signature of the music, or something like that.

### Failure Parameters

```{warning}
If the client sends a request with an invalid type ID, the server does not respond.
```

| Key | Value Type | Description |
|-|-|-|
| `success` | `bool` (`false`) | Whether the island was bought successfully. |
| `message` | `utf_string` | An error message. |