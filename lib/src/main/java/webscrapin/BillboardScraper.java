package webscrapin;

import java.io.IOException;
import java.util.*;

import org.jsoup.*; 
import org.jsoup.nodes.*; 
import org.jsoup.select.*;

public class BillboardScraper {
	private static String website = "https://www.billboard.com/charts/hot-100/";
	
	public static String[] getSongList() {
		
		try { 
			// fetching the target website 
			Document doc = Jsoup
					.connect(website)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36") 
					.header("Accept-Language", "*") 
					.get();

			ArrayList<String> songNames = new ArrayList<>();
			ArrayList<String> artistsNames = new ArrayList<>();
		

			Elements songName1 = doc.getElementsByClass("c-title  a-no-trucate a-font-primary-bold-s u-letter-spacing-0021 u-font-size-23@tablet lrv-u-font-size-16 u-line-height-125 u-line-height-normal@mobile-max a-truncate-ellipsis u-max-width-245 u-max-width-230@tablet-only u-letter-spacing-0028@tablet");
			songName1.forEach(el -> songNames.add(el.text()));
			Elements songNamesElements = doc.getElementsByClass("c-title  a-no-trucate a-font-primary-bold-s u-letter-spacing-0021 lrv-u-font-size-18@tablet lrv-u-font-size-16 u-line-height-125 u-line-height-normal@mobile-max a-truncate-ellipsis u-max-width-330 u-max-width-230@tablet-only");
			songNamesElements.forEach(el -> songNames.add(el.text()));
			
			Elements artistName1 = doc.getElementsByClass("c-label  a-no-trucate a-font-primary-s lrv-u-font-size-14@mobile-max u-line-height-normal@mobile-max u-letter-spacing-0021 lrv-u-display-block a-truncate-ellipsis-2line u-max-width-330 u-max-width-230@tablet-only u-font-size-20@tablet");
			artistName1.forEach(el -> artistsNames.add(el.text()));
			Elements artistNamesElements = doc.getElementsByClass("c-label  a-no-trucate a-font-primary-s lrv-u-font-size-14@mobile-max u-line-height-normal@mobile-max u-letter-spacing-0021 lrv-u-display-block a-truncate-ellipsis-2line u-max-width-330 u-max-width-230@tablet-only");
			artistNamesElements.forEach(el -> artistsNames.add(el.text()));
			
			String[] songList = new String[songNames.size()];
			
			for (int i = 0; i < songNames.size(); i++) {
				songList[i] = songNames.get(i) + " " + artistsNames.get(i);
				System.out.println(i + "\t" + songNames.get(i) + ", " + artistsNames.get(i));
				System.out.println("\t" + songList[i]);
			}
			
			return songList;
			

		} catch (IOException e) { 
			throw new RuntimeException(e); 
		}
	}
}
