package com.fps.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Pong", description = "Pong response for /ping request")
public class Pong {
  @ApiModelProperty(value = "Source IP of ping request")
  private String sourceIp;

  @ApiModelProperty(value = "Timestamp of the ping request")
  private String timestamp;

  @ApiModelProperty(value = "Response text")
  private String msg;

  public String getSourceIp() {
    return this.sourceIp;
  }

  public void setSourceIp(String sourceIp) {
    this.sourceIp = sourceIp;
  }

  public String getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getMsg() {
    return this.msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Pong(String sourceIp, String timestamp, String msg) {
    super();
    this.sourceIp = sourceIp;
    this.timestamp = timestamp;
    this.msg = msg;
  }

  public Pong() {
    // TODO Auto-generated constructor stub
  }
}
