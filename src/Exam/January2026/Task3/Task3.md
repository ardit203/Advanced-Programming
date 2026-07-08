<div class="clearfix" id="yui_3_18_1_1_1783429683288_88"><p id="yui_3_18_1_1_1783429683288_87">Да се дефинира класа <code>FlightRewardsEvaluator</code> за работа со патници во програма за лојалност на една авиокомпанија.
Класата треба да ги има следните функционалности:
- <code>FlightRewardsEvaluator()</code> – Конструктор.</p>

<ul>
<li><p><code>public void loadFlights(InputStream is)</code> – Методот од влезниот поток ги чита летовите за секој патник и ги зачувува во соодветна мапа.</p>

<ul>
<li>Секој ред ги содржи податоците за еден патник во формат: <code>passenger_id flight1 flight2 ... flightN</code>, каде секој лет (flight) е во формат <code>destination:miles</code>.</li>
<li>Бројот на летови по патник е произволен. Се смета дека една дестинација може да се појави само еднаш кај еден патник.</li>
</ul></li>
<li><p><code>public void printPassengers(OutputStream os)</code> – Методот на излезниот поток ги печати патниците и нивните информации, како во тест примерите. При печатење, патниците треба да бидат подредени според вкупните изминати милји во опаѓачки редослед, па потоа според <code>passenger_id</code> во растечки редослед.</p></li>
<li><p><code>public Map&lt;String, Integer&gt; groupByDestination()</code> – Методот враќа мапа во која клуч е дестинацијата, а вредност е бројот на патници кои имаат барем еден лет до таа дестинација. Мапата е сортирана според дестинацијата, во растечки редослед.</p></li>
</ul>

<p>--</p>

<p>Define a class <code>FlightRewardsEvaluator</code> for working with passengers in an airline loyalty program.<br>
The class should have the following functionalities:</p>

<ul>
<li><p><code>FlightRewardsEvaluator()</code> – Constructor.</p></li>
<li><p><code>public void loadFlights(InputStream is)</code> – This method reads the flights for each passenger from the input stream and stores them in an appropriate map.</p>

<ul>
<li>Each line contains the data for one passenger in the format: <code>passenger_id flight1 flight2 ... flightN</code>, where each flight is in the format <code>destination:miles</code>.</li>
<li>The number of flights per passenger is arbitrary. It is assumed that a destination can appear only once for a given passenger.</li>
</ul></li>
<li><p><code>public void printPassengers(OutputStream os)</code> – This method prints the passengers and their information to the output stream, as shown in the test examples. When printing, passengers should be sorted by total miles traveled in descending order, and then by <code>passenger_id</code> in ascending order.</p></li>
<li><p><code>public Map&lt;String, Integer&gt; groupByDestination()</code> – This method returns a map where the key is the destination and the value is the number of passengers who have at least one flight to that destination. The map is sorted by destination in ascending order.</p></li>
</ul>
</div>

### Starter code
```java
// package midterms.january;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class FlightRewardsEvaluator {


    public void loadFlights(InputStream is) {
        //TODO
    }

    public void printPassengers(OutputStream os) {
        //TODO
    }

    public Map<String, Integer> groupByDestination() {
        //TODO
        return new TreeMap<>();
    }

    static void wtf(Scanner sc) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("data.txt"));
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if (line.equals("---")){
                break;
            }
            pw.println(line);
        }
        pw.flush();
    }


    public static void main(String[] args) throws Exception {
        FlightRewardsEvaluator evaluator = new FlightRewardsEvaluator();

        Scanner sc = new Scanner(System.in);
        wtf(sc);

        evaluator.loadFlights(new FileInputStream("data.txt"));

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));

        String command = sc.nextLine();
        switch (command) {
            case "PRINT":
                evaluator.printPassengers(System.out);
                break;

            case "GROUP":
                evaluator.groupByDestination().forEach((dest, cnt) ->
                        pw.printf("Destination [%s] passengers [%d]%n", dest, cnt));
                pw.flush();
                break;
                
            default:
                pw.println("Invalid command");
                pw.flush();
                break;
        }
    }

}
```