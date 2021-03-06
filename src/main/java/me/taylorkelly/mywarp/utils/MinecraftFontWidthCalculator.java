package me.taylorkelly.mywarp.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.bukkit.ChatColor;

/**
 * This class contains several static methods that provide chat formatting based
 * on the individual char width in the Minecraft chat.
 * 
 */
public class MinecraftFontWidthCalculator {

    /**
     * The maximum width that can be used in Minecraft's chat
     */
    private final static int CHAT_WIDTH = 318; // 325

    /**
     * Stores all characters supported in Minecraft's chat
     */
    private final static String charWidthIndexIndex = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~⌂ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»";

    /**
     * Stores the character widths in pixels relative to the characters
     */
    private final static int[] charWidths = { 4, 2, 5, 6, 6, 6, 6, 3, 5, 5, 5, 6, 2, 6, 2, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 2, 2, 5, 6, 5, 6, 7, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 4, 6, 4, 6, 6, 3, 6, 6, 6, 6, 6, 5, 6, 6, 2, 6, 5, 3, 6, 6, 6, 6, 6, 6, 6, 4,
            6, 6, 6, 6, 6, 6, 5, 2, 5, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 3, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 6, 3, 6, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6, 2, 6, 6, 8, 9, 9, 6, 6,
            6, 8, 8, 6, 8, 8, 8, 8, 8, 6, 6, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
            9, 9, 9, 9, 6, 9, 9, 9, 5, 9, 9, 8, 7, 7, 8, 7, 8, 8, 8, 7, 8, 8, 7, 9, 9, 6, 7, 7, 7, 7, 7, 9,
            6, 7, 8, 7, 6, 6, 9, 7, 6, 7, 1 };

    /**
     * Returns the width of the given string in pixels. Color codes are ignored.
     * 
     * @param str
     *            the string to check
     * @return the width of the string in pixels
     */
    public static int getWidth(String str) {
        int i = 0;
        if (str != null) {
            // remove chat colors
            str = ChatColor.stripColor(str);
            for (int j = 0; j < str.length(); j++) {
                i += getWidth(str.charAt(j));
            }
        }
        return i;
    }

    /**
     * Returns the width of the given character. Will return 0 if the character
     * is not allowed in Minecraft's chat.
     * 
     * @param c
     *            the character to check
     * @return the width of the character
     */
    public static int getWidth(char c) {
        int k = charWidthIndexIndex.indexOf(c);
        if (c != '\247' && k >= 0)
            return charWidths[k];
        return 0;
    }

    /**
     * Calls {@link #paddingRight(String, char)} using a space as padding
     * character
     * 
     * @param str
     *            the string
     * @return a space-padded string the same length as the chat
     */
    public static String paddingRight(String str) {
        return paddingRight(str, ' ');
    }

    /**
     * Calls {@link #paddingRight(String, char, int)} using the chat width as
     * length
     * 
     * @param str
     *            the string
     * @param pad
     *            the padding char
     * @return a padded string the same length as the chat
     */
    public static String paddingRight(String str, char pad) {
        return paddingRight(str, pad, CHAT_WIDTH);
    }

    /**
     * This method fills the given string with the given character on the right
     * until the string has the given length (in pixels, not characters!)
     * 
     * @param str
     *            the string
     * @param pad
     *            the padding char
     * @param finalLength
     *            the length of the final string in pixels
     * @return a padded string of the given length
     */
    public static String paddingRight(String str, char pad, int finalLength) {
        finalLength -= getWidth(str);
        return str + StringUtils.repeat(Character.toString(pad), finalLength / getWidth(pad));
    }

    /**
     * Calls {@link #paddingLeft(String, char)} using a space as padding char
     * 
     * @param str
     *            the string
     * @return a space padded string the same length as the chat
     */
    public static String paddingLeft(String str) {
        return paddingLeft(str, ' ');
    }

    /**
     * Calls {@link #paddingLeft(String, char, int)} using the chat width as
     * length
     * 
     * @param str
     *            the string
     * @param pad
     *            the padding char
     * @return a padded string the same length as the chat
     */
    public static String paddingLeft(String str, char pad) {
        return paddingLeft(str, pad, CHAT_WIDTH);
    }

    /**
     * This method pads the given string with the given character on the left
     * until the string has the given length (in pixels, not characters!)
     * 
     * @param str
     *            the string
     * @param pad
     *            the padding character
     * @param finalLength
     *            the length of the final string in pixels
     * @return a padded string with the given length
     */
    public static String paddingLeft(String str, char pad, int finalLength) {
        finalLength -= finalLength - getWidth(str);
        return StringUtils.repeat(Character.toString(pad), finalLength / getWidth(pad)).concat(str);
    }

    /**
     * Calls {@link #centralize(String, char)} using a space as padding
     * character
     * 
     * @param str
     *            the string
     * @return a centralized, space padded string the same length as the chat
     */
    public static String centralize(String str) {
        return centralize(str, ' ');
    }

    /**
     * Calls {@link #centralize(String, char, int)} using the chat width as
     * length
     * 
     * @param str
     *            the string
     * @param pad
     *            the padding char
     * @return a centralized string the same length as the chat
     */
    public static String centralize(String str, char pad) {
        return centralize(str, pad, CHAT_WIDTH);
    }

