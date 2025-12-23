### Req 1
After the concurrent processing of all texts is finished, implement an additional `Callable` task that will aggregate the results from all `Counter` objects and calculate the total number of lines, words, and characters for all texts together.
The task must be executed using the same `ExecutorService`, must return a single `Counter` with `textId = -1`, and the result should be printed after the individual statistics.

### Req 2
**Addition to Task 2:**

You need to implement a mechanism that will limit the number of API calls that are executed in parallel.
After detecting the first API call that did not finish within the allowed time, all other active and not-yet-completed calls must be interrupted.
The API calls that are interrupted must be marked as unsuccessful (**FAILED**).


### Req 3

<div class="no-overflow"><p data-start="47" data-end="266">
<p data-start="301" data-end="485">Extend the program so that,&nbsp;<strong data-start="329" data-end="374">in addition to printing the final balance</strong>, it also prints a <strong data-start="393" data-end="432">deterministic log of all operations</strong>, showing whether each operation succeeded or failed.</p>
<p data-start="487" data-end="648">Each operation must be logged <strong data-start="517" data-end="533">exactly once</strong>, and the output must be <strong data-start="558" data-end="586">ordered by <code data-start="571" data-end="584">operationId</code></strong>, regardless of the order in which the tasks actually execute.</p>
<p data-start="899" data-end="914">Constraints:</p>
<p data-start="1098" data-end="1169">The solution <strong data-start="1111" data-end="1147">must not rely on execution order</strong> or thread scheduling.</p>
<p data-start="1172" data-end="1235">The output <strong data-start="1183" data-end="1215">must always be deterministic</strong> for the same input.</p>
<p data-start="1238" data-end="1306">All shared data structures used for logging must be <strong data-start="1290" data-end="1305">thread-safe</strong>.</p></div>