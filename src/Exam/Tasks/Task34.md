Write a class `TextProcessor` for processing texts which will provide information about the distribution of words in the texts and their vector representation. For the class, implement:

* `TextProcessor()` – constructor
* `readText(InputStream is)` – method for reading text from an input stream. Each text is on a new line. From each text all unnecessary punctuation marks and digits should be removed, so that only words and spaces between them remain.
* `printTextsVectors(OutputStream os)` – method which, to an output stream, prints for each read text its vector representation, where the vector is of the form: `[frequency1, frequency2, ....]`.
  In the vector are the frequencies of all words (lexicographically sorted) that appeared in all texts read from the input stream. Letter case should be ignored.

    * Example: let the texts be `Napredno programiranje` and `napredno rabotenje`.
      Then the vector representation of the first text will be `[1, 1, 0]`, and of the second text `[1, 0, 1]`, because all words that appeared in all texts (lexicographically sorted) are: `[napredno, programiranje, rabotenje]`.
* `printCorpus(OutputStream os, int n, boolean ascending)` – method that prints the first n words from each text according to their frequency of occurrence. If the `ascending` argument is `true`, they are printed in ascending order, otherwise in descending order.
* `public void mostSimilarTexts(OutputStream os)` – method which prints to an output stream the two most similar texts obtained from the input stream. As a measure of similarity between texts, cosine similarity between the frequencies in the document vectors is used. The static function `cosineSimilarity` from the class `CosineSimilarityCalculator` should be used.
