package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap <K extends Comparable<K>,V> implements Map61B<K,V> {
    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;
        public Node(K k,V v){
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }
    private Node root;
    private int size = 0;

    @Override
    public void clear(){
        root = null;
    }
    public boolean containsKey(K key){
        return containsKey(root,key);
    }
    private boolean containsKey(Node node,K key){
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return containsKey(node.left, key);
        } else if (cmp > 0) {
            return containsKey(node.right, key);
        }
        return true;

    }
    @Override
    public V get (K key){
        return get(root,key);
    }

    private V get(Node node, K key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp<0){
            return get(node.left,key);
        } else if (cmp>0) {
            return get(node.right,key);
        }else{
            return node.value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node node){
        if (node ==null){
            return 0;
        }else{
            return 1+size(node.left)+size(node.right);
        }
    }
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }
    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        addKeys(root, set);
        return set;
    }

    private void addKeys(Node node, Set<K> set) {
        if (node == null) {
            return;
        }
        set.add(node.key);
        addKeys(node.left, set);
        addKeys(node.right, set);
    }

    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            V targetValue = get(key);
            root = remove(root, key);
            size -= 1;
            return targetValue;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node originalNode = node;
            node = getMinChild(node.right);
            node.left = originalNode.left;
            node.right = remove(originalNode.right, node.key);
        }
        return node;
    }

    private Node getMinChild(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMinChild(node.left);
    }

    public V remove(K key, V value) {
        if (containsKey(key)) {
            V targetValue = get(key);
            if (targetValue.equals(value)) {
                root = remove(root, key);
                size -= 1;
                return targetValue;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
    public void printInOrder(){
        printInOrder(root);
    }
    private void printInOrder(Node node){
        if (node != null){
            printInOrder(node.left);
            System.out.println(node.key + ":" +node.value);
            printInOrder(node.right);
        }
    }
}
