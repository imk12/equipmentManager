package com.igor.equipmentManager.exception;

public class AlreadyHasAssetTagException extends RuntimeException {
    public AlreadyHasAssetTagException(String assetTag) {
        super("Já existe a tag " + assetTag + " cadastrada.");
    }
}
