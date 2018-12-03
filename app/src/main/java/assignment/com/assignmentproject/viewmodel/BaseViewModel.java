package assignment.com.assignmentproject.viewmodel;

import android.databinding.BaseObservable;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseViewModel extends BaseObservable {

    public PublishSubject<Boolean> dialogVisibility = PublishSubject.create();

    public abstract void subscribe();

    public abstract void clear();

    public abstract void dispose();

    public Observable<Boolean> getDialogVisibility() {
        return dialogVisibility.hide();
    }

}
