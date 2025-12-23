Implement an application for storing products in an online shop. For that purpose, define a class `OnlineShop` in which you will store all products in the shops and which will offer functionalities for listing the products and buying them. For the class, implement:

* Default constructor `OnlineShop()`
* Method `void addProduct(String category, String id, String name, LocalDateTime createdAt, double price)` – method for adding a product to the online shop. Each product is defined with a category, ID, name, the date when it is added to the shop, and its price.
* Method `double buyProduct(String id, int quantity)` – which will implement the purchase of `quantity` units of the product with ID `id`. The method should return how much money is spent for this transaction. An exception of type `ProductNotFoundException` should be thrown if the product does not exist. The method must have complexity `O(1)`.
* Method `List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize)` which will list all products from the category `category`, sorted according to the comparator `comparatorType`, grouped into pages of size `pageSize` (pagination). `category` may also be `null`, in which case all products in the online shop are listed.

`COMPARATOR_TYPE` is an enum that is given to you in the starter code. For printing the products, use the built-in `toString` notation in the IDE (preserve the order and names of the variables).
