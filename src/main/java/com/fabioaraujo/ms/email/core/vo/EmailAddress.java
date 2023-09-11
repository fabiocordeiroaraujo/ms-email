package com.fabioaraujo.ms.email.core.vo;

import java.util.Objects;

public class EmailAddress {
    private String address;

    public EmailAddress(String address) {
        changeAddress(address);
    }

    public String getAddress() {
        return address;
    }

    public void changeAddress(String address){
        if(address == null || !address.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("Endereço de e-mail inválido!");
        }
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return address;
    }
}
