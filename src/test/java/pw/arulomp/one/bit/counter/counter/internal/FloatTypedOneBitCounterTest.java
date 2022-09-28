package pw.arulomp.one.bit.counter.counter.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;
import pw.arulomp.one.bit.counter.counter.internal.utils.CharCountUtis;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FloatTypedOneBitCounterTest {

    TypedOneBitCounter counter = new FloatTypedOneBitCounter();

    @Test
    void test_countOneBit_invalidType() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit((byte) 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit(Long.MAX_VALUE)),
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit(Double.MIN_VALUE))
        );
    }

    @Test
    void test_countOneBit_boundaryValues() {
        assertAll(
                () -> assertEquals(0, counter.countOneBit(0F), "0"),
                () -> assertEquals(7, counter.countOneBit(1F), "1"),
                () -> assertEquals(8, counter.countOneBit(-1F), "-1"),
                () -> assertEquals(30, counter.countOneBit(Float.MAX_VALUE), "MAX_VALUE"),
                () -> assertEquals(31, counter.countOneBit(-Float.MAX_VALUE), "-MAX_VALUE"),
                () -> assertEquals(1, counter.countOneBit(Float.MIN_VALUE), "MIN_VALUE"),
                () -> assertEquals(2, counter.countOneBit(-Float.MIN_VALUE), "-MIN_VALUE")
        );
    }

    public static Stream<Arguments> provider_countOneBit_predefinedValues() {
        return Stream.of(
                Arguments.of("123.45354", "01000010111101101110100000110110"),
                Arguments.of("-3231.3525", "11000101010010011111010110100100"),
                Arguments.of("1", "00111111100000000000000000000000"),
                Arguments.of("-0.1", "10111101110011001100110011001101")
        );
    }

    @ParameterizedTest
    @MethodSource("provider_countOneBit_predefinedValues")
    void test_countOneBit_predefinedValues(String decValue, String binaryValue) {
        float value = Float.parseFloat(decValue);
        int oneBits = CharCountUtis.countChar(binaryValue, '1');
        assertEquals(oneBits, counter.countOneBit(value));
    }


}