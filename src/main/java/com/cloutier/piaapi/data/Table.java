package com.cloutier.piaapi.data;

import java.util.List;

public class Table {

    private String file;
    private String table;
    private List<String> columns;

    public Table(String file, String table, List<String> columns) {
        this.file = file;
        this.table = table;
        this.columns = columns;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" +
                "file='" + file + '\'' +
                ", table='" + table + '\'' +
                ", columns=" + columns +
                '}';
    }

}
