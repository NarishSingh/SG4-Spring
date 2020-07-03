/*
Practice:
regex

[a-zA-Z0-9\\,\\.\\s]*
means it will accept
a-z, A-Z
0-9
,
.
" " - single white spaces (multi white space is \\s+)

[]* means it will accept these matches any number of times
 */
package com.sg.scratchpad;

import java.util.Scanner;

public class RegexApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String charDigitOnly;

        do {
            System.out.print("Enter a string [A-Z, 0-9, commas, and periods] only: ");
            charDigitOnly = input.nextLine();
        } while (!charDigitOnly.matches("[a-zA-Z0-9\\,\\.\\s]*"));

        System.out.println(charDigitOnly + " - String accepted");
    }

}
