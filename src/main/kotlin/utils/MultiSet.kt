package utils

/**
 * 支持排序的多集合
 * */
class MultiSet<T : Comparable<T>>(private val sumBy: (T) -> Long = { 0 }) {

    private val valueList = ArrayList<T>()
    private val countMap = HashMap<T, Int>()
    var size = 0
    var sum = 0L
    val min: T
        get() = valueList.first()
    val max: T
        get() {
            return valueList.last()
        }

    fun get(index: Int): T {
        return valueList[index]
    }

    fun add(value: T) {
        val index = valueList.binarySearch(value)
        if (index < 0) {
            valueList.add(-index - 1, value)
            countMap[value] = 1
        } else {
            countMap[value] = countMap[value]!! + 1
        }
        size++
        sum += sumBy(value)
    }

    fun remove(value: T): Boolean {
        if (value !in countMap.keys) return false
        val index = valueList.binarySearch(value)
        if (countMap[value] == 1) {
            countMap.remove(value)
            valueList.removeAt(index)
        } else {
            countMap[value] = countMap[value]!! - 1
        }
        size--
        sum -= sumBy(value)
        return true
    }

    fun popLeft(): T {
        return valueList.first().also {
            remove(it)
        }
    }

    fun popRight(): T {
        return valueList.last().also {
            remove(it)
        }
    }
}