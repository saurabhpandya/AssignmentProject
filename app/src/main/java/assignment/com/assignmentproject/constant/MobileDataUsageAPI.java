package assignment.com.assignmentproject.constant;

/**
 * Created by fidato on 02/12/18.
 */

public class MobileDataUsageAPI {


    private static String BASE_URL = "https://data.gov.sg/";
    private static String ENDPOINT_MOBILEDATAUSAGE = "api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getMobileDataUsageUrl() {
        return BASE_URL+ENDPOINT_MOBILEDATAUSAGE;
    }

}
