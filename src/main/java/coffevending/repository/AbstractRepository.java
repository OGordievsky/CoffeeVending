package coffevending.repository;

import coffevending.repository.dbconnection.DBConnection;

public  abstract class AbstractRepository {
    public final DBConnection dataBaseConnection = new DBConnection();
}
