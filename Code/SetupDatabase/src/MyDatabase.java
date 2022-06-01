import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyDatabase
{
  private static MyDatabase instance;
  private String postgreSQLpassword;

  /**
   * A constructor registering the Driver.
   * @param postgreSQLpassword the password to the users PostgreSQL database.
   * @throws SQLException if registering the driver fails
   */
  private MyDatabase(String postgreSQLpassword) throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
    this.postgreSQLpassword = postgreSQLpassword;
  }

  /**
   * Method creating an instance variable of the class for the first time.
   * Then returning the same variable every time its called.
   * @param postgreSQLpassword the password to the users PostgreSQL database.
   * @return MyDataBase instance variable.
   * @throws SQLException if the connection to the database fails
   */
  public static synchronized MyDatabase getInstance(String postgreSQLpassword) throws SQLException
  {
    if (instance == null)
    {
      instance = new MyDatabase(postgreSQLpassword);
    }
    return instance;
  }

  /**
   * Method creating a connection to the postgreSQL database.
   * @return database connection
   * @throws SQLException if the connection to the database fails
   */
  private Connection getConnection()
  {
    try
    {
      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", postgreSQLpassword);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method creating the database in postgreSQL.
   * @throws SQLException if the connection to the database fails
   */
  public void createDatabase() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement createDB = connection.prepareStatement(
          "-- DELETE ALL --\n" + "DROP SCHEMA IF EXISTS hoteldatabase CASCADE;\n"
              + "--------------------------\n" + "\n" + "\n"
              + "CREATE SCHEMA hoteldatabase;\n" + "SET SCHEMA 'hoteldatabase';\n" + "\n"
              + "CREATE TABLE IF NOT EXISTS room\n" + "(\n"
              + "    roomID   varchar(20) PRIMARY KEY NOT NULL,\n"
              + "    roomType varchar(30)             NOT NULL CHECK (roomType IN ('Family', 'Single', 'Double', 'Suite', 'Conference')),\n"
              + "    nrBeds   integer                 NOT NULL CHECK ( nrBeds BETWEEN 1 AND 20),\n"
              + "    dailyPrice  integer NOT NULL CHECK ( dailyPrice > 0 )\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS guest\n" + "(\n"
              + "    username varchar(100) NOT NULL PRIMARY KEY,\n"
              + "    fName    VARCHAR(60)  NOT NULL,\n"
              + "    lName    VARCHAR(60)  NOT NULL,\n"
              + "    email    VARCHAR(60) CHECK ( email LIKE ('%@%')),\n"
              + "    phoneNr  integer      NOT NULL\n" + ");\n"
              + "CREATE TABLE IF NOT EXISTS roomBooking\n" + "(\n"
              + "    bookingID SERIAL PRIMARY KEY,\n" + "    startDate date,\n"
              + "    endDate   date,\n" + "    guest     varchar(100),\n"
              + "    roomID    varchar(20),\n" + "    state     varchar(12),\n"
              + "    FOREIGN KEY (roomID) REFERENCES room (roomID),\n"
              + "    FOREIGN KEY (guest) REFERENCES guest (username)\n" + ");\n"
              + "\n" + "CREATE TABLE IF NOT EXISTS login\n" + "(\n"
              + "    username     varchar(100) PRIMARY KEY,\n"
              + "    userPassword varchar(100) NOT NULL,\n"
              + "    FOREIGN KEY (username) REFERENCES guest (username)\n"
              + ");\n" + "\n" + "ALTER TABLE roomBooking\n"
              + "    ADD CONSTRAINT\n"
              + "        start_date_is_before_current_date CHECK ( startDate >= CURRENT_DATE);\n"
              + "\n" + "ALTER TABLE roomBooking\n" + "    ADD CONSTRAINT\n"
              + "        end_date_is_before_start_date CHECK ( startDate < endDate);\n"
              + "\n" + "\n" + "-------------------------------<\n" + "\n"
              + "-----------Trigger------------->\n"
              + "-- Checking if this room is not booked during selected period\n"
              + "CREATE OR REPLACE FUNCTION double_booking()\n"
              + "    RETURNS trigger AS\n" + "$BODY$\n" + "DECLARE\n"
              + "    vBookingCount NUMERIC;\n" + "\n" + "BEGIN\n" + "\n" + "\n"
              + "    SELECT COUNT(*)\n" + "    INTO vBookingCount\n"
              + "    FROM regularBookings\n" + "    WHERE roomID = new.roomID\n"
              + "        AND (new.startDate BETWEEN startDate AND endDate\n"
              + "            OR new.endDate BETWEEN startDate AND endDate)\n"
              + "       OR (new.startDate < startDate AND new.endDate > endDate)\n"
              + "       OR (new.startDate > startDate AND new.endDate < endDate);\n"
              + "\n" + "\n" + "    IF (vBookingCount > 0) THEN\n"
              + "        RAISE EXCEPTION 'Room % is already booked during these dates',\n"
              + "            new.roomID;\n" + "    END IF;\n"
              + "    RETURN new;\n" + "\n" + "END\n"
              + "$BODY$ LANGUAGE plpgsql;\n" + "\n"
              + "DROP TRIGGER IF EXISTS BookingDate\n" + "    ON roomBooking;\n"
              + "\n" + "-- attaching trigger to roomBooking\n"
              + "CREATE TRIGGER BookingDate\n" + "    BEFORE INSERT\n"
              + "    ON roomBooking\n" + "    FOR EACH ROW\n"
              + "EXECUTE PROCEDURE double_booking();\n" + "\n" + "\n" + "\n"
              + "-------------------------------<\n" + "\n"
              + "-----------Trigger------------->\n" + "\n"
              + "-------------------------------<\n" + "\n"
              + "--------------------------------\n"
              + "------ NEW FUNCTION ------------\n" + "\n"
              + "CREATE OR REPLACE FUNCTION update_booking()\n"
              + "    RETURNS trigger AS\n" + "$BODY$\n" + "DECLARE\n"
              + "    vBookingCount NUMERIC;\n" + "\n" + "BEGIN\n" + "\n" + "\n"
              + "    SELECT COUNT(*)\n" + "    INTO vBookingCount\n"
              + "    FROM regularBookings\n" + "    WHERE roomID = new.roomID\n"
              + "        AND bookingID != new.bookingID\n"
              + "        AND (new.startDate BETWEEN startDate AND endDate\n"
              + "            OR new.endDate BETWEEN startDate AND endDate\n"
              + "       OR (new.startDate < startDate AND new.endDate > endDate)\n"
              + "       OR (new.startDate > startDate AND new.endDate < endDate));\n"
              + "\n" + "\n" + "    IF (vBookingCount > 0) THEN\n"
              + "        RAISE EXCEPTION 'Room % is already booked during these dates.',\n"
              + "            new.roomID;\n" + "    END IF;\n"
              + "    RETURN new;\n" + "\n" + "END\n"
              + "$BODY$ LANGUAGE plpgsql;\n" + "\n" + "\n"
              + "CREATE TRIGGER BookingDateUpdate\n" + "    BEFORE UPDATE\n"
              + "    ON roomBooking\n" + "    FOR EACH ROW\n"
              + "    WHEN ( old.state = new.state )\n"
              + "EXECUTE PROCEDURE update_booking();\n" + "\n"
              + "-----------Views--------------->\n"
              + "create view conferenceRooms as\n" + "select *\n"
              + "from room\n" + "where roomType = 'Conference';\n" + "\n" + "\n"
              + "create view regularRooms as\n" + "select *\n" + "from room\n"
              + "where roomType not in ('Conference');\n" + "\n"
              + "create view regularBookings as\n" + "    SELECT *\n"
              + "        from roomBooking\n"
              + "            where state not in ('Cancelled')\n"
              + "ORDER BY bookingID;\n" + "\n"
              + "create view cancelledBookings AS\n" + "    SELECT *\n"
              + "FROM roomBooking\n" + "WHERE state IN ('Cancelled')\n"
              + "ORDER BY bookingID;\n" + "\n"
      );

      createDB.executeUpdate();
    }
  }

  /**
   * Method inserting data into the database.
   * @throws SQLException if the connection to the database fails
   */
  public void insertData() throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement insertData = connection.prepareStatement(
          "SET SCHEMA 'hoteldatabase';"
              + "insert into guest(username, fName, lName, email, phoneNr)\n"
              + "values ('bob','Bob','Builder','BobBuilder@gmail.com', 88851515);\n"
              + "insert into login(username, userPassword)\n"
              + "values ('bob', 'builder');\n"
              + "insert into guest(username, fName, lName, email, phoneNr)\n"
              + "values ('julia','Julia','Mcclain', 'JuliaMcclain@gmail.com', 15637557);\n"
              + "insert into login(username, userPassword)\n"
              + "values ('julia', 'password');\n"
              + "insert into guest(username, fName, lName, email, phoneNr)\n"
              + "values ('norman','Norman','William', 'NormanWilliam@gmail.com', 12637227);\n"
              + "insert into login(username, userPassword)\n"
              + "values ('norman', 'password');\n"
              + "insert into guest(username, fName, lName, email, phoneNr)\n"
              + "values ('Filip','Filip','Joyner', 'Flip@gmail.com', 12437212);\n"
              + "insert into login(username, userPassword)\n"
              + "values ('Filip', 'password');\n" + "\n" + "\n"
              + "insert into room(roomID, roomType, nrBeds, dailyPrice)\n"
              + "values ('202','Family',4,950);\n"
              + "insert into room(roomID, roomType, nrBeds, dailyPrice)\n"
              + "values ('101','Single',1,350);\n"
              + "insert into room(roomID, roomType, nrBeds, dailyPrice)\n"
              + "values ('210','Double',4,650);\n"
              + "insert into room(roomID, roomType, nrBeds, dailyPrice)\n"
              + "values ('303','Family',5,1200);\n"
              + "insert into room(roomID, roomType, nrBeds, dailyPrice)\n"
              + "values ('104','Single',1,350);\n" + "\n"
              + "insert into roomBooking(startDate, endDate, guest, roomID, state)\n"
              + "values ('2022-06-09', '2022-06-11','Filip','303','Booked');\n"
              + "insert into roomBooking(startDate, endDate, guest, roomID, state)\n"
              + "values ('2022-07-11', '2022-07-14','Filip','202','Booked');\n"
              + "insert into roomBooking(startDate, endDate, guest, roomID, state)\n"
              + "values ('2022-07-11', '2022-07-17','bob','210','Booked');\n"
              + "insert into roomBooking(startDate, endDate, guest, roomID, state)\n"
              + "values ('2022-06-09', '2022-06-14','julia','104','Booked');\n"
              + "insert into roomBooking(startDate, endDate, guest, roomID, state)\n"
              + "values ('2022-06-22', '2022-06-24','norman','101','Booked');");

      insertData.executeUpdate();
    }

  }

}