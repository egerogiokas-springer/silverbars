# silverbars

I've created tiny types for all the different fields needed to register an order. I did this because
it will be much easier to know what I'm working with. Also, it will be easier to add order quantity for the summary
information when I don't have to deal with what it will look like.

Added the concept of OrderId so that when I cancel an order I am guaranteed to be cancelling the correct one
(decided to use UUIDs, just to be sure)

