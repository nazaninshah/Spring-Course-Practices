package ir.freeland.spring.validator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


public class Input {

  @Min(1)
  @Max(10)
  private int numberBetweenOneAndTen;

  // Note that this is actually not a valid IP address pattern, since
  // it allows values greater than 255 per octet.
  @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
  private String ipAddress;
  
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email format is invalid")
  private String emailAddress;
  
  @NotEmpty(message = "This field cannot be empty")
  private String nonEmptyField;


  public int getNumberBetweenOneAndTen() {
    return numberBetweenOneAndTen;
  }

  public void setNumberBetweenOneAndTen(int numberBetweenOneAndTen) {
    this.numberBetweenOneAndTen = numberBetweenOneAndTen;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
  
  public String getEmailAddress() {
	return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
  }

  public String getNonEmptyField() {
	return nonEmptyField;
  }

  public void setNonEmptyField(String nonEmptyField) {
	this.nonEmptyField = nonEmptyField;
  } 
}
