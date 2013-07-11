package com.ses.util.collection;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class ObservableDeque<E> extends ObservableQueue<E> implements Deque<E> {

	private final Deque<E> deque;

	public ObservableDeque(Collection<E> base) {
		super(base);
		deque = (Deque<E>) base;
	}

	public void addFirst(E e) {
		deque.addFirst(e);
		fireUpdate(e, true);
	}

	public void addLast(E e) {
		deque.addLast(e);
		fireUpdate(e, true);
	}

	public boolean offerFirst(E e) {
		boolean result = deque.offerFirst(e);
		if (result) {
			fireUpdate(e, true);
		}
		return result;
	}

	public boolean offerLast(E e) {
		boolean result = deque.offerLast(e);
		if (result) {
			fireUpdate(e, true);
		}
		return result;
	}

	public E removeFirst() {
		E result = deque.removeFirst();
		fireUpdate(result, false);
		return result;
	}

	public E removeLast() {
		E result = deque.removeLast();
		fireUpdate(result, false);
		return result;
	}

	public E pollFirst() {
		E result = deque.pollFirst();
		fireUpdate(result, false);
		return result;
	}

	public E pollLast() {
		E result = deque.pollLast();
		fireUpdate(result, false);
		return result;
	}

	public E getFirst() {
		return deque.getFirst();
	}

	public E getLast() {
		return deque.getLast();
	}

	public E peekFirst() {
		return deque.peekFirst();
	}

	public E peekLast() {
		return deque.peekLast();
	}

	@SuppressWarnings("unchecked")
	public boolean removeFirstOccurrence(Object o) {
		boolean result = deque.removeFirstOccurrence(o);
		if (result) {
			fireUpdate((E) o, false);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean removeLastOccurrence(Object o) {
		boolean result = deque.removeLastOccurrence(o);
		if (result) {
			fireUpdate((E) o, false);
		}
		return result;
	}

	public void push(E e) {
		deque.push(e);
		fireUpdate(e, true);
	}

	public E pop() {
		E result = deque.pop();
		fireUpdate(result, false);
		return result;
	}

	public Iterator<E> descendingIterator() {
		return new ObservableIterator<E>(this, deque.descendingIterator());
	}

}
