package org.nullbool.dsa.string;

public interface Trie<S> {
	void insert(S word);

	boolean containsWord(S word);

	boolean containsPrefix(S prefix);
	
	void remove(S word);
}
