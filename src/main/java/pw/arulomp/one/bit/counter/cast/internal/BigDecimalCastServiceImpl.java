package pw.arulomp.one.bit.counter.cast.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pw.arulomp.one.bit.counter.cast.BigDecimalCastService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
public class BigDecimalCastServiceImpl implements BigDecimalCastService {

    List<Function<BigDecimal, Number>> castFlow = List.of(
            BigDecimal::byteValueExact,
            BigDecimal::shortValueExact,
            BigDecimal::intValueExact,
            BigDecimal::longValueExact,
            BigDecimalCastServiceImpl::floatValueExact,
            BigDecimalCastServiceImpl::doubleValueExact,
            BigDecimalCastServiceImpl::toBigIntegerExact
    );

    private static BigInteger toBigIntegerExact(BigDecimal decimal) {
        BigInteger result = decimal.toBigIntegerExact();
        if (decimal.compareTo(new BigDecimal(result)) == 0) {
            return result;
        }
        throw new ArithmeticException(String.format("%s: Cannot be represented as BigInteger", decimal));
    }

    private static float floatValueExact(BigDecimal decimal) {
        float result = decimal.floatValue();
        if (!(Float.isNaN(result) || Float.isInfinite(result))) {
            if (new BigDecimal(String.valueOf(result)).compareTo(decimal) == 0) {
                return result;
            }
        }
        throw new ArithmeticException(String.format("%s: Cannot be represented as float", decimal));
    }

    private static double doubleValueExact(BigDecimal decimal) {
        double result = decimal.doubleValue();
        if (!(Double.isNaN(result) || Double.isInfinite(result))) {
            if (new BigDecimal(String.valueOf(result)).compareTo(decimal) == 0) {
                return result;
            }
        }
        throw new ArithmeticException(String.format("%s: Cannot be represented as double", decimal));
    }

    @Override
    public Number castToNarrowingNumber(BigDecimal value) {
        for (Function<BigDecimal, Number> cast : castFlow) {
            try {
                Number result = cast.apply(value);
                log.debug("value {} cast to {}", value, result.getClass().getTypeName());
                return result;
            } catch (ArithmeticException ignored) {

            }
        }
        return value;
    }
}
