package utils;


import web.constants.HtmlTags;

public class HtmlUtils {

    /*
     * Replace \n by <br>
     */
    public static String replaceBreakLineByBR(String text) {
        return text.replace("\n", HtmlTags.BR_TAG);
    }
}
