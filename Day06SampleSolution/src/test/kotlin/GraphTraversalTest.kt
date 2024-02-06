import edu.olindsa2024.GraphTraversal
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.IndexOutOfBoundsException

class GraphTraversalTest {
    private fun makeGraph(): GraphTraversal<String> {
        val g = GraphTraversal<String>()
        assertTrue(g.addVertex("A"))
        assertTrue(g.addVertex("B"))
        assertTrue(g.addVertex("C"))
        assertTrue(g.addVertex("D"))
        assertTrue(g.addVertex("E"))
        assertTrue(g.addVertex("F"))
        assertTrue(g.addEdge("A", "B"))
        assertTrue(g.addEdge("C", "F"))
        assertTrue(g.addEdge("C", "D"))
        assertTrue(g.addEdge("B", "D"))
        assertTrue(g.addEdge("D", "E"))
        assertTrue(g.addEdge("E", "F"))
        assertTrue(g.addEdge("A", "C"))
        return g
    }
    @Test
    fun bfsTest() {
        val g = makeGraph()
        // BFS always finds the path with the minimum number of edges
        assertEquals(g.bfs("A", "F"),
                     listOf("A", "C", "F"))
    }

    @Test
    fun dagTest() {
        val g = makeGraph()
        assertTrue(g.isDAG())
        g.addEdge("F", "A")
        assertFalse(g.isDAG())
    }

    @Test
    fun dfsTest() {
        val g = makeGraph()
        val result = g.dfs("A", "E")
        // We can't be sure which path we will end up with unless we have
        // a way to decide the order in which we visit neighbors of a node
        assertTrue(result == listOf("A", "B", "D", "E") ||
                   result == listOf("A", "C", "D", "E"))
    }
}