package com.graphql.springbootexample.facade.resolver;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Base class for resolvers with the default implementation
 */
public abstract class BaseResolver<T>  {

	/**
	 * Resolves obtaining the data with the default implementation
	 * 
	 * @param unresolvedList
	 * @return
	 */
	public List<T> resolve(List<T> unresolvedList) {
		List<T> resolvedList = new ArrayList<>();
		if (!requireNonNull(unresolvedList).isEmpty()) {
			for (T element : unresolvedList) {
				if (element != null) {
					resolvedList.add(processElement(element));
				}
			}
		}
		return resolvedList;
	}

	/**
	 * We will check if the entity is resolved and if not, we obtain the "complete" from the persistence
	 * 
	 * @param unresolved
	 * @return
	 */
	private T processElement(final T unresolved) {
		if (unresolved.getClass().equals(unresolvedClass())) {
			T findElement = findById(unresolved);
			return findElement;
		}
		return unresolved;
	}

	/**
	 * Method to implement for the obtaining of the related elements
	 * 
	 * @param unresolved
	 * @return
	 */
	protected abstract T findById(T unresolved);
	
	/**
	 * Method which indicates that an entity is not resolved
	 * 
	 * @return
	 */
	protected abstract Class<?> unresolvedClass();
	
}
