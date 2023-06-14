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

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AddSongsToPlaylist {
	private static String playlistUri;
  private static final String accessToken = SpotifyStuff.accessToken;

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
	
	private static final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
		.addItemsToPlaylist(playlistUri, SpotifyStuff.jsonArray)
		.build();

	public static void addItemsToPlaylist() {
		try {
		  final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();
		
		  System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
		} catch (IOException | SpotifyWebApiException | ParseException e) {
		  System.out.println("Error: " + e.getMessage());
		}
	}

  public AddSongsToPlaylist(String playlistUri) {
	  this.playlistUri = playlistUri;
    addItemsToPlaylist();
  }
}