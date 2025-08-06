import java.util.Stack
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt() // 명령의 수
    val ans = StringBuilder()
    val stack = Stack<Int>()

    for (i in 1..n) {
        val line = StringTokenizer(readLine())
        val order = line.nextToken() // 명령

        // push X: 정수 X를 스택에 넣는 연산
        if (order == "push") {
            stack.push(line.nextToken().toInt())
        }
        else {
            val result = when (order) {
                "pop" -> if (stack.isEmpty()) -1 else stack.pop()
                "size" -> stack.size
                "empty" -> if (stack.isEmpty()) 1 else 0
                else -> if (stack.isEmpty()) -1 else stack.peek()
            }

            ans.append(result).append("\n")
        }

    }

    println(ans)
}