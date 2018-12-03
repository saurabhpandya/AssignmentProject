package assignment.com.assignmentproject;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by fidato on 02/12/18.
 */

public class AssignmentApp extends Application {

    private static WeakReference<AssignmentApp> wApp = new WeakReference<>(null);

    public static AssignmentApp getInstance() {
        return wApp.get();
    }

    public static Context getContext() {
        AssignmentApp app = wApp.get();
        return app != null ? app.getApplicationContext() : new AssignmentApp().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        wApp.clear();
        wApp = new WeakReference<>(AssignmentApp.this);
    }
}
