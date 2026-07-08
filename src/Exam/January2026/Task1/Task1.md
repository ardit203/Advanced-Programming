<div class="clearfix" id="yui_3_18_1_1_1783429512985_88"><p id="yui_3_18_1_1_1783429512985_87">Потребно е да имплементирате систем за управување со производите, купувачките кошнички и нарачките во една онлајн продавница.&nbsp;</p>
<p>Во почетниот код ви се дадени класите Product, Basket, Item и Order. Класите се комплетно имплементирани (нудат toString имплементација и помошни методи кои би ви биле потребни за имплементација на остатокот од решението) и нема потреба да ги менувате.</p>
<p>За системот важат следните правила:</p>
<ul>
<li>продавницата располага со производи (секој производ има број на ставки на залиха од истиот производ)</li>
<li>корисникот на онлајн продавницата може да отвори кошничка и да додава повеќе производи со определена количина во кошничката
<ul>
<li>при додавање на производи во кошничката со определна количина, истата таа количина веќе не е достапна за другите корисници, односно се намалува бројот на ставки на залиха за конкретниот производ</li>
</ul>
</li>
<li>корисникот може креираната кошничка
<ul>
<li>да ја откаже (да не ја купи)
<ul>
<li>претходно резервираната количина во кошничката, се враќа на располагање во информациите за конкретните продукти</li>
</ul>
</li>
<li>да ја купи (да направи нарачка)
<ul>
<li>со ова се ажурира бројот на продадени ставки на производите и се креираат нарачки (Order) кои се чуваат исто така во евиденција на системот</li>
</ul>
</li>
</ul>
</li>
</ul>
<p>Потребно е да имплементирате класа OrderingSystem со следните методи:</p>
<ul>
<li><code>void addProduct(String id, String name, int price, int available)</code> - метод за додавање на нов продукт во онлајн продавницата</li>
<li><code>void openBasket(String basketId, String userId)</code> - метод за отворање на нова кошничка со ID basketId која припаѓа на корисникот userId. Статусот на ваква кошничка е ACTIVE</li>
<li><code>boolean addToBasket(String basketId, String productId, int quantity)</code> - метод за додавање на производот со ID productId со количина quantity во кошничката со ID basketId</li>
<li><code>void cancelBasket(String basketId)</code> - метод за затварање на кошничката со ID basketId. Статусот на ова кошничка преминува во CANCELED.&nbsp;</li>
<li><code>void orderBasket(String basketId) </code>- метод за креирање на нарачка и плаќање на креираната кошничка. Статусот на ова кошничка преминува во ORDERED.&nbsp;&nbsp;</li>
<li><code>Map&lt;String, Integer&gt; totalSpentByUser()</code> - метод кој враќа мапа во која клуч е ID на корисниците, а вредност е колку вкупно имаат потрошени во онлајн продавницата за сите успешно реализирани нарачки.</li>
<li><code>void printOrdersOfUser(String userId)</code> - метод кој ги печати сите нарачки направени од корисникот userId, сортирани според вкупната цена на чинење на нарачката во опаѓачки редослед.</li>
<li><code>void basketStatsPerUsers() </code>- метод кој за сите корисници на системот печати информации за бројот на активни, откажани или нарачани кошници</li>
<li><code>Product getProduct(String productId)</code> - метод кој враќа објект од класата Product за производот со ID productId&nbsp;</li>
</ul>
<p></p>
<p><em><strong>Да се додадат безбедносни маханизми за конкурентно извршување на програмата, односно да се внимава на истовремени (паралелни) резервирања и враќања на резервирани количини на ставки од производите при креирање/откажување на купувачките кошнички!</strong></em></p>
<p><em><strong>--</strong></em></p>
<div class="flex flex-col text-sm pb-25">
<article class="text-token-text-primary w-full focus:outline-none [--shadow-height:45px] has-data-writing-block:pointer-events-none has-data-writing-block:-mt-(--shadow-height) has-data-writing-block:pt-(--shadow-height) [&amp;:has([data-writing-block])&gt;*]:pointer-events-auto scroll-mt-[calc(var(--header-height)+min(200px,max(70px,20svh)))]" dir="auto" tabindex="-1" data-turn-id="request-WEB:393ad0d6-0fa8-4979-b7e0-0b8b6eeb84dc-3" data-testid="conversation-turn-8" data-scroll-anchor="true" data-turn="assistant">
<div class="text-base my-auto mx-auto pb-10 [--thread-content-margin:--spacing(4)] @w-sm/main:[--thread-content-margin:--spacing(6)] @w-lg/main:[--thread-content-margin:--spacing(16)] px-(--thread-content-margin)">
<div class="[--thread-content-max-width:40rem] @w-lg/main:[--thread-content-max-width:48rem] mx-auto max-w-(--thread-content-max-width) flex-1 group/turn-messages focus-visible:outline-hidden relative flex w-full min-w-0 flex-col agent-turn" tabindex="-1">
<div class="flex max-w-full flex-col grow">
<div class="min-h-8 text-message relative flex w-full flex-col items-end gap-2 text-start break-words whitespace-normal [.text-message+&amp;]:mt-1" dir="auto" data-message-author-role="assistant" data-message-id="cea5cc3a-0e3b-4217-a2c1-76e1132a5d91" data-message-model-slug="gpt-5-2">
<div class="flex w-full flex-col gap-1 empty:hidden first:pt-[1px]">
<div class="markdown prose dark:prose-invert w-full wrap-break-word light markdown-new-styling">
<p data-start="0" data-end="102">You need to implement a system for managing products, shopping baskets, and orders in an online store.</p>
<p data-start="104" data-end="396">In the starter code, you are given the classes <strong data-start="151" data-end="162">Product</strong>, <strong data-start="164" data-end="174">Basket</strong>, <strong data-start="176" data-end="184">Item</strong>, and <strong data-start="190" data-end="199">Order</strong>. These classes are fully implemented (they include <code data-start="251" data-end="261">toString</code> implementations and helper methods that may be needed for implementing the rest of the solution), and there is no need to modify them.</p>
<p data-start="403" data-end="447">The following rules apply to the system:</p>
<ul data-start="449" data-end="1252">
<li data-start="449" data-end="541">
<p data-start="451" data-end="541">The online store manages products (each product has a number of items available in stock).</p>
</li>
<li data-start="542" data-end="860">
<p data-start="544" data-end="664">A user of the online store can open a shopping basket and add multiple products with a specified quantity to the basket.</p>
<ul data-start="667" data-end="860">
<li data-start="667" data-end="860">
<p data-start="669" data-end="860">When products are added to the basket with a certain quantity, that quantity immediately becomes unavailable to other users, i.e., the stock quantity for the corresponding product is reduced.</p>
</li>
</ul>
</li>
<li data-start="861" data-end="1252">
<p data-start="863" data-end="904">The user can process the shopping basket:</p>
<ul data-start="907" data-end="1252">
<li data-start="907" data-end="1071">
<p data-start="909" data-end="951"><strong data-start="909" data-end="930">Cancel the basket</strong> (do not purchase it)</p>
<ul data-start="956" data-end="1071">
<li data-start="956" data-end="1071">
<p data-start="958" data-end="1071">The previously reserved quantities in the basket are returned to availability in the corresponding product stock.</p>
</li>
</ul>
</li>
<li data-start="1074" data-end="1252">
<p data-start="1076" data-end="1116"><strong data-start="1076" data-end="1099">Purchase the basket</strong> (place an order)</p>
<ul data-start="1121" data-end="1252">
<li data-start="1121" data-end="1252">
<p data-start="1123" data-end="1252">This updates the number of sold items for the products and creates orders (<code data-start="1198" data-end="1205">Order</code>) that are also stored in the system’s records.</p>
</li>
</ul>
</li>
</ul>
</li>
</ul>
<p data-start="1259" data-end="1339">You need to implement the class&nbsp;<code data-start="1295" data-end="1311">OrderingSystem</code> with the following methods:</p>
<ul data-start="1341" data-end="2859">
<li data-start="1341" data-end="1473">
<p data-start="1343" data-end="1473"><strong data-start="1343" data-end="1414"><code data-start="1345" data-end="1412">void addProduct(String id, String name, int price, int available)</code></strong><br data-start="1414" data-end="1417">A method for adding a new product to the online store.</p>
</li>
<li data-start="1475" data-end="1663">
<p data-start="1477" data-end="1663"><strong data-start="1477" data-end="1530"><code data-start="1479" data-end="1528">void openBasket(String basketId, String userId)</code></strong><br data-start="1530" data-end="1533">A method for opening a new basket with ID <code data-start="1577" data-end="1587">basketId</code> that belongs to the user <code data-start="1613" data-end="1621">userId</code>. The status of such a basket is <code data-start="1654" data-end="1662">ACTIVE</code>.</p>
</li>
<li data-start="1665" data-end="1854">
<p data-start="1667" data-end="1854"><strong data-start="1667" data-end="1741"><code data-start="1669" data-end="1739">boolean addToBasket(String basketId, String productId, int quantity)</code></strong><br data-start="1741" data-end="1744">A method for adding the product with ID <code data-start="1786" data-end="1797">productId</code> in quantity <code data-start="1810" data-end="1820">quantity</code> to the basket with ID <code data-start="1843" data-end="1853">basketId</code>.</p>
</li>
<li data-start="1856" data-end="2003">
<p data-start="1858" data-end="2003"><strong data-start="1858" data-end="1898"><code data-start="1860" data-end="1896">void cancelBasket(String basketId)</code></strong><br data-start="1898" data-end="1901">A method for closing the basket with ID <code data-start="1943" data-end="1953">basketId</code>. The status of this basket changes to <code data-start="1992" data-end="2002">CANCELED</code>.</p>
</li>
<li data-start="2005" data-end="2164">
<p data-start="2007" data-end="2164"><strong data-start="2007" data-end="2046"><code data-start="2009" data-end="2044">void orderBasket(String basketId)</code></strong><br data-start="2046" data-end="2049">A method for creating an order and paying for the created basket. The status of this basket changes to <code data-start="2154" data-end="2163">ORDERED</code>.</p>
</li>
<li data-start="2166" data-end="2389">
<p data-start="2168" data-end="2389"><strong data-start="2168" data-end="2213"><code data-start="2170" data-end="2211">Map&lt;String, Integer&gt; totalSpentByUser()</code></strong><br data-start="2213" data-end="2216">A method that returns a map where the key is the user ID and the value is the total amount the user has spent in the online store across all successfully completed orders.</p>
</li>
<li data-start="2391" data-end="2567">
<p data-start="2393" data-end="2567"><strong data-start="2393" data-end="2436"><code data-start="2395" data-end="2434">void printOrdersOfUser(String userId)</code></strong><br data-start="2436" data-end="2439">A method that prints all orders made by the user with ID <code data-start="2498" data-end="2506">userId</code>, sorted in descending order by the total price of the order.</p>
</li>
<li data-start="2569" data-end="2729">
<p data-start="2571" data-end="2729"><strong data-start="2571" data-end="2603"><code data-start="2573" data-end="2601">void basketStatsPerUsers()</code></strong><br data-start="2603" data-end="2606">A method that prints, for all users in the system, information about the number of active, canceled, and ordered baskets.</p>
</li>
<li data-start="2731" data-end="2859">
<p data-start="2733" data-end="2859"><strong data-start="2733" data-end="2775"><code data-start="2735" data-end="2773">Product getProduct(String productId)</code></strong><br data-start="2775" data-end="2778">A method that returns the <code data-start="2806" data-end="2815">Product</code> object for the product with ID <code data-start="2847" data-end="2858">productId</code>.</p>
</li>
</ul>
<p data-start="2896" data-end="3139" data-is-last-node="" data-is-only-node=""><em><strong>You must add safety mechanisms for concurrent execution of the program. Special care should be taken to handle simultaneous (parallel) reservations and returns of reserved product quantities when creating or canceling shopping baskets.</strong></em></p>
</div>
</div>
</div>
</div>
<div class="z-0 flex min-h-[46px] justify-start">&nbsp;</div>
<div class="mt-3 w-full empty:hidden">
<div class="text-center">&nbsp;</div>
</div>
</div>
</div>
</article>
</div>
<div class="pointer-events-none h-px w-px absolute bottom-0" aria-hidden="true" data-edge="true">&nbsp;</div></div>


