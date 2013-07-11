package com.ses.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class ObservableList<E> extends ObservableCollection<E> implements
		List<E> {

	private final List<E> base;

	/**
	 * @param base
	 *            must be a {@link List}.
	 */
	public ObservableList(Collection<E> base) {
		super(base);
		this.base = (List<E>) base;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		boolean result = base.addAll(index, c);
		fireUpdate(new ArrayList<E>(c), true);
		return result;
	}

	public E get(int index) {
		return base.get(index);
	}

	public E set(int index, E element) {
		E removed = null;
		try {
			removed = base.get(index);
		} catch (Exception e) {
			// do nothing
		}
		E result = base.set(index, element);

		if (removed != null) {
			fireUpdate(removed, false);
		}
		fireUpdate(element, true);

		return result;
	}

	public void add(int index, E element) {
		base.add(index, element);
		fireUpdate(element, true);
	}

	public E remove(int index) {
		E result = base.remove(index);
		fireUpdate(result, false);
		return result;
	}

	public int indexOf(Object o) {
		return base.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return base.lastIndexOf(o);
	}

	public ListIterator<E> listIterator() {
		return new ObservableListIterator<E>(this, base.listIterator());
	}

	public ListIterator<E> listIterator(int index) {
		return new ObservableListIterator<E>(this, base.listIterator(index));
	}

	public List<E> subList(int fromIndex, int toIndex) {
		List<E> baseSubList = base.subList(fromIndex, toIndex);
		ObservableList<E> subList = new ObservableList<E>(baseSubList);
		subList.setObservers(getObservers());
		return subList;
	}

}
