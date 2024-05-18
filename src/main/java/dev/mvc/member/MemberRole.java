package dev.mvc.member;

public enum MemberRole {
  User("USER"),
  ADMIN("ADMIN"),
  BUSINESS("BUSINESS"),
  DELETE("DELETE");
  
  private final String role;
  
  MemberRole(String role){
    this.role = role;
  }
  
  public String value() {
    return role;
  }
}
