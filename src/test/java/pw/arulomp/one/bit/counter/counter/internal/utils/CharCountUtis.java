package pw.arulomp.one.bit.counter.counter.internal.utils;

public class CharCountUtis {
    public static int countChar(String string, char chr) {
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == chr) {
                counter++;
            }
        }
        return counter;
    }
}
