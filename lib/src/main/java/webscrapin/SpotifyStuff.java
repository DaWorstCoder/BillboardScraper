package webscrapin;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import org.apache.hc.core5.http.ParseException;

import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class SpotifyStuff {
  public static final String accessToken = "BQBxNdWGAJV56Kh1ATcSdAeER9ZX0LRGAXhajAER1TvWQgRt-nvjIQvXtZPBpdDK94tMXL0bNEHrkwlnlj0yfowsfh-BoLSkmQsM9KsrIVipGurZGkQWIWQZ9WKE4rimw3qDSlpLjoPTqR4DjkSiw1lT3t3h36UlNIaw95LJUtgadw8GLDabwULWoC9uSPdHJKL9AMi-HEPHwrWgzVfQa0yqVlDLR970BAsz9b0TiFRB5xNo7wA8sXznDz4217CSCRMj";
  private static final String userId = "314ghqyld23gotp2nw537bykdoqi";
  private static final String name = "Billboard Test";
  public static String playlistId;
  public static String[] songListString = BillboardScraper.getSongList();
  public static String[] songListUri = new String[songListString.length];
  public static JsonArray jsonArray = new JsonArray();
  
  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  
  private static final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(userId, name)
    .build();

  public static void createPlaylist() {
    try {	
      final Playlist playlist = createPlaylistRequest.execute();
      playlistId = playlist.getId();

      System.out.println("Created " + playlist.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
  
	public static void searchTracks() {
		try {
		  for (int i = 0; i < songListString.length; i++) {
			  SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songListString[i])
					  .limit(1)
					  .build();
			  final Paging<Track> trackPaging = searchTracksRequest.execute();
			  Track[] searchResults = trackPaging.getItems();
			  songListUri[i] = searchResults[0].getUri();
			  
			  jsonArray.add(searchResults[0].getUri());
			  System.out.println("Searched and found: " + i + "\t" + searchResults[0].getName());
		  }
		  
	    System.out.println("Searched and found tracks.");
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	    System.out.println("Error: " + e.getMessage());
	  }
	}
	
	

  public SpotifyStuff() {
    createPlaylist();
    searchTracks();
    new AddSongsToPlaylist(playlistId);
  }

}