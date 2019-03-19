package com.jrbarbati.search;

public enum SearchAlgorithm
{
    DFS(DepthFirstSearch.class),
    BFS(BreadthFirstSearch.class),
    UCS(UniformCostSearch.class),
    ASTAR(AStarSearch.class);

    private Class<? extends Search> searchClass;

    SearchAlgorithm(Class<? extends Search> searchClass)
    {
        this.searchClass = searchClass;
    }

    public Class<? extends Search> getSearchClass() {
        return searchClass;
    }
}
