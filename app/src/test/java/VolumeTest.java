import android.os.Build;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.openiptv.code.htsp.BaseConnection;
import com.openiptv.code.htsp.ConnectionInfo;
import com.openiptv.code.player.TVPlayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.google.common.truth.Truth.assertThat;
import static org.awaitility.Awaitility.await;

@Config(sdk = Build.VERSION_CODES.O_MR1)
@RunWith(RobolectricTestRunner.class)
public class VolumeTest {
    private static final int FAKE_TEST = 10;
    private static final boolean EXISTS = false;
    private static final String TAG = ChannelTest.class.getSimpleName();
    private static final String DEV_ACCOUNT = "development";
    private static final String DEV_PASSWORD = "development";
    private static final String DEV_ACCOUNT_I = "development_i";
    private static final String DEV_PASSWORD_I = "development_i";
    private static final String DEV_HOST = "tv.theron.co.nz";
    private static final int DEV_PORT = 9982;
    private static final int DEV_PORT_I = 9985;
    private static final float RESULT = 6.7f;

    @Test
    public void increaseVolumeTest() {
        BaseConnection connection = new BaseConnection(new ConnectionInfo(DEV_HOST, DEV_PORT, DEV_ACCOUNT, DEV_PASSWORD,
                "test", "23"));
        connection.start();

        await().until(() -> connection.getAuthenticator().getState() != null);
        TVPlayer tvPlayer = new TVPlayer(getApplicationContext(),
                new SimpleExoPlayer.Builder(getApplicationContext()).build(), connection);
        tvPlayer.changeVolume(6.7f);
        // ...then the result should be the expected one.
        assertThat(tvPlayer.getCurrentVolume()).isEqualTo(RESULT);
    }
}
