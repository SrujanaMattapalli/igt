import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public final class BehaviorDelegateTest {
    interface DoWorkService {
        Call<String> response();

        Call<String> failure();
    }

    private final IOException mockFailure = new IOException("Timeout!");
    private final NetworkBehavior behavior = NetworkBehavior.create(new Random(2847));
    private DoWorkService service;

    @Before
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/s/5zz3hibrxpspoe5/playerInfo.json")
                .build();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
        final BehaviorDelegate<DoWorkService> delegate = mockRetrofit.create(DoWorkService.class);

        service = new DoWorkService() {
            @Override
            public Call<String> response() {
                Call<String> response = Calls.response("Response!");
                return delegate.returning(response).response();
            }

            @Override
            public Call<String> failure() {
                Call<String> failure = Calls.failure(mockFailure);
                return delegate.returning(failure).failure();
            }
        };
    }

    @Test
    public void syncFailureThrowsAfterDelay() {
        behavior.setDelay(100, TimeUnit.MILLISECONDS);
        behavior.setVariancePercent(0);
        behavior.setFailurePercent(100);

        Call<String> call = service.response();

        long startNanos = System.nanoTime();
        try {
            call.execute();
            fail();
        } catch (IOException e) {
            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos);
            assertEquals(e, behavior.failureException());
        }
    }
}

