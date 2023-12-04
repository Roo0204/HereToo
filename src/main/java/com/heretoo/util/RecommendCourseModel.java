package com.heretoo.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RecommendCourseModel {
    @JsonProperty("tm")
    public String tm;
    @JsonProperty("thema")
    public String thema;
    @JsonProperty("courseId")
    public String courseId;
    @JsonProperty("courseAreaId")
    public String courseAreaId;
    @JsonProperty("courseAreaName")
    public String courseAreaName;
    @JsonProperty("spotAreaId")
    public String spotAreaId;
    @JsonProperty("spotAreaName")
    public String spotAreaName;
    @JsonProperty("courseName")
    public String courseName;
    @JsonProperty("spotName")
    public String spotName;
    @JsonProperty("th3")
    public String th3;
    @JsonProperty("wd")
    public String wd;
    @JsonProperty("ws")
    public String ws;
    @JsonProperty("sky")
    public String sky;
    @JsonProperty("rhm")
    public String rhm;
    @JsonProperty("pop")
    public String pop;
}
