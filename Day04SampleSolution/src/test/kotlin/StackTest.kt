import edu.olindsa2024.Stack
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * This is a basic test suite for the main functions of our [Stack] class
 */
class StackTest {
    @Test
    fun pop() {
        val v = Stack<String>()
        v.push("test")
        assertEquals(v.pop(), "test")

        v.push("v1")
        v.push("v2")
        assertEquals(v.pop(), "v2")
    }

    @Test
    fun push() {
        val v = Stack<String>()
        v.push("test")
        assertFalse(v.isEmpty())
    }

    @Test
    fun isEmpty() {
        val v = Stack<String>()
        assertTrue(v.isEmpty())
    }
}