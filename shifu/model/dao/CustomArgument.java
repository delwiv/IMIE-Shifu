/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao;

/**
 *
 * @author delwiv
 */
public class CustomArgument<T> {

    private String fieldName;
    private String tableName;
    private T value;

    public CustomArgument( String fieldName, T value ) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public CustomArgument( String fieldName, String tableName, T value ) {
        this( fieldName, value );
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName( String fieldName ) {
        this.fieldName = fieldName;
    }

    public T getValue() {
        return value;
    }

    public void setValue( T value ) {
        this.value = value;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName( String tableName ) {
        this.tableName = tableName;
    }

}
