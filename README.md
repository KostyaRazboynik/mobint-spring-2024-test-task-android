# Companies List

## Description

A small application that makes requests to remote postman collection which is a list of Companies and displays the contents in the recycler view list.

The request sends 10 random products each and fills the recycler view with them, when one of the last items in the list is reached, a new request is made for the next 10 items, after which the displayed list increases.SwipeRefreshLayout is also used to get new list items. 

Each response contains information about the Company, a link to the icon and the style of the card (text colors, background colors, buttons colors)

At each subsequent launch, previously downloaded data is displayed, which has been cached in the device's memory


## Technologies used

- Retrofit + OkHttp3 + Gson for network requests and parsing of received data
- [Dagger2](https://github.com/google/dagger) for dependency injection
- Room database for caching loaded data
- [Coil image loader](https://coil-kt.github.io/coil/)
- ViewBinding by [ViewBindingPropertyDelegate](https://github.com/androidbroadcast/ViewBindingPropertyDelegate)


