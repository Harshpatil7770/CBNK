package com.cli.bnk.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AccountNumberGeneration {

	public long generateRandomValue() {
		Random random = new Random();
		return random.nextLong();
	}
}
