package com.example.something.leetcode;

import java.util.Arrays;

/**
 * @Description: 插入排序
 * @Author: pan yi
 * @Date: 2022/2/9
 */
public class Code2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertSort()));
    }

    public static int[] insertSort() {
        int times = 0;
        int[] unsorted = {2, 8, 1, 90, 24, 6};
        for (int i = 1; i < unsorted.length; i++) {
            times++;
            int temp = unsorted[i];
            int j = i;

            while (j > 0 && temp < unsorted[j - 1]) {// 2 8 8 j=2
                System.out.println("j=" + j + " temp=" + temp);
                unsorted[j] = unsorted[j - 1];
                times++;
                j--;
                System.out.println(Arrays.toString(unsorted));
            }
            System.out.println("循环外");
            if (j != i) {
                unsorted[j] = temp;
            }
            System.out.println(Arrays.toString(unsorted));
            System.out.println("times: " + times);
        }
        return unsorted;
    }

}
