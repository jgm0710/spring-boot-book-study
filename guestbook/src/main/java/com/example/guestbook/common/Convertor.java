package com.example.guestbook.common;

@FunctionalInterface
public interface Convertor<EN, DTO> {
    public DTO convert(EN entity);
}
