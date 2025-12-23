Implement a class `DailyTemperatures` in which air temperatures (integers) are read for different days of the year (number from 1 to 366).
The temperatures for one day are in one line in the following format (example): `137 23C 15C 28C`.
The first number represents the day of the year, and then follows an unknown number of temperature measurements for that day, in either Celsius scale (C) or Fahrenheit scale (F).

In this class, implement the following methods:

* `DailyTemperatures()` – default constructor
* `void readTemperatures(InputStream inputStream)` – method for reading the data from an input stream
* `void writeDailyStats(OutputStream outputStream, char scale)` – method for printing the daily statistics (total measurements, minimum temperature, maximum temperature, average temperature) for each day, ordered in ascending order by the day.
  The second argument `scale` determines in which scale the temperatures are printed: `C - Celsius`, `F - Fahrenheit`.
  The format for printing the statistics for a given day is:

  `[day]: Count: [total measurements - 3 places] Min: [min temperature] Max: [max temperature] Avg: [average]`

The minimum, maximum, and average temperature are printed with 6 places, of which 2 are decimal, and after the number the scale of the temperature is written (C/F).

Formula for conversion from Celsius to Fahrenheit: $\frac{T \cdot 9}{5} + 32$


Formula for conversion from Fahrenheit to Celsius: $\frac{(T - 32) \cdot 5}{9}$


**Note:** To achieve the same precision as in the results of the solution, for calculating the average and converting between scales, the temperatures should be stored as type `Double`.
