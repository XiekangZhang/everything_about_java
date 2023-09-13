package de.xiekang.sharepoint;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        BasePermissions basePermissions = new BasePermissions(66096, 136515681);
        System.out.println(Arrays.toString(basePermissions.parse()));
    }
}
