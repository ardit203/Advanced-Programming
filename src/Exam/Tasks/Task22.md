You need to implement a class `F1Race` which will read from an input stream (standard input, file, …) data about the lap times of the last 3 laps of several drivers in an F1 race.
The data is in the following format:

`Driver_name lap1 lap2 lap3`,
where each `lap` is in the format `mm:ss:nnn`, where `mm` is minutes, `ss` is seconds, and `nnn` is milliseconds.
Example:

```
Vettel 1:55:523 1:54:987 1:56:134
```

Your task is to implement the following methods:

* `F1Race()` – default constructor
* `void readResults(InputStream inputStream)` – method for reading the data
* `void printSorted(OutputStream outputStream)` – method which prints all drivers sorted by their best lap time (the shortest time from their 3 last laps) in the format
  `Driver_name best_lap`
  with 10 characters reserved for the driver's name (left aligned) and 10 characters for the best lap time (right aligned).
  The time must be printed in the same format as the input times.
