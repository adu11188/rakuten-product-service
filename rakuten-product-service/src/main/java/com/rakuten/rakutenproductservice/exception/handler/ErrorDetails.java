package com.rakuten.rakutenproductservice.exception.handler;

import java.util.Date;
/**
 * VO for Error response to client
 * 
 * @author adarshsumma
 *
 */
public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;

  public ErrorDetails(Date timestamp, String message, String details) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }

}