class Solution {
    public int solution(int n, int k) {
        int drink = 2000;
        int sheep = 12000;
        int answer = n*sheep + k*drink - n/10 * drink;
        return answer;
    }
}