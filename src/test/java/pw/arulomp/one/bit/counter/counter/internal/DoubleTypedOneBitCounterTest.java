package pw.arulomp.one.bit.counter.counter.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;
import pw.arulomp.one.bit.counter.counter.internal.utils.CharCountUtis;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoubleTypedOneBitCounterTest {

    TypedOneBitCounter counter = new DoubleTypedOneBitCounter();

    @Test
    void test_countOneBit_invalidType() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit((byte) 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit(Long.MAX_VALUE)),
                () -> assertThrows(IllegalArgumentException.class, () -> counter.countOneBit(Float.MIN_VALUE))
        );
    }

    @Test
    void test_countOneBit_boundaryValues() {
        assertAll(
                () -> assertEquals(0, counter.countOneBit(0D), "0"),
                () -> assertEquals(10, counter.countOneBit(1D), "1"),
                () -> assertEquals(11, counter.countOneBit(-1D), "-1"),
                () -> assertEquals(62, counter.countOneBit(Double.MAX_VALUE), "MAX_VALUE"),
                () -> assertEquals(63, counter.countOneBit(-Double.MAX_VALUE), "-MAX_VALUE"),
                () -> assertEquals(1, counter.countOneBit(Double.MIN_VALUE), "MIN_VALUE"),
                () -> assertEquals(2, counter.countOneBit(-Double.MIN_VALUE), "-MIN_VALUE")
        );
    }

    public static Stream<Arguments> provider_countOneBit_predefinedValues() {
        return Stream.of(
                Arguments.of(
                        "123.45354",
                        "01000000 01011110 11011101 00000110 11001100 10100010 11011011 01100010"
                ),
                Arguments.of(
                        "-3231.3525",
                        "11000000 10101001 00111110 10110100 01111010 11100001 01000111 10101110"
                ),
                Arguments.of(
                        "1",
                        "00111111 11110000 00000000 00000000 00000000 00000000 00000000 00000000"
                ),
                Arguments.of(
                        "-0.1",
                        "10111111 10111001 10011001 10011001 10011001 10011001 10011001 10011010"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provider_countOneBit_predefinedValues")
    void test_countOneBit_predefinedValues(String decValue, String binaryValue) {
        double parseDouble = Double.parseDouble(decValue);
        int oneBits = CharCountUtis.countChar(binaryValue, '1');
        assertEquals(oneBits, counter.countOneBit(parseDouble));
    }
}