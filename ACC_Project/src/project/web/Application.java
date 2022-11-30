package project.web;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.regex.Pattern;


public class Application {

	public static void main(String[] args) throws IOException {
	SpellCorrector corrector;


		
		while (true) {
			System.out.println("Please choose an option from the list below");
			System.out.println("Choose 1 : Search a url to Crawl");
			System.out.println("Choose 2 : Clear the cache");
			System.out.println("Choose 3 : Rank the web pages according to the occurance of a word");
			System.out.println("Choose 4 : Auto-Correct (Suggestion)");
			System.out.println("Choose 5 : Auto-Complete");
			
			//https://www.w3schools.com/
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please Enter your Choice:");
			int choice = scanner.nextInt();
			scanner = new Scanner(System.in);
			
			if(choice == 6)
				break;
			
			switch(choice) {
			
			case 1:
				System.out.println("Please enter the url to Crawl");
				String url = scanner.nextLine();
				//Pattern matching to validate URL 
				if (isValid(url)) {
					//Checking if the URL is already crawled and available in cache file.
					if (Cache.isUrlAvailable(url)) {
						System.out.println("This URL is already crawled.");

					} else {
						Crawler.crawlWebPage(2, url, new ArrayList<String>());

					}

				} else {
					System.out.println("Please enter a valid url");
				}

				break;
				
			case 2:
				Cache.clearCache();
				break;
				
			case 3:
				System.out.println("Please enter a word to start searching \n");
				FindWord.readAllFiles();
				break;

				
			case 4:
				System.out.println("Please enter a word");

				String sSearch = scanner.nextLine();
				corrector = new SpellCorrector();

				corrector.loadSpellCorrector();
				String suggestion = corrector.findSimilarWord(sSearch);
				if (suggestion.length() == 0)
					System.out.println("There are no similar words. Please enter the valid word to search");
				else
					System.out.println("Suggestion: " + suggestion);

				break;
				
			case 5:
				System.out.println("Please enter a word to Autocomplete");
				String sSearch1 = scanner.nextLine();
				corrector = new SpellCorrector();

				corrector.loadSpellCorrector();
				ArrayList suggestion1 = corrector.autocomplete(sSearch1);
				System.out.println(suggestion1.toString());

				break;

				
			default:
				System.out.println("Please Enter a Valid number.\n");
			
			}
		}
		
		

	}
	
	private static boolean isValid(String url) {
		if(Pattern.matches(Crawler.URL_PATTERN, url))
			return true;
		
		return false;
	}

}
