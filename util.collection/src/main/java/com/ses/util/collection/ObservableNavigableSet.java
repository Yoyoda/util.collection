package com.ses.util.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;

public class ObservableNavigableSet<E> extends ObservableSortedSet<E> implements
		NavigableSet<E> {

	private final NavigableSet<E> set;

	public ObservableNavigableSet(Collection<E> base) {
		super(base);
		set = (NavigableSet<E>) base;
	}

	private ObservableNavigableSet<E> doSub(NavigableSet<E> baseSubSet) {
		ObservableNavigableSet<E> subSet = new ObservableNavigableSet<E>(
				baseSubSet);
		subSet.setObservers(getObservers());
		return subSet;
	}

	public E lower(E e) {
		return set.lower(e);
	}

	public E floor(E e) {
		return set.floor(e);
	}

	public E ceiling(E e) {
		return set.ceiling(e);
	}

	public E higher(E e) {
		return set.higher(e);
	}

	public E pollFirst() {
		E result = set.pollFirst();
		fireUpdate(result, false);
		return result;
	}

	public E pollLast() {
		E result = set.pollLast();
		fireUpdate(result, false);
		return result;
	}

	public NavigableSet<E> descendingSet() {
		return doSub(set.descendingSet());
	}

	public Iterator<E> descendingIterator() {
		return new ObservableIterator<E>(this, set.descendingIterator());
	}

	public NavigableSet<E> subSet(E fromElement, boolean fromInclusive,
			E toElement, boolean toInclusive) {
		return doSub(set.subSet(fromElement, fromInclusive, toElement,
				toInclusive));
	}

	public NavigableSet<E> headSet(E toElement, boolean inclusive) {
		return doSub(set.headSet(toElement, inclusive));
	}

	public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
		return doSub(set.tailSet(fromElement, inclusive));
	}

}
