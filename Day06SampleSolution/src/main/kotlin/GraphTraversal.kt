package edu.olindsa2024

class GraphTraversal<VertexType> {
    private var vertices: MutableSet<VertexType> = mutableSetOf()
    private var edges: MutableMap<VertexType, MutableSet<VertexType>> = mutableMapOf()

    /**
     * Add the vertex [v] to the graph
     * @param v the vertex to add
     * @return true if the vertex is successfully added, false if the vertex
     *   was already in the graph
     */
    fun addVertex(v: VertexType): Boolean {
        if (vertices.contains(v)) {
            return false
        }
        vertices.add(v)
        return true
    }

    /**
     * Add an edge between vertex [from] connecting to vertex [to]
     * @param from the vertex for the edge to originate from
     * @param to the vertex to connect the edge to
     * @return true if the edge is successfully added and false if the edge
     *     can't be added or already exists
     */
    fun addEdge(from: VertexType, to: VertexType): Boolean {
        if (!vertices.contains(from) || !vertices.contains(to)) {
            return false
        }
        edges[from]?.also { currentAdjacent ->
            if (currentAdjacent.contains(to)) {
                return false
            }
            currentAdjacent.add(to)
        } ?: run {
            edges[from] = mutableSetOf(to)
        }
        return true
    }

    /**
     * Clear all vertices and edges
     */
    fun clear() {
        vertices = mutableSetOf()
        edges = mutableMapOf()
    }

    /**
     * Search through a graph using a breadth-first search
     * @param start the node to start the search
     * @param target the node to search for
     * @return the path from start to target (if one exists) and null otherwise
     */
    fun bfs(start: VertexType, target: VertexType): List<VertexType>? {
        val priorityList = MyQueue<VertexType>()
        val marked: MutableSet<VertexType> = mutableSetOf()
        val parents: MutableMap<VertexType, VertexType> = mutableMapOf()
        marked.add(start)
        priorityList.enqueue(start)
        while (true) {
            // bail out if we don't have anymore values to check
            // Note: using the Elvis operator ensures next is non-nullable
            val next = priorityList.dequeue() ?: break
            if (next == target) {
                return reconstructPath(parents, target)
            }
            edges[next]?.forEach { neighbor ->
                if (!marked.contains(neighbor)) {
                    parents[neighbor] = next
                    marked.add(neighbor)
                    priorityList.enqueue(neighbor)
                }
            }
        }
        return null
    }

    /**
     * Search through a graph using a depth-first search
     * @param start the node to start the search
     * @param target the node to search for
     * @return the path from start to target (if one exists) and null otherwise
     */
    fun dfs(start: VertexType, target: VertexType):List<VertexType>? {
        val priorityList = MyStack<VertexType>()
        val marked: MutableSet<VertexType> = mutableSetOf()
        // we use parents to reconstruct the path back to [start]
        val parents: MutableMap<VertexType, VertexType> = mutableMapOf()
        marked.add(start)
        priorityList.push(start)
        while (true) {
            // bail out if we don't have anymore values to check
            // Note: using the Elvis operator ensures next is non-nullable
            val next = priorityList.pop() ?: break
            if (next == target) {
                return reconstructPath(parents, target)
            }
            edges[next]?.forEach { neighbor ->
                if (!marked.contains(neighbor)) {
                    parents[neighbor] = next
                    marked.add(neighbor)
                    priorityList.push(neighbor)
                }
            }
        }
        return null
    }

    /**
     * We use Kahn's algorithm (https://en.wikipedia.org/wiki/Topological_sorting)
     * @return true iff the graph is a directed-acyclic graph
     */
    fun isDAG(): Boolean {
        val L: MutableList<VertexType> = mutableListOf()
        val gCopy = GraphTraversal<VertexType>()
        gCopy.vertices = vertices.toMutableSet()
        gCopy.edges = edges.toMutableMap()
        val S = gCopy.getNodesWithNoIncomingEdges()

        while (S.isNotEmpty()) {
            val n = S.first()
            println(n)
            S.remove(n)
            L.add(n)
            val currentNeighbors = gCopy.edges[n] ?: mutableSetOf()
            // remove all edges coming from n
            gCopy.edges.remove(n)
            // TODO: this is not efficient, we would like a way to find incoming edges easily
            val nodesToAdd = currentNeighbors.intersect(gCopy.getNodesWithNoIncomingEdges())
            S.addAll(nodesToAdd)
        }
        return gCopy.edges.isEmpty()
    }

    private fun getNodesWithNoIncomingEdges(): MutableSet<VertexType> {
        val unmarked = vertices.toMutableSet()
        for (vertex in vertices) {
            edges[vertex]?.forEach() { neighbor ->
                unmarked.remove(neighbor)
            }
        }
        return unmarked
    }

    /**
     * Backtrack through the path to reconstruct the path
     * from [end] to the path start
     *
     */
    private fun reconstructPath(parents: MutableMap<VertexType, VertexType>,
                                end: VertexType): List<VertexType> {
        val returnValue = mutableListOf(end)
        var curr = end
        while (true) {
            curr = parents[curr] ?: break
            returnValue.add(0, curr)
        }
        return returnValue
    }
}