    /**
     * Centralizes the given string relative to the given length using the given
     * char as padding.
     * 
     * @param str
     *            the string to centralize
     * @param pad
     *            the padding char
     * @param finalLength
     *            the width in pixels
     * @return a centralized string
     */
    public static String centralize(String str, char pad, int finalLength) {
        finalLength -= getWidth(str);
        String pading = StringUtils.repeat(Character.toString(pad), finalLength / getWidth(pad) / 2);
        return pading + str + pading;
    }

    /**
     * Calls {@link #trim(String, int)} using the chat width as length
     * 
     * @param str
     *            the string to trim
     * @return a string trimmed to the maximal chat width
     */
    public static String trim(String str) {
        return trim(str, CHAT_WIDTH);
    }

    /**
     * Trims the given String until it has the given length (in pixels, not
     * characters). To do so, the method will remove characters from the end,
     * color codes are recognized and fully removed.
     * 
     * @param str
     *            the string to trim
     * @param length
     *            the length in pixels
     * @return a trimmed string
     */
    public static String trim(String str, int length) {
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            if (getWidth(String.valueOf(chars)) <= length) {
                return String.copyValueOf(chars);
            }
            chars = ArrayUtils.remove(chars, i);

            // if we just cut off a color code we need to remove the remaining
            // part too
            if (chars[i - 1] == '§') {
                chars = ArrayUtils.remove(chars, --i);
            }
        }
        return String.copyValueOf(chars);
    }

    /**
     * Calls {@link #rightLeftAlign(String, String, char)} using a space as
     * padding char
     * 
     * @param left
     *            the string that should be aligned on the left
     * @param right
     *            the string that should be aligned on the right
     * @return a string that contains the left and right aligned strings
     */
    public static String rightLeftAlign(String left, String right) {
        return rightLeftAlign(left, right, ' ');
    }

    /**
     * Calls {@link #rightLeftAlign(String, String, char, int)} using the chat
     * width as length
     * 
     * @param left
     *            the string that should be aligned on the left
     * @param right
     *            the string that should be aligned on the right
     * @param pad
     *            the padding character
     * @return a string that contains the left and right aligned strings
     */
    public static String rightLeftAlign(String left, String right, char pad) {
        return rightLeftAlign(left, right, pad, CHAT_WIDTH);
    }

    /**
     * Returns a string with a left and right aligned string relative to the
     * given length. The 'free' space will be filled with the given character.
     * If both strings are larger than the available space, the method will trim
     * the left string until it fits.
     * 
     * @param left
     *            the string that should be aligned on the left
     * @param right
     *            the string that should be aligned on the right
     * @param pad
     *            the padding character
     * @param length
     *            the length
     * @return a string that contains the left and right aligned strings
     */
    public static String rightLeftAlign(String left, String right, char pad, int length) {
        length -= (getWidth(left) + getWidth(right));
        // the length is negative if both strings are larger than the required
        // length. If this happens we trim the left string.
        if (length < 0) {
            // TODO check if right is bigger?
            left = trim(left, getWidth(left) + length);
        }
        return left + StringUtils.repeat(Character.toString(pad), length / getWidth(pad)) + right;
    }

    /**
     * Calls {@link #toList(char, String...)} using a '-' as list character
     * 
     * @param entries
     *            the list's entries
     * @return a string with all entries
     */
    public static String toList(String... entries) {
        return toList('-', entries);
    }

    /**
     * Calls {@link #toList(char, int, String...)} using the chat width as
     * length
     * 
     * @param listChar
     *            the character that will be displayed as bullet point before
     *            each entry
     * @param entries
     *            the list's entries
     * @return a string with all entries
     */
    public static String toList(char listChar, String... entries) {
        return toList(listChar, CHAT_WIDTH, entries);
    }

    /**
     * Creates a not numbered list from the given strings. Each string
     * represents an independent entry on the list. Strings that are linger than
     * the given length will be split above several lines.
     * 
     * @param listChar
     *            the character that will be displayed as bullet point before
     *            each entry
     * @param length
     *            the maximal length of each entry in pixels
     * @param entries
     *            the list's entries
     * @return a string with all entries
     */
    public static String toList(char listChar, int length, String... entries) {
        StrBuilder fullLines = new StrBuilder();
        for (String entry : entries) {
            if (!fullLines.isEmpty()) {
                fullLines.appendNewLine();
                // reset colors from the previous entry
                fullLines.append(ChatColor.RESET);
            }

            StrBuilder line = new StrBuilder();
            String[] words = entry.split(" ");
            line.append(listChar);
            line.append(' ');

            for (String word : words) {
                // if the word itself is longer than the max. length, add chars
                // as long as possible
                if (getWidth(word) > length) {
                    for (char c : word.toCharArray()) {
                        if (getWidth(line.toString()) + getWidth(c) > length) {
                            fullLines.appendln(line.toString());
                            line.clear();
                            line.appendPadding(3, ' ');
                        }
                        line.append(c);
                    }
                } else {
                    // if the world plus the needed blank is longer than the
                    // max. length, make a new line
                    if (getWidth(line.toString()) + getWidth(word) + getWidth(' ') > length) {
                        fullLines.appendln(line.toString());
                        line.clear();
                        line.appendPadding(3, ' ');
                    }
                    if (!line.isEmpty() && line.charAt(line.length() - 1) != ' ') {
                        line.append(' ');
                    }
                    line.append(word);
                }
            }
            if (!line.isEmpty()) {
                fullLines.append(line.toString());
            }
        }
        return fullLines.toString();
    }
}