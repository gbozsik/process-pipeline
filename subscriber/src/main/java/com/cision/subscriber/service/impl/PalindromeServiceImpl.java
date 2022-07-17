package com.cision.subscriber.service.impl;

import com.cision.subscriber.service.PalindromeService;
import org.springframework.stereotype.Service;

@Service
public class PalindromeServiceImpl implements PalindromeService {

    public int getLongestPalindromeSize(String content) {
        int longestPalindrome = 0;
        for (int i = 0; i < content.length(); i++) {
            for (int j = i + 1; j <= content.length(); j++) {
                var partialString = content.substring(i, j);
                if (isPpalindrome(partialString)) {
                    var partialStringLength = partialString.length();
                    if (longestPalindrome < partialString.length()) {
                        longestPalindrome = partialStringLength;
                    }
                }
            }
        }
        return longestPalindrome;
    }

    private static boolean isPpalindrome(String partialString) {
        StringBuilder input1 = new StringBuilder();
        input1.append(partialString);
        input1.reverse();
        return partialString.equalsIgnoreCase(input1.toString());
    }
}
