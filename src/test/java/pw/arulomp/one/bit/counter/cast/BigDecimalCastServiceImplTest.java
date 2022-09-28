package pw.arulomp.one.bit.counter.cast;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pw.arulomp.one.bit.counter.cast.internal.BigDecimalCastServiceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigDecimalCastServiceImplTest {

    BigDecimalCastService service = new BigDecimalCastServiceImpl();

    public static Stream<Arguments> provider_castToNarrowingNumber_boundaryValues() {
        return Stream.of(
                Arguments.of((byte) 0, "zero byte"),

                Arguments.of((byte) 1, "positive byte min"),
                Arguments.of((byte) -1, "negative byte min"),
                Arguments.of(Byte.MAX_VALUE, "positive byte max"),
                Arguments.of(Byte.MIN_VALUE, "negative byte max"),

                Arguments.of((short) (Byte.MAX_VALUE + 1), "positive short min"),
                Arguments.of((short) (Byte.MIN_VALUE - 1), "negative short min"),
                Arguments.of(Short.MAX_VALUE, "positive short max"),
                Arguments.of(Short.MIN_VALUE, "negative short max"),

                Arguments.of(((int) Short.MAX_VALUE) + 1, "positive int min"),
                Arguments.of(((int) Short.MIN_VALUE) - 1, "negative int min"),
                Arguments.of(Integer.MAX_VALUE, "positive int max"),
                Arguments.of(Integer.MIN_VALUE, "negative int max"),

                Arguments.of(((long) Integer.MAX_VALUE) + 1, "positive long min"),
                Arguments.of(((long) Integer.MIN_VALUE) - 1, "negative long min"),
                Arguments.of(Long.MAX_VALUE, "positive long max"),
                Arguments.of(Long.MIN_VALUE, "negative long max"),

                Arguments.of(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.TEN), "positive BigInteger"),
                Arguments.of(BigInteger.valueOf(Long.MIN_VALUE).subtract(BigInteger.TEN), "negative BigInteger"),

                Arguments.of(0.1F, "positive float min"),
                Arguments.of(-0.1F, "negative float min"),
                Arguments.of(Float.MAX_VALUE, "positive float max"),
                Arguments.of(Float.MIN_VALUE, "negative float max"),

                Arguments.of(((double) Float.MAX_VALUE) * 10, "positive double min"),
                Arguments.of(((double) Float.MIN_VALUE) / 10, "negative double min"),
                Arguments.of(Double.MAX_VALUE, "positive double max"),
                Arguments.of(Double.MIN_VALUE, "negative double max"),

                Arguments.of(BigDecimal.valueOf(Double.MAX_VALUE).add(BigDecimal.valueOf(1.1)), "positive BigDecimal"),
                Arguments.of(BigDecimal.valueOf(Double.MIN_VALUE).subtract(BigDecimal.valueOf(1.1)), "negative BigDecimal")
        );
    }

    @ParameterizedTest
    @MethodSource("provider_castToNarrowingNumber_boundaryValues")
    public void test_castToNarrowingNumber_boundaryValues(Number expected, String label) {
        Class<? extends Number> expectedClass = expected.getClass();
        BigDecimal testValue = new BigDecimal(expected.toString());

        Number result = service.castToNarrowingNumber(testValue);

        assertEquals(expectedClass, result.getClass(), label);
        assertEquals(expected, result, label);
    }

    @Test
    public void test_castToNarrowingNumber_floatZero() {
        BigDecimal value = new BigDecimal("0.0");

        Number result = service.castToNarrowingNumber(value);
        assertEquals(Byte.class, result.getClass());
        assertEquals((byte) 0, result);
    }
}