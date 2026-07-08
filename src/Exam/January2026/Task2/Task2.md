<div class="clearfix" id="yui_3_18_1_1_1783430769607_88"><p id="yui_3_18_1_1_1783430769607_87">Да се дефинира класа <code>Hospital</code> за управување со посетите на пациентите во една болница. Посетата на пациентот започнува со посета или на општ доктор или на доктор специјалист. Во зависност од наодите на докторот или специјалистот, пациентот може да биде упатен на последователни дополнителни анализи.</p>
<p>За класата да се имплементираат методите:</p>
<ul>
<li><code>void generalCheckup(String patientId, String visitId)</code> - метод со кој се започнува преглед со ID visitId кај општ доктор на пациентот со ID patientId</li>
<li><code>void specialist(String patientId, String visitId)</code> - метод со кој се започнува преглед со ID ID visitId кај доктор специјалист на пациентот со ID patientId</li>
<li><code>addBloodTest(String visitId) </code>- метод со кој прегледот со ID visitID се надополнува со крвна слика&nbsp;</li>
<li><code>addUrineTest(String visitId)</code> - метод со кој прегледот со ID visitId се надополнува со лабораториска анализа на урина</li>
<li><code>addUltrasound(String visitId) </code>- метод со кој прегледот со ID visitId се надополнува со ехо</li>
<li><code>addXRay(String visitId)</code> - метод со кој прегледот со ID visitId се надополнува со рендген снимање
<ul>
<li>пациентот може да биде упатен на рендген снимање само од доктор специјалист (односно потребно е конкретната посета на пациентот да биде започната кај доктор специјалист, а не кај општ доктор). Да се фрли исклучок од тип <code>OperationNotSupportedException</code> при обид општ доктор да упрати пациент на рендген.</li>
</ul>
</li>
<li><code>double totalCostForPatient(String patientId)</code> - метод кој враќа вкупна цена на чинење на сите посети на пациентот patientId во болницата</li>
<li><code>int numberOfVisitsForPatient(String patientId)</code> - метод кој враќа вкупен број на посети на пациентот patientId на болницата</li>
<li><code>double averageVisitCost() </code>- метод кој враќа просечна цена на чинење на сите посети на болницата на сите пациенти</li>
<li><code>printVisits(String patientId)</code> - метод кој печати информации за посетите на пациентот patientId на болницата. Информациите за посетите се сортирани во опаѓачки редослед според цената на чинење на посетите.</li>
</ul>
<p>Ценовниот на чинење на услугите на болницата е следен:</p>
<table style="border-collapse: collapse; width: 35.2871%; height: 189px; border-width: 1px; margin-left: auto; margin-right: auto;" border="1"><colgroup><col style="width: 70.1818%;"><col style="width: 29.8182%;"></colgroup>
<tbody>
<tr style="height: 27px;">
<td><strong>Преглед/услуга</strong></td>
<td><strong>Цена</strong></td>
</tr>
<tr style="height: 27px;">
<td><strong>општ доктор</strong></td>
<td>1200</td>
</tr>
<tr style="height: 27px;">
<td><strong>специјалист</strong></td>
<td>2000</td>
</tr>
<tr style="height: 27px;">
<td><strong>крвна слика</strong></td>
<td>800</td>
</tr>
<tr style="height: 27px;">
<td><strong>лабораторија (урина)</strong></td>
<td>500</td>
</tr>
<tr style="height: 27px;">
<td><strong>ехо</strong></td>
<td>1300</td>
</tr>
<tr style="height: 27px;">
<td><strong>рендген</strong></td>
<td>1500</td>
</tr>
</tbody>
</table>
<p>Во рамки на една посета може само еднаш да се додади определена дополнителна анализа (крвна слика, лабораторија, ехо или рендген)! Да се фрли исклучок од тип <code>OperationNotSupportedException</code> при обид да се додаде дупла анализа.</p>
<p>Задачата да се реши користејќи соодветен шаблон за развој на софтвер, така што лесно би можела да биде биде проширена со нови типови на дополнителни анализи (пр. маркери, генетски испитувања, магнетна резонанца итн.), како и со нови типови на прегледи во болницата (пр. преглед кај стоматолог). Решение без употреба на шаблон за развој на софтвер ќе биде оценето најмногу со 70% од предвидените поени!</p>
<p>--</p>
<p data-start="0" data-end="285">Define a class <strong data-start="15" data-end="27">Hospital</strong> for managing patient visits in a hospital. A patient visit starts with either a visit to a general practitioner or to a specialist doctor. Depending on the findings of the doctor or specialist, the patient may be referred for subsequent additional analyses.</p>
<p data-start="287" data-end="346">For the class, the following methods should be implemented:</p>
<ul data-start="348" data-end="2196">
<li data-start="348" data-end="525">
<p data-start="350" data-end="525"><strong data-start="350" data-end="409"><code data-start="352" data-end="407">void generalCheckup(String patientId, String visitId)</code></strong> – a method that starts a checkup with ID <code data-start="451" data-end="460">visitId</code> with a general practitioner for the patient with ID <code data-start="513" data-end="524">patientId</code>.</p>
</li>
<li data-start="527" data-end="697">
<p data-start="529" data-end="697"><strong data-start="529" data-end="584"><code data-start="531" data-end="582">void specialist(String patientId, String visitId)</code></strong> – a method that starts a checkup with ID <code data-start="626" data-end="635">visitId</code> with a specialist doctor for the patient with ID <code data-start="685" data-end="696">patientId</code>.</p>
</li>
<li data-start="699" data-end="812">
<p data-start="701" data-end="812"><strong data-start="701" data-end="735"><code data-start="703" data-end="733">addBloodTest(String visitId)</code></strong> – a method that supplements the checkup with ID <code data-start="784" data-end="793">visitId</code> with a blood test.</p>
</li>
<li data-start="814" data-end="942">
<p data-start="816" data-end="942"><strong data-start="816" data-end="850"><code data-start="818" data-end="848">addUrineTest(String visitId)</code></strong> – a method that supplements the checkup with ID <code data-start="899" data-end="908">visitId</code> with a laboratory urine analysis.</p>
</li>
<li data-start="944" data-end="1071">
<p data-start="946" data-end="1071"><strong data-start="946" data-end="981"><code data-start="948" data-end="979">addUltrasound(String visitId)</code></strong> – a method that supplements the checkup with ID <code data-start="1030" data-end="1039">visitId</code> with an ultrasound examination.</p>
</li>
<li data-start="1073" data-end="1520">
<p data-start="1075" data-end="1191"><strong data-start="1075" data-end="1104"><code data-start="1077" data-end="1102">addXRay(String visitId)</code></strong> – a method that supplements the checkup with ID <code data-start="1153" data-end="1162">visitId</code> with an X-ray examination.</p>
<ul data-start="1194" data-end="1520">
<li data-start="1194" data-end="1373">
<p data-start="1196" data-end="1373">A patient may be referred for an X-ray only by a specialist doctor (i.e., the specific visit must have been started with a specialist doctor, not with a general practitioner).</p>
</li>
<li data-start="1376" data-end="1520">
<p data-start="1378" data-end="1520">An exception of type <strong data-start="1399" data-end="1435"><code data-start="1401" data-end="1433">OperationNotSupportedException</code></strong> should be thrown if a general practitioner attempts to refer a patient for an X-ray.</p>
</li>
</ul>
</li>
<li data-start="1522" data-end="1679">
<p data-start="1524" data-end="1679"><strong data-start="1524" data-end="1574"><code data-start="1526" data-end="1572">double totalCostForPatient(String patientId)</code></strong> – a method that returns the total cost of all visits of the patient with ID <code data-start="1651" data-end="1662">patientId</code> in the hospital.</p>
</li>
<li data-start="1681" data-end="1838">
<p data-start="1683" data-end="1838"><strong data-start="1683" data-end="1735"><code data-start="1685" data-end="1733">int numberOfVisitsForPatient(String patientId)</code></strong> – a method that returns the total number of visits of the patient with ID <code data-start="1810" data-end="1821">patientId</code> in the hospital.</p>
</li>
<li data-start="1840" data-end="1958">
<p data-start="1842" data-end="1958"><strong data-start="1842" data-end="1873"><code data-start="1844" data-end="1871">double averageVisitCost()</code></strong> – a method that returns the average cost of all hospital visits across all patients.</p>
</li>
<li data-start="1960" data-end="2196">
<p data-start="1962" data-end="2196"><strong data-start="1962" data-end="1997"><code data-start="1964" data-end="1995">printVisits(String patientId)</code></strong> – a method that prints information about the visits of the patient with ID <code data-start="2073" data-end="2084">patientId</code> in the hospital. The visit information should be sorted in descending order according to the cost of the visit.</p>
</li>
</ul>
<p>Pricing of hospital services is as follows:</p>
<table style="width: 28.8773%; border-collapse: collapse; margin-left: auto; margin-right: auto;" border="1">
<thead>
<tr>
<th style="width: 80.0185%;">Examination / Service</th>
<th style="width: 20.1952%;">Price</th>
</tr>
</thead>
<tbody>
<tr>
<td style="width: 80.0185%;">General practitioner</td>
<td style="width: 20.1952%;">1200</td>
</tr>
<tr>
<td style="width: 80.0185%;">Specialist</td>
<td style="width: 20.1952%;">2000</td>
</tr>
<tr>
<td style="width: 80.0185%;">Blood test</td>
<td style="width: 20.1952%;">800</td>
</tr>
<tr>
<td style="width: 80.0185%;">Laboratory (urine)</td>
<td style="width: 20.1952%;">500</td>
</tr>
<tr>
<td style="width: 80.0185%;">Ultrasound</td>
<td style="width: 20.1952%;">1300</td>
</tr>
<tr>
<td style="width: 80.0185%;">X-ray</td>
<td style="width: 20.1952%;">1500</td>
</tr>
</tbody>
</table>
<p>Within a single visit, each additional analysis (blood test, laboratory analysis, ultrasound, or X-ray) may be added only once. An exception of type OperationNotSupportedException should be thrown if there is an attempt to add a duplicate analysis.</p>
<p>The task should be solved using an appropriate software design pattern, so that the solution can easily be extended with new types of additional analyses (e.g., markers, genetic testing, magnetic resonance imaging, etc.) as well as new types of examinations in the hospital (e.g., dental checkups). A solution without using a software design pattern will be graded with at most 70% of the total available points.</p></div>

