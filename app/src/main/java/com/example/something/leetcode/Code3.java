package com.example.something.leetcode;

import java.util.Arrays;

/**
 * @Description: 选择排序
 * @Author: pan yi
 * @Date: 2022/2/11
 */
public class Code3 {
    public static void main(String[] args) {
        int[] nums = {2, 8, 1, 90, 24, 6};
        sortNum(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void sortNum(int[] nums) {
        int count=0;
        for (int i = 0; i < nums.length - 1; i++) {
            int value = nums[i];
            int k = i;
            for (int j = i + 1; j < nums.length; j++) {
                int result = nums[j];
                if (value > result) {//2此时大于1 value此时就为1
                    k = j;
                    value = result;
                }
                count++;
            }
            if (k != i) {
                nums[k] = nums[i];
                nums[i] = value;
            }
            System.out.println("result: " + Arrays.toString(nums)+" "+count);
        }
    }
}
