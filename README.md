# GatewayServer

Spring Cloud Gateway is an intelligent proxy service often used in microservices. It transparently centralizes requests in a single entry point and routes them to the proper service. One of its most interesting features is the concept of filters (WebFilter or GatewayFilter).

WebFilter, together with Predicate factories, incorporate the complete routing mechanism. Spring Cloud Gateway provides many built-in WebFilter factories that allow interacting with the HTTP requests before reaching the proxied service and the HTTP responses before delivering the result to the client. It is also possible to implement custom filters.
