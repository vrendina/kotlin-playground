package io.levelsoftware.sandbox.kotlin.algorithms.unionfind

class QuickUnion(size: Int) {

    // Hold the array of ids for the connections
    val id = IntArray(size)

    // Initialize an array of ints of size 'size' and set each entry to its index
    init {
        id.forEachIndexed { index, _ -> id[index] = index }
    }

    fun root(i: Int): Int {
        var root = i
        while(root != id[root]) root = id[root]
        return root
    }

    fun connected(p: Int, q: Int) = root(p) == root(q)

    fun union(p: Int, q: Int) {
        id[root(q)] = root(p)
    }

}