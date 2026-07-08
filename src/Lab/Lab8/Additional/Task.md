<div class="clearfix" id="yui_3_18_1_1_1783428375626_85">
<p></p>
<p data-start="191" data-end="363">Define a class&nbsp;<code data-start="206" data-end="232">ExpensesManagementSystem</code>&nbsp;for managing business expenses made by employees in a company. The system should handle two types of employee business expenses:</p>
<ul data-start="364" data-end="441">
<li data-start="364" data-end="383">
<p data-start="366" data-end="383">travel expenses</p>
</li>
<li data-start="384" data-end="441">
<p data-start="386" data-end="441">daily expenses for which a fiscal receipt is provided</p>
</li>
</ul>
<p data-start="443" data-end="665">Employees usually first pay the expenses themselves, after which they are eligible for a reimbursement of the realized expense. The system should enable calculation of the amount to be reimbursed for the submitted expense.</p>
<p data-start="667" data-end="716">The class should implement the following methods:</p>
<ul id="yui_3_18_1_1_1766649333951_111" data-start="718" data-end="2928">
<li data-start="718" data-end="875">
<p data-start="720" data-end="875">Constructor&nbsp;<code data-start="732" data-end="781">ExpenseManagementSystem(float maxReceiptAmount)</code>&nbsp;– with a single argument, the maximum allowed amount of an expense made with a fiscal receipt</p>
</li>
<li data-start="877" data-end="1210">
<p data-start="879" data-end="1210">Method&nbsp;<code data-start="886" data-end="1008">addTravelExpense(Employee employee, String reason, double amount, LocalDateTime start, LocalDateTime end, String country)</code>&nbsp;– method for adding travel expenses (e.g., airplane tickets) for the employee&nbsp;<code data-start="1087" data-end="1097">employee</code>&nbsp;with reason&nbsp;<code data-start="1110" data-end="1118">reason</code>, with cost&nbsp;<code data-start="1130" data-end="1138">amount</code>, for the period from&nbsp;<code data-start="1160" data-end="1171">start</code>&nbsp;to&nbsp;<code data-start="1175" data-end="1183">end</code>, in the country&nbsp;<code data-start="1200" data-end="1209">country</code>.</p>
</li>
<li data-start="1212" data-end="1490">
<p data-start="1214" data-end="1490">Method&nbsp;<code data-start="1221" data-end="1311">void addReceiptExpense(Employee employee, String reason, Receipt receipt)</code>&nbsp;– method for adding a business expense made by the employee&nbsp;<code data-start="1372" data-end="1382">employee</code>&nbsp;with reason&nbsp;<code data-start="1395" data-end="1403">reason</code>&nbsp;(e.g., lunch at a restaurant), in the amount&nbsp;<code data-start="1449" data-end="1457">amount</code>&nbsp;with a fiscal receipt&nbsp;<code data-start="1480" data-end="1489">receipt</code>.</p>
<ul data-start="718" data-end="2928">
<li data-start="1492" data-end="1692">
<p data-start="1494" data-end="1692">With exception of type NotSupportedExpenseException, prevent adding an expense that is made during a business trip of the employee (for which they already receive a daily allowance) for the period in which they already have an approved travel expense.</p>
</li>
<li data-start="1694" data-end="1791">
<p data-start="1696" data-end="1791">With exception of type NotSupportedExpenseException, prevent adding an expense that exceeds the maximum allowed amount for a fiscal-receipt expense.</p>
</li>
</ul>
</li>
<li data-start="1793" data-end="2725">
<p data-start="1795" data-end="2040"><code data-start="1795" data-end="1816">void printRefunds()</code>&nbsp;– method that prints the employees’ expenses in the format shown in the test examples, sorted by the amount the company will reimburse for the realized expense, in descending order. The reimbursement rules are as follows:</p>
<ul data-start="2043" data-end="2725">
<li data-start="2043" data-end="2272">
<p data-start="2045" data-end="2272">For travel expenses: The employee receives reimbursement of the full reported amount plus a daily allowance for each day spent in the country, according to the allowances defined in the map&nbsp;<code data-start="2235" data-end="2270">DailyExpensesPerCountry.ALLOWANCE</code></p>
</li>
<li data-start="2275" data-end="2725">
<p data-start="2277" data-end="2453">For expenses made with a fiscal receipt: The company reviews all receipt items and reimburses only those from categories the employee is allowed to use for business expenses.</p>
<ul data-start="2458" data-end="2725">
<li data-start="2458" data-end="2559">
<p data-start="2460" data-end="2559">Employees at level&nbsp;<code data-start="2479" data-end="2483">IC</code>&nbsp;may buy items only from the categories&nbsp;<em data-start="2523" data-end="2529">food</em>&nbsp;and&nbsp;<em data-start="2534" data-end="2556">non-alcohol beverage</em>.</p>
</li>
<li data-start="2564" data-end="2665">
<p data-start="2566" data-end="2665">Employees at level&nbsp;<code data-start="2585" data-end="2588">M</code>&nbsp;may also buy items from the categories&nbsp;<em data-start="2628" data-end="2639">transport</em>&nbsp;and&nbsp;<em data-start="2644" data-end="2662">alcohol beverage</em>.</p>
</li>
<li data-start="2670" data-end="2725">
<p data-start="2672" data-end="2725">Employees at level&nbsp;<code data-start="2691" data-end="2694">C</code>&nbsp;have no category restrictions.</p>
</li>
</ul>
</li>
</ul>
</li>
<li id="yui_3_18_1_1_1766649333951_110" data-start="2727" data-end="2928">
<p data-start="2729" data-end="2928" id="yui_3_18_1_1_1766649333951_109"><code data-start="2729" data-end="2778">Map&lt;Employee, Double&gt; totalRefundsPerEmployee()</code> – method that returns a map where the keys are the employees, and the values are the total reimbursement amounts paid to each corresponding employee.</p>
</li>
</ul></div>

