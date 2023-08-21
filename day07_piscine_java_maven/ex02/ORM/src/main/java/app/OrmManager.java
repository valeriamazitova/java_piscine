package app;

import annotations.OrmColumn;
import annotations.OrmColumnId;
import annotations.OrmEntity;

import java.lang.reflect.Field;
import java.sql.*;

public class OrmManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public void initialize(Class<?> clazz) {
        if (clazz.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity ormEntity = clazz.getAnnotation(OrmEntity.class);
            String tableName = ormEntity.table();

            // Generate SQL for creating table
            StringBuilder createTableSql = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                    .append(tableName)
                    .append(" (");

            Field[] fields = clazz.getDeclaredFields();
            boolean firstColumn = true;
            for (Field field : fields) {
                if (field.isAnnotationPresent(OrmColumn.class)) {
                    OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                    String columnName = ormColumn.name();
                    String columnType = getColumnType(field.getType());
                    int length = ormColumn.length();

                    if (!firstColumn) {
                        createTableSql.append(", ");
                    }
                    createTableSql.append(columnName).append(" ").append(columnType);
                    if (length > 0) {
                        createTableSql.append("(").append(length).append(")");
                    }
                    if (field.isAnnotationPresent(OrmColumnId.class)) {
                        createTableSql.append(" AUTO_INCREMENT PRIMARY KEY");
                    }
                    firstColumn = false;
                }
            }
            createTableSql.append(")");

            // Execute SQL to create table
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                try (PreparedStatement statement = connection.prepareStatement(createTableSql.toString())) {
                    statement.executeUpdate();
                    System.out.println("Table created: " + tableName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Other methods for save, update, findById
    // ...

    public void save(Object entity) {

    }

    public void update(Object entity) {

    }

    public <T> T findById(Long id, Class<T> clazz) {
        if (clazz.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity ormEntity = clazz.getAnnotation(OrmEntity.class);
            String tableName = ormEntity.table();

            Field idField = null;
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(OrmColumnId.class)) {
                    idField = field;
                    break;
                }
            }

            if (idField != null) {
                String columnName = idField.getAnnotation(OrmColumn.class).name();
                String selectSql = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement statement = connection.prepareStatement(selectSql)) {
                    statement.setLong(1, id);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            T entity = clazz.getDeclaredConstructor().newInstance();
                            for (Field field : clazz.getDeclaredFields()) {
                                if (field.isAnnotationPresent(OrmColumn.class)) {
                                    field.setAccessible(true);
                                    String fieldColumnName = field.getAnnotation(OrmColumn.class).name();
                                    Class<?> fieldType = field.getType();

                                    if (fieldType == String.class) {
                                        field.set(entity, resultSet.getString(fieldColumnName));
                                    } else if (fieldType == Integer.class || fieldType == int.class) {
                                        field.set(entity, resultSet.getInt(fieldColumnName));
                                    } else if (fieldType == Double.class || fieldType == double.class) {
                                        field.set(entity, resultSet.getDouble(fieldColumnName));
                                    } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                                        field.set(entity, resultSet.getBoolean(fieldColumnName));
                                    } else if (fieldType == Long.class || fieldType == long.class) {
                                        field.set(entity, resultSet.getLong(fieldColumnName));
                                    }
                                }
                            }
                            return entity;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getColumnType(Class<?> fieldType) {
        if (fieldType == String.class) {
            return "VARCHAR";
        } else if (fieldType == Integer.class || fieldType == int.class) {
            return "INT";
        } else if (fieldType == Double.class || fieldType == double.class) {
            return "DOUBLE";
        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
            return "BOOLEAN";
        } else if (fieldType == Long.class || fieldType == long.class) {
            return "BIGINT";
        }
        return "VARCHAR"; // Default to String
    }
}

