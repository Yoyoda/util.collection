package com.ses.util.collection;

import java.util.Iterator;
import java.util.ListIterator;

class ObservableListIterator<E> extends ObservableIterator<E> implements
		ListIterator<E> {

	private final ObservableList<E> list;
	private final ListIterator<E> baseIt;

	ObservableListIterator(ObservableCollection<E> collection,
			Iterator<E> baseIt) {
		super(collection, baseIt);
		this.list = (ObservableList<E>) collection;
		this.baseIt = (ListIterator<E>) baseIt;
	}

	public void add(E e) {
		baseIt.add(e);
		list.fireUpdate(e, true);
	}

	public boolean hasPrevious() {
		return baseIt.hasPrevious();
	}

	public E previous() {
		lastResult = baseIt.previous();
		return lastResult;
	}

	public int nextIndex() {
		return baseIt.nextIndex();
	}

	public int previousIndex() {
		return baseIt.previousIndex();
	}

	public void set(E e) {
		baseIt.set(e);
		if (lastResult != null) {
			list.fireUpdate(lastResult, false);
		}
		lastResult = e;
		list.fireUpdate(e, true);
	}

}
