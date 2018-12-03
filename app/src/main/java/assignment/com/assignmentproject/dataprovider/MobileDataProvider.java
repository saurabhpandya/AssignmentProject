package assignment.com.assignmentproject.dataprovider;

import assignment.com.assignmentproject.model.MobileDataModel;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by fidato on 02/12/18.
 */

public interface MobileDataProvider {

    Disposable getMobileDataUsage(Consumer<MobileDataModel> success, Consumer<Throwable> error);
}
