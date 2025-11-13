## **Qualification task for the first midterm**

Define a class **`ShapesApplication`** in which data is stored for multiple windows, where geometric images in the form of squares are drawn.

For this class, define:

### **`ShapesApplication()` — constructor**

### **`int readCanvases(InputStream inputStream)`**

A method that reads information from an input data stream about multiple windows in which squares are drawn.

Each line of the stream contains information about **one window**, in the format:

```
canvas_id size_1 size_2 size_3 … size_n
```

where:

* `canvas_id` is the ID of the window
* after it follow the side lengths of all squares drawn in that window.

The method should return an **integer** that represents the **total number of squares** successfully read across all windows.

### **`void printLargestCanvasTo(OutputStream outputStream)`**

A method that prints to an output stream the **window whose squares have the largest total perimeter**.

The printing should be done in the format:

```
canvas_id squares_count total_squares_perimeter
```