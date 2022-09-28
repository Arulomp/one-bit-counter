package pw.arulomp.one.bit.counter.counter.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;
import pw.arulomp.one.bit.counter.counter.internal.utils.LongRangeGenerator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerTypedOneBitCounterTest {
    TypedOneBitCounter counter = new IntegerTypedOneBitCounter();

    @Test
    void test_countOneBit_invalidType() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit((byte) 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit(Long.MAX_VALUE))
        );
    }

    @Test
    void test_countOneBit_boundaryValues() {
        assertAll(
                () -> assertEquals(0, counter.countOneBit(0), "0"),
                () -> assertEquals(1, counter.countOneBit(1), "1"),
                () -> assertEquals(32, counter.countOneBit(-1), "-1"),
                () -> assertEquals(31, counter.countOneBit(Integer.MAX_VALUE), "MAX_VALUE"),
                () -> assertEquals(1, counter.countOneBit(Integer.MIN_VALUE), "MIN_VALUE")
        );
    }

    public static Stream<Arguments> provider_countOneBit_randomValues() {
        return LongRangeGenerator.getRandomRange(10, Integer.MIN_VALUE, Integer.MAX_VALUE, 0xFFFF_FFFFL);
    }

    @ParameterizedTest
    @MethodSource("provider_countOneBit_randomValues")
    void test_countOneBit_randomValues(int expected, long value, String ignoredLabel) {
        int reducedValue = (int) value;
        assertEquals(expected, counter.countOneBit(reducedValue));
    }
}