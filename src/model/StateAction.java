/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author TA
 */
public class StateAction {

    private static final int length = State.getLength();
    private static final int size = State.getSize();
// Ham kiem tra trang thai Values co the ve dich duoc khong

    public static boolean canReachGoal(int[] Values) {
        int row;
        int Count = 0;
        int posBlank = 0;
        for (int i = 0; i < length; i++) {
            if (Values[i] == 0) {
                posBlank = i;
                break;
            }
        }
        row = posBlank / size + 1;
        for (int i = 0; i < length; i++) {
            int t = Values[i];
            if (t > 0 && t < length) {
                for (int j = i + 1; j < length; j++) {
                    if (Values[j] < t && Values[j] > 0) {
                        Count++;
                    }
                }
            }
        }
        if (size % 2 == 1) {
            return Count % 2 == 0;
        } else {
            return Count % 2 == (row + 1) % 2;
        }
    }

    //Ham tao mot trang thai Values cua State dung de tao ra mot trang thai bat dau voi Values duoc xao tron
    public static int[] InitRandom() {
        //Tao Values theo thu tu tu 1 -> length
        int[] Values = new int[length];
        for (int i = 0; i < length - 1; i++) {
            Values[i] = i + 1;
        }
        Values[length - 1] = 0;
        //Xao tron vi tri cac phan tu
        Random rand = new Random();
        int rdi;
        for (int i = 0; i < length; i++) {
            rdi = rand.nextInt(length);
            if (rdi != i) {
                int tmp = Values[i];
                Values[i] = Values[rdi];
                Values[rdi] = tmp;
            }
        }
        return Values;
    }

    public static void showMatrix(int[] Values) {
        for (int i = 0; i < length; i++) {
            System.out.format("%5d",Values[i]);
            if ((i + 1) % size == 0) {
                System.out.println("\n");
            }
        }
    }

}
