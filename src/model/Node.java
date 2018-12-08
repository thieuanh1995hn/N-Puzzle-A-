/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author TA
 */
public class Node {

    private final State state;
    private Node parent;
    private int g = 0;
    private final int f;

    public Node(final State state, final Node parent) {
        this.state = state;
        this.parent = parent;
        this.g = getParent().f - getParent().getState().getHeuristic() + 1;
        this.f = g + getState().getHeuristic();

    }

    //Ham khoi tao danh cho node xuat phat
    public Node(int[] Values) {
        this.state = new State(Values);
        this.f = getState().getHeuristic();
    }

    // Them node vao list
    private void addOpenlist(int old_loc, int new_loc, OpenList openlist,LinkedList<Node> closelist) {
        int[] val = (int[]) state.getValues().clone();
        val[old_loc] = val[new_loc];
        val[new_loc] = 0;
        State st = new State(val);
        Node node = new Node(st, this);
        boolean isOpened = false;
        boolean isClosed = false;
//        for (int i = 0; i < closelist.size(); i++) {
        Iterator itr = closelist.iterator();
        while (itr.hasNext()) {
            Node go = (Node) itr.next();
            if (go.getState().getId().equals(node.getState().getId())) {
                isClosed = true;
                break;
            }
        }
        if (!isClosed) {
            itr = openlist.iterator();
            while (itr.hasNext()) {
                Node go = (Node) itr.next();
                if (go.getState().getId().equals(node.getState().getId())) {
                    isOpened = true;
                    //Neu Node co cung State.id da duoc mo truoc do thi thay the neu co f nho hon
                    if (go.getF() < node.getF()) {
                        openlist.remove(go);
                        openlist.add(node);

                    }
                    break;
                }
            }
            //Neu Node chua tung duoc mo thi them node vao
            if (!isOpened) {
                openlist.add(node);
            }
        }
    }

    // Them con cua Node vao openlist
    public void addChildToOpenlistThenClose(OpenList openlist, LinkedList<Node> closelist) {

        if ((state.getBlank_pos() / State.getSize()) > 0) {
            addOpenlist(state.getBlank_pos(), state.getBlank_pos() - State.getSize(), openlist, closelist); //UP
        }
        if ((state.getBlank_pos() / State.getSize()) < State.getSize() - 1) {
            addOpenlist(state.getBlank_pos(), state.getBlank_pos() + State.getSize(), openlist, closelist); //DOWN
        }
        if ((state.getBlank_pos() % State.getSize()) > 0) {
            addOpenlist(state.getBlank_pos(), state.getBlank_pos() - 1, openlist, closelist); //LEFT
        }
        if ((state.getBlank_pos() % State.getSize()) < State.getSize() - 1) {
            addOpenlist(state.getBlank_pos(), state.getBlank_pos() + 1, openlist, closelist);//RIGHT
        }
        closelist.add(this);
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public int getG() {
        return g;
    }

    public int getF() {
        return f;
    }

}
