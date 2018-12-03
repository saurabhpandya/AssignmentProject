package assignment.com.assignmentproject.dataprovider;

import assignment.com.assignmentproject.model.MobileDataModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by fidato on 02/12/18.
 */

public interface MobileDataUsageServices {

    @GET
    Observable<MobileDataModel> getMobileDataUsage(@Url String url);

}
