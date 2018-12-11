# Chronal Calibration (2018 Day #1)
## Part 1
### Description
After feeling like you've been falling for a few minutes, you look at the device's tiny screen. "Error: Device must be calibrated before first use. Frequency drift detected. Cannot maintain destination lock." Below the message, the device shows a sequence of changes in frequency (your puzzle input). A value like +6 means the current frequency increases by 6; a value like -3 means the current frequency decreases by 3.
<br /><br />
For example, if the device displays frequency changes of `+1, -2, +3, +1`, then starting from a frequency of zero, the following changes would occur:
```
Current frequency  0, change of +1; resulting frequency  1.
Current frequency  1, change of -2; resulting frequency -1.
Current frequency -1, change of +3; resulting frequency  2.
Current frequency  2, change of +1; resulting frequency  3.
In this example, the resulting frequency is 3.
```
Here are other example situations:
```
+1, +1, +1 results in  3
+1, +1, -2 results in  0
-1, -2, -3 results in -6
```
Starting with a frequency of zero, what is the resulting frequency after all of the changes in frequency have been applied?
### Plan
Seems simple, we take the input as a list, and parse all items into integers.<br />
Once we do this all we need to do is sum it up.<br />
<br />
Kotlin Example:
```kotlin
input.map(String::toInt).sum()
```
## Part 2
### Description
You notice that the device repeats the same frequency change list over and over. To calibrate the device, you need to find the first frequency it reaches twice.
<br /><br />
For example, using the same list of changes above, the device would loop as follows:
```
Current frequency  0, change of +1; resulting frequency  1.
Current frequency  1, change of -2; resulting frequency -1.
Current frequency -1, change of +3; resulting frequency  2.
Current frequency  2, change of +1; resulting frequency  3.
(At this point, the device continues from the start of the list.)
Current frequency  3, change of +1; resulting frequency  4.
Current frequency  4, change of -2; resulting frequency  2, which has already been seen.
```
In this example, the first frequency reached twice is 2. Note that your device might need to repeat its list of frequency changes many times before a duplicate frequency is found, and that duplicates might be found while in the middle of processing the list.

Here are other examples:
```
+1, -1 first reaches 0 twice.
+3, +3, +4, -2, -4 first reaches 10 twice.
-6, +3, +8, +5, -6 first reaches 5 twice.
+7, +7, -2, -7, -4 first reaches 14 twice.
```
What is the first frequency your device reaches twice?
### Plan
So this part is a bit more difficult, we need to find the first repeating frequency.<br />
There are a few things to know about this frequency list:
* We repeat it to find the repeating value
* Since we repeat the same sequence, all it does is shift a certain way
* Since it shifts, we know the first number which will repeat is in the initial list of resolved frequencies

Since we know that, we first need to make a list of all the resolved frequencies along the list.<br />
So, let's look at a small input for perspective: `+1, +1, +7, -6`<br />
In our frequency list it becomes: `0, 1, 2, 9, <3>`<br />
We mark the end differently though, since it becomes the start of the next iteration, it is our shift.<br />
<br />
With the current list we notice a few things
* The repeating value isn't in the initial list with no work
* The repeating value isn't 0, so the shift isn't the answer
With these 2 out of the way we can continue. We can also find the repeating value manually by determining the shift:
```
0, 1, 2, 9
3, 4, 5, 12
6, 7, 8, 15
9, 10, 11, 18
```
As we can see, 9 is the first repeating number, which does show up in the original list.<br />
So, is there a way to find it earlier?<br />
<br />
YES! There is, using modulo, and modulo is a beautiful thing if we use grouping.<br />
We need to drop the shift from the end, then group them by their modulo value of the shift, keeping the index.<br />
<br />
So we get the groups as follows:
```
group (0): (1, 0), (3, 9) -> 0 mod 3 = 0 | 9 mod 3 = 0
group (1): (2, 1) -> 1 mod 3 = 1
group (2): (3, 2) -> 2 mod 3 = 2
```
As we can see here this is an oversimplified listing, however it fits our purposes.<br />
To find the closest repeating value we find the minimal value between items (of items in the same group).<br />
However, if there becomes multiple with the same difference, the earlier index will always repeat earlier.<br />
This is easy, since we have 1 group with multiple, so 9 - 0 being 9 is the best we have, so the repeating is 9.<br />
When running this with the much larger group of numbers, we get our number quicker than any brute force.
## Code-Solutions
> Languages <br />
> [kotlin](../../jvm/main/kotlin/com/github/coreyshupe/adventofcode/y2018/Day1.kt) <br />
