package com.ses.util.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

public class ObservableSortedSet<E> extends ObservableSet<E> implements
		SortedSet<E> {

	private final SortedSet<E> set;

	public ObservableSortedSet(Collection<E> base) {
		super(base);
		set = (SortedSet<E>) base;
	}

	public Comparator<? super E> comparator() {
		return set.comparator();
	}

	private ObservableSortedSet<E> doSub(SortedSet<E> baseSubSet) {
		ObservableSortedSet<E> subSet = new ObservableSortedSet<E>(baseSubSet);
		subSet.setObservers(getObservers());
		return subSet;
	}

	public SortedSet<E> subSet(E fromElement, E toElement) {
		return doSub(set.subSet(fromElement, toElement));
	}

	public SortedSet<E> headSet(E toElement) {
		return doSub(set.headSet(toElement));
	}

	public SortedSet<E> tailSet(E fromElement) {
		return doSub(set.tailSet(fromElement));
	}

	public E first() {
		return set.first();
	}

	public E last() {
		return set.last();
	}

}
