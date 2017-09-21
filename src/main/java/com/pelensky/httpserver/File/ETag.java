package com.pelensky.httpserver.File;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ETag {

    public String createETagFromFileContents(byte[] dataToConvert) throws NoSuchAlgorithmException {
        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
        msdDigest.update(dataToConvert, 0, dataToConvert.length);
        return DatatypeConverter.printHexBinary(msdDigest.digest()).toLowerCase();
    }

}
