package pw.arulomp.one.bit.counter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "One bit counter",
				description = "Provides api for calculating number of one-bits in the two's complement binary representation of number",
				version = "1.0"
		)
)
@SpringBootApplication
public class OneBitCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneBitCounterApplication.class, args);
	}

}
