package com.heretoo.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class XMLModel {
    @JacksonXmlElementWrapper(useWrapping=false)
    @JacksonXmlProperty(localName = "header")
    public Header header;

    @JacksonXmlElementWrapper(useWrapping=false)
    @JacksonXmlProperty(localName = "body")
    public Body body;

    public List<RecommendCourseModel> getRecommendCourseModelList() {
        return body.items.item;
    }
}

@Data
class Header {
    @JsonProperty("resultCode")
    public String resultCode;
    @JsonProperty("resultMsg")
    public String resultMsg;
}

@Data
class Body {
    @JsonProperty("dataType")
    public String dataType;
    @JacksonXmlElementWrapper(useWrapping=false)
    @JacksonXmlProperty(localName = "items")
    public Items items;
    @JsonProperty("numOfRows")
    public String numOfRows;
    @JsonProperty("pageNo")
    public String pageNo;
    @JsonProperty("totalCount")
    public String totalCount;
}

@Data
class Items {
    @JacksonXmlElementWrapper(useWrapping=false)
    @JacksonXmlProperty(localName = "item")
    public List<RecommendCourseModel> item;
}

