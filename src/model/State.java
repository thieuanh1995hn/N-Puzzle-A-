/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author TA
 */
public class State {

    private String id = ""; // ID trang thai cua ma tran
    private static final int blank = 0;
    private static final int length = Config.getMatrix_size() * Config.getMatrix_size();
    private static final int size = Config.getMatrix_size();
    private int[] Values; //Ma tran 2 chieu bieu dien duoi dang mang 1 chieu
    private int blank_pos;
    private final int heuristic;

    public State(int[] Values) {
        // Khoi tao mang ma tran
        this.Values = Values;
        //Khoi tao vi tri o trong
        for (int i = 0; i < length; i++) {
            if (Values[i] == 0) {
                this.blank_pos = i;
                break;
            }
        }
        //Khoi tao values
        for (int i = 0; i < length; i++) {
            id = id + String.valueOf(Values[i]);
        }
        //Khoi tao heuristc
        int distance = 0;
        //h1 Tổng khoảng cách dịch chuyển ngắn nhất để dịch chuyển các ô sai về vị trí đúng của nó: Manhattan
        if (Config.getHstyle() == 1) {
            
            for (int i = 0; i < length; i++) {
                int c = Values[i];
                if (c != i + 1) {
                    distance++;
                }
            }

        }
        if (Config.getHstyle() == 2) {
            for (int i = 0; i < length; i++) {
                int c = Values[i];
                int v = 0;
                for (int j = 0; j < length; j++) {
                    if (c == Config.getGoalvalues()[j]) {
                        v = j;
                        break;
                    }
                }
                if (c != 0) {
                    int xd = Math.abs((i % size) - (v % size));
                    int yd = Math.abs((i / size) - (v / size));
                    distance += xd + yd;
                }

            }

        }
        //hàm h2 = heuristic1 + chỉ số phạt cặp ô hàng xóm với nhau đang nằm ngược vị trí của nhau
        if (Config.getHstyle() == 3) {
            int a = 0;
            for (int i = 0; i < length; i++) {
                int c = Values[i];
                int v = 0;
                for (int j = 0; j < length; j++) {
                    if (c == Config.getGoalvalues()[j]) {
                        v = j;
                        break;
                    }
                }
                if (c != 0) {
                    int xd = Math.abs((i % size) - (v % size));
                    int yd = Math.abs((i / size) - (v / size));
                    distance += xd + yd;
                }
                //Tính chỉ số phạt
                if ((i != 0) && (i % size != size - 1) && (Values[i] == i + 1) && (Values[i + 1] == i)) {
                    a += 2;
                }
                if ((i != 0) && (i < length - size) && (Values[i] == i + size) && (Values[i + size] == i)) {
                    a += 2;
                }
            }

            distance = distance + a;
        }
        //Hàm ước lượng đề xuất:
        if (Config.getHstyle() == 4) {

            int a = 0;
            for (int i = 0; i < length; i++) {
                int c = Values[i];
                int v = 0;
                for (int j = 0; j < length; j++) {
                    if (c == Config.getGoalvalues()[j]) {
                        v = j;
                        break;
                    }
                }
                if (c != 0) {
                    int xd = (i % size) - (v % size);
                    int yd = (i / size) - (v / size);
                    distance += xd * xd + yd * yd;
                }
                //Tính chỉ số phạt
                if ((i != 0) && (i % size != size - 1) && (Values[i] == i + 1) && (Values[i + 1] == i)) {
                    a += 2;
                }
                if ((i != 0) && (i < length - size) && (Values[i] == i + size) && (Values[i + size] == i)) {
                    a += 2;
                }
            }
            distance = distance - (int) (0.15 * distance) + a;

        }
        this.heuristic = distance;

    }

    public static int getLength() {
        return length;
    }

    public static int getSize() {
        return size;
    }

    public String getId() {
        return id;
    }

    public int[] getValues() {
        return Values;
    }

    public int getBlank_pos() {
        return blank_pos;
    }

    public int getHeuristic() {
        return heuristic;
    }
}
