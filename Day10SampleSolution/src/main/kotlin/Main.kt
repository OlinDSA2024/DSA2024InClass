/**
 * Determine a sequence of consecutive values from [x]
 * x_i, ..., x_j such that x_i + x_{i+1} + ... + x_{j} is
 * as large as possible.
 *
 * @param x the sequence to process
 * @return a list of the continuous subsequence with the greatest sum
 */
fun maximumContinuousSubsequence(x: List<Double>) : List<Double> {
    if (x.isEmpty()) {
        return listOf()
    } else if (x.size == 1) {
        return if (x[0] >= 0) listOf(x[0]) else listOf()
    }
    val midPoint = x.size / 2
    val firstHalf = x.slice(0..<midPoint)
    val secondHalf = x.slice(midPoint ..< x.size)

    val firstHalfMCS = maximumContinuousSubsequence(firstHalf)
    val secondHalfMCS = maximumContinuousSubsequence(secondHalf)
    val spanningMCS: List<Double> = maximumSpanningSum(firstHalf, secondHalf)

    // cache these values so that we don't have to recompute them
    val firstHalfMCSSum = firstHalfMCS.sum()
    val secondHalfMCSSum = secondHalfMCS.sum()
    val spanningMCSSum = spanningMCS.sum()

    if (firstHalfMCSSum > secondHalfMCSSum &&
        firstHalfMCSSum > spanningMCSSum) {
        return firstHalfMCS
    } else if (secondHalfMCSSum > spanningMCSSum) {
        return secondHalfMCS
    } else {
        return spanningMCS
    }
}

/**
 * For the two lists, determine the maximum continuous subsequence
 * that spans the two lists.  We assume the original list is [left] + [right]
 * @param left is the first part of the list
 * @param right is the second part of the list
 * @return the maximum continuous subsequence that spans the lists
 */
fun maximumSpanningSum(left: List<Double>, right: List<Double>): List<Double> {
    val maximumLeft: MutableList<Double> = mutableListOf()
    val maximumRight: MutableList<Double> = mutableListOf()

    // Compute the maximum sum suffix of left
    var bestLeftSum = 0.0
    var currLeftSum = 0.0
    var bestLeftSumLowerIndex: Int? = null

    for (i in left.size-1 downTo 0) {
        currLeftSum += left[i]
        if (currLeftSum > bestLeftSum) {
            bestLeftSum = currLeftSum
            bestLeftSumLowerIndex = i
        }
    }

    // Compute the maximum sum prefix on the right
    var bestRightSum = 0.0
    var currRightSum = 0.0
    var bestRightSumUpperIndex: Int? = null
    for (i in right.indices) {
        currRightSum += right[i]
        if (currRightSum > bestRightSum) {
            bestRightSum = currRightSum
            bestRightSumUpperIndex = i
        }
    }

    val bestLeftSequence =
        if (bestLeftSumLowerIndex != null)  left.slice(bestLeftSumLowerIndex ..< left.size) else listOf()
    val bestRightSequence =
        if (bestRightSumUpperIndex != null)  right.slice(0 .. bestRightSumUpperIndex) else listOf()

    return bestLeftSequence + bestRightSequence
}

fun main() {
    val x = listOf(1.0, 44.0, -22.0)
    println(maximumContinuousSubsequence(x))
}