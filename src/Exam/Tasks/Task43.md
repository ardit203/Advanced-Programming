Implement a class for a phone book `PhoneBook` with the following methods:

* `void addContact(String name, String number)` – adds a new contact to the phone book. If we try to add a contact with an already existing number, an exception of class `DuplicateNumberException` should be thrown with the message `Duplicate number: [number]`. The complexity of this method should not exceed $O(\log N)$ for $N$ contacts.

* `void contactsByNumber(String number)` – prints all contacts whose number contains the number passed as an argument to the method (the minimum length of the number [number] is 3). The complexity of this method should not exceed *O(log N)* for *N* contacts.

* `void contactsByName(String name)` – prints all contacts with the given name. The complexity of this method should be *O(1)*.

In both methods, the contacts are printed sorted lexicographically by name, and those with the same name are then sorted by number.
