package pw.arulomp.one.bit.counter.counter.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;
import pw.arulomp.one.bit.counter.counter.internal.utils.LongRangeGenerator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LongTypedOneBitCounterTest {
    TypedOneBitCounter counter = new LongTypedOneBitCounter();

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
                () -> assertEquals(0, counter.countOneBit(0L), "0"),
                () -> assertEquals(1, counter.countOneBit(1L), "1"),
                () -> assertEquals(64, counter.countOneBit(-1L), "-1"),
                () -> assertEquals(63, counter.countOneBit(Long.MAX_VALUE), "MAX_VALUE"),
                () -> assertEquals(1, counter.countOneBit(Long.MIN_VALUE), "MIN_VALUE")
        );
    }

    public static Stream<Arguments> provider_countOneBit_randomValues() {
        return LongRangeGenerator.getRandomRange(10, Long.MIN_VALUE, Long.MAX_VALUE, 0xFFFF_FFFF_FFFF_FFFFL);
    }

    @ParameterizedTest
    @MethodSource("provider_countOneBit_randomValues")
    void test_countOneBit_randomValues(int expected, long value, String ignoredLabel) {
        assertEquals(expected, counter.countOneBit(value));
    }
}