package com.life.algorithm;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/question/next?pid=21283868&qid=830860&tid=30204590
 * 小Q在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有n座高楼排成一行。
 * 小Q从第一栋一直走到了最后一栋，小Q从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？（当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 */
public class CountBuilding {
    public static void  main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n = Integer.valueOf(scanner.nextLine());
        String[] layerString = scanner.nextLine().split(" ");
        List<Integer> layerList = new ArrayList<>();
        for (String num : layerString) {
            layerList.add(Integer.valueOf(num));
        }
        for (int i = 0; i < n; i++) {
            int sum = 1;
            int current = layerList.get(i);
            int hopeLayer = 0;
            for (int j = 0; j < i; j++) {
                hopeLayer = layerList.get(j);
                Boolean flag = true;
                for (int k = j + 1; k < i; k++) {
                    if (layerList.get(k) > hopeLayer) {
                        flag = false;
                    }
                }
                if (flag) {
                    sum ++;
                }
            }
            for (int j = i + 1; j < n; j++) {
                hopeLayer = layerList.get(j);
                Boolean flag = true;
                for (int k = i + 1; k < j; k++) {
                    if (layerList.get(k) > hopeLayer) {
                        flag = false;
                    }
                }
                if (flag) {
                    sum ++;
                }
            }
            System.out.print(sum + " ");
        }
    }

}
