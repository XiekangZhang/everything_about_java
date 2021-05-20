package de.javaGeneral.Reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java.util.regex: Pattern, Matcher & PatternSyntaxException
 * - Pattern: compile method
 * - Matcher: pattern.matcher()
 * - PatternSyntaxException
 */
public class RegExample {
    public static int runTest(String regex, String text, int flags) {
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    public static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    public static void matchGroup(String regex, String text, int flags) {
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    // info: . --> match any character
    // info: OR Class --> [abc] any of the elements in the set
    // info: NOR Class --> [^abc]
    // info: Range Class --> -
    // info: Union Class --> [[]]
    // info: intersection class --> &&
    // info: Subtraction Class --> && combine with ^
    // info: \\ instead of using \
    // info: ? --> {0,1}
    // info: * --> {0,}
    // info: + --> {1,}
    // info: group --> ()
    // info: group repeat --> ()\\1
    // info: Boundary Matchers: ^string --> this string is located at the beginning of the text
    //                          string$ --> this string is at the end of the text
    //                          \\bstring\\b --> find the pattern in the text

    // info: flags: Pattern.DOTALL --> include new line
    //              Pattern.LITERAL --> to interpret the special symbols as a searching pattern
    //              Pattern.MULTILINE --> used for $ and ^, to take each line of string into account instead of the whole string (?m)
    public static void main(String[] args) {
        /*System.out.println(RegExample.runTest("foo.", "foofoo"));
        System.out.println(RegExample.runTest("[abc]", "cab"));
        System.out.println(RegExample.runTest("[bcr]at", "bat cat rat"));
        System.out.println(RegExample.runTest("[^abc]", "cg"));
        System.out.println(RegExample.runTest("[A-Z]", "Two Uppercase alphabets 34 overall"));
        System.out.println(RegExample.runTest("[a-z]", "Two Uppercase alphabets 34 overall"));
        System.out.println(RegExample.runTest("[A-Za-z]", "Two Uppercase alphabets 34 overall"));
        System.out.println(RegExample.runTest("[1-5]", "Two Uppercase alphabets 34 overall"));
        System.out.println(RegExample.runTest("[30-35]", "Two Uppercase alphabets 34 overall"));
        System.out.println(RegExample.runTest("[A-Z]", "Two Uppercase alphabets 34 overall"));
        System.out.println(RegExample.runTest("[1-3[7-9]]", "123456789"));
        System.out.println(RegExample.runTest("[1-37-9]", "123456789"));
        System.out.println(RegExample.runTest("[1-6&&[3-9]]", "123456789"));
        System.out.println(RegExample.runTest("[0-9&&[^2468]]", "123456789"));*/

        /*System.out.println(RegExample.runTest("\\d", "123")); //\\d means digits = [0-9]
        System.out.println(RegExample.runTest("\\s", "a c")); //\\s means whitespace
        System.out.println(RegExample.runTest("\\S", "a c")); //\\S means non-white space
        System.out.println(RegExample.runTest("\\w", "hi!")); //\\w means [a-zA-Z_0-9]
        System.out.println(RegExample.runTest("\\W", "hi!")); //\\W means non word*/

        /*System.out.println(RegExample.runTest("\\a?", "hi!"));
        System.out.println(RegExample.runTest("\\a{0,1}", "hi!")); // # of characters + one zero length match
        System.out.println(RegExample.runTest("\\a*", "hi!"));
        System.out.println(RegExample.runTest("\\a{0,}", "hi!"));
        System.out.println(RegExample.runTest("\\a+", "hi!"));
        System.out.println(RegExample.runTest("\\a{1,}", "hi!"));*/

        /*System.out.println(RegExample.runTest("a{3}", "aaaaaa"));
        System.out.println(RegExample.runTest("a{3}", "aa"));
        System.out.println(RegExample.runTest("a{2,3}", "aaaa")); // greedy matching: from large to small
        System.out.println(RegExample.runTest("a{2,3}?", "aaaa")); // avoid greedy matching: from small to large*/

        /*System.out.println(RegExample.runTest("(\\d\\d)", "1212"));
        System.out.println(RegExample.runTest("(\\d\\d)\\1", "1212")); //(dd)1 = (dd)(dd)
        System.out.println(RegExample.runTest("(\\d\\d)\\1\\1\\1", "12121212"));
        System.out.println(RegExample.runTest("(\\d\\d)\\1", "1213")); //wrong
        System.out.println(RegExample.runTest("(\\d\\d)(\\d\\d)", "1213"));*/

        /*System.out.println(RegExample.runTest("^dog", "dogs are friendly"));
        System.out.println(RegExample.runTest("^dog", "Are dogs friendly?"));*/

        /*System.out.println(RegExample.runTest("dog$", "Man's best friend is a dog"));
        System.out.println(RegExample.runTest("dog$", "Is a dog man's best friend?"));*/

        /*System.out.println(RegExample.runTest("\\bdog\\b", "dog is man's best friend"));
        System.out.println(RegExample.runTest("\\bdog\\b", "a dog is friendly"));*/

        /*System.out.println(RegExample.runTest("\\bdog\\b", "snoop dogg is a rapper")); // b word boundary
        System.out.println(RegExample.runTest("\\bdog\\B", "snoop dogg is a rapper")); // B non word boundary*/

        /*System.out.println(RegExample.runTest("\u00E9", "\u0065\u0301"));
        System.out.println(RegExample.runTest("\u00E9", "\u0065\u0301", Pattern.CANON_EQ));
        System.out.println(RegExample.runTest("dog", "This is a Dog"));
        System.out.println(RegExample.runTest("(?i)dog", "This is a Dog")); //(?i) ignore case
        System.out.println(RegExample.runTest("dog", "This is a Dog", Pattern.CASE_INSENSITIVE));*/

        /*System.out.println(RegExample.runTest("dog$ #check for word dog at end of text", "This is a dog"));
        System.out.println(RegExample.runTest("(?x)dog$ #check for word dog at end of text", "This is a dog"));
        System.out.println(RegExample.runTest("dog$ #check for word dog at end of text", "This is a dog"
                , Pattern.COMMENTS));*/

        RegExample.matchGroup("dogs", "Dogs is friendly and there is a group of dogs", Pattern.CASE_INSENSITIVE);
    }
}
