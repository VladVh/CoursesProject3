//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.10 at 10:05:14 PM EET 
//


package com.features;

public enum Material {

    STEEL("steel"),
    CAST_IRON("cast iron"),
    METAL("metal");
    private final String value;

    Material(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Material fromValue(String v) {
        for (Material c : Material.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
