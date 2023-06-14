package webscrapin;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AuthorizationBullshit {
  private static final String clientId = "e69bb26dc62448c0ac84d989caf45bf5";
  private static final String clientSecret = "49093b91fd5a4f2db14e2cdccbce215a";
  private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8888/callback");
  private static final String code = "AQBsTg8eGT8lvQzJMwZK5-mCJNOxmhWkZr-aZEOiO86Ju04mB2kmDNz2a94HMIP3lidlnPNxQXFZ5XTeEGL9keehNGilQnsl_JQtuMntFh8JNCcUq-hu-2D3LQF306VUCo3_BThkWIyQTXSrjMzLO1ecLLmTExF2OBlR4LPN4bXF8y5x98oTECVwvm-l_vVX-5KExBw45xqxpWlZ8jBXXYZ7n2owbJEcQ4LEU3aZnapLmVo";
  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();
  private static final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
    .build();

  public static void authorizationCode_Sync() {
    try {
      final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

      // Set access and refresh token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
      spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
      
      System.out.println("AccessToken: " + authorizationCodeCredentials.getAccessToken());
      System.out.println("RefreshToken: " + authorizationCodeCredentials.getRefreshToken());

      System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error l1: " + e.getMessage());
    }
  }

  public AuthorizationBullshit() {
    authorizationCode_Sync();
    //authorizationCode_Async();
  }
}