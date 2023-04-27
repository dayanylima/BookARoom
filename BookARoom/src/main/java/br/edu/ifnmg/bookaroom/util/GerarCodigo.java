/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.bookaroom.util;

import java.math.BigInteger;
import java.util.UUID;

public class GerarCodigo {

    public static String generateUniqueCode() {
        String generateUUIDNo = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        return generateUUIDNo.substring(generateUUIDNo.length() - 10);
    }
}
