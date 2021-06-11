package editor;

public class PersianDigits {
    public static String convert(String selectedString) {
        StringBuilder result = new StringBuilder();
        char[] persianDigits = {'\u06F0', '\u06F1', '\u06F2', '\u06F3', '\u06F4',
                '\u06F5', '\u06F6', '\u06F7', '\u06F7', '\u06F8', '\u06F9'};
        for (char c : selectedString.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(persianDigits[Character.getNumericValue(c)]);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
