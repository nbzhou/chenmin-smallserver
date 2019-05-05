package com.zhonghuilv.codegenerator.model;


import com.zhonghuilv.codegenerator.util.Cache;
import com.zhonghuilv.codegenerator.util.DatabaseDataTypesUtils;
import com.zhonghuilv.codegenerator.util.StringHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * edit by zhengjing
 */
public class Column {
    /**
     * Reference to the containing table
     */
    private final Table _table;

    private final String _remarks;

    /**
     * The java.sql.Types type
     */
    private final int _sqlType;

    /**
     * The sql typename. provided by JDBC driver
     */
    private final String _sqlTypeName;

    /**
     * The name of the column
     */
    private final String _sqlName;

    /**
     * True if the column is a primary key
     */
    private boolean _isPk;

    /**
     * True if the column is a foreign key
     */
    private boolean _isFk;

    /**
     * @todo-javadoc Describe the column
     */
    private final int _size;

    /**
     * @todo-javadoc Describe the column
     */
    private final int _decimalDigits;

    /**
     * True if the column is nullable
     */
    private final boolean _isNullable;

    /**
     * True if the column is indexed
     */
    private final boolean _isIndexed;

    /**
     * True if the column is unique
     */
    private final boolean _isUnique;

    /**
     * Null if the DB reports no default value
     */
    private final String _defaultValue;


    private final String _apiModelProperty;
    /**
     * Get static reference to Log4J Logger
     */
    private static Log _log = LogFactory.getLog(Column.class);

    private final String _jdbcTypeName;

    protected static Map<Integer, String> typeMap;

    //copy from mybatis
    static {
        typeMap = new HashMap<Integer, String>();
        typeMap.put(Types.ARRAY, "ARRAY");
        typeMap.put(Types.BIGINT, "BIGINT");
        typeMap.put(Types.BINARY, "BINARY"); //$NON-NLS-1$
        typeMap.put(Types.BIT, "BIT");
        typeMap.put(Types.BLOB, "BLOB"); //$NON-NLS-1$
        typeMap.put(Types.BOOLEAN, "BOOLEAN");
        typeMap.put(Types.CHAR, "BOOLEAN");
        typeMap.put(Types.CLOB, "CLOB");
        typeMap.put(Types.DATALINK, "DATALINK");
        typeMap.put(Types.DATE, "DATE");
        typeMap.put(Types.DECIMAL, "DECIMAL");
        typeMap.put(Types.DISTINCT, "DISTINCT");
        typeMap.put(Types.DOUBLE, "DOUBLE");
        typeMap.put(Types.FLOAT, "FLOAT");
        typeMap.put(Types.INTEGER, "INTEGER");
        typeMap.put(Types.JAVA_OBJECT, "JAVA_OBJECT");
        typeMap.put(Types.LONGNVARCHAR, "LONGNVARCHAR");
        typeMap.put(Types.LONGVARBINARY, "LONGVARBINARY"); //$NON-NLS-1$
        typeMap.put(Types.LONGVARCHAR, "LONGVARCHAR");
        typeMap.put(Types.NCHAR, "NCHAR");
        typeMap.put(Types.NCLOB, "NCLOB");
        typeMap.put(Types.NVARCHAR, "NVARCHAR");
        typeMap.put(Types.NULL, "NULL");
        typeMap.put(Types.NUMERIC, "NUMERIC");
        typeMap.put(Types.OTHER, "OTHER");
        typeMap.put(Types.REAL, "REAL");
        typeMap.put(Types.REF, "REF");
        typeMap.put(Types.SMALLINT, "INTEGER");// SMALL int use int
        typeMap.put(Types.STRUCT, "STRUCT");
        typeMap.put(Types.TIME, "TIME");
        typeMap.put(Types.TIMESTAMP, "TIMESTAMP");
        typeMap.put(Types.TINYINT, "TINYINT");
        typeMap.put(Types.VARBINARY, "VARBINARY"); //$NON-NLS-1$
        typeMap.put(Types.VARCHAR, "VARCHAR");
    }

    public Column(Table table, int sqlType, String sqlTypeName,
                  String sqlName, int size, int decimalDigits, boolean isPk,
                  boolean isNullable, boolean isIndexed, boolean isUnique,
                  String defaultValue, String remarks, boolean isfk) {
        _table = table;
        _sqlType = sqlType;
        _jdbcTypeName = typeMap.get(sqlType);
        _sqlName = sqlName;
        _sqlTypeName = sqlTypeName;
        _size = size;
        _decimalDigits = decimalDigits;
        _isPk = isPk;
        _isNullable = isNullable;
        _isIndexed = isIndexed;
        _isUnique = isUnique;
        _defaultValue = defaultValue;
        _remarks = remarks;
        _isFk = isfk;

        StringBuilder valid = new StringBuilder();

        //Bean Validation
        if (isNullable) {
            valid.append("@NotNull(message = \"" + (StringHelper.isBlank(_remarks) ? getColumnNameLower() : _remarks)
                    + "不能为空\")");
        }

        //swagger
        StringBuffer sbApi = new StringBuffer();
        sbApi.append("@ApiModelProperty(value = \"" + remarks + "\"");
        if (!isNullable) {
            sbApi.append(", required = true");
        }
        if (Objects.nonNull(remarks) && remarks.contains("eg.")) {
            String eg = remarks.substring(remarks.indexOf("eg."));
            sbApi.append(", example = \"" + eg + "\"");
        }
        sbApi.append(")");
        _apiModelProperty = sbApi.toString();

        if(Cache.isIgnore(this._sqlName)){
            _table.setSupperClass("MainPO");
        }

    }

    public String getApiModelProperty() {
        return _apiModelProperty;
    }

