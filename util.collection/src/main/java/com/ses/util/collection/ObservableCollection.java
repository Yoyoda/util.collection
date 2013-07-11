package com.ses.util.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ObservableCollection<E> implements Collection<E> {

	private final Collection<E> base;

	private Collection<CollectionObserver> observers = new ArrayList<CollectionObserver>();

	protected void fireUpdate(List<E> diff, boolean isAddition) {
		for (CollectionObserver observer : observers) {
			observer.receiveCollectionUpdateEvent(this, isAddition, diff);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void fireUpdate(E diff, boolean isAddition) {
		if (diff != null) {
			fireUpdate(Arrays.asList(diff), isAddition);
		}
	}

	public ObservableCollection(Collection<E> base) {
		this.base = base;
	}
	
	/**
	 * @return the {@link Collection} used to fire events.
	 */
	public Collection<CollectionObserver> getObservers() {
		return observers;
	}
	
	/**
	 * Can be use to directly set the collection to use for firing events.
	 * <p>Might be any kind of collection.
	 * <p>by default, observers are stored in an {@link ArrayList}.
	 * 
	 * @param observers
	 */
	public void setObservers(Collection<CollectionObserver> observers) {
		this.observers = observers;
	}

	public boolean add(E e) {
		boolean result = base.add(e);
		if (result) {
			fireUpdate(e, true);
		}
		return result;
	}

	public boolean addAll(Collection<? extends E> c) {
		boolean result = base.addAll(c);
		if (result) {
			fireUpdate(new ArrayList<E>(c), true);
		}
		return result;
	}

	public void clear() {
		ArrayList<E> old = new ArrayList<E>(base);
		base.clear();
		fireUpdate(old, false);
	}

	public boolean contains(Object o) {
		return base.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return base.containsAll(c);
	}

	@Override
	public boolean equals(Object o) {
		return base.equals(o);
	}

	@Override
	public int hashCode() {
		// TODO ?
		return base.hashCode();
	}

	public boolean isEmpty() {
		return base.isEmpty();
	}

	public Iterator<E> iterator() {
		return new ObservableIterator<E>(this, base.iterator());
	}

	@SuppressWarnings("unchecked")
	public boolean remove(Object o) {
		boolean result = base.remove(o);
		if (result) {
			fireUpdate((E) o, false);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean removeAll(Collection<?> c) {
		boolean result = base.removeAll(c);
		if (result) {
			fireUpdate(new ArrayList<E>((Collection<E>) c), false);
		}
		return result;
	}

	public boolean retainAll(Collection<?> c) {
		ArrayList<E> diff = new ArrayList<E>(base);
		boolean result = base.retainAll(c);
		
		if (result) {
			diff.removeAll(c);
			fireUpdate(diff, false);
		}
		return result;
	}

	public int size() {
		return base.size();
	}

	public Object[] toArray() {
		return base.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return base.toArray(a);
	}

}
