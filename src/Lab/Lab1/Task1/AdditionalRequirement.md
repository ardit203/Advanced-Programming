In the `Bank` class, create a method:
 - `forEachConditional(Predicate<Account> predicate, Consumer<Account> consumer)`
   
which applies the consumer to all accounts (`Account`) that satisfy the given predicate.

Then, test this method in the main function by rewarding all accounts that have a current balance greater than \$10,000 with an additional $100 as a loyalty bonus from the bank.