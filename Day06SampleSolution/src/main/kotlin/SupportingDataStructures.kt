package edu.olindsa2024

import java.util.ArrayDeque

/**
 * @param T the type of value to store in the queue
 * @property deque the data structure to implement the Stack
 */
class MyStack<T> {
    private val deque = ArrayDeque<T>()

    /**
     * Add [v] to the top of the stack
     * @param v the element to add
     */
    fun push(v: T) {
        deque.addFirst(v)
    }

    /**
     * Remove element from the top of the stack
     * @return the top element or null if none exists
     */
    fun pop():T? {
        if (deque.isEmpty()) {
            return null
        } else {
            return deque.removeFirst()
        }
    }

    /**
     * @return true if empty, false otherwise
     */
    fun isEmpty():Boolean {
        return deque.isEmpty()
    }

    /**
     * @return the top element in the stack or null if empty
     */
    fun peek():T? {
        return deque.peekFirst()
    }
}


/**
 * @param T the type of value to store in the queue
 * @property deque the data structure to implement the Queue
 */
class MyQueue<T> {
    private val deque = ArrayDeque<T>()

    /**
     * Add [v] to the end of the queue
     * @param v the element to add
     */
    fun enqueue(v: T) {
        deque.addLast(v)
    }

    /**
     * Remove element from the front of the queue
     * @return the first element or null if none exists
     */
    fun dequeue():T? {
        if (deque.isEmpty()) {
            return null
        } else {
            return deque.removeFirst()
        }
    }

    /**
     * @return true if empty, false otherwise
     */
    fun isEmpty():Boolean {
        return deque.isEmpty()
    }

    /**
     * @return the first element in the queue or null if empty
     */
    fun peek():T? {
        return deque.peekFirst()
    }
}