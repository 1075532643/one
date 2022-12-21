package com.ag.one;

import com.ag.pojo.Edge;
import org.hibernate.validator.cfg.defs.MinDef;

import java.util.*;

//堆
public class code2 {

    //从index位子开始往下做heapFy
    public static void heapFy(int[] arr, int index, int heapSize) {

        int left = (2 * index) + 1;//左孩子下标
        while (left > heapSize) {//代表下方有没有孩子
            //两个孩子中，哪个值大，把哪个坐标赋值给largest；
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1 : left;

            //比较孩子节点和父节点大小
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            code0.swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }

    }

    //堆排序
    public static void heapSort(int[] arr) {
        //堆调整成大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);//logN
        }
        int heapSize = arr.length;
        //排序
        code0.swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapFy(arr, 0, heapSize);//logN
            code0.swap(arr, 0, --heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            code0.swap(arr, index, (index - 1) / 2);
        }
    }

    //基本有序的数组，每个数字调整位置不大于k
    public static void smallHeap(int[] arr, int k) {
        //默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; i < arr.length; i++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        //底层是小根堆，所以默认输出就是有序的
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(8);
        heap.add(2);
        heap.add(4);
        heap.add(6);
        heap.add(5);
        heap.add(7);
        heap.add(1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        //也可以自定义比较器，让他按照大根堆输出
        PriorityQueue<Integer> heap1 = new PriorityQueue<>(new AComp());

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }

    public static class AComp implements Comparator<Integer> {

        //返回负数，认为第一个参数在上面
        //返回正数，认为第二个参数在上面
        //返回0，随便谁放上面都行
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    //单链表的节点
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //判断一个链表是不是回文链表
    public static boolean isPalindrome(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;


    }

    /**
     * 找到链表第一个入环节点
     * 从头开始，快慢指针。第一次相遇时，快指针回到开头，慢指针不动，然后快指针和慢指针都
     * 以一次一步往前走，再次相遇时就是入环节点
     */
    public static Node getLoopNode(Node node) {
        if (node == null || node.next == null || node.next.next == null) {
            return null;
        }
        Node n1 = node.next; //慢指针
        Node n2 = node.next.next; //快指针，一次走两步

        while (n1 != n2) {
            if (node.next == null || node.next.next == null) {
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;

        }
        n2 = node;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }





}
