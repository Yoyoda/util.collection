package com.ses.util.collection;

import java.util.Iterator;

class ObservableIterator<E> implements Iterator<E> {

	private final ObservableCollection<E> observable;
	private final Iterator<E> baseIt;
	protected E lastResult;

	ObservableIterator(ObservableCollection<E> collection, Iterator<E> baseIt) {
		super();
		observable = collection;
		this.baseIt = baseIt;
	}

	public boolean hasNext() {
		return baseIt.hasNext();
	}

	public E next() {
		lastResult = baseIt.next();
		return lastResult;
	}

	public void remove() {
		baseIt.remove();
		observable.fireUpdate(lastResult, false);
	}

}