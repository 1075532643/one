package com.ag.one;

import com.ag.pojo.Node;

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
         * @param word
         * @return
         */
        public int search(String word){
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] -'a';
                if(node.next[index] == null){
                    return 0;
                }
                node = node.next[index];

            }
            return node.end;
        }

        public void delete(String word){
            if(search(word)  != 0 ){
                char[] chars = word.toCharArray();

            }
        }
    }




}
