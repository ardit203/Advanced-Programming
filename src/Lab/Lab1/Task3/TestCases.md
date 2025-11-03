<table style="background: transparent;">
  <thead>
<tr>
<th>Input</th>
<th>Output</th>
</tr>
</thead>

<tbody>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
5
1001 Alice 5 10
1002 Bob 7 25
1003 Carol 10 40
1004 David 2 15
1005 Eva 8 35
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
Doctors that are treating:
Alice (1001) 5 10
Bob (1002) 7 25
Carol (1003) 10 40 [Chief]
David (1004) 2 15
Eva (1005) 8 35

=== All Doctors ===
Alice (1001) 5 10
Bob (1002) 7 25
Carol (1003) 10 40 [Chief]
David (1004) 2 15
Eva (1005) 8 35

=== Doctors with higher number of patients and a higher level of expertise ===
Bob (1002) 7 25
Carol (1003) 10 40 [Chief]
Eva (1005) 8 35

=== Chief doctor (level = 10) ===
Carol (1003) 10 40 [Chief]

=== Increase all expertise levels by 1 (max 10) ===
Alice (1001) 6 10
Bob (1002) 8 25
Carol (1003) 10 40 [Chief]
David (1004) 3 15
Eva (1005) 9 35

=== Increase the level of expertise of every doctor by 1 ===

=== Map doctors to labels ===
Name: Alice, Level: 6
Name: Bob, Level: 8
Name: Carol, Level: 10
Name: David, Level: 3
Name: Eva, Level: 10
</pre>
</td>
</tr>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
6
2001 John 3 18
2002 Mark 4 10
2003 Sarah 2 25
2004 Peter 1 30
2005 Lucy 5 28
2006 Max 6 19
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
Doctors that are treating:
John (2001) 3 18
Mark (2002) 4 10
Sarah (2003) 2 25
Peter (2004) 1 30
Lucy (2005) 5 28
Max (2006) 6 19

=== All Doctors ===
John (2001) 3 18
Mark (2002) 4 10
Sarah (2003) 2 25
Peter (2004) 1 30
Lucy (2005) 5 28
Max (2006) 6 19

=== Doctors with higher number of patients and a higher level of expertise ===

=== Chief doctor (level = 10) ===
No chief found

=== Increase all expertise levels by 1 (max 10) ===
John (2001) 4 18
Mark (2002) 5 10
Sarah (2003) 3 25
Peter (2004) 2 30
Lucy (2005) 6 28
Max (2006) 7 19

=== Increase the level of expertise of every doctor by 1 ===

=== Map doctors to labels ===
Name: John, Level: 4
Name: Mark, Level: 5
Name: Sarah, Level: 3
Name: Peter, Level: 3
Name: Lucy, Level: 6
Name: Max, Level: 7
</pre>
</td>
</tr>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
10
3001 Anna 10 50
3002 Brian 9 15
3003 Chloe 7 45
3004 Daniel 6 22
3005 Emma 3 5
3006 Frank 8 35
3007 Grace 5 40
3008 Henry 10 10
3009 Irene 4 20
3010 Jake 2 55
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
Doctors that are treating:
Anna (3001) 10 50 [Chief]
Brian (3002) 9 15
Chloe (3003) 7 45
Daniel (3004) 6 22
Emma (3005) 3 5
Frank (3006) 8 35
Grace (3007) 5 40
Henry (3008) 10 10 [Chief]
Irene (3009) 4 20
Jake (3010) 2 55

=== All Doctors ===
Anna (3001) 10 50 [Chief]
Brian (3002) 9 15
Chloe (3003) 7 45
Daniel (3004) 6 22
Emma (3005) 3 5
Frank (3006) 8 35
Grace (3007) 5 40
Henry (3008) 10 10 [Chief]
Irene (3009) 4 20
Jake (3010) 2 55

=== Doctors with higher number of patients and a higher level of expertise ===
Anna (3001) 10 50 [Chief]
Chloe (3003) 7 45
Frank (3006) 8 35

=== Chief doctor (level = 10) ===
Anna (3001) 10 50 [Chief]

=== Increase all expertise levels by 1 (max 10) ===
Anna (3001) 10 50 [Chief]
Brian (3002) 10 15 [Chief]
Chloe (3003) 8 45
Daniel (3004) 7 22
Emma (3005) 4 5
Frank (3006) 9 35
Grace (3007) 6 40
Henry (3008) 10 10 [Chief]
Irene (3009) 5 20
Jake (3010) 3 55

=== Increase the level of expertise of every doctor by 1 ===

=== Map doctors to labels ===
Name: Anna, Level: 10
Name: Brian, Level: 10
Name: Chloe, Level: 9
Name: Daniel, Level: 7
Name: Emma, Level: 4
Name: Frank, Level: 10
Name: Grace, Level: 7
Name: Henry, Level: 10
Name: Irene, Level: 5
Name: Jake, Level: 4
</pre>
</td>
</tr>


  </tbody>
</table>
