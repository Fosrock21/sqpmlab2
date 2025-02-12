package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BinaryAPIController {

	// --- Existing 'add' endpoints ---
	@GetMapping("/add")
	public String addString(
			@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
			@RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		return Binary.add(number1, number2).getValue();
		// e.g. http://localhost:8080/add?operand1=111&operand2=1010
	}

	@GetMapping("/add_json")
	public BinaryAPIResult addJSON(
			@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
			@RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		return new BinaryAPIResult(number1, "add", number2, Binary.add(number1, number2));
		// e.g. http://localhost:8080/add_json?operand1=111&operand2=1010
	}

	// --- Newly added endpoints for *, &, | ---

	// 1) Multiply
	@GetMapping("/mul")
	public String multiplyString(
			@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
			@RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		return Binary.multiply(number1, number2).getValue();
		// e.g. http://localhost:8080/mul?operand1=101&operand2=10
	}

	@GetMapping("/mul_json")
	public BinaryAPIResult multiplyJSON(
			@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
			@RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		return new BinaryAPIResult(number1, "mul", number2, Binary.multiply(number1, number2));
	}

	// 2) Bitwise AND
	@GetMapping("/and")
	public String andString(
			@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
			@RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		return Binary.bitwiseAnd(number1, number2).getValue();
		// e.g. http://localhost:8080/and?operand1=1011&operand2=1101
	}

	@GetMapping("/and_json")
	public BinaryAPIResult andJSON(
			@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
			@RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		return new BinaryAPIResult(number1, "and", number2, Binary.bitwiseAnd(number1, number2));
	}

	// 3) Bitwise OR
	@GetMapping("/or")
	public String orString(
			@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
			@RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		return Binary.bitwiseOr(number1, number2).getValue();
		// e.g. http://localhost:8080/or?operand1=1010&operand2=0011
	}

	@GetMapping("/or_json")
	public BinaryAPIResult orJSON(
			@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
			@RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		return new BinaryAPIResult(number1, "or", number2, Binary.bitwiseOr(number1, number2));
	}
}
