package com.ag.one;

import com.ag.pojo.Edge;
import com.ag.pojo.Node;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class code5 {
    //前缀树类
    public static class TrieNode {
        public int pass;
        public int end;
        public TrieNode[] next;

        public TrieNode() {
            pass = 0;
            end = 0;
            //next[0] == null 没有走向'a'的路
            //next[0] ！= null 有走向'a'的路
            //..。.....
            //next[z] ！= null 有走向'z'的路
            next = new TrieNode[26];

        }
    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * 前缀树
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }
            //将'abc'拆成 'a','b','c'
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new TrieNode();
                }
                node = node.next[index];
                node.pass++;

            }
            node.end++;
        }

        /**
         * 查找某个字符串出现过几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];

            }
            return node.end;
        }

        /**
         * 删除字符串
         *
         * @param word
         */
        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if (--node.next[index].pass == 0) {
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }

        @AllArgsConstructor
        public static class Node {
            public int p;
            public int c;
        }

        public static class minCostCom implements Comparator<Node> {

            @Override
            public int compare(Node o1, Node o2) {
                return o1.c - o2.c;
            }
        }

        public static class maxProfileCom implements Comparator<Node> {

            @Override
            public int compare(Node o1, Node o2) {
                return o1.p - o2.p;
            }
        }

        public static int findMaximizedCapital(int k, int w, int[] profile, int[] capital) {
            PriorityQueue<Node> minCosts = new PriorityQueue<>(new minCostCom());
            PriorityQueue<Node> maxProfile = new PriorityQueue<>(new maxProfileCom());

            for (int i = 0; i < profile.length; i++) {
                minCosts.add(new Node(profile[i], capital[i]));
            }
            for (int j = 0; j < k; j++) {
                while (!minCosts.isEmpty() && minCosts.peek().c < w) {
                    maxProfile.add(minCosts.poll());
                }
                if (maxProfile.isEmpty()) {
                    return w;
                }
            }
            return w;
        }
    }

    /**
     * 潜台词：record[0....i-1]的皇后，任何两个皇后都不共行，不公行，不共列，不共斜线
     *
     * @param i      目前的行数
     * @param record 表示之前的行，放了皇后的位置
     * @param n      整体多少行
     * @return 多少种摆法
     */
    public static int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            //j表示第几列
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            //检查是否共列，或者是否是45度，不用检查是否共行
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }

        }
        return true;
    }

    @AllArgsConstructor
    public static class NodeRecord {
        public Node node;
        public int distance;
    }

    public static class NodeHeap {
        private Node[] nodes;
        private HashMap<Node, Integer> heapIndexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void swap(int i, int j) {
            heapIndexMap.put(nodes[i], j);
            heapIndexMap.put(nodes[j], i);
            Node node = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = node;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distance, distanceMap.get(node)));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);

            }
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smaller = left + 1 < size && distanceMap.get(nodes[left + 1])
                        < distanceMap.get(nodes[left]) ? left + 1 : left;
                if (smaller == index) {
                    break;
                }
                swap(smaller, index);
                index = smaller;
                left = index * 2 + 1;
            }
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void insertHeapify(Node node, Integer index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
    }

    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node node = record.node;
            int distance = record.distance;
            for (Edge edge : node.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(node, distance);
        }
        return result;
    }

    //插入删除的概率相同，有相同的key时，不插入
    // 2.3
    public static class Pool<K>{
        private HashMap<K,Integer> keyIndexMap;
        private HashMap<Integer,K> indexKeyMap;
        private int size;

        public Pool(){
            this.keyIndexMap = new HashMap<K,Integer>();
            this.indexKeyMap = new HashMap<Integer,K>();
            this.size = 0;

        }
        public void insert(K key){
            if(!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key,this.size);
                this.indexKeyMap.put(this.size++,key);
            }
        }

        public void delete(K key){
            if(this.keyIndexMap.containsKey(key)){
                int deleteKey = this.keyIndexMap.get(key);
                int lastIndex = --size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey,deleteKey);
                this.indexKeyMap.put(deleteKey,lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }

        public K getRandom(){
            if(this.size ==0 ){
                return null;
            }
            int randomIndex = (int)(Math.random() * this.size);
            return this.indexKeyMap.get(randomIndex);
        }

    }

}
