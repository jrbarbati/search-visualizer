package com.jrbarbati.search.factory;

import com.jrbarbati.search.Search;
import com.jrbarbati.search.SearchAlgorithm;
import com.jrbarbati.search.factory.exception.UnableToConstructSearchException;

import java.lang.reflect.Constructor;

public class SearchFactory
{
    public Search getSearch(String searchType)
    {
        try
        {
            SearchAlgorithm searchAlgorithm = SearchAlgorithm.valueOf(searchType.toUpperCase());
            Class<?> searchClass = Class.forName(searchAlgorithm.getSearchClass().getName());
            Constructor<?> constructor = searchClass.getConstructor();

            return (Search) constructor.newInstance();
        }
        catch (Exception e)
        {
            throw new UnableToConstructSearchException(String.format("Unable to construct a search of type %s", searchType), e);
        }
    }
}
