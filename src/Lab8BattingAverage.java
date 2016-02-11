import java.util.Scanner;

/**
 * caroline_jobelab8 to kamel@grandcircus.co; maurice@grandcircus.co
 * 
 * @author Caroline
 *
 *         Lab 8: Batting Average Calculator
 *
 *         Task: Calculate batting statistics.
 *
 *         Application will: -calculate batting avg and slugging percentage for
 *         one or more baseball players. -for each player, application first
 *         asks for the number of at bats. For each at bat, the application asks
 *         for a result -to enter an at-bat result, the user enters the number
 *         of bases earned by the batter. -after all of the at-bats are entered,
 *         the application displays the batting avg and slugging percentage of
 *         the batter.
 *
 *         Build specs: 1.Use an Array to store the at-bat results for each
 *         player. 2. Validate the input so that the user can only enter
 *         positive integers. For the at-bat results, the user can only enter 0,
 *         1, 2, 3, or 4. 3. Validate the user's response to the question
 *         "Another batter?" so that the user can only enter Y, y, N, or n. If
 *         the user chooses N or n, end the program. 4. Format the batting avg
 *         and slugging percentages such that three decimal places are shown.
 *
 *         Hints: -Batting avg is total # of at-bats for which the player earned
 *         at least one base divided by the total # of at-bats. -Slugging
 *         percentage is the total # of bases earned divided by the total number
 *         of at-bats.
 *
 *         Extended Challenges: -At start of program, prompt user for the # of
 *         batters to enter, then save the stats in a 2D array. The program
 *         won't have to prompt the user whether to enter data for another
 *         batter since it will know how many batters are to be entered After
 *         all batters have been entered, print a one-line summary for each
 *         batter.
 *
 *
 */
public class Lab8BattingAverage {

	static Scanner input = new Scanner(System.in);
	static String play;
	static int col = 0;

	public static void main(String[] args) {
		play = "y"; // initialize object to enter while loop
		
		baseballGreet();

		while (play.equalsIgnoreCase("y")) {
			int batter = getRangedInteger("How many batters do you have?", 1);

			int[][] batterStat = new int[batter][];

			int atBats = 0;

			//get # of at-bats for batter 
			for (int row = 0; row < batter; row++) {
				atBats = getRangedInteger("How many at-bats does batter " + (row + 1) + " have?", 1);
				batterStat[row] = new int[atBats];
				int bCount = 0;
				int sCount = 0;
				
				//get bases earned for each at-bat
				for (col = 0; col < atBats; col++) {
					batterStat[row][col] = getRangedInteger("Enter at-bat result " + (col + 1) + ": \n"	+ "0=out, 1=single, 2=double, 3=triple, 4=home run", 0, 4);
					if (batterStat[row][col] > 0) {
						bCount++;
					}
					sCount += batterStat[row][col];
				}
				
				double bCountNew = (double) bCount;
				returnBattingAverage(atBats, row, bCountNew);
				System.out.println("");

				double sCountNew = (double) sCount;
				returnSluggingPercentage(atBats, row, sCountNew);
				System.out.println("");
			}
			continueOrQuit(input);
		}
		baseballGoodbye();
	}

	private static void returnSluggingPercentage(int atBats, int row, double sCountNew) {
		double slugPercentage = sCountNew / atBats;
		System.out.printf("Batter " + (row + 1) + " slugging percentage: " + "%.3f", slugPercentage);
	}

	private static void returnBattingAverage(int atBats, int row, double bCountNew) {
		double batAvg = bCountNew / atBats;
		System.out.printf("Batter " + (row + 1) + " batting average: " + "%.3f", batAvg);
	}

	private static void baseballGreet() {
		System.out.println("Welcome to the Batting Statistics Calculator!");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("");
		System.out.println("         /");
		System.out.println("        /");
		System.out.println("      <<O                                       O");
		System.out.println("        |                                     o/|>");
		System.out.println("       /|                                       |");
		System.out.println("      / |                                       |");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("");
	}

	private static void baseballGoodbye() {
		System.out.println("Good-bye!");
		System.out.println("        ____");
		System.out.println("      .'    '.");
		System.out.println("     /'-....-'\\");
		System.out.println("     |        |");
		System.out.println("     \\.-''''-./");
		System.out.println("      '.____.'");
	}

	//prompt user to input, validate for input to be integer
	private static int getInteger(String prompt) {
		System.out.println(prompt);
		while (!input.hasNextInt()) {
			System.out.println("You have entered invalid data. " + prompt);
			input.next();
		}
		int answer = input.nextInt();
		return answer;
	}

	//prompt user for input, validate for input to be integer in a range
	private static int getRangedInteger(String prompt, int min, int max) {
		int answer = getInteger(prompt);
		while (answer > max || answer < min) {
			answer = getInteger("Please enter a number from " + min + " to " + max + ": ");
		}
		return answer;
	}
	
	//prompt user for input, validate for input to be integer in a range
	private static int getRangedInteger(String prompt, int min) {
		int answer = getInteger(prompt);
		while (answer < min) {
			answer = getInteger("Please enter a number greater than " + (min-1) + ": ");
		}
		return answer;
	}

	private static void continueOrQuit(Scanner input) {
		System.out.println("\nEnter stats for more batters? (y/n)");
		play = input.next();
		while (!play.equalsIgnoreCase("Y") && !play.equalsIgnoreCase("N")) {
			System.out.println("Invalid input. Enter \"y\" to enter more stats, or \"n\" to quit: ");
			play = input.next();
			continue;
		}
	}
}
