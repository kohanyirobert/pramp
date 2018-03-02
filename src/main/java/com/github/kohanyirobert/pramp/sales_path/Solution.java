package com.github.kohanyirobert.pramp.sales_path;

// https://www.pramp.com/challenge/15oxrQx6LjtQj9JK9XqA
public class Solution {

    public static void main(String[] args) {
        Node lvl4_0 = new Node(1);

        Node lvl3_0 = new Node(1, lvl4_0);
        Node lvl3_1 = new Node(10);

        Node lvl2_0 = new Node(4);
        Node lvl2_1 = new Node(2, lvl3_0);
        Node lvl2_2 = new Node(0, lvl3_1);
        Node lvl2_3 = new Node(1);
        Node lvl2_4 = new Node(5);

        Node lvl1_0 = new Node(5, lvl2_0);
        Node lvl1_1 = new Node(3, lvl2_1, lvl2_2);
        Node lvl1_2 = new Node(6, lvl2_3, lvl2_4);

        Node lvl0_0 = new Node(0, lvl1_0, lvl1_1, lvl1_2);

        System.out.println(SalesPath.getCheapestCost(lvl0_0)); // 7
    }

    static class Node {

        int cost;
        Node[] children = new Node[0];
        Node parent;

        Node(int cost) {
            this.cost = cost;
        }

        Node(int cost, Node... children) {
            this(cost);
            this.children = children;
        }
    }

    static class SalesPath {

        static int getCheapestCost(Node node) {
            if (node.children.length == 0) {
                return node.cost;
            }
            int minSubCost = Integer.MAX_VALUE;
            for (int i = 0; i < node.children.length; i++) {
                Node child = node.children[i];
                int subCost = getCheapestCost(child);
                if (subCost < minSubCost) {
                    minSubCost = subCost;
                }
            }
            return node.cost + minSubCost;
        }
    }
}
