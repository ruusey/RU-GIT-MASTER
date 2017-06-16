package XMLObjects;

import java.util.Scanner;

public class ChooseYourAdventure {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out
				.println("You need to do your CSCE145 Homework, it is 11:00 at night 1 day before it is due. Will you do your homework, go to the gym, sleep or cry?");

		System.out.println(format("Enter: 'Homework','Gym', 'Sleep' or 'Cry'"));
		String choice1 = s.nextLine();

		if (choice1.equals("Homework")) {

			System.out
					.println("You complete your homework in a timely manner and receive a 100 on this asignment. You enjoy the rest of the week with all of your free time");

		}else if(choice1.equals("Gym")){
			System.out
			.println("You decide to go to the gym instead of doing your homework. How long would you like to spend in the gym (minutes)");
			System.out.println(format("Enter: A value greater than 0 and less than 180"));	
			int min = s.nextInt(); s.nextLine();
			
			if(min<=20){
				System.out.println("You wasted your time in the gym... no maximum gainz... You return to your residence hall to contemplate your lack of rock hard abs and incomplete CSCE145 HW");
				
			}else if(min>=20 && min<=80){
				System.out.println("You get a great workout which revitalizes you along with increasing your muscle mass... You run into Ms. South Carolina after exiting the gym... Do you talk to her or just walk home?");
				System.out.println(format("Enter: 'Walk' or 'Talk'"));
				
				String choice2 = s.nextLine();
				if(choice2.equals("Talk")){
					System.out.println("After you hit it off with Ms. South Carolina, she offers to help you with your CSCE145 HW, you turn the homework in and get a 100, Ms. South Carolina becomes your wife and you live happily ever after");
				}else if(choice2.equals("Walk")){
					System.out.println("Little did you know, ms. South Carolina was your future wife, however you still have tons of energy to go come and complete your CSCE145 HW which you turn in for a 100");
				}else{
					System.out.println("You realize you need to see a typing tutor so you immediatly visit https://www.typing.com/typinglessons to fix your crappy typing");
				}
			}else if(min>80 && min<=140){
				System.out.println("You have a slightly excessive workout which forces you to go back to your residence hall yo nap. Little did you know, you entered a coma during your nap and wake up during year 2100 to a world run by JJ Shepherd. You never turned in your CSCE145 HW");
			}else{
				System.out.println("You die of overexhaustion in the gym and your professor gives your CSCE145 HW a grade of 0");
			}
			
			
		}else if(choice1.equals("Sleep")){
			//which leaves you fully refreshed and ready to complete your CSCE145 HW, however after submitting your homework, you realize you slept through your ARTE101 quiz that counted for 100% of your grade. You fail ARTE101 but get a 100 on your CSCE145 HW
			System.out.println("You decide to crawl back into bed. How long would you like to sleep for (hours)? ");
			System.out.println(format("Enter: hours to sleep"));
			int sleep = s.nextInt();
			if(sleep>0 && sleep<=4){
				System.out.println("You take a nice nap and wake up refreshed to complete your CSCE145 HW in a timely matter");
			}else if(sleep>4 && sleep<12){
				System.out.println("You take a long nap and miss the rest of your classes for today, you lose attendance points");
			}else{
				System.out.println("You sleep way too long. You realize you slept through your ARTE101 quiz the next day that counted for 100% of your grade. You fail ARTE101 but get a 100 on your CSCE145 HW");
			}
		}else if(choice1.equals("Cry")){
			System.out.println("You cry so many tears of inability to complete your CSCE145 HW that you fill your residence hall, causing a power outage in all of Columbia, your professor extends the homework dealine 2 days to compensate. You comple");
		}else{
			System.out.println("You realize you need to see a typing tutor so you immediatly visit https://www.typing.com/typinglessons to fix your crappy typing");
		}
		if(true!=false && false!=true && !!!false){
			System.out.println("Thanks for playing");
		}

	}

	public static String format(String s) {
		String res = "";
		for (int x = 0; x < s.length() + 2; x++) {
			res += "*";
		}
		res += "\n*" + s + "*\n";
		for (int x = 0; x < s.length() + 2; x++) {
			res += "*";
		}

		return res;
	}
}
