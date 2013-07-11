package com.ses.util.collection;

import java.util.Collection;
import java.util.Queue;

public class ObservableQueue<E> extends ObservableCollection<E> implements
		Queue<E> {

	private final Queue<E> queue;

	public ObservableQueue(Collection<E> base) {
		super(base);
		queue = (Queue<E>) base;
	}

	public boolean offer(E e) {
		boolean result = queue.offer(e);
		if (result) {
			fireUpdate(e, true);
		}
		return result;
	}

	public E poll() {
		E result = queue.poll();
		fireUpdate(result, false);
		return result;
	}

	public E element() {
		return queue.element();
	}

	public E peek() {
		return queue.peek();
	}

	public E remove() {
		E result = queue.remove();
		fireUpdate(result, false);
		return result;
	}

}
