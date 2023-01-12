package com.aleksa.langunotebook.exception;

public enum  ValidationMessage {

	VALUE_ALREADY_EXISTS("Entered value already exists in the database."),
	VALUE_REQUIRED("Required value is missing.");

    private final String description;

	  private ValidationMessage(String description) {
	    this.description = description;
	  }

	  public String getDescription() {
	     return description;
	  }

	  @Override
	  public String toString() {
	    return  description;
	  }

}
