/**
 * Autogenerated by Thrift Compiler (0.14.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.zhou.thrift;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.2)")
public enum IdentityStatus implements org.apache.thrift.TEnum {
  NOT_IDENTIFIED(0),
  IDENTIFIED(1);

  private final int value;

  private IdentityStatus(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static IdentityStatus findByValue(int value) { 
    switch (value) {
      case 0:
        return NOT_IDENTIFIED;
      case 1:
        return IDENTIFIED;
      default:
        return null;
    }
  }
}
