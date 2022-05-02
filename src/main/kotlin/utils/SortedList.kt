package utils

class SortedList<T : Comparable<T>> {

    val valueList = ArrayList<T>()

    fun insert(value: T) {
        val index = valueList.binarySearch(value)
        if (index < 0) {
            // 无当前值，按index返回值插入
            valueList.add(-index - 1, value)
        } else {
            valueList.add(index, value)
        }
    }

    fun largerThanAndEqual(value: T): Int {
        if (valueList.isEmpty()) return 0
        if (value > valueList.last()) return 0
        if (value < valueList.first()) return valueList.size
        // 获取最小的 大于value的index
        val minIndex = biMin(0L, valueList.lastIndex.toLong()) {
            valueList[it.toInt()] >= value
        }.toInt()
        return valueList.size - minIndex
    }

    fun smallerThanAndEqual(value: T): Int {
        if (valueList.isEmpty()) return 0
        if (value > valueList.last()) return valueList.size
        if (value < valueList.first()) return 0
        // 获取最小的 大于value的index
        val minIndex = biMax(0L, valueList.lastIndex.toLong()) {
            valueList[it.toInt()] <= value
        }.toInt()
        return minIndex + 1
    }

    fun print() {
        println(valueList.joinToString())
    }
}

fun <T : Comparable<T>> List<T>.toSortedList(): SortedList<T> {
    val st = SortedList<T>()
    this.forEach {
        st.insert(it)
    }
    return st
}