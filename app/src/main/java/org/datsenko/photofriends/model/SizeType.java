package org.datsenko.photofriends.model;

public enum SizeType {
    S("s"),
    W("w");

    private final String text;

    SizeType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
