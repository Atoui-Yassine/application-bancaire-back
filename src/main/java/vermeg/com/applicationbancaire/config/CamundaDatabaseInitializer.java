package vermeg.com.applicationbancaire.config;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class CamundaDatabaseInitializer {
    private final DataSource dataSource;

    public CamundaDatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initialize() {
        try (Connection connection = dataSource.getConnection()) {
            // Vérifier si la table Camunda 'ACT_RU_TASK' existe
            if (!isTableExist(connection, "ACT_RU_TASK")) {
                // Si la table n'existe pas, exécuter le script SQL pour créer les tables
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/mysql_engine_7.21.0.sql"));

                System.out.println("Camunda tables created successfully.");
            } else {
                System.out.println("Camunda tables already exist. Skipping initialization.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute Camunda SQL script.", e);
        }
    }

    private boolean isTableExist(Connection connection, String tableName) throws SQLException {
        // Vérifie si la table existe dans la base de données
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + tableName + "'")) {
            return rs.next();
        }
    }
}
