package assignment.com.assignmentproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fidato on 02/12/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel {

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("fields")
    private String fields;

    @JsonProperty("records")
    private RecordsModel records;

    @JsonProperty("links")
    private String links;

    @JsonProperty("total")
    private int total;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public RecordsModel getRecords() {
        return records;
    }

    public void setRecords(RecordsModel records) {
        this.records = records;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
