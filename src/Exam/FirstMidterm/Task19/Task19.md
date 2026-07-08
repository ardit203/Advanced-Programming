A class `Coin` is given, which is used to simulate flipping a coin.
The method `flip` in this class, using the `Random` class (which uses a uniform distribution), returns `HEAD` or `TAIL` with equal probability of 0.5 (50%).

Write a class `LoadedCoin` which inherits from the class `Coin` and overrides the `flip` method so that it returns `HEAD` with some probability `P` (0–100%).
The probability `P` is a class variable and is initialized through the constructor.

### Starter code
```java
package Exam.FirstMidterm.Task19;

import java.util.Random;
import java.util.Scanner;


public class LoadedCoinTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int probability = scanner.nextInt();
		Coin c = new Coin();
		int heads = 0;
		int n = 1000;
		for(int i = 0; i < n; i++) {
			SIDE side = c.flip();
			if(side == SIDE.HEAD) {
				heads++;
			}
		}
		if(heads > 450 && heads < 550) {
			System.out.println("YES");
		} else {
            System.out.println("NO");
        }
		c = new LoadedCoin(probability);
		heads = 0;
		for(int i = 0; i < n; i++) {
			SIDE side = c.flip();
			if(side == SIDE.HEAD) {
				heads++;
			}
		}
		if(heads > probability * 10 - 50 && heads < probability * 10 + 50) {
			System.out.println("YES");
		} else {
            System.out.println("NO");
        }
	}
}
enum SIDE {
	HEAD, TAIL
}
class Coin {

	SIDE side;

	public SIDE flip() {
		Random random = new Random();
		boolean isHead = random.nextBoolean();
		if (isHead) {
			return SIDE.HEAD;
		} else {
			return SIDE.TAIL;
		}
	}
}

class LoadedCoin extends Coin {
	// TODO
}
```

### Solution
```java
import java.util.Random;
import java.util.Scanner;


public class LoadedCoinTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int probability = scanner.nextInt();
		Coin c = new Coin();
		int heads = 0;
		int n = 1000;
		for(int i = 0; i < n; i++) {
			SIDE side = c.flip();
			if(side == SIDE.HEAD) {
				heads++;
			}
		}
		if(heads > 450 && heads < 550) {
			System.out.println("YES");
		} else {
            System.out.println("NO");
        }
		c = new LoadedCoin(probability);
		heads = 0;
		for(int i = 0; i < n; i++) {
			SIDE side = c.flip();
			if(side == SIDE.HEAD) {
				heads++;
			}
		}
		if(heads > probability * 10 - 50 && heads < probability * 10 + 50) {
			System.out.println("YES");
		} else {
            System.out.println("NO");
        }
	}
}
enum SIDE {
	HEAD, TAIL
}
class Coin { 

	SIDE side;

	public SIDE flip() {
		Random random = new Random();
		boolean isHead = random.nextBoolean(); 
		if (isHead) {
			return SIDE.HEAD;
		} else {
			return SIDE.TAIL;
		}
	}
}

class LoadedCoin extends Coin {
	private int probability;

    public LoadedCoin(int probability) {
        this.probability = probability;
    }

    @Override
    public SIDE flip(){
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(randomNumber < probability){
            return SIDE.HEAD;
        }else {
            return SIDE.TAIL;
        }
    }
}
```