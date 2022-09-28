package pw.arulomp.one.bit.counter.counter.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;
import pw.arulomp.one.bit.counter.counter.internal.utils.LongRangeGenerator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ByteTypedOneBitCounterTest {
    TypedOneBitCounter counter = new ByteTypedOneBitCounter();

    @Test
    void test_countOneBit_invalidType() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit((short) 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit(Integer.MAX_VALUE))
        );
    }

    @Test
    void test_countOneBit_boundaryValues() {
        assertAll(
                () -> assertEquals(0, counter.countOneBit((byte) 0), "0"),
                () -> assertEquals(1, counter.countOneBit((byte) 1), "1"),
                () -> assertEquals(8, counter.countOneBit((byte) -1), "-1"),
                () -> assertEquals(7, counter.countOneBit(Byte.MAX_VALUE), "MAX_VALUE"),
                () -> assertEquals(1, counter.countOneBit(Byte.MIN_VALUE), "MIN_VALUE")
        );
    }

    public static Stream<Arguments> provider_countOneBit_randomValues() {
        return LongRangeGenerator.getRandomRange(10, Byte.MIN_VALUE, Byte.MAX_VALUE, 0xFF);
    }

    @ParameterizedTest
    @MethodSource("provider_countOneBit_randomValues")
    void test_countOneBit_randomValues(int expected, long value, String ignoredLabel) {
        byte reducedValue = (byte) value;
        assertEquals(expected, counter.countOneBit(reducedValue));
    }
}