Implement a class `WordVectors` for working with words and their representation in vectors. Implement the following methods:

* `public WordVectors(String[] words, List<List<Integer>> vectors)` – constructor for initialization with words and their corresponding representation in a vector of 5 integers (with values from 0–9). For each string from the array `words` there is a corresponding list of 5 integers (its vector representation).
* `public void readWords(List<String> words)` – a list of words is read from some text for which a vector representation needs to be calculated.
* `public List<Integer> slidingWindow(int n)` – calculates a vector representation of the read text (list of words) using a sliding window of size `n`. A window represents `n` neighboring words, starting from the first word (position 0) and includes the words from `0` to `(n - 1)`. Then this window is moved one position to the right, i.e. from `1` to `n`, etc. For each window of `n` elements, one scalar (integer) is obtained in such a way that the vectors of all words are summed and from the resulting vector the maximum value is taken. Example for the vectors of the words `quiz` and `attempt`:

`quiz = [1, 5, 7]` and `attempt = [3, 1, 4]` give the resulting vector `[1 + 3, 5 + 1, 7 + 4] = [4, 6, 11]` with maximum value `11`.

If for a certain word there is no vector representation, the neutral value `[5, 5, 5, 5, 5]` is used.
