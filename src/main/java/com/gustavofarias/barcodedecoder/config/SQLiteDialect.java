package com.gustavofarias.barcodedecoder.config;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

/**
 * Custom Hibernate dialect for SQLite, based on H2Dialect.
 * SQLite is not officially supported by Hibernate, so this class adapts it for basic compatibility.
 */
public class SQLiteDialect extends H2Dialect {

    public SQLiteDialect() {
        // Sets the database version (SQLite version 3.42 in this case)
        super(DatabaseVersion.make(3, 42));
    }

    /**
     * Maps standard SQL types to SQLite types.
     */
    @Override
    protected String columnType(int sqlTypeCode) {
        switch (sqlTypeCode) {
            case Types.BIT:
            case Types.BOOLEAN:
                return "boolean";
            case Types.TINYINT:
                return "tinyint";
            case Types.SMALLINT:
                return "smallint";
            case Types.INTEGER:
                return "integer";
            case Types.BIGINT:
                return "bigint";
            case Types.FLOAT:
                return "float";
            case Types.DOUBLE:
                return "double";
            case Types.NUMERIC:
                return "numeric";
            case Types.DECIMAL:
                return "decimal";
            case Types.CHAR:
                return "char";
            case Types.VARCHAR:
                return "varchar";
            case Types.LONGVARCHAR:
                return "longvarchar";
            case Types.DATE:
                return "date";
            case Types.TIME:
                return "time";
            case Types.TIMESTAMP:
                return "timestamp";
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
            case Types.BLOB:
                return "blob";
            case Types.CLOB:
                return "clob";
            default:
                return super.columnType(sqlTypeCode);
        }
    }

    /**
     * Registers SQL function patterns compatible with SQLite.
     */
    @Override
    public void initializeFunctionRegistry(FunctionContributions functionContributions) {
        super.initializeFunctionRegistry(functionContributions);

        // CONCAT using SQLite's || operator
        functionContributions.getFunctionRegistry().registerPattern(
                "concat",
                "?1 || ?2",
                functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.STRING)
        );

        // MOD function
        functionContributions.getFunctionRegistry().registerPattern(
                "mod",
                "mod(?1, ?2)",
                functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.INTEGER)
        );

        // SUBSTR and SUBSTRING both mapped to SQLite substr function
        functionContributions.getFunctionRegistry().registerPattern(
                "substr",
                "substr(?1, ?2)",
                functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.STRING)
        );

        functionContributions.getFunctionRegistry().registerPattern(
                "substring",
                "substr(?1, ?2)",
                functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.STRING)
        );
    }

    /**
     * Provides support for identity (auto-increment) columns.
     */
    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl() {
            @Override
            public boolean supportsIdentityColumns() {
                return true;
            }

            @Override
            public String getIdentitySelectString(String table, String column, int type) {
                // SQLite uses last_insert_rowid() to get the last inserted ID
                return "select last_insert_rowid()";
            }

            @Override
            public String getIdentityColumnString(int type) {
                // Defines the identity column as "integer"
                return "integer";
            }
        };
    }

    /**
     * Indicates that SQLite supports temporary tables.
     */
    @Override
    public boolean supportsTemporaryTables() {
        return true;
    }

    /**
     * Indicates that current timestamp selection is supported.
     */
    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    /**
     * Indicates whether the current timestamp string is callable (it's not for SQLite).
     */
    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    /**
     * Returns the SQL to get the current timestamp in SQLite.
     */
    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    /**
     * Declares support for UNION ALL clause.
     */
    @Override
    public boolean supportsUnionAll() {
        return true;
    }

    /**
     * SQLite does not support altering table constraints directly.
     */
    @Override
    public boolean hasAlterTable() {
        return false;
    }

    /**
     * Indicates that DROP CONSTRAINT is not supported.
     */
    @Override
    public boolean dropConstraints() {
        return false;
    }

    /**
     * SQL syntax for adding a column.
     */
    @Override
    public String getAddColumnString() {
        return "add column";
    }

    /**
     * SQLite doesn't support "FOR UPDATE" clause in a meaningful way.
     */
    @Override
    public String getForUpdateString() {
        return "";
    }

    /**
     * SQLite doesn't support outer join with "FOR UPDATE".
     */
    @Override
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    /**
     * Supports checking table existence before creation.
     */
    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    /**
     * Cascade deletes are not supported at the dialect level in SQLite.
     */
    @Override
    public boolean supportsCascadeDelete() {
        return false;
    }
}
