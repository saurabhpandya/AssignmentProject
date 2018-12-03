package assignment.com.assignmentproject.dataprovider;

import assignment.com.assignmentproject.model.MobileDataModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by fidato on 02/12/18.
 */

public class NetworkMobileDataProvider implements MobileDataProvider {
    @Override
    public Disposable getMobileDataUsage(Consumer<MobileDataModel> success, Consumer<Throwable> error) {
        return Observable.just(0).subscribe();
    }
}
