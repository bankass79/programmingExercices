package programmingExercices;

//This program has a method called sort that sorts an array of int values
//using the quicksort algorithm.  Method main tests it by constructing two
//arrays containing random values.  It compares the time used by this sort
//versus Arrays.sort.
//
//The program includes a "trial run" of the sorting calls prior to the timed
//executions.  Java performs some optimizations that distort the times without
//these trial runs.

import java.util.*;

public class Quick {
 public static final int SIZE = 10000000;

 public static void main(String[] args) {
     // make 2 copies for the comparision (list1, list2) and 2 copies
     // for the trial runs (list3, list4)
     int[] list1 = new int[SIZE];
     int[] list2 = new int[SIZE];
     int[] list3 = new int[SIZE];
     int[] list4 = new int[SIZE];
     fill(list1, list2, list3, list4);

     // perform trial runs to warm up the JVM
     sort(list3);
     Arrays.sort(list4);

     runTests(list1, list2);
     checkMatch(list1, list2);
 }

 // make a random list of ints and put in each of the arrays
 public static void fill(int[] list1, int[] list2, int[] list3,
                         int[] list4) {
     Random r = new Random();
     for (int i = 0; i < SIZE; i++) {
         int number = r.nextInt(2 * SIZE);
         list1[i] = number;
         list2[i] = number;
         list3[i] = number;
         list4[i] = number;
     }
 }        

 // Runs and times this sort and built-in sort using given lists
 public static void runTests(int[] list1, int[] list2) {
     // run and time our sort
     long start = System.currentTimeMillis();
     sort(list1);
     double elapsed1 = (System.currentTimeMillis() - start)/1000.0;
     System.out.println("quicksort took " + elapsed1 + " seconds");
     System.out.println();

     // run and time built-in sort
     start = System.currentTimeMillis();
     Arrays.sort(list2);
     double elapsed2 = (System.currentTimeMillis() - start)/1000.0;
     System.out.println("Arrays.sort took " + elapsed2 + " seconds");
     System.out.println();

     System.out.println("Ratio = " + elapsed1 / elapsed2);
 }

 // compares the lists and reports whether they match
 public static void checkMatch(int[] list1, int[] list2) {
     boolean match = true;
     for (int i = 0; i < SIZE; i++) {
         if (list1[i] != list2[i])
             match = false;
     }
     if (match)
         System.out.println("lists match");
     else
         System.out.println("lists don't match");
 }

 // pre : 0 <= index1, index2 < list.length
 // post: Values of list[x] and list[y] are exchanged.
 private static void swap(int[] list, int index1, int index2) {
     int temp = list[index1];
     list[index1] = list[index2];
     list[index2] = temp;
 }

 // post: uses the middle value of the list as a "pivot" to
 //       partition the list into two sublists: all those values less
 //       than or equal to the pivot appearing first followed by the
 //       pivot followed by all those values greater than the pivot;
 //       places the pivot value between the two sublists and returns
 //       the index of the pivot value
 private static int partition(int[] list, int low, int high) {
     // swap middle value into first position
     swap(list, low, (low + high) / 2);
     // remember pivot
     int pivot = list[low];

     // loop invariant: list divided into 3 parts: values <= pivot followed
     //                 by unknown values followed by values > pivot
     int index1 = low + 1; // index of first unknown value
     int index2 = high;    // index of last unknown value
     while (index1 <= index2) // while some values still unknown
         if (list[index1] <= pivot)
             index1++;
         else if (list[index2] > pivot)
             index2--;
         else {
             swap(list, index1, index2);
             index1++;
             index2--;
         }

     // put the pivot value between the two sublists and return its index
     swap(list, low, index2);
     return index2;
 }

 // post: elements low through high of given list are in sorted
 //       (nondecreasing) order
 public static void sort(int[] list, int low, int high) {
     // uses recursive quicksort to sort elements low through high; base
     // case is a list of 0 or 1 elements, which requires no sorting; in
     // recursive case, the list is partitioned using a pivot value and the
     // two resulting lists are recursively sorted
     if (low < high) {
         int pivotIndex = partition(list, low, high);
         sort(list, low, pivotIndex - 1);
         sort(list, pivotIndex + 1, high);
     }
 }

 // post: given list is in sorted (nondecreasing) order
 public static void sort(int[] list) {
     sort(list, 0, list.length - 1);
 }
}