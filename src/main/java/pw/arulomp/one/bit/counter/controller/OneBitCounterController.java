package pw.arulomp.one.bit.counter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pw.arulomp.one.bit.counter.counter.OneBitCountService;
import pw.arulomp.one.bit.counter.cast.BigDecimalCastService;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/one-bit-counter")
@RequiredArgsConstructor
public class OneBitCounterController {
    private final OneBitCountService oneBitCountService;
    private final BigDecimalCastService bigDecimalCastService;

    @Operation(
            summary = "number one bits of given number",
            description = "Return the number of one-bits in the two's complement binary representation of number with automatic detection number type"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid number", content = @Content),
            @ApiResponse(responseCode = "500", description = "unsupported number", content = @Content)
    })
    @Parameter(
            name = "number",
            description = "number for binary representation"
    )
    @GetMapping(value = "/{number}")
    public int countOneBit(@PathVariable BigDecimal number) {
        Number narrowNumber = bigDecimalCastService.castToNarrowingNumber(number);
        return oneBitCountService.countOneBit(narrowNumber);
    }

}
