package com.ses.util.collection;

import java.util.Collection;
import java.util.Set;

public class ObservableSet<E> extends ObservableCollection<E> implements Set<E> {
	
//	private final Set<E> set;

	public ObservableSet(Collection<E> base) {
		super(base);
//		set = (Set<E>) base;
	}

}
