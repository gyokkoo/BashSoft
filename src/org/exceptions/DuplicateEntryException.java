package org.exceptions;

public class DuplicateEntryException extends RuntimeException {

    private static final String DUPLICATE_ENTRY = "The {0} already exists in {1}.";

    public DuplicateEntryException(String entryName, String structureName) {
        super(String.format(DUPLICATE_ENTRY, entryName, structureName));
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}
