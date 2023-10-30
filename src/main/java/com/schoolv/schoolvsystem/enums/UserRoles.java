package com.schoolv.schoolvsystem.enums;

public enum UserRoles {
    USER("USUARIO"),
    PROFESSOR("PROFESSOR"),
    ADMIN("ADMIN"),
    STAFF("STAFF");

    private String codigoRole;

    UserRoles(String codigoRole) {
        this.codigoRole = codigoRole;
    }

    public String getCodigoRole() {
        return codigoRole;
    }

    public void setCodigoRole(String codigoRole) {
        this.codigoRole = codigoRole;
    }
}
