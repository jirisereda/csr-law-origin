package cz.cas.ilaw.csrlaworigin.topicsscreen;

import com.google.gson.annotations.SerializedName;

/**
 * @author Jiri Sereda
 */

public class Ticket {

  @SerializedName("title")
  private String name;

  public Ticket(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
