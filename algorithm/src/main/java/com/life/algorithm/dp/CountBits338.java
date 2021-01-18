package com.life.algorithm.dp;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * @author tengyun
 * @date 2021/1/15 16:34
 **/
public class CountBits338 {


    public static void main(String[] args) {
        CountBits338 countBits338 = new CountBits338();
        countBits338.countBits(19);
    }

    public int[] countBits(int num) {
        int[] result = new int[num+1];
        int i = 0;
        while (i <= num) {
            int index = i + 1;
            int number = result[i]+1;
            int j = 0;
            while (index <= num) {
                if (result[index] != 0) {
                    index = i + 1<<(++j);
                    continue;
                }
                result[index] = number;
                index = i + 1<<(++j);
            }
            i++;
        }
        return result;
    }

    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];
        int i = 0, b = 1;
        // [0, b) is calculated
        while (b <= num) {
            // generate [b, 2b) or [b, num) from [0, b)
            while(i < b && i + b <= num){
                ans[i + b] = ans[i] + 1;
                ++i;
            }
            i = 0;   // reset i
            b <<= 1; // b = 2b
        }
        return ans;
    }
}
