package cz.cas.ilaw.csrlaworigin.topicsscreen;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jiri Sereda
 */

public class UserResponse {

  //@SerializedName("postId")
  //private String postId;

  @SerializedName("id")
  private String id;

  //@SerializedName("name")
  //private String name;
  //
  //@SerializedName("email")
  //private String email;
  //
  //@SerializedName("body")
  //private String body;

  @SerializedName("latitude")
  private double latitude;

  @SerializedName("longitude")
  private double longitude;

  public UserResponse(String postId, String id, String name, String email, String body, double latitude, double longitude) {
    //this.postId = postId;
    this.id = id;
    //this.name = name;
    //this.email = email;
    //this.body = body;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getId() {
    return id;
  }

  //public String getName() {
  //  return name;
  //}

  //public void setName(String name) {
  //  this.name = name;
  //}

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
