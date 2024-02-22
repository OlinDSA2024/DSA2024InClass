import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainKtTest {
    @Test
    fun test1() {
        val x = listOf(1.0, 44.0, -22.0)
        assertEquals(maximumContinuousSubsequence(x), listOf(1.0, 44.0))
    }

    @Test
    fun testHelper() {
        val left = listOf(22.0, -1.0, 20.0)
        val right = listOf(-30.0, 2.0)
        assertEquals(maximumSpanningSum(left, right),
                     listOf(22.0, -1.0, 20.0))
    }

    @Test
    fun test2() {
        val x: List<Double> = listOf(-2.0,2.0,-2.0,-2.0,3.0,2.0)
        assertEquals(maximumContinuousSubsequence(x), listOf(3.0, 2.0))
    }
}