### Starter code
```java
// package midterms.m1;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

enum Level {
    IC, //individual contributor
    M,  //middle management
    C   //C-Level executives
}

class Employee {
    String name;
    String jobTitle;
    Level level;

    public Employee(String name, String jobTitle, Level level) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format(
                "Employee: name=%s, title=%s, level=%s",
                name,
                jobTitle,
                level.toString()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name)
                && Objects.equals(jobTitle, employee.jobTitle)
                && level == employee.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jobTitle, level);
    }
}

class Item {
    String name;
    String category;
    double price;

    public Item(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %.2f USD", name, category, price);
    }
}

class Receipt {
    String merchant;
    LocalDateTime date;
    List<Item> items;

    public Receipt(String merchant, LocalDateTime date, List<Item> items) {
        this.merchant = merchant;
        this.date = date;
        this.items = items;
    }

    double totalAmount() {
        double sum = 0.0;
        for (int i = 0; i < items.size(); i++) {
            sum += items.get(i).price;
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).toString());
            if (i < items.size() - 1) sb.append("; ");
        }

        return String.format(
                "Receipt: merchant=%s, date=%s, items=%s, total=%.2f USD",
                merchant,
                date,
                sb.toString(),
                totalAmount()
        );
    }
}

class NotSupportedExpenseException extends Exception {
    NotSupportedExpenseException(String message) {
        super(message);
    }
}

// "Bad but correct": one record class + enum + lots of ifs, no inheritance/polymorphism/streams.
enum ExpenseType {
    TRAVEL, RECEIPT
}

class ExpenseRecord {
    ExpenseType type;

    // common
    Employee employee;
    String description;

    // travel fields
    double travelAmount;
    LocalDateTime start;
    LocalDateTime end;
    String country;

    // receipt fields
    Receipt receipt;

    static ExpenseRecord travel(Employee employee, String description, double amount,
                                LocalDateTime start, LocalDateTime end, String country) {
        ExpenseRecord r = new ExpenseRecord();
        r.type = ExpenseType.TRAVEL;
        r.employee = employee;
        r.description = description;
        r.travelAmount = amount;
        r.start = start;
        r.end = end;
        r.country = country;
        return r;
    }

    static ExpenseRecord receipt(Employee employee, String description, Receipt receipt) {
        ExpenseRecord r = new ExpenseRecord();
        r.type = ExpenseType.RECEIPT;
        r.employee = employee;
        r.description = description;
        r.receipt = receipt;
        return r;
    }

    double refund() {
        if (type == ExpenseType.TRAVEL) {
            Double allowance = DailyExpensesPerCountry.ALLOWANCE.get(country);
            if (allowance == null) allowance = 0.0;
            long days = Duration.between(start, end).toDays();
            return travelAmount + allowance * days;
        } else {
            // RECEIPT
            double total = receipt.totalAmount();

            // C-level: full receipt amount
            if (employee.level == Level.C) {
                return total;
            }

            // IC/M: filter by categories with loops
            double sum = 0.0;
            for (int i = 0; i < receipt.items.size(); i++) {
                Item it = receipt.items.get(i);
                String cat = it.category;

                boolean allowed = false;

                // allowed for IC + M
                if (cat.equals("food") || cat.equals("non-alcohol beverage")) {
                    allowed = true;
                }

                // extra for M
                if (!allowed && employee.level == Level.M) {
                    if (cat.equals("transport") || cat.equals("alcohol beverage")) {
                        allowed = true;
                    }
                }

                if (allowed) sum += it.price;
            }
            return sum;
        }
    }

    boolean overlaps(LocalDateTime date) {
        if (type == ExpenseType.TRAVEL) {
            return date.isAfter(start) && date.isBefore(end);
        }
        return false;
    }

    @Override
    public String toString() {
        if (type == ExpenseType.TRAVEL) {
            return String.format(
                    "TravelExpense: employee={%s}, description=%s, baseAmount=%.2f USD, " +
                            "country=%s, start=%s, end=%s, refund=%.2f USD",
                    employee.toString(),
                    description,
                    travelAmount,
                    country,
                    start,
                    end,
                    refund()
            );
        } else {
            return String.format(
                    "ReceiptExpense: employee={%s}, description=%s, receiptAmount=%.2f USD, " +
                            "receiptDate=%s, itemsCount=%d, refund=%.2f USD",
                    employee.toString(),
                    description,
                    receipt.totalAmount(),
                    receipt.date,
                    receipt.items.size(),
                    refund()
            );
        }
    }
}

class ExpenseManagementSystem {
    List<ExpenseRecord> expenses;
    float maxReceiptAmount;

    public ExpenseManagementSystem(float maxReceiptAmount) {
        this.expenses = new ArrayList<>();
        this.maxReceiptAmount = maxReceiptAmount;
    }

    public void addReceiptExpense(Employee employee, String reason, Receipt receipt) throws NotSupportedExpenseException {
        if (receipt.totalAmount() > maxReceiptAmount) {
            throw new NotSupportedExpenseException(String.format(
                    "Receipt with amount %.2f exceeds the max allowed amount for receipt expense %.2f",
                    receipt.totalAmount(),
                    maxReceiptAmount
            ));
        }

        for (int i = 0; i < expenses.size(); i++) {
            ExpenseRecord ex = expenses.get(i);

            // only travel expenses can overlap
            if (ex.type == ExpenseType.TRAVEL) {
                if (ex.employee.equals(employee) && ex.overlaps(receipt.date)) {
                    throw new NotSupportedExpenseException(
                            "You cannot add receipt expense in the same period during an approved travel expense."
                    );
                }
            }
        }

        expenses.add(ExpenseRecord.receipt(employee, reason, receipt));
    }

    public void addTravelExpense(Employee employee, String reason, double amount,
                                 LocalDateTime start, LocalDateTime end, String country) throws NotSupportedExpenseException {
        expenses.add(ExpenseRecord.travel(employee, reason, amount, start, end, country));
    }

    public void printRefunds() {
        // sort descending by refund()
        Collections.sort(expenses, new Comparator<ExpenseRecord>() {
            @Override
            public int compare(ExpenseRecord a, ExpenseRecord b) {
                double ra = a.refund();
                double rb = b.refund();
                // reversed
                if (ra < rb) return 1;
                if (ra > rb) return -1;
                return 0;
            }
        });

        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(expenses.get(i).toString());
        }
    }

    public Map<Employee, Double> totalRefundsPerEmployee() {
        Map<Employee, Double> map = new HashMap<>();
        for (int i = 0; i < expenses.size(); i++) {
            ExpenseRecord ex = expenses.get(i);
            Employee emp = ex.employee;
            double current = 0.0;
            if (map.containsKey(emp)) current = map.get(emp);
            map.put(emp, current + ex.refund());
        }
        return map;
    }
}

class DailyExpensesPerCountry {
    static Map<String, Double> ALLOWANCE = new HashMap<>();

    static {
        ALLOWANCE.put("US", 50.0);
        ALLOWANCE.put("MK", 10.0);
        ALLOWANCE.put("PT", 30.0);

        ALLOWANCE.put("DE", 45.0);   // Germany
        ALLOWANCE.put("AT", 40.0);   // Austria
        ALLOWANCE.put("CH", 55.0);   // Switzerland
        ALLOWANCE.put("FR", 50.0);   // France
        ALLOWANCE.put("IT", 40.0);   // Italy
        ALLOWANCE.put("ES", 35.0);   // Spain
        ALLOWANCE.put("UK", 50.0);   // United Kingdom
        ALLOWANCE.put("NL", 45.0);   // Netherlands
        ALLOWANCE.put("BE", 45.0);   // Belgium
        ALLOWANCE.put("SE", 50.0);   // Sweden
        ALLOWANCE.put("NO", 55.0);   // Norway
        ALLOWANCE.put("DK", 50.0);   // Denmark
        ALLOWANCE.put("PL", 25.0);   // Poland
        ALLOWANCE.put("CZ", 25.0);   // Czech Republic
        ALLOWANCE.put("SK", 20.0);   // Slovakia
        ALLOWANCE.put("HU", 20.0);   // Hungary
        ALLOWANCE.put("HR", 25.0);   // Croatia
        ALLOWANCE.put("BG", 20.0);   // Bulgaria
        ALLOWANCE.put("RO", 20.0);   // Romania
        ALLOWANCE.put("GR", 30.0);   // Greece
        ALLOWANCE.put("RS", 15.0);   // Serbia
        ALLOWANCE.put("AL", 15.0);   // Albania
        ALLOWANCE.put("TR", 20.0);   // Türkiye

        ALLOWANCE.put("CA", 45.0);   // Canada
        ALLOWANCE.put("MX", 25.0);   // Mexico
        ALLOWANCE.put("BR", 20.0);   // Brazil
        ALLOWANCE.put("AR", 18.0);   // Argentina
        ALLOWANCE.put("CL", 22.0);   // Chile

        ALLOWANCE.put("AU", 50.0);   // Australia
        ALLOWANCE.put("NZ", 40.0);   // New Zealand

        ALLOWANCE.put("JP", 45.0);   // Japan
        ALLOWANCE.put("CN", 30.0);   // China
        ALLOWANCE.put("KR", 35.0);   // South Korea
        ALLOWANCE.put("SG", 50.0);   // Singapore
        ALLOWANCE.put("IN", 20.0);   // India
        ALLOWANCE.put("AE", 45.0);   // UAE (Dubai)
        ALLOWANCE.put("SA", 30.0);   // Saudi Arabia
    }
}



public class ExpensesManagementSystemTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Float maxReceiptAmount = Float.parseFloat(sc.nextLine());

        // Create system with some default max amount
        ExpenseManagementSystem system = new ExpenseManagementSystem(maxReceiptAmount);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("END")) break;
            if (line.isEmpty()) continue;

            String[] parts = line.split(";");
            String method = parts[0];


            switch (method) {

                case "addReceiptExpense": {
                    // Format:
                    // addReceiptExpense;Name;Job;IC|M|C;description;amount;merchant;datetime;item|cat|price,...
                    String empName = parts[1];
                    String job = parts[2];
                    Level lvl = Level.valueOf(parts[3]);
                    String description = parts[4];

                    String merchant = parts[5];
                    LocalDateTime dt = LocalDateTime.parse(parts[6]);

                    // Items list
                    String itemsRaw = parts[7];
                    List<Item> items = new ArrayList<>();
                    if (!itemsRaw.equalsIgnoreCase("none")) {
                        for (String itemStr : itemsRaw.split(",")) {
                            String[] ip = itemStr.split("\\|");
                            items.add(new Item(ip[0], ip[1], Double.parseDouble(ip[2])));
                        }
                    }

                    Employee e = new Employee(empName, job, lvl);
                    Receipt r = new Receipt(merchant, dt, items);

                    try {
                        system.addReceiptExpense(e, description, r);
                    } catch (NotSupportedExpenseException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                }

                case "addTravelExpense": {
                    // Format:
                    // addTravelExpense;Name;Job;IC|M|C;description;amount;start;end;country
                    String empName = parts[1];
                    String job = parts[2];
                    Level lvl = Level.valueOf(parts[3]);
                    String description = parts[4];
                    double amount = Double.parseDouble(parts[5]);
                    LocalDateTime start = LocalDateTime.parse(parts[6]);
                    LocalDateTime end = LocalDateTime.parse(parts[7]);
                    String country = parts[8];

                    Employee e = new Employee(empName, job, lvl);

                    try {
                        system.addTravelExpense(e, description, amount, start, end, country);
                    } catch (NotSupportedExpenseException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                }

                case "printRefunds": {
                    system.printRefunds();
                    break;
                }

                case "totalRefundsPerEmployee": {
                    Map<Employee, Double> map = system.totalRefundsPerEmployee();
                    map.forEach((emp, total) ->
                            System.out.printf("%s -> %.2f%n", emp.name, total));
                    break;
                }

                default:
                    System.out.println("Unknown method: " + method);
            }
        }
    }
}
```

