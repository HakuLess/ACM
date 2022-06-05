package leetcode.contest.c296

import utils.print
import java.util.*


fun main() {
    val e = TextEditor()
    e.addText("leetcode")
    e.deleteText(4).print()
    e.addText("practice")
    e.cursorRight(3).print()
//    e.cursorLeft(8).print()
//    e.deleteText(10).print()
//    e.cursorLeft(2).print()
//    e.cursorRight(6).print()

//    e.addText("dsqdmtbhxxltl")
//    e.deleteText(13).print()
//    e.addText("iykwsfld")
//    e.deleteText(17).print()
}

class TextEditor() {

    var index = 0

    var cur = StringBuilder()

    fun addText(text: String) {
        val left = cur.substring(0, index)
        val right = cur.substring(index, cur.length)
        cur = StringBuilder(left)
        cur.append(text)
        cur.append(right)
        index += text.length
    }

    fun deleteText(k: Int): Int {
        val right = index
        val left = maxOf(0, index - k)
        cur.delete(left, right)
        if (index >= k) {
            index -= k
            return k
        }
        val ans = index
        index = 0
        return ans
    }

    fun cursorLeft(k: Int): String {
        index = maxOf(0, index - k)
        return cur.substring(maxOf(0, index - 10), index)
    }

    fun cursorRight(k: Int): String {
        index = minOf(index + k, cur.length)
        return cur.substring(maxOf(0, index - 10), index)
    }

}

/**
 * Your TextEditor object will be instantiated and called as such:
 * var obj = TextEditor()
 * obj.addText(text)
 * var param_2 = obj.deleteText(k)
 * var param_3 = obj.cursorLeft(k)
 * var param_4 = obj.cursorRight(k)
 */