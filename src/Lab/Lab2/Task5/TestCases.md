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
0 test
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
2016-10-25T10:15 test
2016-10-25T10:15
test
</pre>
</td>
</tr>

<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
1 879 879
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
2016-10-25T10:15 879
2016-10-25T10:15
879
2016-11-04T10:15 879
2016-11-04T10:15
879
-1
1
false
false
</pre>
</td>
</tr>



<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
2 3600000 sample 3600000 sample
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
1970-01-01T01:00 sample
1970-01-01T01:00
sample
1970-01-01T01:00 sample
1970-01-01T01:00
sample
0
0
true
true
</pre>
</td>
</tr>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
4
873961200000 1
875649600000 2
875955600000 3
876042000000 4
876146400000 5
877068000000 6
878940000000 7
881013600000 8
881802000000 9
891928800000 10
query
867482800000 1744289600000
879722800000 1752688400000
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
1997-06-28T07:26:40 <: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 >: 2025-04-10T12:53:20
1997-11-16T23:26:40 <: 8, 9, 10 >: 2025-07-16T17:53:20
test
1, 2, 3, 4, 5, 6, 7, 8, 9, 10
2, 3, 4, 5, 6, 7, 8, 10
</pre>
</td>
</tr>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
2 3600000 sample 3600000 test
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
1970-01-01T01:00 sample
1970-01-01T01:00
sample
1970-01-01T01:00 test
1970-01-01T01:00
test
0
0
true
true
</pre>
</td>
</tr>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
3 before_inside last before_outside after_inside1 after_inside2 next after_outside
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
next
last
before_inside, last, next, after_inside1, after_inside2
</pre>
</td>
</tr>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
2 3600000 sample 7200000 sample
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
1970-01-01T01:00 sample
1970-01-01T01:00
sample
1970-01-01T02:00 sample
1970-01-01T02:00
sample
-1
1
false
false
</pre>
</td>
</tr>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">
1 5 2
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
2016-10-25T10:15 5
2016-10-25T10:15
5
2016-11-04T10:15 2
2016-11-04T10:15
2
-1
1
false
false
</pre>
</td>
</tr>


<tr valign="top">
      <td style="background: transparent; padding: 6px;">
        <pre style="white-space: pre-wrap; word-break: break-word; margin:0;
                    background: none; padding:0; border:0; box-shadow:none;">

1 2 4
</pre>
</td>
<td style="background: transparent; border: none; padding: 6px;">
<pre style="white-space: pre-wrap; word-break: break-word; margin:0;
background: none; padding:0; border:0; box-shadow:none;">
2016-10-25T10:15 2
2016-10-25T10:15
2
2016-11-04T10:15 4
2016-11-04T10:15
4
-1
1
false
false
</pre>
</td>
</tr>


  </tbody>
</table>
