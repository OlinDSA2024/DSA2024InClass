package edu.olindsa2024

/**
 * The Stack class supports a basic stack class.
 * The stack supports push, pop, and isEmpty in O(1)
 *
 * @constructor creates an empty Stack
 * @property head: the top of the stack
 */
class Stack<T> {

    private var head: StackNode<T>?=null

    /**
     * StackNode represents a value and a reference to the next element in the Stack
     * @param T the type of data to store in the node.
     * @property data the data stored
     * @property next the next value in the Stack
     */
    private class StackNode<T>(val data: T,
                                var next: StackNode<T>?)


    /**
     * Take the top element off the stack and return the data.  If the stack is empty
     * null is returned.
     *
     * @return the data stored at the top of the stack
     */
    fun pop(): T? {
        val returnValue = head?.data
        head = head?.next
        return returnValue
    }

    /**
     * Push new [data] onto the top of the stack
     *
     * @param data the data to put on the stack
     */
    fun push(data: T) {
        head = StackNode<T>(data, head)
    }

    /**
     * @return true if the stack is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return head == null
    }
}
