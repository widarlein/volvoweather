# Volvo Weather

App showing weathers from 6 different cities.

## Limitations
* No persistence. A "by the book" android app would have a room database here, but I feel that stale data for current weather is not worth it. If the product manager has offline caching as a requirement, let's book a meeting and discuss it.
* No Domain layer. Small apps typically don't require a domain layer. Requirement document didn't specify further growth.
* No flow/live data/observable structures in data fetching logic. App only fetches from one endpoint with out any advanced composing or streaming of data, so I deemed it a bit redundant to include stream like logic.
