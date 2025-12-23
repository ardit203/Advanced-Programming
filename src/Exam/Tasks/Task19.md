A class `Coin` is given, which is used to simulate flipping a coin.
The method `flip` in this class, using the `Random` class (which uses a uniform distribution), returns `HEAD` or `TAIL` with equal probability of 0.5 (50%).

Write a class `LoadedCoin` which inherits from the class `Coin` and overrides the `flip` method so that it returns `HEAD` with some probability `P` (0–100%).
The probability `P` is a class variable and is initialized through the constructor.
