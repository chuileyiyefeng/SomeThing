package com.example.something.leetcode;

import java.util.Arrays;

/**
 * @Description: shell排序
 * @Author: pan yi
 * @Date: 2022/2/11
 */
public class Code4 {
    public static void main(String[] args) {
        int[] nums = {2, 8, 1, 90, 24, 6,5};
        sort(nums);
        System.out.println("result: "+Arrays.toString(nums));
    }

    static void sort(int[] nums) {
        int length = nums.length;
        int temp;
        int times=0;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = nums[i];
                int j = i - step;
                times++;
                System.out.println("content: "+j+" j-result:"+nums[j]+" "+i+" i-result:"+nums[i]);
                while (j >= 0 && nums[j] > temp) {
                    times++;
                    nums[j + step] = nums[j];
                    j -= step;
                }
                nums[j + step] = temp;
            }
        }
        System.out.println("times: "+times);
    }
}
