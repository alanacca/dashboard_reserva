package com.Dashboard.dashboard.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Connection;

@Data
@AllArgsConstructor
public class Inicio {
    public static String defFolder;
    public static String hostdb;
    public static String namedb;
    public static String userdb;
    public static String passdb;

    public static Connection c;
}
