# Lab exercise 4 - Advanced Programming


To develop a class `TermFrequency` that will count how many times each word appears in a given text (`String`). Letter case should **not** matter (uppercase and lowercase are treated the same), and punctuation symbols should be ignored (comma, period).

The class should have the following methods:

* `TermFrequency(InputStream inputStream, String[] stopWords)` – a constructor that receives the input stream from which the text should be read and an array of stop-words that should be ignored (not counted).

* `countTotal(): int` – returns the total number of words in the text.

* `countDistinct(): int` – returns the number of unique words.

* `mostOften(int k): List<String>` – returns a list containing the *k* words that appear most frequently in the text, ordered by number of occurrences from highest to lowest. If two words have the same frequency, they should be ordered alphabetically.