### Starter code
```java
class OperationNotSupportedException extends Exception {
    public OperationNotSupportedException(String message) {
        super(message);
    }
}


public class HospitalTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hospital hospital = new Hospital();

        while (sc.hasNext()) {
            String command = sc.next();

            try {
                switch (command) {

                    case "generalCheckup":
                        hospital.generalCheckup(sc.next(), sc.next());
                        break;

                    case "specialist":
                        hospital.specialist(sc.next(), sc.next());
                        break;

                    case "addBloodTest":
                        hospital.addBloodTest(sc.next());
                        break;

                    case "addUrineTest":
                        hospital.addUrineTest(sc.next());
                        break;

                    case "addUltrasound":
                        hospital.addUltrasound(sc.next());
                        break;

                    case "addXRay":
                        hospital.addXRay(sc.next());
                        break;

                    case "totalCostForPatient":
                        System.out.println(
                                hospital.totalCostForPatient(sc.next())
                        );
                        break;

                    case "numberOfVisitsForPatient":
                        System.out.println(
                                hospital.numberOfVisitsForPatient(sc.next())
                        );
                        break;

                    case "averageVisitCost":
                        System.out.println(
                                hospital.averageVisitCost()
                        );
                        break;

                    case "printVisits":
                        hospital.printVisits(sc.next());
                        break;

                    case "END":
                        return;
                }
            } catch (OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
```