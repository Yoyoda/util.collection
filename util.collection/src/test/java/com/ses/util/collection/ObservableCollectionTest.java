package com.ses.util.collection;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObservableCollectionTest {

	private static class ListMatcher<T> extends BaseMatcher<List<Foo>> {
		
		private final List<T> comp;

		private ListMatcher(List<T> comp) {
			super();
			this.comp = comp;
		}

		public void describeTo(Description description) {
			// TODO Auto-generated method stub
		}

		public boolean matches(Object item) {
			if (item instanceof List) {
				List l = (List) item;
				return l.equals(item) || ( l.containsAll(comp) && comp.containsAll(l) );
			}
			return false;
		}
		
	}
	
	private static <T> ListMatcher isEqualList(List<T> l) {
		return new ListMatcher<T>(l);
	}
	
	private static <T> ListMatcher isEqualList(T... l) {
		return isEqualList(Arrays.asList(l));
	}

	private ObservableCollection<Foo> coll;

	@Before
	public void setUp() throws Exception {
		coll = new ObservableCollection<Foo>(new ArrayList<Foo>());
	}

	@After
	public void tearDown() throws Exception {
		coll = null;
	}

	@Test
	public void testObservableCollection() {
		new ObservableCollection<Foo>(new HashSet<Foo>());
		new ObservableCollection<Foo>(new TreeSet<Foo>());
		new ObservableCollection<Foo>(new LinkedList<Foo>());
		new ObservableCollection<Foo>(new Vector<Foo>());
	}

	@Test
	public void testSetObservers() {
		Collection<CollectionObserver> observers = coll.getObservers();
		assertTrue(observers instanceof ArrayList);

		observers = new TreeSet<CollectionObserver>();
		coll.setObservers(observers);
		assertSame(observers, coll.getObservers());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAdd() {
		CollectionObserver mock = mock(CollectionObserver.class);
		coll.getObservers().add(mock);

		Foo foo = new Foo();
		ArrayList<Foo> arg = new ArrayList<Foo>();
		arg.add(foo);

		coll.add(foo);

		verify(mock).receiveCollectionUpdateEvent(
				same(coll),
				eq(true),
				(List)argThat(contains(foo)));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddAll() {
		CollectionObserver mock = mock(CollectionObserver.class);
		coll.getObservers().add(mock);

		Foo foo = new Foo();
		Foo bar = new Foo();
		ArrayList<Foo> arg = new ArrayList<Foo>();
		arg.add(foo);
		arg.add(bar);

		coll.addAll(arg);

		verify(mock).receiveCollectionUpdateEvent(
				same(coll),
				eq(true),
				(List)argThat(isEqualList(arg)));
	}

	@Test
	public void testClear() {
		// TODO write test
	}

	@Test
	public void testContains() {
		// TODO write test
	}

	@Test
	public void testContainsAll() {
		// TODO write test
	}

	@Test
	public void testIsEmpty() {
		// TODO write test
	}

	@Test
	public void testIterator() {
		// TODO write test
	}

	@Test
	public void testRemove() {
		// TODO write test
	}

	@Test
	public void testRemoveAll() {
		// TODO write test
	}

	@Test
	public void testRetainAll() {
		// TODO write test
	}

}
