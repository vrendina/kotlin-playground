package io.levelsoftware.sandbox.kotlin.algorithms.unionfind

class QuickFind(size: Int) {

    // Hold the array of ids for the connections
    val id = IntArray(size)

    // Initialize an array of ints of size 'size' and set each entry to its index
    init {
        id.forEachIndexed { index, _ -> id[index] = index }
    }

    fun connected(p: Int, q: Int): Boolean {
        return id[p] == id[q]
    }

    fun union(p: Int, q: Int) {
        val pid = id[p]
        val qid = id[q]

        id.forEachIndexed { index, _ ->
            if (id[index] == qid) { id[index] = pid }
        }
    }


}