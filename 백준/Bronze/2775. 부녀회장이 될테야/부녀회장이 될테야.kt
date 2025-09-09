// https://www.acmicpc.net/problem/2775

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt() // Test case의 수
    val max = 14 // 1 ≤ k, n ≤ 14
    val dp: Array<IntArray> = Array(max+1) {
        IntArray(max+1)
    }

    // 아파트에는 0층부터 있고 각층에는 1호부터 있으며, 0층의 i호에는 i명이 산다.
    for (i in 1..max) {
        dp[0][i] = i
    }

    /*
    “a층의 b호에 살려면 자신의 아래(a-1)층의 1호부터 b호까지
    사람들의 수의 합만큼 사람들을 데려와 살아야 한다”
     */
    for (k in 1..max) {
        for (n in 1..max) {
            dp[k][n] = dp[k-1][n] + dp[k][n-1]
        }
    }

    val ans = StringBuilder()
    repeat(t) {
        val k = readLine().toInt() // k층에
        val n = readLine().toInt() // n호에는 몇 명이 살고 있는지

        ans.append("${dp[k][n]}\n")
    }

    print(ans)
    close()
}