### Solution
```java
// package midterms.m1;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

enum Level {
    IC, //individual contributor
    M,  //middle management
    C   //C-Level executives
}

class Employee {
    String name;
    String jobTitle;
    Level level;

    public Employee(String name, String jobTitle, Level level) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format(
                "Employee: name=%s, title=%s, level=%s",
                name,
                jobTitle,
                level.toString()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name)
                && Objects.equals(jobTitle, employee.jobTitle)
                && level == employee.level;
    }

    @Override
    public int hashCode()  {
        return Objects.hash(name, jobTitle, level);
    }
}

class Item {
    String name;
    String category;
    double price;

    public Item(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %.2f USD", name, category, price);
    }
}

class Receipt {
    String merchant;
    LocalDateTime date;
    List<Item> items;

    public Receipt(String merchant, LocalDateTime date, List<Item> items) {
        this.merchant = merchant;
        this.date = date;
        this.items = items;
    }

    double totalAmount() {
        double sum = 0.0;
        for (int i = 0; i < items.size(); i++) {
            sum += items.get(i).price;
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).toString());
            if (i < items.size() - 1) sb.append("; ");
        }

        return String.format(
                "Receipt: merchant=%s, date=%s, items=%s, total=%.2f USD",
                merchant,
                date,
                sb.toString(),
                totalAmount()
        );
    }
}

class NotSupportedExpenseException extends Exception {
    NotSupportedExpenseException(String message) {
        super(message);
    }
}

// "Bad but correct": one record class + enum + lots of ifs, no inheritance/polymorphism/streams.
enum ExpenseType {
    TRAVEL, RECEIPT
}

class ExpenseRecord {
    ExpenseType type;

    // common
    Employee employee;
    String description;

    // travel fields
    double travelAmount;
    LocalDateTime start;
    LocalDateTime end;
    String country;

    // receipt fields
    Receipt receipt;

    static ExpenseRecord travel(Employee employee, String description, double amount,
                                LocalDateTime start, LocalDateTime end, String country) {
        ExpenseRecord r = new ExpenseRecord();
        r.type = ExpenseType.TRAVEL;
        r.employee = employee;
        r.description = description;
        r.travelAmount = amount;
        r.start = start;
        r.end = end;
        r.country = country;
        return r;
    }

    static ExpenseRecord receipt(Employee employee, String description, Receipt receipt) {
        ExpenseRecord r = new ExpenseRecord();
        r.type = ExpenseType.RECEIPT;
        r.employee = employee;
        r.description = description;
        r.receipt = receipt;
        return r;
    }

    double refund() {
        if (type == ExpenseType.TRAVEL) {
            Double allowance = DailyExpensesPerCountry.ALLOWANCE.get(country);
            if (allowance == null) allowance = 0.0;
            long days = Duration.between(start, end).toDays();
            return travelAmount + allowance * days;
        } else {
            // RECEIPT
            double total = receipt.totalAmount();

            // C-level: full receipt amount
            if (employee.level == Level.C) {
                return total;
            }

            // IC/M: filter by categories with loops
            double sum = 0.0;
            for (int i = 0; i < receipt.items.size(); i++) {
                Item it = receipt.items.get(i);
                String cat = it.category;

                boolean allowed = false;

                // allowed for IC + M
                if (cat.equals("food") || cat.equals("non-alcohol beverage")) {
                    allowed = true;
                }

                // extra for M
                if (!allowed && employee.level == Level.M) {
                    if (cat.equals("transport") || cat.equals("alcohol beverage")) {
                        allowed = true;
                    }
                }

                if (allowed) sum += it.price;
            }
            return sum;
        }
    }

    boolean overlaps(LocalDateTime date) {
        if (type == ExpenseType.TRAVEL) {
            return date.isAfter(start) && date.isBefore(end);
        }
        return false;
    }

    @Override
    public String toString() {
        if (type == ExpenseType.TRAVEL) {
            return String.format(
                    "TravelExpense: employee={%s}, description=%s, baseAmount=%.2f USD, " +
                            "country=%s, start=%s, end=%s, refund=%.2f USD",
                    employee.toString(),
                    description,
                    travelAmount,
                    country,
                    start,
                    end,
                    refund()
            );
        } else {
            return String.format(
                    "ReceiptExpense: employee={%s}, description=%s, receiptAmount=%.2f USD, " +
                            "receiptDate=%s, itemsCount=%d, refund=%.2f USD",
                    employee.toString(),
                    description,
                    receipt.totalAmount(),
                    receipt.date,
                    receipt.items.size(),
                    refund()
            );
        }
    }
}

class ExpenseManagementSystem {
    List<ExpenseRecord> expenses;
    float maxReceiptAmount;

    public ExpenseManagementSystem(float maxReceiptAmount) {
        this.expenses = new ArrayList<>();
        this.maxReceiptAmount = maxReceiptAmount;
    }

    public void addReceiptExpense(Employee employee, String reason, Receipt receipt) throws NotSupportedExpenseException {
        if (receipt.totalAmount() > maxReceiptAmount) {
            throw new NotSupportedExpenseException(String.format(
                    "Receipt with amount %.2f exceeds the max allowed amount for receipt expense %.2f",
                    receipt.totalAmount(),
                    maxReceiptAmount
            ));
        }

        for (int i = 0; i < expenses.size(); i++) {
            ExpenseRecord ex = expenses.get(i);

            // only travel expenses can overlap
            if (ex.type == ExpenseType.TRAVEL) {
                if (ex.employee.equals(employee) && ex.overlaps(receipt.date)) {
                    throw new NotSupportedExpenseException(
                            "You cannot add receipt expense in the same period during an approved travel expense."
                    );
                }
            }
        }

        expenses.add(ExpenseRecord.receipt(employee, reason, receipt));
    }

    public void addTravelExpense(Employee employee, String reason, double amount,
                                 LocalDateTime start, LocalDateTime end, String country) throws NotSupportedExpenseException {
        expenses.add(ExpenseRecord.travel(employee, reason, amount, start, end, country));
    }

    public void printRefunds() {
        // sort descending by refund()
        Collections.sort(expenses, new Comparator<ExpenseRecord>() {
            @Override
            public int compare(ExpenseRecord a, ExpenseRecord b) {
                double ra = a.refund();
                double rb = b.refund();
                // reversed
                if (ra < rb) return 1;
                if (ra > rb) return -1;
                return 0;
            }
        });

        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(expenses.get(i).toString());
        }
    }

    public Map<Employee, Double> totalRefundsPerEmployee() {
        Map<Employee, Double> map = new HashMap<>();
        for (int i = 0; i < expenses.size(); i++) {
            ExpenseRecord ex = expenses.get(i);
            Employee emp = ex.employee;
            double current = 0.0;
            if (map.containsKey(emp)) current = map.get(emp);
            map.put(emp, current + ex.refund());
        }
        return map;
    }
}

class DailyExpensesPerCountry {
    static Map<String, Double> ALLOWANCE = new HashMap<>();

    static {
        ALLOWANCE.put("US", 50.0);
        ALLOWANCE.put("MK", 10.0);
        ALLOWANCE.put("PT", 30.0);

        ALLOWANCE.put("DE", 45.0);   // Germany
        ALLOWANCE.put("AT", 40.0);   // Austria
        ALLOWANCE.put("CH", 55.0);   // Switzerland
        ALLOWANCE.put("FR", 50.0);   // France
        ALLOWANCE.put("IT", 40.0);   // Italy
        ALLOWANCE.put("ES", 35.0);   // Spain
        ALLOWANCE.put("UK", 50.0);   // United Kingdom
        ALLOWANCE.put("NL", 45.0);   // Netherlands
        ALLOWANCE.put("BE", 45.0);   // Belgium
        ALLOWANCE.put("SE", 50.0);   // Sweden
        ALLOWANCE.put("NO", 55.0);   // Norway
        ALLOWANCE.put("DK", 50.0);   // Denmark
        ALLOWANCE.put("PL", 25.0);   // Poland
        ALLOWANCE.put("CZ", 25.0);   // Czech Republic
        ALLOWANCE.put("SK", 20.0);   // Slovakia
        ALLOWANCE.put("HU", 20.0);   // Hungary
        ALLOWANCE.put("HR", 25.0);   // Croatia
        ALLOWANCE.put("BG", 20.0);   // Bulgaria
        ALLOWANCE.put("RO", 20.0);   // Romania
        ALLOWANCE.put("GR", 30.0);   // Greece
        ALLOWANCE.put("RS", 15.0);   // Serbia
        ALLOWANCE.put("AL", 15.0);   // Albania
        ALLOWANCE.put("TR", 20.0);   // Türkiye

        ALLOWANCE.put("CA", 45.0);   // Canada
        ALLOWANCE.put("MX", 25.0);   // Mexico
        ALLOWANCE.put("BR", 20.0);   // Brazil
        ALLOWANCE.put("AR", 18.0);   // Argentina
        ALLOWANCE.put("CL", 22.0);   // Chile

        ALLOWANCE.put("AU", 50.0);   // Australia
        ALLOWANCE.put("NZ", 40.0);   // New Zealand

        ALLOWANCE.put("JP", 45.0);   // Japan
        ALLOWANCE.put("CN", 30.0);   // China
        ALLOWANCE.put("KR", 35.0);   // South Korea
        ALLOWANCE.put("SG", 50.0);   // Singapore
        ALLOWANCE.put("IN", 20.0);   // India
        ALLOWANCE.put("AE", 45.0);   // UAE (Dubai)
        ALLOWANCE.put("SA", 30.0);   // Saudi Arabia
    }
}



public class ExpensesManagementSystemTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Float maxReceiptAmount = Float.parseFloat(sc.nextLine());

        // Create system with some default max amount
        ExpenseManagementSystem system = new ExpenseManagementSystem(maxReceiptAmount);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("END")) break;
            if (line.isEmpty()) continue;

            String[] parts = line.split(";");
            String method = parts[0];


            switch (method) {

                case "addReceiptExpense": {
                    // Format:
                    // addReceiptExpense;Name;Job;IC|M|C;description;amount;merchant;datetime;item|cat|price,...
                    String empName = parts[1];
                    String job = parts[2];
                    Level lvl = Level.valueOf(parts[3]);
                    String description = parts[4];

                    String merchant = parts[5];
                    LocalDateTime dt = LocalDateTime.parse(parts[6]);

                    // Items list
                    String itemsRaw = parts[7];
                    List<Item> items = new ArrayList<>();
                    if (!itemsRaw.equalsIgnoreCase("none")) {
                        for (String itemStr : itemsRaw.split(",")) {
                            String[] ip = itemStr.split("\\|");
                            items.add(new Item(ip[0], ip[1], Double.parseDouble(ip[2])));
                        }
                    }

                    Employee e = new Employee(empName, job, lvl);
                    Receipt r = new Receipt(merchant, dt, items);

                    try {
                        system.addReceiptExpense(e, description, r);
                    } catch (NotSupportedExpenseException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                }

                case "addTravelExpense": {
                    // Format:
                    // addTravelExpense;Name;Job;IC|M|C;description;amount;start;end;country
                    String empName = parts[1];
                    String job = parts[2];
                    Level lvl = Level.valueOf(parts[3]);
                    String description = parts[4];
                    double amount = Double.parseDouble(parts[5]);
                    LocalDateTime start = LocalDateTime.parse(parts[6]);
                    LocalDateTime end = LocalDateTime.parse(parts[7]);
                    String country = parts[8];

                    Employee e = new Employee(empName, job, lvl);

                    try {
                        system.addTravelExpense(e, description, amount, start, end, country);
                    } catch (NotSupportedExpenseException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                }

                case "printRefunds": {
                    system.printRefunds();
                    break;
                }

                case "totalRefundsPerEmployee": {
                    Map<Employee, Double> map = system.totalRefundsPerEmployee();
                    map.forEach((emp, total) ->
                            System.out.printf("%s -> %.2f%n", emp.name, total));
                    break;
                }

                default:
                    System.out.println("Unknown method: " + method);
            }
        }
    }
}
```