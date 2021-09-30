package ch.zli.m223;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class Util {
    public static final String rootStyle = "-fx-background-color: #DDDDDD; -fx-border-color: black";
    public static final double maxWidth = 900;
    public static final double maxHeight = 600;


    public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(input.getBytes());
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString(byteDatum & 0xff, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }




}
