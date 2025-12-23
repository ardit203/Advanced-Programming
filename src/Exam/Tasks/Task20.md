You need to implement a class `Subtitles` which reads a translation from an input stream (standard input, file, ...) in the standard `srt` format.
Each element of the subtitles consists of an index number, start time, end time, and the text, and is in the following format (example):

```
2
00:00:48,321 --> 00:00:50,837
Let's see a real bet.
```

The text part may contain multiple lines.
All subtitle elements are separated by one empty line.

Your task is to implement the following methods:

* `Subtitles()` – default constructor
* `int loadSubtitles(InputStream inputStream)` – method for reading the subtitles (returns how many elements were read)
* `void print()` – prints the loaded subtitles in the same format as when reading
* `void shift(int ms)` – shifts the timestamps of all subtitle elements by the number of milliseconds given as an argument (can be negative, which shifts the timestamps backwards)