/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author TA
 */
public class Config {

    private static int matrix_size;
    private static int hstyle = 1; // Chon kieu tinh heuristic
    private static int[] goalvalues;

    public static int getMatrix_size() {
        return matrix_size;
    }

    public static void setMatrix_size(int matrix_size) {
        Config.matrix_size = matrix_size;
    }

    public static int getHstyle() {
        return hstyle;
    }

    public static void setHstyle(int hstyle) {
        if (hstyle == 1 || hstyle == 2 || hstyle == 3) {
            Config.hstyle = hstyle;
        }
    }

    public static int[] getGoalvalues() {
        return goalvalues;
    }

    public static int[] initGoalvalues() {
        goalvalues = new int[State.getLength()];
        for (int i = 0; i < State.getLength(); i++) {
            goalvalues[i] = i + 1;
        }
        goalvalues[State.getLength() - 1] = 0;
        return goalvalues;
    }

}
