package com.ses.util.collection;

import java.util.List;

public interface CollectionObserver {
	
	/**
	 * <p> Elements in the diff are not garanteed to have been removed from the collection.
	 * The collection could have not contain these elements.
	 * 
	 * <p> same for additions.
	 * 
	 * @param collection
	 * @param isAddition
	 * @param diff
	 */
	<E> void receiveCollectionUpdateEvent(ObservableCollection<E> collection,
			boolean isAddition, List<E> diff);
	
}
