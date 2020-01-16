package org.nullbool.dsa.string.impl;

import java.util.HashMap;
import java.util.Map;

import org.nullbool.dsa.string.Trie;

/**
 * A Trie (also known as a prefix tree) is a tree data structure that allows for
 * theta(n) search of a word or a prefix or a word. <br>
 * All descendants of a node have a common prefix
 * 
 * @author Bilal
 *
 */
public class SimpleTrie<C, S extends TrieWord<C>> implements Trie<S> {

	private class TrieNode {
		private final Map<C, TrieNode> children = new HashMap<>();
		private boolean isEnd;

		TrieNode(boolean isEnd) {
			this.isEnd = isEnd;
		}
		
		boolean hasChildren() {
			return !children.isEmpty();
		}

		void addChild(S prefix, int index, TrieNode n) {
			C c = prefix.charAt(index);
			if (children.containsKey(c)) {
				throw new IllegalArgumentException();
			} else {
				children.put(c, n);
			}
		}

		TrieNode findDirect(S prefix, int index) {
			C c = prefix.charAt(index);
			if (children.containsKey(c)) {
				return children.get(c);
			} else {
				return null;
			}
		}

		TrieNode find(S prefix, int index) {
			if (index == prefix.length()) {
				return this;
			}
			TrieNode c = findDirect(prefix, index);
			if (c == null) {
				return null;
			} else {
				return c.find(prefix, index + 1);
			}
		}
	}

	private TrieNode root = new TrieNode(true);

	@Override
	public void insert(S word) {
		int len = word.length();
		TrieNode cur = root;
		for (int i = 0; i < len; i++) {
			TrieNode next = cur.findDirect(word, i);
			if (next == null) {
				next = new TrieNode(false);
				cur.addChild(word, i, next);
			}
			cur = next;
		}
		cur.isEnd = true;
	}

	@Override
	public boolean containsWord(S word) {
		TrieNode n = root.find(word, 0);
		return n != null && n.isEnd;
	}

	@Override
	public boolean containsPrefix(S prefix) {
		return root.find(prefix, 0) != null;
	}

	@Override
	public void remove(S word) {
		TrieNode n = root.find(word, 0);
		if(n == null) {
			return;
		}
		if(n.hasChildren()) {
			n.isEnd = false;
			return;
		}
		
	}
	public static void main(String[] args) {
		Trie<String> t = new StringTrie();
		t.insert("hello");
		System.out.println(t.containsPrefix("hello"));
	}
}
