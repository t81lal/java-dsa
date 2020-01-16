package org.nullbool.dsa.string.impl;

import org.nullbool.dsa.string.Trie;

public class StringTrie implements Trie<String> {

	private static class TrieString implements TrieWord<Character> {
		private final String string;

		private TrieString(String string) {
			this.string = string;
		}

		@Override
		public Character charAt(int index) {
			return string.charAt(index);
		}

		@Override
		public int length() {
			return string.length();
		}
	}

	private final SimpleTrie<Character, TrieString> trie = new SimpleTrie<>();

	@Override
	public void insert(String word) {
		trie.insert(new TrieString(word));
	}

	@Override
	public boolean containsWord(String word) {
		return trie.containsWord(new TrieString(word));
	}

	@Override
	public boolean containsPrefix(String prefix) {
		return trie.containsPrefix(new TrieString(prefix));
	}
	
	@Override
	public void remove(String word) {
		trie.remove(new TrieString(word));
	}
}
