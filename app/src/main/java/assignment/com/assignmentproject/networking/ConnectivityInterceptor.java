package assignment.com.assignmentproject.networking;

import android.content.Context;

import java.io.IOException;
import java.net.SocketException;

import javax.net.ssl.SSLException;

import assignment.com.assignmentproject.AssignmentApp;
import assignment.com.assignmentproject.R;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {
    private Context mContext;

    public ConnectivityInterceptor() {
        mContext = AssignmentApp.getContext();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        try {
            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        } catch (Exception e) {
            throw new NoConnectivityException(e);
        }
    }

    public class NoConnectivityException extends IOException {

        private Exception exception;

        public NoConnectivityException(Exception e) {
            exception = e;
        }

        @Override
        public String getMessage() {
//            return TMWApp.getContext().getResources().getString(R.string.error_no_network);
            if (exception instanceof IOException
                    || exception instanceof SocketException
                    || exception instanceof SSLException)// time out exception
                return AssignmentApp.getContext().getResources().getString(R.string.error_no_network);
            else
                return exception.getMessage();
        }

    }
}
