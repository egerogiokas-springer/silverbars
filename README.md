# silverbars

I've created tiny types for all the different fields needed to register an order. I did this because
it will be much easier to know what I'm working with. Also, it will be easier to add order quantity for the summary
information when I don't have to deal with what it will look like.

Added the concept of OrderId so that when I cancel an order I am guaranteed to be cancelling the correct one
(decided to use UUIDs, just to be sure)

I assume that OrderQuantity is always displaying in kilos and that PricePerKilo is always in pounds (based on given
requirements). If this were to change at any point, the types would have to become more complex.

Also, I am using double for OrderQuantity. The examples given only have 1 decimal place. However, if in the system
smaller decimal places are used it would be wise to use something like BigDecimal so as to guarantee the amount is not
altered/rounded because of how the data is kept.