### Starter code
```java
class Product {
    String id;
    String name;
    int available;
    int price;
    int sold;

    public Product(String id, String name, int price, int available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        sold = 0;
    }

    @Override
    public String toString() {
        return name + " | price=" + price + " | available=" + available + " | sold=" + sold;
    }
}

class Item {
    String id;
    String name;
    int quantity;
    int price;

    public Item(String id, String name, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " x" + quantity + " (" + price + " each)";
    }
}

enum BasketStatus {
    ACTIVE, ORDERED, CANCELED
}

class Basket {
    String id;
    String userId;
    Map<String, Item> items;
    BasketStatus status;

    public Basket(String id, String userId) {
        this.id = id;
        this.userId = userId;
        this.items = new LinkedHashMap<>();
        this.status = BasketStatus.ACTIVE;
    }

    public void addItem(Item item) {
        items.put(item.id, item);
    }

    @Override
    public String toString() {
        return String.format("Basket %s (%s)\n%s", id, userId, items.values().stream().map(Item::toString).collect(Collectors.joining("\n")));
    }
}

class Order {
    String id;
    String userId;
    Map<String, Item> items;
    static int ORDER_ID = 1;

    public Order(String userId) {
        this.id = String.valueOf(ORDER_ID++);
        this.userId = userId;
        this.items = new LinkedHashMap<>();
    }

    public Order(Basket basket) {
        this.id = String.valueOf(ORDER_ID++);
        this.userId = basket.userId;
        this.items = new LinkedHashMap<>(basket.items);
    }

    int totalPrice() {
        int sum = 0;
        for (Item item : items.values()) {
            sum += item.price * item.quantity;
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("Order %s (%s)\n%s\nTotal price: %d", id, userId, items.values().stream().map(Item::toString).collect(Collectors.joining("\n")), totalPrice());
    }
}

/* ===================== TEST ===================== */

public class OrderingSystemTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderingSystem system = new OrderingSystem();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String command = parts[0];

            switch (command) {

                case "ADD_PRODUCT": {
                    String id = parts[1];
                    String name = parts[2];
                    int price = Integer.parseInt(parts[3]);
                    int available = Integer.parseInt(parts[4]);
                    system.addProduct(id, name, price, available);
                    break;
                }

                case "OPEN_BASKET": {
                    String basketId = parts[1];
                    String userId = parts[2];
                    system.openBasket(basketId, userId);
                    break;
                }

                case "ADD_TO_BASKET": {
                    String basketId = parts[1];
                    String productId = parts[2];
                    int quantity = Integer.parseInt(parts[3]);

                    system.addToBasket(basketId, productId, quantity);

                    break;
                }

                case "CANCEL_BASKET": {
                    String basketId = parts[1];
                    system.cancelBasket(basketId);
                    break;
                }

                case "ORDER_BASKET": {
                    String basketId = parts[1];
                    system.orderBasket(basketId);
                    break;
                }

                case "GET_PRODUCT": {
                    System.out.println(system.getProduct(parts[1]));
                    break;
                }

                case "PRINT_ORDERS": {
                    String userId = parts[1];
                    System.out.println("Orders of user " + userId + ":");
                    system.printOrdersOfUser(userId);
                    break;
                }

                case "TOTAL_SPENT": {
                    system.totalSpentByUser().entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
                    break;
                }

                case "BASKET_STATS": {
                    system.basketStatsPerUsers();
                    break;
                }
                case "CONCURRENT_VERIFY": {
                    int threads = Integer.parseInt(parts[1]);
                    int iterations = Integer.parseInt(parts[2]);
                    String productId = parts[3];

                    // initial stock snapshot (test-side)
                    int initialStock = system.products.get(productId).available;

                    // test-side counters (NO system involvement)
                    AtomicInteger adds =
                            new AtomicInteger(0);
                    AtomicInteger cancels =
                            new AtomicInteger(0);
                    AtomicInteger orders =
                            new AtomicInteger(0);

                    ExecutorService pool = Executors.newFixedThreadPool(threads);

                    for (int t = 0; t < threads; t++) {
                        final int threadId = t;

                        pool.submit(() -> {
                            for (int i = 0; i < iterations; i++) {
                                String basketId = "B_" + threadId + "_" + i;
                                String userId = "U_" + threadId;

                                system.openBasket(basketId, userId);

                                int q = 1 + (int) Math.ceil((Math.random() * 100));

                                boolean added = system.addToBasket(basketId, productId, q);
                                if (!added) {
                                    continue;
                                }

                                // track successful reservation
                                adds.addAndGet(q);

                                if (Math.random() < 0.9) {
                                    system.cancelBasket(basketId);
                                    cancels.addAndGet(q);
                                } else {
                                    system.orderBasket(basketId);
                                    orders.addAndGet(q);
                                }
                            }
                        });
                    }

                    pool.shutdown();
                    try {
                        pool.awaitTermination(1, TimeUnit.MINUTES);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Product p = system.products.get(productId);

                    int expectedReserved =
                            adds.get() - cancels.get() - orders.get();

                    int expectedAvailable =
                            initialStock - adds.get() + cancels.get();

                    boolean ok = true;

                    if (p.available != expectedAvailable) {
                        System.out.println("FAIL ❌: available mismatch");
                        ok = false;
                    }
                    if (p.sold != orders.get()) {
                        System.out.println("FAIL ❌: sold mismatch");
                        ok = false;
                    }
                    if (expectedReserved < 0) {
                        System.out.println("FAIL ❌: negative reserved (test logic error)");
                        ok = false;
                    }
                    if (p.available + p.sold + expectedReserved != initialStock) {
                        System.out.println("FAIL ❌: stock invariant violated");
                        ok = false;
                    }

                    if (ok) {
                        System.out.println("PASS ✅: state is consistent");
                    }

                    System.out.println("--------------------------------");

                    break;
                }




                case "END":
                    return;


                default:
                    System.out.println("Unknown command: " + command);
            }
        }
    }
}
```