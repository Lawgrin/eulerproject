package za.co.adhd_developers.kotlin.tools

import java.util.*

class KPermUtil<T> @JvmOverloads constructor(arr: Array<T>, permSize: Int = arr.size) {

    private var arr: Array<T>? = null
    private val permSwappings: IntArray

    init {
        this.arr = arr.clone()
        this.permSwappings = IntArray(permSize)
        for (i in permSwappings.indices) {
            permSwappings[i] = i
        }
    }

    operator fun next(): Array<T>? {
        if (arr == null) {
            return null
        }

        val res = Arrays.copyOf(arr!!, permSwappings.size)
        //Prepare next
        var i = permSwappings.size - 1
        while (i >= 0 && permSwappings[i] == arr!!.size - 1) {
            swap(i, permSwappings[i]) //Undo the swap represented by permSwappings[i]
            permSwappings[i] = i
            i--
        }

        if (i < 0) {
            arr = null
        } else {
            val prev = permSwappings[i]
            swap(i, prev)
            val next = prev + 1
            permSwappings[i] = next
            swap(i, next)
        }

        return res
    }

    private fun swap(i: Int, j: Int) {
        val tmp = arr!![i]
        arr!![i] = arr!![j]
        arr!![j] = tmp
    }

}