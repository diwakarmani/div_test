package com.nouveau.poker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.nouveau.poker.cmd_runner.CommandLineRun;
import com.nouveau.poker.pojo.Hand;

class PokerApplicationTests {

	// unit test of winner() for different scenarios
	@Test
	public void testCombination() {
		System.out.println("Unit test of winner()");
		Hand handOne = new Hand("7C 9D 8C 8D 3C".split(" "));
		Hand handTwo = new Hand("2S 3D 4H 9H 8H".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();

		System.out.println(handOne.toString() + " " + handTwo.toString());
		assertEquals(1, CommandLineRun.winner(handOne, handTwo));
		System.out.println("Player 1 won with ONE PAIR. PASSED.\n");

		handOne = new Hand("5C 2D JC 7C 3D".split(" "));
		handTwo = new Hand("5H 5C 5S 5D 7C".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();

		System.out.println(handOne.toString() + " " + handTwo.toString());
		assertEquals(2, CommandLineRun.winner(handOne, handTwo));
		System.out.println("Player 2 won with FOUR OF A KIND. PASSED.\n");

		handOne = new Hand("9C 9D 8D 7C TC".split(" "));
		handTwo = new Hand("9H 9S 8C 7D 3D".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();

		System.out.println(handOne.toString() + " " + handTwo.toString());
		assertEquals(1, CommandLineRun.winner(handOne, handTwo));
		System.out.println("Player 1 won with 1 PAIR - HIGH CARD. PASSED.\n");

		handOne = new Hand("9C 9D 8D 7C 3C".split(" "));
		handTwo = new Hand("9H 9S 8C 7D 3D".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();

		System.out.println(handOne.toString() + " " + handTwo.toString());
		assertEquals(-1, CommandLineRun.winner(handOne, handTwo));
		System.out.println("TIE. PASSED.\n");

		handOne = new Hand("TC 2C TS 2S TD".split(" "));
		handTwo = new Hand("AH KH JH QH TH".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();

		System.out.println(handOne.toString() + " " + handTwo.toString());
		assertEquals(2, CommandLineRun.winner(handOne, handTwo));
		System.out.println("Player 2 won with ROYAL FLUSH. PASSED.\n");

		System.out.println("Done\n");
	}

	// unit test for sample file
	@Test
	public void testMain() {
		System.out.println("##Sample file test##");
		int winsPlayer1 = 0;
		int winsPlayer2 = 0;

		try {
			File file = new File("poker-hands.txt"); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			
			while (true) {
				String input = br.readLine();
				if (input == null) {
					break;
				}
				if (!input.matches("(?:[2-9TJQKA][SCHD] ){9}[2-9TJQKA][SCHD]")) {
					System.out.println("Wrong input format.");
					break;
				}
				String[] cards = input.split(" ");
				String[] handOneStr = Arrays.copyOfRange(cards, 0, 5);
				String[] handTwoStr = Arrays.copyOfRange(cards, 5, 10);

				Hand handOne = new Hand(handOneStr);
				Hand handTwo = new Hand(handTwoStr);
				handOne.sortCards();
				handTwo.sortCards();

				handOne.evaluate();
				handTwo.evaluate();
				int res = CommandLineRun.winner(handOne, handTwo);
				if (res == 1) {
					winsPlayer1++;
				} else if (res == 2) {
					winsPlayer2++;
				} else {
					System.out.println(CommandLineRun.TIE);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(winsPlayer1, 263);
		System.out.println("Player 1: 263 wins. PASSED.\n");
		assertEquals(winsPlayer2, 237);
		System.out.println("Player 2: 237 wins. PASSED.\n");
		System.out.println("Done\n");
	}

}
