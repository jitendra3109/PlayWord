package com.jsrathore.wordgame;

import android.util.Log;
import java.util.HashMap;
import static android.util.Log.*;



public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        HashMap<Character,TrieNode> temp_child =children;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            TrieNode trieNode;
            if(temp_child.containsKey(c)){
                trieNode = temp_child.get(c);
            }else{
                trieNode=new TrieNode();
                temp_child.put(c,trieNode);
            }
            temp_child=trieNode.children;
            if(i==s.length()-1)
                trieNode.isWord=true;
        }

    }

    public boolean isWord(String s) {
        TrieNode trieNode=searchNode(s);
        if(trieNode!=null && trieNode.isWord)
            return  true;
        else
            return false;
    }
    public TrieNode searchNode(String s){
        HashMap<Character,TrieNode> temp_child=children;
        TrieNode trieNode=null;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(temp_child.containsKey(c)){
                trieNode=temp_child.get(c);
                temp_child=trieNode.children;

            }else{
                return null;
            }
        }
        return trieNode;
    }

}
