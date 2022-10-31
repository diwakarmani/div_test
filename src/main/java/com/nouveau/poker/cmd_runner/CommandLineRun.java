package com.nouveau.poker.cmd_runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nouveau.poker.pojo.Hand;

/*
 * Class to facilitate any command line input and entry point for our business logic
 * 
 */
@Component
public class CommandLineRun implements CommandLineRunner {

	public static final String TIE = "TIE";

	@Override
	public void run(String... args) throws Exception {

		int winsPlayer1 = 0;
		int winsPlayer2 = 0;
		int count = 0;
		BufferedReader br = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				++count;
				String input = br.readLine();
				if (input == null) {
					break;
				}
				/*
				 Regex expression which validate the two players card value and that should be total 10 in number 
				 and each card can have value from [2 to 9 or T J Q K A][S C H D] where first bracket denotes the card value
				 and second denotes the suits value.
				 Here total 9 similar pattern with space and last pattern without space.
				*/
				if (!input.matches("(?:[2-9TJQKA][SCHD] ){9}[2-9TJQKA][SCHD]")) {
					System.out.println("Wrong input format at line " + count + " and exiting");
					break;
				}

				// 0 to 5 stdin is hand 1(palayer 1) and 5 to 10 is hand 2 (player 2)
				String[] cards = input.split(" ");
				String[] handOneStr = Arrays.copyOfRange(cards, 0, 5);
				String[] handTwoStr = Arrays.copyOfRange(cards, 5, 10);

				Hand handOne = new Hand(handOneStr);
				Hand handTwo = new Hand(handTwoStr);

				// sort the hands
				handOne.sortCards();
				handTwo.sortCards();

				//evaluate the hands based on the cards value and set the handvalue for the hand
				handOne.evaluate();
				handTwo.evaluate();
				
				int res = winner(handOne, handTwo);
				if (res == 1) {
					winsPlayer1++;
				} else if (res == 2) {
					winsPlayer2++;
				} else {
					System.out.println(TIE);
				}

			}

			System.out.println("Player 1: " + winsPlayer1 + " hands");
			System.out.println("Player 2: " + winsPlayer2 + " hands");

			System.exit(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Takes Hand 1 and Hand 2 as input and gives the integer output as 1(player 1 winner),2(player 2 winner) or -1(Tie) 
	public static int winner(Hand hand1, Hand hand2) {

		if (hand1.getCombination().getValue() > hand2.getCombination().getValue()) {
			return 1;
		} else if (hand1.getCombination().getValue() < hand2.getCombination().getValue()) {
			return 2;
		} else if (hand1.getHandValue() > hand2.getHandValue()) {
			return 1;
		} else if (hand1.getHandValue() < hand2.getHandValue()) {
			return 2;
		} else {
			for (int i = 4; i >= 0; i--) {
				if (hand1.getCard(i).getValue() > hand2.getCard(i).getValue()) {
					return 1;
				} else if (hand1.getCard(i).getValue() < hand2.getCard(i).getValue()) {
					return 2;
				}
			}
			return -1;
		}

	}
}
