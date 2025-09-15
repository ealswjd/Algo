// https://www.acmicpc.net/problem/25325

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt() // 학생 수
    val studentMap = HashMap<String, Int>() // 학생 이름, 인기도
    val students = readLine().split(" ").map { it ->
        studentMap[it] = 0
    }

    repeat(n) {
        val likeNames = readLine().split(" ").map { it ->
            studentMap[it] = studentMap.get(it)!! + 1
        }
    }

    /**
     * 인기도가 높은 학생부터 낮은 학생 순으로 출력하고,
     * 인기도가 같은 경우 학생 이름 기준으로 오름차순으로 출력한다.
     */
    val ans = StringBuilder()
    val sorted = studentMap.toList()
        .sortedWith(
            compareByDescending<Pair<String, Int>> { it.second }
            .thenBy {it.first}
        )

    for ((name, cnt) in sorted) {
        ans.append("$name $cnt\n")
    }

    close()
    print(ans)
}