package com.ses.util.collection;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObservableListTest {
	
	private int result;
	private ObservableList<Foo> list;

	private void attachListener(ObservableList<Foo> list) {
		list.getObservers().add(new CollectionObserver() {
			
			public <E> void receiveCollectionUpdateEvent(
					ObservableCollection<E> collection, boolean isAddition, List<E> diff) {
				result++;
			}
		});
	}

	@Before
	public void setUp() throws Exception {
		result = 0;
		list = new ObservableList<Foo>(new ArrayList<Foo>());
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testObservableList() {
		// TODO write test
	}

	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		// TODO write test
	}

	@Test
	public void testSet() {
		list.getObservers().add(new CollectionObserver() {
			
			public <E> void receiveCollectionUpdateEvent(
					ObservableCollection<E> collection, boolean isAddition, List<E> diff) {
				assertTrue(isAddition);
				assertSame(list, collection);
				assertTrue(diff.contains(Foo.FOO));
				result++;
			}
		});
		
		list.add(Foo.FOO);
		
		assertEquals(1, list.size());
		assertEquals(1, result);
	}

	@Test
	public void testAddIntE() {
		list.getObservers().add(new CollectionObserver() {
			
			public <E> void receiveCollectionUpdateEvent(
					ObservableCollection<E> collection, boolean isAddition, List<E> diff) {
				assertTrue(isAddition);
				assertSame(list, collection);
				assertTrue(diff.contains(Foo.FOO));
				result++;
			}
		});
		
		list.add(0, Foo.FOO);
		
		assertEquals(1, list.size());
		assertEquals(1, result);
	}

	@Test
	public void testRemoveInt() {
		attachListener(list);

		list.add(Foo.FOO);
		list.remove(0);
		
		assertEquals(0, list.size());
		assertEquals(2, result);
	}

	@Test
	public void testListIterator() {
		list.add(Foo.FOO);
		list.getObservers().add(new CollectionObserver() {
			
			public <E> void receiveCollectionUpdateEvent(
					ObservableCollection<E> collection, boolean isAddition, List<E> diff) {
				assertFalse(isAddition);
				assertSame(list, collection);
				assertTrue(diff.contains(Foo.FOO));
				result++;
			}
		});
		
		for (Iterator<Foo> it = list.iterator(); it.hasNext();) {
			Foo next = it.next();
			assertSame(Foo.FOO, next);
			it.remove();
		}
		
		assertTrue(list.isEmpty());
		assertEquals(1, result);
	}

	@Test
	public void testListIteratorInt() {
		// TODO write test
	}

	@Test
	public void testSubList() {
		list.add(Foo.FOO);
		list.add(Foo.BAR);
		List<Foo> subList = list.subList(1, 2);
		attachListener(list);
		
		subList.clear();

		assertEquals(0, subList.size());
		assertEquals(1, list.size());
		assertEquals(1, result);
	}

}
