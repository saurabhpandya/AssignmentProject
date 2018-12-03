package assignment.com.assignmentproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fidato on 02/12/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecordsModel {
    @JsonProperty("volume_of_mobile_data")
    private String volumeOfMobileData;

    @JsonProperty("volume_of_mobile_data")
    private String quarter;

    @JsonProperty("_id")
    private int id;

    public String getVolumeOfMobileData() {
        return volumeOfMobileData;
    }

    public void setVolumeOfMobileData(String volumeOfMobileData) {
        this.volumeOfMobileData = volumeOfMobileData;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
