package com.example.something.leetcode;

import android.app.AlertDialog;

import java.util.Arrays;

/**
 * @Description: 冒泡排序算法
 * @Author: pan yi
 * @Date: 2022/1/25
 */
public class Code1 {
    public static void main(String[] args) {
        int[] nums = {2, 8, 1, 90, 24, 6};
        // 1 31 8 7
        // 1  8 31 7
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public static boolean isChange;

    private static void sort(int[] nums) {
        isChange = true;
        int times = 0;
        for (int j = 0; j < nums.length - 1 && isChange; j++) {
            isChange = false;
            for (int i = 0; i < nums.length - j - 1; i++) {// 里循环 把最大的数到队尾
                times++;

                if (nums[i] > nums[i + 1]) {
                    isChange = true;
                    swap(nums, i, i + 1);
                }
            }
        }
        System.out.println("times " + times);
    }

    private static void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