    public boolean isBaseField() {
        return Cache.isIgnore(this._sqlName);
    }

    /**
     * Gets the SqlType attribute of the Column object
     *
     * @return The SqlType value
     */
    public int getSqlType() {
        return _sqlType;
    }

    /**
     * Gets the Table attribute of the DbColumn object
     *
     * @return The Table value
     */
    public Table getTable() {
        return _table;
    }

    /**
     * Gets the Size attribute of the DbColumn object
     *
     * @return The Size value
     */
    public int getSize() {
        return _size;
    }

    /**
     * Gets the DecimalDigits attribute of the DbColumn object
     *
     * @return The DecimalDigits value
     */
    public int getDecimalDigits() {
        return _decimalDigits;
    }

    /**
     * Gets the SqlTypeName attribute of the Column object
     *
     * @return The SqlTypeName value
     */
    public String getSqlTypeName() {
        return _sqlTypeName;
    }

    /**
     * Gets the SqlName attribute of the Column object
     *
     * @return The SqlName value
     */
    public String getSqlName() {
        return _sqlName;
    }

    /**
     * Gets the Pk attribute of the Column object
     *
     * @return The Pk value
     */
    public boolean isPk() {
        return _isPk;
    }

    /**
     * Gets the Fk attribute of the Column object
     *
     * @return The Fk value
     */
    public boolean isFk() {
        return _isFk;
    }

    /**
     * Gets the Nullable attribute of the Column object
     *
     * @return The Nullable value
     */
    public final boolean isNullable() {
        return _isNullable;
    }

    /**************eidt by zhengjing ***************/
    public final String getJdbcTypeName() {
        if (_jdbcTypeName == null) return "OTHER";
        return _jdbcTypeName;
    }

    /**
     * Gets the Indexed attribute of the DbColumn object
     *
     * @return The Indexed value
     */
    public final boolean isIndexed() {
        return _isIndexed;
    }

    /**
     * Gets the Unique attribute of the DbColumn object
     *
     * @return The Unique value
     */
    public boolean isUnique() {
        return _isUnique;
    }

    /**
     * Gets the DefaultValue attribute of the DbColumn object
     *
     * @return The DefaultValue value
     */
    public final String getDefaultValue() {
        return _defaultValue;
    }

    /**
     * Describe what the method does
     *
     * @return Describe the return value
     * @todo-javadoc Write javadocs for method
     * @todo-javadoc Write javadocs for return value
     */
    public int hashCode() {
        return (getTable().getSqlName() + "#" + getSqlName()).hashCode();
    }

    /**
     * Describe what the method does
     *
     * @param o Describe what the parameter does
     * @return Describe the return value
     * @todo-javadoc Write javadocs for method
     * @todo-javadoc Write javadocs for method parameter
     * @todo-javadoc Write javadocs for return value
     */
    public boolean equals(Object o) {
        // we can compare by identity, since there won't be dupes
        return this == o;
    }

    /**
     * Describe what the method does
     *
     * @return Describe the return value
     * @todo-javadoc Write javadocs for method
     * @todo-javadoc Write javadocs for return value
     */
    public String toString() {
        return getSqlName();
    }

    /**
     * Describe what the method does
     *
     * @return Describe the return value
     */
    protected final String prefsPrefix() {
        return "tables/" + getTable().getSqlName() + "/columns/" + getSqlName();
    }

    /**
     * Sets the Pk attribute of the DbColumn object
     *
     * @param flag The new Pk value
     */
    void setFk(boolean flag) {
        _isFk = flag;
    }

    public String getColumnName() {
        return StringHelper.makeAllcolWordFirstLetterUpperCase(getSqlName());
    }

    public String getColumnNameLower() {
        return StringHelper.uncapitalize(getColumnName());
    }

    public boolean getIsNotIdOrVersionField() {
        return !isPk();
    }

    public String getValidateString() {
        String result = getNoRequiredValidateString();
        if (!isNullable()) {
            result = "required " + result;
        }
        return result;
    }

    public String getNoRequiredValidateString() {
        String result = "";
        if (getSqlName().indexOf("mail") >= 0) {
            result += "validate-email ";
        }
        if (DatabaseDataTypesUtils.isFloatNumber(getSqlType(), getSize(), getDecimalDigits())) {
            result += "validate-number ";
        }
        if (DatabaseDataTypesUtils.isIntegerNumber(getSqlType(), getSize(), getDecimalDigits())) {
            result += "validate-integer ";
        }
//		if(DatabaseDataTypesUtils.isDate(getSqlType(), getSize(), getDecimalDigits())) {
//			result += "validate-date ";
//		}
        return result;
    }

    public boolean getIsDateTimeColumn() {
        return DatabaseDataTypesUtils.isDate(getSqlType(), getSize(), getDecimalDigits());
    }

    public boolean isHtmlHidden() {
        return isPk() && _table.isSingleId();
    }

    public String getJavaType() {
        return DatabaseDataTypesUtils.getPreferredJavaType(getSqlType(), getSize(), getDecimalDigits());
    }

    public String getRemarks() {
        return _remarks;
    }


    /******************* edit *****************************/
    public boolean getIsdicColumn() {
        String sqlName = getSqlName();
        if (sqlName.indexOf("DIC_") > -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getIstextareaColumn() {

        if (getSize() >= 200) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getIscheckBoxColumn() {
        String sqlName = getSqlName();
        if (sqlName.indexOf("CBOX_") > -1) {
            return true;
        } else {
            return false;
        }
    }

    //radioBox
    public boolean getIsradioBoxColumn() {
        String sqlName = getSqlName();
        if (sqlName.indexOf("RBOX_") > -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getFk() {
        return isFk();
    }
}
