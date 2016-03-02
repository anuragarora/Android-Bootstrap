package com.anurag.androidbootstrap.module;

import com.anurag.androidbootstrap.R;
import com.anurag.androidbootstrap.network.NetworkManager;
import com.anurag.androidbootstrap.network.RetrofitNetworkManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.anurag.androidbootstrap.module.ApplicationModule.resources;

/**
 * Created by anurag_arora on 11/17/2015.
 */
public class NetworkManagerModule {
    private static final int TIMEOUT = 60;
    private static final String TAG = NetworkManagerModule.class.getSimpleName();
    private static OkHttpClient sHttpClient;

    public static NetworkManager retrofitNetworkManager() {
        return new RetrofitNetworkManager(getHttpClient(),
                resources().getString(R.string.base_url));
    }

    private static OkHttpClient getHttpClient() {
        if (sHttpClient == null) {
            sHttpClient.newBuilder()
                    .retryOnConnectionFailure(true)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();

            // Setting the client to trust all certificates
            //installTrustManagerToClient(sHttpClient);

            // Adding interceptor to inject basic authorisation
            // sHttpClient.interceptors().add(basicAuthorizationRequestInterceptor());
        }

        return sHttpClient;
    }

    /*private static TrustManager[] getTrustManager() {
        // Create a trust manager that does not validate certificate chains
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };
    }

    private static void installTrustManagerToClient(OkHttpClient client) {
        // installing all trusting trust manager to SSL
        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            client.setSslSocketFactory(sslSocketFactory);
            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

        } catch (GeneralSecurityException e) {
            Logger.e(TAG, e.getMessage());
        }
    }*/

    /*private static Interceptor basicAuthorizationRequestInterceptor() {
        String encodedAuthString = FileUtils.createStringFromInputStream(resources().openRawResource(R.raw.client_credentials));
        Logger.i("credentials","string: "+encodedAuthString);
        return new BasicAuthorizationRequestInterceptor(stringEncryptor(), encodedAuthString);
    }*/
}
