# Lab Exercise 2 - Advanced Programming

## Task 7


You need to develop a generic class for working with complex numbers, called `ComplexNumber`, that has two generic type parameters `T` and `U` and both parameters must inherit from `Number`.

The class `ComplexNumber` has two variables —
one representing the real part and the other representing the imaginary part.

It must implement the following methods:
- `ComplexNumber(T real, U imaginary)` – constructor that initializes both variables.

- `getReal(): T` – returns the real part.

- `getImaginary(): U` – returns the imaginary part.

- `modul(): double` – calculates the modulus (magnitude) of the complex number.

- `compareTo(ComplexNumber<?, ?> o)` – compares two complex numbers based on their moduli.

- `toString(): String` – prints the number in the following format: `2.30+3.00i`