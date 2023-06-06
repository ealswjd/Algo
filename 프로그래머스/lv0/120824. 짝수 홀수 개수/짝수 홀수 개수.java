class Solution {
    public int[] solution(int[] num_list) {
        int even = 0;        
        for(int num : num_list){
            if(num%2 == 0) even++;
        }

        return new int[] {even, num_list.length - even};
    }
}