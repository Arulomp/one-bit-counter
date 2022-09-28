package pw.arulomp.one.bit.counter.counter.internal.utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Random;
import java.util.stream.Stream;

public class LongRangeGenerator {

    public static Stream<Arguments> getRandomRange(int size, long minValue, long maxValue, long mask) {
        return new Random().longs(size, minValue, maxValue).mapToObj(
                value -> getArgument(value, mask)
        );
    }

    private static Arguments getArgument(long value, long mask) {
        String binaryRecord = Long.toBinaryString(value & mask);

        int expected = 0;
        for (int i = 0; i < binaryRecord.length(); i++) {
            char c = binaryRecord.charAt(i);
            if (c == '1') {
                expected++;
            }
        }
        return Arguments.of(expected, value, binaryRecord);
    }


}
