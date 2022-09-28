package pw.arulomp.one.bit.counter.counter.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;
import pw.arulomp.one.bit.counter.counter.internal.utils.LongRangeGenerator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ShortTypedOneBitCounterTest {

    TypedOneBitCounter counter = new ShortTypedOneBitCounter();

    @Test
    void test_countOneBit_invalidType() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit((byte) 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit(Integer.MAX_VALUE))
        );
    }

    @Test
    void test_countOneBit_boundaryValues() {
        assertAll(
                () -> assertEquals(0, counter.countOneBit((short) 0), "0"),
                () -> assertEquals(1, counter.countOneBit((short) 1), "1"),
                () -> assertEquals(16, counter.countOneBit((short) -1), "-1"),
                () -> assertEquals(15, counter.countOneBit(Short.MAX_VALUE), "MAX_VALUE"),
                () -> assertEquals(1, counter.countOneBit(Short.MIN_VALUE), "MIN_VALUE")
        );
    }


    public static Stream<Arguments> provider_countOneBit_randomValues() {
        return LongRangeGenerator.getRandomRange(10, Short.MIN_VALUE, Short.MAX_VALUE, 0xFFFF);
    }

    @ParameterizedTest
    @MethodSource("provider_countOneBit_randomValues")
    void test_countOneBit_randomValues(int expected, long value, String ignoredLabel) {
        short reducedValue = (short) value;
        assertEquals(expected, counter.countOneBit(reducedValue));
    }
}