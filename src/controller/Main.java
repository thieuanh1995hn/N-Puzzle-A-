/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Config;
import model.Node;
import model.OpenList;
import model.State;
import model.StateAction;

/**
 *
 * @author TA
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        LinkedList<Node> closeList = new LinkedList<Node>();
        OpenList openList = new OpenList();
        ArrayList<int[]> wayToGoal = new ArrayList<int[]>();
        boolean isPossible;
        System.out.print("Nhập vào n:  ");
        Config.setMatrix_size(sc.nextInt());
        char choose = '0';
        while (choose != '1' && choose != '2' && choose != '3'&& choose != '4') {
            System.out.print("Chọn phương thức tính heuristic  1.h1   2.h2   3.h3   4.h4: ");
            choose = sc.next().trim().charAt(0);
        }
        switch (choose) {
            case '1':
                Config.setHstyle(1);
            case '2':
                Config.setHstyle(2);
            case '3':
                Config.setHstyle(3);
                  case '4':
                Config.setHstyle(4);
        }
        //Khoi tao trang thai dich

        System.out.println("Trạng thái đích:");

        Config.initGoalvalues();
        StateAction.showMatrix(Config.getGoalvalues());
        //---------
        sc.nextLine();
        // khoi tao start node
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thực hiện tự động xáo trộn tạo trạng thái đầu tiên");

        int[] StartValues = StateAction.InitRandom();

        isPossible = StateAction.canReachGoal(StartValues);

        while (!isPossible) {

            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            StateAction.showMatrix(StartValues);
            System.out.println("Trạng thái này không thể chuyển về đích được! \n");
            System.out.println("Tạo lại: \n");
            StartValues = StateAction.InitRandom();
            isPossible = StateAction.canReachGoal(StartValues);

        }

        StateAction.showMatrix(StartValues);
        System.out.println("Trạng thái này có thể chuyển được về đích. ");

        Node StartNode = new Node(StartValues);
        openList.add(0, StartNode);
        //Lấy ra khỏi tập mở
        int h = 1;
        Node next = StartNode;
        //Đưa các con vào tập mở sau đó đưa chính nó vào tập đóng
        //   next.addChildToOpenlistThenClose(openList, closeList); 

        try {
            while (h != 0) {
                next = openList.getMinF_Node();
                h = next.getState().getHeuristic();
                next.addChildToOpenlistThenClose(openList, closeList);
            }
        } catch (Exception e) {
        }

        System.out.print("Số trạng thái đã được xét: ");
        System.out.println(closeList.size());
        Node list = next;
        while (!list.getState().getId().equals(StartNode.getState().getId())) {
            wayToGoal.add(list.getState().getValues());
            list = list.getParent();
        }
        System.out.println("Số bước để đi tới đích là " + wayToGoal.size());
        for (int i = wayToGoal.size() - 1; i >= 0; i--) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Bước " + (wayToGoal.size() - i) + " :");
            StateAction.showMatrix(wayToGoal.get(i));
        }
    }
}
