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
public class OpenList extends LinkedList<Node> {

    public Node getMinF_Node() {
        Node min = (Node) this.get(0);
        Iterator itr = this.iterator();
        while (itr.hasNext()) {
            Node node = (Node) itr.next();
            if (node.getF() < min.getF()) {
                min = node;
            }
        }
        this.remove(min);
        return min;
    }
}
