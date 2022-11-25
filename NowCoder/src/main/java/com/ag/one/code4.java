package com.ag.one;

import com.ag.pojo.Edge;
import com.ag.pojo.Graph;
import com.ag.pojo.Node;

import java.lang.invoke.CallSite;
import java.util.*;

/**
 * map 图
 */
public class code4 {

    /**
     * 二阶矩阵 0 1 9  表示从0-1有权值为9的一条边
     * 2 3 6  表示从2-3有权值为6的一条边
     * 1 2 3  表示从1-2有权值为3的一条边
     *
     * @param matrix
     * @return
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int from = matrix[i][0];
            int to = matrix[i][1];
            int weight = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.next.add(toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);

        }
        return graph;
    }

    /**
     * 从node出发，宽度优先遍历
     *
     * @param node
     */
    public static void mapBfs(Node node) {
        if (node == null) {
            return;
        }
        HashSet<Node> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println("处理");
            for (Node next : cur.next) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    /**
     * 深度优先
     *
     * @param node
     */
    public static void mapDfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        set.add(node);
        stack.push(node);
        System.out.println("---");
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.next) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println("处理");
                    break;
                }
            }
        }
    }

    public static class MySet {
        public HashMap<Node, List<Node>> setMap;


        public MySet(List<Node> nodes) {
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        public boolean isE(Node from, Node to) {
            List<Node> fromNode = setMap.get(from);
            List<Node> toNode = setMap.get(to);
            return fromNode == toNode;
        }

        public void union(Node from, Node to) {
            List<Node> fromNode = setMap.get(from);
            List<Node> toNode = setMap.get(to);
            for (Node cur : toNode) {
                fromNode.add(cur);
                setMap.put(cur, fromNode);
            }
        }
    }

    /**
     * 克鲁斯卡尔算法
     *
     * @param
     * @return
     */
    /*public static Set<Edge> kruskalMST(Graph graph){

    }*/

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o2.weight - o1.weight;
        }
    }

    /**
     * prime算法
     *
     * @param graph
     * @return
     */
    public static Set<Edge> primMST(Graph graph) {
        //解锁的边进入小根堆
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> set = new HashSet<>();
        HashSet<Edge> result = new HashSet<>();
        //开始节点
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()) {
                    //弹出最小值的边
                    Edge edgeTo = queue.poll();
                    Node to = edgeTo.to;//可能的一个新节点
                    if (!set.contains(to)) {
                        set.add(to);
                        result.add(edgeTo);
                        for (Edge nextEdge : to.edges) {
                            queue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static HashMap<Node, Integer> dijkstral(Node head) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);

        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                }
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode)
                        , distance + edge.weight)
                );
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;

    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap
            , HashSet<Node> touchedNodes) {
        {
            Node minNode = null;
            int minDistance = Integer.MIN_VALUE;
            for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
                Node node = entry.getKey();
                Integer distance = entry.getValue();
                if (!touchedNodes.contains(node) && distance < minDistance) {
                    minNode = node;
                    minDistance = distance;
                }
            }
            return minNode;
        }
    }

}
