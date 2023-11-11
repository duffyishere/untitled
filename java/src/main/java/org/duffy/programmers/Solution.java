package org.duffy.programmers;

import java.util.*;

public class Solution {

    public int 포켄몬(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) set.add(num);
        int k = nums.length / 2;

        return Math.min(set.size(), k);
    }

    public String 완주하지_못한_선수(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        int n = participant.length;
        int m = completion.length;
        String answer = "";

        for (int i = 0; i < m; i++) {
            if (!participant[i].equals(completion[i])) {
                answer = participant[i];
                break;
            }
        }

        return answer.isEmpty() ? participant[n - 1] : answer;
    }

    public boolean 전화번호_목록(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            String now = phone_book[i];
            String next = phone_book[i + 1];

            if (now.length() <= next.length() && next.startsWith(now))
                return false;
        }
        return true;
    }

    public String 가장_큰_수(int[] numbers) {
        List<String> str = new ArrayList<>();
        for (int num : numbers) str.add(String.valueOf(num));

        str.sort(((o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s2.compareTo(s1);
        }));

        StringBuilder sb = new StringBuilder();
        for (String s : str)
            sb.append(s);

        if (str.get(0).charAt(0) == '0') return "0";

        return sb.toString();
    }

    public int 의상(String[][] clothes) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String[] cloth: clothes) {
            String category = cloth[1];
            frequencyMap.put(category, frequencyMap.getOrDefault(category, 0) + 1);
        }

        int result = 0;
        for (String key: frequencyMap.keySet()) {
            Integer frequency = frequencyMap.get(key);
            if (result == 0) result += frequency + 1;
            else result *= frequency + 1;
        }
        return result - 1;
    }

    public int[] 베스트앨범(String[] genres, int[] plays) {
        int n = genres.length;
        Map<String, Integer> totalPlay = new HashMap<>();
        Map<String, Map<Integer, Integer>> musics = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String genre = genres[i];
            int play = plays[i];

            totalPlay.put(genre, totalPlay.getOrDefault(genre, 0) + play);

            if (musics.containsKey(genre)) {
                musics.get(genre).put(i, play);
            } else {
                Map<Integer, Integer> music = new HashMap<>();
                music.put(i, play);
                musics.put(genre, music);
            }
        }

        List<String> sortedKey = new ArrayList<>(totalPlay.keySet());
        sortedKey.sort(((o1, o2) -> totalPlay.get(o2).compareTo(totalPlay.get(o1))));
        List<Integer> ret = new ArrayList<>();

        for (String genre: sortedKey) {
            Map<Integer, Integer> music = musics.get(genre);
            List<Integer> genreKey = new ArrayList<>(music.keySet());

            genreKey.sort(((o1, o2) -> music.get(o2).compareTo(music.get(o1))));

            ret.add(genreKey.get(0));
            if (1 < genreKey.size()) ret.add(genreKey.get(1));
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] K번째수(int[] array, int[][] commands) {
        int n = commands.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++)
            ret[i] = K번째수_helper(array, commands[i]);

        return ret;
    }

    private int K번째수_helper(int[] array, int[] command) {
        int n = command[0];
        int m = command[1];
        int k = command[2];

        int[] tmp = new int[m - n + 1];
        int i = 0;
        n = n - 1;
        m = m - 1;
        while (n <= m) {
            tmp[i++] = array[n++];
        }

        Arrays.sort(tmp);
        return tmp[k - 1];
    }

    public int H_Index(int[] citations) {
        int n = citations.length;
        int ret = 0;
        Arrays.sort(citations);

        for (int i = 0; i < n; i++) {
            int h = n - i;
            if (h <= citations[i]) {
                ret = h;
                break;
            }
        }
        return ret;
    }
}