package com.example.something.leetcode;

import java.util.Arrays;

/**
 * @Description: 插入排序
 * @Author: pan yi
 * @Date: 2022/2/9
 */
public class Code2 {
    public static void main(String[] args) {
        int nums[] = {2, 8, 1, 90, 24, 6};
        insertSort(nums);
//        System.out.println(Arrays.toString(nums));
    }

    // i=2 8>1  temp=1  j=2&&8>1 [2]=8  j-- j=1 3>1  [1]=3 [0]=1
    public static void insertSort(int[] unsorted) {
        int times=0;
        for (int i = 1; i < unsorted.length; i++) {
            times++;
            if (unsorted[i - 1] > unsorted[i]) {// 3>1
                int temp = unsorted[i];// 1
                int j = i;// j=1
                times++;
                while (j > 0 && unsorted[j - 1] > temp) {// j=1 && 3>1?
                    times++;
                    unsorted[j] = unsorted[j - 1];
                    j--;
//                    System.out.println(Arrays.toString(unsorted));// 8 8 1
                }
                unsorted[j] = temp;// 3 8 1
                System.out.println(Arrays.toString(unsorted));
            }
        }
        System.out.println("times: "+times);
    }
}
